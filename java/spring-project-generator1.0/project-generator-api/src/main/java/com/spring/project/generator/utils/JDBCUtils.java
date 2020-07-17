/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.utils;

import com.project.utils.lang.StringUtils;
import com.spring.project.generator.core.entity.Columns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-26 14:52
 */
public class JDBCUtils {
    private final Logger logger = LoggerFactory.getLogger(JDBCUtils.class);
    private String url;
    private String user;
    private String password;
    private String driver;
    private JavaTypeResolverDefault javaTypeResolverDefault;


    /**
     * 文件读取，只会执行一次，使用静态代码块
     */
    public JDBCUtils() {
        //3获取数据
        this.url = "jdbc:mysql://localhost:3306/jx_test?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
        this.user = "root";
        this.password = "123456";
        this.driver = "com.mysql.jdbc.Driver";
        this.javaTypeResolverDefault = new JavaTypeResolverDefault();
    }

    /**
     * 获取连接
     *
     * @return 连接对象
     */
    public Connection getConnection() throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", this.user);
        props.setProperty("password", this.password);
        //设置可以获取remarks信息
        props.setProperty("remarks", "true");
        //设置可以获取tables remarks信息
        props.setProperty("useInformationSchema", "true");

        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }

    /**
     * 释放资源
     *
     * @param rs
     * @param st
     * @param conn
     */
    public void close(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet executeSql(String sql) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            //获取执行sql的对象
            st = conn.createStatement();
            //执行sql
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, st, conn);
        }
        return rs;
    }

    public DatabaseMetaData getMetaData() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        DatabaseMetaData data = null;
        try {
            conn = getConnection();
            //获取执行sql的对象
            st = conn.createStatement();
            data = conn.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, st, conn);
        }
        return data;
    }

    public List<TableEntity> getTableInfo() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        DatabaseMetaData metaData = null;
        List<TableEntity> list = new ArrayList<>();
        try {
            conn = getConnection();
            //获取执行sql的对象
            st = conn.createStatement();
            metaData = conn.getMetaData();
            String schema = conn.getSchema();
            rs = metaData.getTables(conn.getCatalog(), null, null, null);
            String tableName = "";
            while (rs.next()) {
                tableName = rs.getString("TABLE_NAME");
                TableEntity tableEntity = new TableEntity();
                tableEntity.setSchema(rs.getString("TABLE_SCHEM"));
                tableEntity.setTableName(tableName);
                tableEntity.setClassName(StringUtils.underToCamelFirst(tableName));
                tableEntity.setClassNameNew(StringUtils.underToCamel(tableName));
                tableEntity.setTableType(rs.getString("TABLE_TYPE"));
                tableEntity.setTableComment(rs.getString("REMARKS"));
                Map<String, String> hashmapKey = getColumnsPrimaryKey(metaData, tableName);
                List<TableColumnEntity> columnAll = getColumns(metaData, tableName, hashmapKey);
                list.add(tableEntity);

                Map<String, List<TableColumnEntity>> hashmapIndexUnique = getColumnsIndexInfo(metaData, tableName, hashmapKey, columnAll);

                tableEntity.setPrimaryKeys(hashmapKey);
                List<TableColumnEntity> columnsPrimary = new ArrayList<>();

                for (TableColumnEntity columnEntity : columnAll) {
                    if (hashmapKey.containsKey(columnEntity.getColumnName())) {
                        columnsPrimary.add(columnEntity);
                    }
                }

                tableEntity.setColumnsAll(columnAll);
                tableEntity.setColumnsPrimaryKey(columnsPrimary);
                tableEntity.setColumnsUnique(hashmapIndexUnique);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, st, conn);
        }
        return list;
    }

    public Map<String, List<TableColumnEntity>> getColumnsIndexInfo(DatabaseMetaData metaData, String tableName, Map<String, String> hashmapKey, List<TableColumnEntity> columnsAll) {
        Map<String, List<TableColumnEntity>> map = new HashMap<>();
        try {
            ResultSet rs = metaData.getIndexInfo(null, null, tableName, true, true);
            while (rs.next()) {
                TableColumnEntity column = new TableColumnEntity();
                String indexName = rs.getString("INDEX_NAME");
                String columnName = rs.getString("COLUMN_NAME");
                if (hashmapKey.containsKey(columnName)) {
                    continue;
                }
                short type = rs.getShort("TYPE");//索引类型;
//                System.out.print(indexName + " ");
//                System.out.print(columnName + "  ");
//                System.out.print(type + " ");
//                System.out.print(rs.getShort("ORDINAL_POSITION") + " ");
//                System.out.print(rs.getBoolean("NON_UNIQUE") + " ");
//                System.out.print(rs.getString("INDEX_QUALIFIER") + "  ");
//                System.out.print(rs.getInt("CARDINALITY")+" ");

                TableColumnEntity tableColumn = null;
                for (TableColumnEntity col : columnsAll) {
                    if (columnName.equals(col.getColumnName())) {
                        tableColumn = col;
                        break;
                    }
                }
                if (tableColumn == null) {
                    break;
                }

                if (map.containsKey(indexName)) {
                    map.get(indexName).add(tableColumn);
                } else {
                    List<TableColumnEntity> list = new ArrayList<>();
                    list.add(tableColumn);
                    map.put(indexName, list);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, String> getColumnsPrimaryKey(DatabaseMetaData metaData, String tableName) {
        Map<String, String> hashmap = new HashMap<>();
        try {
            ResultSet rs = metaData.getPrimaryKeys(null, null, tableName);
            while (rs.next()) {
                TableColumnEntity column = new TableColumnEntity();
                String columnName = rs.getString("COLUMN_NAME");
                hashmap.put(columnName, columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashmap;
    }

    public List<TableColumnEntity> getColumns(DatabaseMetaData metaData, String tableName) {
        return getColumns(metaData, tableName, null);
    }

    public List<TableColumnEntity> getColumns(DatabaseMetaData metaData, String tableName, Map<String, String> hashmap) {
        List<TableColumnEntity> List = new ArrayList<>();
        try {
            ResultSet rs = metaData.getColumns(null, null, tableName, "%");
            String columnName = "";
            int jdbcType;
            while (rs.next()) {
                //打印字段name信息
                TableColumnEntity column = new TableColumnEntity();
                columnName = rs.getString("COLUMN_NAME");
                jdbcType = rs.getInt("DATA_TYPE");
                JavaTypeResolverDefault.JdbcTypeInformation jtype = javaTypeResolverDefault.get(jdbcType);
                column.setColumnName(columnName);
                column.setPropertyName(StringUtils.underToCamelFirst(columnName));
                column.setPropertyNameNew(StringUtils.underToCamel(columnName));
                column.setJdbcType(jdbcType);
                column.setJdbcTypeName(jtype.getJdbcTypeName());
                column.setJavaTypeFully(jtype.getFullyQualifiedJavaType());
                column.setJavaTypeShort(jtype.getShortQualifiedJavaType());
                column.setTableName(tableName);

                //小数位数
                column.setColumnDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
                column.setColumnComment(rs.getString("REMARKS"));
                column.setColumnSize(rs.getInt("COLUMN_SIZE"));
                column.setColumnAutoIncrement("YES".equals(rs.getString("IS_AUTOINCREMENT")));
                column.setColumnGenerated("YES".equals(rs.getString("IS_GENERATEDCOLUMN")));
                column.setColumnIsNull(rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable);

                //判断是不是主键
                if (hashmap != null) {
                    column.setColumnIsKey(hashmap.containsKey(columnName));
                }
                List.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List;
    }


    public List<Columns> getColumns(String tableName) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        DatabaseMetaData data = null;
        List<Columns> list = new ArrayList<>();
        JavaTypeResolverDefault javaTypeResolverDefault = new JavaTypeResolverDefault();
        try {
            conn = getConnection();
            //获取执行sql的对象
            st = conn.createStatement();
            data = conn.getMetaData();
            rs = data.getColumns(null, null, tableName, "%");

            Map<String, String> hashMap = new HashMap<>();
            ResultSet rsKey = data.getPrimaryKeys(null, null, tableName);

            while (rsKey.next()) {
                hashMap.put(rsKey.getString("COLUMN_NAME"), rsKey.getString("COLUMN_NAME"));
            }

            while (rs.next()) {
                //打印字段name信息
                Columns column = new Columns();
                String columnName = rs.getString("COLUMN_NAME");
                column.setColumnName(columnName);
                column.setJdbcType(rs.getInt("DATA_TYPE"));
                column.setTableName(rs.getString("TABLE_NAME"));
                column.setColumnKey("id");
                column.setColumnComment(rs.getString("REMARKS"));
                column.setJdbcTypeName(javaTypeResolverDefault.get(column.getJdbcType()).getJdbcTypeName());
                column.setFullJavaType(javaTypeResolverDefault.get(column.getJdbcType()).getFullyQualifiedJavaType());
                column.setShortJavaType(javaTypeResolverDefault.get(column.getJdbcType()).getShortQualifiedJavaType());

                list.add(column);
//                System.out.println(rs.getString("TYPE_NAME"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, st, conn);
        }
        return list;
    }
}
