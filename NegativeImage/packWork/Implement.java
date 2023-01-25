package packWork;

import java.awt.image.BufferedImage;

//nivelul 1 de mostenire
public class Implement extends AbstractClass {
    BufferedImage img;

    public Implement() {
        // Nothing
    }

    public Implement(BufferedImage image) {
        img = image;
    }

    public int getHeights() {
        return img.getHeight();
    }

    public int getWidths() {
        return img.getWidth();
    }

    public int produs(int... args) {
        int produs = 1;
        for (int index : args) {
            produs *= index;
        }
        return produs;
    }
}
