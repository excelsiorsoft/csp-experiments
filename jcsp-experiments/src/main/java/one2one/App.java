package one2one;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.CSTimer;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;

public class App {

	public class Consumer implements CSProcess {

		private final ChannelInputInt in;
		private String name;
		
		public Consumer(ChannelInputInt in, String string) {
			this.in = in;
			this.name = string;
		}
		
		public void run() {
			while(true) {
				System.out.println(name+": "+in.read());
			}
		}

	}

	public class Producer implements CSProcess {

		private final ChannelOutputInt out;
		private int k;
		
		public Producer(ChannelOutputInt out, int k) {
			this.out = out;
			this.k = k;
		}
		
		public void run() {
			CSTimer timer  = new CSTimer();
			for(int i = 0; i<10; i++) {
				out.write(k++);
				timer.sleep(250);
			}
		}

	}

	public static void main(String[] args) {
		
		//channel is similar to synchronousQueue in j.u.c.                    
		
		One2OneChannelInt channel = Channel.one2oneInt();
		
		new Parallel(new CSProcess[] {
				new App().new Producer(channel.out(), 0),
				new App().new Consumer(channel.in(), "dst")
		}).run();
	}
	
}
