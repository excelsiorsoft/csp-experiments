/**
 * 
 */
package integrate;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.plugNplay.ints.Delta2Int;
import org.jcsp.plugNplay.ints.PlusInt;
import org.jcsp.plugNplay.ints.PrefixInt;

/**
 * @author Simeon
 *
 */
public class MyIntegrateInt implements CSProcess {

	private final ChannelInputInt in;
	private final ChannelOutputInt out;
	
	public MyIntegrateInt(ChannelInputInt in, ChannelOutputInt out) {
		super();
		this.in = in;
		this.out = out;
	}


	@Override
	public void run() {
		One2OneChannelInt a = Channel.one2oneInt();
		One2OneChannelInt b = Channel.one2oneInt();
		One2OneChannelInt c = Channel.one2oneInt();

		new Parallel(
				new CSProcess[] {
						//new PlusInt(in, c.in(), a.out()),
						new MyPlusInt(in, c.in(), a.out()),
						new Delta2Int(a.in(), out, b.out()),
						new PrefixInt(0, b.in(), c.out())
				}).run();
	}

}
