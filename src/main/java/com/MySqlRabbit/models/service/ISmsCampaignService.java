package com.MySqlRabbit.models.service;

import com.MySqlRabbit.models.entity.SmsCampaign;

import java.util.List;
import java.util.Optional;

public interface ISmsCampaignService {
    public List<SmsCampaign> findBySmsCampaignId(Integer id) throws Exception;
    public Integer updateCarrier(Integer id, String carrier);
    public List <SmsCampaign> list();
    public Optional <SmsCampaign> listById(Integer id);
    public Integer save(SmsCampaign sms);
}
