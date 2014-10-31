package co.jxnl.bandits.demo;

import java.util.ArrayList;
import java.util.List;

import co.jxnl.bandits.arms.BanditArm;
import co.jxnl.bandits.impl.GreedyEpsilonBanditImpl;
import co.jxnl.bandits.impl.UpperConfidenceBoundBanditImpl;

public class BanditDemo {

	public static List<BanditArm> baseBanditArms;

	public static void init() {

		BanditArm arm1 = new BanditArmBernoulliDemo("Blue Button");
		BanditArm arm2 = new BanditArmBernoulliDemo("Red Button");
		BanditArm arm3 = new BanditArmBernoulliDemo("Yellow Button");

		arm1.setSeed(1);
		arm2.setSeed(2);
		arm3.setSeed(3);

		baseBanditArms = new ArrayList<BanditArm>();
		baseBanditArms.add(arm1);
		baseBanditArms.add(arm2);
		baseBanditArms.add(arm3);
	}

	public static List<BanditArm> getCopyOfArms() {
		List<BanditArm> banditArms = new ArrayList<BanditArm>();
		for (BanditArm banditArm : baseBanditArms) {
			banditArms.add(banditArm.clone());
		}
		return banditArms;
	}

	/**
	 * Ubc Bandit Demo
	 */
	public static void ubc(List<BanditArm> arms) {
		// Create the strategy
		UpperConfidenceBoundBanditImpl strat = new UpperConfidenceBoundBanditImpl(
				"Testing Buttons", arms);

		// Initialize and sample;
		strat.init();
		strat.setSeed(1);
		strat.sample(100000);

		System.out.println(strat);
	}

	/**
	 * GreedyBandit Demo
	 * <p>
	 * This runner creates three separate arms labeled blue, red, yellow. These
	 * would be various website designs or ads in a campaign.
	 * </p>
	 */
	public static void mean(List<BanditArm> arms) {
		// Create the strategy
		GreedyEpsilonBanditImpl strat = new GreedyEpsilonBanditImpl(
				"Testing Buttons", arms, 0.5);

		// Initialize and sample;
		strat.init();
		strat.setSeed(1);
		strat.sample(100000);

		System.out.println(strat);
	}

	public static void main(String[] args) {
		init();
		ubc(getCopyOfArms());
		mean(getCopyOfArms());
	}
}
