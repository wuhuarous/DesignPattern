package com.jd.test.stream;


import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jd
 * @date 2022/11/1 16:16
 */
public class StreamTest {

    public static void main(String[] args) {


        List<String> fruitList = Arrays.asList("banana", "orange", "watermelon");

        List<String> vegetableList = Arrays.asList("kale", "leek", "carrot");

        List<String> transportList = Arrays.asList("car", "bike", "train");


        List<String> collect = Stream.of(fruitList, vegetableList, transportList).flatMap(list -> list.stream()).collect(Collectors.toList());


        List<List<String>> collect1 = Stream.of(fruitList, vegetableList, transportList).collect(Collectors.toList());

        collect1.parallelStream().forEach(i -> {


        });


        collect.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);
        System.out.println();

        Stream<String> stringStream = Stream.of("my", "name", "is", "envy");

        Optional<String> stringOptional = stringStream.reduce((before, after) -> before + "、" + after);

        stringOptional.ifPresent(System.out::println);


    }
}
