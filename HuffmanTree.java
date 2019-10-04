package huffman;


/**
 * @description Parent class extended by the classes 'Node' and 'Leaf' that is
 *              used as a blueprint to create a binary tree
 * @author Eric Voong
 * Date: 21/01/18
 */

public abstract class HuffmanTree implements Comparable<HuffmanTree> {

	public int frequency;

	public HuffmanTree(int frequency) {
		this.frequency = frequency;
	}

	public int compareTo(HuffmanTree tree) {
		return frequency - tree.frequency;
	}

}