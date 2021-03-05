package cn.jimoos.rest.be;

import cn.jimoos.form.BaseSettingsCreateForm;
import cn.jimoos.model.BaseSettings;
import cn.jimoos.service.impl.BaseSettingsService;
import cn.jimoos.utils.http.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

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
     * @param content
     * @return
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public BaseSettings saveSetting(@RequestParam("keyword") String keyword, @RequestParam("content") String content) throws IOException {
        BaseSettingsCreateForm baseSettingsCreateForm = new BaseSettingsCreateForm();
        baseSettingsCreateForm.setKeyword(keyword);
        baseSettingsCreateForm.setContent(content);
        BaseSettings baseSettings = baseSettingsService.save(baseSettingsCreateForm);
        return baseSettings;
    }

    /**
     * 查询设置列表
     *
     * @param keyword
     * @param offset
     * @param limit
     * @return
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
     * @return
     */
    @GetMapping(value = "/byKeyword", produces = "application/json; charset=utf-8")
    public BaseSettings getSettings(@RequestParam("keyword") String keyword) {
        return baseSettingsService.findByKeyword(keyword);
    }
}
