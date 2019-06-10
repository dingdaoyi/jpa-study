/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.yanbing.manage.managecontrol.tcp;


import com.yanbing.manage.managecontrol.tcp.pojo.SubscribeReq;
import com.yanbing.manage.managecontrol.tcp.pojo.SubscribeResp;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
@Sharable
@Component
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
	    throws Exception {
	SubscribeReq req = (SubscribeReq) msg;
	if ("Lilinfeng".equalsIgnoreCase(req.getUserName())) {
	    System.out.println("Service accept client subscrib req : ["
		    + req.toString() + "]");
	    ctx.writeAndFlush(resp(req.getSubReqID()));
	}
    }

    private SubscribeResp resp(int subReqID) {
	SubscribeResp resp = new SubscribeResp();
	resp.setSubReqID(subReqID);
	resp.setRespCode(0);
	resp.setDesc("你好已完成");
	return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	cause.printStackTrace();
	ctx.close();// 发生异常，关闭链路
    }
}
