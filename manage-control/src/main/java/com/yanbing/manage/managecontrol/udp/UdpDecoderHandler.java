package com.yanbing.manage.managecontrol.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;

@Service
public class UdpDecoderHandler extends MessageToMessageDecoder<DatagramPacket>  {
    private static final Logger LOGGER = LoggerFactory.getLogger(UdpDecoderHandler.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> out) throws Exception {
        ByteBuf byteBuf = datagramPacket.content();
        InetSocketAddress address = datagramPacket.sender();
        System.out.println("address = " + address);
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        String msg = new String(data, Charset.forName("gbk"));
        LOGGER.info("{}收到消息{}:" + msg);
        out.add(msg); //将数据传入下一个handler
    }
}
