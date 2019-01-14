package com.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * IO与NIO的区别
 * IO是面向流的。NIO是面向缓冲区的。
 * 不太好理解。主要就是数据的载体问题，
 * IO传输的数据是在流中，单向的流，分为输入流，输出流。数据像水一样流动
 * NIO中没有流的概念，但是有管道的概念，管道就像铁路，铁路不能直接运送货物，还得有火车。缓冲区就相当于火车。
 * 管道是没有方向的。管道负责连接，缓冲区负责装载数据
 */
public class NioTest01 {

    /**
     * buffer
     * buffer负责存储数据。buffer本质就是数组
     * 在NIO中，根据数据类型的不同，提供了多个buffer（boolean除外，其他的基本数据类型全部都有）
     * IntBuffer
     * LongBuffer
     * CharBuffer
     * ByteBuffer
     * 等等。。。。
     * <p>
     * 缓存区统一用allocate()方法来生成
     * 统一用get，put方法来存取数据
     */
    @Test
    public void test_buffer() {

        ByteBuffer buffer = ByteBuffer.allocate(100);
        byte b = 4;
        buffer.put(b);
        buffer.position(0);
        System.out.println(buffer.get());
    }

    /**
     * 要想正确的操作缓冲区，有几个核心概念必须要理解，缓冲区的本质是数组
     * private int mark = -1;       调用mark() 会记录当前position位置 后续调用reset()时，会把position重置到mark位置
     * private int position = 0;    当前指针在数组中的位置
     * private int limit;           limit之前的数据可以进行读写。limit之后的数据不能进行读写
     * private int capacity;        缓冲区的容量（数组的大小）
     * <p>
     * 0<=mark<=position<=limit<=capacity
     */
    @Test
    public void test_缓冲区核心概念() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        /**
         * 写数据
         * 每put完一个字节，position自动指向下一个位置
         */
        buffer.put("hello".getBytes());
        System.out.println("===position " + buffer.position());
        System.out.println("===limit " + buffer.limit());
        System.out.println("============flip=============");
        /**
         * 如果调用flip方法，那么position 跟 limit 都会变化
         * flip是翻转的意思
         */
        buffer.flip();
        System.out.println("===position " + buffer.position());
        System.out.println("===limit " + buffer.limit());
        //再flip一次，limit就变为0了，可见，flip的意思是把limit = position,position=0
        buffer.flip();
        System.out.println("===position " + buffer.position());
        System.out.println("===limit " + buffer.limit());

        /**
         * 读
         * limit 在读的时候，用处较大，可以理解为读到哪个位置
         */
        System.out.println("===========get==============");
        byte[] b = new byte[buffer.limit()];
        buffer.get(b);
        System.out.println(new String(b));
        System.out.println("===position " + buffer.position());//position又变了！！
        System.out.println("===limit " + buffer.limit());

        /**
         * rewind
         * 倒带的意思,此时又可以读了
         */
        System.out.println("===========rewind==============");
        buffer.rewind();
        System.out.println("===position " + buffer.position());//position又变了！！
        System.out.println("===limit " + buffer.limit());

        /**
         * clear
         * 清空缓冲区
         * 非常重要： 只是重置了position跟limit ，里面的数据还在
         */
        System.out.println("===========clear==============");
        buffer.clear();
        System.out.println("===position " + buffer.position());//position又变了！！
        System.out.println("===limit " + buffer.limit());//limit 也变了
        /**
         * mark
         * reset
         * 挺好理解的，可以看上面的注释
         */

        //缓冲区中是否还有可以操作的数据
        if (buffer.hasRemaining()) {
            //可以操作的数据的数量
            System.out.println(buffer.remaining());
        }
    }

    /**
     * 所谓非直接缓冲区，就是jvm的内存
     * 所谓直接缓冲区，就是操作系统的内存
     * 一般而言，java应用程序读取或者写入硬盘数据，都需要一个内核内存过度一下，将数据从内核内存copy到应用内存
     * 有了直接缓冲区，就省下了copy的过程，应用程序直接操作操作系统的内存。
     */
    @Test
    public void test_直接缓冲区与非直接缓冲区() {
        //非直接内存
        Buffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.isDirect());
        //直接内存
        buffer = ByteBuffer.allocateDirect(10);
        System.out.println(buffer.isDirect());
    }

    /**
     * 通道Channel
     * 指应用程序与IO源（网络，磁盘等）之间建立的连接。
     * 再次解读IO流
     * 当应用程序请求磁盘数据时，在数据传输的过程中，时不需要cpu干预的。是cpu委托一个叫DMA的东西来干活，干完活告诉cpu一声
     * 这个DMA跟应用程序之间的数据传输，就可以理解为IO流
     * 下面是百度百科资料
     * DMA(Direct Memory Access，直接内存存取) 是所有现代电脑的重要特色，它允许不同速度的硬件装置来沟通，而不需要依赖于 CPU 的大量中断负载。
     * 否则，CPU 需要从来源把每一片段的资料复制到暂存器，然后把它们再次写回到新的地方。在这个时间中，CPU 对于其他的工作来说就无法使用。
     *
     * DMA还是依赖cpu工作的。只是减轻了cpu的负担，但是通道（Channel）拥有完全独立的处理器。拥有自己的命令集
     *
     * 通道负责缓冲区中数据的传输。数据在缓存区中。通道本身不存储数据，需要配合缓冲区来进行数据传输，就像铁路，火车与乘客的关系。
     *
     *
     *
     */

    /**
     * 用直接缓冲区的速度要比用流（间接缓冲区）的数据快，而且快很多
     *
     * @throws IOException
     */
    @Test
    public void test_采用直接缓存区复制文件() throws IOException {
        //在磁盘跟应用程序之间，建立一个通道，这个通道只能读
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/peter/Pictures/我.jpg"), StandardOpenOption.READ);
        //在内存跟磁盘之间建立一个通道，这个通道可读可写，如果是写，那么就是覆盖模式
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/peter/Pictures/还是我.jpg"), StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        //在内存中创建一个直接缓存，我觉得也可以叫非堆内存。接收通道中的数据
        MappedByteBuffer inMap = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        //将直接内存中的数据取出来，放入jvm内存
        byte[] b = new byte[inMap.limit()];
        inMap.get(b);
        //在内存中创建一个直接缓存，用来装载要写入的数据
        MappedByteBuffer outMap = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        //将数据写入直接缓存，这个时候火车就会开通，直接送达目的地
        outMap.put(b);


        inChannel.close();
        outChannel.close();
    }

    /**
     * 通道之间的数据传输
     * 也是走的直接缓冲区的方式
     */
    @Test
    public void test_通道之间的数据传输() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/peter/Pictures/我.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/peter/Pictures/你.jpg"), StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inChannel.close();
        outChannel.close();
    }
}
