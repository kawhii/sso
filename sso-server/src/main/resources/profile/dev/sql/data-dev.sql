/*
初始化数据
*/



---明文MD5数据

/*123*/
INSERT INTO SYS_USER VALUES ('admin', '202cb962ac59075b964b07152d234b70', 'huang.wenbin@foxmail.com', '广州天河', 24, 0, 0);
/*12345678*/
INSERT INTO SYS_USER VALUES ('zhangsan', '25d55ad283aa400af464c76d713c07ad', 'zhangsan@foxmail.com', '广州越秀', 26, 0, 0);
/*1234*/
/*锁定用户*/
INSERT INTO SYS_USER VALUES('zhaosi','81dc9bdb52d04dc20036dbd8313ed055', 'zhaosi@foxmail.com', '广州海珠', 25, 0 , 1);
/*12345*/
/*不可用*/
INSERT INTO SYS_USER VALUES('wangwu','827ccb0eea8a706c4c34a16891f84e7b', 'wangwu@foxmail.com', '广州番禺', 27, 1 , 0);


---加盐数据
/*123  可以采用PasswordSaltTest输出值*/
INSERT INTO SYS_USER_ENCODE VALUES ('admin_en', 'bfb194d5bd84a5fc77c1d303aefd36c3', 'huang.wenbin@foxmail.com', '江门蓬江', 24, 0, 0);
INSERT INTO SYS_USER_ENCODE VALUES ('zhangsan_en', '68ae075edf004353a0403ee681e45056',  'zhangsan@foxmail.com', '深圳宝安', 21, 0, 0);
INSERT INTO SYS_USER_ENCODE VALUES ('zhaosi_en', 'd66108d0409f68af538301b637f13a18',  'zhaosi@foxmail.com', '清远清新', 20, 0, 1);
INSERT INTO SYS_USER_ENCODE VALUES ('wangwu_en', '44b907d6fee23a552348eabf5fcf1ac7',  'wangwu@foxmail.com', '佛山顺德', 19, 1, 0);
