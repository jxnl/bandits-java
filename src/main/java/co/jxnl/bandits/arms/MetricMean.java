package co.jxnl.bandits.arms;

/**
 * This implements a comparator using the mean of a <code>BanditArm</code>
 * 
 * @author JasonLiu
 *
 */
public class MetricMean extends Metric {

	@Override
	public int compare(BanditArm o1, BanditArm o2) {
		return Double.compare(mle(o1), mle(o2));
	}
}