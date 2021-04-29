package cn.jimoos.error;

import cn.jimoos.common.error.IErrorCode;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:21.
 */
public enum CollectionError implements IErrorCode {
    COLLECTION_NOT_FOUND("collection.collection_not_found", "商品集合不存在"),
    RPRODUT_COLLECTION_IS_EXIST("collection.rprodut_collection_is_exist", "关联关系已存在"),
    RPRODUT_COLLECTION_NOT_FOUND("collection.rprodut_collection_not_found", "关联关系不存在");

    private String code;
    private String desc;

    CollectionError(String code, String desc) {
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
