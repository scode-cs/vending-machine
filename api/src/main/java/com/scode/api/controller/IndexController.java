package com.scode.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
public class IndexController {

    @RequestMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }

    public static void main(String[] args) {
        test(Collections.singletonMap("name", "User 1"));
    }

    public static void test(Map<String, String> map) {
        map.put("id", "1");
        map.put("password", "pwd");
        System.out.println(map);
    }

}
