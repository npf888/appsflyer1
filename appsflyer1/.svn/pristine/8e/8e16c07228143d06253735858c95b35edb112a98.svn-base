#在texas_activity 库中
CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(32) DEFAULT NULL COMMENT '国家编号',
  `langDesc` varchar(32) DEFAULT NULL COMMENT '国家全名称',
  `icon` varchar(32) DEFAULT NULL,
  `nameId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#在texas库中
alter table t_conversioncode add column codeType int(1) comment '0：通用型-每人可用一次；1：特殊型-只能给一个人用';

#在postGre中 左侧的菜单目录
INSERT INTO sys_admin_texas.pre_common_nav(
            id, sid, pid, title, url, target, ntype, displayorder)
    VALUES ('', '106013', '106','兑换码','code.do?method=queryCode', '0', '0', '0');

INSERT INTO sys_admin_texas.pre_common_nav(
            id, sid, pid, title, url, target, ntype, displayorder)
    VALUES ('7456', '100017', '100','玩家留存(国家)','log.do?method=retainedCountry', '0', '0', '0');
