import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validateRecords {

	// -------------method for validating records-----------------------
	static writeFile wf = new writeFile();
	static String msgstr = null;
	static int flag = 0;
	static int flagCount = 0;
	//static int fg = 0;

	public static void valRecords(String[] recArr, int rowcount, int CID,char rej_level) {
		String logstr = " ";

		// ---------------customer code----

		if (recArr[0].equalsIgnoreCase("")) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[0] + " : null value ";
		}

		else if (recArr[0].length() > 10) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[0] + " : length exceeded  ";
		}

		else if (FileUpload.hm.containsKey(recArr[0])) {
			flag = 1;
			flagCount++;
			logstr += "Error in " + recArr[0]
					+ " : duplicate customer code values...";
		}

		else {
			flag = 0;
		}

		FileUpload.hm.put(recArr[0], 0);

		// -----------------------Customer name-------------------

		if (recArr[1].equalsIgnoreCase("")) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[1] + " : null value ";
		}

		else if (recArr[1].length() >= 30) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[1] + " : length exceeded ";
		} else {
			Pattern p = Pattern.compile("[\\p{Punct}&&[^_]]+");
			Matcher m = p.matcher(recArr[1]);
			boolean b = m.find();
			if (b) {
				flag = 1;
				flagCount += 1;
				logstr += "Error in " + recArr[1]
						+ " : error in customer name  ";
			} else {
				flag = 0;
			}
		}
		// ----------------------C Address 1----------------

		if (recArr[2].equalsIgnoreCase("")) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[2] + " : null value .. ";
		} else if (recArr[0].length() > 100) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[2] + " : length exceeded..";
		} else {
			flag = 0;
		}

		// ------------------C Address 2 ----------------------
		if (recArr[3].length() > 100) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[3] + " : length exceeded.. ";
		} else {
			flag = 0;
		}

		// ---------------C Pincode---------------
		if (recArr[4].equalsIgnoreCase("")) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[4] + " : null value ";
		} else {
			int i = 0;
			int j = 0;
			try {
				i = Integer.parseInt(recArr[4]);
			} catch (Exception E) {
				logstr += "Error in " + recArr[4]
						+ " : invalid pincode ,Data type not correct...";
				j++;
			}

			if (recArr[4].length() != 6) {
				logstr += "Error in " + recArr[4]
						+ " : invalid pincode ,less than 6 digits...";
				j++;
			}
			if (j != 0) {
				flag = 1;
				flagCount += 1;
			} else {
				flag = 0;
			}
		}

		// ----------------------------EMAIL ADDRESS--------------------------
		if (recArr[5].equalsIgnoreCase("")) {
			flag = 1;
			flagCount += 1;
		} else if (recArr[5].length() > 100) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[5] + " length exceeded..";
		} else {
			String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
			java.util.regex.Pattern pr = java.util.regex.Pattern
					.compile(ePattern);
			String email = recArr[5];
			java.util.regex.Matcher ma = pr.matcher(email);

			if (ma.matches()) {
				flag = 0;
			}

			else {
				flag = 1;
				flagCount += 1;
				logstr += "Error in " + recArr[5] + " invalid email address...";
			}

		}

		// --------------------------------Contact Number---------
		if (recArr[6].length() > 20) {
			flag = 1;
			logstr += "Error in " + recArr[6] + " length exceeded...";
		}

		long y;
		int pi = 0;
		try {
			y = Long.parseLong(recArr[6]);
		} catch (Exception E) {
			logstr += "Error in " + recArr[6] + " invalid contact no...";
			pi++;
		}

		if (pi != 0) {
			flag = 1;
			flagCount += 1;
		} else {
			flag = 0;
		}

		// ----------------------------------Primary contact
		// person-----------------
		if (recArr[7].equalsIgnoreCase("")) {
			flag = 1;
			flagCount++;
		} else if (recArr[7].length() > 100) {
			flag = 1;
			logstr += "Error in " + recArr[7] + " length exceeded...";
		} else {
			flag = 0;
		}

		// ------------------------record status--------------------------------
		if (recArr[8].equalsIgnoreCase("")) {
			flag = 1;
			flagCount += 1;
		} else if (recArr[8].length() > 1) {
			flag = 1;
			flagCount++;
			logstr += "Error in " + recArr[8] + " length exceeded...";
		} else {
			flag = 0;
		}

		if (!recArr[8].matches("[NMDAR]")) {
			logstr += "Error in " + recArr[8] + " values out of domain...";
			flag = 1;
			flagCount++;
		}

		else {
			flag = 0;
		}

		// -------------------------active inactive
		// flag--------------------------------
		if (recArr[9].equalsIgnoreCase("")) {
			flag = 1;
			flagCount += 1;
		} else if (recArr[9].length() > 1) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[9] + " length exceeded...";
		} else {
			flag = 0;
		}
		if (!recArr[9].matches("[AI]")) {
			logstr += "Error in " + recArr[9] + " values our of domain...";
			flag = 1;
			flagCount++;
		}

		// ------------------------create date---------------------------------
		if (recArr[10].equalsIgnoreCase("")) {
			flag = 1;
			flagCount += 1;
		} else {
			flag = 0;
		}
		java.util.Date dob;
		if (!recArr[10].equals("")) {
			try {
				dob = new SimpleDateFormat("dd/MMM/yyyy").parse(recArr[10]);
				java.sql.Date create_date = new java.sql.Date((dob).getTime());
			} catch (ParseException e) {
				logstr += "Error in " + recArr[10]
						+ "create date invalid date format...";
				flag = 1;
			}

		}

		// -------------------------create
		// by------------------------------------
		if (recArr[11].equalsIgnoreCase("")) {
			flag = 1;
			flagCount += 1;
		} else if (recArr[11].length() > 30) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[11] + " length exceeded...";
		}

		else {
			flag = 0;
		}

		// ---------------------modified date------------------------

		if (!recArr[12].equals("")) {
			try {
				dob = new SimpleDateFormat("dd/MMM/yyyy").parse(recArr[12]);
				java.sql.Date modified_date = new java.sql.Date((dob).getTime());
			} catch (ParseException e) {
				logstr += "Error in " + recArr[12]
						+ "modified date invalid date format...";
				flag = 1;
				flagCount++;
			}
		}

		// -------------------------modified
		// by-----------------------------------
		if (recArr[13].length() > 30) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[13] + " length exceeded...";
		} else {
			flag = 0;
		}
		// ----------------------Authorized date-----------------------

		if (!recArr[14].equals("")) {
			try {
				dob = new SimpleDateFormat("dd/MMM/yyyy").parse(recArr[14]);
				java.sql.Date authorised_date = new java.sql.Date((dob).getTime());
			} catch (ParseException e) {
				logstr += "Error in " + recArr[14]
						+ " Authorised  date invalid date format";
				flag = 1;
				flagCount++;
			}
		}

		// -------------------------------authorized by-----------------------------------------
		if (recArr[15].length() > 30) {
			flag = 1;
			flagCount += 1;
			logstr += "Error in " + recArr[15] + " length exceeded...";
		} else {
			flag = 0;
		}
