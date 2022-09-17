package com.springboot.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-09-16 10:49
 *
 * @author
 */
@Component
public class MessageListener {
    private Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @KafkaListener(topics = {"demo"}, groupId = "task1")
    public void listener(ConsumerRecord<String, String> record) {
        String msg = record.value();
        //消息偏移量
        long offset = record.offset();
        logger.info("消费数据:{} \\n偏移量{}", msg, offset);
    }
}
