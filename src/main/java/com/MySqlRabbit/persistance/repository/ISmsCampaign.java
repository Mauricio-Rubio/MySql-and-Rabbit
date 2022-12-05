package com.MySqlRabbit.persistance.repository;

import com.MySqlRabbit.persistance.entity.SmsCampaign;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISmsCampaign extends CrudRepository<SmsCampaign, Integer> {
    @Query(value = "SELECT * FROM sms_campaign WHERE sms_campaign_id = ?", nativeQuery = true) //JPQL
    List<SmsCampaign> findBySmsCampaignId(Integer id);
}
