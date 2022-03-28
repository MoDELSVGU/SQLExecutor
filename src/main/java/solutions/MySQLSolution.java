package solutions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import configurations.Configuration;
import configurations.QueryConfiguration;
import connections.MySQLConnection;

public class MySQLSolution extends Solution {

	private final static String METRIC_EXECUTION_TIME = "ExecutionTimeSecs";

	@Override
	public void run(Configuration c) {
		if (c instanceof QueryConfiguration) {
			QueryConfiguration qc = (QueryConfiguration) c;
			Connection conn = MySQLConnection.getConnection(qc.getsScenario(), qc.getDbusername(), qc.getDbpassword());
			PreparedStatement st;
			try {
				st = conn.prepareStatement(qc.getsQuery());
				if (qc.getsQuery().contains("?")) {
					st.setString(1, qc.getsCaller());
				}
				final long nanosExecutionStart = System.nanoTime();
				st.executeQuery();
				final long nanosExecutionEnd = System.nanoTime();
				final double timeInSecs = ((double) nanosExecutionEnd - nanosExecutionStart) / 1_000_000_000;
				printMetric(qc, METRIC_EXECUTION_TIME, timeInSecs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void printMetric(Configuration c, String metricExecutionTime, Object metricValue) {
		if (c instanceof QueryConfiguration) {
			QueryConfiguration qc = (QueryConfiguration) c;

			System.out.println(String.format("%s;%s;%s;%s;%s;%s", qc.getsTool(), qc.getsScenario(), qc.getsQuery(), qc.getRunIndex(),
					metricExecutionTime, metricValue.toString()));

		}

	}

}
