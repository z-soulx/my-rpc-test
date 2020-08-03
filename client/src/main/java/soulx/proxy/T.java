package soulx.proxy;

import org.springframework.stereotype.Service;
import soulx.HelloService;

/**
 * @program: demo
 * @description:
 * @author: soulx
 * @create: 2020-08-03 12:19
 **/
@Service
// 为了生成bean 代理，实现了接口
// 实际中可以在注入入口入手
public class T implements HelloService {
    @Override
    public String hello(String name) {
        return null;
    }
}
