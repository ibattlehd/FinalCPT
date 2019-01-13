import java.io.*;

public class MarvelGOTGHighscores{
	public static void main(String[] args){
		boolean blnFileFail = false;
		FileReader thefile = null;
		BufferedReader thefiledata = null;
		try{
			thefile = new FileReader("highscores.txt");
		}catch(FileNotFoundException e){
			System.out.println("Unable to read from the file");
			blnFileFail = true;
		}
		if(blnFileFail == false){
			thefiledata = new BufferedReader(thefile);
			
			String strLine = "";
			try{
				strLine = thefiledata.readLine();
			}catch(IOException e){
				strLine = "testing";
			}
			while(strLine != null){
				System.out.println(strLine);
				try{
					strLine = thefiledata.readLine();
				}catch(IOException e){
					strLine = "testing";
				}
			}
			
		}
		
	}
}
