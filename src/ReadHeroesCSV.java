import java.io.*;

public class ReadHeroesCSV{
    public static void main(String[] args){
		
		FileReader thefile = null;
		BufferedReader thefiledata = null;
		boolean blnOpen = false;
		String strLine;
		
		try{
			thefile = new FileReader("../data/heroes.csv");
			thefiledata = new BufferedReader(thefile);
			blnOpen = true;
		}catch(IOException e){
			e.printStackTrace();
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
				strLinesCsv[0][1] = "test";
				System.out.println(strLinesCsv[0][1]);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
