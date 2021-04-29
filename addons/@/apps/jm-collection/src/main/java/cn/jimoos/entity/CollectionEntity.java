package cn.jimoos.entity;

import cn.jimoos.form.BeCollectionForm;
import cn.jimoos.model.Collection;
import cn.jimoos.model.RCollectionProduct;
import cn.jimoos.repository.CollectionRepository;
import cn.jimoos.utils.http.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:25.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CollectionEntity extends Collection {
    private CollectionRepository collectionRepository;

    private Page<RCollectionProduct> rCollectionProductPage;

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
}
