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
public class DecayingGreedyEpsilonBanditImpl extends AbstractBanditAlgorithm {

	public DecayingGreedyEpsilonBanditImpl(String name,
			List<BanditArm> banditArms, double epsilon, double alpha) {
		super(name, banditArms, epsilon, new MetricMean());
		this.setAlpha(alpha);
	}

	private boolean exploreArms() {
		return getEpsilon() > getRandom().nextDouble();
	}

	/**
	 * The objective is to either exploit the best known arm or explore a random
	 * one. An arm is exploited p% of the time which is decided by epsilon. In
	 * this implementation, epsilon decreases over time as to be less risk
	 * taking.
	 */
	public void sample() {
		setEpsilon(getEpsilon() * getAlpha());
		setTimesSampled(getTimesSampled() + 1);
		if (exploreArms()) {
			sampleRandomArm();
		} else {
			sampleBestArm();
		}
	}
}