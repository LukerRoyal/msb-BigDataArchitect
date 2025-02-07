# 01 Hive的基本介绍

### 1、hive产生的原因

·	a) 方便对文件及数据的元数据进行管理，提供统一的元数据管理方式

​	 b) 提供更加简单的方式来访问大规模的数据集，使用SQL语言进行数据分析

### 2、hive是什么？

```
The Apache Hive ™ data warehouse software facilitates reading, writing, and managing large datasets residing in distributed storage using SQL. Structure can be projected onto data already in storage. A command line tool and JDBC driver are provided to connect users to Hive.
```

​		Hive经常被大数据企业用作企业级数据仓库。

​		Hive在使用过程中是使用SQL语句来进行数据分析，由SQL语句到具体的任务执行还需要经过解释器，编译器，优化器，执行器四部分才能完成。

​		（1）解释器：调用语法解释器和语义分析器将SQL语句转换成对应的可执行的java代码或者业务代码

​		（2）编译器：将对应的java代码转换成字节码文件或者jar包

​		（3）优化器：从SQL语句到java代码的解析转化过程中需要调用优化器，进行相关策略的优化，实现最优的								 查询性能

​		（4）执行器：当业务代码转换完成之后，需要上传到MapReduce的集群中执行

### 3、数据仓库--Hive（简单了解即可）	

#### 		1、数据仓库基本概念

​	    数据仓库，英文名称为Data Warehouse，可简写为DW或DWH。数据仓库，是为企业所有级别的决策制定过程，提供所有类型数据支持的战略集合。它是单个数据存储，出于分析性报告和决策支持目的而创建。 为需要业务智能的企业，提供指导业务流程改进、监视时间、成本、质量以及控制。

#### 		2、数据处理分类：OLAP与OLTP

​		数据处理大致可以分成两大类：联机事务处理OLTP（on-line transaction processing）、联机分析处理OLAP（On-Line Analytical Processing）。OLTP是传统的关系型数据库的主要应用，主要是基本的、日常的事务处理，例如银行交易。OLAP是数据仓库系统的主要应用，支持复杂的分析操作，侧重决策支持，并且提供直观易懂的查询结果。

#### 		3、OLTP

​		OLTP，也叫联机事务处理（Online Transaction Processing），表示事务性非常高的系统，一般都是高可用的在线系统，以小的事务以及小的查询为主，评估其系统的时候，一般看其每秒执行的Transaction以及Execute SQL的数量。在这样的系统中，单个数据库每秒处理的Transaction往往超过几百个，或者是几千个，Select 语句的执行量每秒几千甚至几万个。典型的OLTP系统有电子商务系统、银行、证券等，如美国eBay的业务数据库，就是很典型的OLTP数据库。

#### 		4、OLAP

​		OLAP（On-Line Analysis Processing）在线分析处理是一种共享多维信息的快速分析技术；OLAP利用多维数据库技术使用户从不同角度观察数据；OLAP用于支持复杂的分析操作，侧重于对管理人员的决策支持，可以满足分析人员快速、灵活地进行大数据复量的复杂查询的要求，并且以一种直观、易懂的形式呈现查询结果，辅助决策。

##### 		基本概念：

​			度量：数据度量的指标，数据的实际含义
​			维度：描述与业务主题相关的一组属性
​			事实：不同维度在某一取值下的度量

##### 		特点：

​			(1)快速性：用户对OLAP的快速反应能力有很高的要求。系统应能在5秒内对用户的大部分分析要求做出反								应。
​			(2)可分析性：OLAP系统应能处理与应用有关的任何逻辑分析和统计分析。

​			(3)多维性：多维性是OLAP的关键属性。系统必须提供对数据的多维视图和分析,包括对层次维和多重层次								维的完全支持。
​			(4)信息性：不论数据量有多大，也不管数据存储在何处，OLAP系统应能及时获得信息，并且管理大容量信								息。

##### 		分类：

​			按照存储方式分类：

​					ROLAP：关系型在线分析处理

​					MOLAP：多维在线分析处理

​					HOLAP：混合型在线分析处理

​			按照处理方式分类：

​					Server OLAP和Client OLAP

##### 		操作：

![OLAP](https://github.com/msbbigdata/hive/blob/master/images/OLAP.png)

![OLAP.png](assets/01%20Hive%E7%9A%84%E5%9F%BA%E6%9C%AC%E4%BB%8B%E7%BB%8D/media/OLAP.png)

​			钻取：在维的不同层次间的变化，从上层降到下一层，或者说将汇总数据拆分到更细节的数据，比如通过						对2019年第二季度的总销售数据进行钻取来查看2019年4,5,6,每个月的消费数据，再例如可以钻取						浙江省来查看杭州市、温州市、宁波市......这些城市的销售数据
​			上卷：钻取的逆操作，即从细粒度数据向更高汇总层的聚合，如将江苏省、上海市、浙江省的销售数据进						行汇总来查看江浙沪地区的销售数据
​			切片：选择维中特定的值进行分析，比如只选择电子产品的销售数据或者2019年第二季度的数据
​			切块：选择维中特定区间的数据或者某批特定值进行分析，比如选择2019年第一季度到第二季度的销售数						据或者是电子产品和日用品的销售数据
​			旋转：维的位置互换，就像是二维表的行列转换，比如通过旋转来实现产品维和地域维的互换		

### 4、数据库与数据仓库的区别

​		**注意：前三条重点掌握理解，后面的了解即可**				

​		1、数据库是对业务系统的支撑，性能要求高，相应的时间短，而数据仓库则对响应时间没有太多的要求，当然也是越快越好

​		2、数据库存储的是某一个产品线或者某个业务线的数据，数据仓库可以将多个数据源的数据经过统一的规则清洗之后进行集中统一管理

​		3、数据库中存储的数据可以修改，无法保存各个历史时刻的数据，数据仓库可以保存各个时间点的数据，形成时间拉链表，可以对各个历史时刻的数据做分析

​		4、数据库一次操作的数据量小，数据仓库操作的数据量大

​		5、数据库使用的是实体-关系（E-R）模型，数据仓库使用的是星型模型或者雪花模型

​		6、数据库是面向事务级别的操作，数据仓库是面向分析的操作