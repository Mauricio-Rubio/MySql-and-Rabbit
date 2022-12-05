package com.MySqlRabbit.consumer;

import com.MySqlRabbit.config.MessageConfig;
import com.MySqlRabbit.dto.OrderStatus;
import com.MySqlRabbit.persistance.entity.SmsCampaign;
import com.MySqlRabbit.persistance.service.ISmsCampaignService;
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

    @RabbitListener(id ="", queues = MessageConfig.QUEUE, concurrency = "1")
    public void consumeMessage(OrderStatus orderStatus){
        log.info("INICIANDO EL CONSUMIDOR");
        try{
            Thread.sleep(5000);

        }catch (Exception e){
            log.error("ERR "+e);
        }
        finally {
            log.info(orderStatus.toString());
            try{
                //orderStatus.getOrder().setOrderId(-381072979);
                List<SmsCampaign> data = service.findBySmsCampaignId(orderStatus.getOrder().getOrderId());
                log.info(data.toString());
                if(data.equals(null)){
                    SmsCampaign ob = new SmsCampaign();
                    ob.setCarrier(orderStatus.getOrder().getCarrier());
                    ob.setSmsCampaignContent(orderStatus.getOrder().getName());
                    ob.setSmsCampaignId(orderStatus.getOrder().getOrderId());
                    service.save(ob);
                    List<SmsCampaign> sms = service.list();
                    log.info(sms.toString());
                }else{
                    log.info("Message is already in DB");
                }
            }catch (Exception e){
                log.error("ERR "+e);
            }



        }
    }
}
