package soulx.transport.netty;

import soulx.transport.Transport;
import soulx.transport.command.Command;

import java.util.concurrent.CompletableFuture;

/**
 * @program: demo
 * @description: 基于netty框架实现基础通信
 * @author: soulx
 * @create: 2020-08-02 21:32
 **/
public class NettyTransport implements Transport {
    @Override
    public CompletableFuture<Command> send(Command request) {
        // 构建返回值
        CompletableFuture<Command> completableFuture = new CompletableFuture<>();

        return null;
    }
}
