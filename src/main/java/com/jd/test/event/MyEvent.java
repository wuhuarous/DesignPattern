package com.jd.test.event;


import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author jd
 * @date 2022/8/2 16:14
 */
@Getter
public class MyEvent extends ApplicationEvent {

    private String b;

    public MyEvent(Object source, String a) {
        super(source);
        this.b = a;
    }
}
