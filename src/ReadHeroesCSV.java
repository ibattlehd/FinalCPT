import java.io.*;

/**
* <h1>Read Heroes CSV</h1>
* Java class that reads from a CSV file and enters the data into a 2D array.
* @author  ibattlehd (Nick)
* @version 1.0
*/
public class ReadHeroesCSV{
	
	public String[][] readHeroesArray(){
		FileReader thefile = null;
		BufferedReader thefiledata = null;
		boolean blnOpen = false;
		String strLine;
		String strLine2;
		String strHeroes[][] = new String[8][9];
				
		try{
			thefile = new FileReader("../data/heroes.csv");
			thefiledata = new BufferedReader(thefile);
			blnOpen = true;
		}catch(IOException e){
			e.printStackTrace();
		}
			
		if(blnOpen){
			try{
				String strLine1 = thefiledata.readLine();
				while(strLine1 != null){
					strLine = thefiledata.readLine();
					String strLines[] = strLine.split(",");
					String[][] strLinesCsv = new String[strLines.length][];
					for (int intCount=0; intCount<strLines.length; intCount++) {
						strLinesCsv[intCount] = strLines[intCount].split(",");
					}
					for (int intCount=0; intCount<strLines.length; intCount++) {
						strHeroes[0][intCount] = strLinesCsv[intCount][0];
						System.out.println(strHeroes[0][intCount]);
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return strHeroes.clone();
	}
	
}
