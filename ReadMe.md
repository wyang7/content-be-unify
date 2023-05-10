```
project
        \-- api(rpc-api释放包)
            \-- *.api(rpc服务interface，类名以Api后缀)
            \-- *.dto(rpc出入参数定义，以DTO后缀)
        \-- basic（基础层）
            \-- common
                \-- *.util(工具类)
                \-- *.data(公共数据结构定义，如枚举常量等)
            \-- repository
                \-- *.dao(dao interface)
                    \-- sql (sql implement)
                    \-- cache(cache implement)
                    \-- *.domain(表数据结构定义)
                \-- cache
                    \-- *.cache(cache定义)
                    \-- *.key(cache key定义，以Key为后缀)
                    \-- *.value(cache value定义，以Value为后缀)
            \-- adapter
            	  \-- *.rpc(远程rpc封装)
                  \-- *.http(第三方http封装)
        \-- manager（通用业务逻辑层）
            \-- *.mananger(通用业务逻辑层定义，以Manager为后缀)
            \-- *.dto(mananger出入参定义，以DTO为后缀)
        \-- service（具体业务逻辑层）
            \-- *.service(具体业务逻辑层定义，包括对api等实现，以Servcie为后缀)
            \-- *.dto(service出入参定义，以DTO后缀)
        \-- entrance（访问入口层）
            \-- web
                \-- *.controller
                \-- *.vo(显示层对象，以VO后缀)
            \-- job
                \-- *.job(job定义，以Job为后缀)
            \-- mq
                \-- *.consumer
                \-- *.message(消息数据结构定义，以Message为后缀)
```

详见《[工程结构规范](https://kms.netease.com/topics/topic/594/item/15069)》

### [脚手架文档](https://music-rtfm.hz.netease.com/music-arc-docs/root-pom-3.x/3.x.html)


### 工程生成

#### 快速生成项目

```
$mvn archetype:generate -DarchetypeCatalog=remote
...
Choose archetype:
1: remote -> com.netease.haitao:haitao-archetype (haitao-archetype)
2: remote -> org.apache.maven.archetypes:maven-archetype-quickstart (quickstart)
3: remote -> com.netease.music:music-server-sample-archetype (music-server-sample-archetype)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 2: 3
...
Define value for property 'groupId': com.netease.music
Define value for property 'artifactId': music-video-search
Define value for property 'version' 1.0-SNAPSHOT: :
Define value for property 'package' com.netease.music: : com.netease.music.video.search
```

#### archetype制作

mvn clean archetype:create-from-project -Darchetype.properties=./archetype.properties
archetype-catalog.xml添加，手动上传到公司maven仓库

```xml
<archetype>
  <groupId>com.netease.music</groupId>
  <artifactId>music-server-sample-archetype</artifactId>
  <version>0.3-SNAPSHOT</version>
  <description>music-server-sample-archetype</description>
</archetype>
```
