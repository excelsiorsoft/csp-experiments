/**
 * 
 */
package eratosthenes.sieve;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.plugNplay.ints.PrinterInt;

/**
 * @author Simeon
 *
 */
public class EratosthenesCSP {


	public static final class NoMultiples implements CSProcess{

		private final int n;
		private final ChannelInputInt in;
		private final ChannelOutputInt out;
		
		
		
		public NoMultiples(int n, ChannelInputInt in, ChannelOutputInt out) {
			super();
			this.n = n;
			this.in = in;
			this.out = out;
		}



		@Override
		public void run() {
			while(true) {
				int a = in.read();
				if(( a % n) != 0) out.write(a);
			}
			
		}
		
	}
	
	public final static class Sieve implements CSProcess {

		private final ChannelInputInt in;
		private final ChannelOutputInt out;
		
		
		
		public Sieve(ChannelInputInt in, ChannelOutputInt out) {
			super();
			this.in = in;
			this.out = out;
		}



		@Override
		public void run() {
			int n = in.read();
			out.write(n);
			One2OneChannelInt c = Channel.one2oneInt();
			new Parallel(
					new CSProcess[] {
							new NoMultiples(n, in, c.out()),
							new Sieve(c.in(), out)
					}
					).run();
		}

	}

	public final static class NumbersFrom implements CSProcess {

		private final int start;
		private final int increment;
		private final ChannelOutputInt out;
		
		
		
		public NumbersFrom(int start, int increment, ChannelOutputInt out) {
			super();
			this.start = start;
			this.increment = increment;
			this.out = out;
		}



		@Override
		public void run() {
			int n = start;
			while (true) {
				out.write(n);
				n += increment;
			}
		}

	}

	public final static class Primes implements CSProcess {

		private final ChannelOutputInt out;
		
		public Primes(ChannelOutputInt out) {
			this.out = out;
		}

		@Override
		public void run() {
			out.write(2);
			One2OneChannelInt c = Channel.one2oneInt();
			new Parallel(
					new CSProcess[] {
							new NumbersFrom(3, 2, c.out()),
							new Sieve(c.in(), out)
					}
					).run();
		}

	}

	public static void main(String[] args) {
		One2OneChannelInt c = Channel.one2oneInt();
		new Parallel(
				new CSProcess[] {
						new Primes(c.out()),

						new PrinterInt(c.in(), "--> ", "\n")
				}
				).run();
	}
	
}
