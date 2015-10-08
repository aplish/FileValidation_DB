import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class FileUpload {

	static HashMap<String, Integer> hm = new HashMap<String, Integer>();
   static int fg ;
   static  String path;
	// **********************Main

	public static void main(String[] arr) throws IOException, SQLException,
			ParseException {

		// writeFile.delFromDataTable();

		// -----------------------validate
		// extension--------------------------------------
		String fName;
		
		String file;
		char rej_level;
		char ch;

		do {

			BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out
						.println(" Enter details of the file ...\n ****************** ");
				while (true) {
					System.out.println("Enter the path of the File: ");
					try {
						path = inp.readLine();
						break;
					} catch (Exception e) {
						System.out.println("Enter correct path");
					}
				}
				while (true) {
					System.out
							.println("Enter the name of the File in .txt format : ");
					try {
						file = inp.readLine();
						if (fileExVal(file)) {
							break;
						} else {
							System.out.println(" Enter correct format .");
						}
					} catch (Exception e) {
						System.out.println("Enter correct file name");
					}
				}
				
				while (true) {
					System.out.println("Rejection level F or R");
					try {
						rej_level = inp.readLine().charAt(0);
						break;
					} catch (Exception e) {
						System.out.println("Enter Rejection level name");
					}
				}
				fName = path + file;
				// fName="C:\\Documents and Settings\\shilpa.rana\\Desktop\\BRD3\\Test Cases\\File2.txt";
				File testFile = new File(fName);

				if (testFile.isFile()) {
					writeFile.readTable(); 
					// String rlevel = Character.toString(rej_level);
					// wf1.insertRecordIntoSPTable(path,file,rlevel); // if want to use system parameter table
					// storing records in system parameter table
					fileRead(testFile, rej_level); // VALIDATING FILE CONTENTS
					// System.out.println(" records are written...");
					break;
				} else {
					System.out
							.println(" file doesn't exist...Re-enter the details...");
				}

			}// END OF INNER WHILE LOOP

			System.out.println("do you want to continue ? press 'y'for yes or 'n' for Exit.");
			ch = inp.readLine().charAt(0);

		} while (ch == 'y' || ch == 'Y');
		System.out.println(" Exit");
	}

	// ---method for validation for file format---
	public static boolean fileExVal(String fName) {
		String extension = fName.substring(fName.lastIndexOf(".") + 1,
				fName.length());
		String tFile = "txt";
		if (!tFile.equals(extension)) {
			return false;
		} else {
			return true;
		}
	}
	
	
	

	// -------------Method for reading file-----------------
	

	public static void fileRead(File testFile, char rej_level) {
		Scanner sc = null;
		
		try {
			sc = new Scanner(testFile);
			String[] details = null;
			String s = null;
			int count = 0;// row value
			//int cid = 0;
			
			while (sc.hasNextLine()) {
				++count;
				String line = sc.nextLine();
				details = line.split("~", 16);
				 validateRecords.valRecords(details, count, fg, rej_level); // validation
																				// method
			}
			hm.clear();
			fg=0;
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
	}

}
