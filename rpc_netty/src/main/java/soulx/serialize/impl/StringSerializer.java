package soulx.serialize.impl;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import soulx.serialize.Serializer;
import soulx.serialize.Types;

import java.nio.charset.StandardCharsets;

/**
 * @program: demo
 * @description: 字符串序列化
 * @author: soulx
 * @create: 2020-08-02 19:53
 **/
//@Component
public class StringSerializer  implements Serializer<String> {

    @Override
    public int size(String entry) {
        return entry.getBytes(StandardCharsets.UTF_8).length;
    }

    @Override
    public void serialize(String entry, byte[] bytes, int offset, int length) {
        byte[] entryBytes = entry.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(entry,0,bytes,offset,entry.length());
    }

    @Override
    public String parse(byte[] bytes, int offset, int length) {
        return new String(bytes,offset,length,StandardCharsets.UTF_8);
    }

    @Override
    public byte type() {
        return Types.TYPE_STRING;
    }

    @Override
    public Class<String> getSerializeClass() {
        return String.class;
    }
}
