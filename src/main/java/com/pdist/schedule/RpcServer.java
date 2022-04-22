package com.pdist.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdist.schedule.Model.Message;
import com.pdist.schedule.Service.MessageService;
import com.rabbitmq.client.StringRpcServer;
import com.rabbitmq.client.Channel;

public class RpcServer extends StringRpcServer {

    public RpcServer(Channel channel, String queueName) throws Exception {
        super(channel, queueName);
    }

    @Override
    public String handleStringCall(String req) {
        String reply;

        try {
            System.out.println(" [.] Receiving request: \"" + req + "\"");
            reply = process(req);
        } catch (Exception e) {
            System.out.println(" [ ] Error reply: \"" + e.getMessage() + "\"");
            reply = null;
        }

        System.out.println(" [ ] Sending reply: \"" + reply + "\"");
        return reply;
    }

    private static String process(String req) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(req, Message.class);
        MessageService messageService = new MessageService();
        Message messageResult = messageService.push(message);
        return messageResult != null ? objectMapper.writeValueAsString(messageResult) : null;
    }
}
