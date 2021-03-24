package cn.jimoos.dic;

/**
 * @author :keepcleargas
 * @date :2021-01-19 12:04.
 */
public class BannerStatus {
    /**
     * 增加 私有方法 明确为 util/status 类
     */
    private BannerStatus() {
    }

    /**
     * 下架
     */
    public static final Integer DOWN = 0;
    /**
     * 上架
     */
    public static final Integer UP = 1;

}
