package secCom;

public class BBSGeneration {
	private int p, q, M, seed, actual;
	   public BBSGeneration(int s, int p, int q) {
	       this.p = p;
	       this.q = q;
	       this.M = p*q;
	       this.seed = s;
	       this.actual = s;
	   }
	 
	   public int getSecret() {
		   
		   //Formula: Xn+1=Xn^2 mod(M)
	       int sk = (actual*actual)%M; 
	       actual = sk;
	       return sk;
	   }
	 }
