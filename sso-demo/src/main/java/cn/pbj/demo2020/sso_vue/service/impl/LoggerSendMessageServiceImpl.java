package cn.pbj.demo2020.sso_vue.service.impl;

import cn.pbj2019.demo.sso_vue.service.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerSendMessageServiceImpl implements SendMessageService {

    private static final Logger logger = LoggerFactory.getLogger(LoggerSendMessageServiceImpl.class);

    @Override
    public void send(String key, String message) {
        logger.info("Send to key:[{}] with message:[{}]", key, message);
    }
}
