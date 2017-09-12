/*
初始化数据
*/
INSERT INTO SYS_USER VALUES('admin','123', 'huang.wenbin@foxmail.com', '广州天河', 24, 0, 0);
INSERT INTO SYS_USER VALUES('test','12345678', 'test@foxmail.com', '广州越秀', 26, 0, 0);
/*锁定用户*/
INSERT INTO SYS_USER VALUES('test_l','1234', 'test_lock@foxmail.com', '广州海珠', 25, 0 , 1);
/*不可用*/
INSERT INTO SYS_USER VALUES('test_d','12345', 'test_d@foxmail.com', '广州番禺', 27, 1 , 0);
