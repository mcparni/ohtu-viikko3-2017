package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
		
		QueryBuilder query = new QueryBuilder();
		
		Matcher m8 = query.oneOf(
                        new QueryBuilder().playsIn("PHI")
                             .hasAtLeast(10, "goals")
                             .hasFewerThan(20, "assists").build(),
 
                        new QueryBuilder().playsIn("EDM")
                             .hasAtLeast(60, "points").build()
                       ).build();
		
		for (Player player : stats.matches(m8)) {
            System.out.println( player );
        }
		
    }
}
