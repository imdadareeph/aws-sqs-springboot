package com.app.aws.awssqsspringboot.service;


import com.app.aws.awssqsspringboot.entity.QuoteEntity;

import java.util.List;

public interface SqsService {
    void saveQuote(QuoteEntity incomingQuote,
                   String messageId,
                   String approximateFirstReceiveTimestamp);

    public List<QuoteEntity> findAll();
}
