CAS Overlay Template
============================

Generic CAS WAR overlay to exercise the latest versions of CAS. This overlay could be freely used as a starting template for local CAS war overlays. The CAS services management overlay is available [here](https://github.com/apereo/cas-services-management-overlay).

# Versions

```xml
<cas.version>5.1.x</cas.version>
```

# Requirements
* JDK 1.8+

# Configuration

The `etc` directory contains the configuration files and directories that need to be copied to `/etc/cas/config`.

# Build

To see what commands are available to the build script, run:

```bash
./build.sh help
```

To package the final web application, run:

```bash
./build.sh package
```

To update `SNAPSHOT` versions run:

```bash
./build.sh package -U
```


## Executable WAR

Run the CAS web application as an executable WAR.

```bash
./build.sh run
```

## Spring Boot

Run the CAS web application as an executable WAR via Spring Boot. This is most useful during development and testing.

```bash
./build.sh bootrun
```

### Warning!

Be careful with this method of deployment. `bootRun` is not designed to work with already executable WAR artifacts such that CAS server web application. YMMV. Today, uses of this mode ONLY work when there is **NO OTHER** dependency added to the build script and the `cas-server-webapp` is the only present module. See [this issue](https://github.com/apereo/cas/issues/2334) and [this issue](https://github.com/spring-projects/spring-boot/issues/8320) for more info.


## Spring Boot App Server Selection
There is an app.server property in the pom.xml that can be used to select a spring boot application server.
It defaults to "-tomcat" but "-jetty" and "-undertow" are supported. 
It can also be set to an empty value (nothing) if you want to deploy CAS to an external application server of your choice and you don't want the spring boot libraries included. 

```xml
<app.server>-tomcat<app.server>
```

## Windows Build
If you are building on windows, try build.cmd instead of build.sh. Arguments are similar but for usage, run:  

```
build.cmd help
```

## External

Deploy resultant `target/cas.war`  to a servlet container of choice.


## 生成步骤
各参数含义：
  * -genkeypair 生成密钥
  * -keyalg 指定密钥算法，这时指定RSA,
  * -keysize 指定密钥长度，默认是1024位,这里指定2048，长一点，我让你破解不了（哈哈...）,
  * -siglag 指定数字签名算法，这里指定为SHA1withRSA算法
  * -validity 指定证书有效期，这里指定36500天，也就是100年，我想我的应用用不到那么长时间
  * -alias 指定别名，这里是cas.server.com
  * -keystore 指定密钥库存储位置，这里存在d盘
  * -dname 指定用户信息，不用一个一个回答它的问题了；

  注意：CN=域名，我们采用`passport.sso.com`

1.
```cmd
keytool -genkeypair -keyalg RSA -keysize 2048 -sigalg SHA1withRSA -validity 36500 -alias passport.sso.com -keystore d:/tomcat.keystore -dname "CN=passport.sso.com,OU=sunrizetech,O=esaleb,L=GuangZhou,ST=GuangDong,C=CN"
# 输入上述命令，下面密码我们输入 123456,然后一直回车，就在d盘生成了tomcat.keystore文件；
```

2.导出数字证书
  在cmd下输入如下命令：
```cmd
keytool -exportcert -alias passport.sso.com -keystore d:/tomcat.keystore  -file d:/tomcat.cer -rfc
```

3.将服务端的证书tomcat.cer导入到客户端java的cacerts证书库中
cmd到 `${JAVA_HOME}jre\lib\security`

运行如下命令：
```cmd
keytool -import -alias passport.sso.com -keystore %JAVA_HOME%\jre\lib\security\cacerts -file d:/tomcat.cer -trustcacerts
# 密码为 changeit
```

4.检查是否导入成功
```cmd
keytool -list -keystore "%JAVA_HOME%\jre\lib\security\cacerts" | findstr/i server
```
有东西输出代表成功