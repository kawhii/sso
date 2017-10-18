# CAS单点登录-简介（一）

> 由于工作上的需求以及个人的兴趣，最近在研究CAS单点登录，为了记录学习的一些过程，以便后面翻阅也一同给大家分享一下。笔者文采并不是很好，但注意事项还是会着重，那么如果大家看到不正确欢迎纠正留言大家互相学习。

我们这次实战用的版本为`CAS-5.1.3`

# 1. 什么是CAS？什么是单点登录？
CAS是一个单点登录框架，开始是由耶鲁大学的一个组织开发，后来归到apereo去管。
同时CAS也是开源，遵循着apache 2.0协议，代码目前是在[github](https://github.com/apereo/cas)上管理。

单点登录：Single Sign On,简称SSO，SSO使得在多个应用系统中，用户只需要登录一次就可以访问所有相互信任的应用系统。

通俗理解为一个应用登录了，其他被授权的应用不用再登录。
应用例子为：淘宝登录了天猫不用再登录。

# 2. 应用场景

* 分布式多系统用户集中管理
* 用户权限集中管理
* 多因素认证（如微信pc端登录手机确认）

如公司有多个系统，分别OA系统、CRM系统、财务管理系统、设备管理系统等，总不能访问每个系统都要登录一遍吧，用户会疯掉的，应该我们认证一遍，其他系统即可访问。

# 3. CAS特性

* 多协议认证支持
 * CAS
 * OAuth
 * OpenID
 * SAML
 * REST
* 多因素认证
 * [Duo](https://www.duo.com/)
 * [Google Authenticator](https://lastpass.com/auth/) 
 * [YubiKey](https://upgrade.yubico.com/getapikey/)
* [Cas-Management](https://apereo.github.io/cas/5.1.x/installation/Installing-ServicesMgmt-Webapp.html) 服务管理中心（谁能接入SSO）
* TGT（授权码）管理、ST（票据）管理
* 动态主题（不通客户端提供不同的登录页）
* 多方式认证（校验器）
* 配置中心
* 监控平台
* 多属性管理（默认只返回用户名，例如后续返回权限数据）
* 密码管理
* ...

CAS特性非常强大，我们可以到[官网文档](https://apereo.github.io/cas/5.1.x/index.html)一一了解。

# 3. 计划

## 3.1. 博文计划
笔者也是有工作，可能不会输出得很快。那么计划一周会输出1~3篇博文来搭建CAS以及介绍CAS。

## 3.2. SSO搭建计划

1. CAS搭建HelloWorld
2. SSL证书生成并导入https
3. 配置中心搭建
4. cas-management搭建
5. 监控平台搭建
6. CAS数据库认证，密码管理
7. 多属性返回
8. 自定义主题
9. 授权码持久化配置、票据持久化配置
10. CAS多系统安全
11. CAS client demo测试单点登录
12. pa4j客户端单点登录整合
13. rest协议认证

# 4. 注意事项

所需知识：

* spring boot
* spring security
* spring cloud
* maven

# 5. 复习

提前看看cas协议的流程图：

![这里写图片描述](https://apereo.github.io/cas/5.1.x/images/cas_flow_diagram.png)