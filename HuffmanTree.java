import java.util.PriorityQueue;
import java.util.Scanner;

class MyMain {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String Data = "";
		System.out.printf("Please enter your string???\t");
		Data = input.nextLine();
		CompressWithHuffman CWH = new CompressWithHuffman(Data);
	}
}

abstract class HuffmanTree implements Comparable<HuffmanTree> {
	public final int frequency; // the frequency of this tree
	
	public HuffmanTree(int freq) {
		frequency = freq;
	}

	// compares on the frequency
	public int compareTo(HuffmanTree tree) {
		return frequency - tree.frequency;
	}
}

class HuffmanLeaf extends HuffmanTree {
	public final char value; // the character this leaf represents

	public HuffmanLeaf(int freq, char val) {
		super(freq);
		value = val;
	}
}

class HuffmanNode extends HuffmanTree {
	public final HuffmanTree left, right; // subtrees

	public HuffmanNode(HuffmanTree l, HuffmanTree r) {
		super(l.frequency + r.frequency);
		left = l;
		right = r;
	}
}

class HuffmanCode {
	// input is an array of frequencies, indexed by character code
	public static HuffmanTree buildTree(list ls) {
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
		// initially, we have a forest of leaves
		// one for each non-empty character
		Node C = ls.Start;
		while (C != null) {
			trees.offer(new HuffmanLeaf(C.value, C.Data));
			C = C.next;
		}
		assert trees.size() > 0;
		// loop until there is only one tree left
		while (trees.size() > 1) {
			// two trees with least frequency
			HuffmanTree a = trees.poll();
			HuffmanTree b = trees.poll();

			// put into new node and re-insert into queue
			trees.offer(new HuffmanNode(a, b));
		}
		return trees.poll();
	}

	public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
		assert tree != null;
		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;

			// print out character, frequency, and code for this leaf (which is
			// just the prefix)
			System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);

		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) tree;

			// traverse left
			prefix.append('0');
			printCodes(node.left, prefix);
			prefix.deleteCharAt(prefix.length() - 1);

			// traverse right
			prefix.append('1');
			printCodes(node.right, prefix);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	/*public static void main(String[] args) {
		String test = "this is an example of a huffman tree";

		// we will assume that all our characters will have
		// code less than 256, for simplicity
		int[] charFreqs = new int[256];
		// read each character and record the frequencies
		for (char c : test.toCharArray())
			charFreqs[c]++;

		// build tree
		HuffmanTree tree = buildTree(charFreqs);

		// print out results
		System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
		printCodes(tree, new StringBuffer());
	}*/
}

class Node {
	public char Data;
	public int DataValue;
	public int value;
	public Node next;

	public Node() {

	}

	public Node(char data) {
		this.Data = data;
		DataValue = (int) data;
		value = 1;
	}

	public Node(Node node) {
		Data = node.Data;
		DataValue = node.DataValue;
		value = node.value;
		next = null;
	}
}

class list {
	public Node Start;
	public int size;

	//
	public list() {

	}

	//
	public list(String Str) {
		char[] D;
		D = StringProssesing(Str);
		for (int i = 0; i < D.length; i++) {
			// System.out.printf("%c",D[i]);
			AddChar(D[i]);
		}
		// accSort();

		deSort();
		Reverse();
	}

	//
	public boolean Empty() {
		if (Start == null) {
			return true;
		}
		return false;
	}

	//
	private static char[] StringProssesing(String text) {
		char[] data = new char[text.length()];
		for (int i = 0; i < text.length(); i++) {
			data[i] = text.charAt(i);
		}
		return data;
	}

	//
	private boolean AddChar(char data) {
		Node p = new Node(data);
		Node C = Start, s = null;
		while (C != null && C.DataValue < p.DataValue) {
			s = C;
			C = C.next;
		}
		p.next = C;
		if (C != null && C.DataValue == p.DataValue) {
			C.value++;
		} else if (s == null) {
			Start = p;
		} else {
			s.next = p;
		}
		size++;
		// System.out.printf("your list Updated successfully:IAE\n");
		return true;
	}

	//
	public void listPrint() {
		if (this.Empty()) {
			System.out.printf("\nyour list is Empty\n");
		} else {
			Node C = Start;
			while (C != null) {
				System.out.printf("%c\t%d\t%d\n", C.Data, C.DataValue, C.value);
				C = C.next;
			}
			System.out.println();
		}
	}

	//
	public void Reverse() {
		Node r = null, s = Start, t = Start.next;

		while (s.next != null) {
			s.next = r;
			r = s;
			s = t;
			t = t.next;
		}
		s.next = r;
		Start = s;
	}

	//
	private void accSort() {
		boolean flag = true;
		while (flag) {
			Node C = Start, s = null;
			flag = false;
			while (C.next != null) {
				s = C;
				C = C.next;
				if (C.next != null && C.value < C.next.value) {
					Node temp = new Node(C);
					s.next = C.next;
					C = C.next;
					if (C.next != null) {
						temp.next = C.next;
						C.next = temp;
					} else
						C.next = temp;
					flag = true;
				}
			}
		}
	}

