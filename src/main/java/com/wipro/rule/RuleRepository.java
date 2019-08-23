package com.wipro.rule;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RuleRepository extends MongoRepository<Rules, String>{

}
