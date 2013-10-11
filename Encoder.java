import java.util.*;
import java.io.*;
import java.lang.Math;

class AlphaChar{
	//***change to string to handle double char
	public char letter;
	public int freq;
	public String encoding;

	AlphaChar(char l, int fr){
		letter = l;
		freq = fr;
	}
}

class Encode{
	//get file testText
	//parse each char, write encoded version to testText.enc1
	Encode(File file, File encTestText, HuffmanCode huffman) throws IOException{
		
        FileWriter encFw = new FileWriter(encTestText);
        BufferedWriter encBw = new BufferedWriter(encFw);

        Scanner encFile1 = new Scanner(new FileReader(file));

        String fileInput;
        
        if (encFile1.hasNext()){
        	fileInput = encFile1.next();
        	char[] charToEncode = fileInput.toCharArray();

        	for (int i = 0; i < charToEncode.length; i++){
        		//System.out.print(charToEncode[i]);
        		// file.write(huffman.encodedPairings.get(charToEncode[i]));
        		//System.out.print(huffman.encodedPairings.get(charToEncode[i]));
        		encBw.write(huffman.encodedPairings.get(charToEncode[i]));
        	}
        }

        encBw.flush();
        encBw.close();
	}
}

class Decode{
	//get file testText.enc1
	//parse and write decoded version to testText.dec1
	Decode(File encTestText, File decTestText, HuffmanTree newTree, HuffmanCode huffman, HuffmanTree tree) throws IOException{

                FileWriter decFw = new FileWriter(decTestText);
                BufferedWriter decBw = new BufferedWriter(decFw);

                Scanner decFile1 = new Scanner(new FileReader(encTestText));

                String fileInput;
                if (decFile1.hasNext()){
                        fileInput = decFile1.next();
                        //System.out.println("fileInput = " + fileInput + "======\n");
                        char[] charToDecode = fileInput.toCharArray();
                        int[] toDecode = new int[charToDecode.length];
                        for (int i = 0; i < charToDecode.length; i++){
                                toDecode[i] = charToDecode[i] - '0';
                                //System.out.print(toDecode[i]);
                        }       
                        huffman.decode(tree, newTree, toDecode, 0, decBw);
                }

		//int[] toDecode = {0,1};
		//huffman.decode(tree, newTree, toDecode, 0);
		// System.out.println("\nhuffman.decodedSB====================");
		// System.out.println(huffman.decodedSB);
                decBw.flush();
                decBw.close();
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


        //===Define the alphabet based on given frequency file
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


		//===Generate dartboard for proportional character generation
		int numToGenerate = Integer.valueOf(args[1]);
		System.out.println("number of chars to encode/decode= " + numToGenerate);
		Random rand = new Random();
		//Prints dartboard arraylist
		// for (int i=0; i<freqTotal; i++){
		// 	System.out.print(dartboard.get(i));
		// }


		//===Generate proportional random file testText
		for(int i = 0; i < numToGenerate; i++){
			// generate a random number in a range 0 to freqTotal
			int randNum = rand.nextInt(freqTotal);
			//System.out.println("random number generated = " + randNum);
			//System.out.println("dart landed on " + dartboard.get(randNum));
			bw.write(dartboard.get(randNum));
		}
		bw.flush();
        bw.close();


        //===Get Huffman encoding for each letter of the alphabet
        HuffmanCode huffman = new HuffmanCode();
        HuffmanTree tree = huffman.buildTree(freqCharArray);
        HuffmanTree newTree = huffman.buildTree(freqCharArray);
        // print out results
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        huffman.printCodes(tree, new StringBuffer());
        //print chars and associated encoding value
        // for (int i=0; i<lengthOfAlphabet; i++){
        // 	System.out.println("char = " + (char)(i+65));
        // 	System.out.println("encoded pairing = " + huffman.encodedPairings.get((char)(i+65)));
        // }
        for (int i = 0; i < freqCharArray.size(); i++){
        	freqCharArray.get(i).encoding = huffman.encodedPairings.get(freqCharArray.get(i).letter);
        	//System.out.println("encoding for " + freqCharArray.get(i).letter + " set to " + freqCharArray.get(i).encoding);
        }


        File encTestText = new File("testText.enc1");
        File decTestText = new File("testText.dec1");
        Encode encodeFile = new Encode(testText, encTestText, huffman);
        Decode decodeFile = new Decode(encTestText, decTestText, newTree, huffman, tree);



		System.out.println("\nEncoder!");

	}
}