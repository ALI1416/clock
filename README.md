# High performance clock 高性能时钟

## 项目地址
[源码](https://github.com/ALI1416/clock)
[![Build Status](https://travis-ci.com/ALI1416/clock.svg?branch=master)](https://travis-ci.com/ALI1416/clock)

[测试](https://github.com/ALI1416/clock-test)
[![Build Status](https://travis-ci.com/ALI1416/clock-test.svg?branch=master)](https://travis-ci.com/ALI1416/clock-test)

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
    <version>1.2.0</version>
</dependency>
```

gradle
```gradle
implementation 'cn.404z:clock:1.2.0'
```

## 使用方法
代码
```java
System.out.println("现在时间戳为：" + Clock.now());
System.out.println("现在Date为：" + Clock.date());
System.out.println("现在Timestamp为：" + Clock.timestamp());
```

结果
```txt
现在时间戳为：1614583563907
现在Date为：Mon Mar 01 15:26:03 CST 2021
现在Timestamp为：2021-03-01 15:26:03.973
```

## 性能比较
| 次数   | Clock.now()耗时 | System.currentTimeMillis()耗时 | 倍数    |
| ------ | --------------- | ------------------------------ | ------- |
| 100万  | 1毫秒           | 5毫秒                          | 5.0倍   |
| 1000万 | 2毫秒           | 22毫秒                         | 11.0倍  |
| 1亿    | 3毫秒           | 327毫秒                        | 109.0倍 |
| 21亿   | 35毫秒          | 6720毫秒                       | 192.0倍 |

## 许可证
[![License](https://img.shields.io/badge/license-BSD-brightgreen)](https://opensource.org/licenses/BSD-3-Clause)

## 交流
QQ：1416978277  
微信：1416978277  
支付宝：1416978277@qq.com
![交流](https://ali1416.github.io/web/image/contact.png)

## 赞助
![赞助](https://ali1416.github.io/web/image/donate.png)
