package com.gmp.bkk.code.coach.repository;

import com.gmp.bkk.code.coach.domain.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign, String> {
}
