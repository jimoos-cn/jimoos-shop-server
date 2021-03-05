package cn.jimoos.service;

import cn.jimoos.form.BaseSettingsCreateForm;
import cn.jimoos.model.BaseSettings;
import cn.jimoos.utils.http.Page;

/**
 * @author :keepcleargas
 * @date :2020-11-16 13:45.
 */
public interface IBaseSettingsService {
    /**
     * 根据keyword更新content
     *
     * @param content content
     * @param keyword keyword
     * @return int
     */
    void saveContentByKeyword(String content, String keyword);

    /**
     * 获取 配置对象
     *
     * @param keyword
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T getObjectByKeyword(String keyword, Class<T> tClass);

    /**
     * 保存基本的设置
     * <p>
     * 如果未存在或找不到 则插入新的设置
     *
     * @param baseSettingsCreateForm
     * @return
     */
    BaseSettings save(BaseSettingsCreateForm baseSettingsCreateForm);

    /**
     * 按 table 查询配置分页
     *
     * @param keyword 配置关键词
     * @param offset  -
     * @param limit   -
     * @return Page<BaseSettings>
     */
    Page<BaseSettings> table(String keyword, Integer offset, Integer limit);

    /**
     * 根据 id 删除
     *
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 根据关键词删除
     *
     * @param keyword
     * @return
     */
    int deleteByKeyword(String keyword);

    /**
     * 根据关键词 查询
     *
     * @param keyword
     * @return
     */
    BaseSettings findByKeyword(String keyword);

    /**
     * 根据关键词 查询 内容
     *
     * @param keyword
     * @return null 如果为空的话
     */
    String findContentByKeyword(String keyword);
}
