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

		//import file and generate matching alphabet
		Scanner freqFile = new Scanner(new FileReader(args[0]));
		ArrayList<AlphaChar> freqCharArray = new ArrayList<AlphaChar>();

		int freqOfAlpha;
		int freqTotal = 0;
		int lengthOfAlphabet = 0;
		char nextAlphabetChar;
		while (freqFile.hasNext()){
			freqOfAlpha = Integer.valueOf(freqFile.nextLine());
			nextAlphabetChar = (char)(lengthOfAlphabet+65);
			lengthOfAlphabet++;
			freqCharArray.add(new AlphaChar(nextAlphabetChar,freqOfAlpha));
			System.out.println("===nextAlphabetChar= " + nextAlphabetChar);
		}

		// generate chars in right proportion
		int numToGenerate = Integer.valueOf(args[1]);
		System.out.println("k = " + numToGenerate);
		Random rand = new Random();
		//for(int i = 0; i < numToGenerate; i++){
			// generate a random number in a rannge 0 to freqTotal

			//int randNum = rand.nextInt(freqTotal) + 1;

			// Switch
			// System.out.println("random number generated = " + randNum);
			// swich ()

			// if (randNum > 0 && randNum < freqCharArray[0].freq){
			// 	// write A to a file
			// 	System.out.print(freqCharArray[0].letter);
			// }
			// else if (randNum >= freqCharArray[0].freq && randNum < freqCharArray[1].freq){
			// 	System.out.print(freqCharArray[1].letter);
			// }
			// else if (randNum >= freqCharArray[1].freq && randNum < freqCharArray[2].freq){
			// 	System.out.print(freqCharArray[2].letter);
			// }
			// else if (randNum >= freqCharArray[2].freq && randNum < freqCharArray[3].freq){
			// 	System.out.print(freqCharArray[3].letter);
			// }
			// else if (randNum >= freqCharArray[3].freq && randNum < freqCharArray[4].freq){
			// 	System.out.print(freqCharArray[4].letter);
			// }
			// else if (randNum >= freqCharArray[4].freq && randNum < freqCharArray[5].freq){
			// 	System.out.print(freqCharArray[5].letter);
			// }
			// else if (randNum >= freqCharArray[5].freq && randNum < freqCharArray[6].freq){
			// 	System.out.print(freqCharArray[6].letter);
			// }
			// else{
			// 	System.out.println("bad random number generated!");
			// }

		//}
		System.out.println("\nEncoder!");

	}
}