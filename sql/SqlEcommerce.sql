CREATE DATABASE  IF NOT EXISTS `curriculo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `curriculo`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: curriculo
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
-- Table structure for table `areasinteresse`
--

DROP TABLE IF EXISTS `areasinteresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areasinteresse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areasinteresse`
--

LOCK TABLES `areasinteresse` WRITE;
/*!40000 ALTER TABLE `areasinteresse` DISABLE KEYS */;
INSERT INTO `areasinteresse` VALUES (1,'Administrativa','Administrativa'),(2,'Artes / Música / Teatro / Cultural','Artes / Música / Teatro / Cultural'),(3,'Comercial / Vendas','Comercial / Vendas'),(4,'Comunicação / Publicidade','Comunicação / Publicidade'),(5,'Financeira','Financeira'),(6,'Educação / Ensino','Educação / Ensino'),(7,'Engenharia de Alimentos','Engenharia de Alimentos'),(8,'Engenharia Civil','Engenharia Civil'),(9,'Engenharia Eletrônica / Computação','Engenharia Eletrônica / Computação'),(10,'Engenharia Mecânica / Mecatrônica','Engenharia Mecânica / Mecatrônica'),(11,'Engenharia de Produção','Engenharia de Produção'),(12,'Engenharia de Materiais / Química','Engenharia de Materiais / Química'),(13,'Informática / Internet / T.I.','Informática / Internet / T.I.'),(14,'Jurídica','Jurídica'),(15,'Logística','Logística'),(16,'Marketing','Marketing'),(17,'Recursos Humanos','Recursos Humanos'),(18,'Segurança do Trabalho','Segurança do Trabalho'),(19,'Saúde','Saúde'),(20,'MedicinaOdontologia','MedicinaOdontologia'),(21,'Fisioterapia','Fisioterapia'),(22,'Fonoaudiologia','Fonoaudiologia'),(23,'Enfermagem','Enfermagem'),(24,'Farmácia','Farmácia'),(25,'Psicologia','Psicologia'),(26,'Veterinária','Veterinária'),(27,'Terapia Ocupacional','Terapia Ocupacional'),(28,'Seguros','Seguros'),(29,'Turismo','Turismo'),(30,'Telecomunicações','Telecomunicações'),(31,'Comércio Exterior / Relações Internacionais','Comércio Exterior / Relações Internacionais'),(32,'Telemarketing / Call Center','Telemarketing / Call Center'),(33,'Controle e Sistema de Qualidade','Controle e Sistema de Qualidade'),(34,'Atendimento','Atendimento'),(35,'Química','Química'),(36,'Suprimentos / Compras','Suprimentos / Compras'),(37,'Ciências Agrárias / Agribusiness','Ciências Agrárias / Agribusiness'),(38,'Auditoria','Auditoria'),(39,'Beleza / Estética','Beleza / Estética'),(40,'Meio Ambiente / Biologia','Meio Ambiente / Biologia'),(41,'Hotelaria','Hotelaria'),(42,'Idiomas','Idiomas'),(43,'Assistência Técnica','Assistência Técnica'),(44,'Jornalismo','Jornalismo'),(45,'Moda','Moda'),(46,'Nutrição','Nutrição'),(47,'Segurança Pessoal / Patrimonia','Segurança Pessoal / Patrimonia'),(48,'Esportes','Esportes'),(49,'Bancária / Mercado Financeiro','Bancária / Mercado Financeiro'),(50,'Transportes','Transportes'),(51,'Arquitetura','Arquitetura'),(52,'Gráfica','Gráfica'),(53,'Serviço Social','Serviço Social'),(54,'ConstruçãoCivil','ConstruçãoCivil'),(55,'ONGs / Terceiro Setor','ONGs / Terceiro Setor'),(56,'Biblioteconomia','Biblioteconomia');
/*!40000 ALTER TABLE `areasinteresse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `areasinteressecurriculo`
--

