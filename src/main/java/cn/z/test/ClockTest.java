package cn.z.test;

import cn.z.Clock;

/**
 * <h1>高性能系统时钟测试</h1>
 *
 * <p>
 * createDate 2020/12/22 19:53:00
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class ClockTest {

    public static void main(String[] args) {
        once();
        _100w();
        _1e();
        _21e();
    }

    /**
     * 使用方法
     */
    static void once() {
        System.out.println("现在时间戳：" + Clock.now());
        // 现在时间戳：1614047152484
    }

    /**
     * 100万次高性能时钟与系统时钟比较
     */
    static void _100w() {
        compare(1000000);
        // 高性能时钟调用1000000次使用时间为：5毫秒
        // 系统时钟调用1000000次使用时间为：11毫秒
        // 调用1000000次，高性能时钟比系统时钟性能高1.8333333333333333倍
    }

    /**
     * 1亿次高性能时钟与系统时钟比较
     */
    static void _1e() {
        compare(100000000);
        // 高性能时钟调用100000000次使用时间为：3毫秒
        // 系统时钟调用100000000次使用时间为：327毫秒
        // 调用100000000次，高性能时钟比系统时钟性能高109.0倍
    }

    /**
     * 21亿次高性能时钟与系统时钟比较
     */
    static void _21e() {
        compare(Integer.MAX_VALUE);
        // 高性能时钟调用2147483647次使用时间为：35毫秒
        // 系统时钟调用2147483647次使用时间为：6750毫秒
        // 调用2147483647次，高性能时钟比系统时钟性能高192.85714285714286倍
    }

    /**
     * 高性能时钟与系统时钟比较
     * 
     * @param count 次数
     */
    static void compare(int count) {
        // 调用一次，并延时1秒，初始化高性能时钟
        Clock.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 高性能时钟
        long a = Clock.now();
        for (int i = 0; i < count; i++) {
            Clock.now();
        }
        long b = Clock.now();
        long ba = b - a;
        System.out.println("高性能时钟调用" + count + "次使用时间为：" + ba + "毫秒");

        // 系统时钟
        long c = Clock.now();
        for (int i = 0; i < count; i++) {
            System.currentTimeMillis();
        }
        long d = Clock.now();
        long dc = d - c;
        System.out.println("系统时钟调用" + count + "次使用时间为：" + dc + "毫秒");

        System.out.println("调用" + count + "次，高性能时钟比系统时钟性能高" + dc / (double) ba + "倍");
    }
}
