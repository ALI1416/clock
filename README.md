# High Performance Clock 高性能时钟

[![Maven Central](https://img.shields.io/maven-central/v/cn.404z/clock?label=Maven%20Central)](https://mvnrepository.com/artifact/cn.404z/clock)
[![License](https://img.shields.io/badge/license-BSD-brightgreen)](https://opensource.org/licenses/BSD-3-Clause)

[Github源码](https://github.com/ALI1416/clock)
[Gitee源码](https://gitee.com/ALI1416/clock)
[![Java CI with Maven](https://github.com/ALI1416/clock/actions/workflows/maven.yml/badge.svg)](https://github.com/ALI1416/clock/actions/workflows/maven.yml)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_clock&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=ALI1416_clock)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_clock&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=ALI1416_clock)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_clock&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=ALI1416_clock)

[Github测试](https://github.com/ALI1416/clock-test)
[Gitee测试](https://gitee.com/ALI1416/clock-test)
[![Java CI with Maven](https://github.com/ALI1416/clock-test/actions/workflows/maven.yml/badge.svg)](https://github.com/ALI1416/clock-test/actions/workflows/maven.yml)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_clock-test&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=ALI1416_clock-test)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_clock-test&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=ALI1416_clock-test)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_clock-test&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=ALI1416_clock-test)

## 简介

`Clock`通过`System.currentTimeMillis()`获取时间戳，放入到线程池中，并1ms刷新一次。  
由于`System.currentTimeMillis()`的时间戳是通过系统底层获取，比直接获取静态变量慢许多。  
所以`Clock.now()`的性能更高。

## 依赖导入

```xml
<!-- 必须依赖 -->
<dependency>
    <groupId>cn.404z</groupId>
    <artifactId>clock</artifactId>
    <version>2.2.0</version>
</dependency>
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

## 交流

QQ：1416978277  
微信：1416978277  
支付宝：1416978277@qq.com  
![交流](https://cdn.jsdelivr.net/gh/ALI1416/ALI1416/image/contact.png)

## 赞助

![赞助](https://cdn.jsdelivr.net/gh/ALI1416/ALI1416/image/donate.png)
