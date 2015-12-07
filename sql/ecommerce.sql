-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	5.6.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carrinho`
--

DROP TABLE IF EXISTS `carrinho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carrinho` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_carrinho` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrinho`
--

LOCK TABLES `carrinho` WRITE;
/*!40000 ALTER TABLE `carrinho` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrinho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriaproduto`
--

DROP TABLE IF EXISTS `categoriaproduto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoriaproduto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriaproduto`
--

LOCK TABLES `categoriaproduto` WRITE;
/*!40000 ALTER TABLE `categoriaproduto` DISABLE KEYS */;
INSERT INTO `categoriaproduto` VALUES (1,'ctg1','categoria',''),(2,'ctg2','categoria 2',''),(3,'eweqwe','weqweqwe','\0'),(4,'Alterado','sdasdasd','');
/*!40000 ALTER TABLE `categoriaproduto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contato`
--

DROP TABLE IF EXISTS `contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contato` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `assunto` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `descricao` text,
  `protocolo_venda` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contato`
--

LOCK TABLES `contato` WRITE;
/*!40000 ALTER TABLE `contato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desconto`
--

DROP TABLE IF EXISTS `desconto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `desconto` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `faixa_valor` float DEFAULT NULL,
  `desconto` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desconto`
--

LOCK TABLES `desconto` WRITE;
/*!40000 ALTER TABLE `desconto` DISABLE KEYS */;
/*!40000 ALTER TABLE `desconto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `rua` varchar(45) NOT NULL,
  `numero` int(11) NOT NULL,
  `complemento` varchar(100) NOT NULL,
  `bairro` varchar(45) NOT NULL,
  `cidade` varchar(45) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `idPessoa` int(11) NOT NULL,
  `estado` varchar(2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_endereco_pessoa1_idx` (`idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'rua',22,'123123eawawdasdasd','bairro','cidade','12312-312',8,'es');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotosproduto`
--

DROP TABLE IF EXISTS `fotosproduto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fotosproduto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `caminho` varchar(150) NOT NULL,
  `tipo` varchar(10) NOT NULL,
  `tamanho` int(11) NOT NULL,
  `idProduto` int(11) NOT NULL,
  `principal` bit(1) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fotoProduto_produtp_idx` (`idProduto`),
  CONSTRAINT `fotoProduto_produtp` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotosproduto`
--

LOCK TABLES `fotosproduto` WRITE;
/*!40000 ALTER TABLE `fotosproduto` DISABLE KEYS */;
INSERT INTO `fotosproduto` VALUES (20,'1332437623_Save.png','C:\\Users\\Gustavo\\Documents\\Programação\\JAVA\\JAVA_WEB\\E-commerce-SVN\\build\\web\\imagensProdutos\\1332437623_Save.png','.png',8529,18,''),(21,'cash_register_91371.jpg','C:\\Users\\Gustavo\\Documents\\Programação\\JAVA\\JAVA_WEB\\E-commerce-SVN\\build\\web\\imagensProdutos\\cash_register_91371.jpg','.jpg',12194,19,''),(22,'custom_reports_98549.jpg','C:\\Users\\Gustavo\\Documents\\Programação\\JAVA\\JAVA_WEB\\E-commerce-SVN\\build\\web\\imagensProdutos\\custom_reports_98549.jpg','.jpg',8198,20,''),(23,'edit.png','C:\\Users\\Gustavo\\Documents\\Programação\\JAVA\\JAVA_WEB\\E-commerce-SVN\\build\\web\\imagensProdutos\\edit.png','.png',66081,21,''),(24,'misc_edit_98007.jpg','C:\\Users\\Gustavo\\Documents\\Programação\\JAVA\\JAVA_WEB\\E-commerce-SVN\\build\\web\\imagensProdutos\\misc_edit_98007.jpg','.jpg',23147,22,'');
/*!40000 ALTER TABLE `fotosproduto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marca` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `ativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` VALUES (1,'marca 1','marca 1',1),(2,'marca 2','marca 2',1),(3,'marca teste Cad','marca teste Cad',1),(4,'tesndo','adasdasd',0),(5,'dasdas','asdasd',1),(6,'sdawdad','asdasd',1),(7,'Aletrado','dasdasd',1),(8,'Aletrado','dasdasd',0),(9,'Aletrado','dasdasd',0);
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `idProduto` int(11) NOT NULL,
  `idVenda` int(11) NOT NULL,
  PRIMARY KEY (`idProduto`,`idVenda`),
  KEY `fk_produto_has_venda_venda1_idx` (`idVenda`),
  KEY `fk_produto_has_venda_produto1_idx` (`idProduto`),
  CONSTRAINT `fk_produto_has_venda_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_has_venda_venda` FOREIGN KEY (`idVenda`) REFERENCES `venda` (`coidgo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cpfCnpj` varchar(14) NOT NULL,
  `sexo` char(1) NOT NULL,
  `dataNascimento` date DEFAULT NULL,
  `dddRes` int(2) NOT NULL,
  `telRes` int(9) NOT NULL,
  `dddCel` int(2) DEFAULT NULL,
  `telCel` int(9) DEFAULT NULL,
  `dataCadastro` datetime DEFAULT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_pessoa_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_pessoa_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (8,'sdasdasdas','231.231.231-23','M','2015-11-20',22,23232323,0,0,'2015-11-28 00:00:00',11);
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  `valorCompra` float DEFAULT NULL,
  `valorVenda` float NOT NULL,
  `acessos` int(11) DEFAULT NULL,
  `dataCadastro` datetime DEFAULT NULL,
  `idCategoriaProduto` int(11) NOT NULL,
  `idMarca` int(11) NOT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`codigo`),
  KEY `fk_produto_categoria_produto1_idx` (`idCategoriaProduto`),
  KEY `fk_produto_marca1_idx` (`idMarca`),
  CONSTRAINT `fk_produto_categoria_produto` FOREIGN KEY (`idCategoriaProduto`) REFERENCES `categoriaproduto` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_marca` FOREIGN KEY (`idMarca`) REFERENCES `marca` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (6,'produto1','produto 1',1,12,14,NULL,'2015-11-24 00:00:00',2,1,'\0'),(7,'produto 2','produto2',1,12,14,NULL,'2015-11-24 00:00:00',2,1,'\0'),(18,'prod teste img 1','ewweqewq',3,33,33,NULL,'2015-11-24 00:00:00',1,1,''),(19,'prod teste img 2','ewweqewq',3,33,33,NULL,'2015-11-24 00:00:00',1,1,''),(20,'prod teste img 3','ewweqewq',3,33,33,NULL,'2015-11-24 00:00:00',1,1,''),(21,'prod teste img 4','ewweqewq',3,33,33,NULL,'2015-11-24 00:00:00',1,1,''),(22,'adasd','wqweqwe',12312,2,3,NULL,'2015-11-26 00:00:00',1,1,''),(23,'Aletrado dinovo','wqweqwe',12312,2,3,NULL,'2015-11-26 00:00:00',1,1,'\0');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipousuario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` text,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousuario`
--

LOCK TABLES `tipousuario` WRITE;
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(150) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `ultimoAcesso` date DEFAULT NULL,
  `tipoUsuario` varchar(30) NOT NULL,
  `ativo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_usuario_tipo_user_idx` (`tipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (11,'email','senha',NULL,'admin','');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venda` (
  `coidgo` int(11) NOT NULL AUTO_INCREMENT,
  `protocolo` varchar(45) NOT NULL,
  `dataVenda` datetime NOT NULL,
  `valorTotal` double NOT NULL,
  `idPessoa` int(11) NOT NULL,
  `idStatus` int(11) NOT NULL,
  PRIMARY KEY (`coidgo`),
  KEY `fk_venda_pessoa1_idx` (`idPessoa`),
  KEY `fk_venda_status` (`idStatus`),
  CONSTRAINT `fk_venda_pessoa` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_status` FOREIGN KEY (`idStatus`) REFERENCES `status` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-04  8:38:18
