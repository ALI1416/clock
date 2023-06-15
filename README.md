# High Performance Clock 高性能时钟

[![License](https://img.shields.io/github/license/ALI1416/clock?label=License)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Java Support](https://img.shields.io/badge/Java-8+-green)](https://openjdk.org/)
[![Maven Central](https://img.shields.io/maven-central/v/cn.404z/clock?label=Maven%20Central)](https://mvnrepository.com/artifact/cn.404z/clock)
[![Tag](https://img.shields.io/github/v/tag/ALI1416/clock?label=Tag)](https://github.com/ALI1416/clock/tags)
[![Repo Size](https://img.shields.io/github/repo-size/ALI1416/clock?label=Repo%20Size&color=success)](https://github.com/ALI1416/clock/archive/refs/heads/master.zip)

[![Java CI](https://github.com/ALI1416/clock/actions/workflows/ci.yml/badge.svg)](https://github.com/ALI1416/clock/actions/workflows/ci.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_clock&metric=coverage)
![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_clock&metric=reliability_rating)
![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_clock&metric=sqale_rating)
![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ALI1416_clock&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=ALI1416_clock)

## 简介

此工具类在`高并发`下比`System.currentTimeMillis()`性能更高

## 依赖导入

```xml
<dependency>
  <groupId>cn.404z</groupId>
  <artifactId>clock</artifactId>
  <version>2.4.0</version>
</dependency>
```

## 使用示例

代码

```java
log.info("现在时间戳为：{}", Clock.now());
log.info("现在Date为：{}", Clock.date());
log.info("现在Timestamp为：{}", Clock.timestamp());
```

结果

```txt
[main] INFO com.demo.ClockTest - 现在时间戳为：1679038464047
[main] INFO com.demo.ClockTest - 现在Date为：Fri Mar 17 15:34:24 CST 2023
[main] INFO com.demo.ClockTest - 现在Timestamp为：2023-03-17 15:34:24.047
```

更多请见[测试](./src/test)

## 性能比较

| 次数   | Clock.now()耗时 | System.currentTimeMillis()耗时 | 倍数    |
| ------ | --------------- | ------------------------------ | ------- |
| 100万  | 1毫秒           | 5毫秒                          | 5.0倍   |
| 1000万 | 2毫秒           | 22毫秒                         | 11.0倍  |
| 1亿    | 3毫秒           | 327毫秒                        | 109.0倍 |
| 21亿   | 35毫秒          | 6720毫秒                       | 192.0倍 |

## 更新日志

[点击查看](./CHANGELOG.md)

## 关于

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://www.404z.cn/images/about.dark.svg">
  <img alt="About" src="https://www.404z.cn/images/about.light.svg">
</picture>
