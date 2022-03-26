package launchers;

import configurations.Configuration;
import configurations.QueryConfiguration;
import solutions.MySQLSolution;

public class MySQLLauncher {

	public static void main(String[] args) {
		Configuration c = new QueryConfiguration();
		new MySQLSolution().run(c);
	}
	
}
