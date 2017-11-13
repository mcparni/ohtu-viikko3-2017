package ohtu;

public class Submission {
    private int week;
	private int hours;
	private int[] exercises;
	private int maxExercises = -1;

    public void setWeek(int week) {
        this.week = week;
    }
	public void setMaxExercises(int max) {
		this.maxExercises = max;
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
		if(maxExercises == -1)
			return "viikko "+week+ ": tehtyjä tehtäviä yhteensä: "+exercises.length+", aikaa kului "+hours+" tuntia, tehdyt tehtävät: " + getExercises();
		else
			return "viikko "+week+ ": tehtyjä tehtäviä yhteensä: "+exercises.length+" (maksimi "+maxExercises+"), aikaa kului "+hours+" tuntia, tehdyt tehtävät: " + getExercises();
    }	
    
}