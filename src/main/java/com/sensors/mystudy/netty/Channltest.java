package com.sensors.mystudy.netty;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.file.Paths;

/**
 * @author 唐跃文
 */
public class Channltest {
    public static void main(String[] args) throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("111.txt"));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        StringBuffer text = new StringBuffer();
        while (channel.read(buf) != -1) {
            buf.flip();
            // 读取 buf 中的数据
            while (buf.position() < buf.limit()) { 
                text.append((char) buf.get());
            }
            buf.clear(); // 清空 buffer，缓存区切换到写模式
        }
        System.out.println(text);


        ByteBuffer buffer = ByteBuffer.allocate(10);
        RandomAccessFile randomAccessFile = new RandomAccessFile("copy.yaml","rw");
        FileChannel writechannle = randomAccessFile.getChannel();
        for (int i = 0; i < text.length(); i++) {
            buffer.put((byte)text.charAt(i));
            if (buffer.position() == buffer.limit() || i == text.length() - 1) { // 缓存区已满或者已经遍历到最后一个字符
                buffer.flip(); // 将缓冲区由写模式置为读模式
                writechannle.write(buffer); // 将缓冲区的数据写到通道
                buffer.clear(); // 清空缓存区，将缓冲区置为写模式，下次才能使用
            }
        }
        channel.force(false);
        channel.close();
    }
}
