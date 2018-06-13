package me.dakbutfly.spark_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.dakbutfly.spark_api.models.ApiCaller;

import java.util.ArrayList;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class Application {

    public static ArrayList<ApiCaller> apiCallerList = new ArrayList<>();

    public static void main(String[] args) {


        post("/api-callers", "application/json", (req, res) -> {
            // json body to map
            ObjectMapper mapper = new ObjectMapper();
            Map bodyMap = mapper.readValue(req.body(), Map.class);
            String url = (String) bodyMap.get("url");
            String method = (String) bodyMap.get("method");

            //create apiCaller
            ApiCaller apiCaller = ApiCaller.builder()
                    .url(url)
                    .method(ApiCaller.Method.valueOf(method.toUpperCase()))
                    .build();

            // save to list
            apiCallerList.add(apiCaller);

            return "{\"ok\" : true}";
        });

        get("/api-callers", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();

            //get list and translate string of json
            return mapper.writeValueAsString(apiCallerList);
        });

    }
}