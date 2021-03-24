# 积墨开源商城 服务端代码

![Build Status](https://github.com/jimoos-cn/jimoos-shop-server/workflows/Java%20CI%20with%20Maven/badge.svg)

## 项目构建

`mvn clean package -Dmaven.test.skip=true -s docker/settings.xml`

## 业务建模图

![业务建模图](docs/_media/model.jpg)

## 开发文档入口

文档地址: https://jimoos-cn.github.io/jimoos-shop-server/

## TODO

[X] 数据库配置读取     
[X] 华为云OBS接入    
[X] 用户注册    
[X] Banner管理    
[ ] 商品管理    
[ ] 购物车     
[ ] 下单      
[-] 优惠券     
[ ] 我的订单    
[ ] 商品集合
[ ] 商城介绍


----
[ ] 接口文档  
[ ] 接口部署

## 项目结构

- api-starter 客户端接口入口
- portal-starter 后台管理接口入口
- `addons/@`下 为项目内插件
- `addons/depends`为外部插件，外部插件禁止修改。
