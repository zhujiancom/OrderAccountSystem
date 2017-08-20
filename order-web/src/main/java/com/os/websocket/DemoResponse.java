package com.os.websocket;

import lombok.Data;

@Data
public class DemoResponse {
    private String responseMessage;

    public DemoResponse(){}

    public DemoResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
