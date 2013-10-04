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


class Alphabet{
	//Defines alphabet to be used for encoding/decoding
	public static char a = 'A';
	public static char b = 'B';
	public static char c = 'C';
	public static char d = 'D';
	public static char e = 'E';
	public static char f = 'F';
	public static char g = 'G';

	public static int alphaLength = 7;

	char[] alphabetArray = {a, b, c, d, e, f, g};
}



class Encoder{
	public static void main(String[] args) throws IOException{

		//define alphabet
		Alphabet alphabet = new Alphabet();
		//for char in alphabet.alphabetArray
			//make instance on AlphaChar


		
		//import file and align these frequencies with the alphabet
		Scanner freqFile = new Scanner(new FileReader(args[0]));

		
		//else if (args[1].equals("k")){
			//then generate a file to encode/decode of length k with given frequency probabilities
		AlphaChar[] freqCharArray = new AlphaChar[alphabet.alphaLength];

		String freqOfAlpha;
		int[] alphaIntArray = new int[alphabet.alphaLength];
		int freqTotal = 0;
		for (int i=0; i<alphabet.alphaLength; i++){
			if (freqFile.hasNext()){
				freqOfAlpha = freqFile.nextLine();
				System.out.println(freqOfAlpha);
				alphaIntArray[i] = Integer.valueOf(freqOfAlpha);
				freqTotal += alphaIntArray[i];
				freqCharArray[i] = new AlphaChar(alphabet. alphabetArray[i], alphaIntArray[i]);
				//System.out.println(freqTotal);
			}
			else {
				System.out.println("frequenciesFile does not have enough freequencies to assign to this alphabet!");
			}
		}

		// generate chars in right proportion
		int numToGenerate = Integer.valueOf(args[1]);
		System.out.println("k = " + numToGenerate);
		Random rand = new Random();
		for(int i = 0; i < numToGenerate; i++){
			// generate a random number in a rannge 0 to freqTotal


			// Switch
			int randNum = rand.nextInt(freqTotal) + 1;
			System.out.println("random number generated = " + randNum);
			// swich ()

			// if (randNum > 0 || randNum < freqCharArray[0].freq){
			// 	// write A to a file
			// }
			// else if (randNum > freqCharArray[0].freq ||)

		}
		System.out.println("\nEncoder!");

	}
}