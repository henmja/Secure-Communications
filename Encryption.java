package secCom;

class Encryption
{
  private int[] text = new int[8];
  private int[] key1 = new int[8];
  private int[] key2 = new int[8];
  //Initial Permutation
  public void IP()	{
    int[] out = new int[8];
    
    out[0] = text[1];
    out[1] = text[5];
    out[2] = text[2];
    out[3] = text[0];
    out[4] = text[3];
    out[5] = text[7];
    out[6] = text[4];
    out[7] = text[6];
    
    text = out;
  } 
  //Inverse Initial Permutation
  public void invIP()	{
    int[] out = new int[8];
    
    out[0] = text[3];
    out[1] = text[0];
    out[2] = text[2];
    out[3] = text[4];
    out[4] = text[6];
    out[5] = text[1];
    out[6] = text[7];
    out[7] = text[5];
    
    text = out;
  }
  
  public void initVars(int[] k1, int[] k2, String plainText)
  {
	int[] text = new int[8];
	String s;
	char c;
	for(int i=0;i<8;i++)
    {
       c = plainText.charAt(i);
       s = Character.toString(c);
       text[i] = Integer.parseInt(s);
    }
	
    this.key1 = k1;
    this.key2 = k2;
    this.text = text;
  }
  
//EP, XOR with K1, use S-boxes to generate 4 bits from 8, P4
  int[] repOps(int[] rightBits, int[] subKey)
  {
    int[] out = new int[8];
    //EP
    out[0]  = rightBits[3];
    out[1]  = rightBits[0];
    out[2]  = rightBits[1];
    out[3]  = rightBits[2];
    out[4]  = rightBits[1];
    out[5]  = rightBits[2];
    out[6]  = rightBits[3];
    out[7]  = rightBits[0];
	 
    // XOR with subkey
    out[0] = out[0] ^ subKey[0];
    out[1] = out[1] ^ subKey[1];
    out[2] = out[2] ^ subKey[2];
    out[3] = out[3] ^ subKey[3];
    out[4] = out[4] ^ subKey[4];
    out[5] = out[5] ^ subKey[5];
    out[6] = out[6] ^ subKey[6];
    out[7] = out[7] ^ subKey[7];
	 
    //S-boxes
    final int[][] S0 = {{1,0,3,2} , {3,2,1,0} , {0,2,1,3} , {3,1,3,2}};
    final int[][] S1 = {{0,1,2,3},  {2,0,1,3}, {3,0,1,0}, {2,1,0,3}};
    
   
    int first = out[0]; 
    int fourth = out[3];
	int r1 = ConvMethods.toDec(first,fourth);
      
	  
    int second = out[1];
    int third = out[2];     
    int c1 = ConvMethods.toDec(second,third);
      
	  
    int o1 = S0[r1][c1];   
	int[] res1 = ConvMethods.toBin(o1);

	int fifth = out[4];
    int eigth = out[7];
    int r2 = ConvMethods.toDec(fifth,eigth);
	  
	int sixth = out[5];
	int seventh = out[6];
	int c2 = ConvMethods.toDec(sixth,seventh);
	  
	int o2 = S1[r2][c2]; 	 
	int[] res2 = ConvMethods.toBin(o2); 
		
      //4 result bits from s-boxes
	  int[] result = new int[4];
	  result[0] = res1[0];
      result[1] = res1[1];
	  result[2] = res2[0];
	  result[3] = res2[1];
	  
	  //P4
	  int[] P4 = new int[4];
	  P4[0] = result[1];
	  P4[1] = result[3];
	  P4[2] = result[2];
      P4[3] = result[0];
	 
	 return P4;
  }
  
//XOR left 4 bits with result from P4 in repOps
  int[] P4XOR(int[] left4Bits, int[] right4Bits,int[] subKey)
  {	
	int[] P4Res = new int[4];
	int[] output = new int[8];
	
	
	P4Res = repOps(right4Bits,subKey);
	
	
	output[0] = left4Bits[0] ^ P4Res[0];
	output[1] = left4Bits[1] ^ P4Res[1];
	output[2] = left4Bits[2] ^ P4Res[2];
	output[3] = left4Bits[3] ^ P4Res[3];
	
	output[4] = right4Bits[0];
	output[5] = right4Bits[1];
	output[6] = right4Bits[2];
	output[7] = right4Bits[3];
	
	
	return output;
	
	
  }
  
//swap 4 left and right bits
  	int[] swapLRBits(int[] input)	{
	
	int[] output = new int[8];
	
	output[0] = input[4];
	output[1] = input[5];
	output[2] = input[6];
	output[3] = input[7];
  
	output[4] = input[0];
	output[5] = input[1];
	output[6] = input[2];
	output[7] = input[3];	
	
	return output;
  }

  int[] encrypt(String plaintext , int[] key1, int[] key2)	{
	initVars(key1,key2,plaintext);
	IP();
	int[] leftBits = new int[4];
	int[] rightBits = new int[4];
	leftBits[0] = text[0];
	leftBits[1] = text[1];
	leftBits[2] = text[2];
	leftBits[3] = text[3];
	

	rightBits[0] = text[4];
	rightBits[1] = text[5];
	rightBits[2] = text[6];
	rightBits[3] = text[7];
	
	int[] res = new int[8];
	res = P4XOR(leftBits,rightBits,key1);
	int[] swapRes = new int[8];
	swapRes = swapLRBits(res);

	leftBits[0] = swapRes[0];
	leftBits[1] = swapRes[1];
	leftBits[2] = swapRes[2];
	leftBits[3] = swapRes[3];
	
	rightBits[0] = swapRes[4];
	rightBits[1] = swapRes[5];
	rightBits[2] = swapRes[6];
	rightBits[3] = swapRes[7];
	
	int[] res2 = new int[8];
	res2 = P4XOR(leftBits,rightBits,key2);
	text = res2;
	 
	invIP();

	return text;
	
	
	
	
  }
 
}
