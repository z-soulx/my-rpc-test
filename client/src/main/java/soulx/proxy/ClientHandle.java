package soulx.proxy;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: demo
 * @description:
 * @author: soulx
 * @create: 2020-08-03 11:05
 **/
public class ClientHandle implements InvocationHandler {
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("test");
        // 可以用socket 连接远端，返回后放入 的处理
        return "asdas";
    }
}
