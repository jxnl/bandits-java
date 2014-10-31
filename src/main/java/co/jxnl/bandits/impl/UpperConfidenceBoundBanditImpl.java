package co.jxnl.bandits.impl;

import java.util.List;

import co.jxnl.bandits.AbstractBanditAlgorithm;
import co.jxnl.bandits.arms.BanditArm;
import co.jxnl.bandits.arms.MetricUbc;

/**
 * This implementation uses the Upper Confidence Bound to select the best
 * available arm.
 * 
 * @author jliu
 *
 */
public class UpperConfidenceBoundBanditImpl extends AbstractBanditAlgorithm {

	private static final int MAX_GREEDY = 1;

	public UpperConfidenceBoundBanditImpl(String name,
			List<BanditArm> banditArms) {
		super(name, banditArms, MAX_GREEDY, new MetricUbc());
	}

	/**
	 * The objective is to be optimistic and keep sampling the bestarm, the more
	 * samples you take the lower the upper bound gets.
	 * 
	 * ubc_arm = mean_arm + sqrt(ln(2 * pulls_total) / pulls_arm)
	 */
	public void sample() {
		setTimesSampled(getTimesSampled() + 1);
		sampleBestArm();
	}
}