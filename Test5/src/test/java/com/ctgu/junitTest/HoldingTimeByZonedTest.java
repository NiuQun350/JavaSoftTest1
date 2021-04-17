package com.ctgu.junitTest;

import com.ctgu.junit.HoldingTimeByZoned;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HoldingTimeByZonedTest {
    @Test
    @Order(1)
    @DisplayName("开始通话和结束通话时间均在标准时间内,且通话为整数分钟")
    void testBetweenStandardTime() {

        LocalDateTime startingTime =
                LocalDateTime.of(2021,2,3,12,30,0);
        ZonedDateTime zonedStartTime =
                startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime =
                LocalDateTime.of(2021, 2,3,12,50,0);
        ZonedDateTime zonedEndTime =
                endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(20);
    }

    @Test
    @Order(2)
    @DisplayName("开始通话和结束通话时间均在标准时间内,且通话不为整数分钟")
    void testBetweenStandardTime_with_seconds() {

        LocalDateTime startingTime =
                LocalDateTime.of(2021,2,3,12,30,0);
        ZonedDateTime zonedStartTime =
                startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime =
                LocalDateTime.of(2021, 2,3,12,50,25);
        ZonedDateTime zonedEndTime =
                endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(21);
    }


    @Test
    @Order(3)
    @DisplayName("开始通话在标准时间，结束通话时间在夏令时内,且通话为整数分钟")
    void test_standard_to_DaylightTime() {

        LocalDateTime startingTime = LocalDateTime.of(2021,3,14,0,40,0);
        ZonedDateTime zonedStartTime = startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime = LocalDateTime.of(2021, 3,14,3,40,0);
        ZonedDateTime zonedEndTime = endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(120);
    }

    @Test
    @Order(4)
    @DisplayName("开始通话在标准时间，结束通话时间在夏令时内,且通话不为整数分钟")
    void test_standard_to_DaylightTime_with_seconds() {

        LocalDateTime startingTime = LocalDateTime.of(2021,3,14,0,40,0);
        ZonedDateTime zonedStartTime = startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime = LocalDateTime.of(2021, 3,14,3,40,20);
        ZonedDateTime zonedEndTime = endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(121);
    }


    @Test
    @Order(5)
    @DisplayName("开始通话在夏令时间，结束通话时间在标准时间内,且通话为整数分钟")
    void test_daylight_to_timeStandard() {

        LocalDateTime startingTime = LocalDateTime.of(2021,11,7,0,40,0);
        ZonedDateTime zonedStartTime = startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime = LocalDateTime.of(2021, 11,7,3,40,0);
        ZonedDateTime zonedEndTime = endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(240);
    }

    @Test
    @Order(6)
    @DisplayName("开始通话在夏令时间，结束通话时间在标准时间内,且通话不为整数分钟")
    void test_daylight_to_timeStandard_with_seconds() {

        LocalDateTime startingTime = LocalDateTime.of(2021,11,7,0,40,0);
        ZonedDateTime zonedStartTime = startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime = LocalDateTime.of(2021, 11,7,3,40,20);
        ZonedDateTime zonedEndTime = endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(241);
    }
    @Test
    @Order(7)
    @DisplayName("开始通话和结束通话时间均在夏令时内,且通话为整数分钟")
    void testBetweenDaylightTime() {

        LocalDateTime startingTime =
                LocalDateTime.of(2021,3,30,12,30,0);
        ZonedDateTime zonedStartTime =
                startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime =
                LocalDateTime.of(2021, 3,30,12,50,0);
        ZonedDateTime zonedEndTime = endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(20);
    }

    @Test
    @Order(8)
    @DisplayName("开始通话和结束通话时间均在夏令时内,且通话不为整数分钟")
    void testBetweenDaylightTime_with_seconds() {

        LocalDateTime startingTime =
                LocalDateTime.of(2021,3,30,12,30,0);
        ZonedDateTime zonedStartTime =
                startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime =
                LocalDateTime.of(2021, 3,30,12,50,30);
        ZonedDateTime zonedEndTime = endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);

        assertThat(holdingTime.getHoldingTime()).isEqualTo(21);
    }

    @Test
    @Order(9)
    @DisplayName("通话时间超过3小时")
    void testBetweenError() {

        LocalDateTime startingTime =
                LocalDateTime.of(2021,2,3,12,30,0);
        ZonedDateTime zonedStartTime =
                startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime =
                LocalDateTime.of(2021, 6,3,12,50,0);
        ZonedDateTime zonedEndTime =
                endingTime.atZone(ZoneId.of("America/New_York"));

        HoldingTimeByZoned holdingTime = new HoldingTimeByZoned(zonedStartTime, zonedEndTime);


        Throwable throwable = assertThrows(RuntimeException.class, () ->{
            holdingTime.getHoldingTime();
        });

        assertAll(
                () -> assertEquals("通话时间不正确",throwable.getMessage())
        );
    }
}