package com.jd.test.handle;


import jdk.nashorn.internal.ir.RuntimeNode;

/**
 * @author jd
 * @date 2022/8/4 16:27
 */
public abstract class Handle {

    private Handle nextHandle;

    private int level;

    public Handle(int level) {
        this.level = level;
    }

    public void setNextHandle(Handle handle) {
        this.nextHandle = handle;
    }

    public final void handleMessage(int lev) {
        if (level == lev) {
            this.echo(lev);
        } else {

        }
    }

    public abstract void echo(int level);
}
