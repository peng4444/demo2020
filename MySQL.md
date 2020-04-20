# MySQL学习总结

## [1.手把手教你分析Mysql死锁问题](https://www.cnblogs.com/jay-huaxiao/p/12685287.html)
```mysql
 select @@tx_isolation;  # 查看数据库隔离级别
 set autocommit=0;  # 自动提交关闭
 select * from information_schema.innodb_locks; # 查看锁情况
 show engine innodb status; # 查看最近一次死锁日志
```
### 1.1共享锁与排他锁
```markdown
InnoDB 实现了标准的行级锁，包括两种：共享锁（简称 s 锁）、排它锁（简称 x 锁）。
共享锁（S锁）：允许持锁事务读取一行。
排他锁（X锁）：允许持锁事务更新或者删除一行。
```
### 1.2 意向锁
```markdown
意向共享锁( IS 锁)：事务想要获得一张表中某几行的共享锁
意向排他锁( IX 锁)： 事务想要获得一张表中某几行的排他锁
```
### 1.3 记录锁（Record Locks）
```markdown
记录锁是最简单的行锁，仅仅锁住一行。如：SELECT c1 FROM t WHERE c1 = 10 FOR UPDATE
记录锁永远都是加在索引上的，即使一个表没有索引，InnoDB也会隐式的创建一个索引，并使用这个索引实施记录锁。
会阻塞其他事务对其插入、更新、删除
```
### 1.4 间隙锁（Gap Locks）
```markdown
间隙锁是一种加在两个索引之间的锁，或者加在第一个索引之前，或最后一个索引之后的间隙。
使用间隙锁锁住的是一个区间，而不仅仅是这个区间中的每一条数据。
间隙锁只阻止其他事务插入到间隙中，他们不阻止其他事务在同一个间隙上获得间隙锁，所以 gap x lock 和 gap s lock 有相同的作用。
```
### 1.5 Next-Key Locks
```markdown
Next-key锁是记录锁和间隙锁的组合，它指的是加在某条记录以及这条记录前面间隙上的锁。
```
### 1.6插入意向锁（Insert Intention）
```markdown
插入意向锁是在插入一行记录操作之前设置的一种间隙锁，这个锁释放了一种插入方式的信号，
亦即多个事务在相同的索引间隙插入时如果不是插入间隙中相同的位置就不需要互相等待。
```

## [2.一文彻底读懂MySQL事务的四大隔离级别](https://www.cnblogs.com/jay-huaxiao/p/12639435.html)

## [3.Mysql面试题及千万级数据查询优化](https://www.cnblogs.com/lyn20141231/p/11742042.html)

## [4.数据库优化 - SQL优化](https://www.cnblogs.com/lyn20141231/p/11742042.html)

## [5.不就是SELECT COUNT语句吗，竟然能被面试官虐的体无完肤](https://www.cnblogs.com/hollischuang/p/11711778.html)
