package com.fpoly.sof3012.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpoly.sof3012.entity.Message;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String encode(Message message) throws EncodeException {
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new EncodeException(message, "Unable to encode");
        }
    }

    @Override
    public void init(EndpointConfig config) {
        Text.super.init(config);
    }

    @Override
    public void destroy() {
        Text.super.destroy();
    }
}
