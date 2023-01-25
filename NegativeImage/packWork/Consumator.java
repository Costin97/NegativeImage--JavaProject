package packWork;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
// import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
// import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Consumator extends Thread {
    public Buffer buffer;
    public PipedOutputStream out;
    public BufferedImage image;
    public BufferedImage image1;
    public BufferedImage image2;

    public Consumator(Buffer b, PipedOutputStream output) {
        buffer = b;
        out = output;
    }

    public void setImage(BufferedImage img) {
        image = img;
    }

    public void setImage1(BufferedImage img) {
        image1 = img;
    }

    public void setImage2(BufferedImage img) {
        image2 = img;
    }

    public void run() {
        // try {
        BufferedImage modifiedImage;
        float start_time = System.nanoTime();
        for (int i = 0; i < 4; i++) {
            // Obtinerea imaginii introduse de clasa Producator.
            modifiedImage = buffer.get();
            for (int y = 0; y < modifiedImage.getHeight(); y++) {
                // Parcurgerea pixelilor si modificarea indicilor R,G,B conform formulelor.
                for (int x = 0; x < modifiedImage.getWidth(); x++) {
                    int p = modifiedImage.getRGB(x, y);
                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;

                    // subtract RGB from 255
                    r = 255 - r;
                    g = 255 - g;
                    b = 255 - b;

                    // set new RGB value
                    p = (a << 24) | (r << 16) | (g << 8) | b;
                    modifiedImage.setRGB(x, y, p);
                }
            }
            if (i == 0) {
                // Salvarea imaginii
                setImage1(modifiedImage);
            } else if (i == 1) {
                BufferedImage actualImage;

                // Contopirea sferturilor de imagine prelucrate pana in acest moment
                actualImage = image1;
                int w = actualImage.getWidth() + modifiedImage.getWidth();
                int h = actualImage.getHeight();
                BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                Graphics g = combined.getGraphics();
                g.drawImage(actualImage, 0, 0, null);
                g.drawImage(modifiedImage, actualImage.getWidth(), 0, null);
                // Salvarea imaginii
                setImage1(combined);

            } else if (i == 2) {
                setImage2(modifiedImage);
            } else {
                File img_file2 = null;
                BufferedImage actualImage;
                BufferedImage actualImage2;
                BufferedImage actualImage3;

                try {
                    // Contopirea sferturilor de imagine prelucrate pana in acest moment
                    actualImage = image2;
                    int w = actualImage.getWidth() + modifiedImage.getWidth();
                    int h = actualImage.getHeight();
                    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                    Graphics g = combined.getGraphics();
                    g.drawImage(actualImage, 0, 0, null);
                    g.drawImage(modifiedImage, actualImage.getWidth(), 0, null);
                    setImage2(combined);

                    // Introducerea numelui fisierului de iesire:
                    System.out.println("Introduceti numele fisierului de iesire: ");
                    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                    String nume = null;
                    try {
                        nume = read.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Numele fisierului de iesire este: " + nume);

                    img_file2 = new File(nume);
                    actualImage2 = image1;
                    actualImage3 = combined;

                    int w2 = actualImage2.getWidth();
                    int h2 = actualImage2.getHeight() * 2;
                    BufferedImage combined2 = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_ARGB);
                    Graphics g2 = combined2.getGraphics();
                    g2.drawImage(actualImage2, 0, 0, null);
                    g2.drawImage(actualImage3, 0, actualImage2.getHeight(), null);
                    // Scrierea imaginii finale in fisier.
                    ImageIO.write(combined2, "png", img_file2);
                    // Salvarea imaginii finale.
                    setImage(combined2);
                    System.out.println("Imaginea a fost scrisa in fisierul de iesire");

                } catch (IOException e) {
                    System.out.println(e);
                }

            }

        }

        float exec_time = System.nanoTime() - start_time;
        System.out.println("Timpul executiei este: " + exec_time);

        // try {
        // // Crearea streamurilor
        // ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // ImageIO.write(image, "png", baos);
        // byte[] imageBytes = baos.toByteArray();
        // // Crearea pipe-urilor
        // PipedOutputStream pos = new PipedOutputStream();
        // PipedInputStream pis = new PipedInputStream(pos);
        // pos.write(imageBytes);
        // pos.close();
        // System.out.println("Iese");

        // WriterImage iw = new WriterImage(pis);
        // iw.imgWrite();

        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }
}