/*
 * Copyright 2013 The Netty Project
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
package com.netty;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;
import io.netty.handler.ssl.SslContext;
import org.springframework.context.ApplicationContext;

public class HttpHelloWorldServerInitializer extends ChannelInitializer<SocketChannel> {

    private ApplicationContext applicationContext;
    private final SslContext sslCtx;

    public HttpHelloWorldServerInitializer(ApplicationContext appContext, SslContext sslCtx) {
        this.sslCtx = sslCtx;
        this.applicationContext = appContext;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }
//        p.addLast(new HttpServerCodec(4096, 8192, 8192, false));
        p.addLast( new HttpServerCodec(4096, 8192, 8192, false));
        p.addLast("decompressor", new HttpContentDecompressor());
        p.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
//        p.addLast(new HttpAggregatorInitializer(false));
        ServerBaseHandler channelHandler = applicationContext.getBean(ServerBaseHandler.class);
        channelHandler.setApplicationContext(applicationContext);
//        p.addLast(new HttpHelloWorldServerHandler());
        p.addLast(channelHandler);
        p.addLast(new HttpContentCompressor());
    }
}
