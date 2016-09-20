package integrate;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.CSTimer;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.plugNplay.ints.IntegrateInt;

public class IntegrateIntApp {

	public static void main(String[] args) {
		
		final One2OneChannelInt to = Channel.one2oneInt();
		final One2OneChannelInt from = Channel.one2oneInt();
		
		new Parallel(
				new CSProcess[] {
						() -> {
							CSTimer timer = new CSTimer();
							for(int i=0; i<10; i++) {
								to.out().write(1);
								timer.sleep(250);
							}
						}, 
						
						new integrate.MyIntegrateInt(to.in(), from.out()),
						
						//IntegrateInt also comes with JCSP
						//new IntegrateInt(to.in(), from.out()),
						
						() -> {
								CSTimer timer = new CSTimer();
								while(true) {
									System.out.println(from.in().read());
								}
						}
						
						
				}).run();
	}
	
}
