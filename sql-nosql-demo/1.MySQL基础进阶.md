# Linux_MySQL

[TOC]

## 一，MySQL基础
[参考博客：随笔分类 - mysql 基础篇系列]( https://www.cnblogs.com/MrHSR/category/1209857.html )
### 1.安装部署
```markdown
数据库系统：DBS
需求分析阶段的任务是：对现实世界要处理的对象（组织、部门、企业等）进行详细调查，在了解现行系统的概况，确定新系统功能的过程中，确定系统边界、
收集支持系统目标的基础数据及其处理方法。
概念设计阶段的任务是：对用户要求描述的现实世界，通过对其进行分类、聚集和概括，建立抽象的概念数据模型，如果是关系数据库，其得到的结果是ER模型。
逻辑设计阶段的任务是：将概念数据模型设计成数据库的一种逻辑模式（关系模式），然后对关系模式进一步做规范化处理，从而提高存储效率和处理效率。
物理结构设计的任务是：根据特定数据库管理系统所提供的多种存储结构和存取方法，为具体的应用任务选定最合适的物理存储结构、存取方法和存取路径等。
这一步设计的结果就是物理数据库。
数据库实施阶段：建立实际的数据库，加人数据，数据库试运行，编码调试，整理文档。
数据库运行与维护：改善数据库的安全性和完整性，监测数据库，重建数据库。
```
[Linux_MySQL安装参考博客](https://blog.csdn.net/junzixing1985/article/details/80036858)
```markdown
1.rpm 安装： 默认在/usr/bin/mysql    bjpeng01,bjpeng02
			Server version: 5.6.42 MySQL Community Server (GPL)
2.预编译安装  https://www.cnblogs.com/MrHSR/category/1255732.html
3.源码 安装 
MySQL忘记 https://blog.csdn.net/vv19910825/article/details/82979563
	密码位置： vi /etc/my.cnf
			[mysqld]
			skip-grent-tables     -- 跳过密码登录
		# systemctl restart mysqld
		# mysql
mysql> update mysql.user set authentication_string=password('bjpeng') where user='root';
mysql> flush privileges; 
mysql> update user set password=password("bjpeng") where user="root";
mysql> flush privileges;
mysql> quit
mysql远程连接主机：mysql --host=ip地址 --user=root --password=连接目标的密码
```
### 2.MySQL数据类型
```markdown
int:执行整数类型 4个字节
double:小数类型 8个字节
data:日期,yyyy-MM-dd
datatime:日期，yyyy-MM-dd HH:mm:ss
timestamp:时间戳类型
varchar:字符串
create table bjtest1(bj_test tinyint,int_test int); --创建表
insert into bjtest1 values(111,111);--插入数据
insert into bjtest1(bj_test) values(128);   --tinyint 最大值127 有符号
create table bjtest2(bj_test tinyint unsigned,int_test int); 
--tinyint 最大值255 无符号 1个字节
desc bjtest1;
--zerofill 零填充，无需显示宽度 
create table bjtest1(t1 char(5),t2 varchar(5));
char varchar char删除后面的空格 varchar保留空格
```
### 3.DDL语句
```markdown
show databases; --查看数据库
show create database; 数据库名称; --查看数据库字符集
create database pbj character set utf8; --创建数据库
alter database pbj character set utf8;--修改数据库字符集
drop database 数据库名称；--删除数据库
use 库名; --切换数据库
show tables; --查看数据库中的表
desc 表名;--查询表结构
create table 表名（ --创建表
	列名1 数据类型1,
	列名2 数据类型2,
	。。。。
	列名n 数据类型n
）;
create table student(
	id int,
	name varchar(32),
	age int,
	score double(4,1),
	birthday date,
	insert_time timestamp
);
drop table 表名；--删除表
create table stu like student;--复制表
--修改表
show create table student;--查看表的字符集
alter table student character set utf8;--修改表的字符集
修改表名：alter table t_book rename to bbb;
添加列：alter table 表名 add column列名 varchar(30);
删除列：alter table 表名 drop column列名;
修改列名： alter table bbb change nnnnn int;--既修改名字又修改数据类型
修改列属性：alter table t_book modify name varchar(22);--只修改数据类型
alter table salary add column name varchar(30);--增加列
update salary set name = 'aaaaaa' where userid = 1;--更新数据
create index index_salary on salary(salary(5));--设置索引
explain select * from salary where name ='';--解析执行
drop index index_salary on salary;--删除索引
show variables like 'table_type'; --查看表的存储引擎
-- 如果想在一个已经建好的表中添加一列，可以用诸如：
alter table TABLE_NAME add column NEW_COLUMN_NAME varchar(20) not null;
这条语句会向已有的表中加入新的一列，这一列在表的最后一列位置。如果我们希望添加在指定的一列，可以用：
alter table TABLE_NAME add column NEW_COLUMN_NAME varchar(20) not null after COLUMN_NAME;
注意，上面这个命令的意思是说添加新列到某一列后面。如果想添加到第一列的话，可以用：
alter table TABLE_NAME add column NEW_COLUMN_NAME varchar(20) not null first;
ALTER TABLE：添加，修改，删除表的列，约束等表的定义。
```
[linux下导出、导入mysql数据库sql文件的命令](https://blog.csdn.net/guo_qiangqiang/article/details/85789735)
```markdown
导出sql文件命令
	1.导出数据和表结构
	mysqldump -u用户名 -p密码 数据库名 > 数据库名.sql
	#/usr/local/mysql/bin/ mysqldump -uroot -p abc > abc.sql
	敲回车后会提示输入密码
	2.只导出表结构
	mysqldump -u用户名 -p密码 -d 数据库名 > 数据库名.sql
	#/usr/local/mysql/bin/ mysqldump -uroot -p -d abc > abc.sql
	注：/usr/local/mysql/bin/ —> mysql的data目录
导入数据库
	首先建空数据库
	选择数据库
	设置数据库编码
	导入数据（注意sql文件的路径） mysql>source /home/abc/abc.sql;
```
### 4.DML语句
```markdown
	添加数据
		insert into student(id,name,age) values(1,'kaka',18);
	删除数据
		delete from student [where 条件]；
		delete from stu;--删除表，记录一条一条删除，慢
		truncate table stu；--删除表，再创建一个一模一样的空表
	修改数据
		update 表名 set 列名1=值1，列名2=值2 [where 条件]；
```
### 5.DQL查询语句
```markdown
	查询语句
		select 
			字段列表
		from
			表名列表
		where
			条件列表
		group by
			分组列表
		having
			分组之后的条件
		limit
			分页限定
		select * from student;
		distinct --去除重复
		like --模糊查询
			_ 单个字符
			% 多个字符
	排序语句
		select * from student order by socre;--ASC 升序，默认。
		--DESC 降序
		select * from student order by socre, name DESC;--两个条件排序
	聚合函数（排除非空）
		count:计算个数
			select count(score) from student; --
			select count(ifnull（score,0）) from student;--加入非空
		max：最大值
		min:最小值
		sum:总值
		avg:平均值
	分组查询
		--按照性别分组，分别查询平均分,人数
		select sex,avg(socre)，count(id) from student group by sex;
		select sex,avg(socre)，count(id) from student  where socre>70 group by sex;--分数大于70参与分组
		select sex,avg(socre)，count(id) from student  where socre>70 group by sex having count(id)>2;--分数大于70参与分组,且分组后人数大于2
		where和having的区别？
		where在分组之前，having在分组值后，where不能带有聚合函数，having可以
		分页查询 limit 开始的索引，每页查询的条数
		select * from student limit 0,3;--第一页，0条开始，每页3条
		公式：开始的索引 = （当前的页码 - 1） * 每页显示的条数
```
### 6.SQL约束
```markdown
主键约束 primary key
	非空且唯一；一张表只能有一个字段为主键
	alter table stu drop primary key;--删除主键
	自动增长：auto_increment
	alter table stu modify id int;--删除自动增长
非空约束 not null
唯一约束 unique --可以有多个null（唯一索引）
	alter table stu drop index id;--删除唯一约束
外键约束 foreign key 让表与表产生关联，从而保证数据的准确性
	constraint 外键名称 foreign key （外键列名称）references 主表名称（主表列名称）
	alter table stu drop foreign key emp_stu_fk;--删除外键
	alter table stu add constraint emp_stu_fk foreign key (stu_id) references dapartment(id);--添加外键
	级联操作：级联更新，级联删除
		alter table stu add constraint emp_stu_fk foreign key (stu_id) references dapartment(id) on update cascade on delete cascade;--级联更新，级联删除
```
### 7.数据库设计
```markdown
多表之间的关系
	一对一：外键唯一，主键
	一对多：主键，外键关联
	多对多：设计中间表关联，分别的主键作为关联表的外键
数据库设计的范式
    1NF：每一列都是不可分分割的原子数据项（一列不能再分成多列）
    2NF：在1NF的基础上，消除非主属性对主码的部分函数依赖（非主属性都依赖于主码）（只有一个主键，没有联合主键）
		函数依赖
		完全依赖
		部分函数依赖
		传递依赖
		码
    3NF：在2NF的基础上，消除传递依赖（消除非主属性的传递依赖）
    BCNF：消除主属性对码的部分和传递函数依赖
需求分析、概念结构设计、逻辑结构设计、物理结构设计、数据库的实施和数据库的运行和维护。
数据库设计范式化和反范式化
```
### 8.数据库备份与还原
```markdown
备份数据库：mysqldump -uroot -pmysqladmin mysqltest >d:/mysql.sql
还原数据库：create database mysqladmin;
		  use mysqladmin;
		  source d:/mysql.sql
```
### 9.多表查询
```markdown
	笛卡尔积 n*m
	消除笛卡尔积：内连接，外连接，子查询
```
### 10.事物
```markdown
	事物：要么一起成功，要么一起失败，默认自动提交
四大特征
	原子性：一个事物是一个原子性操作，要么都成功要么全部回滚失败
	持久性：一旦提交，修改永远保存在数据库中
	隔离性：数据的修改在未完成以前不可见
	一致性：数据完整性没有破坏
产生的问题：脏读，不可重复读，幻读
事物隔离级别
	读未提交（read uncommited）产生的问题：脏读，不可重复读，幻读
	读已提交（read commited）产生的问题：不可重复读，幻读
	可重复读(repeatable read --默认 产生的问题：幻读
	可串行化（serializable）--可以解决所有的问题
并发操作带来的数据不一致性：丢失修改，不可重复读，脏读。
```
### 11.DCL语句
```markdown
DCL：管理用户，授权
```
### 12.视图
```markdown
--创建视图
create or replace view view_salary
	as 
	select * from salary;
-- 查看视图
select  * from view_salary;
--修改视图
alter view view_salary
    as
    select  name from salary;
--删除视图   
drop view view_salary; 
-- 查看创建视图的信息
show create view view_salary;
-- 查看视图信息
select * from information_schema.views where table_name = 'view_salary';
```
### 13.触发器
```markdown
--触发器
select * from salary_memory;
delimiter $$
create trigger tri_salary
    after insert on salary for each row begin
    insert into salary_memory (userid,salary,name)
    values (new.userid,new.salary, new.name);
end;
$$
delimiter;
--插入salary数据，触发salary插入触发器
insert into salary (userid, salary,name) values(6,'6000','001触发器')；
select * from information_schema.views where table_name = 'tri_salary';
--删除触发器 drop trigger tri_salary;
create user 'pbj'@'localhost' identified by 'pbj';
```
```
插入的字段只能在给定的范围内选择
enum:枚举 单选
set：集合 多选
```
```
索引
存储过程
阻塞和死锁
死锁：指两个或者多个事物在同一个资源上相互占用，并请求锁定对方占用的资源，从而导致恶性循环的现象。
解决办法：死锁检测和死锁超时机制。
```
## 二，MySQL进阶
[参考博客：随笔分类 - mysql 架构篇系列]( https://www.cnblogs.com/MrHSR/category/1323212.html )
### 1.备份恢复

### 2.主备复制

### 3.读写分离

### 4.HA架构

### 5.分布式数据库

### 6.压力测试
```markdown
mysql基准测试
TPS
QPS
响应时间
```
### 7.性能优化
#### 7.1.SQL优化(show status命令)
```markdown
 1.  通过show status 命令了解各种sql的执行频率
通过show [session | global] 命令可以提供服务器状态信息，也可以在操作系统上使用mysqladmin extended-status 命令来获得。 session 是默认参数 是当前连接的统计结果， global是自数据库上次启动到今的统计结果。
 -- 查看全局所有统计的值  SHOW GLOBAL STATUS  LIKE 'Com%';
(1)  Com_xxx:通常比较关心的是以下几个统计参数
Com_select执行select 操作的次数，一次查询只累计加1
Com_insert执行insert操作的次数，对于批量插入的insert操作，只累计加1
Com_update执行update操作的次数
Com_delete执行delete 操作的次数
(2)  针对innodb 存储引擎的参数,累加的算法有所不同
Innodb_rows_read	Select 查询返回的行数
Innodb_rows_inserted	执行insert操作插入的行数
Innodb_rows_updated	执行update操作更新的行数
Innodb_rows_deleted	执行delete操作删除的行数
　通过以上参数，可以了解当前数据库的应用是以插入更新为主还是查询操作为主，以及各种类型的sql的分布比例。
(3) com_commit和com_rollback
　　　　对于事务型应用,通过com_commit和com_rollback可以了解事务提交和回滚的情况，对于回滚操作非常频繁的数据库，可能意味着应用编写存在问题。
　(4) 以下参数便于用户了解数据库的基本情况
connections	试图连接mysql 服务器的次数
Uptime	服务器工作时间(单位：秒) 9001527秒 = 工作了104天
Slow_queries	慢查询的次数.
对于Slow_queries 是指超过Long_query_time值就会被记录。Long_query_time表示超过多少秒的查询就写入日志，默认的是10s,设置为0的话表示记录所有的查询。
--  下面设置的时间是2秒    SHOW VARIABLES LIKE 'long%'
```
#### 7.2.SQL优化(explain分析)
```markdown
　1. 定位执行效率较低的sql 语句
　　通过两种方式可以定位出效率较低的sql 语句。
　　(1) 通过上篇讲的慢日志定位，在mysqld里写一个包含所有执行时间超过 long_query_time秒的sql语句的日志文件，后面具体介绍。
　　(2) 通过show processlist 实时定位线程状态，是否锁表等，下面简单演示下show processlist。
-- 会话 1获取city 表锁 	LOCK TABLE city READ;
-- 会话2更新city表		UPDATE city SET citycode='001'
查看发现: 状态列中找到waiting for table metadata lock(等待 table元数据锁)，当前线程的info 信息 如下所示：
2. 通过explain 来分析sql执行计划
　　通过上面的慢日志定位和processlist 找出效率低的sql语句后，可以通过explain或者desc命令获取mysql 如何执行查询语句的信息。
3. 确定问题采取优化措施
　　通过上面的索引解释，可以对照sql语句进行问题确认，以及索引的优化。如重点查看 rows 扫描了多少行， type 取值对应的性能， key字段和extra描述都可以来确定该语句是否需要调优。下面是各种索引的创建：
-- 主键索引
ALTER TABLE city ADD PRIMARY KEY(city_id);
-- 唯一索引
ALTER TABLE city ADD UNIQUE  KEY(city_id);
-- 普通索引 或叫辅助索引
CREATE INDEX ixcityname ON city(cityname);
-- 前缀索引 cityname字段创建10个字节
CREATE INDEX ixcityname ON city(cityname(10));
-- 复合索引  创建city表的多列
CREATE INDEX ix1 ON city(cityname(10),citycode);
-- 外键索引
ALTER TABLE city ADD KEY idx_fk_country_id(country_id) ;
```
#### 7.3SQL 优化(索引使用方法)
```markdown
1.  索引的存储分类
　　MyISAM存储引擎的表的数据和索引是自动分开存储的，各自是独立的一个文件, innodb 存储引擎的表的数据和索引是存储在同一个表空间里面，可以有多个文件组成。 MyISAM和Innodb存储引擎都支持btree索引，memory/heap存储引擎支持hash和btree索引。
2. mysql如何使用索引
查询要使用索引最主要的条件是查询条件中需要使用索引关键字，如果是多列索引，那么只有索引条件使用了多列关键字最左边的前缀时，才可以使用索引，否则不能.
(1) 创建多列索引
-- 多列索引又叫复合索引  创建city表的多
CREATE INDEX ix1 ON city(cityname(10),citycode);    
-- 使用cityname进行查询，发现即使不使用citycode也能使用到索引，这就是索引前缀的特性
EXPLAIN SELECT * FROM city WHERE cityname='12'
-- 使用citycode进行查询，就没有使用索引
EXPLAIN SELECT * FROM city WHERE citycode='12'
(2) 使用like 需要使用模糊查询时， 建了索引的字段 %必须放在关键词后面
 -- 如下所示EXPLAIN SELECT * FROM city WHERE cityname LIKE '12%'
(3) 使用条件is null 如果列名是索引，那么使用is null 会使用到索引
 -- 如下所示EXPLAIN SELECT * FROM city WHERE cityname IS NULL
3. 存在索引但不会使用到索引
(1) 使用索引比全表扫描更慢，则不使用索引。 比如key分布在1到100之间，使用索引 key>1 and key<90 。
(2) 使用memory/heap表 使用where条件不使用 = 进行查询时，那么不会用到索引。
(3) 用or分割开的条件， 如果or 前面条件中列有索引，or后面列没有索引，那么索引不会被用到如下
 --  country_id 列是索引  citycode不是索引
EXPLAIN SELECT * FROM city WHERE country_id=2 OR citycode='000'
--  country_id 列是外键索引  city_id主键索引
EXPLAIN SELECT * FROM city WHERE country_id=2 OR city_id=2 (发现key也是空后面在分析)
(4) 列类型是字符串，记得值加引号
--  没有加引号 EXPLAIN SELECT * FROM city WHERE cityname=22
```
#### 7.4SQL 优化(各种优化方法点)
```markdown
1.通过handler_read 查看索引使用情况
　　如果索引经常被用到 那么handler_read_key的值将很高，这个值代表了一个行被索引值读的次数， 很低的值表明增加索引得到的性能改善不高，索引并不经常使用。
handler_read_rnd_next 的值高则意味着查询运行低效，应该建立索引，这个值表示在数据文件中读下一行的请求数，如果是正进行大量扫描值会较高，一般是索引不正确或没有利用到索引。
SHOW STATUS LIKE 'Handler_read%';
2. 优化定期分析表
　　analyze 语句用于分析和存储表的关键字分布，分析的结果将可以使得系统得到准确的统计信息，使得sql能够生成正确的执行计划。如果用户感觉实际执行计划并不是预期的执行计划，执行一次分析表可能会解决问题。 在分析时使用一个读取锁对表进行了锁定，这个对于myisam,bdb,innodb表有作用。
-- 分析表ANALYZE TABLE city;
3. 优化检查表
　　check 检查表的作用是检查一个或多个表是否有错误。check table对myisam和innodb表有作用。
-- 检查表CHECK TABLE city;
4. 优化optimize
　　如果一个表已经删除了一大部分，更者对可变长度行的表(varchar,blob,text)进行了很多更改，则就使用optimize table命令来进行优化,它是将表空间碎片进行合并，可以消除由于删除或者更新造成的空间浪费，对myisam, bdb ,innodb表起作用。
 -- 优化表OPTIMIZE TABLE city;
总结： analyze, check, OPTIMIZE 执行期间将对表进行锁定，在繁忙时候不要操作。
5.  优化大批量插入数据
5.1 针对大量数据导入到一个非空的myisam表，可以通过以下方式快速导入大量数据。
    ALTER TABLE tab_name DISABLE KEYS;
        loading the DATA
    ALTER TABLE tab_name ENABLE KEYS;
　　   DISABLE KEYS和 ENABLE KEYS是打开或者关闭myisam表非唯一索引的更新，对于myisam空表则默认是先导入数据然后才创建索引，所以不用设置。
5.2 针对innodb表
 　在导入之前设置unique_checks=0导完后开启setunique_checks=1。
 　设置autocommit=0导完后开启autocommit=1。
6. 优化insert 语句
(1) 不同客户插入很多行数据时，更改INSERT INTO为 INSERT DELAYED INTO，这使语句得到更高的速度。
(2) 将索引文件和磁盘文件分在不同磁盘上存放（利用表的选项）。
(3) 如果是批量插入 对myisam表可使用bulk_insert_buffer_size 来提高速度。
(4) 使用load data infile 通常比insert语句快20倍。
7. 优化group by语句
　　默认情况下 group by 会对字段进行排序，如果想避免排序结果带来的消耗，可以指定order by null 来禁止排序 如下：
-- CityCode  默认使用了排序   (如果CityCode已建索引，默认就排序好了 不用优化)
EXPLAIN SELECT COUNT(country_id), CityCode FROM city   GROUP BY CityCode
-- 使用 order by null 来禁止排序
EXPLAIN SELECT COUNT(country_id), CityCode FROM city   GROUP BY CityCode ORDER BY NULL
8. 优化order by 语句
　　mysql 可以使用一个索引来满足order by 子句,而不需要额外的排序(上面group by 就是未键索引 需要再排序),并且order by 的顺序与索引顺序相同，升序或降序。
--  order by 使用到了索引的排序
EXPLAIN SELECT country_id, city_id FROM city   GROUP BY country_id,city_id ;
EXPLAIN SELECT country_id, city_id FROM city   GROUP BY country_id,city_id ORDER BY country_id,city_id;
EXPLAIN SELECT country_id, city_id FROM city   GROUP BY country_id,city_id ORDER BY country_id ASC ,city_id ASC;
EXPLAIN SELECT country_id, city_id FROM city   GROUP BY country_id,city_id ORDER BY country_id DESC ,city_id DESC;
-- order by 未使用到索引的排序
EXPLAIN SELECT country_id, city_id FROM city   GROUP BY country_id,city_id ORDER BY country_id ASC ,city_id DESC;
```
#### 7.5 SQL 优化(表优化)
```markdown
一. 使用sql提示
sql 提示(sql hint)是优化数据库的一个重要手段，是在sql语句中加入一些人为的提示来达到优化操作的目的。
1.1 use index
在查询语句中表名的后面,添加use index 强制mysql使用该索引，不考虑其它索引。
EXPLAIN SELECT * FROM city USE INDEX(ix1) WHERE city_id=1;
1.2 ignore index 
在查询语句中表名的后面,添加ignore index，使用mysql忽视一个或者多个索引。
EXPLAIN SELECT * FROM city IGNORE INDEX(ix1) WHERE  city_id=14;
1.3 force index
在查询语句中表名的后面,添加force index，当mysql不走索引时，强制走索引。
-- 某些情况下，有索引但mysql不走索引，强制使用
EXPLAIN SELECT * FROM city FORCE INDEX (PRIMARY) WHERE  city_id>0;
二 .优化数据库对象
1. 优化表的数据类型
　在mysql中,可以使用函数procedure analyse()对当前应用的表进行分析。对表列中的数据类型给出合理的改进建议，用户可以根据实际情况来考虑。
　例如:下面生产库中有一个菜单表，字段类型及长度如下：
-- 使用procedure analyse()分析
SELECT * FROM Adm_Menu PROCEDURE ANALYSE(16,256);
  下面remark 字段里面值的最大长度的是30长度, 所以系统建议给出30长度：
2. 通过折分提高表的访问效率
折分可以是垂直拆分和水平拆分，这是一种设计思路，这篇不讲。
3. 逆规范化
逆规范化也叫提高表的冗余，有利于提高查询性能。这是一种设计思路，这篇不讲。
4. 使用中间表提高统计查询速度
比如有一个大表记录了客户的每天消费记录，需要按月统计总消费金额， 可以放入到中间表，减轻大表的频繁查询. 这是一种设计思路。
```
### 8.锁问题
```markdown
一.概述((事务与隔离级别介绍)看上面)
　　在数据库中，数据是属于共享资源，为了保证并发访问的一致性，有效性，产生了锁。接下来重点讨论mysql锁机制的特点，常见的锁问题，以及解决mysql锁问题的一些方法或建议。 相比其他数据库，mysql 锁机制比较简单，显著的特点是 不同的存储引擎支持不同的锁机制。在innodb中支持行锁和表锁，默认行锁。
　　mysql 的三种锁归纳如下：
　　表级锁：开销小，加锁快，不会出现死锁，锁定粒度大，发生锁冲突的概率最高，并发度最低。
　　行级锁：开销大，加锁慢；会出现死锁，锁定粒度最小，发生锁冲突的概率最低，并发度最高。
　　页面锁：开销和加锁时间界于表锁和行锁之间，会出现死锁，并发度一般。
　在不同sql语句执行，会采用不同的锁，由mysql内部自动加锁，解锁，以及对应的锁类型。重点介绍mysql表锁和innodb行锁。由于MyisAm将被innodb取代了，后面重点讲innodb。
1. 事务介绍
innodb的二个特点是一是支持事务,二是采用行级锁，但事务的引入也带来了一些新问题，先介绍一下背景知识。
　　1.1 事务及其属性ACID
　　　　事务是同一组sql语句组成的逻辑处理单元，具有原子性，一致性，隔离性，持久性。
　　1.2 并发事务处理带来的问题
　　　　更新丢失，脏读，不可重复读，幻读。
　　1.3 事务隔离级别
　　　　由于并发事务处理带来的问题，那么解决方法就是对应不同的事务隔离级别。                     数据库实现事务隔离的方式，基本上分为以下两种：
　　　　（1）一种是在读取数据前，对其加锁，阻止其他事务对数据进行修改。
　　　　（2）另一种是不用加任何锁，通过快照 Snapshot 形式的 “数据版本并发控制” (MultiVersion Concurrency Control)简称MVCC。
　　　　数据库的事务隔离级别越严格，并发副作用越小，付出的代价也就越大，因为实质上就是使事务在一定程度上"串行化"进行，这与"并发"是矛盾的。
-- 查看事务隔离级别,默认是REPEATABLE-READ，在sql server里默认是Read Committed
SELECT @@tx_isolation
　关于mysql锁机制，mysql版本为5.7，引擎为innodb。 
概述总的来说，InnoDB共有七种类型的锁：
        共享/排它锁(Shared and Exclusive Locks)
        意向锁(Intention Locks)
        记录锁(Record Locks)
        间隙锁(Gap Locks)
        临键锁(Next-key Locks)
        插入意向锁(Insert Intention Locks)
        自增锁(Auto-inc Locks)
```
#### 8.1mysql锁详解
```markdown
1. 共享/排它锁(Shared and Exclusive Locks)
    共享锁（Share Locks，记为S锁），读取数据时加S锁
    排他锁（eXclusive Locks，记为X锁），修改数据时加X锁
使用的语义为：共享锁之间不互斥，简记为：读读可以并行
排他锁与任何锁互斥，简记为：写读，写写不可以并行。可以看到，一旦写数据的任务没有完成，数据是不能被其他任务读取的，这对并发度有较大的影响。对应到数据库，可以理解为，写事务没有提交，读相关数据的select也会被阻塞，这里的select是指加了锁的，普通的select仍然可以读到数据(快照读)。
2. 意向锁(Intention Locks)
　　InnoDB为了支持多粒度锁机制(multiplegranularitylocking)，即允许行级锁与表级锁共存，而引入了意向锁(intention locks)。意向锁是指，未来的某个时刻，事务可能要加共享/排它锁了，先提前声明一个意向。
意向锁是一个表级别的锁(table-level locking)；
意向锁又分为：
意向共享锁(intention shared lock, IS)，它预示着，事务有意向对表中的某些行加共享S锁；
意向排它锁(intention exclusive lock, IX)，它预示着，事务有意向对表中的某些行加排它X锁；
加锁的语法为：
    select ... lock in share mode;　　要设置IS锁；
    select ... for update;　　　　　　 要设置IX锁；
事务要获得某些行的S/X锁，必须先获得表对应的IS/IX锁，意向锁仅仅表明意向，意向锁之间相互兼容，兼容互斥表如下：
 	IS		IX
IS	兼 容	兼 容
IX	兼 容	兼 容
虽然意向锁之间互相兼容，但是它与共享锁/排它锁互斥，其兼容互斥表如下:
 	S		X
IS	兼 容	互 斥
IX	互 斥	互 斥
排它锁是很强的锁，不与其他类型的锁兼容。这其实很好理解，修改和删除某一行的时候，必须获得强锁，禁止这一行上的其他并发，以保障数据的一致性。
3. 记录锁(Record Locks)
记录锁，它封锁索引记录，例如(其中id为pk)：
create table lock_example(id smallint(10),name varchar(20),primary key id)engine=innodb;
数据库隔离级别为RR，表中有如下数据：
10, zhangsan
20, lisi
30, wangwu
select * from t where id=1 for update;
其实这里是先获取该表的意向排他锁(IX)，再获取这行记录的排他锁(我的理解是因为这里直接命中索引了)，以阻止其他事务插入，更新，删除id=1的这一行。
4. 间隙锁(Gap Locks)
间隙锁，它封锁索引记录中的间隔，或者第一条索引记录之前的范围，又或者最后一条索引记录之后的范围。依然是上面的例子，InnoDB，RR：
select * from lock_example
    where id between 8 and 15 
    for update;
　　这个SQL语句会封锁区间(8,15)，以阻止其他事务插入id位于该区间的记录。
间隙锁的主要目的，就是为了防止其他事务在间隔中插入数据，以导致“不可重复读”。如果把事务的隔离级别降级为读提交(Read Committed, RC)，间隙锁则会自动失效。
5. 临键锁(Next-key Locks)
临键锁，是记录锁与间隙锁的组合，它的封锁范围，既包含索引记录，又包含索引区间。
默认情况下，innodb使用next-key locks来锁定记录。但当查询的索引含有唯一属性的时候，Next-Key Lock 会进行优化，将其降级为Record Lock，即仅锁住索引本身，不是范围。
举个例子，依然是如上的表lock_example，但是id降级为普通索引(key)，也就是说即使这里声明了要加锁(for update)，而且命中的是索引，但是因为索引在这里没有UK约束，所以innodb会使用next-key locks，数据库隔离级别RR：
事务A执行如下语句，未提交：select * from lock_example where id = 20 for update;
事务B开始，执行如下语句，会阻塞：insert into lock_example values('zhang',15);
如上的例子，事务A执行查询语句之后，默认给id=20这条记录加上了next-key lock，所以事务B插入10(包括)到30(不包括)之间的记录都会阻塞。临键锁的主要目的，也是为了避免幻读(Phantom Read)。如果把事务的隔离级别降级为RC，临键锁则也会失效。
6. 插入意向锁(Insert Intention Locks)
　　对已有数据行的修改与删除，必须加强互斥锁(X锁)，那么对于数据的插入，是否还需要加这么强的锁，来实施互斥呢？插入意向锁，孕育而生。
　　插入意向锁，是间隙锁(GapLocks)的一种（所以，也是实施在索引上的），它是专门针对insert操作的。多个事务，在同一个索引，同一个范围区间插入记录时，如果插入的位置不冲突，不会阻塞彼此。
Insert Intention Lock signals the intent to insert in such a way that multiple transactions inserting into the same index gap need not wait for each other if they are not inserting at the same position within the gap.
举个例子(表依然是如上的例子lock_example，数据依然是如上)，事务A先执行，在10与20两条记录中插入了一行，还未提交：insert into t values(11, xxx);
事务B后执行，也在10与20两条记录中插入了一行：insert into t values(12, ooo);
　　因为是插入操作，虽然是插入同一个区间，但是插入的记录并不冲突，所以使用的是插入意向锁，此处A事务并不会阻塞B事务。
7. 自增锁(Auto-inc Locks)
　　自增锁是一种特殊的表级别锁（table-levellock），专门针对事务插入AUTO_INCREMENT类型的列。最简单的情况，如果一个事务正在往表中插入记录，所有其他事务的插入必须等待，以便第一个事务插入的行，是连续的主键值。
AUTO-INC lock is a special table-level lock taken by transactions inserting into tables with AUTO_INCREMENT columns. In the simplest case, if one transaction is inserting values into the table, any other transactions must wait to do their own inserts into that table, so that rows inserted by the first transaction receive consecutive primary key values.
　　举个例子(表依然是如上的例子lock_example)，但是id为AUTO_INCREMENT，数据库表中数据为：
1, zhangsan
2, lisi
3, wangwu
事务A先执行，还未提交： insert into t(name) values(xxx);
事务B后执行： insert into t(name) values(ooo);
此时事务B插入操作会阻塞，直到事务A提交。
```
#### 8.2锁总结
```markdown
以上总结的7种锁，个人理解可以按两种方式来区分：
1. 按锁的互斥程度来划分，可以分为共享、排他锁；
共享锁(S锁、IS锁)，可以提高读读并发；
为了保证数据强一致，InnoDB使用强互斥锁(X锁、IX锁)，保证同一行记录修改与删除的串行性；
2. 按锁的粒度来划分，可以分为：
表锁：意向锁(IS锁、IX锁)、自增锁；
行锁：记录锁、间隙锁、临键锁、插入意向锁；
其中InnoDB的细粒度锁(即行锁)，是实现在索引记录上的(我的理解是如果未命中索引则会失效)；　　
记录锁锁定索引记录；间隙锁锁定间隔，防止间隔中被其他事务插入；临键锁锁定索引记录+间隔，防止幻读；
InnoDB使用插入意向锁，可以提高插入并发；
间隙锁(gap lock)与临键锁(next-key lock)只在RR以上的级别生效，RC下会失效；
```
### 9.自动化运维

## 三，MySQL高级
[参考博客：随笔分类 - mysql 进阶篇系列]( https://www.cnblogs.com/MrHSR/category/1255732.html )
[参考博客：MySQL高手系列](https://www.cnblogs.com/itsoku123/category/1539183.html)
### 1.MySQL执行代码解析
```markdown
MySQL 手写顺序
	select ... from... where.... group by... having... order by.. limit [offset,] (rows)
MySQL 机器读顺序
	from... where...group by... having.... select ... order by... limit
FORM: 对FROM的左边的表和右边的表计算笛卡尔积。产生虚表VT1
ON: 对虚表VT1进行ON筛选，只有那些符合<join-condition>的行才会被记录在虚表VT2中。
JOIN： 如果指定了OUTER JOIN（比如left join、 right join），那么保留表中未匹配的行就会作为外部行添加到虚拟表VT2中，产生虚拟表VT3, rug from子句中包含两个以上的表的话，那么就会对上一个join连接产生的结果VT3和下一个表重复执行步骤1~3这三个步骤，一直到处理完所有的表为止。
WHERE： 对虚拟表VT3进行WHERE条件过滤。只有符合<where-condition>的记录才会被插入到虚拟表VT4中。
GROUP BY: 根据group by子句中的列，对VT4中的记录进行分组操作，产生VT5.
CUBE | ROLLUP: 对表VT5进行cube或者rollup操作，产生表VT6.
HAVING： 对虚拟表VT6应用having过滤，只有符合<having-condition>的记录才会被 插入到虚拟表VT7中。
SELECT： 执行select操作，选择指定的列，插入到虚拟表VT8中。
DISTINCT： 对VT8中的记录进行去重。产生虚拟表VT9.
ORDER BY: 将虚拟表VT9中的记录按照<order_by_list>进行排序操作，产生虚拟表VT10.
LIMIT：取出指定行的记录，产生虚拟表VT11, 并将结果返回。

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'linuxmysql' WITH GRANT OPTION;
```
### 2.MySQL join查询（七个）
```markdown
inner join --a,b公共部分
select	* from tbl_emp a inner join tbl_dept b on a.deptId = b.id; 
left join --a,b公共部分和a的全部
select	* from tbl_emp a left join tbl_dept b on a.deptId = b.id; 
right join --a,b公共部分和b的全部
select	* from tbl_emp a right join tbl_dept b on a.deptId = b.id; 
 --a(不包括a,b公共部分和)
select	* from tbl_emp a left join tbl_dept b on a.deptId = b.id where b.id is null;
 --b(不包括a,b公共部分和)
select	* from tbl_emp a right join tbl_dept b on a.deptId = b.id where a.deptId is null;
 --a,b全部 Full outer join
select	* from tbl_emp a left join tbl_dept b on a.deptId = b.id
union
select	* from tbl_emp a right join tbl_dept b on a.deptId = b.id;
--a,b全部（不包括a,b的公共部分）
select	* from tbl_emp a left join tbl_dept b on a.deptId = b.id where b.id is null
union
select	* from tbl_emp a right join tbl_dept b on a.deptId = b.id where a.deptId is null;
```
### 3.索引与数据处理
```markdown
 简单理解为排好序的快速查找数据结构   --数据结构（B-tree(多路平衡查找树)）
 优势：建立索引，提高查询效率，降低数据库的IO成本，索引进行排序，降低排序成本，降低CPU消耗。
 劣势：
 myISAM和Innodb存储引擎默认是B—tree索引，
 show index from tbl_name;  --查看默认索引
 索引分类：单值索引，唯一索引，复合索引
```
### 4.Explain
```markdown
Explain 查看执行计划
表的读取顺序
数据读取查找的查找类型
哪些索引可以使用
哪些索引被实际使用
表之间的引用
每张表有多少行被优化器查询
	Explain +SQL语句；
	--  查看执行计划
 DESC SELECT   LedRecycleInfoLogID FROM LedLogInfo WHERE LedRecycleInfoLogID=2;
 EXPLAIN SELECT   LedRecycleInfoLogID FROM LedLogInfo WHERE LedRecycleInfoLogID=2;	
```
![查看执行计划](https://images2018.cnblogs.com/blog/151560/201807/151560-20180718135800357-269624994.png)

| **类型**                      | **说明**                                                     |
| ----------------------------- | ------------------------------------------------------------ |
| Select_type 表示select 的类型 | 取值有: simple：简单表不使用表连接或子查询       Primary:主查询       Union： union中的第二个或者后面的查询语句       Subquery: 子查询中的第一个select |
| Type 表示表的连接类型         | 性能由好到差依次是: system: 表中仅有一行。 Const: 单表中最多有一个匹配行， 例如 primary key, unique index                   Eq_ref: 多表连接下使用primary key 或者unique index                   Ref: 与Eq_ref区别在于使用普通索引。                   Ref_or_null: 与Ref区别在于条件中包含有null值的查询                   Index_merge: 索引合并优化                   Unique_subquery: in的后面是一个查询主键字段的子查询  Index_subquery: 与 Unique_subquery区别在于in后面查询非唯一索引字段的子查询                   Range: 单表中的范围查询                   Index: 全表索引扫描                   All :全表扫描 |
| Possible_keys                 | 表示查询时，可能使用的索引                                   |
| key                           | 表示实际使用的索引                                           |
| Key_len                       | 索引字段的长度.  长度越短， 性能越好                         |
| rows                          | 扫描的行数                                                   |
| extra                         | 执行情况的说明和描述                                         |
### 5.索引失效



## 未阅读
[MySQL必知必会1-20章读书笔记](https://www.cnblogs.com/hwahe/p/12822943.html)

## 四，SQL 练习
```sql
/*题目要求*/
#试题一、找出平均成绩大于60的所有学生的学号（即student_id)、姓名和平均分数

SELECT t.student_id,t.name,AVG(t.number) 
FROM (  SELECT a.`student_id` AS stuid,a.`name`,b.`student_id`,b.`number` 
	FROM tbl_student a INNER JOIN tbl_score b ON a.`student_id` = b.`student_id`) t
 GROUP BY student_id HAVING AVG(number)>60 ;

SELECT a.`student_id`,c.`name`,AVG(a.number) 
FROM tbl_score a  INNER JOIN tbl_course  b ON a.course_id = b.course_id 
		  INNER JOIN tbl_student c ON a.`student_id` = c.`student_id`
		  GROUP BY a.student_id HAVING AVG(a.number)>60 ;

SELECT b.`student_id`,a.`name`,AVG(b.number) AS avg_number
FROM tbl_student a INNER JOIN tbl_score b ON a.`student_id` = b.`student_id`
GROUP BY b.`student_id` HAVING AVG(b.number)>60 

#试题二、查询所有学生的学号，姓名，选课数和总成绩；
SELECT t.student_id,a.name,t.totalcourse,t.totalnumber FROM tbl_student a INNER JOIN
(SELECT student_id,COUNT(course_id) totalcourse,SUM(number) totalnumber 
	FROM tbl_score GROUP BY student_id ) t ON t.student_id = a.student_id 

SELECT a.student_id,b.name,COUNT(a.course_id),SUM(a.number) FROM tbl_score a INNER JOIN
tbl_student b ON a.student_id = b.student_id GROUP BY a.student_id;

#试题三、查找名字中含“封”字的老师的总数
SELECT COUNT(teacher_id) FROM tbl_teacher WHERE NAME LIKE '%佟%';

#试题四、查询没有学过李玉婷老师课的同学的学号、姓名
SELECT a.student_id,a.name 
FROM tbl_student a WHERE a.student_id NOT IN 
  (SELECT student_id FROM tbl_score WHERE course_id = 
    (SELECT course_id FROM tbl_course WHERE teacher_id = 
    (SELECT teacher_id FROM tbl_teacher WHERE NAME = '李玉婷')))

#试题五、查询学过"4"且学过编号"5"课程的同学的学号
SELECT 
	a.student_id
FROM 		
	(SELECT * FROM tbl_score WHERE course_id = 4) a 
INNER JOIN 
	(SELECT * FROM tbl_score WHERE course_id = 5) b
ON a.student_id = b.student_id;

#试题六、查询编号"1"成绩比编号"2"成绩低的学生的学号
SELECT 
	t1.student_id,t1.course_id,t1.number,t2.course_id,t2.number 
FROM
	(SELECT student_id,course_id,number FROM tbl_score WHERE course_id = 1) t1
INNER JOIN
	(SELECT student_id,course_id,number FROM tbl_score WHERE course_id = 2) t2
ON 
	t1.student_id = t2.student_id 
WHERE 
	t1.number < t2.number	ORDER BY t1.student_id;
	
#试题七、找出有一门课程低于60分的学生的学号和名字

SELECT t2.student_id,t1.name FROM tbl_student t1
    INNER JOIN
    (SELECT student_id,MIN(number) FROM tbl_score 
GROUP BY student_id HAVING MIN(number)<60) t2 
ON t1.student_id = t2.student_id;

#试题八、查询选完全部课程的学生的学号
SELECT t1.student_id,COUNT(t2.`course_id`) AS totalCourse 
FROM tbl_score t1 INNER JOIN tbl_course t2 ON t1.`course_id` = t2.`course_id` 
GROUP BY t1.`student_id` 
HAVING totalCourse = (SELECT COUNT(course_id) FROM tbl_course);

#试题九、按平均成绩从高到低，显示所有学生的各科课程成绩

SELECT 
	t.student_id,t.course_id,t.number,b.avgScore 
FROM 
	tbl_score t 
LEFT JOIN 
	(SELECT student_id,AVG(number) AS avgScore FROM tbl_score GROUP BY student_id) b 
ON t.`student_id` = b.student_id  
ORDER BY avgScore DESC;

#试题十、查询各科成绩的最高分和最低分及对应的学生学号。
SELECT * FROM (
SELECT a.`course_id`,a.`student_id`,b.max_number FROM tbl_score a INNER JOIN
(SELECT course_id,MAX(number) max_number FROM tbl_score GROUP BY course_id) b 
ON a.`course_id` = b.course_id
WHERE a.`number` = b.max_number ) x1 
INNER JOIN (
SELECT c.`course_id`,c.`student_id`,d.min_number  FROM tbl_score c INNER JOIN
(SELECT course_id,MIN(number) min_number FROM tbl_score GROUP BY course_id) d 
ON c.`course_id` = d.course_id
WHERE c.`number` = d.min_number ) x2
ON x1.course_id = x2.course_id 
ORDER BY x1.course_id;
```

## 五，SQL语句练习
[参考资料1](https://www.cnblogs.com/wan-ge1212/p/10254742.html)
```mysql
#准备数据 ： 测试表格
-- 数据库文件：
–- 1.学生表
Student(s_id,s_name,s_birth,s_sex) –-学生编号,学生姓名, 出生年月,学生性别
–- 2.课程表
Course(c_id,c_name,t_id) ––课程编号, 课程名称, 教师编号
–- 3.教师表
Teacher(t_id,t_name) –教师编号,教师姓名
–- 4.成绩表
Score(s_id,c_id,s_score) –-学生编号,课程编号,分数
-- 建库
create database sqltest character set utf8;
use sqltest;
-- 建表
–- 1.学生表
CREATE TABLE `Student`(
    `s_id` VARCHAR(20),
    `s_name` VARCHAR(20) NOT NULL DEFAULT '',
    `s_birth` VARCHAR(20) NOT NULL DEFAULT '',
    `s_sex` VARCHAR(10) NOT NULL DEFAULT '',
    PRIMARY KEY(`s_id`)
);
--课程表
CREATE TABLE `Course`(
    `c_id`  VARCHAR(20),
    `c_name` VARCHAR(20) NOT NULL DEFAULT '',
    `t_id` VARCHAR(20) NOT NULL,
    PRIMARY KEY(`c_id`)
);
-- 教师表
CREATE TABLE `Teacher`(
    `t_id` VARCHAR(20),
    `t_name` VARCHAR(20) NOT NULL DEFAULT '',
    PRIMARY KEY(`t_id`)
);
-- 成绩表
CREATE TABLE `Score`(
    `s_id` VARCHAR(20),
    `c_id`  VARCHAR(20),
    `s_score` INT(3),
    PRIMARY KEY(`s_id`,`c_id`)
);
-- 插入学生表测试数据
insert into Student values('01' , '赵雷' , '1990-01-01' , '男');
insert into Student values('02' , '钱电' , '1990-12-21' , '男');
insert into Student values('03' , '孙风' , '1990-05-20' , '男');
insert into Student values('04' , '李云' , '1990-08-06' , '男');
insert into Student values('05' , '周梅' , '1991-12-01' , '女');
insert into Student values('06' , '吴兰' , '1992-03-01' , '女');
insert into Student values('07' , '郑竹' , '1989-07-01' , '女');
insert into Student values('08' , '王菊' , '1990-01-20' , '女');
-- 课程表测试数据
insert into Course values('01' , '语文' , '02');
insert into Course values('02' , '数学' , '01');
insert into Course values('03' , '英语' , '03');
-- 教师表测试数据
insert into Teacher values('01' , '张三');
insert into Teacher values('02' , '李四');
insert into Teacher values('03' , '王五');
-- 成绩表测试数据
insert into Score values('01' , '01' , 80);
insert into Score values('01' , '02' , 90);
insert into Score values('01' , '03' , 99);
insert into Score values('02' , '01' , 70);
insert into Score values('02' , '02' , 60);
insert into Score values('02' , '03' , 80);
insert into Score values('03' , '01' , 80);
insert into Score values('03' , '02' , 80);
insert into Score values('03' , '03' , 80);
insert into Score values('04' , '01' , 50);
insert into Score values('04' , '02' , 30);
insert into Score values('04' , '03' , 20);
insert into Score values('05' , '01' , 76);
insert into Score values('05' , '02' , 87);
insert into Score values('06' , '01' , 31);
insert into Score values('06' , '03' , 34);
insert into Score values('07' , '02' , 89);
insert into Score values('07' , '03' , 98);
```
```markdown
--------------------- 
-- 1、查询"01"课程比"02"课程成绩高的学生的信息及课程分数
  SELECT A.s_id,A.s_score
FROM (SELECT * FROM score WHERE c_id=1) A,
     (SELECT * FROM score WHERE c_id=2 ) B
WHERE A.s_id=B.s_id
AND A.s_score>B.s_score;

-- 2、查询"01"课程比"02"课程成绩低的学生的信息及课程分数
SELECT A.s_id,A.s_score
FROM (SELECT * FROM score WHERE c_id=1) A,
     (SELECT * FROM score WHERE c_id=2 ) B
WHERE A.s_id=B.s_id
   AND A.s_score>B.s_score;

-- 3、查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩
SELECT A.s_id,B.s_name,AVG(A.s_score)
FROM Score A,Student B
WHERE A.s_id=B.s_id
GROUP BY A.s_id,B.s_name
HAVING AVG(A.s_score)>=60;

-- 4、查询平均成绩小于60分的同学的学生编号和学生姓名和平均成绩(包括有成绩的和无成绩的)
SELECT A.s_id,B.s_name,AVG(A.s_score)
FROM Score A,Student B
WHERE A.s_id=B.s_id
GROUP BY A.s_id,B.s_name
HAVING AVG(A.s_score)<60
or AVG(A.s_score) is null;

-- 5、查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩
SELECT E.s_id,E.s_name,COUNT(C.c_id)OVER(PARTITION BY C.s_id,E.s_name),
SUM(C.s_score)OVER(PARTITION BY C.s_id,C.c_id)
FROM Student E,Score C
WHERE E.s_id=C.s_id;

-- 6、查询"李"姓老师的数量
SELECT COUNT(t_id)
FROM Teacher
WHERE t_name LIKE '李%';

-- 7、查询学过"张三"老师授课的同学的信息
SELECT S.*
FROM Student S,Score  C
WHERE S.s_id=C.s_id AND C.c_id IN
(SELECT E.c_id FROM Course E,Teacher T
WHERE E.c_name=T.t_name AND T.t_name='张三');

-- 8、查询没学过"张三"老师授课的同学的信息

SELECT  S.*
FROM Student S WHERE NOT EXISTS
(SELECT s_id  FROM Score X,Course C,Teacher T
WHERE X.c_id=C.c_id AND C.t_id=T.t_id AND T.t_name='张三' AND S.s_id=X.s_id)

-- 9、查询学过编号为"01"并且也学过编号为"02"的课程的同学的信息
SELECT DISTINCT S.* FROM Score C,Student S
WHERE S.S_ID=C.S_ID AND C_ID=1
UNION
SELECT DISTINCT S.* FROM Score C,Student S
WHERE S.S_ID=C.S_ID AND C_ID=2

-- 10、查询学过编号为"01"但是没有学过编号为"02"的课程的同学的信息
SELECT DISTINCT S.* FROM Score C,Student S
WHERE S.S_ID=C.S_ID AND C_ID=1
UNION
SELECT  DISTINCT S.* FROM Student S
WHERE NOT EXISTS
(SELECT s_id  FROM Score C WHERE C_ID=2  AND S.s_id=C.s_id)

-- 11、查询没有学全所有课程的同学的信息
SELECT S.* FROM Student S,Score C
WHERE S.S_ID=C.S_ID
GROUP BY S.S_ID,S.s_name
HAVING COUNT(C.C_ID)!=(SELECT COUNT(C_ID)FROM COURSE);

-- 12、查询至少有一门课与学号为"01"的同学所学相同的同学的信息
SELECT DISTINCT S.* FROM STUDENT S,SCORE C
WHERE S.S_ID!=1 AND C.C_ID  IN
(SELECT C_ID FROM SCORE WHERE S_ID=1);

-- 13、查询和"01"号的同学学习的课程完全相同的其他同学的信息
SELECT DISTINCT S.*
FROM STUDENT S,SCORE C
WHERE S.S_ID=C.S_ID AND C.S_ID!=1  
GROUP BY S.S_ID,S.S_NAME
HAVING COUNT( C.C_ID)=
(SELECT COUNT(C_ID) FROM SCORE WHERE S_ID=1);

-- 14、查询没学过"张三"老师讲授的任一门课程的学生姓名
SELECT S_ID,S_NAME FROM STUDENT WHERE S_ID NOT IN
(SELECT C.S_ID
FROM SCORE C,COURSE E,TEACHER T
WHERE  C.C_ID=E.C_ID AND E.T_ID=T.T_ID AND T.T_NAME='张三' );

-- 15、查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩
select s_name,avg(s_score)
from Student S,Score sc
where s.s_id=sc.s_id and s_score<60
group by s_name
having count(s_score)>=2

-- 16、检索"01"课程分数小于60，按分数降序排列的学生信息
SELECT S_ID FROM SCORE
WHERE C_ID=1 AND S_SCORE<60
ORDER BY S_SCORE DESC;

-- 17、按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩
select distinct A.c_id,c_name,最高分,最低分,平均分,及格率,中等率,优良率,优秀率
from Score A
left join Course on A.c_id=Course.c_id
left join (select c_id,MAX(s_score) 最高分,
MIN(s_score) 最低分,AVG(s_score) 平均分
from Score  group by c_id)B  on A.c_id=B.c_id
left join (select c_id,(convert(decimal(5,2),
(sum(case when s_score>=60  then 1 else 0 end)*1.00)/COUNT(*))*100) 及格率
from Score group by c_id)C  on A.c_id=C.c_id
left join (select c_id,(convert(decimal(5,2),
(sum(case when s_score >=70 and s_score<80 then 1 else 0 end)*1.00)/COUNT(*))*100) 中等率
from Score group by c_id)D on A.c_id=D.c_id
left join (select c_id,(convert(decimal(5,2),
(sum(case when s_score >=80 and s_score<90 then 1 else 0 end)*1.00)/COUNT(*))*100) 优良率
from Score group by c_id)E  on A.c_id=E.c_id
left join (select c_id,(convert(decimal(5,2),
(sum(case when s_score >=90 then 1 else 0 end)*1.00)/COUNT(*))*100) 优秀率
 from Score group by c_id)F  on A.c_id=F.c_id

-- 18、查询各科成绩最高分、最低分和平均分：以如下形式显示：课程ID，课程name，最高分，最低分，平均分，及格率，中等率，优良率，优秀率及格为>=60，中等为：70-80，优良为：80-90，优秀为：>=90
select *,RANK()over(order by s_score desc)排名 from Score

-- 19、查询下月过生日的学生           (sql server)
select s.* from Student s
where to_char(s.s_birth, 'mm')=to_char(add_months(trunc(sysdate),1),'mm')

-- 20、查询本月过生日的学生            (sql server)
select s.* from Student s
 where to_char(s.s_birth, 'mm') = to_char(sysdate,'mm')

-- 21、查询下周过生日的学生            (sql server)
select s.* from student s
 where to_char(s.s_birth, 'mmdd') between
to_char(trunc(sysdate, 'iw')+7, 'mmdd') and       
to_char(trunc(sysdate, 'iw') + 13, 'mmdd')

-- 22、查询本周过生日的学生            (sql server)
select st.*  from student s
 where to_char(s.s_birth, 'mmdd')  between
to_char(trunc(sysdate, 'iw'), 'mmdd') and
to_char(trunc(sysdate, 'iw') + 6, 'mmdd')

-- 23、查询各学生的年龄,按照出生日期来算，当前月日 < 出生年月的月日则，年龄减一        (sql server)
select s.*, (to_char(sysdate, 'yyyy') - to_char(s.s_birth, 'yyyy')) "年龄"  from student s

-- 24、查询选修了全部课程的学生信息
SELECT S.* FROM STUDENT S,SCORE C
WHERE S.S_ID=C.S_ID
GROUP BY S.S_ID,S.s_name
HAVING COUNT(C.C_ID) IN (SELECT COUNT(C_ID)FROM COURSE);

-- 25、检索至少选修两门课程的学生学号
SELECT S_ID,COUNT(C_ID)FROM SCORE GROUP BY S_ID HAVING COUNT(C_ID)>2;

-- 26、查询每门功成绩最好的前两名
select c_id,COUNT(s_id)选修人数 from Score
group by c_id
having COUNT(s_id)>5
order by 选修人数 desc,c_id

-- 27、查询选修"张三"老师所授课程的学生中，成绩最高的学生信息及其成绩
SELECT S.S_ID,C.S_SCORE
FROM STUDENT S,SCORE C,COURSE E,TEACHER T
WHERE S.S_ID=C.S_ID AND C.C_ID=E.C_ID AND E.T_ID=T.T_ID AND T.T_NAME='张三'
AND C.S_SCORE=(SELECT MAX(S_SCORE)FROM SCORE WHERE C_ID=C.C_ID );

-- 28、查询所有学生的课程及分数情况

select a.s_id,a.s_name,b.s_id,c.c_name,b.s_score
         from student a,Score b,Course c
         where a.s_id=b.s_id and b.c_id=c.c_id;

-- 29、查询平均成绩大于等于85的所有学生的学号、姓名和平均成绩
SELECT S.S_ID,S_NAME,AVG(S_SCORE) FROM STUDENT S,SCORE C
WHERE S.S_ID=C.S_ID GROUP BY S.S_ID,S_NAME
HAVING AVG(S_SCORE)>=85;

-- 30、查询同名同性学生名单，并统计同名人数
select s_name,count(s_name) from student
 group by s_name having count(s_name)>1 ;
```

