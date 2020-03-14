use kitetsu;
-- ########################## GUIDE START ########################
-- # 函数文档 https://dev.mysql.com/doc/refman/8.0/en/func-op-summary-ref.html
-- ########################## GUIDE END ##########################
drop table if exists `user`;
create table if not exists `user`(
  id bigint(20) unsigned not null auto_increment comment '记录id',
  `name` varchar(50) comment '姓名',
  `birthday` varchar(10) comment '生日',
  `gender` tinyint comment '性别 0-男 1女 2-保密',
  create_time      datetime         default current_timestamp comment '创建时间',
  update_time      datetime comment '更新时间',
  primary key (id)
)engine = InnoDB default CHARACTER SET utf8;
create index idx_name on `user`(`name`);
commit;
