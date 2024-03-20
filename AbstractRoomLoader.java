import java.io.*;
import java.util.*;

public abstract class AbstractRoomLoader {

	protected CaveData cave;

	public abstract void load();

	public Room getStart() {
		return cave.getStart();
	}

	public Room getEnd() {
		return cave.getEnd();
	}

	public void serialize(String fileName) {
		// Serialization
		try {
			// Saving of object in a file
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);

			// Method for serialization of object
			out.writeObject(cave);

			out.close();
			file.close();

			System.out.println("Cave has been serialized to =>" + fileName);

		}

		catch (IOException ex) {
			System.out.println("IOException is caught => " + ex);
		}

	}

	public void deserialize(String fileName) {
		try {
			// Reading the object from a file
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);

			// Method for deserialization of object
			cave = (CaveData) in.readObject();

			in.close();
			file.close();

			System.out.println("Object has been deserialized  from file " + fileName);
			System.out.println("Start = " + getStart() + ", end = " + getEnd());
		}

		catch (IOException ex) {
			System.out.println("IOException is caught => " + ex);
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught => " + ex);
		}

	}

}