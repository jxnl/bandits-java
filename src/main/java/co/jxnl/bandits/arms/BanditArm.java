package co.jxnl.bandits.arms;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BanditArm {

	// name of this arm
	private String banditName;

	// arbitrary properties and meta data
	private Map<String, Object> propertiesMap;

	// counts how many times an arm has been pulled
	private int pullCounter;

	// counts how many times an arm has produced a reward
	private int successCounter;

	// random number generator
	private Random random;

	public BanditArm(String name) {
		setRandom(new Random());
		banditName = name;
		propertiesMap = new HashMap<String, Object>();
		pullCounter = 0;
		successCounter = 0;
	}

	public BanditArm(String name, Map<String, Object> metaData) {
		setRandom(new Random());
		banditName = name;
		propertiesMap = metaData;
		pullCounter = 0;
		successCounter = 0;
	}

	// this defines how the arm behaves when pulled
	public abstract void sample();

	public abstract BanditArm clone();

	/**
	 * Produce the current state of a Bandit arm as a JSONObject. This can be
	 * serialized to reproduce the bandit arm for future reference.
	 * 
	 * @return json
	 */
	public JSONObject toJson() {
		JSONObject report = new JSONObject();
		try {
			report.put("name", this.banditName);
			report.put("pulled", this.getSuccessCounter());
			report.put("success", this.getPullCounter());
			report.put("mle", Metric.mle(this));
			report.put("properties", this.getPropertiesMap());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;
	}

	public String getBanditName() {
		return banditName;
	}

	public void setBanditName(String name) {
		banditName = name;
	}

	public Map<String, Object> getPropertiesMap() {
		return propertiesMap;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random r) {
		random = r;
	}

	public void setSeed(long seed) {
		this.getRandom().setSeed(seed);
	}

	public int getPullCounter() {
		return pullCounter;
	}

	public int getSuccessCounter() {
		return successCounter;
	}

	public int incrementPullCounter() {
		return ++pullCounter;
	}

	public int incrementSuccessCounter() {
		return ++successCounter;
	}

	@Override
	public String toString() {
		return this.toJson().toString();
	}
}