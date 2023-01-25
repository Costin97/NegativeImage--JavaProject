package packWork;

import java.awt.image.BufferedImage;
import java.io.PipedOutputStream;

//nivelul 2 de mostenire
public class RunApp extends Implement {
    public Buffer buffer;
    public BufferedImage imageToConvert;
    {
        System.out.println("Apelarea constructorului clasei RunApp");
    }

    public RunApp() {
        // Nothing
    }

    public RunApp(Buffer b, BufferedImage image) {
        super();
        buffer = b;
        imageToConvert = image;
    }

    public void run() {
        PipedOutputStream out = new PipedOutputStream();

        // Initializarea clasei Implement si obtinerea dimensiunilor imaginii
        // folosindu-ne de metodele getWidths si getHeights
        Implement implement = new Implement(imageToConvert);
        int width = implement.getWidths();
        int height = implement.getHeights();
        int dimensiune = implement.produs(width, height);
        System.out.println("Dimensiunea imaginii este de: " + dimensiune + "\n");
        // Initializarea claselor Producator si Consumator si pornirea Thread-urilor.
        Producator producator = new Producator(buffer, imageToConvert, width, height);
        Consumator consumator = new Consumator(buffer, out);
        producator.start();
        consumator.start();
    }
}
