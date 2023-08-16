package com.jd.test.strategy;


import com.jd.test.idempotent.ApplicationContextHolder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author jd
 * @date 2023/5/22 17:03
 */
public class AbstractStrategyChoose implements ApplicationListener<ApplicationEvent> {


    private final Map<String, AbstarctExecuteStrategy> abstarctExecuteStrategyMap = new HashMap<>(16);


    public AbstarctExecuteStrategy choose(String mark) {

        return Optional.ofNullable(abstarctExecuteStrategyMap.get(mark)).orElseThrow(() -> new RuntimeException(""));
    }


    public <REQUEST> void chooseAndExecute(String mark, REQUEST request) {

        AbstarctExecuteStrategy executeStrategy = choose(mark);
        executeStrategy.execute(request);
    }

    public <REQUEST, RESPONSE> RESPONSE chooseAndExecuteResp(String mark, REQUEST request) {

        AbstarctExecuteStrategy executeStrategy = choose(mark);
        return (RESPONSE) executeStrategy.executeResp(request);

    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        Map<String, AbstarctExecuteStrategy> beansOfType = ApplicationContextHolder.getBeansOfType(AbstarctExecuteStrategy.class);
        beansOfType.forEach((beanName,bean)->{
            AbstarctExecuteStrategy executeStrategy = abstarctExecuteStrategyMap.get(bean.mark());
            abstarctExecuteStrategyMap.put(bean.mark(), bean);

        });

    }
}
