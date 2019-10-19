package io.test.solu;

import io.test.solu.model.MessageTestLombok;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Tag("UNIT_BASIC")
@Slf4j
class MainTest {

    //Logger log = LoggerFactory.getLogger(MainTest.class);
    @BeforeEach
    void setUp() {
        log.debug("This is unit test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testJunit5setup() {
        double floatNumber=0.01;
        final String TestString="abcd";
        MessageTestLombok m = new MessageTestLombok(TestString);
        m.setMynumber(100);

        assertAll("Basic test:",
                ()->{
                    assertEquals(floatNumber,0.01,0.00001);
                    assertThat(floatNumber).isCloseTo(0.01, Offset.offset(0.00001));
                },
                ()->{
                    assertEquals(TestString.toUpperCase(),"ABCD");
                },
                ()->{
                    assertThat(m.getMyText()).isNotEmpty();
                    assertEquals(m.getMyText(),TestString);
                },
                ()->{
                    assertEquals(m.getMynumber(),100);
                }
                );
    }

    @Test
    public void testTimeOUt(){
        assertThrows(AssertionFailedError.class,
                ()->{
                    assertTimeout(Duration.ofMillis(1), () -> {
                        Thread.sleep(5);
                        log.debug("I got here");
                    });
                }
                );

    }
}