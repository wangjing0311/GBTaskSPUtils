package wangjing.shareprefrenceutil.task;

public interface TaskExecutor {
    public void post(Task task);

    public boolean postDelayed(Task task, long delayMillis);
}
