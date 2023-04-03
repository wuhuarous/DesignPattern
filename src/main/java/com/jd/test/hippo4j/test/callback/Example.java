package com.jd.test.hippo4j.test.callback;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 如果将一个方法的引用，当成另一个方法的参数 则执行该方法则是对该引用的回调
 * <p>
 * 这种用法在实际代码中有许多应用场景。一些常见的应用场景包括：
 * <p>
 * 在事件处理中使用：可以将事件处理程序（如点击事件）的方法引用传递给其他对象，当事件触发时，这些对象就可以对该方法引用进行回调，从而执行相应的操作。
 * <p>
 * 在多线程编程中使用：可以将一个方法的引用作为回调函数传递给另一个线程或线程池，当某个条件满足时，该线程或线程池就会调用该方法引用进行回调，从而执行相应的操作。
 * <p>
 * 在函数式编程中使用：可以将一个方法的引用作为函数式接口的实例传递给其他方法，从而在这些方法中使用该方法引用执行相应的操作。
 * <p>
 * 在回调函数中使用：可以将一个方法的引用作为回调函数传递给其他函数或对象，当某个事件或条件满足时，这些函数或对象就会调用该方法引用进行回调，从而执行相应的操作。
 * <p>
 * 这种用法在实际代码中非常灵活和强大，可以提高代码的可读性和可维护性，并简化代码的实现。
 *
 * @author jd
 * @date 2023/2/28 11:02
 */
@Component
public class Example {

    private ScheduledExecutorService executorService;
    public static void myMethod(String str, Consumer<String> consumer) {
        consumer.accept(str);
    }

    @PostConstruct
    public void main() {
        FunctionInterface functionInterface = System.out::println;
//        myMethod("Hello, world!", functionInterface::doSomething);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1, runnable -> {
            Thread thread = new Thread(runnable);
            thread.setName("client.worker.executor");
            thread.setDaemon(true);
            return thread;
        });
//         executorService = Executors.newSingleThreadScheduledExecutor(
//                ThreadFactoryBuilder.builder().prefix("client.long.polling.executor").daemon(true).build());
        executor.schedule(() -> {
            try {
                System.out.println(111);
                executorService.execute(new LongPollRunable());
            } catch (Throwable ex) {/**/
            }
        }, 1L, TimeUnit.MILLISECONDS);

    }

    public class LongPollRunable implements Runnable{
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            System.out.println("我在做轮训");
//            executorService.execute(this);
        }
    }
}
