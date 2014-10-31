package co.jxnl.bandits.arms;

/**
 * This implements a comparator using the Upper Confidence Bound of a
 * <code>BanditArm</code>
 * 
 * @author JasonLiu
 *
 */
public class MetricUbc extends Metric {

	public int compare(BanditArm o1, BanditArm o2) {
		return Double.compare(ubc(o1), ubc(o2));
	}

}
