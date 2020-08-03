package soulx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: demo
 * @description:
 * @author: soulx
 * @create: 2020-08-03 12:07
 **/
@SpringBootApplication
@EnableAspectJAutoProxy
public class ClientApplication {
    public static void main( String[] args )
    {

        SpringApplication.run(ClientApplication.class,args);
    }
}
