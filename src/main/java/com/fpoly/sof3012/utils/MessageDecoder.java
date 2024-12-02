package com.fpoly.sof3012.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpoly.sof3012.entity.Message;
import jakarta.websocket.*;

public class MessageDecoder implements Decoder.Text<Message> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Message decode(String json) throws DecodeException {
        try {
            return mapper.readValue(json, Message.class);
        } catch (JsonProcessingException e) {
            throw new DecodeException(json, "Unable to encode");
        }
    }

    @Override
    public boolean willDecode(String json) {
        return json.contains("type") && json.contains("text");
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
