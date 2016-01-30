package com.netty.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;

/**
 * Created by raja on 27/11/15.
 */
public class HttpAggregatorInitializer extends ChannelInitializer<SocketChannel> {
    private final boolean client;

    public HttpAggregatorInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (client) {
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
//            pipeline.addLast("codec", new HttpServerCodec(4096, 8192, 8192, false));
            pipeline.addLast("codec", new HttpServerCodec(4096, 8192, 8192, false));
        }
//        pipeline.addLast("decompressor", new HttpContentDecompressor());
//        pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));

    }
}