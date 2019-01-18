import java.io.*;

/**
* <h1>Marvel Game of the Generals Highscores</h1>
* @author  ibattlehd (Nick)
* @version 1.0
*/
public class MarvelGOTGHighscores{
	public static void main(String[] args){
		FileReader thefile = null;
		BufferedReader thefiledata = null;
		boolean blnOpen = false;
		
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
					System.out.println(strLinesTxt[intCount][0]);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
}