//---------------------------------------------------
		if (flagCount != 0)//error in record

		{	++FileUpload.fg; // to check if any error was there
			logstr += "\n Invalid record  of customer code :" + recArr[0]+ " error in " + rowcount;
			logstr += "\n ========================================================== ";
			System.out.println("Errors..." + FileUpload.fg);
			
			  //---writing error in text file
			  try { writeFile.logtable(logstr); } 
			   catch (Exception e ) { e.printStackTrace(); }
			 
			
			flagCount = 0;
		}

		else {	if ((rej_level == 'R' && FileUpload.fg != 0) || (rej_level == 'R' && FileUpload.fg == 0)) {
				 writeFile.insertRecordIntoTable(recArr,rowcount);
				// msgstr=" Records are inserted into table....R..with or without error. .";
				System.out.println(" NO error in this record ");
				//System.out.println(" error pointer " + FileUpload.fg);
				 
			}

		if (rej_level == 'F' && FileUpload.fg == 0) {

			 writeFile.insertRecordIntoTable(recArr,rowcount);
			msgstr = " Records are inserted into table...";
			System.out.println(" level F and NO error earlier");
			// System.out.println(" coUnter of errors "+ FileUpload.fg);
		}

		if (rej_level == 'F' && FileUpload.fg != 0) {
			 writeFile.insertRecordIntoTable(recArr,rowcount);
			msgstr = "******* Errors Found in file .File level rejection is followed ."
					+ "Records are not inserted into table ."
					+ "Error log text file is created. ********";
           System.out.println(" level F and error Earlier ");
		}
		}

		flagCount = 0;
		flag = 0;
		

	}
}
