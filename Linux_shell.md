# Linux Shell

[TOC]


## 1.Shell能做什么
![1551238715944](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551238715944.png)
```
#！/usr/bin/bash    # #!指定shell的解析器，只能放在第一行
ping -c1 10.18.42.127 &>/dev/null && echo "10.18.42.127 is up!" || echo "10.18.42.127 is down!"
							&>/dev/null 重定向运行过程，没有脚本运行过程，直接输出结果
/usr/bin/python <<-EOF		#重定向执行，在bash里面可以执行Python demo
print "python hello world!"
EOF

echo "hello bash"
```
![1551239311478](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551239311478.png)
```
在当前shell中执行和在子shell中执行结果不同
常规是子shell中执行
```
![1551247309985](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551247309985.png)
#### Shell特性
```
login shell   su - pbj    文件执行： /etc/profile /etc/bashrc ~/.bash_profile ~/.bashrc
nologin shell        su pbj    文件执行： /etc/bashrc  ~/.bashrc
```
![1551248388863](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551248388863.png)

![1551248449498](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551248449498.png)

![1551248474494](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551248474494.png)
```
； 无论前一个是否成功，后一个都会执行
&& 前一个demo执行成功，后一个才执行
|| 前一个执行失败，后一个执行
```
![1551249237074](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551249237074.png)
```
echo ： 带颜色的输出       31~37 字体颜色，41~47 背景颜色
print： 格式化的输出
```
![1551249452056](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551249452056.png)
## 2.Shell 变量
```
1.自定义变量（仅仅当前shell）
a=10
echo $a        或者 echo ${a}
从终端输入变量的值： read ip
				  read -p "Please input a ip: " ip  #-p加提示
				  read -t 5 -p "提示信息：" 变量名      #延迟时间
				  read -n 2 变量名             ##两个字符
				  read a b c d          #读取多个变量
		'' 强引用 ：没有变量
		“” 弱引用： 可以有变量
2.环境变量（当前shell和子shell）
export 。。。
env : 查看环境变量
```

```
变量位置 $1
#!/usr/bin/bash
set one two three
echo $1
echo "$*"
echo "$@"
echo "$#"
one
one two three
one two three
3
```
![1551255181928](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551255181928.png)
```
整数运算
1.expr    expr 1+2    expr 1\*2
2.$(())   $((1+2))    $((1*2))
3.$[]
4.let
```

```
变量删除和替换
url=www.baidu.com
echo ${url}
echo ${url#*.}      #从前到后删除
echo ${url##*.}
echo ${url%*.}		%从后往前删除
echo ${url%%*.}    
索引切片
echo ${url:0:5}
echo ${url:5:5}
echo ${url:5}
变量替换
	${变量名-新变量值}
```
![1551257318527](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551257318527.png)

![1551257515473](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551257515473.png)

## 3条件测试

```
test -d  测试文件类型和数值比较	test -d /home    测试文件为目录，加！取返 ！test -d /home
[   ]							[ ! -d $back_dir ] 
[ [ ] ]							支持正则表达式
man test
```

![1551321888268](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551321888268.png)

```
条件测试：内存磁盘使用警告 mem_use.sh
chmod u+x mem_use.sh
bash -vx mem_use.sh   调试测试的执行方式
```

![1551322376281](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551322376281.png)

```
字符串比较： [ $USER = root ]; echo $?     0 真 1 假
			[ -z "" ]; echo $?    字符串长度为0  变量也要加 " "
			[ -n "" ]; echo $?    字符串长度不为0   变量为空或者未定义都为0
case ：替代多个 if else
```

![ 1551323195047](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551323195047.png)

```
case删除用户
```

![1551323355934](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551323355934.png)

## 4.if for流程控制

```
安装Apache测试 vi install_apache.sh
#!/usr/bin/bash
```

![1551332383198](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551332383198.png)

```
if条件判断，多分枝结构，多系统配置yum源
vi yum_config.sh
```

![1551332845371](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551332845371.png)

 ![1551332881869](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551332881869.png)

![1551332964859](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551332964859.png)

```
for空行：不会影响参数个数，当成不存在或者空格
```

```
for循环实现批量主机ping测试
vi pinghost.sh
time ./pinghost.sh 查看脚本运行的时间
```

![1551333752271](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551333752271.png)

```
for实现批量用户创建 vi create_user.sh
```

![1551334358603](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551334358603.png)

![1551334456123](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551334456123.png)

```
for实现文件中的用户批量创建
aaa1   123
bbb2   321
ccc3   213
```



![1551334894894](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551334894894.png)

![1551335459332](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551335459332.png)

