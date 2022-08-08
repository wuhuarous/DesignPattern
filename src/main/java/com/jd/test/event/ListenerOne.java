package com.jd.test.event;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * @author jd
 * @date 2022/8/2 16:16
 */
@Component
public class ListenerOne  implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent myEvent) {

        System.out.println("22222222"+myEvent.getB());
    }

}
