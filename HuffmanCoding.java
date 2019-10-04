package huffman;

import java.util.*;
import java.io.*;

/**
 * @description Data Compression through the encoding and decoding of data from
 *              a text file using a reference binary tree
 * @author Eric Voong
 * Date: 21/01/18
 */

public class HuffmanCoding {

	protected static List<Character> characterList = new ArrayList<Character>();
	private static List<Integer> frequencyList = new ArrayList<Integer>();
	protected static List<String> huffmanKey = new ArrayList<String>();
	protected static String huffmanCode = "";

	/**
	 * @param characterFrequency
	 *            Array of integers that contains the frequencies of the characters
	 *            from the text file
	 * @return Balanced binary tree
	 */

	public static HuffmanTree buildTree(int[] characterFrequency) {
		PriorityQueue<HuffmanTree> huffmanQueue = new PriorityQueue<HuffmanTree>();
		// Creates a leaf for all characters
		for (int charNum = 0; charNum < characterFrequency.length; charNum++) {
			if (characterFrequency[charNum] > 0) {
				huffmanQueue.offer(new Leaf(characterFrequency[charNum], (char) charNum));
			}
		}
		// Combines lowest frequency leaf nodes to create new parent node
		while (huffmanQueue.size() > 1) {
			HuffmanTree lowestFrequency1 = huffmanQueue.poll();
			HuffmanTree lowestFrequency2 = huffmanQueue.poll();
			huffmanQueue.offer(new Node(lowestFrequency1, lowestFrequency2));
		}
		return huffmanQueue.poll();
	}

	/**
	 * @param tree
	 *            Instance of the binary tree that is used to traverse the tree
	 * @param character
	 *            Instance of StringBuilder, to which '0' or '1' is appended when
	 *            navigating either left or right respectively through the binary
	 *            tree
	 */

	public static void addReferenceKeys(HuffmanTree tree, StringBuilder character) {
		if (tree instanceof Leaf) {
			Leaf leaf = (Leaf) tree;
			System.out.println(leaf.letter + "       " + leaf.frequency);
			characterList.add(leaf.letter);
			frequencyList.add(leaf.frequency);
			huffmanKey.add(character.toString());
		}
		// Traverses the binary tree from left to right to create the Huffman keys which
		// are later used to encode and decode
		else if (tree instanceof Node) {
			Node node = (Node) tree;
			character.append('0');
			addReferenceKeys(node.left, character);
			character.deleteCharAt(character.length() - 1);
			character.append('1');
			addReferenceKeys(node.right, character);
			character.deleteCharAt(character.length() - 1);
		}
	}

	/**
	 * @param filePath
	 *            String that is the path of the target file
	 * @return List of the character frequencies from the target file which will be
	 *         used to create the binary tree
	 */

	public static int[] getCharFrequencies(String filePath) {
		int[] charFrequencies = new int[256];
		BufferedReader fileIn;
		try {
			String word = "";
			fileIn = new BufferedReader(new FileReader(filePath));
			// Counts the number of characters from the target file by reading each line
			while (word != null) {
				try {
					word = fileIn.readLine();
					for (char c1 : word.toCharArray()) {
						charFrequencies[c1]++;
					}
					for (char c2 : "\n".toCharArray()) {
						charFrequencies[c2]++;
					}
				} catch (NullPointerException e) {
					System.out.println("NULL POINTER ERROR OCCURED");
				} catch (IOException e) {
					System.out.println("I/O ERROR OCCURED");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND");
		}
		return charFrequencies;
	}

	/**
	 * @param filePath
	 *            String that points to the target text file for reading
	 */

	public static void encodePath(String filePath) {
		try {
			String word = "";
			BufferedReader fileIn;
			fileIn = new BufferedReader(new FileReader(filePath));
			while (word != null) {
				try {
					word = fileIn.readLine();
					if (word != null) {
						huffmanCode += encode(word + "\n");
					} else {
						encode("\n");
					}
				} catch (NullPointerException e) {
					System.out.println("NULL POINTER ERROR OCCURED");
				} catch (IOException e) {
					System.out.println("I/O ERROR OCCURED");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND");
		}
	}

	/**
	 * @param word
	 *            String that is going to be encoded
	 * @return Binary string obtained using the binary tree
	 */

	public static String encode(String word) {
		String encodedString = "";
		char[] wordArray = word.toCharArray();
		for (int x = 0; x < wordArray.length; x++) {
			for (int y = 0; y < frequencyList.size(); y++) {
				if (wordArray[x] == characterList.get(y)) {
					encodedString += huffmanKey.get(y);
				}
			}
		}
		return encodedString;
	}

	/**
	 * @param encodedString
	 *            Binary instance string variable
	 * @return BitSet where the indexes of '1' are set to true
	 */

	public static BitSet bitEncode(String encodedString) {
		BitSet bit = new BitSet(encodedString.length());
		for (int x = 0; x < encodedString.length(); x++) {
			if (encodedString.charAt(x) == '1') {
				bit.set(x);
			}
		}
		return bit;
	}

	/**
	 * @param bit
	 *            BitSet which is used to determine the location of '1' in the
	 *            binary string
	 * @param endZeroes
	 *            Integer that keeps track of the number of '0's after the last '1'
	 * @return String obtained from decoding file
	 */

	public static String bitDecode(BitSet bit, int endZeroes) {
		String decodedBits = "";
		for (int x = 0; x < bit.length(); x++) {
			if (bit.get(x) == true) {
				decodedBits += "1";
			} else {
				decodedBits += "0";
			}
		}
		// Adds the trailing zeroes
		for (int zeroes = 0; zeroes < endZeroes; zeroes++) {
			decodedBits += "0";
		}
		String decodedString = "";
		while (decodedBits.length() > 0) {
			for (int y = 0; y < huffmanKey.size(); y++) {
				if (huffmanKey.get(y).length() > decodedBits.length()) {
					continue;
				}
				if (decodedBits.substring(0, huffmanKey.get(y).length()).equals(huffmanKey.get(y))) {
					decodedString += characterList.get(y);
					decodedBits = decodedBits.substring(huffmanKey.get(y).length(), decodedBits.length());
				}
			}
		}
		System.out.println(decodedString);
		return decodedString;
	}

	/**
	 * @param bit
	 *            BitSet of the indexes of '1' in the encoded file
	 * @param encodedString
	 *            Encoded binary string to find the number of trailing zeroes
	 * @param characterList
	 *            char ArrayList
	 * @param huffCode
	 *            String ArrayList containing '0's and '1's, of which each index
	 *            corresponds with a character in characterList
	 */

	public static void serialize(List<Character> characterList, List<String> huffmanKey, BitSet bit,
			String encodedString) {
		HuffmanSerialization s = new HuffmanSerialization();
		s.characterList = characterList;
		s.huffmanKey = huffmanKey;
		s.bitSet = bit;
		s.endZeroes = encodedString.length() - bit.length();
		try {
			FileOutputStream createdFile = new FileOutputStream(
					"C:\\Users\\eric9\\Desktop\\EncodedFile.ser"); // enter file output location & name
			ObjectOutputStream outputFile = new ObjectOutputStream(createdFile);
			outputFile.writeObject(s);
			outputFile.close();
			createdFile.close();
			System.out.println("Serialization has completed onto a new file called EncodedFile.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
