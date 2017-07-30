package shahen.mahmoud.mobilechat.Chat;

/**
 * Created by harty on 7/30/2017.
 */

public class Message {
    String id;
    String message;
    String owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
