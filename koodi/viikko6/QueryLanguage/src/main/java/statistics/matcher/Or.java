package statistics.matcher;

import statistics.Player;

public class Or implements Matcher {

    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
		for(int i = 0;  i < matchers.length; i++) {
			Matcher matcher = matchers[i];
			if(!matcher.matches(p)) {
				for(int j = 1; j < matchers.length; j++)  {
					Matcher anotherMatcher = matchers[j];
					if(anotherMatcher.matches(p))
						return true;
				}
			} else {
				for(int j = 1; j < matchers.length; j++)  {
					Matcher anotherMatcher = matchers[j];
					if(!anotherMatcher.matches(p))
						return true;
				}
			}
		}

        return false;
    }
}
