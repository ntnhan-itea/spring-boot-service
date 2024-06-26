-- mySqlSpringBootDb.T_SYS_ROLE definition

CREATE TABLE `T_SYS_ROLE` (
  `id` varchar(255) NOT NULL,
  `role_name` enum('ROLE_ADMIN','ROLE_SUPER_ADMIN','ROLE_USER') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- mySqlSpringBootDb.T_SYS_USER definition

CREATE TABLE `T_SYS_USER` (
  `is_enable` bit(1) NOT NULL,
  `id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe4wtq2bms77xijcoqlj4wclkj` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- mySqlSpringBootDb.T_USER_ROLES definition

CREATE TABLE `T_USER_ROLES` (
  `role_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FKjoihtkgxejcasj0hxw2c7oixf` (`user_id`),
  CONSTRAINT `FK41etae6qt2vunqmodns79cai6` FOREIGN KEY (`role_id`) REFERENCES `T_SYS_ROLE` (`id`),
  CONSTRAINT `FKjoihtkgxejcasj0hxw2c7oixf` FOREIGN KEY (`user_id`) REFERENCES `T_SYS_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;