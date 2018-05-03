package ua.od.and.mediaplayertests;

public class MessageEvent {
    private int to;
    private int code;
    private String message;

    public MessageEvent(int to, int code, String message) {
        this.to = to;
        this.code = code;
        this.message = message;
    }

    public MessageEvent(int to, int code) {
        this.to = to;
        this.code = code;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