DROP TABLE IF EXISTS `areasinteressecurriculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `areasinteressecurriculo` (
  `idAreasInteresse` int(11) NOT NULL,
  `idCurriculo` int(11) NOT NULL,
  PRIMARY KEY (`idAreasInteresse`,`idCurriculo`),
  KEY `fk_areasInteresse_has_Curriculo_Curriculo1_idx` (`idCurriculo`),
  KEY `fk_areasInteresse_has_Curriculo_areasInteresse1_idx` (`idAreasInteresse`),
  CONSTRAINT `fk_areasInteresse_has_Curriculo_Curriculo1` FOREIGN KEY (`idCurriculo`) REFERENCES `curriculo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_areasInteresse_has_Curriculo_areasInteresse1` FOREIGN KEY (`idAreasInteresse`) REFERENCES `areasinteresse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areasinteressecurriculo`
--

LOCK TABLES `areasinteressecurriculo` WRITE;
/*!40000 ALTER TABLE `areasinteressecurriculo` DISABLE KEYS */;
INSERT INTO `areasinteressecurriculo` VALUES (1,5),(4,5),(8,5),(4,10),(4,11),(7,11);
/*!40000 ALTER TABLE `areasinteressecurriculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculo`
--

DROP TABLE IF EXISTS `curriculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resumo` text,
  `expProfissional` text,
  `idPessoa` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Curriculo_Pessoa1_idx` (`idPessoa`),
  CONSTRAINT `fk_Curriculo_Pessoa1` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculo`
--

LOCK TABLES `curriculo` WRITE;
/*!40000 ALTER TABLE `curriculo` DISABLE KEYS */;
INSERT INTO `curriculo` VALUES (5,NULL,'Mussum ipsum cacilds, vidis litro abertis. Consetis adipiscings elitis. Pra lá , depois divoltis porris, paradis. Paisis, filhis, espiritis santis. Mé faiz elementum girarzis, nisi eros vermeio, in elementis mé pra quem é amistosis quis leo. Manduma pindureta quium dia nois paga. Sapien in monti palavris qui num significa nadis i pareci latim. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.\r\n',10),(10,NULL,'dsdasdasdasdasd',15),(11,NULL,'wdaweqweqwe',16);
/*!40000 ALTER TABLE `curriculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomeRua` varchar(100) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `complemento` varchar(205) DEFAULT NULL,
  `cep` varchar(10) NOT NULL,
  `estado` varchar(10) NOT NULL,
  `idPessoa` int(11) NOT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `municipio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Endereço_Estado1_idx` (`estado`),
  KEY `fk_Endereco_Pessoa1_idx` (`idPessoa`),
  CONSTRAINT `fk_Endereco_Pessoa1` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (7,'Rua Júlia Alexandrina Florindo','31','dasdasdasdasd','88061-423','SC',10,'Barra da Lagoa','Florianópolis'),(11,'Rua Marina Reboa Zanella','20',NULL,'99712-206','RS',15,'Paiol Grande','Erechim'),(12,'Rua Criciúma','12',NULL,'88168-712','SC',16,'Areias de Cima (Guapora','Biguaçu');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_pais` int(11) NOT NULL,
  `sigla` varchar(4) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `codigo_ibge` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,31,'AC','ACRE',12),(2,31,'AL','ALAGOAS',27),(3,31,'AM','AMAZONAS',13),(4,31,'AP','AMAPÁ',16),(5,31,'BA','BAHIA',29),(6,31,'CE','CEARÁ',23),(7,31,'DF','DISTRITO FEDERAL',53),(8,31,'ES','ESPÍRITO SANTO',32),(9,31,'GO','GOIÁS',52),(10,31,'MA','MARANHÃO',21),(11,31,'MG','MINAS GERAIS',31),(12,31,'MS','MATO GROSSO DO SUL',50),(13,31,'MT','MATO GROSSO',51),(14,31,'PA','PARÁ',15),(15,31,'PB','PARAÍBA',25),(16,31,'PE','PERNAMBUCO',26),(17,31,'PI','PIAUÍ',22),(18,31,'PR','PARANÁ',41),(19,31,'RJ','RIO DE JANEIRO',33),(20,31,'RN','RIO GRANDE DO NORTE',24),(21,31,'RO','RONDÔNIA',11),(22,31,'RR','RORAIMA',14),(23,31,'RS','RIO GRANDE DO SUL',43),(24,31,'SC','SANTA CATARINA',42),(25,31,'SE','SERGIPE',28),(26,31,'SP','SÃO PAULO',35),(27,31,'TO','TOCANTINS',17);
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formacao`
--

DROP TABLE IF EXISTS `formacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomeInstituicao` varchar(45) DEFAULT NULL,
  `dataInicio` date DEFAULT NULL,
  `dataTermino` date DEFAULT NULL,
  `idTipo` int(11) NOT NULL,
  `idCurriculo` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Formacao_TipoFormacao_idx` (`idTipo`),
  KEY `fk_Formacao_Curriculo1_idx` (`idCurriculo`),
  CONSTRAINT `fk_Formacao_Curriculo1` FOREIGN KEY (`idCurriculo`) REFERENCES `curriculo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Formacao_TipoFormacao` FOREIGN KEY (`idTipo`) REFERENCES `tipoformacao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formacao`
