package com.sensors.mystudy.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ClientChannle {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 9999));
        socketChannel.write(Charset.defaultCharset().encode("aaa"));
        System.out.println("a");
    }
}
