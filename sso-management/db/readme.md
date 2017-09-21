```cmd
#启动
>mongod.exe

#登录
>mongo

#切换数据库
>use admin

#新增管理员
>db.createUser({user: "admin",pwd: "123456",roles:[{role:"userAdminAnyDatabase", db: "admin" } ]})

#切换数据库
>use cas-mongo-database

# 新增用户
>db.createUser({user: "cas-config",pwd: "123456",roles: [ { role: "readWrite", db: "cas-mongo-database" }]})

#重启并开启认证
>mongod.exe --auth
```