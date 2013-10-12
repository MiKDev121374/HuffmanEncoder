import java.util.*;
import java.io.*;
import java.lang.Math;

class AlphaChar{
	public char letter;
	public int freq;
	public String encoding;

	AlphaChar(char l, int fr){
		letter = l;
		freq = fr;
	}
}

class DoubleChar{
    //Holds two chars now
    public String letter;
    public char assignedChar;
    //this is calculated by multiplying the AlphaChar.freq's of the 2 chars it is made of
    public int freq;
    public String encoding;

    DoubleChar(String l, int fr, char assigned){
        letter = l;
        freq = fr;
        assignedChar = assigned;
    }
}

class Encode{
	//get file testText
	//parse each char, write encoded version to testText.enc1
	Encode(File file, File encTestText, HuffmanCode huffman, Boolean one) throws IOException{
		
                FileWriter encFw = new FileWriter(encTestText);
                BufferedWriter encBw = new BufferedWriter(encFw);

                Scanner encFile1 = new Scanner(new FileReader(file));

                String fileInput;
                String doubleMatch;
                
                if (encFile1.hasNext()){
                	fileInput = encFile1.next();
                	char[] charToEncode = fileInput.toCharArray();
                    if (one){
                    	for (int i = 0; i < charToEncode.length; i++){
                    		encBw.write(huffman.encodedPairings.get(charToEncode[i]));
                    	}
                    }
                    else{
                        for (int i = 0; i < charToEncode.length; i+=2){
                            doubleMatch = Character.toString(charToEncode[i]) + Character.toString(charToEncode[i+1]);
                            encBw.write(huffman.encodedPairings2.get(doubleMatch));
                        }
                    }
                }

                encBw.flush();
                encBw.close();
	}
}

class Decode{
	//get file testText.enc1
	//parse and write decoded version to testText.dec1
    //new Decode(encTestText, decTestText, newTree, huffman, tree);
	Decode(File encTestText, File decTestText, HuffmanTree newTree, HuffmanCode huffman, HuffmanTree tree, HashMap<Character, String> doubleCharMap, Boolean one) throws IOException{

                FileWriter decFw = new FileWriter(decTestText);
                BufferedWriter decBw = new BufferedWriter(decFw);

                Scanner decFile1 = new Scanner(new FileReader(encTestText));

                String fileInput;
                if (decFile1.hasNext()){
                        fileInput = decFile1.next();
                        char[] charToDecode = fileInput.toCharArray();
                        int[] toDecode = new int[charToDecode.length];
                        for (int i = 0; i < charToDecode.length; i++){
                                toDecode[i] = charToDecode[i] - '0';
                        }      
                        huffman.decode(tree, newTree, toDecode, 0, decBw, doubleCharMap, one);
                }

                decBw.flush();
                decBw.close();

	}
}


