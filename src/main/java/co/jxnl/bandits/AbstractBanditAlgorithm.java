package co.jxnl.bandits;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import co.jxnl.bandits.arms.BanditArm;
import co.jxnl.bandits.arms.Metric;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Multi-armed Bandits
 * <p>
 * Multi-armed bandits have been used to model the problem of managing research
 * projects in a large organization. Given a fixed budget, the problem is to
 * allocate resources among the competing projects, whose properties are only
 * partially known at the time of allocation, but which may become better
 * understood as time passes.
 * </p>
 * 
 * @author JasonLiu
 */
public abstract class AbstractBanditAlgorithm {

	private Random random;
	private Metric metric;

	private String name;
	private List<BanditArm> banditArms;

	private double epsilon;
	private double alpha;

	private int timesSampled;

	public AbstractBanditAlgorithm(String name, List<BanditArm> banditArms,
			double epsilon, Metric metric) {
		setRandom(new Random());
		this.name = name;
		this.banditArms = banditArms;
		this.setEpsilon(epsilon);
		this.metric = metric;
	}

	abstract public void sample();

	public void init() {
		for (BanditArm b : banditArms) {
			b.sample();
		}
	}

	public void sample(int k) {
		for (int i = 0; i <= k; i++) {
			sample();
		}
	}

	protected void sampleBestArm() {
		metric.setTotalPullCounter(getTimesSampled());
		Collections.max(banditArms, metric).sample();
	}

	protected void sampleRandomArm() {
		banditArms.get(getRandom().nextInt(banditArms.size())).sample();
	}

	public int getTimesSucceed() {
		int timesSucceeded = 0;
		for (BanditArm ba : banditArms) {
			timesSucceeded += ba.getSuccessCounter();
		}
		return timesSucceeded;
	}

	public JSONObject toJson() {
		JSONObject report = new JSONObject();
		Map<String, JSONObject> armPerformance = new HashMap<String, JSONObject>();
		for (BanditArm ba : banditArms) {
			armPerformance.put(ba.getBanditName(), ba.toJson());
		}
		try {
			report.put("name", name);
			report.put("sampled", getTimesSampled());
			report.put("succeeded", getTimesSucceed());
			report.put("epsilon", getEpsilon());
			report.put("bandit_arms", armPerformance);
			if (getAlpha() > 0) {
				report.put("alpha", getAlpha());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;
	}

	@Override
	public String toString() {
		return this.toJson().toString();
	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	protected Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public void setSeed(long seed) {
		this.getRandom().setSeed(seed);
	}

	public int getTimesSampled() {
		return timesSampled;
	}

	public void setTimesSampled(int timesSampled) {
		this.timesSampled = timesSampled;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
}