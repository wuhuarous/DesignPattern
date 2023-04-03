package com.jd.test.strategy.q;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author jd
 * @date 2022/10/27 9:47
 */
@Slf4j
@Component
public class BFileResolve implements IFileStrategy {

    @Override
    public FileTypeResolveEnum gainFileType() {
        return FileTypeResolveEnum.xml;
    }

    @Override
    public void resolve(Object objectparam) {
        log.info("B 类型解析文件，参数：{}", objectparam);
    }
}
