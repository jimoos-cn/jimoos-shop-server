package cn.jimoos.form.product;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 20:31.
 */
@Data
public class BeProductForm extends AbstractAdminForm4L {
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
    private Long merchantId = 0L;

    /**
     * sku列表
     */
    private List<SkuInput> skus;

    /**
     * 标签
     */
    private List<Long> tagIds;

    @Data
    public static class SkuInput {
        private Long id;
        /**
         * 销售属性键值对
         */
        private List<Attr> attrs;

        /**
         * 封面
         */
        private String cover;

        /**
         * 价格
         */
        private BigDecimal price;

        /**
         * 展示价
         */
        private BigDecimal showPrice;
    }

    @Data
    public static class Attr {
        /**
         * attrId
         */
        private Long attrId;
        /**
         * attrName
         */
        private String attrName;
        /**
         * attrValueId
         */
        private Long attrValueId;
        /**
         * attrValueName
         */
        private String attrValueName;
    }
}
