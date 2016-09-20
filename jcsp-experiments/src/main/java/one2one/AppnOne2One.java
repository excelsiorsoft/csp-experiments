/**
 * 
 */
package one2one;

import java.util.Random;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.CSTimer;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;

/**
 * @author Simeon
 *
 */
public class AppnOne2One {

	public static void main(String[] args) {

		final One2OneChannelInt c = Channel.one2oneInt();
		new Parallel(new CSProcess[] { 
				
				() -> {
					Random rnd = new Random(0);
					CSTimer timer = new CSTimer();
					for (int i = 0; i < 20; i++) {
						timer.sleep(500);
						c.out().write(1 + rnd.nextInt(100));
					}
				} ,
				
				() -> {
					while(true) {
						System.out.println(c.in().read());
					}
				} 
		
		}).run();
	}
}
