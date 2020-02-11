# init_schema.sql
数据库基本表初始化建表语句

# init_Ownschema.sql
数据库个人表初始化建表语句

## tb_regist_message
  `id`  主键
  
  `email` 收件Email地址
  
  `verify_code`  验证码内容
  
  `verify_message`  验证信息内容
  
  `usable`  是否已使用，1-已使用，0-未使用
  
  `status`   发送状态，1-已发送，0-未发送
  
  `create_time` 邮件创建时间

` INSERT INTO tb_regist_message VALUES(1,'risoyo@163.com','123456','验证码是【123456】','0','0','2019-04-05 22:49:15');`

# init_data.sql
数据库测试数据初始化
