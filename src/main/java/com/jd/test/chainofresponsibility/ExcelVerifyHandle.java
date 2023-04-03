package com.jd.test.chainofresponsibility;


import java.util.List;

/**
 * @author jd
 * @date 2023/3/10 15:39
 */
public interface ExcelVerifyHandle<E> {

   <T> List<E> varifyHandle(T data);


}
