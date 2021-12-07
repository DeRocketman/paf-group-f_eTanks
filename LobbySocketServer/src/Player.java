import java.awt.*;
import java.io.Serializable;

public class Player implements Serializable {
    String publicName;
    Image image;
    boolean isRdy;

    public String getPublicName() {

        return this.publicName;
    }

    public void setName(String publicName) {
        this.publicName = publicName;
    }

    public Image getImage() {
        return this.image;
    }

    public void setPicture(Image image) {
        this.image = image;
    }

    public boolean getIsRdy() {
        return this.isRdy;
    }

    public void setRdy(boolean isRdy) {
        this.isRdy = isRdy;
    }
}
