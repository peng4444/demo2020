# MongoDB基础
[TOC]

## JSON
```markdown
JSON
	- JSON就是一种数据交换的格式
	- JSON就是一个字符串，通过JSON可以表示不同的语言的对象，
		并且该字符串可以转换为不同语言中的对象
	- JavaScript Object Notation（JavaScript对象表示法）	
	- JSON的规范：
		1.JSON是一个字符串
		2.JSON中的属性名必须使用双引号引起来
	- JSON的两种格式
		JSON对象 {}
		JSON数组 []
	- JSON中可以保存的数据的类型
		Number 数值
		String 字符串
		Boolean 布尔值
		null 空值
		Object 对象
		Array 数组
		{"name":"孙悟空","age":18,"gender":"男"}
		[123,true,"hello"]
```
## MongoDB
### MongoDB安装
```markdown
步骤：
	1.安装mongodb
	2.配置环境变量
		C:\Program Files\MongoDB\Server\3.2\bin
	3.在c盘根目录创建一个文件夹data/db
	4.打开命令行窗口，输入mongod来启动mongodb服务器
	5.打开一个新的命令行窗口，输入mongo启动客户端
将MongoDB设置系统服务
	1.在c盘根目录创建如下文件夹
		data/db
		data/log
	2.创建配置文件
		C:\Program Files\MongoDB\Server\3.2
		- 在以上目录创建 mongod.cfg
	3.以管理员的身份打开命令行窗口	
	4.在窗口中执行以下命令
		sc.exe create MongoDB binPath= "\"C:\Program Files\MongoDB\Server\3.4\bin\mongod.exe\" --service --config=\"C:\Program Files\MongoDB\Server\3.4\mongod.cfg\"" DisplayName= "MongoDB" start= "auto"
		sc.exe create MongoDB binPath= "\"mongo的bin目录\mongod.exe\" --service --config=\"mongo安装目录\mongod.cfg\"" DisplayName= "MongoDB" start= "auto"
		sc.exe create MongoDB binPath= "\"C:\Program Files\MongoDB\Server\3.2\bin\mongod.exe\" --service --config=\"C:\Program Files\MongoDB\Server\3.2\mongod.cfg\"" DisplayName= "MongoDB" start= "auto"
	5.启动MongoDB的服务	
	if(不行)
	6.执行sc delete MongoDB
	7.从1开始再来一遍
```
### MongoDB的基本概念
```markdown
	数据库 database
		- 一个服务器中可以有多个数据库
		- 数据库用来保存集合
	集合 collection
		- 一个数据库中可以有多个集合
		- 集合用来保存文档
	文档 document	
		- 一个集合中有多个文档
		- 文档就是我们操作的数据
		- 文档实际上就是一个JSON，但是是增强版的JSON，称为BSON
	- 在MongoDB中，数据库和集合都不需要创建，数据库和集合会在第一次插入文档时创建
	- 基本的指令
		show dbs 
			- 显示所有的数据库
		use 数据库名
			- 进入到指定的数据库中
		db
			- 表示当前数据库
		show collections
			- 显示当前数据库的所有集合
		db.<collection>.insert(doc(s))
			- 向指定的集合插入文档
			- db.users.insert({username:"sunwukong",age:18,gender:"male"})
		db.<collection>.find()
			- 查询当前集合中的所有文档
			- db.users.find()
		db.<collection>.count()
			- 统计集合中文档的数量
```
### MongoDB命令操作
```markdown
//1.进入my_test数据库
use my_test
//2.向数据库的user集合中插入一个文档
db.user.insert({username:"sunwukong"})
db.collection.insert()
   --向集合中插入一个或者多个文档
   db.user.insert(){[
       {username:"aaa"},
       {username:"bbb"},
       {username:"ccc"}
   ]};
//3.查询user集合中的文档
	db.user.find()
//4.向数据库的user集合中插入一个文档		
	db.collection.insertOne()  --向集合中插入一个文档，该方法只能传递一个docume对象
	db.collection.insertMany() --向集合中插入多个文档，该方法只能接受一个数组作为参数
//5.查询数据库user集合中的文档
	db.user.find();
	db.user.find({username:"aaa",age:"18"});  --返回一个数组
	db.user.findOne();   --查询指定集合中符合条件的第一个文档 ，返回的是一个文档
//6.统计数据库user集合中的文档数量
	db.user.count();
//7.查询数据库user集合中username为sunwukong的文档
	db.user.find({username:"sunwukong"})
//8.向数据库user集合中的username为sunwukong的文档，添加一个address属性，属性值为huaguoshan
	db.user.update({username:"sunwukong"},{address:"huagoushan"}) --update() 默认是替换
	$set 添加员工属性
	db.user.update({username:"sunwukong"},{$set:{address:"huaguoshan"}}); 
//9.使用{username:"tangseng"} 替换 username 为 zhubajie的文档
	db.user.update({username:"zhubajie"},{username:"tangseng"});
//10.删除username为sunwukong的文档的address属性
	db.user.update({username:"sunwukong"},{$unset:{address:1}})
//11.向username为sunwukong的文档中，添加一个hobby:{cities:["beijing","shanghai","shenzhen"] , movies:["sanguo","hero"]}
	db.update（{username:"sunwukong"},{$set.{hobby:{cities:	["beijing","shanghai","shenzhen"] , movies:["sanguo","hero"]}}}）;
	db.user.find();
//12.向username为tangseng的文档中，添加一个hobby:{movies:["A Chinese Odyssey","King of comedy"]};
	db.user.update({username:"tangseng"},{$set{hobby:{movies:["A Chinese Odyssey","King of comedy"]}}});
//13.查询喜欢电影hero的文档
要匹配内嵌文档的属性，通过.的方式进行查询，属性名必须加引号
	db.user.find({"hobby.movies":"hero"});
//14.向tangseng中添加一个新的电影Interstellar  $push 可以向数组中添加员工元素
											￥addToSet 用来向数组中添加员工不存在的元素
	db.user.update({username:"tangseng"},{$push:{"hobby.movies":"Interstellar"}});
//15.删除喜欢beijing的用户
	db.user.remove({“hobby.cities":"beijing"});
	db.user.remvoe(); --删除所有的元素 慎用
//16.删除user集合
	db.user.drop();  --删除集合，如果数据库中只有一个集合，则数据库也被删除
	db.dropDatabase(); --删除数据库
db.collection.updateOne(); --修改一个
db.collection.updateMany(); --修改多个
db.collection.replaceOne(); --替换一个
db.collection.update(); --修改或者替换一个或者多个
//17.向numbers中插入20000条数据
for(var i= 1; i<=20000 ; i++){
    db,numbers.insert({num:i});
}
db.numbers.find(); db.numbers.drop();
//创建一个数组，用来保存文档
var arr= [];
	for(var i= 1;i<=2000;i++){
        arr.push({num:i});
	}
	db.numbers.insert(arr);
//18.查询numbers中num为500的文档
	db.unmbers.find({num;500}); --包含500
	db.numbers.find({num:{$eq:500}});  查询操作符 $eq 查询指定的字段是否等于某一个值
//19.查询numbers中num大于5000的文档
	db.numbers.find({num:{$gt:5000}});  查询操作符 $gt 查询指定的字段是否大于某一个值 $gte大于等于
//20.查询numbers中num小于30的文档
	db.numbers.find({num:{$lt:30}});  查询操作符 $rt 查询指定的字段是否小某一个值 $lte小于等于
//21.查询numbers中num大于40小于50的文档
	db.numbers.find({num:{$gt:40 , $lt:50});
	db.numbers.find({$or:[{num:{$gt:19000}} ,{num:{$lt:50}}]});--大于19000，小于50
//22.查询numbers中num大于19996的文档
	db.numbers.find({num:{$gt:19996}});
//23.查看numbers集合中的前10条数据
	db.numbers.find({}).limit(10);  --limit（） 用来限制显示数据的最大的条数
//24.查看numbers集合中的第11条到20条数据
	db.numbers.find({}).limit(10)。skip（10）; --skip() 用来跳过10条
//25.查看numbers集合中的第21条到30条数据
	db.numbers.find({}).limit(10)。skip（20）;
```
