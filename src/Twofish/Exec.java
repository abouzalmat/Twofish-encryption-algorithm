package Twofish;

import java.security.InvalidKeyException;

public class Exec {

	public static void main(String[] args) throws InvalidKeyException {
		String test = "arcana is the boss kika o hlib lmika w hada ra gha test dial dak";
		byte p[] = test.getBytes();
		String keyy = "OABDHTNBQMTBDPHS";
		byte k[] = keyy.getBytes();
		Object key = Twofish_Algorithm.makeKey(k);
		byte cipher[];
		for(int i=0; i<4; i++) {
			cipher = Twofish_Algorithm.blockEncrypt(p, i*16 , key);
			String cipherText = new String(cipher);
			System.out.print(cipherText);
		}
		
		
		
		
	}

}
