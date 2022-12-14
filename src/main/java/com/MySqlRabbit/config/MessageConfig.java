package com.MySqlRabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    //public static final String QUEUE = "my-queue";
    @Value("${rabbit.shooter.queue}")
    private String QUEUE;

    @Value("${rabbit.shooter.exchange}")
    private String EXCHANGE;
    //public static final String EXCHANGE = "my-exchange";
    //public static final String ROUTING_KEY = "my-routing-key";
    
    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }
    
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(QUEUE);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
    
}
