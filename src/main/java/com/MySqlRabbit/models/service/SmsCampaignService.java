package com.MySqlRabbit.models.service;

import com.MySqlRabbit.models.entity.SmsCampaign;
import com.MySqlRabbit.models.dao.ISmsCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SmsCampaignService implements ISmsCampaignService{

    @Autowired
    private ISmsCampaign data;

    @Override
    public List<SmsCampaign> findBySmsCampaignId(Integer id) throws Exception {
        try{
            List<SmsCampaign> sms = data.findBySmsCampaignId(Integer.valueOf(id));
            return sms;
        }catch (Exception e){
            throw new Exception();
        }
    }

        @Override
    public Integer save(SmsCampaign sms) {
        Integer aux = 0;
        SmsCampaign smsCampaign = data.save(sms);
        if(!smsCampaign.equals(null)) aux = 1;
        else aux = 0;
        return aux;
    }
    
    @Override
    public List<SmsCampaign> list(){
        return (List<SmsCampaign>) data.findAll();
    }

    @Override
    public Optional<SmsCampaign> listById(Integer id) {
        return data.findById(id);
    }

    @Override
    public Integer updateCarrier(Integer id, String carrier) {
        return data.updateSms(id, carrier);
    }
}
