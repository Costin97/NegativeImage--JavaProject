package packWork;

import java.awt.image.BufferedImage;

public class Buffer {
    public BufferedImage img;
    private boolean available = false;

    public synchronized BufferedImage get() {
        while (!available) {
            try {
                // System.out.println("Asteapta sa fie pusa imaginea");
                wait();
                // Asteapta producatorul sa puna o valoare
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Imaginea a fost luata din buffer \n");
        available = false;
        notifyAll();
        return img;
    }

    public synchronized void put(BufferedImage img) {
        while (available) {
            try {
                wait();
                // Asteapta consumatorul sa preia valoarea
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Imaginea s-a pus in buffer");
        this.img = img;
        available = true;
        notifyAll();
    }

}
