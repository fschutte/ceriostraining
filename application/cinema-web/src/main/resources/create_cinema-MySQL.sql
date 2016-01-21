DROP TABLE IF EXISTS `filmagenda`;
CREATE TABLE `filmagenda` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`titel` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`datum` DATE NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB;


INSERT INTO `filmagenda`
  (`datum`, `titel`)
VALUES
  ('2016-07-01', 'The Legend of Tarzan (2016)'),
  ('2016-02-06', 'Tarzan the Ape Man (1932)'),
  ('2016-02-13', 'Tarzan (1999)');


SELECT * FROM `filmagenda`;