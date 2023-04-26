package com.jd.test.idempotent.toolkit;


import cn.hutool.core.util.ArrayUtil;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @author jd
 * @date 2023/4/18 17:03
 */
public class SpELUtil {

    public static Object parseKey(String spEL, Method method, Object[] objects){

        String spELFlag = "#";
        if(!spEL.contains(spELFlag)){
            return  spEL;
        }
        return  parse(spEL,method,objects);
    }

    private static Object parse(String spEL, Method method, Object[] objects) {

        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(spEL);
        String [] params = discoverer.getParameterNames(method);
        StandardEvaluationContext context = new StandardEvaluationContext();

        if(ArrayUtil.isNotEmpty(params)){
            for (int i = 0; i < params.length; i++) {
                context.setVariable(params[i],objects[i]);

            }
        }
        return expression.getValue(context);

    }
}
