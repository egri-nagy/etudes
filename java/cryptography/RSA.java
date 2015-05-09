/*
 * A naive implementation of the RSA algorithm.
 */
package cryptography;

//immutable class
public class RSA{
    // p and q are 'big' primes (limited to 63-bits here)
    private final long p;
    private final long q;
    private final long n;
    private final long phin;
    //positive integer e such that gcd(e,phi(n))=1
    private final long e;
    //positive integer d such that de=1 mod phi(n)
    private final long d;

    public RSA(long p, long q, long e, long d){
        this.p = p;
        this.q = q;
        n = p*q;
        phin = (p-1) * (q-1);
        this.e = e;
        this.d = d;
    }

    public static long modularPow(long base, long exponent, long modulus){
        long result = base;
        for (int i = 1; i < exponent; i++){
            result = (result * base) % modulus;
        }
        return result;
    }

    //encryption key is (n,e)
    public long encryptBlock(long block) {return  modularPow(block,e,n);}

    //encryption key is (n,d)
    public long decryptBlock(long block) {return  modularPow(block,d,n);}

}
