package com.example.event;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xuh
 * @date: 2023/5/29 10:51
 * @description:
 */
@Service
public class SendEmailService implements ApplicationEventPublisherAware {

    private List<String> blockList = Lists.newArrayList(
            "known.spammer@example.org",
            "john.doe@example.org",
            "known.hacker@example.org"
    );

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendEmail(String address, String content){
        if (blockList.contains(address)){
            applicationEventPublisher.publishEvent(new BlockedListEvent(this, address, content));
            return;
        }
        applicationEventPublisher.publishEvent(new NormalMailEvent(this, address, content));
    }
}
