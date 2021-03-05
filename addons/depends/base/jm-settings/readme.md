# jm-settings

应用设置,通过写入 keyword - content 配对 使用。

## 如何使用

在 `starter` 项目里的`pom.xml` 加入依赖 即可使用

```xml

<dependency>
    <groupId>cn.jimoos</groupId>
    <artifactId>jm-settings</artifactId>
    <version>${project.version}</version>
</dependency>
```

具体的使用 参考 `jm-settings-example`

``` java
@Configuration
public class SettingsConfiguration {
    @Resource
    BaseSettingsService baseSettingsService;

    @Bean
    public SettingsProperties getExampleProperties() {
        return baseSettingsService.getObjectByKeyword(BaseSettingsService.KEY, SettingsProperties.class);
    }
}
```

## 接口

### 使用方法

导入 `jm-settings-example` 的 `SettingsClient`到项目`rest`里.

### 管理接口

http调试文件: `resources/http/SettingClientHttp.http`

* POST /bAdmin/v1/settings 写入配置
* GET /bAdmin/v1/settings/byKeyword?keyword=${keyword} 获取配置

## DB

liquibase 可用 sql 路径。
`resources/db/settings-init.sql`

```sql
create table t_base_settings
(
    id      int(11) unsigned auto_increment
        primary key,
    keyword varchar(255)     null comment '关键词, PAY.ALI PAY.MP',
    content text             null comment '配置内容',
    created bigint           null,
    updated bigint           null,
    deleted int(1) default 0 null
) comment '设置表';
```

## 维护信息

key | 值
-----|-----
author | keepcleargas
email | qisheng.chen@jimoos.cn
version | 0.0.1