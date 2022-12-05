package com.MySqlRabbit.persistance.service;

import com.MySqlRabbit.persistance.entity.SmsCampaign;

import java.util.List;
import java.util.Optional;

public interface ISmsCampaignService {
    List<SmsCampaign> findBySmsCampaignId(Integer id) throws Exception;
    public List <SmsCampaign> list();
    public Optional <SmsCampaign> listById(Integer id);
    public Integer save(SmsCampaign sms);
}
