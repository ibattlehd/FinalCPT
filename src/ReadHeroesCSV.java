import java.io.*;

public class ReadHeroesCSV{
    public static void main(String[] args){
		
		boolean blnOpen = false;
		BufferedReader con = new BufferedReader(new InputStreamReader(System.in));
		String strHeroes[][] = new String[8][9];
		String strLine;
		
		FileReader thefile = null;
		BufferedReader thefiledata = null;
		
		try{
			thefile = new FileReader("../data/heroes.csv");
			thefiledata = new BufferedReader(thefile);
			blnOpen = true;
		}catch(IOException e){
			System.out.println("unable to open file");
		}
		
		if(blnOpen){
			try{
				strLine = thefiledata.readLine();
				System.out.println(strLine);
				String strLines[] = strLine.split(",");
				String[][] strLinesCsv = new String[strLines.length][];
				for (int intCount=0; intCount<strLines.length; intCount++) {
					strLinesCsv[intCount] = strLines[intCount].split(",");
					System.out.println(strLinesCsv[intCount][0]);
				}
			}catch(IOException e){
			}
		}
	}
}
