package cn.jimoos.factory;

import cn.jimoos.entity.RProductTagEntity;
import cn.jimoos.form.tag.RProductTagAddForm;
import cn.jimoos.form.tag.RProductTagDeleteForm;
import cn.jimoos.form.tag.RProductTagSearchForm;
import cn.jimoos.repository.ProductTagRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author SiletFlower
 * @date 2021/7/20 13:41:08
 * @description
 */
@Component
public class RProductTagFactory {
    @Resource
    ProductTagRepository productTagRepository;

    public RProductTagEntity create(RProductTagAddForm rProductTagAddForm) {
        RProductTagEntity rProductTagEntity = new RProductTagEntity(productTagRepository);
        rProductTagEntity.create(rProductTagAddForm.getTagId(), rProductTagAddForm.getProductId());
        return rProductTagEntity;
    }

    public RProductTagEntity create(RProductTagDeleteForm rProductTagDeleteForm) {
        RProductTagEntity rProductTagEntity = new RProductTagEntity(productTagRepository);
        rProductTagEntity.create(rProductTagDeleteForm.getTagId(), rProductTagDeleteForm.getProductId());
        return rProductTagEntity;
    }

    public RProductTagEntity create(RProductTagSearchForm rProductTagSearchForm) {
        RProductTagEntity rProductTagEntity = new RProductTagEntity(productTagRepository);
        rProductTagEntity.create(rProductTagSearchForm.getTagId(), rProductTagSearchForm.getProductId());
        return rProductTagEntity;
    }
}
