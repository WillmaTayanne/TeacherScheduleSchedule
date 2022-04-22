package com.pdist.schedule;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdist.schedule.Model.Message;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ClientRPC implements AutoCloseable {

    private final Connection connection;
    private final Channel channel;

    public ClientRPC() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("beaver.rmq.cloudamqp.com");
        factory.setUsername("fvbmyulz");
        factory.setPassword("sWunE0VdpHq0hXBh4N7qrZNKpQbtqgwL");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void sendMessage(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (ClientRPC fibRpc = new ClientRPC()) {
            String response = fibRpc.call( objectMapper.writeValueAsString(message) );
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String call(String message) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", "request-queue", props, message.getBytes(StandardCharsets.UTF_8));

        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.offer(new String(delivery.getBody(), StandardCharsets.UTF_8));
            }
        }, consumerTag -> {
        });

        String result = response.take();
        channel.basicCancel(ctag);
        return result;
    }


    public void close() throws Exception {
        connection.close();
    }
}