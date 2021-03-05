package cn.jimoos.service.impl;

import cn.jimoos.dao.BaseSettingsMapper;
import cn.jimoos.form.BaseSettingsCreateForm;
import cn.jimoos.model.BaseSettings;
import cn.jimoos.service.IBaseSettingsService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.utils.mapper.JsonMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础设置服务类
 *
 * @author :keepcleargas
 * @date :2018-11-19 12:06.
 */
@Component
public class BaseSettingsService implements IBaseSettingsService {
    public static final String KEY = "settings.key";
    @Resource
    BaseSettingsMapper baseSettingsMapper;

    @Override
    public void saveContentByKeyword(String content, String keyword) {
        baseSettingsMapper.updateContentByKeyword(content, keyword);
    }

    @Override
    public <T> T getObjectByKeyword(String keyword, Class<T> tClass) {
        String content = findContentByKeyword(keyword);
        if (content != null) {
            return JsonMapper.INSTANCE.fromJson(content, tClass);
        } else {
            return null;
        }
    }


    @Override
    public BaseSettings save(BaseSettingsCreateForm baseSettingsCreateForm) {
        BaseSettings baseSettings = baseSettingsMapper.findOneByKeyword(baseSettingsCreateForm.getKeyword());
        if (baseSettings != null) {
            baseSettings.setContent(baseSettingsCreateForm.getContent());
            baseSettings.setKeyword(baseSettingsCreateForm.getKeyword());
            baseSettings.setUpdated(System.currentTimeMillis());
            baseSettingsMapper.updateByPrimaryKey(baseSettings);
            return baseSettings;
        } else {
            return init(baseSettingsCreateForm);
        }
    }

    @Override
    public Page<BaseSettings> table(String keyword, Integer offset, Integer limit) {
        Map<String, Object> qm = new HashMap<>(3);
        qm.put("name", keyword);
        qm.put("offset", offset);
        qm.put("limit", limit);

        int count = baseSettingsMapper.queryTableCount(qm);
        if (count > 0) {
            return Page.create((long) count, baseSettingsMapper.queryTable(qm));
        }
        return Page.empty();
    }

    /**
     * 初始化基础设置
     *
     * @param baseSettingsCreateForm
     * @return
     */
    private BaseSettings init(BaseSettingsCreateForm baseSettingsCreateForm) {
        BaseSettings baseSettings = new BaseSettings();
        baseSettings.setKeyword(baseSettingsCreateForm.getKeyword());
        baseSettings.setContent(baseSettingsCreateForm.getContent());
        baseSettings.setCreated(System.currentTimeMillis());
        baseSettings.setDeleted(0);
        baseSettingsMapper.insert(baseSettings);
        return baseSettings;
    }

    @Override
    public int delete(int id) {
        return baseSettingsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByKeyword(String keyword) {
        BaseSettings baseSettings = baseSettingsMapper.findOneByKeyword(keyword);

        if (ObjectUtils.isEmpty(baseSettings)) {
            return 0;
        } else {
            return baseSettingsMapper.deleteByPrimaryKey(baseSettings.getId());
        }
    }

    @Override
    public BaseSettings findByKeyword(String keyword) {
        Assert.notNull(keyword, "查找的关键词不能为空!");
        return baseSettingsMapper.findOneByKeyword(keyword);
    }

    @Override
    public String findContentByKeyword(String keyword) {
        Assert.notNull(keyword, "查找的关键词不能为空!");
        BaseSettings baseSettings = baseSettingsMapper.findOneByKeyword(keyword);
        if (baseSettings == null) {
            return null;
        } else {
            return baseSettings.getContent();
        }
    }
}
