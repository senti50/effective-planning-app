package pl.senti.effectiveplanningapp.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String causePath;

    public ErrorDetails(Date timestamp, String message, String causePath) {
        this.timestamp = timestamp;
        this.message = message;
        this.causePath = causePath;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCausePath() {
        return causePath;
    }

    public void setCausePath(String causePath) {
        this.causePath = causePath;
    }


}
