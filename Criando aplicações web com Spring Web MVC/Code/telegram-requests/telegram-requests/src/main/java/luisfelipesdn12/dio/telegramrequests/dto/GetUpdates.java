package luisfelipesdn12.dio.telegramrequests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.logging.log4j.message.Message;

public class GetUpdates {
    @JsonProperty
    private Long updateId;
    private Message message;

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}