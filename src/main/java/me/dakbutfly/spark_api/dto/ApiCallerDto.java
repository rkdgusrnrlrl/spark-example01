package me.dakbutfly.spark_api.dto;

import lombok.Data;

@Data
public class ApiCallerDto {
    private int id;
    private String url;
    private String method;
    private String body;
}