```
空行跳过 if[ ${#line} -eq 0 ];then
				echo "Nothing to do"
				continue
		fi
```

![1551334936162](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551334936162.png)

```
for实现批量主机密码修改
vi modify_passwd.sh
```

![1551335780421](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551335780421.png)

![1551335845489](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551335845489.png)

## 5.非交互式Expect

```
Expect实现scp非交互传输文件
vi expect_scp01.sh
```

![1551336279550](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551336279550.png)

```
ecpect实现批量主机公钥推送
vi get_ip.sh
```

![1551336747647](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551336747647.png)

![1551336786828](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551336786828.png)

```
expect实现ssh非交互登录
vi expect_ssh.sh
```

![1551337090454](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551337090454.png)

##  6.shell数组变量

![1551259012875](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551259012875.png)

```
查看数组 declare -a
```

![1551259145663](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551259145663.png)

![1551259402195](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551259402195.png)

```
统计男女数量
awk '{print $2}' sex.txt |sort |uniq -C
数组方式：
	#！/usr/bin/bash
	#count sex
	#v1.0 by pbj
	while read line
	do 
		type='echo $line |awk '{print $2}'
		let sex[$type]++
	done < sex.txt
	
	for i in ${!sex[@]}
	do
		echo "$i: ${sex[$i]}"
	done
```

```
统计不同类型的shell的数量
```

![1551260000194](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551260000194.png)

![1551260040309](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551260040309.png)

```
统计连接状态数量
```

![1551260318985](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551260318985.png) 

## 7.shell Function函数

```
传参： $1 $2
变量 local
返回值 return $?
```

![1551340367689](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551340367689.png)

```
[pbj@bjpeng02 ~]$ vi function_factorial.sh 
#!/bin/bash
#function
function function_factorial (){
        factorial=1
        for((i=1;i<=5;i++))                      i<=$1
        #for((i=1;i<=$1;i++))  ==> for i in `seq $1`
        do
                factorial=$[$factorial * $i]   #等效于 let factorial=$factoral*$i
        done
        echo "5!= $factorial"
}

function_factorial                                function_factorial 5  function_factorial $1          #函数内的$1 和函数外面的$1不同

[pbj@bjpeng02 ~]$ ./function_factorial.sh 
5!= 120
[pbj@bjpeng02 ~]$ ./function_factorial.sh  10
5!= 120

function_factorial $1
function_factorial $2
function_factorial $3

[pbj@bjpeng02 ~]$ ./function_factorial.sh  5 10 20
5!= 120
10！
20！
```

```
return :shell返回码 （0~255）不能输出函数返回值
```

![1551342304430](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551342304430.png)

![1551342388912](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551342388912.png)

```
函数传参 位置参数，数组变量
```

![1551342977831](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551342977831.png)

```
函数返回 输出数组变量
```

![1551343221457](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551343221457.png)

## 8.shell内置命令

```
true false exit 
break continue                 break 2 跳出两层循环
： 
shift 使位置参数左移
```

![1551343522167](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551343522167.png)

## 9.shell正则表达式

![1551345241459](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551345241459.png)

![1551345333953](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551345333953.png)

![1551345457032](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551345457032.png)

```
grep 'root' /etc/passwd
```

![1551346684380](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551346684380.png)

```
grep
```

![1551346852462](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551346852462.png)

![1551346903040](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551346903040.png)

![1551346946125](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551346946125.png)

![1551347015855](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551347015855.png)

## 10.sed流编辑器

![1551359334238](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551359334238.png)

```
sed -r 's/root/pbj/' passwd  #在passwd文件中查找root替换为pbj 一行替换一个
sed -r 's/root/pbj/g passwd  #在passwd文件中查找root替换为pbj g一行全部替换
sed -r 's/root/pbj/gi passwd 							   i忽略大小写
```

![1551360044130](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551360044130.png)

```
sed -r '3d' /etc/passwd 删除第三行
sed -r '1,3d' /etc/passwd 删除第一到第三行
sed -r '/root/,5d' /etc/passwd 删除从root行开始的五行，可能重复匹配
```

![1551360374527](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551360374527.png)

![1551360435959](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551360435959.png)

![1551360499660](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551360499660.png)

![1551360522830](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551360522830.png)

```
sed -r 's/(.*)/#\1' passwd 在PASSWD文件的所有的行前面加上# 
sed -r '1,5s/(.*)/#\1' passwd 在PASSWD文件的1到5行前面加上# 
sed -r '/root/s/(.*)/#\1' passwd 在PASSWD文件的有root的行前面加上# 

sed -r 's/(.)(.)(.*)/\1yyy\2\3/' passwd 在第二个字母的前面加上yyy
sed -r 's/(.*)(.)/\1yyy\2/' passwd 在最后一个字母的前面加上yyy
```

