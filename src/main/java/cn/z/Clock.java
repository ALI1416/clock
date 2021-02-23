package cn.z;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h1>高性能时钟</h1>
 *
 * <p>
 * createDate 2020/12/22 19:53:00
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class Clock {

    /**
     * 现在时间戳(原子长整形，防止多线程异常)
     */
    private final static AtomicLong now = new AtomicLong(System.currentTimeMillis());

    static {
        Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "Clock");
            thread.setDaemon(true);
            return thread;
        }).scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), 1, 1, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取当前时间戳
     */
    public static long now() {
        return now.get();
    }

}
