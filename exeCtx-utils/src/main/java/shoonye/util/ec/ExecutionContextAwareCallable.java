package shoonye.util.ec;

import java.util.concurrent.Callable;


public class ExecutionContextAwareCallable<T> implements Callable<T>{
    private Callable<T> original = null;
    private ExecutionContext executionContext;
    

    public ExecutionContextAwareCallable(Callable<T> original, ExecutionContext executionContext) {
        super();
        this.original = original;
        this.executionContext = new SimpleExecutionContext(executionContext);
    }


    @Override
    public T call() throws Exception {
        ThreadLocalExecutionContext ec = new ThreadLocalExecutionContext();
        ec.set(executionContext);
        return original.call();
    }

}
