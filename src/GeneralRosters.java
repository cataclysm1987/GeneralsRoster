//OHRiley
//ReadSequentialFile
//23 February 2014
//Part of Chap 17 Examples
//this reads the  master file from
//   ArtifactRecord layout
//13 Nov 2017  modify sort/search requirement

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GeneralRosters
{
	private ObjectInputStream input;
	private General [] roster = new General[20];
	
	public General[] getRoster() {
		return roster;
	}



    // **** SORT ROUTINE *******
    public void sortRoster(General[] data, int n){
    	if (n < 2) {
            return;
        }
        int mid = n / 2;
        General[] l = new General[mid];
        General[] r = new General[n - mid];
     
        for (int i = 0; i < mid; i++) {
            l[i] = data[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = data[i];
        }
        
        sortRoster(l, mid);
        sortRoster(r, n - mid);
     
        mergeRoster(data, l, r, mid, n - mid);
		}
	 //end sortRoster

    public static void mergeRoster(General[] data, General[] l, General[] r, int left, int right) {
    	int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getID() <= r[j].getID()) {
                data[k++] = l[i++];
            }
            else {
                data[k++] = r[j++];
            }
        }
        while (i < left) {
            data[k++] = l[i++];
        }
        while (j < right) {
            data[k++] = r[j++];
        }
    	
    	
    }

    //******* SEARCH ROUTINE *******

    public static int searchRoster(General data[], int low, int high, int key){
		System.out.println("** searchRoster **");
		int index = -1;
	    
	    while (low <= high) {
	        int mid = (low + high) / 2;
	        if (data[mid].getID() < key) {
	            low = mid + 1;
	        } else if (data[mid].getID() > key) {
	            high = mid - 1;
	        } else if (data[mid].getID() == key) {
	            index = mid;
	            break;
	        }
	    }
	    return index;
			
    }
		
		
      //end searchRoster

    //  ******* OPEN FILE ROUTINE *******
    //  make sure that generalMaster.ser  is in the folder from
    //  whih your programs are running...for Eclipse the .ser file
    //  needs to be at the project Level...for TextPad the .ser will
    // will be in the folder containing the .jav file
    //  NO CHANGES ARE NEEDED IN THIS METHOD
	public void openFile(){
		System.out.println("** In openFile **");
		try	{
			input = new ObjectInputStream(new FileInputStream("generalMaster.ser"));
	    }
	    catch(IOException ioException) {
			System.err.println("can't open or accesss file  " + ioException + "\n");
	    }
	}  //end openFile

 //******* READ FILE and LOAD ARRAY ROUTINE *******

	public void readRecords(){
		System.out.println("** In readRecords **");
		General  record;
	    int count = 0;   //Used to populate array in while loop

		System.out.printf("%-12s%-20s%-12s\n","ID","Name","Age");
		try	{
			while(true)	{
				record = (General) input.readObject();
                //ADD TWO LINES HERE... TO POPULATE YOUR Roster ARRAY and increment your array index...
				
				roster[count] = record;
				count++;
				System.out.printf("%-12d%-20s%-12d\n",record.getID(), record.getName(), record.getAge());

	        }  //end while
        }
	    catch(EOFException e){
			System.out.println("....catch ...." + e + "\n");
			return;
		}
		catch(ClassNotFoundException eclassNotFound){
			System.out.println("....catch ...." + eclassNotFound + "\n");
		}
		catch(IOException eIO){
			System.out.println("....catch ...." + eIO + "\n");
		}
	}  //end readRecords

 //******* CLOSE FILE ROUTINE *******
 //  NO CHANGES ARE NEEDED IN THIS METHOD
	public void closeFile(){
		System.out.println("** In closeFile **");
		try	{
		    if (input != null)
			    input.close();
		}
		catch(IOException ioExcetion) {
			System.err.println("Error closing file");
			System.exit(1);
		}
    }  //end closeFile
}  //end class