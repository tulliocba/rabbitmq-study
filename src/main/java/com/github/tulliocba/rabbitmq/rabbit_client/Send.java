package com.github.tulliocba.rabbitmq.rabbit_client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try(
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()
        ) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            for (int i = 0; i < 100; i++) {
            final String message = "Hello World --> " + i;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("[X] Sent <"+ message +">");
            }
        }
    }
}
