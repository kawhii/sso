# 证书生成（开启ssl时参考此文档）

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