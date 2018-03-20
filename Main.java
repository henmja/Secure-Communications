package assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Main {
	public static void main(String[] args) throws IOException { 
			// TODO Auto-generated method stub
			//STEP 1 agree on parameters:
			System.out.println("Step 1 - Bob and Alice agree on public parameters to generate the cyclic "
					+ "group:");
	        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Enter prime number:");
	        BigInteger p=new BigInteger(br.readLine());
	        System.out.print("Enter primitive root of "+p+":");
	        BigInteger g=new BigInteger(br.readLine());
	        System.out.println("Cyclic group Z" + p + " with generator g="+g+
	        		": "+g+"^x mod "+p+", x~[0,22]");
	        //STEP 2
	      //x secret key Alice
	        Person alice = new Person();
	        System.out.println("\nStep 2 - Generate private keys for Alice and Bob respectively:");
	        System.out.println("Enter value less than "+p+":");
	        BigInteger x=new BigInteger(br.readLine());
	        alice.setPrivateKey(x);
	        System.out.println("Alice's Private Key: " + alice.getPrivateKey());
	        //R1 public key Alice        
	        alice.calculatePublicKey(alice.getPrivateKey(),g,p);
	        BigInteger R1 = alice.getPublicKey();
	        System.out.println("Alice's Public Key: "+R1);
	        //y secret key Bob:
	        Person bob = new Person();
	        System.out.println("Enter value less than "+p+":");
	        BigInteger y=new BigInteger(br.readLine());
	        bob.setPrivateKey(y);
	        System.out.println("Bob's Private Key: " + bob.getPrivateKey());
	        //R2 Public key Bob
	        bob.calculatePublicKey(bob.getPrivateKey(),g,p);
	        BigInteger R2 = bob.getPublicKey();
	        System.out.println("Bob's Public Key: "+R2);
	        
	        //STEP 3 exchange public keys:
	        System.out.println("Step 3 - Alice and Bob exchange public keys.:");
	        R1=bob.getPublicKey();
	        R2=alice.getPublicKey();
	        System.out.println("Alice sends key '" + R2 + "'" + " to Bob, Bob sends key: '" + R1 + "'" +
	        " to Alice");
	        
	        //STEP 4 Alice and Bob generate each their shared key:
	        BigInteger sk1=alice.generateSharedKey(R1, x, p);
	        BigInteger sk2=bob.generateSharedKey(R2, y, p);
	        System.out.println("Step 4 - Alice and bob generates each their identical shared key. "
	        		+ "Alice's shared key: " + sk1 + ", Bob's Shared key: " + sk2);
	        
	        //STEP 5 generates secret key:
	      //TODO Using a BBS generator as CSPRNG:
	        //BBS Formula: Xn+1=X^2modM
	        System.out.println("\nStep 5 - Using a BBS generator as CSPRNG: ");
	        System.out.println("Enter Alice's first prime number: \n");
	        Integer prime1=new Integer(br.readLine());
	        System.out.println("Enter Alice's second prime number: \n");
	        Integer prime2=new Integer(br.readLine());
	        int seed = sk1.intValue();

	        BBSGeneration b = new BBSGeneration(seed,prime1,prime2);
	        String rawKeyAlice = "";
	        String binaryBBS = "";
	        //generate 10 bit rawKey from lsb in each BBS result:
	        for(int i = 0; i <= 9; ++i) {
	            binaryBBS = Integer.toBinaryString((int) b.getSecret());
	            rawKeyAlice+=binaryBBS.charAt(binaryBBS.length()-1);
	            binaryBBS="";
	        }
	        System.out.println("Alice's raw key/secret key: " + rawKeyAlice);
	        
	        //Generating Bob's shared key aswell to simulate the communication scenario:
	        System.out.println("Enter Bob's first prime number: \n");
	        int prime3=new Integer(br.readLine());
	        System.out.println("Enter Bob's second prime number: \n");
	        int prime4=new Integer(br.readLine());
	        int seed2 = sk2.intValue();

	        BBSGeneration b2 = new BBSGeneration(seed2,prime3,prime4);
	        String rawKeyBob = "";
	        String binaryBBS2 = "";
	        //generate 10 bit rawKey from lsb in each BBS result:
	        for(int i = 0; i <= 9; ++i) {
	            binaryBBS2 = Integer.toBinaryString((int) b2.getSecret());
	            rawKeyBob+=binaryBBS2.charAt(binaryBBS2.length()-1);
	            binaryBBS2="";
	        }
	        System.out.println("Bob's raw key/secret key: " + rawKeyBob);
	        
	        //Step 6 Alice sends and encrypts message
	        System.out.println("Step 6 - Alice sends and encrypts message.");
	        GenerateKeys GK = new GenerateKeys();
			GK.genKeys(rawKeyAlice);
			int k1Alice[] = GK.getK1();
			int k2Alice[] = GK.getK2();
			
			//Encrypt:
			
			Encryption enc = new Encryption();
			System.out.println("Alice's plaintext in binary format: \n");
			String plainText = new String(br.readLine());

			int output[] = enc.encrypt(plainText, k1Alice, k2Alice);
			System.out.println("Result from Alice's encryption: ");
			for (int i = 0; i<output.length;i++) {
				System.out.print(output[i]);
			}
			//step 7 Bob decrypts message from Alice
			System.out.println("step 7 - Bob decrypts message from Alice.");
			GenerateKeys GK2 = new GenerateKeys();
			GK2.genKeys(rawKeyBob);
			int k1Bob[] = GK2.getK1();
			int k2Bob[] = GK2.getK2();
			
			Decryption decrypt = new Decryption();
			System.out.println("\nBob's cipher received from Alice: ");
			int cipher[] = output;
			for (int i = 0; i<cipher.length;i++) {
				System.out.print(cipher[i]);
			}
			System.out.println("\n");
			int out[] = decrypt.decrypt(cipher, k1Bob, k2Bob);
			System.out.println("Bob's decryption: ");
			for (int i = 0; i<out.length;i++) {
				System.out.print(out[i]);
			}
	        
			
		}	

	}
