package com.gmp.bkk.code.coach.service;

import com.gmp.bkk.code.coach.domain.Campaign;
import com.gmp.bkk.code.coach.repository.CampaignRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public Campaign save(Campaign campaign) {
        Campaign savedCampaign = campaignRepository.save(campaign);
        return savedCampaign;
    }

    public Optional<Campaign> findById(String id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        return campaign;
    }

    public Page<Campaign> list(Pageable pageable) {
        Page<Campaign> campaigns = campaignRepository.findAll(pageable);
        return campaigns;
    }

    public Campaign delete(String id) {
        Campaign existCampaign = findById(id)
                .orElseThrow(() -> new RuntimeException("The campaign not found"));
        campaignRepository.delete(existCampaign);
        return existCampaign;
    }

}
