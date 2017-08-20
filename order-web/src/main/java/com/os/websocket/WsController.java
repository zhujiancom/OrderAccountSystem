package com.os.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public DemoResponse say(DemoRequestMessage message) throws Exception{
        Thread.sleep(3000);
        return new DemoResponse("Welcome, "+message.getName()+" !");
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal,String msg){
        if(principal.getName().equals("zj")){
            messagingTemplate.convertAndSendToUser("eric","/queue/notifications",principal.getName()+"-send:"+msg);
        }else{
            messagingTemplate.convertAndSendToUser("zj","/queue/notifications",principal.getName()+"-send:"+msg);
        }
    }
}
