#悲观锁
CREATE TABLE `price` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `total` decimal(12,2) DEFAULT '0.00' COMMENT '总值',
  `front` decimal(12,2) DEFAULT '0.00' COMMENT '消费前',
  `end` decimal(12,2) DEFAULT '0.00' COMMENT '消费后',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#乐观锁
CREATE TABLE `price_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `total` decimal(12,2) DEFAULT '0.00' COMMENT '总值',
  `front` decimal(12,2) DEFAULT '0.00' COMMENT '消费前',
  `end` decimal(12,2) DEFAULT '0.00' COMMENT '消费后',
  `version` int(11) DEFAULT '0' COMMENT '并发版本控制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;