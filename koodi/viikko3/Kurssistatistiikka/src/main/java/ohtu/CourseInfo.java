
package ohtu;


public class CourseInfo {
	private String name;
	private String term;
	private int week;
	private int[] exercises;
	
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public String getName() {
		return name;
	}
	public String getTerm() {
		return term;
	}
	public int getMaximumExercises(int week) {
		week -= 1;
		return exercises[week];
	}
	@Override
    public String toString() {
        return ""+week;
    }
}
