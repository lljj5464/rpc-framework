package github.lljj.config;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lihan
 * @create 2021-10-23 1:08 上午
 */
@Getter
@Setter
public class ThreadPoolConfig {
    private static final int DEFAULT_CORE_POOL_SIZE = 10;
    private static final int DEFAULT_MAX_POOL_SIZE = 100;
    private static final int DEFAULT_KEEP_ALIVE_tIME = 1;
    private static final TimeUnit DEFAULT_UNIT = TimeUnit.MINUTES;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;

    private int corePoolSize = DEFAULT_CORE_POOL_SIZE;
    private int maximumPoolSize = DEFAULT_MAX_POOL_SIZE;
    private long keepAliveTime = DEFAULT_KEEP_ALIVE_tIME;
    private TimeUnit timeUnit = DEFAULT_UNIT;
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
}
