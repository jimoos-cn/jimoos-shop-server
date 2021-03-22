package cn.jimoos;

/**
 * jimo event
 *
 * @author :keepcleargas
 * @date :2021-03-22 14:38
 */
public abstract class JmEvent {
    private long time;

    public JmEvent() {
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }
}
