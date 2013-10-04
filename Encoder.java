import java.util.*;
import java.io.*;

class AlphaChar{
	//alphabet char
	//freq
	//encode


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
		
		String freqOfAlpha;
		int[] alphaIntArray = new int[alphabet.alphaLength];
		for (int i=0; i<alphabet.alphaLength; i++){
			if (freqFile.hasNext()){
				freqOfAlpha = freqFile.nextLine();
				System.out.println(freqOfAlpha);
				alphaIntArray[i] = Integer.valueOf(freqOfAlpha);

			}
			else {
				System.out.println("frequenciesFile does not have enough freequencies to assign to this alphabet!");
			}
		}


		System.out.println("\nEncoder!");

	}
}