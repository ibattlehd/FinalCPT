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
			thefile = new FileReader("highscores.txt");
			blnOpen = true;
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		if(blnOpen){
			thefiledata = new BufferedReader(thefile);
			String strLine = "";
			try{
				strLine = thefiledata.readLine();
				System.out.println(strLine);
			}catch(IOException e){
				e.printStackTrace();
			}
			while(strLine != null){
				try{
					strLine = thefiledata.readLine();
					System.out.println(strLine);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
	}
}
