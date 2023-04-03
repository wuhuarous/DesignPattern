package com.jd.test.strategy.q;


/**
 * @author jd
 * @date 2022/10/27 9:45
 */
public interface IFileStrategy {

    //属于哪种文件解析类型
    FileTypeResolveEnum gainFileType();

    //封装的公用算法（具体的解析方法）
    void resolve(Object objectparam);
}
