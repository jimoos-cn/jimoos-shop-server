package cn.jimoos.common.error;

/**
 * 错误获取接口
 *
 * @author :keepcleargas
 * @date :2021-03-23 15:15.
 */
public interface IErrorCode {
    /**
     * 返回错误编码
     *
     * @return
     */
    String getCode();

    /**
     * 返回错误描述
     *
     * @return
     */
    String getDesc();
}
