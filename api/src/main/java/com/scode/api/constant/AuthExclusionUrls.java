package com.scode.api.constant;

import java.util.Arrays;
import java.util.List;

public class AuthExclusionUrls {
    public static List<String> list() {
        return Arrays.asList("/api/users");
    }
}
