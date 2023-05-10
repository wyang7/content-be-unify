package com.resource.wangyang.cbu.service.resource.event;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author wangyang18
 * @program: SubscribeEvent
 * @description:
 * @date 2023-05-09 21:17:43
 */
public class SubscribeEvent {


    public static enum RowChangeType implements Serializable {
        INSERT(0),
        DELETE(1),
        UPDATE(2);

        private int code;

        RowChangeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }

        public static SubscribeEvent.RowChangeType valueOf(int code) {
            switch(code) {
                case 0:
                    return INSERT;
                case 1:
                    return DELETE;
                case 2:
                    return UPDATE;
                default:
                    return null;
            }
        }
    }


    public static enum ColumnType implements Serializable {
        INTEGER(0, Integer.class),
        LONG(1, Long.class),
        FLOAT(2, Float.class),
        DOUBLE(3, Double.class),
        BIGDECIMAL(4, BigDecimal.class);//其余自行补充


        private int code;
        private Class<?> clazz;

        ColumnType(int code, Class<?> clazz) {
            this.code = code;
            this.clazz = clazz;
        }

        public int getCode() {
            return this.code;
        }


        public static SubscribeEvent.ColumnType valueOf(int code) {
            switch(code) {
                case 0:
                    return INTEGER;
                case 1:
                    return LONG;
                case 2:
                    return FLOAT;
                case 3:
                    return DOUBLE;
                case 4:
                    return BIGDECIMAL;
                default:
                    return null;
            }
        }

    }

    @Data
    public static class OneColumnChange implements Serializable {
        private String columnName;
        private SubscribeEvent.ColumnType type;
        private Object oldValue;
        private Object newValue;

    }


    @Data
    public static class OneRowChange implements Iterable<SubscribeEvent.OneColumnChange> {
        private String schemaName;
        private String tableName;
        private SubscribeEvent.RowChangeType type;
        private List<SubscribeEvent.OneColumnChange> columnChanges;
        private Map<String, SubscribeEvent.OneColumnChange> columnMap;

        public OneRowChange(String schemaName, String tableName, SubscribeEvent.RowChangeType type) {
            this.schemaName = schemaName;
            this.tableName = tableName;
            this.type = type;
            this.columnChanges = new ArrayList();
            this.columnMap = new HashMap();
        }

        public Iterator<SubscribeEvent.OneColumnChange> iterator() {
            return this.columnChanges.iterator();
        }
    }
}
