# 数据迁移

用于addon里的 数据库安装和维护。

局限于liquibase ,不支持sql更改，只能叠加。

已支持 `classpath*:/db/**` 下的 自动加载，含`init`的优先的 `String.CompareTo()`
的顺序的加载。

## 配置数据库

`application.yml` 配置 数据库地址。

## 添加依赖的addon依赖

pom.xml添加依赖，eg:

```xml
<dependency>
      <groupId>cn.jimoos</groupId>
      <artifactId>jm-ping</artifactId>
      <version>${project.version}</version>
</dependency>
```

## 执行迁移
执行 test中`MigrationDone` -> `migrationAction` 进行迁移。

## 网络问题

加载 `http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.6.xsd`
失败，请使用代理进行第一次加载。
