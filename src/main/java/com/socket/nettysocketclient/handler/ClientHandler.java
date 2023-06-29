package com.socket.nettysocketclient.handler;

import com.socket.nettysocketclient.model.RequestData;
import com.socket.nettysocketclient.model.ResponseData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestData requestData = new RequestData();
        requestData.setIntInput(22);
        requestData.setStringValue("Jack pot");
        ctx.writeAndFlush(requestData);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResponseData responseData = (ResponseData) msg;
        System.out.println(responseData);
        ctx.close();
    }
}
