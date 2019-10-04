package huffman;


/**
 * @description Subclass where the left and right branches of the tree are
 *              created while assigning frequencies through its parent class
 *              which are used to sort the 'Nodes'
 * @author Eric Voong
 * Date: 21/01/18
 */

public class Node extends HuffmanTree {

	public HuffmanTree left;
	public HuffmanTree right;

	public Node(HuffmanTree left, HuffmanTree right) {
		super(left.frequency + right.frequency);
		this.left = left;
		this.right = right;
	}

}