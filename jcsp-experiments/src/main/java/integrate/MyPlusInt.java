/**
 * 
 */
package integrate;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutputInt;

/**
 * @author Simeon
 *
 */
public class MyPlusInt implements CSProcess {

	private final ChannelInputInt in0;
	private final ChannelInputInt in1;
	private final ChannelOutputInt out;
	
	public MyPlusInt(ChannelInputInt in0, ChannelInputInt in1, ChannelOutputInt out) {
		super();
		this.in0 = in0;
		this.in1 = in1;
		this.out = out;
	}

	@Override
	public void run() {
		
		while(true) {
			out.write(in0.read() + in1.read());
		}

	}

}
