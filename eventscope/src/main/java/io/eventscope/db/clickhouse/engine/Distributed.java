package io.eventscope.db.clickhouse.engine;

public class Distributed {

    String dataTable;

    String shardingKey;

    public Distributed(String dataTable, String shardingKey) {
        this.dataTable = dataTable;
        this.shardingKey = shardingKey;
    }
}