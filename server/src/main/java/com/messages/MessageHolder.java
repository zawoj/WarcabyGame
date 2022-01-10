package com.messages;

import java.io.Serializable;

/**
 * Class being a message
 */
public class MessageHolder implements Serializable {
    private String messageType;

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }
}
