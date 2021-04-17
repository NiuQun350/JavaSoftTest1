package com.ctgu.junitTest;

import com.ctgu.junit.PhoneBillCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @2021/4/17
 * @NiuQun
 */
public class PhoneBillCalculatorTest {
    @Test
    @Order(1)
    @DisplayName("通话时长在(0,20]内")
    void testCalledTimeFirst() {

        LocalDateTime startingTime =
                LocalDateTime.of(2021, 2, 3, 12, 30, 0);
        ZonedDateTime zonedStartTime =
                startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime =
                LocalDateTime.of(2021, 2, 3, 12, 40, 0);
        ZonedDateTime zonedEndTime =
                endingTime.atZone(ZoneId.of("America/New_York"));

        PhoneBillCalculator phoneBillCalculator = new PhoneBillCalculator(zonedStartTime, zonedEndTime);

        assertThat(phoneBillCalculator.billCalculator()).isEqualTo(0.5);
    }


    @Test
    @Order(2)
    @DisplayName("通话时长在(20, 1800]内")
    void testCalledTimeSecond() {
        LocalDateTime startingTime =
                LocalDateTime.of(2021, 2, 3, 12, 30, 0);
        ZonedDateTime zonedStartTime =
                startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime =
                LocalDateTime.of(2021, 2, 3, 13, 0, 0);
        ZonedDateTime zonedEndTime =
                endingTime.atZone(ZoneId.of("America/New_York"));

        PhoneBillCalculator phoneBillCalculator = new PhoneBillCalculator(zonedStartTime, zonedEndTime);

        assertThat(phoneBillCalculator.billCalculator()).isEqualTo(2);
    }

    @Test
    @Order(3)
    @DisplayName("通话时长不在(0,1800]内")
    void testCalledTimeThird() {
        LocalDateTime startingTime =
                LocalDateTime.of(2021, 2, 3, 12, 0, 0);
        ZonedDateTime zonedStartTime =
                startingTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endingTime =
                LocalDateTime.of(2021, 2, 5, 12, 0, 0);
        ZonedDateTime zonedEndTime =
                endingTime.atZone(ZoneId.of("America/New_York"));


        PhoneBillCalculator phoneBillCalculator = new PhoneBillCalculator(zonedStartTime, zonedEndTime);

        Throwable throwable = assertThrows(RuntimeException.class, () ->{
            phoneBillCalculator.billCalculator();
        });

        assertAll(
                () -> assertEquals("通话时间不正确",throwable.getMessage())
        );
    }

}
