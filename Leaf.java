package huffman;
/**
 * @description Subclass that contains a character and its frequency and has a
 *              'Node' object connected to a 'Leaf' object
 * @author Eric Voong
 * Date: 21/01/18
 */

public class Leaf extends HuffmanTree {

	public char letter;

	public Leaf(int frequency, char letter) {
		super(frequency);
		this.letter = letter;
	}

}