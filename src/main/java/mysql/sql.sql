SELECT ������ FROM ������
SELECT * FROM ������
SELECT DISTINCT ������ FROM ������
SELECT ������ FROM ������ WHERE �� ����� ֵ
SELECT ������ FROM ������ WHERE �� ����� ֵ AND �� ����� ֵ
SELECT ������ FROM ������ ORDER BY ������ DESC/ASC
SELECT Company, OrderNumber FROM Orders ORDER BY Company DESC, OrderNumber ASC
INSERT INTO ������ VALUES (ֵ1, ֵ2,....)
INSERT INTO table_name (��1, ��2,...) VALUES (ֵ1, ֵ2,....)
UPDATE ������ SET ������ = ��ֵ WHERE ������ = ĳֵ
DELETE FROM ������ WHERE ������ = ֵ
SELECT TOP number|percent column_name(s) FROM ������
SELECT column_name(s) FROM ������ LIMIT ��ֵ
SELECT column_name(s) FROM ������ WHERE ROWNUM <= ��ֵ
SELECT column_name(s) FROM ������ WHERE column_name LIKE pattern
SELECT column_name(s) FROM ������ WHERE column_name IN (value1,value2,...)
SELECT column_name(s) FROM ������ WHERE column_name BETWEEN value1 AND value2
SELECT column_name(s) FROM ������ AS alias_name
SELECT column_name AS alias_name FROM ������
SELECT ������1.������1, ������2.������2, ...
FROM ������1
INNER JOIN ������2
ON ������1.�������� = ������2.��������
SELECT ������1.������1, ������2.������2, ...
FROM ������1, ������2
WHERE ������1.�������� = ������2.��������
SELECT column_name(s) 
FROM ������1
INNER JOIN ������2
ON ������1.column_name=������2.column_name
SELECT column_name(s)
FROM table_name1
LEFT JOIN table_name2 
ON table_name1.column_name=table_name2.column_name
SELECT column_name(s)
FROM table_name1
RIGHT JOIN table_name2 
ON table_name1.column_name=table_name2.column_name
SELECT column_name(s)
FROM table_name1
FULL JOIN table_name2 
ON table_name1.column_name=table_name2.column_name
SELECT column_name(s) FROM table_name1
UNION [All]
SELECT column_name(s) FROM table_name2
SELECT *
INTO new_table_name [IN externaldatabase] 
FROM old_tablename
SELECT column_name(s)
INTO new_table_name [IN externaldatabase] 
FROM old_tablename
CREATE DATABASE database_name
CREATE TABLE ������
(
	������1 ��������,
	������2 ��������,
	������3 ��������,
	....
)
CREATE TABLE Persons
(
	Id_P int NOT NULL,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255)
)
CREATE TABLE Persons
(
	Id_P int NOT NULL,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255),
	UNIQUE (Id_P)
)
CREATE TABLE Persons
(
	Id_P int NOT NULL UNIQUE,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255)
)
CREATE TABLE Persons
(
	Id_P int NOT NULL,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255),
	CONSTRAINT uc_PersonID UNIQUE (Id_P,LastName)
)
ALTER TABLE Persons ADD UNIQUE (Id_P)
ALTER TABLE Persons ADD CONSTRAINT uc_PersonID UNIQUE (Id_P,LastName)
ALTER TABLE Persons DROP INDEX uc_PersonID
ALTER TABLE Persons DROP CONSTRAINT uc_PersonID
CREATE TABLE Persons
(
	Id_P int NOT NULL,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255),
	PRIMARY KEY (Id_P)
)
CREATE TABLE Persons
(
	Id_P int NOT NULL PRIMARY KEY,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255)
)
CREATE TABLE Persons
(
	Id_P int NOT NULL,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255),
	CONSTRAINT pk_PersonID PRIMARY KEY (Id_P,LastName)
)
ALTER TABLE Persons ADD PRIMARY KEY (Id_P)
ALTER TABLE Persons ADD CONSTRAINT pk_PersonID PRIMARY KEY (Id_P,LastName)
ALTER TABLE Persons DROP PRIMARY KEY
ALTER TABLE Persons DROP CONSTRAINT pk_PersonID
CREATE TABLE Orders
(
	Id_O int NOT NULL,
	OrderNo int NOT NULL,
	Id_P int,
	PRIMARY KEY (Id_O),
	FOREIGN KEY (Id_P) REFERENCES Persons(Id_P)
)
CREATE TABLE Orders
(
	Id_O int NOT NULL PRIMARY KEY,
	OrderNo int NOT NULL,
	Id_P int FOREIGN KEY REFERENCES Persons(Id_P)
	)
