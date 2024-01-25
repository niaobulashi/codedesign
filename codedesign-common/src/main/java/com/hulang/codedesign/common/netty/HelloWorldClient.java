package com.hulang.codedesign.common.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.concurrent.TimeUnit;

/**
 * @description: Netty客户端
 * @date: 2024/1/25 10:51
 * @author: HuLang
 * @version: 1.0
 */
public class HelloWorldClient {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        Channel channel = bootstrap.connect("127.0.0.1", 8081).sync().channel();
        // 发送消息
        while (true) {
            channel.writeAndFlush("hello world..");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
