package com.MySqlRabbit.consumer;

import com.MySqlRabbit.config.MessageConfig;
import com.MySqlRabbit.dto.OrderStatus;
import com.MySqlRabbit.models.entity.SmsCampaign;
import com.MySqlRabbit.models.service.ISmsCampaignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Consumer {

    @Autowired
    private ISmsCampaignService service;

    @RabbitListener(id = "${rabbit.listener.id}", queues = {"${rabbit.listener.queue}"}, concurrency = "${rabbit.listener.concurrency}")
    public void consumeMessage(OrderStatus orderStatus) {
        log.info("STARTING CONSUMER");
        try {
            log.info(orderStatus.toString());
            List<SmsCampaign> data = service.findBySmsCampaignId(orderStatus.getOrder().getOrderId());
            log.info(data.toString());
            //orderStatus.getOrder().setOrderId(-381072979);
            if (data.equals(null)) {
                SmsCampaign ob = new SmsCampaign();
                ob.setCarrier(orderStatus.getOrder().getCarrier());
                ob.setSmsCampaignContent(orderStatus.getOrder().getName());
                ob.setSmsCampaignId(orderStatus.getOrder().getOrderId());
                //Integer saveStatus = service.save(ob);
                Integer updateStatus = service.updateCarrier(ob.getSmsCampaignId(),ob.getCarrier());
                if (updateStatus == 1) {
                    log.info("Sms saved in db");
                }
                if (updateStatus == 0) {
                    log.info("Error to save in db");
                    throw new Exception();
                }
                List<SmsCampaign> sms = service.list();
                log.info(sms.toString());
            } else {
                log.info("Message is already in DB");
            }
        } catch (Exception e) {
            log.error("ERR " + e);
        }
    }
}
