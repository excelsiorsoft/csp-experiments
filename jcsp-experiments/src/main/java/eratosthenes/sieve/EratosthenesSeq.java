/**
 * 
 */
package eratosthenes.sieve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Simeon
 *
 */
public class EratosthenesSeq {

	public static void main(String[] args) {
		int N = 1000;
		
		//initially assume that all integers are primes (except 0 and 1)
		boolean[] isPrime = new boolean [N +1];
		Arrays.fill(isPrime, 2, N, true);
		
		
		for(int i = 2; i * i <= N; i++) { // equivalent to i < Math.sqrt(N) but less resource intensive
			if(isPrime[i]) {
				for(int j = i; i * j <= N; j++) {
					isPrime[i * j] = false;
				}
			}
		}
		
		List<Integer> result = new ArrayList<>(N);
		for(int i = 0; i<isPrime.length; i++) {
			if(isPrime[i] == true) {
				result.add(i);
			}
		}
		System.out.println(result);
	}
}
