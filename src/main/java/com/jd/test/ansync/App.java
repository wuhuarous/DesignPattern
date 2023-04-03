package com.jd.test.ansync;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import lombok.var;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

/**
 * @author jd
 * @date 2022/11/16 16:28
 */
public class App {

    public static void main(String[] args) {
        ThreadAnsyncExcutor excutor = new ThreadAnsyncExcutor();

        var integerAnsyncResult = excutor.startProcess(lazybval(1, 10));

        System.out.println(JSON.toJSON(integerAnsyncResult));
        Integer integer = excutor.endProcess(integerAnsyncResult);

//        System.out.println(integer);

//        FutureTask futureTask = new FutureTask();


        List<Integer> a = new ArrayList<>(10);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);

        List<Integer> b = new ArrayList<>(10);
        b.add(1);
        b.add(2);
        b.add(3);
        b.add(4);
        b.add(5);

        List<Integer> collect = b.stream().limit(2).collect(Collectors.toList());

        collect.forEach(System.out::println);
//        b.stream().forEach(i->{
//            b.remove(i);
//            System.out.println("B-"+i);
//        });
//        a.forEach(i->{
//            a.remove(i);
//            System.out.println("A-"+i);
//        });


    }


    private static <T> Callable<T> lazybval(T value, long delayMills) {
        return () -> {
            Thread.sleep(delayMills);
            return value;
        };
    }
}
