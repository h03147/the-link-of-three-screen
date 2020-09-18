DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(32) NOT NULL COMMENT '用户名称',
  `birthday` datetime default NULL COMMENT '生日',
  `sex` char(1) default NULL COMMENT '性别',
  `address` varchar(256) default NULL COMMENT '地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert  into `user`(`id`,`username`,`birthday`,`sex`,`address`) values (41,'老王','2018-02-27 17:47:08','男','北京'),(42,'小二王','2018-03-02 15:09:37','女','北京金燕龙'),(43,'小二王','2018-03-04 11:34:34','女','北京金燕龙'),(45,'传智播客','2018-03-04 12:04:06','男','北京金燕龙'),(46,'老王','2018-03-07 17:37:26','男','北京'),(48,'小马宝莉','2018-03-08 11:44:00','女','北京修正');





DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `ID` int(11) NOT NULL COMMENT '编号',
  `UID` int(11) default NULL COMMENT '用户编号',
  `MONEY` double default NULL COMMENT '金额',
  PRIMARY KEY  (`ID`),
  KEY `FK_Reference_8` (`UID`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`UID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert  into `account`(`ID`,`UID`,`MONEY`) values (1,41,1000),(2,45,1000),(3,41,2000);



DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `ID` int(11) NOT NULL COMMENT '编号',
  `ROLE_NAME` varchar(30) default NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(60) default NULL COMMENT '角色描述',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert  into `role`(`ID`,`ROLE_NAME`,`ROLE_DESC`) values (1,'院长','管理整个学院'),(2,'总裁','管理整个公司'),(3,'校长','管理整个学校');





DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `UID` int(11) NOT NULL COMMENT '用户编号',
  `RID` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY  (`UID`,`RID`),
  KEY `FK_Reference_10` (`RID`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`RID`) REFERENCES `role` (`ID`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`UID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `user_role`(`UID`,`RID`) values (41,1),(45,1),(41,2);

DROP TABLE IF EXISTS `upload`;

CREATE TABLE `upload` (
  `id` int(11) NOT NULL auto_increment,
  `url` varchar(600) default NULL COMMENT 'url',
  `name` varchar(600) default NULL COMMENT '名字',
  `lujing` varchar(600) default NULL COMMENT '物理路径',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `thelinkofthreescreenuploadbtn1`;

CREATE TABLE `thelinkofthreescreenuploadbtn1` (
  `id` int(11) NOT NULL auto_increment,
  `secondmenu` varchar(32) default NULL COMMENT '二级按钮',
  `thirdmenu` varchar(32) default NULL COMMENT '三级按钮',
  `fourthmenu` varchar(32) default NULL COMMENT '四级按钮',
  `fifthmenu` varchar(32) default NULL COMMENT '五级按钮',
  `leftscreenminiimg` varchar(600) default NULL COMMENT '左屏缩略图',
  `leftscreencontext` varchar(600) default NULL COMMENT '左屏文字',
  `rightscreenimg` varchar(600) default NULL COMMENT '右屏图片',
  `rightscreenvideo` varchar(600) default NULL COMMENT '右屏视频',
  `rightscreencontext` text COMMENT '右屏文字',
  `lujing` varchar(600) default NULL COMMENT '物理路径',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `thelinkofthreescreenuploadbtn2`;

CREATE TABLE `thelinkofthreescreenuploadbtn2` (
  `id` int(11) NOT NULL auto_increment,
  `secondmenu` varchar(32) default NULL COMMENT '二级菜单',
  `leftscreenurl` varchar(300) default NULL COMMENT '左屏网址',
  `rightscreenimg` varchar(600) default NULL COMMENT '右屏图片',
  `rightscreencontext` text COMMENT '右屏文字',
  `lujing` varchar(600) default NULL COMMENT '物理路径',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `thelinkofthreescreen`.`thelinkofthreescreenuploadbtn2`(`id`, `secondmenu`, `leftscreenurl`, `rightscreenimg`, `rightscreencontext`, `lujing`) VALUES (1, '新时代智库', 'http://www.dlcmkj.com', 'http://192.168.31.100:8080/btn1files/AVsoqfAzIDQsC7i0Kb0SeUExiwb2148waW2vRokDqcRNSw8aNFru.jpg', 'http://www.dlcmkj.com', 'F:\\btn1fileUpload\\\\files\\AVsoqfAzIDQsC7i0Kb0SeUExiwb2148waW2vRokDqcRNSw8aNFru.jpg');
INSERT INTO `thelinkofthreescreen`.`thelinkofthreescreenuploadbtn2`(`id`, `secondmenu`, `leftscreenurl`, `rightscreenimg`, `rightscreencontext`, `lujing`) VALUES (2, '不忘初心牢记使命', 'http://www.dlcmkj.com', 'http://192.168.31.100:8080/btn1files/AVmPhfJDST.u.oy3ssVSOgbX6s6yQ0TOyeRrU9RJEjHA5.pFtBhu.png', '右屏内容', 'F:\\btn1fileUpload\\\\files\\AVmPhfJDST.u.oy3ssVSOgbX6s6yQ0TOyeRrU9RJEjHA5.pFtBhu.png');
INSERT INTO `thelinkofthreescreen`.`thelinkofthreescreenuploadbtn2`(`id`, `secondmenu`, `leftscreenurl`, `rightscreenimg`, `rightscreencontext`, `lujing`) VALUES (3, '党建数据库', 'http://www.dlcmkj.com', 'http://192.168.31.100:8080/btn1files/AV5eBizSJ.K1CcbBlbouOhLZl6vYAvwDryTSuAnfBTYqkO0ZJ19..png', '右屏文字', 'F:\\btn1fileUpload\\\\files\\AV5eBizSJ.K1CcbBlbouOhLZl6vYAvwDryTSuAnfBTYqkO0ZJ19..png');
INSERT INTO `thelinkofthreescreen`.`thelinkofthreescreenuploadbtn2`(`id`, `secondmenu`, `leftscreenurl`, `rightscreenimg`, `rightscreencontext`, `lujing`) VALUES (4, '图解新闻', 'http://www.dlcmkj.com', 'http://192.168.31.100:8080/btn1files/AVQWbMaSiX2jc1LwqJP.OMSBxynSprnsNNALqtO3RDqdLtNRUBQ6.png', '右屏文字', 'F:\\btn1fileUpload\\\\files\\AVQWbMaSiX2jc1LwqJP.OMSBxynSprnsNNALqtO3RDqdLtNRUBQ6.png');



DROP TABLE IF EXISTS `thelinkofthreescreenuploadbtn3`;

CREATE TABLE `thelinkofthreescreenuploadbtn3` (
  `id` int(11) NOT NULL auto_increment,
  `secondmenu` varchar(32) default NULL COMMENT '二级按钮',
  `leftscreenminiimg` varchar(600) default NULL COMMENT '左屏缩略图',
  `leftscreenvideo` varchar(600) default NULL COMMENT '左屏视频',
  `leftscreencontext` varchar(300) default NULL COMMENT '左屏文字',
  `rightscreenurl` varchar(300) default NULL COMMENT '右屏网址',
  `lujing` varchar(600) default NULL COMMENT '物理路径',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

