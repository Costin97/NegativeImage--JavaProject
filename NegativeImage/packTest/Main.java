package packTest;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import packWork.Buffer;

import packWork.RunApp;

//nivelul 3 de mostenire
public class Main extends RunApp {
	public Main() {
		super();
	}

	public static void main(String args[]) throws IOException {
		// Citim numele fisierului,de la tastatura,pe care vrem sa il citim.
		System.out.println("Introduceti numele fisierului de intrare: ");
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		String nume = null;
		try {
			nume = read.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Numele fisierului de intrare este: " + nume);
		File image_file = null;
		BufferedImage image = null;
		// String image_path = "D:/NegativeImage/packTest/java.bmp";
		Buffer buff = new Buffer();

		// read image
		try {
			// Obtinerea path-ului la care se afla imaginea si citirea acesteia.
			image_file = new File(nume);
			image = ImageIO.read(image_file);
			// Initializarea clasei RunApp si apelarea metodei run()
			RunApp runApp = new RunApp(buff, image);
			runApp.run();

		} catch (IOException e) {
			System.out.println(e);
		}

	}
}