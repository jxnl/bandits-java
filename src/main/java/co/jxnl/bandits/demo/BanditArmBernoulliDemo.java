package co.jxnl.bandits.demo;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import co.jxnl.bandits.arms.BanditArm;
import co.jxnl.bandits.arms.Metric;

/**
 * This implements a Bandit Arm with sampling characteristic of a Bernoulli
 * trial Return rate is randomly set within 0-20%.
 * 
 * @author jliu
 * 
 */
public class BanditArmBernoulliDemo extends BanditArm implements Cloneable {

	private double trueWinRate;

	public BanditArmBernoulliDemo(String name, Map<String, Object> metaData) {
		super(name, metaData);
		this.trueWinRate = getRandom().nextDouble() * 0.2;
	}

	public BanditArmBernoulliDemo(String name) {
		super(name);
		this.trueWinRate = getRandom().nextDouble() * 0.2;
	}

	public BanditArm clone() {
		BanditArmBernoulliDemo arm = new BanditArmBernoulliDemo(
				this.getBanditName(), this.getPropertiesMap());
		arm.trueWinRate = this.trueWinRate;
		return arm;
	}

	@Override
	public void sample() {
		incrementPullCounter();
		if (getRandom().nextDouble() < trueWinRate) {
			incrementSuccessCounter();
		}
	}

	@Override
	public JSONObject toJson() {
		JSONObject report = new JSONObject();
		try {
			// report.put("name", this.getBanditName());
			report.put("success", this.getSuccessCounter());
			report.put("pulled", this.getPullCounter());
			report.put("mle", Metric.mle(this));
			report.put("true_value", this.trueWinRate);
			report.put("properties", this.getPropertiesMap());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;
	}
}