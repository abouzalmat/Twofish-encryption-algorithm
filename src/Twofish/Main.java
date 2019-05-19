package Twofish;
import java.security.InvalidKeyException;



import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws InvalidKeyException{
		// Variables
		
		Scanner sc = new Scanner(System.in);// Scanner to accept user input
		
		System.out.println("Plain text (input): ");
		
		String scannerInput = sc.nextLine();
		sc.close();
		
		// Convert input String to byte array
		byte[] p = scannerInput.getBytes();
		
		// Initialize ArrayList: Used to handle input size (should multiple of 8
		ArrayList<Byte> inputText = new ArrayList();
		
		// If input text is less than 128 bits
		if(p.length > 32) {
			// Add all p elements in arraylist 
			for(int i = 0; i<p.length;i++) {
				inputText.add(p[i]);
			}
			
			// Increment array list size until it reaches a multiple of 8 (bytes)
			// and populate missing elements with 0 bytes
			while(inputText.size() % 8 != 0){
				inputText.add((byte)0);
			}
			
			// Create new Array that would be used as input to blockEncrypt
			p = null;
			p = new byte[inputText.size()];
			for(int i=0; i<p.length; i++) {
				p[i] = inputText.get(i);
			}
		}
		
		// If p length is less than 128 bits 
		else if(p.length < 32) {
			
			// First copy all p elements to array list
			for(int i=0; i < p.length; i++) {
				inputText.add(p[i]);
			}
			
			// Fill in the missing elements with 0 
			for(int i = inputText.size();i<32; i++) {
				inputText.add((byte)0);
			}
			
			// Recreate byte array from the array list (input to blockEncrypt)
			p = null;
			p = new byte[inputText.size()];
			for(int i=0; i<p.length; i++) {
				p[i] = inputText.get(i);
			}
		}
		System.out.println("Input Text : ");
		System.out.println(new String(p));
		
		// Random Key Array In Bytes
		Random r = new Random();
		byte[] key = new byte[16]; 
		r.nextBytes(key);
		

		System.out.println("Key : ");
		
		System.out.println(new String(key));
		Object sessionKey = Twofish_Algorithm.makeKey(key);
		
		
		ArrayList<byte[]> ciphers = new ArrayList();
		
		byte[] cipher;
		
		System.out.println("\n\nCipher Text : ");
		for(int i = 0 ; i < inputText.size() / 16; i++) {
			
			// Encryption 
			
			cipher = Twofish_Algorithm.blockEncrypt(p,  16 * i, sessionKey);
			ciphers.add(cipher);
			String cipherString = new String(cipher);
			System.out.print(cipherString);
		}
		
		
		System.out.println("\n\nDecrypted Cipher Text : ");
		for(int i = 0; i < ciphers.size(); i++) {
			
			// Decryption
			
			byte[] decrypted =  Twofish_Algorithm.blockDecrypt(ciphers.get(i),0, sessionKey);
			String decryptedString = new String(decrypted);
			System.out.print(decryptedString);
			
			
		}
	}
}
	
