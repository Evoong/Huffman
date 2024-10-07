# Huffman Coding

This project implements Huffman Coding for data compression through the encoding and decoding of data from a text file using a reference binary tree.

![Huffman Tree](https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Huffman_tree_2.svg/1200px-Huffman_tree_2.svg.png)

## Description

Huffman Coding is a lossless data compression algorithm. The idea is to assign variable-length codes to input characters, with shorter codes assigned to more frequent characters. The steps involved in this project include:

1. **Building a Huffman Tree**: Construct a binary tree where each leaf node represents a character from the input text, and the path from the root to the leaf represents the binary code for that character.
2. **Encoding**: Convert the input text into a binary string using the Huffman Tree.
3. **Decoding**: Convert the binary string back into the original text using the Huffman Tree.
4. **Serialization**: Save the Huffman Tree and encoded data to a file for later use.

## Classes and Methods

### `HuffmanCoding`

- **Fields**:
	- `characterList`: List of characters in the input text.
	- `frequencyList`: List of frequencies of the characters.
	- `huffmanKey`: List of Huffman codes for the characters.
	- `huffmanCode`: Encoded binary string of the input text.

- **Methods**:
	- `buildTree(int[] characterFrequency)`: Builds the Huffman Tree from character frequencies.
	- `addReferenceKeys(HuffmanTree tree, StringBuilder character)`: Traverses the Huffman Tree to generate Huffman codes.
	- `getCharFrequencies(String filePath)`: Reads a file and returns an array of character frequencies.
	- `encodePath(String filePath)`: Encodes the content of a file into a binary string.
	- `encode(String word)`: Encodes a string into a binary string using the Huffman codes.
	- `bitEncode(String encodedString)`: Converts a binary string into a BitSet.
	- `bitDecode(BitSet bit, int endZeroes)`: Decodes a BitSet back into the original string.
	- `serialize(List<Character> characterList, List<String> huffmanKey, BitSet bit, String encodedString)`: Serializes the Huffman Tree and encoded data to a file.

### `HuffmanTree`

An abstract class representing a node in the Huffman Tree.

### `Leaf`

A subclass of `HuffmanTree` representing a leaf node containing a character and its frequency.

### `Node`

A subclass of `HuffmanTree` representing an internal node with left and right children.

### `HuffmanSerialization`

A class used for serializing the Huffman Tree and encoded data.

## Usage

1. **Build the Huffman Tree**:
	 ```java
	 int[] charFrequencies = HuffmanCoding.getCharFrequencies("path/to/textfile.txt");
	 HuffmanTree tree = HuffmanCoding.buildTree(charFrequencies);
	 ```

2. **Generate Huffman Codes**:
	 ```java
	 HuffmanCoding.addReferenceKeys(tree, new StringBuilder());
	 ```

3. **Encode a File**:
	 ```java
	 HuffmanCoding.encodePath("path/to/textfile.txt");
	 ```

4. **Serialize the Huffman Tree and Encoded Data**:
	 ```java
	 BitSet bitSet = HuffmanCoding.bitEncode(HuffmanCoding.huffmanCode);
	 HuffmanCoding.serialize(HuffmanCoding.characterList, HuffmanCoding.huffmanKey, bitSet, HuffmanCoding.huffmanCode);
	 ```

5. **Decode the Encoded Data**:
	 ```java
	 String decodedString = HuffmanCoding.bitDecode(bitSet, endZeroes);
	 ```
