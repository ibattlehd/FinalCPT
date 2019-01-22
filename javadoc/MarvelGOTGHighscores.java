import java.io.*;

/**
* <h1>Marvel Game of the Generals Highscores</h1>
*/
public class MarvelGOTGHighscores{
	
	public String[][] readMapArray(){
		FileReader thefile = null;
		BufferedReader thefiledata = null;
		boolean blnOpen = false;
		String strScores[][] = new String[1][50]; // holds top 50 high scores
		
		try{
			thefile = new FileReader("../data/highscores.txt");
			blnOpen = true;
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		if(blnOpen){
			thefiledata = new BufferedReader(thefile);
			try{
				String strLine = thefiledata.readLine();
				String strLines[] = strLine.split(",");
				String[][] strLinesTxt = new String[strLines.length][];
				for (int intCount=0; intCount<strLines.length; intCount++) {
					strLinesTxt[intCount] = strLines[intCount].split(",");
				}
				for (int intCount=0; intCount<strLines.length; intCount++) {
					strScores[0][intCount] = strLinesTxt[intCount][0];
					System.out.println(strScores[0][intCount]);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		return strScores.clone();
	}
}
