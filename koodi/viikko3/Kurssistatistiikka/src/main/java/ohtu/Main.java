package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
		
		String studentNr = "011120775";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String studentUrl = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
		String courseInfoUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
		
        String studentBodyText = Request.Get(studentUrl).execute().returnContent().asString();
		String courseInfoBodyText = Request.Get(courseInfoUrl).execute().returnContent().asString();
        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson studentMapper = new Gson();
        Submission[] subs = studentMapper.fromJson(studentBodyText, Submission[].class);
		
		Gson courseInfoMapper = new Gson();
        CourseInfo courseinfo = courseInfoMapper.fromJson(courseInfoBodyText, CourseInfo.class);
        
		int totalHours = 0;
		int totalExercises = 0;

		System.out.println("Kurssi: " + courseinfo.getName() + ", " + courseinfo.getTerm());
		System.out.println();
        System.out.println("opiskelijanumero: " + studentNr);
		System.out.println();
		

        for (Submission submission : subs) {
            
			int currentWeek = submission.getWeek();
			int maxExercises = courseinfo.getMaximumExercises(currentWeek);
			submission.setMaxExercises(maxExercises);
			System.out.println(submission);
			
			totalHours += submission.getHours();
			totalExercises += submission.getExerciseCount();
        }	
		System.out.println();
		System.out.println("yhteensä: " + totalExercises + " tehtävää " + totalHours + " tuntia");

    }
}