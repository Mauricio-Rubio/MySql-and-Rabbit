package com.MySqlRabbit.persistance.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "sms_campaign")
@Data
@ToString
public class SmsCampaign {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer smsCampaignId;
    private String carrier;
    private String smsCampaignContent;
}