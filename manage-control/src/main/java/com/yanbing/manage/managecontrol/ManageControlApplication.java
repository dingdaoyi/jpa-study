package com.yanbing.manage.managecontrol;

import com.yanbing.manage.managecontrol.tcp.EchoServerHandler;
import com.yanbing.manage.managecontrol.tcp.SubReqServerHandler;
import com.yanbing.manage.managecontrol.tcp.TcpDecoderHandler;
import com.yanbing.manage.managecontrol.tcp.TcpHandler;
import com.yanbing.manage.managecontrol.udp.UdpDecoderHandler;
import com.yanbing.manage.managecontrol.udp.UdpEncoderHandler;
import com.yanbing.manage.managecontrol.udp.UdpHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.netty.tcp.TcpServer;
import reactor.netty.udp.UdpServer;

import java.nio.charset.Charset;
import java.time.Duration;

@SpringBootApplication
public class ManageControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageControlApplication.class, args);
    }

    @Bean
    CommandLineRunner serverRunner(UdpDecoderHandler udpDecoderHanlder,
                                   UdpEncoderHandler udpEncoderHandler,
                                   UdpHandler udpHandler,
                                   TcpHandler tcpHandler,
                                   SubReqServerHandler subReqServerHandler
    ) {

        return strings -> {
            createUdpServer(udpDecoderHanlder, udpEncoderHandler, udpHandler);

            createDcpServer(subReqServerHandler, tcpHandler);
        };
    }

    private void createDcpServer( SubReqServerHandler subReqServerHandler, TcpHandler tcpHandler) {
        ByteBuf delemit = Unpooled.copiedBuffer("$_".getBytes());
        TcpServer.create().handle((in, out) -> {
            in.receive().asByteArray().subscribe();
            return Flux.never();
            //new DelimiterBasedFrameDecoder(1024,delemit)
        }).doOnConnection(conn -> conn.addHandler(new LoggingHandler(LogLevel.INFO))
                .addHandlerLast(new ObjectDecoder(1024*1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
        .addHandlerLast(new ObjectEncoder())
        .addHandlerLast(subReqServerHandler))
    //实例只写了如何添加handler,可添加delimiter，tcp生命周期，decoder，encoder等handler
        .port(1935) .bindNow();
    }

    /**
     * 创建UDP Server
     *
     * @param udpDecoderHandler： 用于解析UDP Client上报数据的handler
     * @param udpEncoderHandler： 用于向UDP Client发送数据进行编码的handler
     * @param udpHandler:        用户维护UDP链接的handler
     */
    private void createUdpServer(UdpDecoderHandler udpDecoderHandler, UdpEncoderHandler udpEncoderHandler, UdpHandler udpHandler) {
        UdpServer.create().handle((in, out) -> {
            in.receive().asByteArray().subscribe();
            return Flux.never();
        }).port(1934) //UDP Server端口
                .doOnBound(conn -> conn.addHandler("decoder", udpDecoderHandler)
                        .addHandler("encoder", udpEncoderHandler)
                        .addHandler("handler", udpHandler))
                //可以添加多个handler
                .bindNow(Duration.ofSeconds(30));
    }

}

