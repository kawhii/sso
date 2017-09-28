# 配置

该例子需要设置host为passport.sso.com

[证书导入https教程](http://blog.csdn.net/u010475041/article/details/77931867)

需要启动配置中心、cas服务、客户端demo

启动demo:
```cmd
mvn jetty:run
```

访问：
[http://localhost:8080/sample](http://localhost:8080/sample)
不需登录访问
[http://localhost:8080/sample/zhangsan.jsp](http://localhost:8080/sample/zhangsan.jsp)