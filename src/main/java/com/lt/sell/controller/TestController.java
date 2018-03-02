package com.lt.sell.controller;

import com.lt.sell.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    WebSocket webSocket;

    @RequestMapping("/websocket")
    public void testWebSocket(@RequestParam("code") Integer code) {
        webSocket.sendMessage(code.toString());
    }
}
