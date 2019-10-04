package huffman;

/**
 * @description Encodes data from a text file using a reference binary tree to
 *              compress the total size of the data
 * @author 
 * Date: 21/01/18
 */

public class HuffmanTestEncode {

	public static void main(String[] args) {
		// Creates the binary tree
		HuffmanTree testTree = HuffmanCoding.buildTree(HuffmanCoding.getCharFrequencies(
				"C:\\Users\\eric9\\Desktop\\TextFile.txt"));
		// Prints the characters, their frequencies and their corresponding reference
		// Huffman keys
		System.out.println("Character&Frequency");
		HuffmanCoding.addReferenceKeys(testTree, new StringBuilder());
		// Path of file to be encoded and compressed
		HuffmanCoding.encodePath(
				"C:\\Users\\eric9\\Desktop\\TextFile.txt"); // enter txt file
		// Serialization onto a new file called 'HuffmanEncoded.ser'
		HuffmanCoding.serialize(HuffmanCoding.characterList, HuffmanCoding.huffmanKey,
				HuffmanCoding.bitEncode(HuffmanCoding.huffmanCode), HuffmanCoding.huffmanCode);
	}

}
