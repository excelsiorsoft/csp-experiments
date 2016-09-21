package one2one;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelSymmetricInt;
import org.jcsp.lang.Parallel;

@Deprecated
public class AllAlternative {
	
	public static void main(String[] args) {
		
		One2OneChannelSymmetricInt chan0 = Channel.one2oneSymmetricInt();
		One2OneChannelSymmetricInt chan1 = Channel.one2oneSymmetricInt();
		One2OneChannelSymmetricInt chan2 = Channel.one2oneSymmetricInt();
		
		new Parallel(new CSProcess[] {
				//couldn't find the impl of MultiSrc
				//new MultiSrc(chan0.out(), chan1.out(), chan2.out()), 
				//new MultiSrc(chan0.in(), chan1.in(), chan2.in()),
		}).run();
	}

}
