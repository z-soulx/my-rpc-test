package soulx.transport;

import soulx.transport.command.Command;

import java.util.concurrent.CompletableFuture;

/**
 * @program: demo
 * @description: 发送请求的接口定义
 * @author: soulx
 * @create: 2020-08-02 21:21
 **/
public interface Transport {
    /**
     * 发送请求命令
     * @param request 请求命令
     * @return 返回值是一个Future，Future
     */
    CompletableFuture<Command> send(Command request);
}