	private void deSort() {
		boolean flag = true;
		while (flag) {
			Node C = Start, s = null;
			flag = false;
			while (C.next != null) {
				s = C;
				C = C.next;
				if (C.next != null && (int)C.Data > (int)C.next.Data) {
					Node temp = new Node(C);
					s.next = C.next;
					C = C.next;
					if (C.next != null) {
						temp.next = C.next;
						C.next = temp;
					} else
						C.next = temp;
					flag = true;
				}
			}
		}
		if ((int)Start.Data > (int)Start.next.Data) {
			Node P = new Node(Start);
			Node C = Start, s = null;
			while (C != null) {
				s = C;
				C = C.next;
			}
			P.next = C;
			s.next = P;
			Start = Start.next;
		}
	}
}

class Encodelist {
	class BuiltTreeLeaf {
		public char Data;
		public StringBuffer leafCode;
		String lf;
		BuiltTreeLeaf next;

		public BuiltTreeLeaf(char data, StringBuffer prefix) {
			Data = data;
			leafCode = new StringBuffer(prefix);
			lf = new String(prefix);
		}
	}

	BuiltTreeLeaf Start;

	public boolean Add(char data, StringBuffer prefix) {
		BuiltTreeLeaf p = new BuiltTreeLeaf(data, prefix);
		BuiltTreeLeaf C = Start, s = null;
		while (C != null) {
			s = C;
			C = C.next;
		}
		p.next = C;
		if (s == null) {
			Start = p;
		} else {
			s.next = p;
		}
		// System.out.printf("your list Updated successfully:IAE\n");
		return true;
	}

	public String FindItem(char item) {
		BuiltTreeLeaf t = Start;
		while (t != null) {
			if (t.Data == item)
				return t.leafCode.toString();
			t = t.next;
		}
		return t.leafCode.toString();
	}

	public boolean AddCodes(HuffmanTree tree, StringBuffer prefix) {
		assert tree != null;
		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;

			// print out character, frequency, and code for this leaf (which is
			// just the prefix)
			// System.out.println(leaf.value + "\t" + leaf.frequency + "\t" +
			// prefix);
			this.Add(leaf.value, prefix);

		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) tree;

			// traverse left
			prefix.append('0');
			AddCodes(node.left, prefix);
			prefix.deleteCharAt(prefix.length() - 1);

			// traverse right
			prefix.append('1');
			AddCodes(node.right, prefix);
			prefix.deleteCharAt(prefix.length() - 1);
		}
		return true;
	}

	public void listPrint() {
		BuiltTreeLeaf C = Start;
		while (C != null) {
			System.out.println(C.Data + "\t" + C.leafCode.toString() + "\t" + C.lf);
			C = C.next;
		}
		System.out.println();

	}
}

class EncodeString {
	char[] str;
	String temp = "";
	Encodelist ls;

	public EncodeString(String Str, Encodelist els) {
		str = StringProssesing(Str);
		ls = els;
		Convert();
	}

	public String getHash() {
		return temp;
	}

	boolean Convert() {
		int i = 0;
		boolean flag = true;
		while (flag) {
			temp += ls.FindItem(str[i]);
			i++;
			if (i == str.length - 1)
				flag = false;
		}
		return true;
	}

	private static char[] StringProssesing(String text) {
		char[] data = new char[text.length()];
		for (int i = 0; i < text.length(); i++) {
			data[i] = text.charAt(i);
		}
		return data;
	}

	public static int[] Compress(String str) {
		int[] result;
		if (str.length() % 8 != 0) {
			int a = (str.length() / 8) + 1;
			int b = 8 - (str.length() % 8);
			String t = "";
			for (int i = 0; i < b; i++) {
				t += "0";
			}
			str += t;
		}
		result = new int[str.length() / 8];
		for (int i = 0; i < result.length; i++) {
			String temp = "";
			for (int j = i * 8; j < (i * 8) + 8; j++) {
				temp += str.charAt(j);
			}
			result[i] = binaryToInteger(temp);
		}
		return result;
	}

	private static int binaryToInteger(String binary) {
		char[] numbers = binary.toCharArray();
		int result = 0;
		for (int i = numbers.length - 1; i >= 0; i--)
			if (numbers[i] == '1')
				result += Math.pow(2, (numbers.length - i - 1));
		return result;
	}

}

class CompressWithHuffman {
	public CompressWithHuffman(String Data) {
		list ls = new list(Data);
		HuffmanTree HF = HuffmanCode.buildTree(ls);
		ls.listPrint();
		StringBuffer pre1 = new StringBuffer();
		HuffmanCode.printCodes(HF, pre1);
		Encodelist els = new Encodelist();
		StringBuffer pre = new StringBuffer();
		els.AddCodes(HF, pre);
		EncodeString ES = new EncodeString(Data, els);
		System.out.println(ES.getHash());
		int[] a = EncodeString.Compress(ES.getHash());
		System.out.printf(Data.length() + " " + a.length + "\n");
		for (int i = 0; i < a.length; i++)
			System.out.printf(a[i] + " ");
		//System.out.println(ES.getHash());
	}
}