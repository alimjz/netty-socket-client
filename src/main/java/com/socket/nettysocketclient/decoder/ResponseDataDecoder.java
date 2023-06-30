package com.socket.nettysocketclient.decoder;

import com.socket.nettysocketclient.model.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ResponseData responseData = new ResponseData();
        responseData.setResponse(in.readInt());
        int stringIndex = in.readInt();
        responseData.setText(in.readCharSequence(stringIndex, StandardCharsets.UTF_8).toString());
        out.add(responseData);
    }
}
