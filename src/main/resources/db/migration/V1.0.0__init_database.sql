-- mySqlSpringBootDb.sys_user_role definition

CREATE TABLE `sys_user_role` (
  `id` varchar(255) NOT NULL,
  `role_name` enum('ROLE_ADMIN','ROLE_SUPER_ADMIN','ROLE_USER') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- mySqlSpringBootDb.`system_user` definition

CREATE TABLE `system_user` (
  `is_enable` bit(1) NOT NULL,
  `id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK74y7xiqrvp39wycn0ron4xq4h` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- mySqlSpringBootDb.user_roles definition

CREATE TABLE `user_roles` (
  `role_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK5ela46ht5rxjavsyaih4rxsc5` (`user_id`),
  CONSTRAINT `FK5ela46ht5rxjavsyaih4rxsc5` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`id`),
  CONSTRAINT `FKmxl0g0hwa479k9tq0d3osr7k5` FOREIGN KEY (`role_id`) REFERENCES `sys_user_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;