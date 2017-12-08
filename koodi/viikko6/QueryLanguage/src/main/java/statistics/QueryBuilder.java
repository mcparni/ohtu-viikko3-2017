package statistics;

import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Not;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;
import statistics.matcher.True;

public class QueryBuilder implements Matcher {
	
	private Matcher matcher;
	
	public QueryBuilder() {
		True alwaysTrue = new True();
		this.matcher = new And(alwaysTrue, alwaysTrue);
	}
	
	public Matcher build() {
		return matcher;
	}
	
	public QueryBuilder not(Matcher... matchers) {
		Matcher[] allMatchers;
		allMatchers = matchers;
		this.matcher = new Not(allMatchers);
		return this;
    }
	
	public QueryBuilder oneOf(Matcher...matchers) {
		Matcher[] allMatchers;
		allMatchers = matchers;
		this.matcher = new Or(allMatchers);
		return this;
		
	}
	
	public QueryBuilder and(Matcher... matchers) {
		Matcher[] allMatchers;
		allMatchers = matchers;
		this.matcher = new And(allMatchers);
		return this;
    }
	
	public QueryBuilder or(Matcher... matchers) {
		Matcher[] allMatchers;
		allMatchers = matchers;
		this.matcher = new Or(allMatchers);
		return this;
    }
	
	public QueryBuilder playsIn(String team) {
        Matcher newMatcher = new PlaysIn(team);
		this.matcher = new And(newMatcher, this.matcher);
		return this;	
    }
	
	public QueryBuilder hasFewerThan(int value, String category) {
		Matcher newMatcher = new HasFewerThan(value, category);
        this.matcher = new And(newMatcher, this.matcher);
		return this;
	}
	
    public QueryBuilder hasAtLeast(int value, String category){
		Matcher newMatcher = new HasAtLeast(value, category);
        this.matcher = new And(newMatcher, this.matcher);
		return this;
	}

	@Override
	public boolean matches(Player p) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
