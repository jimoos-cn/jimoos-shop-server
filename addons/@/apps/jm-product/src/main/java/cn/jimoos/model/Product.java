package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品(spu)
 *
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Data
@NoArgsConstructor
public class Product {
    /**
     * 商品(SPU) ID
     */
    private Long id;

    /**
     * spu名称
     */
    private String name;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * spu描述
     */
    private String text;

    /**
     * 商品封面
     */
    private String cover;

    /**
     * 商品视频 多个视频逗号分隔
     */
    private String videoUrl;

    /**
     * banner图片 多个图片逗号分隔
     */
    private String bannerUrls;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 运营销量
     */
    private Integer fakeSales;

    /**
     * 0 未上架 1待审核 2 上架中 3 未通过
     */
    private Byte status;

    /**
     * 0 普通商品
     */
    private Byte type;

    /**
     * 商家 ID
     */
    private Long merchantId;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 0 未删除 1 已删除
     */
    private Boolean deleted;
}