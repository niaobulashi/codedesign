package com.hulang.codedesign.common.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @description: Netty服务端
 * @date: 2024/1/25 10:46
 * @author: HuLang
 * @version: 1.0
 */
public class HelloWorldServer {

    public static void main(String[] args) {
        // 创建一个ServerBootstrap对象
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 设置事件循环组，用于处理异步事件
        bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                // 设置通道类型为NioServerSocketChannel
                .channel(NioServerSocketChannel.class)
                // 设置子通道处理器
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    // 初始化连接通道
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 添加StringDecoder解码器
                        ch.pipeline().addLast(new StringDecoder());
                        // 添加StringEncoder编码器
                        ch.pipeline().addLast(new StringEncoder());
                        // 添加一个ChannelInboundHandlerAdapter类型的处理器
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            // 当通道激活时调用
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                // 打印通道以及"hello world"字符串
                                System.out.println(ctx.channel() + ",hello world");
                            }

                            // 当收到数据时调用
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                // 打印当前时间以及接收到的消息
                                System.out.println(new Date() + "：" + msg);
                            }
                        });
                    }
                }).bind(8081);
    }
}
