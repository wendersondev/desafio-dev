package com.desafio.dev.infrastructure.repository;

import com.desafio.dev.infrastructure.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Page<TransactionEntity> findAllByNameStore(String nameStore, Pageable pageable);

}
