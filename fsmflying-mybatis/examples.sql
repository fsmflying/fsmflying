/*for sqlserver */

create table students(id int primary key identity not null,name varchar(128) not null,sex int not null default 0,birthDate date not null);
create table teachers(id int primary key identity not null,name varchar(128) not null,sex int not null default 0,birthDate date not null);
create table subjects(id int primary key identity not null,name varchar(128) not null,memo varchar(512));
create table courses(id int primary key identity not null,name varchar(128) not null,subjectId int not null,teacherId int not null,memo varchar(512));


insert into subjects(name,memo) values('微积分','微积分（Calculus）是高等数学中研究函数的微分（Differentiation）、积分(Integration)以及有关概念和应用的数学分支。');
insert into subjects(name,memo) values('线性代数','线性代数是数学的一个分支，它的研究对象是向量，向量空间（或称线性空间），线性变换和有限维的线性方程组');
insert into subjects(name,memo) values('离散数学','离散数学(Discrete mathematics)是研究离散量的结构及其相互关系的数学学科，是现代数学的一个重要分支。');                                               
insert into subjects(name,memo) values('微机原理','《微机原理》是一门专业基础课程，它的主要内容包括微型计算机体系结构、8086微处理器和指令系统、汇编语言设计以及微型计算机各个组成部分介绍等内容。');
insert into subjects(name,memo) values('C语言','C语言是一门通用计算机编程语言，应用广泛。C语言的设计目标是提供一种能以简易的方式编译、处理低级存储器、产生少量的机器码以及不需要任何运行环境支持便能运行的编程语言。'); 
insert into subjects(name,memo) values('计算机操作系统','介绍了现代计算机操作系统的基本概念、原理和实现方法。');
insert into subjects(name,memo) values('数据结构','数据结构是计算机存储、组织数据的方式。数据结构是指相互之间存在一种或多种特定关系的数据元素的集合。');                           
insert into subjects(name,memo) values('数据库系统原理','数据库系统原理');
insert into subjects(name,memo) values('软件工程','软件工程');                                             
insert into subjects(name,memo) values('计算机网络','计算机网络');


/*for mysql*/

create table students(
	id int primary key auto_increment not null,
	name varchar(128) not null,
	sex int not null default 0,
	birthDate date not null
);
create table teachers(id int primary key auto_increment not null,name varchar(128) not null,sex int not null default 0,birthDate date not null);
create table subjects(id int primary key auto_increment not null,name varchar(128) not null,memo varchar(512));
create table courses(id int primary key auto_increment not null,name varchar(128) not null,subjectId int not null,teacherId int not null,memo varchar(512));




