package cn.jimoos.entity;

import cn.jimoos.form.BeCollectionForm;
import cn.jimoos.form.BeRCollectionProductForm;
import cn.jimoos.model.Collection;
import cn.jimoos.model.RCollectionProduct;
import cn.jimoos.repository.CollectionRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:25.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CollectionEntity extends Collection {
    private CollectionRepository collectionRepository;

    private List<RCollectionProduct> rCollectionProductInputs;
    private boolean batchUpdate = false;

    public CollectionEntity(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public void update(BeCollectionForm beCollectionForm) {
        this.setTitle(beCollectionForm.getTitle());
        this.setSubTitle(beCollectionForm.getSubTitle());
        this.setRecommend(beCollectionForm.getRecommend());
        this.setStatus(beCollectionForm.getStatus());
        this.setSort(beCollectionForm.getSort());
        this.setType(beCollectionForm.getType());
        this.setUpdateAt(System.currentTimeMillis());
    }

    public void up() {
        this.setStatus(Boolean.TRUE);
        this.setUpdateAt(System.currentTimeMillis());
    }

    public void down() {
        this.setStatus(Boolean.FALSE);
        this.setUpdateAt(System.currentTimeMillis());
    }

    public void recommend() {
        this.setRecommend(Boolean.TRUE);
        this.setUpdateAt(System.currentTimeMillis());
    }

    public void unRecommend() {
        this.setRecommend(Boolean.FALSE);
        this.setUpdateAt(System.currentTimeMillis());
    }

    public void softDelete() {
        this.setDeleted(true);
        this.setUpdateAt(System.currentTimeMillis());
    }

    public void addProduct(BeRCollectionProductForm form) {
        if (CollectionUtils.isEmpty(rCollectionProductInputs)) {
            rCollectionProductInputs = new ArrayList<>();
        }
        RCollectionProduct rCollectionProduct = new RCollectionProduct();
        rCollectionProduct.setCollectionId(form.getCollectionId());
        rCollectionProduct.setProductId(form.getProductId());
        rCollectionProduct.setCreateAt(System.currentTimeMillis());
        rCollectionProduct.setSort(form.getSort());

        rCollectionProductInputs.add(rCollectionProduct);
    }

    public void updateProduct(BeRCollectionProductForm beRCollectionProductForm) {
        if (CollectionUtils.isEmpty(rCollectionProductInputs)) {
            rCollectionProductInputs = new ArrayList<>();
        }

        RCollectionProduct rCollectionProduct = collectionRepository.findByRId(beRCollectionProductForm.getId());

        if (rCollectionProduct != null) {
            beRCollectionProductForm.setSort(beRCollectionProductForm.getSort());
        }
        rCollectionProductInputs.add(rCollectionProduct);
    }

    public void batchUpdateProducts(List<BeRCollectionProductForm> collectionProductForms) {
        this.setBatchUpdate(true);
        for (BeRCollectionProductForm beRCollectionProductForm : collectionProductForms) {
            addProduct(beRCollectionProductForm);
        }
    }

    public void remove(BeRCollectionProductForm beRCollectionProductForm) {
        collectionRepository.deleteRCollectionProduct(beRCollectionProductForm.getId());
    }
}
