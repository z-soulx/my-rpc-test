package soulx.serialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soulx.serialize.impl.StringSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description: 序列化支持工具
 * @author: soulx
 * @create: 2020-08-02 20:34
 **/
@SuppressWarnings("unchecked")
public class SerializeSupport {
    private static final Logger logger = LoggerFactory.getLogger(SerializeSupport.class);
    private static Map<Class<?>/*序列化对象类型*/, Serializer<?>/*序列化实现*/> serializerMap = new HashMap<>();
    private static Map<Byte/*序列化实现类型*/, Class<?>/*序列化对象类型*/> typeMap = new HashMap<>();
    static {
        Serializer serializer = new StringSerializer();
//        for (Serializer serializer : ServiceSupport.loadAll(Serializer.class)) {
            registerType(serializer.type(), serializer.getSerializeClass(), serializer);
            logger.info("Found serializer, class: {}, type: {}.",
                    serializer.getSerializeClass().getCanonicalName(),
                    serializer.type());
//        }
    }
    private static <E> void registerType(byte type, Class<E> eClass, Serializer<E> serializer) {
        serializerMap.put(eClass, serializer);
        typeMap.put(type, eClass);
    }
    /**
    * @Description: 获取解析类型
    * @Author: soulx
    */
    private static byte parseEntryType(byte[] buffer) {
        return buffer[0];
    }
    public static <E> byte [] serialize(E  entry) {
        Serializer<E> serializer = (Serializer<E>) serializerMap.get(entry.getClass());
        if(serializer == null) {
            throw new SerializeException(String.format("Unknown entry class type: %s", entry.getClass().toString()));
        }
        int size = serializer.size(entry);
        byte[] bytes = new byte[size+1];
        bytes[0]= serializer.type();  //塞入序列化类型
         serializer.serialize(entry,bytes,1,size); //1 开始 因为第一字节序列是类型
        return bytes;
    }


    // 解析
    private static  <E> E parse(byte [] buffer, int offset, int length, Class<E> eClass) {
        Object entry =  serializerMap.get(eClass).parse(buffer, offset, length);
        if (eClass.isAssignableFrom(entry.getClass())) {
            return (E) entry;
        } else {
            throw new SerializeException("Type mismatch!");
        }
    }
    public static  <E> E parse(byte [] buffer) {
        return parse(buffer, 0, buffer.length);
    }

    private static  <E> E parse(byte[] buffer, int offset, int length) {
        byte type = parseEntryType(buffer);
        @SuppressWarnings("unchecked")
        Class<E> eClass = (Class<E> )typeMap.get(type);
        if(null == eClass) {
            throw new SerializeException(String.format("Unknown entry type: %d!", type));
        } else {
            return parse(buffer, offset + 1, length - 1,eClass);
        }

    }
}
