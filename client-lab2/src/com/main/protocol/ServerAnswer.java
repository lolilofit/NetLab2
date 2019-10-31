package com.main.protocol;

import java.io.Serializable;

public class ServerAnswer implements Serializable {
    private boolean answer;
    private String description;
    public ServerAnswer(boolean answer, String description) {
        this.answer = answer;
        this.description = description;
    }

    public String getDescription() { return description;}
    public boolean isAnswer() { return answer; }
}
