package com.a306.fanftasy.domain.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDefault {
    private boolean success;
    private String messege;
    private Object data;
}
