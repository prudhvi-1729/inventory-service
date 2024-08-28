CREATE TABLE `t_inventory` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `sku_code` VARCHAR(255) DEFAULT NULL,
    `quantity` int(11) DEFAULT NULL,
     PRIMARY KEY (`id`)
)