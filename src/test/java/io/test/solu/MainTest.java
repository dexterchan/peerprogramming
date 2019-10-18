package io.test.solu;

import io.test.solu.model.MessageTestLombok;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Tag("UNIT_BASIC")
class MainTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testJunit5setup() {
        double floatNumber=0.01;
        final String TestString="abcd";
        MessageTestLombok m = new MessageTestLombok(TestString);
        assertAll("Basic test:",
                ()->{
                    assertEquals(floatNumber,0.01,0.00001);
                },
                ()->{
                    assertEquals(TestString.toUpperCase(),"ABCD");
                },
                ()->{
                    assertThat(m.getMyText()).isNotEmpty();
                    assertEquals(m.getMyText(),TestString);
                }
                );
    }
}