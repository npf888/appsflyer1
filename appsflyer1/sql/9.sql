#两个 目录菜单   订单系统，实时在线人数 ，下边是 日志表的修改

alter table  texas_log.player_keep_log_2017_09_07 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_08 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_09 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_10 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_11 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_12 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_13 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_14 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_15 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_16 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_17 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_18 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_19 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_20 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_21 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_22 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_23 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_24 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;
alter table  texas_log.player_keep_log_2017_09_25 add column  channel_type int(11) DEFAULT NULL, add column device_mode varchar(256) DEFAULT NULL,  add column client_version varchar(256) DEFAULT NULL;




update texas_log.player_keep_log_2017_09_07 t1 left join texas_log.player_login_log_2017_09_07 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_08 t1 left join texas_log.player_login_log_2017_09_08 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_09 t1 left join texas_log.player_login_log_2017_09_09 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_10 t1 left join texas_log.player_login_log_2017_09_10 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_11 t1 left join texas_log.player_login_log_2017_09_11 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_12 t1 left join texas_log.player_login_log_2017_09_12 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_13 t1 left join texas_log.player_login_log_2017_09_13 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_14 t1 left join texas_log.player_login_log_2017_09_14 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_15 t1 left join texas_log.player_login_log_2017_09_15 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_16 t1 left join texas_log.player_login_log_2017_09_16 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_17 t1 left join texas_log.player_login_log_2017_09_17 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_18 t1 left join texas_log.player_login_log_2017_09_18 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_19 t1 left join texas_log.player_login_log_2017_09_19 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_20 t1 left join texas_log.player_login_log_2017_09_20 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_21 t1 left join texas_log.player_login_log_2017_09_21 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_22 t1 left join texas_log.player_login_log_2017_09_22 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_23 t1 left join texas_log.player_login_log_2017_09_23 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_24 t1 left join texas_log.player_login_log_2017_09_24 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
update texas_log.player_keep_log_2017_09_25 t1 left join texas_log.player_login_log_2017_09_25 t2 on t1.account_id=t2.account_id set t1.channel_type=t2.channel_type,t1.device_mode=t2.device_mode,t1.client_version=t2.client_version;
