package io.test.solu;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    void testImmutableMap(){
        final Map<String, MessageTestLombok> m =
                ImmutableMap.of(
                        "Piglet",new MessageTestLombok("Hi Piglet"),
                        "Pooh",new MessageTestLombok("Hi Pooh"));
        assertThat(m).hasSize(2);
        assertThat(m.get("Piglet").getMyText()).isEqualTo("Hi Piglet");
        assertThrows(UnsupportedOperationException.class,
                ()->{
                    m.put("new",new MessageTestLombok("new"));
                });

        Map<String,MessageTestLombok> m2=Maps.newHashMap(m);
        m2.put("new",new MessageTestLombok("new"));
        assertThat(m2).hasSize(3);

    }
    @Test
    void testListToMap(){
        List<MessageTestLombok> mList = Arrays.asList(
          new MessageTestLombok("Hi Piglet"),
          new MessageTestLombok("Hi Pooh"),
          new MessageTestLombok("Hi Tiger") ,
          new MessageTestLombok("Hi Kangeroo")
        );
        Pattern pattern = Pattern.compile("Hi (\\w+)");

        Supplier<Stream< MessageTestLombok> > supplier = ()-> mList.stream();

      boolean matchHIALL=supplier.get().allMatch(m->m.getMyText().indexOf("Hi")==0);
        assertTrue(matchHIALL);

        Map<String, MessageTestLombok> mapMessage = supplier.get().collect(Collectors.toMap(
                m->{

                    Matcher match = pattern.matcher(m.getMyText());
                    if(match.matches()){
                        return match.group(1);
                    }else{
                        throw new IllegalArgumentException(m.getMyText());
                    }

                }, Function.identity()
        ));

        assertThat(mapMessage).hasSize(mList.size());
        assertNotNull(mapMessage.get("Piglet"));

        assertAll("test optional",
                ()->{
                    String tigerout=Optional.ofNullable(mapMessage.get("Tiger")).map(m->m.getMyText()).orElse("Not found");
                    assertEquals(tigerout,"Hi Tiger");
                },
                ()->{
                    String tigerout=Optional.ofNullable(mapMessage.get("Kangeroo")).map(m->m.getMyText()).orElse("Not found");
                    assertEquals(tigerout,"Hi Kangeroo");
                },
                ()->{
                    String tigerout=Optional.ofNullable(mapMessage.get("Penguin")).map(m->m.getMyText()).orElse("Not found");
                    assertEquals(tigerout,"Not found");
                });

    }
}