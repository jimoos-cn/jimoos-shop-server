# Jimoos-api-tracer

![Build Status](https://github.com/jimoos-cn/jimoos-api-tracer/workflows/Java%20CI%20with%20Maven/badge.svg)

实现 `Spring-boot mvc` 的接口 `request/response` 的捕捉应用。

> 支持版本 `spring-boot 2.1.x`,其它版本暂未测试。

## 快速上手

### 0. 初始化 数据库

```sql
CREATE TABLE `JM_API_TRACE`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `request`     text COLLATE utf8mb4_unicode_ci COMMENT '请求',
    `response`    longtext COLLATE utf8mb4_unicode_ci COMMENT '返回值',
    `tenant_id`   varchar(128) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '租户ID',
    `uri`         varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'path',
    `domain`      varchar(1023) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '域名',
    `method`      varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT 'http method,eg. POST',
    `path`        varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '路径',
    `ip`          varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '请求IP',
    `user_agent`  varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT 'user-agent',
    `os_family`   varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '系统',
    `correlation` varchar(128) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT 'correlation',
    `status`      varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '返回状态',
    `duration`    int(11)                                  DEFAULT NULL COMMENT '请求时间',
    `create_at`   bigint(20)                               DEFAULT NULL COMMENT '创建事件',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='api 接口的 request 和 response 跟踪';
```

### 1. 配置 maven setting.conf

```xml

<profile>
    <id>Repository Proxy</id>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
    <repositories>
        <repository>
            <id>oss</id>
            <name>oss</name>
            <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>
</profile>
```

### 2. 在接口模块(监听端)增加 依赖

```xml

<dependencies>
    <dependency>
        <groupId>cn.jimoos</groupId>
        <artifactId>jm-api-tracer</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>org.zalando</groupId>
        <artifactId>logbook-spring-boot-starter</artifactId>
        <version>2.4.1</version>
    </dependency>
    <!-- ua 解析 -->
    <dependency>
        <groupId>com.github.ua-parser</groupId>
        <artifactId>uap-java</artifactId>
        <version>1.5.1</version>
    </dependency>
</dependencies>
```

> 现在启动 你的spring-boot 应用，你的 表中`JM_API_TRACE`应该有 request/response 捕捉了。

> 注意 在启动命令里 须增加 `-Dlogbook.servlet.form-request=parameter`,
> 解决 `application/x-www-form-urlencoded` 捕捉`body`为空的问题。

idea 调试如下

![](docs/vm.png)

## 启动 api-trace-manager web 服务

* 1 下载 `manager jar`
  ,[Manager-Jar 下载地址](https://github.com/jimoos-cn/jimoos-api-tracer/releases/download/v0.0.1/jm-api-tracer-manager-0.0.1-SNAPSHOT.jar)

* 2 初始化 application.yml

```yaml
spring:
  application:
  name: jimoos-api-tracer-manager
  datasource:
    url: jdbc:mysql://localhost:3306/jimoos?useUnicode=true&characterEncoding=utf-8
    username: root
    password: 123456
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
mybatis:
  mapper-locations: classpath*:mapper/**/*.xml
  type-aliases-package: cn.jimoos.model
server:
  port: 4000
```

* 3 启动 manager.jar

```bash
java  -Djava.security.egd=file:/dev/./urandom   -jar jm-api-tracer-manager-0.0.1-SNAPSHOT.jar --spring.config.location=file:application.yml
```

> 配置数据库连接，需要和 你的应用 数据库连接一致。

* 4 打开应用 http://127.0.0.1:4000

输入密码 : `123456`

即可查看预览

![](docs/web.png)

### 使用 Docker镜像

```bash
docker run -d -p 4000:4000  -v /Users/keepcleargas/tmp/api-trace:/config --name jimoos-api-trace-manager -e TZ=Asia/Shanghai  --entrypoint '/bin/sh' keepcleargas/jm-api-tracer-manager -c 'java  -Djava.security.egd=file:/dev/./urandom   -jar /app.jar --spring.config.location=file:/config/application.yml'
```

> 注意 修改 config 的映射文件地址 到 application.yml 的 路径下。

### 其它

### 发布组件到oss

```bash
 export GPG_TTY=$(tty) 
 mvn clean deploy -P release 
```

### Docker 镜像构建

```bash
 #构建镜像
 docker build -t keepcleargas/jm-api-tracer-manager -f docker/Dockerfile .
 #推送镜像
 docker push keepcleargas/jm-api-tracer-manager
```

## 开源版权声明

[Lgpl-3.0](https://www.gnu.org/licenses/lgpl-3.0.en.html)

