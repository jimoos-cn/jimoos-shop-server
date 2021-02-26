package cn.jimoos;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 1. 配置数据库
 * 2. 配置依赖的addon包
 * 执行迁移
 *
 * @author :keepcleargas
 * @date :2020-03-31 22:18.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MigrationApp.class})
public class MigrationDone {
    @Test
    public void migrationAction() {

    }
}
