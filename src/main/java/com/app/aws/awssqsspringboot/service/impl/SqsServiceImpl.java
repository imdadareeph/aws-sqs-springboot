package com.app.aws.awssqsspringboot.service.impl;

;
import com.app.aws.awssqsspringboot.entity.QuoteEntity;
import com.app.aws.awssqsspringboot.repository.SqsRepository;
import com.app.aws.awssqsspringboot.service.SqsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SqsServiceImpl implements SqsService {
    private static final Logger LOG = LoggerFactory.getLogger(SqsServiceImpl.class);

    private final SqsRepository sqsRepository;

    public SqsServiceImpl(SqsRepository sqsRepository) {
        this.sqsRepository = sqsRepository;
    }

    @Override
    public void saveQuote(QuoteEntity incomingQuote, String messageId, String approximateFirstReceiveTimestamp) {
        if (null!=messageId && sqsRepository.existsByAwsMessageId(messageId)) {
            LOG.warn("Quote with AWS Message ID {} already exists", messageId);
        }  else {
            QuoteEntity quote = new QuoteEntity(
                    incomingQuote.getText(),
                    incomingQuote.getAuthor(),
                    messageId,
                    toTimespamp(approximateFirstReceiveTimestamp)
            );
            QuoteEntity quoteEntity = new QuoteEntity();
            quoteEntity.setText(incomingQuote.getText());
            quoteEntity.setAuthor("");
            quoteEntity.setDateReceived(toTimespamp(approximateFirstReceiveTimestamp));
            quoteEntity.setAwsMessageId(messageId);
            LOG.info("Saving quote with AWS Message ID {}", messageId);
            sqsRepository.save(quoteEntity);
        }
    }

    private Timestamp toTimespamp(String dateAsStr) {
        LocalDateTime localDateTime = new Timestamp(Long.parseLong(dateAsStr)).toLocalDateTime();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }

    @Override
    public List<QuoteEntity> findAll() {
        List<QuoteEntity> quoteEntity = new ArrayList<>();
        quoteEntity = sqsRepository.findAll();
        return quoteEntity;
    }
}
