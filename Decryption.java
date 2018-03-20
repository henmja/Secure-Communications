package secCom;

public class Decryption {
	public int[] decrypt(int[] cipher, int k1[], int k2[]) {
		
		int swap[] = new int[8];
		int plainText[] = new int[8];
		//initial permutation:
		int[] temp = new int[8];
		temp[0]=cipher[1];
		temp[1]=cipher[5];
		temp[2]=cipher[2];
		temp[3]=cipher[0];
		temp[4]=cipher[3];
		temp[5]=cipher[7];
		temp[6]=cipher[4];
		temp[7]=cipher[6];
		
		int[] ipres= new int[8];
		for (int i = 0; i<8; i++) {
			cipher[i]=temp[i];
			ipres[i]=cipher[i];
		}
		System.out.println("Decrypt START -------\nip: ");
		for (int i = 0; i<ipres.length; i++) {
			System.out.print(ipres[i]);
		}
		System.out.println("\nEP: ");
		//EP (use 4 bits to the right to expand to 8 bits:
		temp[0]=cipher[7];
		temp[1]=cipher[4];
		temp[2]=cipher[5];
		temp[3]=cipher[6];
		temp[4]=cipher[5];
		temp[5]=cipher[6];
		temp[6]=cipher[7];
		temp[7]=cipher[4];
		
		for (int i = 0; i<cipher.length; i++) {
		cipher[i] = temp[i];
		}
		
		for (int i = 0; i<cipher.length; i++) {
			System.out.print(cipher[i]);
		}
		System.out.println("\nXOR K2: ");
		//XOR cipher with K2:
		
		for (int i = 0; i<cipher.length; i++) {
			cipher[i] = cipher[i] ^ k2[i];
		}
		
		for (int i = 0; i<cipher.length; i++) {
			System.out.print(cipher[i]);
		}
		System.out.println("");
		
		// S-Boxes
	    final int[][] S0 = { {1,0,3,2} , {3,2,1,0} , {0,2,1,3} , {3,1,3,2} } ;
	    final int[][] S1 = { {0,1,2,3},  {2,0,1,3}, {3,0,1,0}, {2,1,0,3}} ;
	    
	      int first = cipher[0];
	      int fourth = cipher[3];
	      
		  int r1 = ConvMethods.toDec(first,fourth); 
		  
	      int second = cipher[1]; 
	      int third = cipher[2];       
	      int c1 = ConvMethods.toDec(second,third);
	      
		  
	      int o1 = S0[r1][c1]; 
		      
		  int[] out1 = ConvMethods.toBin(o1);

		  int fifth = cipher[4]; 
	      int eight = cipher[7]; 
	      int r2 = ConvMethods.toDec(fifth,eight);
		  
		  int sixth = cipher[5]; 
		  int seventh = cipher[6]; 
		  int c2 = ConvMethods.toDec(sixth,seventh);
		  
		  int o2 = S1[r2][c2];
		  	 
		  int[] out2 = ConvMethods.toBin(o2); 
			
		  int[] output = new int[4];
		  output[0] = out1[0];
	      output[1] = out1[1];
		  output[2] = out2[0];
		  output[3] = out2[1];
		  
		  System.out.println("Out: ");
	      for (int i = 0; i<output.length; i++) {
				System.out.print(output[i]);
			}
			System.out.println("");
		  
		  //P4
		  
		  int [] P4 = new int[4];
		  P4[0] = output[1];
		  P4[1] = output[3];
		  P4[2] = output[2];
	      P4[3] = output[0];
	      
	      System.out.println("out with P4: ");
	      for (int i = 0; i<P4.length; i++) {
				System.out.print(P4[i]);
			}
			System.out.println("\nipres before XOR after P4 ");
		      for (int i = 0; i<ipres.length; i++) {
					System.out.print(ipres[i]);
				}
				System.out.println("");
	      for (int i = 0; i<4; i++) {
	    	  ipres[i] = P4[i] ^ ipres[i];
	      }
	      System.out.println("XOR after P4 ");
	      for (int i = 0; i<P4.length; i++) {
				System.out.print(P4[i]);
			}
			System.out.println("");
		  for (int i = 0; i<cipher.length; i++) {
			  cipher[i]=ipres[i];
		   }
	      //swap 4 left and 4 right bits
	      temp[0]=cipher[4];
	      temp[1]=cipher[5];
	      temp[2]=cipher[6];
	      temp[3]=cipher[7];
	      temp[4]=cipher[0];
	      temp[5]=cipher[1];
	      temp[6]=cipher[2];
	      temp[7]=cipher[3];
	      for (int i = 0; i<cipher.length; i++) {
	      cipher[i]=temp[i];
	      swap[i] = cipher[i];
	      }
	      //EP: 4,1,2,3, 2,3,4,1 expand to 8 bits using the 4 right bits
	      temp[0]=cipher[7];
	      temp[1]=cipher[4];
	      temp[2]=cipher[5];
	      temp[3]=cipher[6];
	      temp[4]=cipher[5];
	      temp[5]=cipher[6];
	      temp[6]=cipher[7];
	      temp[7]=cipher[4];
	      for (int i = 0; i<cipher.length; i++) {
	      cipher[i]=temp[i];
	      }
	      //XOR with K1:
	      for (int i = 0; i<cipher.length; i++) {
	    	  cipher[i] = cipher[i]^k1[i];
	      }
	      //use s tables again:
	      first = cipher[0]; 
	      fourth = cipher[3]; 
	      
		  r1 = ConvMethods.toDec(first,fourth);
		  
	      second = cipher[1]; 
	      third = cipher[2];     
	      c1 = ConvMethods.toDec(second,third); 
	      
		  
	      o1 = S0[r1][c1]; 
		      
		  out1 = ConvMethods.toBin(o1);

		  fifth = cipher[4]; 
	      eight = cipher[7];
	      r2 = ConvMethods.toDec(fifth,eight);
		  
		  sixth = cipher[5]; 
		  seventh = cipher[6]; 
		  c2 = ConvMethods.toDec(sixth,seventh);
		  
		  o2 = S1[r2][c2];
		  	 
		  out2 = ConvMethods.toBin(o2); 
		  
		  output[0] = out1[0];
	      output[1] = out1[1];
		  output[2] = out2[0];
		  output[3] = out2[1];
		  
		  //P4
		  
		  
		  P4[0] = output[1];
		  P4[1] = output[3];
		  P4[2] = output[2];
	      P4[3] = output[0];
	      
	      //XOR P4 with four left bits in result after swap
		  for (int i =0; i<4; i++) {
			  swap[i] = P4[i] ^ swap[i];
		  }
	      for (int i = 0; i<cipher.length; i++) {
		  cipher[i]=swap[i];
	      }
		  //Inverse initial permutation (4,1,3,5, 7,2,8,6) gives plaintext:
		  temp[0] = cipher[3];
		  temp[1] = cipher[0];
		  temp[2] = cipher[2];
		  temp[3] = cipher[4];
		  temp[4] = cipher[6];
		  temp[5] = cipher[1];
		  temp[6] = cipher[7];
		  temp[7] = cipher[5];
	      for (int i = 0; i<cipher.length; i++) {
		  cipher[i]=temp[i];
		  plainText[i] = cipher[i];
	      }
		  return plainText;
		  
		  
	}

}
