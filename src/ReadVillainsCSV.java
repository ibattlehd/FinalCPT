import java.io.*;

public class ReadVillainsCSV{
    public static void main(String[] args){
		
		FileReader thefile = null;
		BufferedReader thefiledata = null;
		boolean blnOpen = false;
		String strLine;
		String strVillains[][] = new String[8][9];
		
		try{
			thefile = new FileReader("../data/villains.csv");
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
				}
				for (int intCount=0; intCount<strLines.length; intCount++) {
					strVillains[0][intCount] = strLinesCsv[intCount][0];
					System.out.println(strVillains[0][intCount]);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
