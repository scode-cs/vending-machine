package com.scode.api.dto;

import com.scode.api.dto.response.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
public class AuthDTO {
    private String userId;
    private String userName;
    private String role;

    public static void main(String[] args) {
        int a = a();
        if (((Integer)10).equals(a)) {
            System.out.println("hi");
        } else {
            System.out.println("wrong");
        }
    }

    public static Integer a() {
        return null;
    }

}
