package secCom;

import java.util.*;

public class GenerateKeys	{
	//10 bit rawkey
  private int[] rawKey = new int[10];
 //subkeys:
  private int[] key1 = new int[8];
  private int[] key2 = new int[8];
  
  public void genKeys(String inRawKey)	{
    int[] rk = new int[10];
	String s ;
	char c;
	
	for(int i=0;i<10;i++)	{
       c = inRawKey.charAt(i);
       s = Character.toString(c);
       rk[i] = Integer.parseInt(s);
    }
	
    this.rawKey = rk;
//apply permutation p10 to rawkey
    P10();
//move 5 left bits 1 position to the left, then do the same for the 5 right bits in the rawkey
    LS1();
//the first key is the result from applying permutation p8 to the result from LS1
    this.key1 = P8();
//leftshift the result from LS1
    LS2();
//apply permutation p8 ot the last result for the second key:    
    this.key2 = P8();

}
  private void LS1()	{
    int[] out = new int[10];
	    
    out[0] = rawKey[1];
    out[1] = rawKey[2];
    out[2] = rawKey[3];
    out[3] = rawKey[4];
    out[4] = rawKey[0];
	    
    out[5] = rawKey[6];
    out[6] = rawKey[7];
    out[7] = rawKey[8];
    out[8] = rawKey[9];
    out[9] = rawKey[5];
	    
	  rawKey = out;
	    
   }
//Permutation P10
  private void P10()	{
    int[] out = new int[10];
    
    out[0] = rawKey[2];
    out[1] = rawKey[4];
    out[2] = rawKey[1];
    out[3] = rawKey[6];
    out[4] = rawKey[3];
    out[5] = rawKey[9];
    out[6] = rawKey[0];
    out[7] = rawKey[8];
    out[8] = rawKey[7];
    out[9] = rawKey[5];
    
    
    rawKey = out;
        
  }
  
  private void LS2()
  {
    int[] out = new int[10];
    
    out[0] = rawKey[2];
    out[1] = rawKey[3];
    out[2] = rawKey[4];
    out[3] = rawKey[0];
    out[4] = rawKey[1];
    
    out[5] = rawKey[7];
    out[6] = rawKey[8];
    out[7] = rawKey[9];
    out[8] = rawKey[5];
    out[9] = rawKey[6];
    
    rawKey = out;
    
  }
  
  private int[] P8()
  {
    int[] out = new int[8];
    
    out[0] = rawKey[5];
    out[1] = rawKey[2];
    out[2] = rawKey[6];
    out[3] = rawKey[3];
    out[4] = rawKey[7];
    out[5] = rawKey[4];
    out[6] = rawKey[9];
    out[7] = rawKey[8];
    
    return out;
        
  }


public int[] getK1()	{
    return key1;
}

public int[] getK2()	{
    return key2;
}  

}
