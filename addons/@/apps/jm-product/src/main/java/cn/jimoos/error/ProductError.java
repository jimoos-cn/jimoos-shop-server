package cn.jimoos.error;


import cn.jimoos.common.error.IErrorCode;

/**
 * @author keepcleargas
 * @date :2021-03-29 19:52.
 */
public enum ProductError implements IErrorCode {
    PRODUCT_TAG_NOT_EXIST("product.tag_not_exist", "产品标签不存在！"),
    PRODUCT_CATEGORY_NOT_EXIST("product.category_not_exist", "商品分类不存在！"),
    PRODUCT_SKU_ATTR_NOT_EXIST("product.attr_value_not_exist", "销售属性不存在！"),
    PRODUCT_NOT_EXIST("product.not_exist", "商品不存在"),
    PRODUCT_RECOMMEND("product.recommend", "该商品已经是精品"),
    PRODUCT_NOT_RECOMMEND("product.not_recommend", "该商品不是精品"),
    PRODUCT_INFO_CHANGE("product.info_change", "商品信息发生变化，请刷新重试！"),
    PRODUCT_SKU_LOSE("product.lose_exist", "商品的sku缺失,不可上架"),
    CATEGORY_USED("product.category_used", "已有商品使用该分类，不可删除"),
    TAG_USED("product.tag_used", "已有商品使用该标签，不可删除"),
    ATTR_USED("product.attr_used", "已有sku使用该销售属性，不可删除"),
    ATTR_VALUE_USED("product.attr_value_used", "已有sku使用该销售属性值，不可删除"),
    RECOMMEND_NOT_DOWN("product.recommend_not_down", "已被推荐的商品不可下架，请先取消推荐"),
    SKU_USED("product.sku_used", "与商品相关的sku被绑定了,不可删除"),
    PRODUCT_NOT_LIST("product.not_list", "商品未上架"),
    TAG_NOT_FOUND("product.tag_not_found", "标签不存在"),
    CATEGORY_NOT_FOUND("product.category_not_found", "分类不存在"),
    ATTR_NOT_FOUND("product.attr_not_found", "属性不存在");
    private final String code;
    private final String desc;

    ProductError(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
