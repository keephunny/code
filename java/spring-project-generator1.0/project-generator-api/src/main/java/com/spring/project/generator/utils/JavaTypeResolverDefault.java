/**
 * Copyright 2006-2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spring.project.generator.utils;

import com.project.utils.lang.StringUtils;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;

public class JavaTypeResolverDefault {
    protected List<String> warnings;
    protected Properties properties;
    protected boolean forceBigDecimals;
    protected boolean useJSR310Types;
    protected Map<Integer, JdbcTypeInformation> typeMap = new HashMap<>();

    public JavaTypeResolverDefault() {
        typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY", Object.class.getName()));
        typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT", Long.class.getName()));
        typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY", "byte[]"));
        typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", Boolean.class.getName()));
        typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB", "byte[]"));
        typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN", Boolean.class.getName()));
        typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR", String.class.getName()));
        typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB", String.class.getName()));
        typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK", Object.class.getName()));
        typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", Date.class.getName()));
        typeMap.put(Types.DECIMAL, new JdbcTypeInformation("DECIMAL", BigDecimal.class.getName()));
        typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT", Object.class.getName()));
        typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE", Double.class.getName()));
        typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT", Double.class.getName()));
        typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", Integer.class.getName()));
        typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT", Object.class.getName()));
        typeMap.put(Types.LONGNVARCHAR, new JdbcTypeInformation("LONGNVARCHAR", String.class.getName()));
        typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation("LONGVARBINARY", "byte[]"));
        typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR", String.class.getName()));
        typeMap.put(Types.NCHAR, new JdbcTypeInformation("NCHAR", String.class.getName()));
        typeMap.put(Types.NCLOB, new JdbcTypeInformation("NCLOB", String.class.getName()));
        typeMap.put(Types.NVARCHAR, new JdbcTypeInformation("NVARCHAR", String.class.getName()));
        typeMap.put(Types.NULL, new JdbcTypeInformation("NULL", Object.class.getName()));
        typeMap.put(Types.NUMERIC, new JdbcTypeInformation("NUMERIC", BigDecimal.class.getName()));
        typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER", Object.class.getName()));
        typeMap.put(Types.REAL, new JdbcTypeInformation("REAL", Float.class.getName()));
        typeMap.put(Types.REF, new JdbcTypeInformation("REF", Object.class.getName()));
        typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", Short.class.getName()));
        typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT", Object.class.getName()));
        typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", Date.class.getName()));
        typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", Date.class.getName()));
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", Byte.class.getName()));
        typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY", "byte[]"));
        typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", String.class.getName()));
        // JDK 1.8 types
        typeMap.put(Types.TIME_WITH_TIMEZONE, new JdbcTypeInformation("TIME_WITH_TIMEZONE", "java.time.OffsetTime"));
        typeMap.put(Types.TIMESTAMP_WITH_TIMEZONE, new JdbcTypeInformation("TIMESTAMP_WITH_TIMEZONE", "java.time.OffsetDateTime"));
    }

    public JdbcTypeInformation get(int index) {

        return typeMap.get(index);

    }

    public static class JdbcTypeInformation {
        private String jdbcTypeName;

        private String fullyQualifiedJavaType;
        private String shortQualifiedJavaType;

        public JdbcTypeInformation(String jdbcTypeName, String fullyQualifiedJavaType) {
            this.jdbcTypeName = jdbcTypeName;
            this.fullyQualifiedJavaType = fullyQualifiedJavaType;
        }

        public String getJdbcTypeName() {
            return jdbcTypeName;
        }

        public String getFullyQualifiedJavaType() {
            return fullyQualifiedJavaType;
        }

        public String getShortQualifiedJavaType() {
            String str = this.fullyQualifiedJavaType;
            if (StringUtils.isEmpty(str)) {
                return null;
            }
            if (!str.contains(".")) {
                return str;
            }
            str = str.substring(str.lastIndexOf(".") + 1);

            return str;
        }
    }
}
