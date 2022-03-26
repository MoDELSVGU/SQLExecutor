package configurations;

import java.util.Map;

public class QueryConfiguration extends DatabaseConfiguration {
	private String sScenario, sQuery, sCaller;

	private static final String ENV_QUERY = "QUERY";
	private static final String ENV_SCENARIO = "SCENARIO";
	private static final String ENV_CALLER = "CALLER";

	public String getsScenario() {
		return sScenario;
	}

	public void setsScenario(String sScenario) {
		this.sScenario = sScenario;
	}

	public String getsQuery() {
		return sQuery;
	}

	public void setsQuery(String sQuery) {
		this.sQuery = sQuery;
	}

	public QueryConfiguration() {
		super();
		final Map<String, String> env = System.getenv();

		final String sQuery = env.get(ENV_QUERY);
		if (sQuery != null) {
			setsQuery(sQuery);
		}

		final String sScenario = env.get(ENV_SCENARIO);
		if (sScenario != null) {
			setsScenario(sScenario);
		}
		
		final String sCaller = env.get(ENV_CALLER);
		if (sCaller != null) {
			setsCaller(sCaller);
		}

	}

	public String getsCaller() {
		return sCaller;
	}

	public void setsCaller(String sCaller) {
		this.sCaller = sCaller;
	}

}
