package statistics.matcher;

import statistics.Player;

public class True implements Matcher {

	@Override
	public boolean matches(Player p) {
		return true;
	}
	
}
