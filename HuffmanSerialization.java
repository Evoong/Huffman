package huffman;


import java.util.*;

/**
 * @description Blueprint required to serialize, store and read data compressed
 *              and decompressed using Huffman encoding and decoding
 * @author Eric Voong
 * Date: 21/01/18
 */

public class HuffmanSerialization implements java.io.Serializable {

	private static final long serialVersionUID = -1341182230346329552L;
	public List<Character> characterList = new ArrayList<Character>();
	public List<String> huffmanKey = new ArrayList<String>();
	public BitSet bitSet;
	public int endZeroes;

}

