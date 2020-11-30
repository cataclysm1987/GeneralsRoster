import java.util.Scanner;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
//OHRiley
//GeneralRostersTest
//14 March 2014
//This is the driver to process the general master file
//modified 10 Nov 2018

public class GeneralRosterTest
{
	public static void main( String[] args )
	{
		GeneralRosters application = new GeneralRosters();

		application.openFile();

		application.readRecords();

		application.closeFile();
		General[] generals = application.getRoster();
		Scanner in = new Scanner(System.in); 

		application.sortRoster(generals, generals.length);
		
		for (int i = 0; i < generals.length; i++) {
			System.out.println(generals[i].tOString());
		}
		
		boolean tryagain = true;
		while(tryagain) {
			System.out.println("Successfully sorted generals. Please enter an index to find the general with that index. Enter -1 to exit");
			try {
				int read = in.nextInt();
				if (read != -1) {
					int index = application.searchRoster(generals, 0, generals.length-1, read);
					if (index != -1) {
						System.out.println("General found! General information: " + generals[index].tOString());
					} else {
						System.out.println("No general found with index of: " + read + ". Please try again.");
					}
				} else {
					tryagain = false;
					System.out.println("Exiting program");
				}
				
				
			} catch(Exception ex) {
				System.out.println("Error entering input. Exiting program.");
				tryagain = false;
			}
		}

    }
}

