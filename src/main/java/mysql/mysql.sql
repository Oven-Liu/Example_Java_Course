show databases
show processlist
show engines
create database database_name
create database 数据库名称 default character set utf8
show create database 数据库名称
alter database 数据库名称 default character set 字符集
show tables;
create table table_name(column1 data_type1, column2 data_type2...)
create table table_name like exist_table_name
create table 表名称(
	sid int,
	sname varchar(20),
	sage int
);
desc 表名称;
drop table table_name
alter table 表名称 rename [to] 新表名称
alter table 表名称 add [column] column_name data_type
alter table 表名称 add [column] column_name1 data_type1, add [column] column_name2 data_type2...
alter table 表名称 drop [column] column_name1,[drop [column] column_name2],...
alter table 表名称 modify [column] column_name new_data_tpye
alter table 表名称 change [column] old_column_name new_column_name data_tpye/new_data_tpye

SELECT DISTINCT column_name FROM 表名
SELECT column(s) FROM 表名 WHERE condition1 AND condition2
SELECT column(s) FROM 表名 WHERE condition1 OR condition2
SELECT * FROM 表名 WHERE column_name1>=value1 AND column_name2<=value2
SELECT * FROM 表名 WHERE column_name<>value
SELECT * FROM 表名 WHERE column_name1 IS NOT NULL AND column_name2<>''
SELECT * FROM 表名 WHERE column_name LIKE '李%'
SELECT sum(column_names)[,sum(column_names)…] from tablename [WHERE condition]  
SELECT MIN(column_names) AS alias_name FROM 表名
SELECT COUNT(*) FROM 表名
SELECT * FROM 表名 LIMIT start_column,count_column
SELECT * FROM 表名 ORDER BY column_name ASC/DESC
SELECT * FROM 表名 ORDER BY column_name1 ASC/DESC,column_name2 ASC/DESC
SELECT column_name,COUNT(*) FROM 表名 GROUP BY column_name
SELECT column_name,COUNT(*) FROM 表名 GROUP BY column_name HAVING condition
start transaction
savepoint 回滚点标记
rollback
rollback to savepoint 标记
commit
column_name data_type DEFAULT value
column_name data_type NOT NULL
column_name data_type UNIQUE
column_name data_type PRIMARY KEY
column_name data_type AUTO_INCREMENT
[CONSTRAINT foreign_key_name] FOREIGN KEY(column_name) REFERENCES 参考表名(column_name)
create table 表名称(
	t1 int,
	t2 int,
	sname varchar(20),
	sage int,
	PRIMARY KEY(T1,T2)
);
id INT(4) ZEROFILL PRIMARY KEY AUTO_INCREMENT
DROP FOREIGN KEY foreign_key_name
CONSTRAINT foreign_key_name FOREIGN KEY(column_name) REFERENCES table_name(column_name) ON UPDATE CASCADE
CONSTRAINT foreign_key_name FOREIGN KEY(column_name) REFERENCES 参考表名(column_name) ON DELETE CASCADE
CONSTRAINT foreign_key_name FOREIGN KEY(column_name) REFERENCES 参考表名(column_name) ON UPDATE CASCADE ON DELETE CASCADE
SELECT column_name(s) FROM table_name1,table_name2
SELECT column_name(s) 
FROM table_name1,table_name2 
WHERE table_name1.column_name1=table_name2.column_name2
SELECT column_name(s) 
FROM table_name1 
[INNER] JOIN table_name2 
WHERE table_name1.column_name2=table_name2.column_name2
SELECT column_name(s) 
FROM table_name1 
[INNER] JOIN table_name2 
ON table_name1.column_name2=table_name2.column_name2
SELECT column_name(s) 
FROM table_name1 
[INNER] JOIN middle_table_name 
ON middle_table_name.column_name=table_name1.column_name1
[INNER] JOIN table_name2 
ON middle_table_name.column_name=table_name2.column_name2

SELECT column_name(s) 
FROM table_name1 [AS] alias_name1,table_name2 [AS] alias_name2 
WHERE alias_name1.column_name2=alias_name2.column_name2
SELECT stuName,courseName,score 
FROM (SELECT * FROM stuInfo WHERE stuID='U001') AS stuInfo2 
INNER JOIN stuExamScore
ON stuInfo2.stuID=stuExamScore.stuID
INNER JOIN (SELECT * FROM examCourse WHERE courseName='java') AS examCourse2
ON examCourse2.courseID=stuExamScore.courseID

SELECT column_name(s)
FROM left_table_name
LEFT [OUTER] JOIN right_table_name
ON left_table_name.column_name=right_table_name.column_name
SELECT column_name(s)
FROM left_table_name
RIGHT [OUTER] JOIN right_table_name
ON left_table_name.column_name=right_table_name.column_name
SELECT alias_name1.column_name,alias_name2.column_name
FROM 表名 alias_name1
LEFT [OUTER] JOIN 表名 alias_name2
ON alias_name1.column_name=alias_name2.column_name
SELECT stuInfo.stuName,stuInfo.birthday
FROM stuInfo
INNER JOIN (SELECT * FROM stuInfo WHERE stuName='李四') AS stuInfo2
ON stuInfo.birthday<stuInfo2.birthday

show variables
set variable_name=value
select @@variable_name
set @variable_name=值
select @variable_name
DECLARE variable_name data_type [DEFAULT 值]
set variable_name=值
select variable_name
DELIMITER 结束符
CREATE PROCEDURE 储存过程名称(参数1 参数名称 参数类型,参数2 参数名称 参数类型...)
BEGIN
	SQL语句;
END 结束符
CALL 储存过程名称(实参)
CALL 储存过程名称(@变量名)
CREATE TRIGGER trigger_name BEFORE/AFTER INSERT ON 表名 FOR EACH ROW 
INSERT INTO 表名 VALUES(值)
UPDATE 表名 SET column_name=值 WHERE condition
DELETE FROM 表名 WHERE column_name=值
DROP PROCEDURE [IF EXISTS] 触发器名称
USE mysql;
SELECT * FROM USER;
SELECT PASSWORD('account_name')
UPDATE USER SET PASSWORD=PASSWORD('password') WHERE USER='account_name'
GRANT privilege_name ON database_name.table_name TO 'account_name'@'account_type' IDENTIFIED BY 'password'
SHOW GRANTS
SHOW GRANTS FOR 'account_name'@'account_type'
REVOKE ALL ON *.* FROM 'account_name'@'account_type'
mysqldump -h 主机名 -u 用户名 -p 数据库名 > 地址


















