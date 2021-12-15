import java.awt.*;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player implements Serializable {
    long id;
    String publicName;
    Image image;
    boolean isRdy;
    ObjectOutputStream objectOutputStream;

    public Player() {
        this.isRdy = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isRdy() {
        return isRdy;
    }

    public void setRdy(boolean rdy) {
        isRdy = rdy;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }
}
