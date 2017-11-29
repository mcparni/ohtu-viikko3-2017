package ohtu;

public class TennisGame {
    
    private int playerScore1 = 0;
    private int playerScore2 = 0;
    private String playerName1;
    private String playerName2;
	private final String[] evenScores = {"Love-All","Fifteen-All","Thirty-All","Forty-All","Deuce"};
	private final String[] scores = {"Love","Fifteen","Thirty","Forty"};

    public TennisGame(String playerName1, String playerName2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1"))
            playerScore1 += 1;
        else
            playerScore2 += 1;
    }

	private String gameEven() {
		String score = "";
		return evenScores[playerScore1];
	}
	
	private String tieBreak(int advantage) {
		String score = "";
		if (advantage == 1)
			score ="Advantage " + playerName1;
		else if(advantage == -1)
			score ="Advantage " + playerName2;
		
		return score;
	}
	
	private String gameWin(int advantage) {
		String score = "";
		if (advantage >= 2) 
			score = "Win for " + playerName1;
        else 
			score = "Win for " + playerName2;
		
		return score;
	}
	
	private String endGame() {
		String score = "";
		int advantage = playerScore1 - playerScore2;
		if(advantage == 1 || advantage == -1)
			score = tieBreak(advantage);
		else
			score = gameWin(advantage);
           
		return score;
	}
	
	private String gameScore() {
		String currentScore = "";
		currentScore += scores[playerScore1];
		currentScore += "-";
		currentScore += scores[playerScore2];
		return currentScore;
	}
	
    public String getScore() {
        String score = "";
		int fourPoints = 4;
        if (playerScore1 == playerScore2) {    
			score = gameEven();        
        } else if (playerScore1 >= fourPoints || playerScore2 >= fourPoints) {
            score = endGame();
        } else {
			score = gameScore();        
        }
        return score;
    }
}