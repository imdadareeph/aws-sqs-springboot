package com.app.aws.awssqsspringboot.controller;

import com.app.aws.awssqsspringboot.entity.QuoteEntity;
import com.app.aws.awssqsspringboot.service.SqsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;
    public static final String QUOTE_QUEUE = "aws-sqs-springboot";
    @Autowired
    private SqsService sqsService;

    @RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        model.addAttribute("quoteEntity", new QuoteEntity());

        ModelAndView modelAndView = getModelAndView();
        return modelAndView;
    }

    private ModelAndView getModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("queueList", sqsService.findAll());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/sendmessage")
    public String sendMessage(Model model) {
        model.addAttribute("quoteEntity", new QuoteEntity());return "home";
    }

    @GetMapping("/showmessage")
    public String showMessages(Model model) {
        model.addAttribute("quoteEntity", new QuoteEntity());return "home";
    }

    @PostMapping("/post")
    public ModelAndView sendtoqueue(@ModelAttribute("quoteEntity") QuoteEntity quoteEntity,Model model) {
        quoteEntity.setAuthor("Imdad Areeph");
        this.queueMessagingTemplate.convertAndSend(QUOTE_QUEUE, quoteEntity);
        model.addAttribute("quoteEntity", new QuoteEntity());
        LOG.info("Message sent to SQS Queue - "+quoteEntity.getText());
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            LOG.info("Thread sleep for 1 second "+quoteEntity.getText());
        }
        ModelAndView modelAndView = getModelAndView();
        return modelAndView;
    }



}
