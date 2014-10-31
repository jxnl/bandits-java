package co.jxnl.bandits.impl;

import java.util.List;

import co.jxnl.bandits.AbstractBanditAlgorithm;
import co.jxnl.bandits.arms.BanditArm;
import co.jxnl.bandits.arms.MetricMean;

/**
 * This implementation uses the maximum likelihood estimate to select the best
 * available arm.
 * 
 * @author JasonLiu
 *
 */
public class GreedyEpsilonBanditImpl extends AbstractBanditAlgorithm {

	public GreedyEpsilonBanditImpl(String name, List<BanditArm> banditArms,
			double epsilon) {
		super(name, banditArms, epsilon, new MetricMean());
	}

	private boolean exploreArms() {
		return getEpsilon() > getRandom().nextDouble();
	}

	/**
	 * The objective is to either exploit the best known arm or explore a random
	 * one. An arm is exploited p% of the time which is decided by epsilon. In
	 * this naive implementation, epsilon does not decrease over time.
	 */
	public void sample() {
		setTimesSampled(getTimesSampled() + 1);
		if (exploreArms()) {
			sampleRandomArm();
		} else {
			sampleBestArm();
		}
	}
}