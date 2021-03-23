package cn.jimoos.rest.be;

import cn.jimoos.form.BaseSettingsCreateForm;
import cn.jimoos.model.BaseSettings;
import cn.jimoos.service.impl.BaseSettingsService;
import cn.jimoos.utils.http.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Back-end settings api
 *
 * @author :keepcleargas
 * @date :2021-02-16 13:59.
 */
@RestController
@RequestMapping("/bAdmin/v1/settings")
@Slf4j
public class BeSettingsApi {
    @Resource
    BaseSettingsService baseSettingsService;

    /**
     * 保存配置
     *
     * @param keyword 例如ali.pay
     * @param content 配置内容
     * @return setting 对象
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public BaseSettings saveSetting(@RequestParam("keyword") String keyword, @RequestParam("content") String content) {
        BaseSettingsCreateForm baseSettingsCreateForm = new BaseSettingsCreateForm();
        baseSettingsCreateForm.setKeyword(keyword);
        baseSettingsCreateForm.setContent(content);
        return baseSettingsService.save(baseSettingsCreateForm);
    }

    /**
     * 查询设置列表
     *
     * @param keyword 例如ali.pay
     * @param offset  -
     * @param limit   -
     * @return setting 对象列表
     */
    @GetMapping(value = "/query", produces = "application/json; charset=utf-8")
    public Page<BaseSettings> table(@RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam("offset") int offset,
                                    @RequestParam("limit") int limit) {
        if ("".equals(keyword)) {
            keyword = null;
        }
        return baseSettingsService.table(keyword, offset, limit);
    }


    /**
     * 获取配置
     *
     * @param keyword 例如ali.pay
     * @return setting 对象
     */
    @GetMapping(value = "/byKeyword", produces = "application/json; charset=utf-8")
    public BaseSettings getSettings(@RequestParam("keyword") String keyword) {
        return baseSettingsService.findByKeyword(keyword);
    }
}