class Encoder{
	public static void main(String[] args) throws IOException{

		//import frequencies file and generate matching alphabet
		Scanner freqFile = new Scanner(new FileReader(args[0]));
        //Create file to encode and decode
        File testText = new File("testText");
        FileWriter fw = new FileWriter(testText);
        BufferedWriter bw = new BufferedWriter(fw);
        ArrayList<Character> dartboard = new ArrayList<Character>();

        //Single char alphabet
		ArrayList<AlphaChar> freqCharArray = new ArrayList<AlphaChar>();
        //Double char alphabet
        ArrayList<DoubleChar> freqDoubleArray2 = new ArrayList<DoubleChar>();
        ArrayList<AlphaChar> freqCharArray2 = new ArrayList<AlphaChar>();
        HashMap<Character, String> doubleCharMap = new HashMap<Character, String>();


        //======Define the alphabets based on given frequency file=====
        //single char alphabet
		int freqOfAlpha;
        //Denominator of all probabilities associated with this 1char language
		int freqTotal = 0;
		int lengthOfAlphabet = 0;
		char nextAlphabetChar;
        //2char alphabet
        int dFreq;
        //Denominator of all probabilities associated with this language
        int freqTotal2 = 0;
        int lengthOfAlphabet2 = 0;
        String dChar;
        char assignedChar;


		while (freqFile.hasNext()){
			freqOfAlpha = Integer.valueOf(freqFile.nextLine());
			nextAlphabetChar = (char)(lengthOfAlphabet+65);
			freqCharArray.add(new AlphaChar(nextAlphabetChar,freqOfAlpha));
			for (int j=0; j<freqOfAlpha; j++){
				dartboard.add(nextAlphabetChar);
			}
			freqTotal += freqOfAlpha;
			lengthOfAlphabet++;
		}
        //Make 2char alphabet based on 1char alphabet just created
        for (int i=0; i<lengthOfAlphabet; i++){
            for(int j=0; j<lengthOfAlphabet; j++){
                dChar = Character.toString(freqCharArray.get(i).letter) + Character.toString(freqCharArray.get(j).letter);
                //System.out.println("dChar = " + dChar);
                dFreq = freqCharArray.get(i).freq * freqCharArray.get(j).freq;
                //System.out.println("dFreq = " + dFreq);
                assignedChar = (char)lengthOfAlphabet2;
                //System.out.println("assignedChar = " + assignedChar);
                freqDoubleArray2.add(new DoubleChar(dChar, dFreq, assignedChar));
                freqCharArray2.add(new AlphaChar(assignedChar,dFreq));
                doubleCharMap.put(assignedChar, dChar);
                freqTotal2 += dFreq;
                lengthOfAlphabet2++;
            }
        }

        //===Generate dartboard for proportional character generation
        int numToGenerate = Integer.valueOf(args[1]);
        //if not divisible by 2 add extra symbol for the 2-char encoding
        if (numToGenerate % 2 != 0){
            numToGenerate++;
        }
        System.out.println("number of chars to encode/decode= " + numToGenerate);
        Random rand = new Random();
        //Prints dartboard arraylist
        // for (int i=0; i<freqTotal; i++){
        //  System.out.print(dartboard.get(i));
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



        //===Get Huffman encoding for each letter of the 1char alphabet
        HuffmanCode huffman = new HuffmanCode();
        HuffmanTree tree = huffman.buildTree(freqCharArray);
        HuffmanTree newTree = huffman.buildTree(freqCharArray);
        //====Print out results of Huffman encoding
        System.out.println("\nSYMBOL\tWEIGHT\tHUFFMAN CODE");
        huffman.printCodes(tree, new StringBuffer());

        //Assign 1char alphabet encodings
        double totalBits = 0;
        for (int i = 0; i < freqCharArray.size(); i++){
            freqCharArray.get(i).encoding = huffman.encodedPairings.get(freqCharArray.get(i).letter);
            totalBits += freqCharArray.get(i).encoding.length();
        }


        //===Get Huffman encoding for each letter of the 2char alphabet
        HuffmanCode huffman2 = new HuffmanCode();
        HuffmanTree tree2 = huffman.buildTree(freqCharArray2);
        HuffmanTree newTree2 = huffman.buildTree(freqCharArray2);
        //====Print out results of Huffman encoding
        System.out.println("\nSYMBOL\tWEIGHT\tHUFFMAN CODE");
        huffman.printCodes2(tree2, doubleCharMap, new StringBuffer());

        //Assign 2char alphabet encodings
        double totalBits2 = 0;
        for (int i = 0; i < freqDoubleArray2.size(); i++){
            freqDoubleArray2.get(i).encoding = huffman.encodedPairings2.get(freqDoubleArray2.get(i).letter);
            totalBits2 += freqDoubleArray2.get(i).encoding.length();
        }


        File encTestText = new File("testText.enc1");
        File decTestText = new File("testText.dec1");
        Encode encodeFile = new Encode(testText, encTestText, huffman, true);
        Decode decodeFile = new Decode(encTestText, decTestText, newTree, huffman, tree, doubleCharMap, true);

        File encTestText2 = new File("testText.enc2");
        File decTestText2 = new File("testText.dec2");
        Encode encodeFile2 = new Encode(testText, encTestText2, huffman2, false);
        Decode decodeFile2 = new Decode(encTestText2, decTestText2, newTree2, huffman2, tree2, doubleCharMap, false);


        System.out.println("\n\nTotal frequency Denominator for char1 alphabet= " + freqTotal);
        System.out.println("Total frequency Denominator for char2 alphabet= " + freqTotal2);

        //==Compute entropies of this language
        double entropy = 0;
        for (int i=0; i < freqCharArray.size(); i++){
            entropy += (freqCharArray.get(i).freq)/((double)freqTotal) * (Math.log((freqCharArray.get(i).freq)/((double)freqTotal))/Math.log(2));
        }
        entropy = -entropy;
        System.out.println("Entropy for 1char alphabet = " + entropy);

        double entropy2 = 0;
        for (int i=0; i < freqDoubleArray2.size(); i++){
            entropy2 += (freqDoubleArray2.get(i).freq)/((double)freqTotal2) * (Math.log((freqDoubleArray2.get(i).freq)/((double)freqTotal2))/Math.log(2));
        }
        entropy2 = -entropy2;
        System.out.println("Entropy for 2char alphabet = " + entropy2);


        System.out.println("totalBits = " + totalBits);
        double bitsPerSymbol = totalBits / lengthOfAlphabet;
        System.out.println("bitsPerSymbol = " + bitsPerSymbol);
        //=== Compare bits per symbol to entropy
        double percentDiff = ((bitsPerSymbol - entropy) / entropy)*100;
        System.out.println("percentDiff = " + percentDiff);

        System.out.println("totalBits2 = " + totalBits2);
        double bitsPerSymbol2 = totalBits2 / lengthOfAlphabet2;
        System.out.println("bitsPerSymbol2 = " + bitsPerSymbol2);
        //=== Compare bits per symbol to entropy
        double percentDiff2 = ((bitsPerSymbol2 - entropy2) / entropy2)*100;
        System.out.println("percentDiff2 = " + percentDiff2);


		System.out.println("\n\nEncoder!");

	}
}