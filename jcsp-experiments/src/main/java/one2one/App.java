package one2one;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;

public class App {

	public static void main(String[] args) {
		
		One2OneChannelInt channel = Channel.one2oneInt();
		new Parallel(new CSProcess[] {
				new Producer(channel.out(), 0),
				new Consumer(channel.in(), "dst")
		}).run();
	}
	
}
