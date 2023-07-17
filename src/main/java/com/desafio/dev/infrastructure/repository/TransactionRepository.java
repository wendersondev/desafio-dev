package com.desafio.dev.infrastructure.repository;

import com.desafio.dev.infrastructure.entity.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {
}
