package com.jd.test.tolstore;


//import cn.hutool.core.date.DateUtil;

import com.alicloud.openservices.tablestore.ClientConfiguration;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.*;
import com.alicloud.openservices.tablestore.model.sql.SQLQueryRequest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jd
 * @date 2022/8/26 17:56
 */
public class test {

    private static final String endPoint = "http://sun.cn-hangzhou.ots.aliyuncs.com";
    private static final String accessKeyId = "LTAI5tMiasNy42rAz2zaverR";
    private static final String accessKeySecret = "dh0D3DjfyqVNlezmV0qBppNiQcjgwA";
    private static final String instanceName = "wuhua";
    private static final String TABLE_NAME = "order";

//    SyncClient client = new SyncClient(endPoint, accessKeyId, accessKeySecret, instanceName);

    public static void main(String[] args) {

//        ClientConfiguration clientConfiguration = new ClientConfiguration();
//        clientConfiguration.setConnectionTimeoutInMillisecond(5000);
//        clientConfiguration.setSocketTimeoutInMillisecond(5000);
////        clientConfiguration.setRetryStrategy(new AlwaysRetryStrategy());
//        SyncClient client = new SyncClient(endPoint, accessKeyId, accessKeySecret, instanceName, clientConfiguration);
//
//
//        //构造主键。
//        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
//        primaryKeyBuilder.addPrimaryKeyColumn("_id", PrimaryKeyValue.fromString("1"));
//        primaryKeyBuilder.addPrimaryKeyColumn("oId", PrimaryKeyValue.fromString("1"));
//        PrimaryKey primaryKey = primaryKeyBuilder.build();
//
//        //读取一行数据，设置数据表名称。
//        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(TABLE_NAME, primaryKey);
//        //设置读取最新版本。
//        criteria.setMaxVersions(1);
//        GetRowResponse getRowResponse = client.getRow(new GetRowRequest(criteria));
//        Row row = getRowResponse.getRow();
//
//        SQLQueryRequest request = new SQLQueryRequest("select pk, long_value, double_value, string_value, bool_value from test_table limit 20");


        System.out.println(new BigDecimal(10).divide(new BigDecimal(2)));

//        System.out.println(DateUtil.beginOfDay(new Date()));
    }
}
