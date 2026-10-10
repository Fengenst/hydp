package com.hydp.event;

import com.alibaba.fastjson.JSONObject;
import com.hydp.entity.Event;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KafkaOrderProducer {
    @Resource
    private KafkaTemplate kafkaTemplate;

    public void publishEvent(Event event) {
        // 将事件发布到指定的主题
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }

}