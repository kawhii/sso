# [SSO](https://kawhii.github.io/sso/index.html) [![Build Status](https://travis-ci.org/kawhii/sso.svg?branch=master)](https://travis-ci.org/kawhii/sso) [![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/kawhii/sso/master/LICENSE) [![GitHub issues](https://img.shields.io/github/issues/kawhii/sso.svg)](https://github.com/kawhii/sso/issues)

打造一个单点登录平台，其中包括以下子系统

* CAS SERVER
* 配置中心
* 服务管理系统
* 监控平台
* 客户端集成(cas client、pac4j、shiro)

并且在博客中记录整个搭建过程以及注意事项，目前教程如下：

![目前教程](http://img.blog.csdn.net/20171019000741357?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


# 特性

* GitHub授权登录
* Cas Clint、Shiro Pac4j Client集成
* jdbc用户密码加密
* 自定义主题
* 配置统一管理
* 根据官网5.1.x新版本迭代
* 各阶段发布博客教程
* 密码管理
* 一键启动
* 第三方登录接入(QQ,WeChat,CSDN,GitHub)
* 绑定用户
* 验证码输出
* 自定义校验器
* 验证码发送、校验（注册发送邮箱验证码）

## Tutorial: [![Csdn Blog Tutorial](https://img.shields.io/badge/csdn%20blog-tutorial-orange.svg)](http://blog.csdn.net/u010475041/article/category/7156505)

* [Blog](http://blog.csdn.net/u010475041/article/category/7156505)
* [Support](https://github.com/kawhii/sso/wiki)

域名：passport.sso.com

用户：

| 用户名 |密码|是否可登录|备注|
|:-------|:-------|:-------|:-------|
|admin|123|√||
|zhangsan|12345678|√||
|zhaosi|12345|×|禁用|
|wangwu|1234|√|需修改密码|

### 注意
* 由于修改密码功能目前是发送到笔者的邮箱，若调整，需要修改`sso-server/src/main/resources/profile/dev/sql/data-dev.sql`
* 密码修改功能可以关闭问题回答功能

## 模块介绍


| 模块名 |模块介绍|端口情况|必须https|path|启动循序
|:-------|:-------|:----|:-------|:-----|:--|
|sso-server|cas服务|8443|√|cas|2|
|sso-config|配置中心|8888|×|config|1|
|sso-management|service管理|8081|×|cas-management|3|
|sso-cas-client-demo|cas-client-demo|8080|×|/sample|4|
|sso-client-shiro-demo|shiro-client-demo|8083|×|/|5|
|sso-client-proxy-demo|OAuth2代理转发客户端|8808|×|/||

## 代码下载 [![码云](https://img.shields.io/badge/download-码云-yellowgreen.svg)](https://git.oschina.net/Kawhi-Carl/sso) [![Github](https://img.shields.io/badge/download-GitHub-brightgreen.svg)](https://github.com/kawhii/sso)

1. 每一个阶段都会封版打一个tag，需要的进行[下载](https://github.com/kawhii/sso/releases)
2. 原始构建文件存在于original-files目录下

## Development [![Dependency Status](https://www.versioneye.com/user/projects/59b6afd60fb24f004e1a656b/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/59b6afd60fb24f004e1a656b) <!-- [![Dependency Status](https://dependencyci.com/github/kawhii/sso/badge)](https://dependencyci.com/github/kawhii/sso)  -->[![codebeat badge](https://codebeat.co/badges/4b430ffd-0cb8-4310-b081-955a66e65c76)](https://codebeat.co/projects/github-com-kawhii-sso-master)

* jdk8
* maven3

### 帮助
```cmd
build.cmd help
```
输出以下帮助信息
```cmd
"Usage: build.bat [help|sso-server|sso-management|sso-config|cas-client-demo|shiro-client-demo|run-all|hosts]"
1. sso-config: Config Server
2. sso-server: CAS Server
3. sso-management: Cas Management
4. cas-client-demo: CasClient Demo
5. shiro-client-demo: ShiroDemo
6. run: Run all server
7. init: set '127.0.0.1 passport.sso.cm' to HOSTS, import cert to D:\soft\work\java\jdk1.8-144\jre\lib\security\cacerts
```

### 初始化

1. 负责把`passport.sso.com`设置到host文件
2. 把域名自签名证书导入到java环境（提示信息，第一个需要输入密码为**123456**，第二个导入密码为**changeit**）

```cmd
build.cmd init
```

### 启动服务

> 由于启动服务多，开始占用CPU、内容稍高

```cmd
build.cmd run
```


* sso-config [配置中心](http://passport.sso.com:8888/config)
* sso-server [单点登录服务](https://passport.sso.com:8443/cas)
* cas-client-demo [cas客户端](http://passport.sso.com:8080/sample)
* shiro-client-demo [shiro客户端](http://passport.sso.com:8083)
* sso-management [服务管理客户端](http://passport.sso.com:8081/cas-management)

```cmd
#server-id 为上面的各服务名称，
#如启动sso-management为，build.cmd sso-management

build.cmd [server-id]
```

# 联系方式

如果技术的交流或者疑问可以联系或者提出issue。

邮箱：huang.wenbin@foxmail.com


QQ: 756884434 (请注明：SSO-github)

> 如果项目对你有技术上的提升、工作上的帮助或者一些启示，不妨请小编喝杯咖啡，小编更会满怀激情的为大家讲解和输出博文哦。

微信
<img src="http://img.blog.csdn.net/20170908092906735?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast" width="230" height="230"/>
支付宝
<img src="http://img.blog.csdn.net/20170908100804669?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMDQ3NTA0MQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast" width="230" height="230"/>
