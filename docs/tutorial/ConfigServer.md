# CAS单点登录-配置中心（三）

# 本章计划及内容

计划：

* 微服务概念
* 配置中心充当角色
* 搭建配置中心
* cas连接配置中心

内容：

* 采用 `spring cloud Dalston SR3`搭建配置中心
* 采用 `spring cloud config 1.3.2.RELEASE`版本
* 采用[spring手脚架](http://start.spring.io/)构建项目框架
* 采用`spring cloud 1.5.3.RELEASE`版本依赖

# 什么是微服务？

介绍微服务并不是本章的重点，当然若没有了解过微服务的，可以先了解一下，再继续本章的内容。

推荐几篇文章：
[解析微服务架构(一)：什么是微服务](https://www.ibm.com/developerworks/community/blogs/3302cc3b-074e-44da-90b1-5055f1dc0d9c/entry/%E8%A7%A3%E6%9E%90%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%9E%B6%E6%9E%84_%E4%B8%80_%E4%BB%80%E4%B9%88%E6%98%AF%E5%BE%AE%E6%9C%8D%E5%8A%A1?lang=es)
[解析微服务架构(二)：融入微服务的企业集成架构](https://www.ibm.com/developerworks/community/blogs/3302cc3b-074e-44da-90b1-5055f1dc0d9c/entry/%E8%A7%A3%E6%9E%90%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%9E%B6%E6%9E%84_%E4%BA%8C_%E8%9E%8D%E5%85%A5%E5%BE%AE%E6%9C%8D%E5%8A%A1%E7%9A%84%E4%BC%81%E4%B8%9A%E9%9B%86%E6%88%90%E6%9E%B6%E6%9E%84?lang=zh)
[解析微服务架构(三)：微服务重构应用及IBM解决方案](https://www.ibm.com/developerworks/community/blogs/3302cc3b-074e-44da-90b1-5055f1dc0d9c/entry/%E8%A7%A3%E6%9E%90%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%9E%B6%E6%9E%84_%E4%B8%89_%E5%BE%AE%E6%9C%8D%E5%8A%A1%E9%87%8D%E6%9E%84%E5%BA%94%E7%94%A8%E5%8F%8AIBM%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88?lang=zh)

若读者不愿意看长篇大论抑或觉得过于抽象不能很好的理解，那么笔者简单的例子来介绍微服务

> 去超市，我们发现有专门卖肉的、卖零食的、卖蔬菜的、卖酒的，各部门**`各司其职`**，不像80年代卖肉的也卖菜，而每个部门相当于一个服务，同时也发现，超市有服务台，可以问酒都放哪里，在软件中我门可以简单理解为网关，但网关还有很多事情可以做，后面我们再介绍

微服务优点：

* 达成低耦合高内聚
* 软件高可用
* 独立部署
* 便于扩展

微服务缺点：

* 部署困难
* 增加系统复杂度
* 维护成本高

# 什么是配置中心？

先看两个图尝试理解理解

图一：
![这里写图片描述](http://img.blog.csdn.net/20170909142010710?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


图二：
![这里写图片描述](http://img.blog.csdn.net/20170909142038038?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


图一我们理解一下：
目前config server支持从三个地方获取配置文件，分别为：

* GIT
* SVN
* 本地文件

那非常好，我们可以把配置提交到版本仓库即可


图二我们简单理解下：

> 配置中心可以理解成超市中的仓库，我卖酒的，得问他我能卖些什么酒，也就是说，在软件中，我每个服务得问他我有些什么配置

也同时看得出，我们只要把配置文件提交到配置仓库，发送事件/钩子，那么服务就会随之而去配置中心获取文件更新，降低我们的维护成本

---

接下来我们不得不说一个事情，否则玩不转配置中心

**重点：**

* 服务是以`spring.application.name`的配置属性来决定这个服务的id，也就是告诉配置中心，我是谁

* `spring.profiles.active`这是要拿哪个配置文件，那么这样我们就可以区分多个维度或者说环境，这里可以是多个，可以逗号分隔

访问策略：

```cmd
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```
若配置为：

```properties
spring.application.name=sso
spring.profiles.active=dev
```
会寻找配置文件名为：sso-dev.properties文件，当然也可以为sso-dev.yml或其他

---

# 搭建配置中心

> 上面啰嗦了那么多，终于要到代码阶段了...


## 初始化框架代码

采用[spring initializr](http://start.spring.io/)下载
![这里写图片描述](http://img.blog.csdn.net/20170909144314705?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

如图所示以最简单的方式获取代码，当然也把原始代码放置于original-files文件夹下

由于项目工程集中化，所以解压拷贝到工程后需要进行简单调整

### pom.xml


1. 把spring-cloud-starter-config调整成spring-cloud-config-server
2. 添加仓库代理

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.carl.auth</groupId>
    <artifactId>sso-config</artifactId>
    <version>1.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>sso-config</name>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.6.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
        <spring-cloud.version>Dalston.SR3</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <!--为了加快速度，修改成国内的代理-->
    <repositories>
        <repository>
            <releases>
                <enabled>true</enabled>
            </releases>
            <id>maven2-release</id>
            <url>http://uk.maven.org/maven2/</url>
        </repository>
        <repository>
            <snapshots>
                <enabled>true</enabled>
                <!--快照版本库两个小时检查更新一遍-->
                <updatePolicy>interval:120</updatePolicy>
            </snapshots>
            <id>oss-snapshots</id>
            <url>http://repository.jboss.org/nexus/content/groups/public/</url>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <releases>
                <enabled>true</enabled>
            </releases>
            <id>maven2-release</id>
            <url>http://uk.maven.org/maven2/</url>
        </pluginRepository>
    </pluginRepositories>
</project>

```

### SsoConfigApplication.java

配置管理服务服务支持

```java
/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.ssoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SsoConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoConfigApplication.class, args);
    }
}

```


### application.properties/yml

把application.properties修改成application.yml，因为yml文件更直观

该配置文件为配置中心的项目设置

```yml
#指定日志输出文件
logging:
  file: "logs/sso-config.log"
info:
  name : "配置中心"

---
server:
  #服务端口
  port: 8888
  #访问路径
  context-path: /config
spring:
  profiles:
    #本地配置文件
    active:
      #配置文件本地化
      - native
  application:
    #指定应用名称
    name: sso-config
```


### 启动

```cmd
mvn spring-boot:run
```

若看到如下结果，恭喜你，配置成功

```cmd
2017-09-09 15:54:44.776  INFO 10696 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Located managed bean 'refreshEndpoint': registering with J
MX server as MBean [org.springframework.cloud.endpoint:name=refreshEndpoint,type=RefreshEndpoint]
2017-09-09 15:54:45.287  INFO 10696 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
2017-09-09 15:54:45.727  INFO 10696 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8888 (http)
2017-09-09 15:54:45.743  INFO 10696 --- [           main] c.c.auth.ssoconfig.SsoConfigApplication  : Started SsoConfigApplication in 17.302 seconds (JVM runnin
g for 28.779)

```

可以尝试访问：[http://localhost:8888/config](http://localhost:8888/config)

出现如下别灰心，那是正常的，不出来才是不正常的呢。。。
![这里写图片描述](http://img.blog.csdn.net/20170909160303174?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


# cas连接配置中心
> 到此，已经完成大部分的工程，那么接下来是我们的重头戏

1. 上面我们提到配置是放置于配置中心，而不放在服务内
2. 由于配置是放在与本地，那么spring的默认配置在目录为`resources/config`

---

接下来我们尝试写配置文件在config目录就叫`sso-dev.properties`吧，意味着cas的配置应该为：

```properties
spring.application.name=sso
spring.profiles.active=dev
```

测试一下配置文件（sso-dev.properties 内容如下）：

```properties
#
# 版权所有.(c)2008-2017. 卡尔科技工作室
#

hello_key=value
```

**重启一下配置中心**
试试访问：[http://localhost:8888/config/sso/dev](http://localhost:8888/config/sso/dev)

结果如下，恭喜你！！！

![这里写图片描述](http://img.blog.csdn.net/20170909161233089?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

---

继续走...
把sso-dev.properties文件修改成sso-server需要的配置（把application.properties内容拷贝过来即可）

```properties
#
# 版权所有.(c)2008-2017. 卡尔科技工作室
#

##
# CAS Server Context Configuration
#
server.context-path=/cas
server.port=8443

 server.ssl.enabled=false

server.max-http-header-size=2097152
server.use-forward-headers=true
server.connection-timeout=20000
server.error.include-stacktrace=NEVER

server.tomcat.max-http-post-size=2097152
server.tomcat.basedir=build/tomcat
server.tomcat.accesslog.enabled=true
server.tomcat.accesslog.pattern=%t %a "%r" %s (%D ms)
server.tomcat.accesslog.suffix=.log
server.tomcat.max-threads=10
server.tomcat.port-header=X-Forwarded-Port
server.tomcat.protocol-header=X-Forwarded-Proto
server.tomcat.protocol-header-https-value=https
server.tomcat.remote-ip-header=X-FORWARDED-FOR
server.tomcat.uri-encoding=UTF-8

spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
spring.http.encoding.force=true

##
# CAS Cloud Bus Configuration
#
spring.cloud.bus.enabled=false

endpoints.enabled=false
endpoints.sensitive=true

endpoints.restart.enabled=false
endpoints.shutdown.enabled=false

management.security.enabled=true
management.security.roles=ACTUATOR,ADMIN
management.security.sessions=if_required
management.context-path=/status
management.add-application-context-header=false

security.basic.authorize-mode=role
security.basic.enabled=false
security.basic.path=/cas/status/**

##
# CAS Web Application Session Configuration
#
server.session.timeout=300
server.session.cookie.http-only=true
server.session.tracking-modes=COOKIE

##
# CAS Thymeleaf View Configuration
#
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.cache=true
spring.thymeleaf.mode=HTML
##
# CAS Log4j Configuration
#
# logging.config=file:/etc/cas/log4j2.xml
server.context-parameters.isLog4jAutoInitializationDisabled=true

##
# CAS AspectJ Configuration
#
spring.aop.auto=true
spring.aop.proxy-target-class=true

##
# CAS Authentication Credentials
#
cas.authn.accept.users=casuser::Mellon

```


**再次重启sso-config**
试试访问：[http://localhost:8888/config/sso/dev](http://localhost:8888/config/sso/dev)

结果如下，再次恭喜你！！！
![这里写图片描述](http://img.blog.csdn.net/20170909161920009?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

---
接下来是**重点**
在sso-server工程下作业：

1. **清空** application.properties
2. 新增bootstrap.properties内容如下

bootstrap.properties：


```properties
#
# 版权所有.(c)2008-2017.广州市森锐科技股份有限公司

#指定日志文件
logging.file=logs/cas.log
info.name=单点登录系统

#定义application.name的id
spring.application.name=sso
#寻找配置中心为sso-dev.properties
spring.profiles.active=dev
#指定配置中心地址
spring.cloud.config.uri=http://localhost:8888/config
#开启配置中心
spring.cloud.config.enabled=true
#支持自动任务去配置中心刷新配置
spring.cloud.config.watch.enabled=true
#30秒刷新一次
spring.cloud.config.watch.initialDelay=30000
#请求配置中心超市
spring.cloud.config.watch.delay=1000
#检查配置健康
health.config.enabled=true
```

由于手脚架建立出来的不便于看调试信息，所以把log4j2.xml也调整一下，由于文件行数过多，就不放出来了，需要的自行下载，主要把warn改成info和一些debug

最后，项目的目录结构应该如下：
![这里写图片描述](http://img.blog.csdn.net/20170909171848624?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**运行sso-server**

```cmd
build.cmd run
```
若出现如下图，恭喜你，运行成功
![这里写图片描述](http://img.blog.csdn.net/20170909171254149?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


最后访问：[http://localhost:8443/cas](http://localhost:8443/cas)

用户名/密码：casuser/Mellon

若最后能登录成功，本章的内容你已得到一定的收获


# 总结
1. 简单介绍了微服务以及配置中心的作用
2. 搭建了配置中心以及把sso-server的配置文件放置于配置中心以本地文件存储

[代码下载](https://github.com/laomazi2006/sso/releases/tag/1.1.0)


# 作者联系方式

如果技术的交流或者疑问可以联系或者提出issue。

邮箱：huang.wenbin@foxmail.com

QQ: 756884434 (请注明：SSO-CSDN)


[//]: <> (> 如果项目对你有技术上的提升、工作上的帮助或者一些启示，不妨请小编喝杯咖啡，小编更会满怀激情的为大家讲解和输出博文哦。)

[//]: <> (微信<img src="http://img.blog.csdn.net/20170908092906735?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast" width="230" height="230"></img>支付宝<img src="http://img.blog.csdn.net/20170908100804669?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast" width="230" height="230"></img>)