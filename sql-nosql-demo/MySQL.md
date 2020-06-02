# MySQL
## MySQL 基础

## MySQL 相关博客
[[制作mysql大数据表验证覆盖索引](https://www.cnblogs.com/codestory/p/11585161.html)]

### [MySQL如何有效的存储IP地址](https://www.cnblogs.com/mmzs/p/12992560.html)
```markdown
1.将 IP地址 以字符串的形式保存在数据库是完全没问题的。
2.将 IP地址 存成 int 型的数据。
3.数据库函数实现转换 就是通过数据库自带的函数 INET_ATON 和 INET_NTOA 进行转化：
    如果是IPv6地址的话，则使用函数INET6_ATON和INET6_NTOA进行转化。
```

##
