package packWork;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
// import java.io.PipedOutputStream;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class WriterImage {
    public PipedInputStream in;

    public WriterImage() {

    }

    public WriterImage(PipedInputStream output) {
        System.out.println("Intra");
        // try {
        in = output;
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    public void imgWrite() {
        try {
            System.out.println("Intra in pipe");
            BufferedImage image = ImageIO.read(in);
            in.close();
            File img_file = null;
            img_file = new File(
                    "D:/NegativeImage/paktText/negative.bmp");
            ImageIO.write(image, "bmp", img_file);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

}

// In aceasta clasa se doreste a fi trimisa,prin intermediul pipe-ului denumit
// in,imaginea prelucrata in cadrul clasei Consumator.
// Insa,odata ce se incearca citirea din cadrul acestui pipe,programul se
// opreste in acel punct,neputand sa citeasca informatia din interiorul
// pipe-ului.