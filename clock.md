# Clock 高性能时钟

## 许可证
[![License](https://img.shields.io/badge/license-BSD-brightgreen)](https://opensource.org/licenses/BSD-3-Clause)

## 项目地址
[源码](https://github.com/ALI1416/clock)
[![Build Status](https://travis-ci.org/ALI1416/clock.svg?branch=master)](https://travis-ci.org/ALI1416/clock)

[测试](https://github.com/ALI1416/clock-test)
[![Build Status](https://travis-ci.org/ALI1416/clock-test.svg?branch=master)](https://travis-ci.org/ALI1416/clock-test)

## 简介
`Clock`通过`System.currentTimeMillis()`获取时间戳，放入到线程池中，并1ms刷新一次。  
由于`System.currentTimeMillis()`的时间戳是通过系统底层获取，比直接获取静态变量慢许多。  
所以`Clock.now()`的性能更高。

## 依赖导入
最新版本
[![Maven central](https://maven-badges.herokuapp.com/maven-central/cn.404z/clock/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cn.404z/clock)

maven
```xml
<dependency>
    <groupId>cn.404z</groupId>
    <artifactId>clock</artifactId>
    <version>1.1.0</version>
</dependency>
```

gradle
```gradle
implementation 'cn.404z:clock:1.1.0'
```

## 使用方法
代码
```java
System.out.println("现在时间戳：" + Clock.now());
```

结果
```txt
现在时间戳：1614138897113
```

## 性能比较
| 次数   | 常规System.currentTimeMillis()耗时 | 高性能Clock.now()耗时 | 倍数    |
| ------ | ---------------------------------- | --------------------- | ------- |
| 100万  | 1毫秒                              | 5毫秒                 | 5.0倍   |
| 1000万 | 2毫秒                              | 22毫秒                | 11.0倍  |
| 1亿    | 3毫秒                              | 327毫秒               | 109.0倍 |
| 21亿   | 35毫秒                             | 6720毫秒              | 192.0倍 |

## 测试
ClockTest.java
```java
import cn.z.clock.Clock;

/**
 * <h1>高性能时钟测试</h1>
 *
 * <p>
 * createDate 2021/02/24 11:54:46
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class ClockTest {

    public static void main(String[] args) {
        test();
        compare100w();
        compare1000w();
        compare1e();
        compare21e();
    }

    /**
     * 测试
     */
    static void test() {
        System.out.println("现在时间戳：" + Clock.now());
        // 现在时间戳：1614138897113
    }

    /**
     * 100万次高性能时钟与系统时钟比较
     */
    static void compare100w() {
        compare(1000000);
        // 高性能时钟调用1000000次使用时间为：1毫秒
        // 系统时钟调用1000000次使用时间为：5毫秒
        // 调用1000000次，高性能时钟比系统时钟性能高5.0倍
    }

    /**
     * 1000万次高性能时钟与系统时钟比较
     */
    static void compare1000w() {
        compare(10000000);
        // 高性能时钟调用10000000次使用时间为：2毫秒
        // 系统时钟调用10000000次使用时间为：22毫秒
        // 调用10000000次，高性能时钟比系统时钟性能高11.0倍
    }

    /**
     * 1亿次高性能时钟与系统时钟比较
     */
    static void compare1e() {
        compare(100000000);
        // 高性能时钟调用100000000次使用时间为：3毫秒
        // 系统时钟调用100000000次使用时间为：327毫秒
        // 调用100000000次，高性能时钟比系统时钟性能高109.0倍
    }

    /**
     * 21亿次高性能时钟与系统时钟比较
     */
    static void compare21e() {
        compare(Integer.MAX_VALUE);
        // 高性能时钟调用2147483647次使用时间为：35毫秒
        // 系统时钟调用2147483647次使用时间为：6720毫秒
        // 调用2147483647次，高性能时钟比系统时钟性能高192.0倍
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
```

## 源码
Clock.java
```java
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h1>高性能时钟</h1>
 *
 * <p>
 * createDate 2021/02/24 10:09:31
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class Clock {

    /**
     * 现在时间戳(原子长整形，防止多线程异常)
     */
    private final static AtomicLong NOW = new AtomicLong(System.currentTimeMillis());

    // 静态初始化
    static {
        Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "Clock");
            thread.setDaemon(true);
            return thread;
        }).scheduleAtFixedRate(() -> NOW.set(System.currentTimeMillis()), 1, 1, TimeUnit.MILLISECONDS);
    }

    /**
     * 禁止构造
     */
    private Clock() {

    }

    /**
     * 获取当前时间戳
     */
    public static long now() {
        return NOW.get();
    }
}
```

## 交流
QQ1416978277

## 赞助
[赞助]()
