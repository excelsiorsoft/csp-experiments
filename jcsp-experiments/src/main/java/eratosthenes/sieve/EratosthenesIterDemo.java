/**
 * 
 */
package eratosthenes.sieve;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Simeon
 *
 */

public class EratosthenesIterDemo{
	
	public static final class EratosthenesIter implements Iterator<Integer>{

		private List<Integer> primes = new ArrayList<>(asList(2));
		
		public Integer next() {
			int k = primes.get(primes.size() - 1);
			if(primes.size() == 1) System.out.print(k + ", ");
			next_probe:
				while(true) {
					k++;
					for (int prime : primes) {
						if(k % prime == 0) {
							continue next_probe;
						}
					}
					primes.add(k);
					return k;
				}
		}
		
		public boolean hasNext(){
			return true;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	
	public static void main(String[] args) {
		// iteration is lazy
		final int howMany = 10; 
		int counter = 0;
		for (Iterator<Integer> primes = new EratosthenesIter(); primes.hasNext() && counter < howMany; counter++) {
			
			if (counter < (howMany-1)) {
				System.out.print(primes.next() + ", ");
			} else {
				System.out.print(primes.next());
			}

		}

	}
		
	
}


