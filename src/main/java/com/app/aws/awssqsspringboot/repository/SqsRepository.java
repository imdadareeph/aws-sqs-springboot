package com.app.aws.awssqsspringboot.repository;

import com.app.aws.awssqsspringboot.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SqsRepository extends JpaRepository<QuoteEntity, Long> {

    boolean existsByAwsMessageId(String awsMessageId);
}