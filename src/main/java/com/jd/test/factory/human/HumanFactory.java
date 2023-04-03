package com.jd.test.factory.human;


/**
 * @author jd
 * @date 2022/8/5 14:38
 */
public class HumanFactory {

    private static <T extends Human> T createHuman(Class<T> c) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Human human = null;

        human = (Human) Class.forName(c.getName()).newInstance();


        return (T) human;

    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        YellowHuman human = HumanFactory.createHuman(YellowHuman.class);
//        human.getColer();
//        human.getName();
    }
}
