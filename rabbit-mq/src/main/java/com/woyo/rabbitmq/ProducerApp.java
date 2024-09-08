package com.woyo.rabbitmq;

import com.rabbitmq.client.*;

import java.util.Map;

public class ProducerApp {

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://guest:guest@localhost:5672/");
        factory.setVirtualHost("finance");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        for (int i = 0; i < 10; i++) {
            String message = "email " + i;

            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .headers(Map.of("sample", "value"))
                    .build();

            channel.basicPublish("notification", "email", properties, message.getBytes());
        }

        channel.close();
        connection.close();
    }
}