--

LOCK TABLES `formacao` WRITE;
/*!40000 ALTER TABLE `formacao` DISABLE KEYS */;
INSERT INTO `formacao` VALUES (2,'sdasdasda','2015-10-21','2015-10-28',1,5),(3,'sdasdasda 3','2015-10-21','2015-10-28',1,5),(4,'dasdasdasdas','2015-11-16','2015-11-24',1,10),(5,'weqweqweqwe','2015-11-05','2015-11-11',1,11);
/*!40000 ALTER TABLE `formacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paises` (
  `iso` char(2) NOT NULL,
  `iso3` char(3) NOT NULL,
  `numcode` smallint(6) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`iso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--

LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
INSERT INTO `paises` VALUES ('AD','AND',20,'Andorra'),('AE','ARE',784,'Emiratos Árabes Unidos'),('AF','AFG',4,'Afeganistão'),('AG','ATG',28,'Antigua e Barbuda'),('AI','AIA',660,'Anguilla'),('AL','ALB',8,'Albânia'),('AM','ARM',51,'Arménia'),('AN','ANT',530,'Antilhas Holandesas'),('AO','AGO',24,'Angola'),('AQ','ATA',10,'Antárctida'),('AR','ARG',32,'Argentina'),('AS','ASM',16,'Samoa Americana'),('AT','AUT',40,'Áustria'),('AU','AUS',36,'Austrália'),('AW','ABW',533,'Aruba'),('AX','ALA',248,'Åland, Ilhas'),('AZ','AZE',31,'Azerbeijão'),('BA','BIH',70,'Bósnia-Herzegovina'),('BB','BRB',52,'Barbados'),('BD','BGD',50,'Bangladesh'),('BE','BEL',56,'Bélgica'),('BF','BFA',854,'Burkina Faso'),('BG','BGR',100,'Bulgária'),('BH','BHR',48,'Bahrain'),('BI','BDI',108,'Burundi'),('BJ','BEN',204,'Benin'),('BM','BMU',60,'Bermuda'),('BN','BRN',96,'Brunei'),('BO','BOL',68,'Bolívia'),('BR','BRA',76,'Brasil'),('BS','BHS',44,'Bahamas'),('BT','BTN',64,'Butão'),('BV','BVT',74,'Bouvet, Ilha'),('BW','BWA',72,'Botswana'),('BY','BLR',112,'Bielo-Rússia'),('BZ','BLE',84,'Belize'),('c*','CUB',192,'Cuba'),('CA','CAN',124,'Canadá'),('CC','CCK',166,'Cocos, Ilhas'),('CD','COD',180,'Congo, República Democrática do (antigo Zaire)'),('CF','CAF',140,'Centro-africana, República'),('CG','COG',178,'Congo, República do'),('CH','CHE',756,'Suíça'),('CI','CIV',384,'Costa do Marfim'),('CK','COK',184,'Cook, Ilhas'),('CL','CHL',152,'Chile'),('CM','CMR',120,'Camarões'),('CN','CHN',156,'China'),('CO','COL',170,'Colômbia'),('CR','CRI',188,'Costa Rica'),('CV','CPV',132,'Cabo Verde'),('CX','CXR',162,'Christmas, Ilha'),('CY','CYP',196,'Chipre'),('CZ','CZE',203,'Checa, República'),('DE','DEU',276,'Alemanha'),('DJ','DJI',262,'Djibouti'),('DK','DNK',208,'Dinamarca'),('DM','DMA',212,'Dominica'),('DO','DOM',214,'Dominicana, República'),('DZ','DZA',12,'Argélia'),('EC','ECU',218,'Equador'),('EE','EST',233,'Estónia'),('EG','EGY',818,'Egipto'),('EH','ESH',732,'Saara Ocidental'),('ER','ERI',232,'Eritreia'),('ES','ESP',724,'Espanha'),('ET','ETH',231,'Etiópia'),('FI','FIN',246,'Finlândia'),('FJ','FJI',242,'Fiji'),('FK','FLK',238,'Malvinas, Ilhas (Falkland)'),('FM','FSM',583,'Micronésia, Estados Federados da'),('FO','FRO',234,'Faroe, Ilhas'),('FR','FRA',250,'França'),('GA','GAB',266,'Gabão'),('GB','GBR',826,'Reino Unido da Grã-Bretanha e Irlanda do Norte'),('GD','GRD',308,'Grenada'),('GE','GEO',268,'Geórgia'),('GF','GUF',254,'Guiana Francesa'),('GG','GGY',831,'Guernsey'),('GH','GHA',288,'Gana'),('GI','GIB',292,'Gibraltar'),('GL','GRL',304,'Gronelândia'),('GM','GMB',270,'Gâmbia'),('GN','GIN',324,'Guiné-Conacri'),('GP','GLP',312,'Guadeloupe'),('GQ','GNQ',226,'Guiné Equatorial'),('GR','GRC',300,'Grécia'),('GS','SGS',239,'Geórgia do Sul e Sandwich do Sul, Ilhas'),('GT','GTM',320,'Guatemala'),('GU','GUM',316,'Guam'),('GW','GNB',624,'Guiné-Bissau'),('GY','GUY',328,'Guiana'),('HK','HKG',344,'Hong Kong'),('HM','HMD',334,'Heard e Ilhas McDonald, Ilha'),('HN','HND',340,'Honduras'),('HR','HRV',191,'Croácia'),('HT','HTI',332,'Haiti'),('HU','HUN',348,'Hungria'),('ID','IDN',360,'Indonésia'),('IE','IRL',372,'Irlanda'),('IL','ISR',376,'Israel'),('IM','IMN',833,'Man, Ilha de'),('IN','IND',356,'Índia'),('IO','IOT',86,'Território Britânico do Oceano Índico'),('IQ','IRQ',368,'Iraque'),('IR','IRN',364,'Irão'),('IS','ISL',352,'Islândia'),('IT','ITA',380,'Itália'),('JE','JEY',832,'Jersey'),('JM','JAM',388,'Jamaica'),('JO','JOR',400,'Jordânia'),('JP','JPN',392,'Japão'),('KE','KEN',404,'Quénia'),('KG','KGZ',417,'Quirguistão'),('KH','KHM',116,'Cambodja'),('KI','KIR',296,'Kiribati'),('KM','COM',174,'Comores'),('KN','KNA',659,'São Cristóvão e Névis (Saint Kitts e Nevis)'),('KP','PRK',408,'Coreia, República Democrática da (Coreia do Norte)'),('KR','KOR',410,'Coreia do Sul'),('KW','KWT',414,'Kuwait'),('KY','CYM',136,'Cayman, Ilhas'),('KZ','KAZ',398,'Cazaquistão'),('LA','LAO',418,'Laos'),('LB','LBN',422,'Líbano'),('LC','LCA',662,'Santa Lúcia'),('LI','LIE',438,'Liechtenstein'),('LK','LKA',144,'Sri Lanka'),('LR','LBR',430,'Libéria'),('LS','LSO',426,'Lesoto'),('LT','LTU',440,'Lituânia'),('LU','LUX',442,'Luxemburgo'),('LV','LVA',428,'Letónia'),('LY','LBY',434,'Líbia'),('MA','MAR',504,'Marrocos'),('MC','MCO',492,'Mónaco'),('MD','MDA',498,'Moldávia'),('ME','MNE',499,'Montenegro'),('MG','MDG',450,'Madagáscar'),('MH','MHL',584,'Marshall, Ilhas'),('MK','MKD',807,'Macedónia, República da'),('ML','MLI',466,'Mali'),('MM','MMR',104,'Myanmar (antiga Birmânia)'),('MN','MNG',496,'Mongólia'),('MO','MAC',446,'Macau'),('MP','MNP',580,'Marianas Setentrionais'),('MQ','MTQ',474,'Martinica'),('MR','MRT',478,'Mauritânia'),('MS','MSR',500,'Montserrat'),('MT','MLT',470,'Malta'),('MU','MUS',480,'Maurícia'),('MV','MDV',462,'Maldivas'),('MW','MWI',454,'Malawi'),('MX','MEX',484,'México'),('MY','MYS',458,'Malásia'),('MZ','MOZ',508,'Moçambique'),('NA','NAM',516,'Namíbia'),('NC','NCL',540,'Nova Caledónia'),('NE','NER',562,'Níger'),('NF','NFK',574,'Norfolk, Ilha'),('NG','NGA',566,'Nigéria'),('NI','NIC',558,'Nicarágua'),('NL','NLD',528,'Países Baixos (Holanda)'),('NO','NOR',578,'Noruega'),('NP','NPL',524,'Nepal'),('NR','NRU',520,'Nauru'),('NU','NIU',570,'Niue'),('NZ','NZL',554,'Nova Zelândia (Aotearoa)'),('OM','OMN',512,'Oman'),('PA','PAN',591,'Panamá'),('PE','PER',604,'Peru'),('PF','PYF',258,'Polinésia Francesa'),('PG','PNG',598,'Papua-Nova Guiné'),('PH','PHL',608,'Filipinas'),('PK','PAK',586,'Paquistão'),('PL','POL',616,'Polónia'),('PM','SPM',666,'Saint Pierre et Miquelon'),('PN','PCN',612,'Pitcairn'),('PR','PRI',630,'Porto Rico'),('PS','PSE',275,'Palestina'),('PT','PRT',620,'Portugal'),('PW','PLW',585,'Palau'),('PY','PRY',600,'Paraguai'),('QA','QAT',634,'Qatar'),('RE','REU',638,'Reunião'),('RO','ROU',642,'Roménia'),('RS','SRB',688,'Sérvia'),('RU','RUS',643,'Rússia'),('RW','RWA',646,'Ruanda'),('SA','SAU',682,'Arábia Saudita'),('SB','SLB',90,'Salomão, Ilhas'),('SC','SYC',690,'Seychelles'),('SD','SDN',736,'Sudão'),('SE','SWE',752,'Suécia'),('SG','SGP',702,'Singapura'),('SH','SHN',654,'Santa Helena'),('SI','SVN',705,'Eslovénia'),('SJ','SJM',744,'Svalbard e Jan Mayen'),('SK','SVK',703,'Eslováquia'),('SL','SLE',694,'Serra Leoa'),('SM','SMR',674,'San Marino'),('SN','SEN',686,'Senegal'),('SO','SOM',706,'Somália'),('SR','SUR',740,'Suriname'),('ST','STP',678,'São Tomé e Príncipe'),('SV','SLV',222,'El Salvador'),('SY','SYR',760,'Síria'),('SZ','SWZ',748,'Suazilândia'),('TC','TCA',796,'Turks e Caicos'),('TD','TCD',148,'Chade'),('TF','ATF',260,'Terras Austrais e Antárticas Francesas (TAAF)'),('TG','TGO',768,'Togo'),('TH','THA',764,'Tailândia'),('TJ','TJK',762,'Tajiquistão'),('TK','TKL',772,'Toquelau'),('TL','TLS',626,'Timor-Leste'),('TM','TKM',795,'Turquemenistão'),('TN','TUN',788,'Tunísia'),('TO','TON',776,'Tonga'),('TR','TUR',792,'Turquia'),('TT','TTO',780,'Trindade e Tobago'),('TV','TUV',798,'Tuvalu'),('TW','TWN',158,'Taiwan'),('TZ','TZA',834,'Tanzânia'),('UA','UKR',804,'Ucrânia'),('UG','UGA',800,'Uganda'),('UM','UMI',581,'Menores Distantes dos Estados Unidos, Ilhas'),('US','USA',840,'Estados Unidos da América'),('UY','URY',858,'Uruguai'),('UZ','UZB',860,'Usbequistão'),('VA','VAT',336,'Vaticano'),('VC','VCT',670,'São Vicente e Granadinas'),('VE','VEN',862,'Venezuela'),('VG','VGB',92,'Virgens Britânicas, Ilhas'),('VI','VIR',850,'Virgens Americanas, Ilhas'),('VN','VNM',704,'Vietname'),('VU','VUT',548,'Vanuatu'),('WF','WLF',876,'Wallis e Futuna'),('WS','WSM',882,'Samoa (Samoa Ocidental)'),('YE','YEM',887,'Iémen'),('YT','MYT',175,'Mayotte'),('ZA','ZAF',710,'África do Sul'),('ZM','ZMB',894,'Zâmbia'),('ZW','ZWE',716,'Zimbabwe');
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `sobreNome` varchar(30) DEFAULT NULL,
  `idade` int(11) NOT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `cpf` varchar(45) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pessoa_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_pessoa_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Gustavo Alterado','Agostinho',22,'M','000.000.000-00',0),(10,'Gustavo ','Agostinho ',21,'masculino','asdasd',1),(11,'carralho','ficho da put',4454,'masculino','5454',2),(12,'barra 2','barra3',10,'masculino','123123',2),(14,'dasdas','asdasd',11,'masculino','3232',2),(15,'asdasd','asdasda',3,'masculino','454',2),(16,'Sandoval','Junior',12,'masculino','222.222.222-44',4);
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoformacao`
--

DROP TABLE IF EXISTS `tipoformacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoformacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoformacao`
--

LOCK TABLES `tipoformacao` WRITE;
/*!40000 ALTER TABLE `tipoformacao` DISABLE KEYS */;
INSERT INTO `tipoformacao` VALUES (1,'Doutorado Completo'),(2,'Doutorado Incompleto'),(3,'Mestrado Completo'),(4,'Mestrado Incompleto'),(5,'Pós-graduação Completa'),(6,'Pós-graduação Incompleta'),(7,'Superior Completo'),(8,'Superior Incompleto'),(9,'Ensino Médio Técnico (2º Grau) Completo'),(10,'Ensino Médio (2º Grau) Completo'),(11,'Ensino Médio Técnico (2º Grau) Incompleto'),(12,'Ensino Fundamental (1º Grau) Completo'),(13,'Ensino Fundamental (1º Grau) Incompleto'),(14,'Não Alfabetizado');
/*!40000 ALTER TABLE `tipoformacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipopublicados`
--

DROP TABLE IF EXISTS `tipopublicados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipopublicados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipopublicados`
--

LOCK TABLES `tipopublicados` WRITE;
/*!40000 ALTER TABLE `tipopublicados` DISABLE KEYS */;
INSERT INTO `tipopublicados` VALUES (1,'Artigo'),(2,'Fichamento'),(3,'Paper ou Short Paper'),(4,'Monografia'),(5,'Resenha'),(6,'Resumo');
/*!40000 ALTER TABLE `tipopublicados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `titulopublicados`
--

DROP TABLE IF EXISTS `titulopublicados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `titulopublicados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `ano` int(11) DEFAULT NULL,
  `pais` varchar(45) DEFAULT NULL,
  `idTipoPublicados` int(11) NOT NULL,
  `idCurriculo` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tbPublicados_TipoPublicados1_idx` (`idTipoPublicados`),
  KEY `fk_tbPublicados_Curriculo1_idx` (`idCurriculo`),
  CONSTRAINT `fk_tbPublicados_Curriculo1` FOREIGN KEY (`idCurriculo`) REFERENCES `curriculo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbPublicados_TipoPublicados1` FOREIGN KEY (`idTipoPublicados`) REFERENCES `tipopublicados` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titulopublicados`
--

LOCK TABLES `titulopublicados` WRITE;
/*!40000 ALTER TABLE `titulopublicados` DISABLE KEYS */;
INSERT INTO `titulopublicados` VALUES (4,'dasdasdasd',21,'Antigua e Barbuda',1,5),(5,'dasdasdasd 33',21,'Arménia',1,5),(9,'dasdasd',555,'Antigua e Barbuda',1,10),(10,'qweqweqwe',222,'Anguilla',1,11);
/*!40000 ALTER TABLE `titulopublicados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `enable` tinyint(1) DEFAULT '1',
  `perfil` varchar(15) DEFAULT 'ROLE_USER',
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','202cb962ac59075b964b07152d234b70','Gustavo Agostinho',1,'ROLE_USER','gustavo.agostinho0@gmail.com'),(2,'barra','97c1fad8d8d23747d6184693dffab860','gustavo2',1,'ROLE_USER','gustavo@gmail.com'),(3,'gustavo','4c96f8324e3ba54a99e78249b95daa30','userTeste',1,'ROLE_USER','gustavo.agostinho0@gmail.com'),(4,'123','202cb962ac59075b964b07152d234b70','Sandoval',1,'ROLE_USER','sansa@gmail.com'),(5,'novo','42323e3211ed4478b2b8ba87d4185a03','novo',1,'ROLE_USER','gustabo@gma.comm');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-14 10:08:12
