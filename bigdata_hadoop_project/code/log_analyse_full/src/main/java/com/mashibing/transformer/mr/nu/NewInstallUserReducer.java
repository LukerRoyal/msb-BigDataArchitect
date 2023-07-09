package com.mashibing.transformer.mr.nu;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.mashibing.common.KpiType;
import com.mashibing.transformer.model.dim.StatsUserDimension;
import com.mashibing.transformer.model.value.map.TimeOutputValue;
import com.mashibing.transformer.model.value.reduce.MapWritableValue;

/**
 * 计算new isntall user的reduce类
 * 将相同key的数据汇聚到一起
 * Key:
 *      维度组合
 * value:
 *      TimeOutputValue (uuid, time)
 *
 * @author 马士兵教育
 *
 */
public class NewInstallUserReducer extends Reducer<StatsUserDimension, TimeOutputValue, StatsUserDimension, MapWritableValue> {
    //准备工作
    //创建reduce端输出的value对象
    private MapWritableValue outputValue = new MapWritableValue();
    //创建去重的集合
    private Set<String> unique = new HashSet<String>();

    @Override
    protected void reduce(StatsUserDimension key, Iterable<TimeOutputValue> values, Context context) throws IOException, InterruptedException {
        this.unique.clear();
        // 开始计算uuid的个数
        for (TimeOutputValue value : values) {
            this.unique.add(value.getId());
        }
        MapWritable map = new MapWritable();
        //使用map结构是为了后续再计算过程中不跟其他指标值得计算结果混为一谈，当获取某个指标值得时候必须要根据map的key去获取
        map.put(new IntWritable(-1), new IntWritable(this.unique.size()));
        outputValue.setValue(map);

        // 设置kpi名称
        //设罝向哪张表插入数据
        String kpiName = key.getStatsCommon().getKpi().getKpiName();
        if (KpiType.NEW_INSTALL_USER.name.equals(kpiName)) {
            // 计算stats_user表中的新增用户
            //new_install_user 向用户模块插入数据，向stats_user表插入结果
            outputValue.setKpi(KpiType.NEW_INSTALL_USER);
        } else if (KpiType.BROWSER_NEW_INSTALL_USER.name.equals(kpiName)) {
            // 计算stats_device_browser表中的新增用户
            //browser_new_install_user，向浏览器模块中插入数据，向stats_device_browser表插入数据
            outputValue.setKpi(KpiType.BROWSER_NEW_INSTALL_USER);
        }
        //向mysql插入数据
        context.write(key, outputValue);
    }
}
