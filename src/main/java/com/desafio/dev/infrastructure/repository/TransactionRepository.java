package com.desafio.dev.infrastructure.repository;

import com.desafio.dev.infrastructure.entity.TransactionEntity;
import org.h2.mvstore.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {

    public Page<TransactionEntity> findAllByNameStore(String nameStore, Pageable pageable);

}
