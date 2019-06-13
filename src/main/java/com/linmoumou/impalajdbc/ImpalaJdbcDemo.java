package com.linmoumou.impalajdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.linmoumou.utils.ProperUtil;
import com.linmoumou.utils.SqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ImpalaJdbcDemo {
    private static final Logger logger = LoggerFactory.getLogger(ImpalaJdbcDemo.class);


    public static void main(String[] args) {
        Properties props = ProperUtil.getProperties("impala.properties");

        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            DataSource dataSource = DruidDataSourceFactory.createDataSource(props);
            List<Map<String, Object>> list = SqlUtils.query(dataSource, "select count(*) from test.test");
            for (Map<String, Object> stringObjectMap : list) {
                System.out.println(stringObjectMap);
            }
        } catch (Exception e) {
            logger.error("",e);
        }

    }


}
