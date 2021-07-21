package cn.jimoos.entity;

import cn.jimoos.dto.ProductTagDto;
import cn.jimoos.model.RProductTag;
import cn.jimoos.repository.ProductTagRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/7/20 11:30:09
 * @description
 */
@Data
@NoArgsConstructor
public class RProductTagEntity extends RProductTag {

    private ProductTagRepository productTagRepository;


    public RProductTagEntity(ProductTagRepository productTagRepository) {
        this.productTagRepository = productTagRepository;
    }

    /**
     * 创建绑定属性表
     * @param tagId
     * @param productId
     */
    public void create(Long tagId,Long productId) {
        this.setProductId(productId);
        this.setTagId(tagId);
        this.setCreateAt(System.currentTimeMillis());
    }

    public boolean delete(){
        return productTagRepository.deleteBoundValue(this);
    }

    /**
     * 保存或修改
     * @return boolean
     */
    public boolean save() {
        return productTagRepository.saveBoundValue(this);
    }

    /**
     * 查询商品绑定的Tag
     * @return List<ProductTagDto>
     */
    public List<ProductTagDto> queryBoundValue(){
        return productTagRepository.queryBoundValue(this);
    }

    /**
     * 查询商品未绑定的Tag
     * @return
     */
    public List<ProductTagDto> queryUnBoundValue() {
        return productTagRepository.queryUnBoundValue(this);
    }
}
