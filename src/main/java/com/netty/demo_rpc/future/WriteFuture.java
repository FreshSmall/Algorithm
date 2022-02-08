package com.netty.demo_rpc.future;


import com.netty.demo_rpc.domain.Response;

import java.util.concurrent.Future;

public interface WriteFuture<T> extends Future<T> {

    Throwable cause();

    void setCause(Throwable cause);

    boolean isWriteSuccess();

    void setWriteResult(boolean result);

    String requestId();

    T response();

    void setResponse(Response response);

    boolean isTimeout();
}