CREATE TABLE Orders
(
	Id_O int NOT NULL,
	OrderNo int NOT NULL,
	Id_P int,
	PRIMARY KEY (Id_O),
	CONSTRAINT fk_PerOrders FOREIGN KEY (Id_P)
	REFERENCES Persons(Id_P)
)
ALTER TABLE Orders
ADD FOREIGN KEY (Id_P)
REFERENCES Persons(Id_P)
ALTER TABLE Orders
ADD CONSTRAINT fk_PerOrders
FOREIGN KEY (Id_P)
REFERENCES Persons(Id_P)
ALTER TABLE Orders DROP FOREIGN KEY fk_PerOrders
ALTER TABLE Orders DROP CONSTRAINT fk_PerOrders
CREATE TABLE Persons
(
	Id_P int NOT NULL,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255),
	CHECK (Id_P>0)
)
CREATE TABLE Persons
(
	Id_P int NOT NULL CHECK (Id_P>0),
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255)
)
CREATE TABLE Persons
(
	Id_P int NOT NULL,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255),
	CONSTRAINT chk_Person CHECK (Id_P>0 AND City='Sandnes')
)
ALTER TABLE Persons ADD CHECK (Id_P>0)
ALTER TABLE Persons ADD CONSTRAINT chk_Person CHECK (Id_P>0 AND City='Sandnes')
ALTER TABLE Persons DROP CONSTRAINT chk_Person
ALTER TABLE Persons DROP CHECK chk_Person
CREATE TABLE Persons
(
	Id_P int NOT NULL,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255) DEFAULT 'Sandnes'
	)
CREATE TABLE Orders
(
	Id_O int NOT NULL,
	OrderNo int NOT NULL,
	Id_P int,
	OrderDate date DEFAULT GETDATE()
)
ALTER TABLE Persons ALTER City SET DEFAULT 'SANDNES'
ALTER TABLE Persons ALTER COLUMN City SET DEFAULT 'SANDNES'
ALTER TABLE Persons ALTER City DROP DEFAULT
ALTER TABLE Persons ALTER COLUMN City DROP DEFAULT
CREATE INDEX index_name ON table_name (column_name(s))
CREATE UNIQUE INDEX index_name ON table_name (column_name(s))
CREATE INDEX PersonIndex ON Person (LastName) 
CREATE INDEX PersonIndex ON Person (LastName DESC) 
CREATE INDEX PersonIndex ON Person (LastName, FirstName)
DROP INDEX index_name ON table_name
DROP INDEX table_name.index_name
DROP INDEX index_name
ALTER TABLE table_name DROP INDEX index_name
DROP TABLE ������
DROP DATABASE ���ݿ�����
TRUNCATE TABLE ������
ALTER TABLE table_name ADD column_name datatype
ALTER TABLE table_name DROP COLUMN column_name
ALTER TABLE table_name ALTER COLUMN column_name datatype
CREATE TABLE Persons
(
	P_Id int NOT NULL AUTO_INCREMENT,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255),
	PRIMARY KEY (P_Id)
)
ALTER TABLE Persons AUTO_INCREMENT=100
INSERT INTO Persons (FirstName,LastName) VALUES ('Bill','Gates')
CREATE TABLE Persons
(
	P_Id int PRIMARY KEY IDENTITY(20,10),
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255)
)
INSERT INTO Persons (FirstName,LastName) VALUES ('Bill','Gates')
CREATE TABLE Persons
(
	P_Id int PRIMARY KEY AUTOINCREMENT(20,10),
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255)
)
INSERT INTO Persons (FirstName,LastName) VALUES ('Bill','Gates')
CREATE VIEW view_name AS
SELECT column_name(s)
FROM table_name
WHERE condition
CREATE VIEW [Current Product List] AS
SELECT ProductID,ProductName
FROM Products
WHERE Discontinued=No
SELECT * FROM [Current Product List]
CREATE VIEW [Products Above Average Price] AS
SELECT ProductName,UnitPrice
FROM Products
WHERE UnitPrice>(SELECT AVG(UnitPrice) FROM Products) 
SELECT * FROM [Products Above Average Price]
CREATE VIEW [Category Sales For 1997] AS
SELECT DISTINCT CategoryName,Sum(ProductSales) AS CategorySales
FROM [Product Sales for 1997]
GROUP BY CategoryName 
SELECT * FROM [Category Sales For 1997]
CREATE OR REPLACE VIEW view_name AS
SELECT column_name(s)
FROM table_name
WHERE condition
CREATE VIEW [Current Product List] AS
SELECT ProductID,ProductName,Category
FROM Products
WHERE Discontinued=No
DROP VIEW view_name
CONVERT(VARCHAR(19),GETDATE())
CONVERT(VARCHAR(10),GETDATE(),110) 
CONVERT(VARCHAR(11),GETDATE(),106)
CONVERT(VARCHAR(24),GETDATE(),113)
SELECT column_name(s) FROM table_name WHERE column_name IS NOT NULL
SELECT function(��) FROM ��
SELECT AVG(column_name) FROM table_name
SELECT COUNT(column_name) FROM table_name
SELECT COUNT(*) FROM table_name
SELECT COUNT(DISTINCT column_name) FROM table_name
SELECT FIRST(column_name) FROM table_name
SELECT MID(column_name,start[,length]) FROM table_name
SELECT ProductName, UnitPrice, FORMAT(Now(),'YYYY-MM-DD') as PerDate FROM Products
show engines;
SELECT DISTINCT �ֶ��� FROM ����





















