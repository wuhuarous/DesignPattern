package com.jd.test.lamda;


import lombok.extern.slf4j.Slf4j;

/**
 * @author jd
 * @date 2023/7/26 16:47
 */
@Slf4j
public class FunctionTest {

    public static void main(String[] args) {
//        FunctionInterface functionInterface = text-> log.info("请问：{}",text);
//
//        functionInterface.print("你们好啊");
//
        FunctionInterface functionInterface1 = Utils::printText;

        functionInterface1.print("我不好");

        FunctionInterfaceHandler handler = Utils::handler;
        handler.print(text -> {
            System.out.println(text+"eeee");

        });
    }


    public static class Utils {

        public static void printText(String text) {

            if(text != null){
                System.out.println(text);
            }
        }
        public static void handler(FunctionInterface handler) {

            handler.print("dff");
        }
    }
}
