package ohtu;

public class Submission {
    private int week;
	private int hours;
	private int[] exercises;

    public void setWeek(int week) {
        this.week = week;
    }	
	
	public int getHours() {
		return hours;
	}
	
	public int getWeek() {
		return week;
	}
	
    public String getExercises() {
        String s = "";
		for(int i = 0; i < exercises.length; i++) {
			s += exercises[i] + " ";
		}		
		return s;
    }
	
	public int getExerciseCount() {
		return exercises.length;
	}

    @Override
    public String toString() {
        return "viikko "+week+ ": tehtyjä tehtäviä yhteensä: "+exercises.length+", aikaa kului "+hours+" tuntia, tehdyt tehtävät: " + getExercises();
    }
    
}