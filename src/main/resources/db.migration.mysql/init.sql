-- 数据库初始化脚本
use mysql;
-- 创建数据库
create database if not exists kitetsu default charset utf8 collate utf8_general_ci;
-- 创建用户
-- 当前版本不允许创建账户和赋予权限一起，所以分开执行
create user kitetsu@localhost identified by '123456';
grant all privileges on kitetsu.* to kitetsu@localhost;
create user kitetsu@'%' identified by '123456';
grant all privileges on kitetsu.* to kitetsu@'%';

