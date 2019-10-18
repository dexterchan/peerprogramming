package io.test.solu.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class MessageTestLombok {
    @Getter
    private final String myText;

    @Setter
    @Getter
    private int mynumber;
}
