package com.MySqlRabbit.models.dao;

import com.MySqlRabbit.models.entity.SmsCampaign;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISmsCampaign extends CrudRepository<SmsCampaign, Integer> {
    @Query(value = "SELECT * FROM sms_campaign WHERE sms_campaign_id = ?", nativeQuery = true) //JPQL
    List<SmsCampaign> findBySmsCampaignId(Integer id);

    // Update no es parte de la interfaz JPA
    @Modifying
    @Query(value = "UPDATE sms_campaign u set u.carrier = :carrier WHERE u.sms_campaign_id = :id ?", nativeQuery = true)
    Integer updateSms(@Param(value = "id") Integer id, @Param(value ="carrier") String carrier);

    @Modifying
    @Query(value = "INSERT INTO sms_campaign (smsCampaignId, carrier) values (:smsCampaignId, :carrier)", nativeQuery = true)
    Integer insertSms(@Param("smsCampaignId") Integer id, @Param("carrier") String carrier);
}
