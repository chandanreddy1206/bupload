package datastore;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The Class ConversionUtil provides methods for type conversion.
 * 
 * @author BabajyotiPrakashSingh
 * @version 0.1
 */
public class ConversionUtil {

	/**
	 * Convert object to byte.
	 * 
	 * @param obj
	 *            the objectReceived
	 * @return the byte[]
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] convertObjectToByte(Object objectReceived)
			throws IllegalArgumentException, IOException {
		try (ByteArrayOutputStream bytesArrayOutput = new ByteArrayOutputStream();
				ObjectOutputStream bytesObjectOutput = new ObjectOutputStream(
						bytesArrayOutput);) {
			bytesObjectOutput.writeObject(objectReceived);
			return bytesArrayOutput.toByteArray();
		}
	}

	/**
	 * Convert byte to object.
	 * 
	 * @param byteArray
	 *            the byte array
	 * @return the object
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 */
	public static Object convertByteToObject(byte[] byteArrayReceived)
			throws IllegalArgumentException, IOException {
		try (ByteArrayInputStream inputByteArray = new ByteArrayInputStream(
				byteArrayReceived);
				ObjectInputStream returnList = new ObjectInputStream(
						inputByteArray);) {
			try {
				return returnList.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
