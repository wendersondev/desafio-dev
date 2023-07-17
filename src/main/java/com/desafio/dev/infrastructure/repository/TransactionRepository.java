package com.desafio.dev.infrastructure.repository;

import com.desafio.dev.infrastructure.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {

    Page<TransactionEntity> findAllByNameStore(String nameStore, Pageable pageable);

}
