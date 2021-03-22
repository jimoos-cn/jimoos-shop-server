package cn.jimoos;

/**
 * 事件发布器
 *
 * @author :keepcleargas
 * @date :2021-03-22 14:38
 */
public interface JmEventPublisher<T extends JmEvent> {
    /**
     * 发布事件
     *
     * @param event
     */
    void publish(T event);

    /**
     * 异步发布事件
     *
     * @param event
     */
    void asyncPublish(T event);
}
