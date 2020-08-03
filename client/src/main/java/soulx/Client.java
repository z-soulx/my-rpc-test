package soulx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;
import soulx.proxy.ClientHandle;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;

/**
 * @program: demo
 * @description: 客户端使用
 * @author: soulx
 * @create: 2020-08-03 10:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
//@ActiveProfiles("dev")
public class Client {


    @Resource
    private HelloService helloService;

    public static void main(String[] args) {

    }

    @Test
    public void JDK(){
        // jdk 代理模拟请求
        HelloService helloService = (HelloService)Proxy.newProxyInstance(HelloService.class.getClassLoader(),new Class[]{HelloService.class},new ClientHandle());
        String hello = helloService.hello("123");
        System.out.println(hello);
    }
    @Test
    public void SpringAop(){
        String hello = helloService.hello("123");
        System.out.println(hello);
    }
}
