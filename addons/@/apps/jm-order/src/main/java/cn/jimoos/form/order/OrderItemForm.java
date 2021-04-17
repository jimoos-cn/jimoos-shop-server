package cn.jimoos.form.order;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.product.model.ProductItem;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2020-11-30 10:21.
 */
@Data
public class OrderItemForm {
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品封面
     */
    private String productCover;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品类型
     */
    private String productType;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 实际支付价格 realPay
     */
    private BigDecimal productPrice;

    private Long skuId;

    private String skuCover;

    private String skuName;

    /**
     * 额外信息 Map
     */
    private Map<String, Object> extraMap = new HashMap<>();

    public OrderItemForm() {
    }

    public OrderItemForm(ProductItem productItem, Integer number) throws BussException {
        if (productItem == null) {
            throw new BussException("productItem不能为空");
        }
        if (StringUtils.isEmpty(productItem.getProductType())) {
            throw new BussException("productType不能为空");
        }

        if (number == null || number <= 0) {
            throw new BussException("商品数目不能为空");
        }

        if (productItem.getPrice() == null || productItem.getPrice().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new BussException("商品价格不能为空");
        }
        this.productId = productItem.getProductId();
        this.productCover = productItem.getProductCover();
        this.productName = productItem.getProductName();
        this.skuId = productItem.getSkuId();
        this.skuName = productItem.getSkuName();
        this.skuCover = productItem.getSkuCover();
        this.number = number;
        this.productPrice = productItem.getPrice();
        this.productType = productItem.getProductType();
    }

    /**
     * sku 版本
     */
    public OrderItemForm(Long productId, String productCover, String productName, String productType, Integer number, BigDecimal productPrice, Long skuId, String skuCover, String skuName) throws BussException {
        if (productId == null || productId <= 0) {
            throw new BussException("productId不能为空");
        }

        if (StringUtils.isEmpty(productType)) {
            throw new BussException("productType不能为空");
        }

        if (number == null || number <= 0) {
            throw new BussException("商品数目不能为空");
        }

        if (productPrice == null || productPrice.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new BussException("商品价格不能为空");
        }

        if (skuId == null || skuId <= 0) {
            throw new BussException("skuId不能为空");
        }

        if (StringUtils.isEmpty(skuName)) {
            throw new BussException("skuName不能为空");
        }

        this.productId = productId;
        this.productCover = productCover;
        this.productName = productName;
        this.productType = productType;
        this.number = number;
        this.productPrice = productPrice;
        this.skuId = skuId;
        this.skuCover = skuCover;
        this.skuName = skuName;
    }

    /**
     * 无 sku 版本订单
     */
    public OrderItemForm(Long productId, String productCover, String productName, String productType, Integer number, BigDecimal productPrice) throws BussException {
        if (productId == null || productId <= 0) {
            throw new BussException("productId不能为空");
        }

        if (StringUtils.isEmpty(productType)) {
            throw new BussException("productType不能为空");
        }

        if (number == null || number <= 0) {
            throw new BussException("商品数目不能为空");
        }

        if (productPrice == null || productPrice.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new BussException("商品价格不能为空");
        }

        this.productId = productId;
        this.productCover = productCover;
        this.productName = productName;
        this.productType = productType;
        this.number = number;
        this.productPrice = productPrice;
    }


    public void addExtraMap(String key, String value) {
        this.extraMap.put(key, value);
    }

    public void addAllExtraMap(Map<String, String> extraMap) {
        this.extraMap.putAll(extraMap);
    }

}
