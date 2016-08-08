package prototype;

import java.util.Scanner;

public class ScannerInputPrototype {

	public static void main(String[] args) {
		Scanner keyboardInputScanner = new Scanner(System.in);
		int keySize = 0;
		String key = "";
		
		while(keySize != 8){
			System.out.println("Input the encryption key (exactly 8 characters): ");
			key = keyboardInputScanner.nextLine();
			keySize = key.length();
			if(keySize != 8)
				System.out.println("Invalid size.");
		}
		
		System.out.println("Key: " + key);
		keyboardInputScanner.close();
	}
}