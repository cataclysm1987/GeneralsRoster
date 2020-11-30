// OHRiley
// General class for lab 8
// 12 March 2014
// class General
// modified April 5, 2019

//you must add methods, complete stubs, and
//   fix errors (there are 3 errors in this code...
//     Hint: fix those errors before you add code

import java.io.Serializable;

public class General implements Serializable {
	private int ID;
	private String name;
	private int age;

	private static final long serialVersionUID =  6894788911163027404L;   //not an instnace variable,needed for serial file



	public General() {

	}

	public General(int ID, String name, int age) {

	}
	
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}

	public String tOString() {
		return ("ID: " + ID + "\n NAME: \t" + name + "\n AGE: \t");
	}
}