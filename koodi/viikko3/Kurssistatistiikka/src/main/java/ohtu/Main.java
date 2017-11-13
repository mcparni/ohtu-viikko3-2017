package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
		String courseStatsUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";
		
        String studentBodyText = Request.Get(studentUrl).execute().returnContent().asString();
		String courseInfoBodyText = Request.Get(courseInfoUrl).execute().returnContent().asString();
		String courseStatsBodyText = Request.Get(courseStatsUrl).execute().returnContent().asString();
        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson studentMapper = new Gson();
        Submission[] subs = studentMapper.fromJson(studentBodyText, Submission[].class);
		
		Gson courseInfoMapper = new Gson();
        CourseInfo courseinfo = courseInfoMapper.fromJson(courseInfoBodyText, CourseInfo.class);
        
		String statsResponse = courseStatsBodyText;
		JsonParser parser = new JsonParser();
		JsonObject courseStats = parser.parse(statsResponse).getAsJsonObject();
		
		int statsLength = courseStats.size();
		int allSubmissions = 0;
		int allHours = 0;
		int allExercises = 0;
		
		for(int i = 1; i < statsLength + 1; i++) {
			JsonObject j = courseStats.getAsJsonObject("" +i);
			JsonElement s = j.get("students");
			JsonElement h = j.get("hour_total");
			JsonElement e = j.get("exercise_total");
			allSubmissions += s.getAsInt();
			allHours += h.getAsInt();
			allExercises += e.getAsInt();
		}
		
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
		System.out.println("kurssilla yhteensä " + allSubmissions + " palautusta, palautettuja tehtäviä " + allExercises + " kpl,  joihin on mennyt yhteensä " + allHours + " tuntia");

    }
}