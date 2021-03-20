# huawei-obs

华为云OBS存储

## 如何使用

在 `starter` 项目里的`pom.xml` 加入依赖 即可使用

```xml

<dependency>
    <groupId>cn.jimoos</groupId>
    <artifactId>huawei-obs</artifactId>
    <version>${project.version}</version>
</dependency>
```

## 接口列表

## 配置

往 `settings`配置表 中 写入 `key` 值为`huawei.obs`,`content` 如下的

```json
{
  "accessKey": "请填写accessKey",
  "secretKey": "请填写secretKey",
  "endPoint": "请填写 endPoint 地址",
  "duration": 3600,
  "buckets": [
    {
      "type": 0,
      "bucketName": "bucketName1"
    },
    {
      "type": 1,
      "bucketName": "bucketName2"
    }
  ]
}

```