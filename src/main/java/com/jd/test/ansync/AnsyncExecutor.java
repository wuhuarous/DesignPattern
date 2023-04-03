package com.jd.test.ansync;


import java.util.concurrent.Callable;

/**
 * @author jd
 * @date 2022/11/16 16:04
 */
public interface AnsyncExecutor {

    <T> AnsyncResult<T> startProcess(Callable<T> task);

    <T> AnsyncResult<T> startProcess(Callable<T> task, AnsyncCallback<T> back);

    <T> T endProcess(AnsyncResult<T> re);
}
