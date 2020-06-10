package com.app.aws.awssqsspringboot.controller;

import com.app.aws.awssqsspringboot.entity.QuoteEntity;
import com.app.aws.awssqsspringboot.service.SqsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/addtoqueue")
public class SQSController {

    private static final Logger LOG = LoggerFactory.getLogger(SQSController.class);
    public static final String QUOTE_QUEUE = "aws-sqs-springboot";

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    private SqsService sqsService;

    @GetMapping
    @ResponseBody
    public void sendMessage(@RequestParam(required = false) String msg) {
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setAuthor("Imdad Areeph");
        quoteEntity.setText(msg);
        this.queueMessagingTemplate.convertAndSend(QUOTE_QUEUE, quoteEntity);
    }

    @PostMapping("/quotes")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendQuote(@RequestBody QuoteEntity quote) {
        LOG.info("Sending quote {} to SQS", quote);
        this.queueMessagingTemplate.convertAndSend(QUOTE_QUEUE, quote);
    }

   @SqsListener("aws-sqs-springboot")
    @ResponseStatus(HttpStatus.CREATED)
    public void getMessage(@Valid QuoteEntity quote,
                           @Header("MessageId") String messageId,
                           @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp) {
        LOG.info("Received quote {} with messageId {}", quote, messageId);
        sqsService.saveQuote(quote, messageId, approximateFirstReceiveTimestamp);
    }
}
