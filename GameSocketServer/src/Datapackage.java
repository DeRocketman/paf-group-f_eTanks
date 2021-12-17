import java.util.ArrayList;

public class Datapackage extends ArrayList<Object> {
    private static final long serialVersionUID = 7878787877787878787L;

    private String senderID = "UNSIGNED";
    private String senderGroupName = "UNSIGNED";

    public Datapackage(String id, Object... o) {
        this.add(0, id);
        for (Object current : o) {
            this.add(current);
        }
    }

    public String id() {
        if (!(this.get(0) instanceof String)) {
            throw new IllegalArgumentException("Identifier of Datapackage is not a String");
        }
        return (String) this.get(0);
    }

    public String getSenderID() {
        return this.senderID;
    }

    public String getSenderGroup() {
        return this.senderGroupName;
    }

    protected void sign(String senderID, String senderGroup) {
        this.senderID = senderID;
        this.senderGroupName = senderGroup;
    }



}
