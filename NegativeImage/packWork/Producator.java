package packWork;

import java.awt.image.BufferedImage;

public class Producator extends Thread {
    public Buffer buffer;
    public BufferedImage img;
    int width;
    int height;

    public Producator(Buffer b, BufferedImage image, int w, int h) {
        buffer = b;
        img = image;
        width = w;
        height = h;
    }

    public void run() {
        //Obtinerea dimensiunilor imaginii
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage croppedImage;
        //Parcurgerea imaginii pe sferturi folosindu-ne de functia getSubImage,ce ne sectioneaza imaginea asa cum ne dorim.
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                croppedImage = img.getSubimage(0, 0, width / 2, height / 2);
            } else if (i == 1) {
                croppedImage = img.getSubimage(width / 2, 0, width / 2, height / 2);
            } else if (i == 2) {
                croppedImage = img.getSubimage(0, height / 2, width / 2, height / 2);
            } else {
                croppedImage = img.getSubimage(width / 2, height / 2, width / 2, height / 2);
            }
            System.out.println("Se pune imaginea in buffer");
            //Apelarea functiei put in buffer,ce ofera imaginea pentru a putea fi preluata de clasa Consumator.
            buffer.put(croppedImage);
            try {
                //Asteptam 1s
                sleep((1000));
            } catch (InterruptedException e) {
            }
        }
    }
}