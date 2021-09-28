package fa.training.services.implement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileIO {
	public static void readToArray(ArrayList objects, String fileName) {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			Object obj;
			while ((obj = ois.readObject()) != null) {
				objects.add(obj);
			}

			fis.close();
			ois.close();

		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file ");
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};

	public static void writeToFile(ArrayList objects, String fileName) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			for (Object obj : objects) {
				oos.writeObject(obj);

			}
			fos.close();
			oos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
