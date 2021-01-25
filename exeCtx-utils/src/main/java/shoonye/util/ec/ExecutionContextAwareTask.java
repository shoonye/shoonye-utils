package shoonye.util.ec;


public class ExecutionContextAwareTask implements Runnable {
    private Runnable task;
    private ExecutionContext executionContext;
    
    public ExecutionContextAwareTask(Runnable task, ExecutionContext ec0) {
        super();
        this.task = task;
        this.executionContext = new SimpleExecutionContext(ec0);
    }

    @Override
    public void run() {
        ThreadLocalExecutionContext ec = new ThreadLocalExecutionContext();
        ec.set(executionContext);
        task.run();
    }

}
