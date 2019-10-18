package io.test.solu;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("integration")
@Slf4j
public class MainIntegationTest {
    @Test
    public void testIntegrationTest(){
        log.debug("This is the integration test");
    }
}
