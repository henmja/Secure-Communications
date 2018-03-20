package secCom;

import java.math.BigInteger;


public class Person {
	private BigInteger privateKey;
	private BigInteger publicKey;
//Method used for step 4:
	public BigInteger generateSharedKey(BigInteger r, BigInteger pk, BigInteger p) {
	    BigInteger key=r.modPow(pk,p);
	    return key;
	}
//Generate public key for step 2:
	public void calculatePublicKey(BigInteger privateKey, BigInteger g, BigInteger p) {
	    setPublicKey(g.modPow(privateKey,p));
	}
//return private key for step 2:
	public BigInteger getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(BigInteger privateKey) {
		this.privateKey = privateKey;
	}

	public BigInteger getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(BigInteger publicKey) {
		this.publicKey = publicKey;
	}

	}
