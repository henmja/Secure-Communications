Secure communications

The goal of this project is to simulate secure communications between Alice and Bob by using Diffie-Hellman's key exchange scheme to generate shared keys (step 1-4), the Blum-Blum-Shub algorithm as CSPRNG to further enhance the security of the shared key (step 5) and SDES with the shared key to encrypt Alice's message (step 6). In the end Bob decrypts Alice's message using the shared key.      

Design and implementation 

In step 1 Alice and Bob agree on the public parameters p and g (which I generate through input) to generate the cyclic group: g^x mod p, x ~ [0,p-1]. 
In step 2 Alice and Bob generate each their public and private keys. The private keys are generated through input. The public keys are generated using the formula given in the first paragraph where x is the private key. 
In step 3 Alice and Bob exchange public keys. I used getters to simulate the exchange. 
In step 4 Alice and Bob generate each their identical shared key using the formula from the first paragraph again, but now g is their public keys. 
In step 5 Alice and Bob enhances the security of their shared key by generating each their secret key using the shared key from step 4 as the seed. The secret key is obtained by using the following formula ten times and adding the lsb from the result of the formula to the secret key: Xn+1=Xn^2 mod m where x is the seed and m is the product of two primes of their (Alice’s and bob’s) choosing in the interval [0,x-1]. 
In step 6 Alice encrypts plaintext generated through input in binary form using SDES with the raw key she generated in step 5. 
In step 7 Bob decrypts the cipher generated by Alice using SDES and the raw key he generated in step 5. 

How to run - input: 
1. Cyclic group (DH)/prime number.
2. base (DH)/primitive prime number of the number from 1.
3. Alice's private key (DH)/integer lower than the cyclic group.
4. Bob's private key (DH)/integer lower than the cyclic group.
5. Alice's first prime as input for BBS.
6. Alice's second prime as input for BBS.
7. Bob's first prime as input for BBS.
8. Bob's second prime as input for BBS.
9. Alice's plaintext to be encrypted (8 binary numbers).

Test Results

Using the parameters: 23 (public parameter prime, p), 9 (public parameter generator, g), 4 (Alice’s private key), 3 (Bob’s private key), 19 (Alice’s first prime for generating shared key), 11 (Alice’s second prime), 19 (Bob’s first prime for generating shared key), 11 (Bob’s second prime), 10101010 (binary String Alice encrypts) results in the decrypted binary String, 10101010. 
