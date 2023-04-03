package com.jd.test.hippo4j.console;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jd
 * @date 2023/3/3 14:41
 */
@RestController
@RequestMapping(value = "test/asyncContext")
public class AsyncContentConroller {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

    @GetMapping(value = "servlet/pull")
    public @ResponseBody void startServer(HttpServletRequest request, HttpServletResponse response) {
        String name = Thread.currentThread().getName();
        System.out.println("--------当前线程名称-------" + name);
        try {
            if (request.isAsyncSupported()) {
                //用于启动异步工作线程,进入异步模式,调用业务处理线程进行业务处理
                request.startAsync(request, response);
                if (request.isAsyncStarted()) {
                    /**
                     * 1 获取AsyncContext，对异步执行的上下文提供支持，可以透过AsyncContext的getRequest() 、 getResponse()方法取得Request、Response对象
                     * 2  客户端的响应将暂缓至，调用AsyncContext的complete()方法或dispatch()为止，前者表示回应完成，后者表示将响应调派给指定的URL
                     * 3 使用异步处理方式，web容器的请求处理线程释放了，可以服务其他的请求处理。但是该Request的处理并没有结束，
                     *   在使用AsyncContext的complete或者dispatch完成后，这个request的处理才结束。
                     */
                    request.getServletContext();
                    final AsyncContext asyncContext = request.getAsyncContext();
                    asyncContext.setTimeout(10000);
                    System.out.println("--------超时时间------" + asyncContext.getTimeout());
                    // Servlet不会被阻塞,而是直接往下执行
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            PrintWriter printWriter = null;
                            try {
                                Thread.sleep(10000);
                                response.setCharacterEncoding("UTF-8");
                                response.setHeader("Content-type", "application/json;charset=UTF-8");
                                printWriter = response.getWriter();
                                printWriter.write("jsonDto.toString()");
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (printWriter != null) {
                                    printWriter.flush();
                                    printWriter.close();
                                }
                                //告诉启动异步处理的Servlet异步处理已完成，Servlet就会提交请求响应
                                asyncContext.complete();
                                System.out.println(name + "-完成操作");
                            }
                        }
                    });
                }
            } else { // 不支持异步
                System.out.println("当前servlet容器不支持异步....");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}