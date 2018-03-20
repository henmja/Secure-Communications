package secCom;

class ConvMethods
{ 
  public static int[] toBin(int input)	{
	if(input==0)	{
		int[] o = new int[2];
		o[0] = 0;
		o[1] = 0;
		return o;	
	}
    int[] out = new int[10];
	int n = 0 ;
    for(int i= 0 ; input!= 0 ; i++)	{
      out[i] = input % 2;
      input = input/2;
	  n++;
    }
    
	
	int[] out2 = new int[n];
	
	
	for(int i=n-1, j=0;i>=0 && j<n;i--,j++)	{
		out2[j] = out[i];
	}
	
    if(n<2)	{
		out = new int[2];
		out[0] = 0;
		out[1] = out2[0];
		return out;
	}
	 
	return out2;
  }
  public static int toDec(int...bits)	{
	     int par = 1;
	     int output=0;
	     for(int i=bits.length-1 ; i>=0;i--)	{
	        output = output + (par*bits[i]);
	        par*=2;
	     }
	      return output;
	  }
}
