package shoonye.util.spring;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import shoonye.util.ec.ExecutionContextAwareCallable;
import shoonye.util.ec.ExecutionContextAwareTask;
import shoonye.util.ec.ThreadLocalExecutionContext;



public class ExecutionContextAwareExecutor extends ThreadPoolTaskExecutor{
    private static final long serialVersionUID = 1L;
    
    public ExecutionContextAwareExecutor(){
        super();
    }

    @Override
    public void execute(Runnable task) {
        ThreadLocalExecutionContext ec0 = new ThreadLocalExecutionContext();
        ExecutionContextAwareTask wrappedTask = new ExecutionContextAwareTask(task, ec0);
        super.execute(wrappedTask);
    }

    @Override
    public Future<?> submit(Runnable task) {
        ThreadLocalExecutionContext ec0 = new ThreadLocalExecutionContext();
        ExecutionContextAwareTask wrappedTask = new ExecutionContextAwareTask(task, ec0);
        return super.submit(wrappedTask);
    }

    @Override
    public <T> Future<T> submit(Callable<T> callale) {
        ThreadLocalExecutionContext ec0 = new ThreadLocalExecutionContext();
        ExecutionContextAwareCallable<T> wrappedCallable = new ExecutionContextAwareCallable<T>(callale, ec0);
       return super.submit(wrappedCallable);
    }
    
}
