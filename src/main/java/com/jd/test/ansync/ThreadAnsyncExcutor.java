package com.jd.test.ansync;


import lombok.SneakyThrows;
import lombok.var;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author jd
 * @date 2022/11/16 16:07
 */
public class ThreadAnsyncExcutor implements AnsyncExecutor {
    @Override
    public <T> AnsyncResult<T> startProcess(Callable<T> task) {
        return startProcess(task, null);
    }

    @Override
    public <T> AnsyncResult<T> startProcess(Callable<T> task, AnsyncCallback<T> back) {

        var compleableResult = new CompleableResult(back);
        new Thread(() -> {
            try {
                compleableResult.setValue(task.call());
            } catch (Exception e) {
                compleableResult.setException(e);

            }

        }).start();

        return compleableResult;
    }

    @Override
    public <T> T endProcess(AnsyncResult<T> re) {
        if (!re.isComplated()) {
            re.await();
        }
        return re.getValue();
    }

    private class CompleableResult<T> implements AnsyncResult<T> {

        static final int RUNING = 1;
        static final int FAILED = 2;
        static final int COMPLETED = 3;
        final Object lock;
        final Optional<AnsyncCallback<T>> callback;
        volatile int state = RUNING;
        T value;
        Exception exception;

        CompleableResult(AnsyncCallback<T> callback) {
            this.lock = new Object();
            this.callback = Optional.ofNullable(callback);
        }

        void setValue(T value) {
            this.value = value;
            this.state = COMPLETED;
            this.callback.ifPresent(ac -> {
                try {
                    ac.onCompletec(value, Optional.empty());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            synchronized (lock) {
                lock.notifyAll();
            }
        }

        void setException(Exception e) {
            this.exception = e;
            this.state = FAILED;
            this.callback.ifPresent(ac -> {
                try {
                    ac.onCompletec(null, Optional.of(e));
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
            synchronized (lock) {
                lock.notifyAll();
            }

        }

        @Override
        public Boolean isComplated() {
            return state > RUNING;
        }

        @SneakyThrows
        @Override
        public T getValue() {
            if (state == COMPLETED) {
                return value;
            } else if (state == FAILED) {
                throw new ExecutionException(exception);
            }
            return null;
        }


        @SneakyThrows
        @Override
        public void await() {
            synchronized (lock) {
                while (!isComplated()) {
                    lock.wait();
                }
            }

        }
    }
}
