package com;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class 编码与解码 {
    public static void main(String[] args) {
        final Charset gbk = Charset.forName("GBK");
        final CharBuffer charBuffer = CharBuffer.wrap("你吗死了".toCharArray());
        final ByteBuffer encode = gbk.encode(charBuffer);
        final CharBuffer decode = gbk.decode(encode);
        System.out.println(decode.position());
        System.out.println(decode.array());
    }
}
