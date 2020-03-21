package com.gmp.bkk.code.coach.resource.rest;

import com.gmp.bkk.code.coach.domain.Campaign;
import com.gmp.bkk.code.coach.service.CampaignService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CampaignResource {

    private final CampaignService campaignService;

    public CampaignResource(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping("/campaigns")
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        Campaign save = campaignService.save(campaign);
        return ResponseEntity.created(URI.create("/api/campaings/" + save.getId())).body(campaign);
    }

    @PutMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> updateCampaign(@RequestBody Campaign campaign, @PathVariable String id) {
        if (campaign.getId() != null) {
            throw new RuntimeException("Bad Request");
        }
        campaign.setId(id);
        Campaign save = campaignService.save(campaign);
        return ResponseEntity.ok().body(save);
    }

    @GetMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable String id) {
        Optional<Campaign> campaign = campaignService.findById(id);
        if (campaign.isPresent()) {
            return ResponseEntity.ok().body(campaign.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/campaigns")
    public ResponseEntity<List<Campaign>> listCampaign(Pageable pageable) {
        Page<Campaign> campaigns = campaignService.list(pageable);
        return ResponseEntity.ok().body(campaigns.getContent());
    }

    @DeleteMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> listCampaign(@PathVariable String id) {
        campaignService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
