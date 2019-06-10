package com.yanbing.manage.managecontrol.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.List;

@Service
public class TcpDecoderHandler extends MessageToMessageDecoder  {
    private static final Logger LOGGER = LoggerFactory.getLogger(TcpDecoderHandler.class);
    @Override
    protected void decode(ChannelHandlerContext ctx, Object o, List list) throws UnsupportedEncodingException {
        byte[] data = o.toString().getBytes("gbk");
        ByteBuf buf = ctx.alloc().buffer(data.length);
        buf.writeBytes(data);
        LOGGER.info("解析client上报数据,{}",new String(data));
    }
}

