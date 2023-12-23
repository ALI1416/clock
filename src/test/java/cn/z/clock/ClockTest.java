package cn.z.clock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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
@TestMethodOrder(MethodOrderer.MethodName.class)
@Slf4j
class ClockTest {

    /**
     * 常规
     */
    @Test
    void test00Normal() {
        log.info("现在时间戳为：{}", Clock.now());
        log.info("现在Date为：{}", Clock.date());
        log.info("现在Timestamp为：{}", Clock.timestamp());
        log.info("现在Calendar为：{}", Clock.calendar());
        log.info("现在Instant为：{}", Clock.instant());
        log.info("现在LocalDateTime为：{}", Clock.localDateTime());
        log.info("现在OffsetDateTime为：{}", Clock.offsetDateTime());
        log.info("现在ZonedDateTime为：{}", Clock.zonedDateTime());
        // 现在时间戳为：1703313006732
        // 现在Date为：Sat Dec 23 14:30:06 CST 2023
        // 现在Timestamp为：2023-12-23 14:30:06.748
        // 现在Calendar为：java.util.GregorianCalendar[time=1703313006748,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=31,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2023,MONTH=11,WEEK_OF_YEAR=51,WEEK_OF_MONTH=4,DAY_OF_MONTH=23,DAY_OF_YEAR=357,DAY_OF_WEEK=7,DAY_OF_WEEK_IN_MONTH=4,AM_PM=1,HOUR=2,HOUR_OF_DAY=14,MINUTE=30,SECOND=6,MILLISECOND=748,ZONE_OFFSET=28800000,DST_OFFSET=0]
        // 现在Instant为：2023-12-23T06:30:06.748Z
        // 现在LocalDateTime为：2023-12-23T14:30:06.748
        // 现在OffsetDateTime为：2023-12-23T14:30:06.748+08:00
        // 现在ZonedDateTime为：2023-12-23T14:30:06.748+08:00[Asia/Shanghai]
    }

    /**
     * 100万次高性能时钟与系统时钟比较
     */
    // @Test
    void test01Compare100w() {
        compare(1000000);
        // 高性能时钟调用1000000次使用时间为：1毫秒
        // 系统时钟调用1000000次使用时间为：5毫秒
        // 调用1000000次，高性能时钟比系统时钟性能高5.0倍
    }

    /**
     * 1000万次高性能时钟与系统时钟比较
     */
    // @Test
    void test02Compare1000w() {
        compare(10000000);
        // 高性能时钟调用10000000次使用时间为：2毫秒
        // 系统时钟调用10000000次使用时间为：22毫秒
        // 调用10000000次，高性能时钟比系统时钟性能高11.0倍
    }

    /**
     * 1亿次高性能时钟与系统时钟比较
     */
    // @Test
    void test03Compare1e() {
        compare(100000000);
        // 高性能时钟调用100000000次使用时间为：3毫秒
        // 系统时钟调用100000000次使用时间为：327毫秒
        // 调用100000000次，高性能时钟比系统时钟性能高109.0倍
    }

    /**
     * 21亿次高性能时钟与系统时钟比较
     */
    // @Test
    void test04Compare21e() {
        compare(Integer.MAX_VALUE);
        // 高性能时钟调用2147483647次使用时间为：35毫秒
        // 系统时钟调用2147483647次使用时间为：6720毫秒
        // 调用2147483647次，高性能时钟比系统时钟性能高192.0倍
    }

    /**
     * 高性能时钟与系统时钟比较
     *
     * @param count 比较次数
     */
    void compare(int count) {
        /* 初始化 */
        Clock.now();
        System.currentTimeMillis();
        /* 高性能时钟 */
        long a = Clock.now();
        for (int i = 0; i < count; i++) {
            Clock.now();
        }
        long b = Clock.now();
        long ba = b - a;
        log.info("高性能时钟调用{}次使用时间为：{}毫秒", count, ba);
        /*  系统时钟 */
        long c = Clock.now();
        for (int i = 0; i < count; i++) {
            System.currentTimeMillis();
        }
        long d = Clock.now();
        long dc = d - c;
        log.info("系统时钟调用{}次使用时间为：{}毫秒", count, dc);
        /* 比较 */
        log.info("调用{}次，高性能时钟比系统时钟性能高{}倍", count, dc / (double) ba);
    }

}
