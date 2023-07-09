1、从hbase中获取数据
    hive跟hbase做整合，使用外部表
    在hive中创建跟hbase的整合表
2、在hive中创建最终结果表（跟mysql中存储的结果表一致）

3、分析需求计算结果
    计算用户的浏览深度 （在某一个时问段内（会话）用户连续访问了至少个页面)
        两个角度米分析浏览深度：
            会话：
            用户：

![image-20230707195104038](assets/hive%E7%BB%9F%E8%AE%A1%E7%94%A8%E6%88%B7%E6%B5%8F%E8%A7%88%E6%B7%B1%E5%BA%A6%E5%B9%B6%E6%98%A0%E5%B0%84%E5%88%B0mysql/media/image-20230707195104038.png)

参考文件： [statsViewDepth.hql](statsViewDepth.hql) 

```sql
select pl,s_time, case count (p_url) when 1 then pv1 end when 2 then pv2 ..， from event 1ogs
where
    en=e_pv and
    s_time>=2019-08-20 and
    s_time<=2019-08-21 and
    p_url is not null
group by

一条sql语句无法得到最终结果，可以使用中问表，需要使用行转列
```







