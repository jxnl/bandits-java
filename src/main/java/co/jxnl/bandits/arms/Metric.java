package co.jxnl.bandits.arms;

import java.util.Comparator;

/**
 * This class represents the different ways BanditArms can be compared. While
 * MLE and UBC exist there may be other ways to order the success metric of an
 * arm.
 * 
 * @author JasonLiu
 *
 */
public abstract class Metric implements Comparator<BanditArm> {

	protected int totalPullCounter;

	public void setTotalPullCounter(int n) {
		totalPullCounter = n;
	}

	public static double mle(BanditArm ba) {
		return (double) ba.getSuccessCounter() / ba.getPullCounter();
	}

	public static double upperConfidenceInterval(BanditArm ba, int n) {
		return Math.sqrt(2 * Math.log((double) n)) / ba.getPullCounter();
	}

	public double upperConfidenceInterval(BanditArm ba) {
		return Math.sqrt(2 * Math.log((double) this.totalPullCounter)
				/ ba.getPullCounter());
	}

	public double ubc(BanditArm ba) {
		return mle(ba) + upperConfidenceInterval(ba);
	}

	public abstract int compare(BanditArm o1, BanditArm o2);
}