import java.io.*;

/**
* <h1>Board Data</h1>
* Java class that reads from a CSV file and enters the data into a 2D array.
* @author  ibattlehd (Nick)
* @version 1.0
*/
public class BoardData{
	
	//public String[][] readMapArray(){
	public static void main(String[] args){
		FileReader thefile = null;
		BufferedReader thefiledata = null;
		boolean blnOpen = false;
		String strHeroes[][] = new String[8][9];
				
		try{
			thefile = new FileReader("../data/board.csv");
			thefiledata = new BufferedReader(thefile);
			blnOpen = true;
		}catch(IOException e){
			e.printStackTrace();
		}
			
		if(blnOpen){
			try{
				while(true){
					String strLine = thefiledata.readLine();
					//System.out.println(strLine);
					if(strLine == null){
						break;
					}
					String strLines[] = strLine.split(","); // 1-dimensional array strLines[0-8]
					String[][] strLinesCsv = new String[strLines.length][];
					for (int intCount=0; intCount<strLines.length; intCount++) {
						strLinesCsv[intCount] = strLines[intCount].split(",");
					}
					System.out.println(strLinesCsv[0][0]);
					for (int intCount=0; intCount<strLines.length; intCount++) {
						for(int intRow=0; intRow<8; intRow++) {
							for(int intCol=0; intCol<9; intCol++){
								strHeroes[intRow][intCol] = strLinesCsv[intCount][0];
							}
						}
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		//return strHeroes.clone();
	}
	
}
