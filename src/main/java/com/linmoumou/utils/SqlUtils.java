package com.linmoumou.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlUtils {
    private static final Logger logger = LoggerFactory.getLogger(ProperUtil.class);

    public static List<Map<String, Object>> query(DataSource dataSource, String sql) {

        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Map<String, Object> map;
            while (rs.next()) {
                map = new LinkedHashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    map.put(metaData.getColumnName(i + 1), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (Exception e) {
            logger.error("execute query failed",e);
        }
        return list;
    }
}
