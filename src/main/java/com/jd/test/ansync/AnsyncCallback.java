package com.jd.test.ansync;


import javax.security.auth.callback.Callback;
import java.util.Optional;

/**
 * @author jd
 * @date 2022/11/16 16:02
 */
public interface AnsyncCallback<T> {

    void onCompletec(T value, Optional<Exception> ex) throws InterruptedException;
}
