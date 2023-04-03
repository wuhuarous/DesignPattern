package com.jd.test.hippo4j.test;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jd
 * @date 2023/2/27 18:18
 */
@Component
@RequiredArgsConstructor
public class MyTest {

    /**
     * 在 test 方法中，我们创建了一个匿名内部类的实例，实现了 TestInter 接口并重写了其中的 myInter 方法。在这个匿名内部类的 myInter 方法中，我们打印了传入的 context 参数并调用了传入的 ThreadPoolSubscribeCallback 实例的 callback 方法。然后，我们将这个实现了 TestInter 接口的匿名内部类实例存储到了 map 中，键为字符串 "1"。
     * <p>
     * 在 MyTest 类的 my 方法中，我们调用了 testClass.test 方法，将字符串 "我在测试test" 和 threadPoolDynamicRefresh1::dynamicRefresh（threadPoolDynamicRefresh1 的 dynamicRefresh 方法的方法引用）作为参数传入。在 test 方法内部，我们创建了一个实现了 TestInter 接口的匿名内部类实例，并将其存储到了 map 中，键为字符串 "1"。因此，testClass.map.get("1") 返回的是刚刚创建的那个实例。然后，我们调用该实例的 myInter 方法，并将字符串 "!11" 作为参数传入。由于这个实例是我们在 test 方法中创建的，因此调用的是我们创建的那个匿名内部类实例的 myInter 方法，而不是 TestInter 接口的默认实现。在这个匿名内部类的 myInter 方法中，我们打印了传入的 context 参数并调用了 threadPoolSubscribeCallback 实例的 callback 方法。因此，threadPoolDynamicRefresh1::dynamicRefresh 方法被作为回调函数传递给了 myInter 方法，在 myInter 方法内部被执行了。
     */

/*
    @Autowired
    private TestClass testClass;

    @PostConstruct
    public void my() {
        testClass.test("我在测试test");
        TestInter inter = testClass.map.get("1");
        inter.myInter("!11");

    }
*/

}
