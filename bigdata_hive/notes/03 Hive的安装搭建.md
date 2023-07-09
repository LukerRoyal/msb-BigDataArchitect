# 03 Hive的安装搭建

Hive可以从源码中编译安装，也可以直接使用官网下载的安装包，在此处我们选择安装包解压安装的方式。

**Hive中最最重要的角色就是metastore**

因此按照metastore的管理共有四种hive的安装搭建方式：官网参考地址如下：

https://cwiki.apache.org/confluence/display/Hive/AdminManual+Metastore+Administration

##### Hive安装分类：

​	1、Local/Embedded Metastore Database（Derby）

​	2、Remote Metastore Database

​	3、Local/Embedded Metastore Server

​	4、Remote Metastore Server

​	根据上述分类，可以简单归纳为以下三类

​	1、使用Hive自带的内存数据库Derby作为元数据存储

​	2、使用远程数据库mysql作为元数据存储

​	3、使用本地/远程元数据服务模式安装Hive

##### 详细操作：

​	1、使用Hive自带的内存数据库Derby作为元数据存储

![1563262098436](https://github.com/msbbigdata/hive/blob/master/images/本地数据库模式安装.png)

<img src="assets/03%20Hive%E7%9A%84%E5%AE%89%E8%A3%85%E6%90%AD%E5%BB%BA/media/%E6%9C%AC%E5%9C%B0%E6%95%B0%E6%8D%AE%E5%BA%93%E6%A8%A1%E5%BC%8F%E5%AE%89%E8%A3%85.png" alt="本地数据库模式安装.png" style="zoom:50%;" />

​	2、使用远程数据库mysql作为元数据存储

![1563262117701](https://github.com/msbbigdata/hive/blob/master/images/远程数据库模式安装.png))

<img src="assets/03%20Hive%E7%9A%84%E5%AE%89%E8%A3%85%E6%90%AD%E5%BB%BA/media/%E8%BF%9C%E7%A8%8B%E6%95%B0%E6%8D%AE%E5%BA%93%E6%A8%A1%E5%BC%8F%E5%AE%89%E8%A3%85-20230704192111393.png" alt="远程数据库模式安装.png" style="zoom:50%;" />

​	3、使用本地/远程元数据服务模式安装Hive

![1563262138267](https://github.com/msbbigdata/hive/blob/master/images/远程元数据服务安装.png)

<img src="assets/03%20Hive%E7%9A%84%E5%AE%89%E8%A3%85%E6%90%AD%E5%BB%BA/media/%E8%BF%9C%E7%A8%8B%E5%85%83%E6%95%B0%E6%8D%AE%E6%9C%8D%E5%8A%A1%E5%AE%89%E8%A3%85.png" alt="远程元数据服务安装.png" style="zoom:50%;" />