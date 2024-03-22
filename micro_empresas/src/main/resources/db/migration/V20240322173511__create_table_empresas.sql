CREATE TABLE IF NOT EXISTS `empresas`(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `nome` varchar(200) NOT NULL CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    PRIMARY KEY(`id`)
) COMMENT='Tabela para armazenar dados de empresas abertas na bolsa de valores';

