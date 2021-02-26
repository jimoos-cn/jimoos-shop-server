# 积墨开源商城 服务端代码

![Build Status](https://github.com/jimoos-cn/jimoos-shop-server/workflows/Java%20CI%20with%20Maven/badge.svg)

## 项目构建

`mvn clean package -Dmaven.test.skip=true -s docker/settings.xml`

## 项目入口

api-starter 客户端接口入口

portal-starter 后台管理接口入口

## 项目插件列表

- `addons/@`下 为项目内插件
- `addons/depends`为外部插件，外部插件禁止修改。
