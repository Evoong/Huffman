package huffman;

import java.util.*;
import java.io.*;

/**
 * @description Decodes the serialized file created from 'HuffmanCoding.java'
 * @author Eric Voong
 * Date: 21/01/18
 */

public class HuffmanTestDecode {

	private String decodedString = "";

	/**
	 * @param path
	 *            String that points to the target text file for decoding
	 */

	public HuffmanTestDecode(String path) {
		HuffmanSerialization encoded = null;
		try {
			FileInputStream inputFile = new FileInputStream(path);
			ObjectInputStream input = new ObjectInputStream(inputFile);
			encoded = (HuffmanSerialization) input.readObject();
			input.close();
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		List<Character> characterList = encoded.characterList;
		List<String> huffmanKey = encoded.huffmanKey;
		BitSet bit = encoded.bitSet;
		int endZeroes = encoded.endZeroes;
		// Creates binary string from the serialized BitSet using a '1' for every 'true'
		// index of the BitSet
		String createdString = "";
		for (int x = 0; x < bit.length(); x++) {
			if (bit.get(x) == true) {
				createdString += "1";
			} else {
				createdString += "0";
			}
		}
		// Adds trailing zeroes to the end of the binary string
		for (int zeroes = 0; zeroes < endZeroes; zeroes++) {
			createdString += "0";
		}
		// Compares the binary string to the reference Huffman keys to determine the
		// characters
		while (createdString.length() > 0) {
			for (int y = 0; y < huffmanKey.size(); y++) {
				if (huffmanKey.get(y).length() > createdString.length()) {
					continue;
				}
				if (createdString.substring(0, huffmanKey.get(y).length()).equals(huffmanKey.get(y))) {
					decodedString += characterList.get(y);
					createdString = createdString.substring(huffmanKey.get(y).length(), createdString.length());
				}
			}
		}
	}

	public static void main(String[] args) {
		HuffmanTestDecode huffmanDecode = new HuffmanTestDecode(
				"C:\\Users\\eric9\\Desktop\\EncodedFile.ser");
		// Prints the contents of the decoded serialized file
		System.out.println("Serialized file 'EncodedFile.ser' contains:");
		System.out.println(huffmanDecode.decodedString);
	}

}
