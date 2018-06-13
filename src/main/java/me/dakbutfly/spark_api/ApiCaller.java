package me.dakbutfly.spark_api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiCaller {
    private String url;
    private Method method;
    private String body;

    public enum Method {
        GET, POST, DELETE, PUT
    }
}
