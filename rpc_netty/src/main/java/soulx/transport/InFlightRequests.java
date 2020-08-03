package soulx.transport;

import java.io.Closeable;
import java.util.Map;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @program: demo
 * @description: 所有在途的请求 统一管理
 * requestId 和返回的 completableFuture 绑定在一起，存储在该类
 * @author: soulx
 * @create: 2020-08-02 21:35
 **/
public class InFlightRequests implements Closeable {
    private final static long TIMEOUT_SEC = 10L;
    // 背压机制  防止服务端消费过慢，客户端内存爆表
    private final Semaphore semaphore = new Semaphore(10);
    private final Map<Integer, ResponseFuture> futureMap = new ConcurrentHashMap<>();
//    ScheduledExecutorService service = new ThreadPoolExecutor(1,1,0,NANOSECONDS,延时队列,Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledFuture scheduledFuture;
    public InFlightRequests() {
        scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(this::removeTimeoutFutures, TIMEOUT_SEC, TIMEOUT_SEC, TimeUnit.SECONDS);
    }

    public void put(ResponseFuture responseFuture) throws InterruptedException, TimeoutException {
        if(semaphore.tryAcquire(TIMEOUT_SEC, TimeUnit.SECONDS)) {
            futureMap.put(responseFuture.getRequestId(), responseFuture);
        } else {
            throw new TimeoutException();
        }
    }


// 超时释放
    private void removeTimeoutFutures() {
        futureMap.entrySet().removeIf(entry -> {
            if( System.nanoTime() - entry.getValue().getTimestamp() > TIMEOUT_SEC * 1000000000L) {
                semaphore.release();
                return true;
            } else {
                return false;
            }
        });
    }
//移除对应请求
    public ResponseFuture remove(int requestId) {
        ResponseFuture future = futureMap.remove(requestId);
        if(null != future) {
            semaphore.release();
        }
        return future;
    }
// 重写关闭
    @Override
    public void close() {
        scheduledFuture.cancel(true);
        scheduledExecutorService.shutdown();
    }
}
