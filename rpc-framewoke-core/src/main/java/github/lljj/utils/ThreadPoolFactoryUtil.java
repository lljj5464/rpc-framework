package github.lljj.utils;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import github.lljj.config.ThreadPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.*;


/**
 * @author lihan
 * @create 2021-10-20 2:07 下午
 */
@Slf4j
public final class ThreadPoolFactoryUtil {
    private static final Map<String, ExecutorService> THREAD_POOLS = new ConcurrentHashMap<>();

    public static ThreadFactory createThreadFactory(String threadNamePrefix, Boolean daemon){
        if(StringUtils.isNotBlank(threadNamePrefix)){
            if(daemon != null){
                return new ThreadFactoryBuilder().setNameFormat(threadNamePrefix).setDaemon(daemon).build();
            } else {
                return new ThreadFactoryBuilder().setNameFormat(threadNamePrefix).build();
            }
        }
        return Executors.defaultThreadFactory();
    }

    public static ExecutorService createThreadPool(ThreadPoolConfig threadPoolConfig, String threadNamePrefix, Boolean daemon){
        ThreadFactory threadFactory = createThreadFactory(threadNamePrefix, daemon);
        return new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaximumPoolSize(),
                threadPoolConfig.getKeepAliveTime(), threadPoolConfig.getTimeUnit(), threadPoolConfig.getWorkQueue());
    }
    public static ExecutorService createCustomThreadPoolIfAbsent(String threadNamePrefix) {
        ThreadPoolConfig threadPoolConfig = new ThreadPoolConfig();
        return createCustomThreadPoolIfAbsent(threadPoolConfig, threadNamePrefix, false);
    }

    public static ExecutorService createCustomThreadPoolIfAbsent(String threadNamePrefix, ThreadPoolConfig threadPoolConfig) {
        return createCustomThreadPoolIfAbsent(threadPoolConfig, threadNamePrefix, false);
    }

    public static ExecutorService createCustomThreadPoolIfAbsent(ThreadPoolConfig customThreadPoolConfig, String threadNamePrefix, Boolean daemon) {
        ExecutorService threadPool = THREAD_POOLS.computeIfAbsent(threadNamePrefix, k -> createThreadPool(customThreadPoolConfig, threadNamePrefix, daemon));
        // 如果 threadPool 被 shutdown 的话就重新创建一个
        if (threadPool.isShutdown() || threadPool.isTerminated()) {
            THREAD_POOLS.remove(threadNamePrefix);
            threadPool = createThreadPool(customThreadPoolConfig, threadNamePrefix, daemon);
            THREAD_POOLS.put(threadNamePrefix, threadPool);
        }
        return threadPool;
    }

    /**
     * shutDown 所有线程池
     */
    public static void shutDownAllThreadPool() {
        log.info("call shutDownAllThreadPool method");
        THREAD_POOLS.entrySet().parallelStream().forEach(entry -> {
            ExecutorService executorService = entry.getValue();
            executorService.shutdown();
            log.info("shut down thread pool [{}] [{}]", entry.getKey(), executorService.isTerminated());
            try {
                executorService.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.error("Thread pool never terminated");
                executorService.shutdownNow();
            }
        });
    }

}
