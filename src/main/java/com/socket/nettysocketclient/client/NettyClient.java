package com.socket.nettysocketclient.client;

import com.socket.nettysocketclient.decoder.ResponseDataDecoder;
import com.socket.nettysocketclient.encoder.RequestDataEncoder;
import com.socket.nettysocketclient.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NettyClient {
    private int port = 9090;
    private String host = "localhost";

    @PostConstruct
    void init(){
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel socketChannel){
                    socketChannel.pipeline().addLast(new RequestDataEncoder(),
                            new ResponseDataDecoder(),
                            new ClientHandler());
                }
            });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