```
sed -r '/lp/r /ect/hosts' passwd 读passwd文件读到lp时去读取/etc/hosts文件
sed -r '2r/ect/hosts' passwd 读passwd文件读到第二行时去读取/etc/hosts文件
sed -r '/2/r /ect/hosts' passwd 读passwd文件读到有2的行时去读取/etc/hosts文件
```

```
sed -r '/root/w /tmp/1.txt' passwd 读passwd文件是读到有root的行写到/tmp/1.txrt文件中去
sed -r '3,$w' /file.txt passwd 读取passwd文件的3到最后一行保存到file.txt
sed -r '2a1111111' passwd 读passwd文件第二行后面追加111111
sed -r '2i1111111' passwd 读passwd文件第二行前面追加111111
sed -r '2c1111111' passwd 读passwd文件第二行用111111替换
g h ：覆盖 G H ：追加（每一行后面追加一行空行）
```

![1551361868857](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551361868857.png)

![1551409622330](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551409622330.png)

```

```

![1551409781736](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551409781736.png)

![1551409985575](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551409985575.png)

```
 
```

![1551410252336](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551410252336.png)

```
a.txt
1
2
3
4
5
# sed -r '1!G; $!h; $!d' a.txt         tac a.txt
5
4
3
2
1
```

## 11.awk文本处理工具

![1551410666721](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551410666721.png)

```
awk -F":" '{print $1}' passwd   指定：分割文件，输出第一个
awk 'BEGIN{FS=":"} {print $1,$2}' passwd    FS 字段分隔符
awk 'BEGIN{FS=":";OFS="---"} {print $1,$2}' passwd OFS字段输出分隔符
RS：记录分隔符
```

![1551410745316](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551410745316.png)

![1551411377536](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551411377536.png)

![1551411428137](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551411428137.png)

```
ORS默认输出一条记录一个回车，加了一个空格
格式化输出：print函数
		  printf函数 格式化输出
```

![1551419748050](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551419748050.png)

```
awk模式：
$0 整行 $1 第一个字符
~ ！~ 匹配操作符
比较表达式，条件表达式，算术运算，逻辑运算，范围运算
```

![1551420517034](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551420517034.png)

```
awk脚本编程: if while for 
vi b.txt
111 222
333 444 555
666 777 888 999
awk '{i=1;while(i<=NF){print $i;i++}}' b.txt
awk 'BGEIN{for(i=1;i<=NF;i++){print $i}}' b.txt
awk数组；
awk -F: '{username[i++]=$1} END{print username[0]}' /etc/passwd
数组遍历：按索引遍历
awk -F:'{username[x++]=$1} END{for(i in username) {print i,username[i]} }' /etc/passwd
```

 ![1551422444817](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551422444817.png)

![1551422467696](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551422467696.png)

![1551422512185](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551422512185.png)

![1551422594006](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551422594006.png)

![1551422672406](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551422672406.png)

```
将需要统计的内容（某一个字段）作为数组的索引++
```

![1551422854607](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551422854607.png)

![1551422902299](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551422902299.png)

```
awk使用外部变量
```

![1551423044293](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551423044293.png)

## 12.项目实战：分析系统资源性能瓶颈

![1551423910930](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551423910930.png)

![1551429819860](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551429819860.png)

​	![1551429958208](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551429958208.png)

![1551430051223](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551430051223.png)

![1551430205248](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551430205248.png)

![1551430296177](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551430390156.png)

![1551430479525](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551430479525.png)

![1551430579132](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551430579132.png)

![1551430759944](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551430759944.png)

![1551430818233](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551430818233.png)

![1551430890961](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551430890961.png)

![1551430957361](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551430957361.png)

![1551431059445](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551431059445.png)

![1551431168608](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551431168608.png)

![1551431238809](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551431238809.png)

![1551431325654](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551431325654.png)

![1551431383468](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551431383468.png)

## 13.项目实战：判断主机存活三次机会

```
vi ping_count3_1.sh
```

![1551431637368](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551431637368.png)

![1551431696236](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551431696236.png)

```
vi ip.txt
192.168.1.1
10.44.23.11
chmod u+x ip.txt
vi ping_count3_2.sh
```

![1551431939383](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551431939383.png)

![1551431980843](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551431980843.png)

```
vi ping_count3_3.sh
```

![1551432192713](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1551432192713.png)

## 14.项目实战：Nginx日志分析项目

```
yum -y install nginx
```

```

```

```

```

```

```

