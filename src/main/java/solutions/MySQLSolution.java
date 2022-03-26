package solutions;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
			Statement st;
			try {
				st = conn.createStatement();
				final long nanosExecutionStart = System.nanoTime();
				st.executeQuery(qc.getsQuery());
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

			System.out.println(String.format("%s;%s;%s;%s;%s", qc.getsTool(), qc.getsQuery(), qc.getRunIndex(),
					metricExecutionTime, metricValue.toString()));

		}

	}

}
