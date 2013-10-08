import java.util.*;
import java.io.*;
import java.lang.Math;

class AlphaChar{
	//alphabet char
	//freq
	//encode
	public char letter;
	public int freq;
	public String encode;

	AlphaChar(char l, int fr){
		letter = l;
		freq = fr;
	}

}



class Encoder{
	public static void main(String[] args) throws IOException{

		//import frequencies file and generate matching alphabet
		Scanner freqFile = new Scanner(new FileReader(args[0]));
		ArrayList<AlphaChar> freqCharArray = new ArrayList<AlphaChar>();
		ArrayList<Character> dartboard = new ArrayList<Character>();
		//Create file to encode and decode
        File testText = new File("testText");
        FileWriter fw = new FileWriter(testText);
        BufferedWriter bw = new BufferedWriter(fw);

		int freqOfAlpha;
		int freqTotal = 0;
		int lengthOfAlphabet = 0;
		char nextAlphabetChar;
		while (freqFile.hasNext()){
			freqOfAlpha = Integer.valueOf(freqFile.nextLine());
			nextAlphabetChar = (char)(lengthOfAlphabet+65);
			freqCharArray.add(new AlphaChar(nextAlphabetChar,freqOfAlpha));
			for (int j=0; j<freqOfAlpha; j++){
				dartboard.add(nextAlphabetChar);
			}
			freqTotal += freqOfAlpha;
			lengthOfAlphabet++;
			//System.out.println("===nextAlphabetChar= " + nextAlphabetChar);
		}

		// generate chars in right proportion
		int numToGenerate = Integer.valueOf(args[1]);
		System.out.println("number of chars to encode/decode= " + numToGenerate);
		Random rand = new Random();

		//Prints dartboard arraylist
		// for (int i=0; i<freqTotal; i++){
		// 	System.out.print(dartboard.get(i));
		// }

		//***Generate proportional random file testText
		for(int i = 0; i < numToGenerate; i++){
			// generate a random number in a range 0 to freqTotal
			int randNum = rand.nextInt(freqTotal);
			//System.out.println("random number generated = " + randNum);
			//System.out.println("dart landed on " + dartboard.get(randNum));
			bw.write(dartboard.get(randNum));
		}
		bw.flush();
        bw.close();

        HuffmanCode huffman = new HuffmanCode();
        HuffmanTree tree = huffman.buildTree(freqCharArray);
        // print out results
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        huffman.printCodes(tree, new StringBuffer());




		System.out.println("\nEncoder!");

	}
}