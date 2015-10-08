import java.io.*;
import java.sql.*;
import java.text.*;

public class writeFile {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@finn:1521:orcl";
	private static final String DB_USER = "hr";
	private static final String DB_PASSWORD = "hr";

	public static void main(String[] argv) {

	}

	// ----------------------------insert records in
	// table-------------------------------
	public static void insertRecordIntoTable(String[] recArr, int rowcount) {

		Connection dbConnection = null;
		PreparedStatement update = null;
		java.util.Date dob1, dob2, dob3;
		java.sql.Date create_date = null, modified_date = null, authorised_date = null;

		if (!recArr[10].equals("")) {
			try {
				dob1 = new SimpleDateFormat("dd/MMM/yyyy").parse(recArr[10]);
				create_date = new java.sql.Date((dob1).getTime());
			} catch (ParseException e) {

			}
		}
		if (!recArr[12].equals("")) {

			try {
				dob2 = new SimpleDateFormat("dd/MMM/yyyy").parse(recArr[12]);
				modified_date = new java.sql.Date((dob2).getTime());
			} catch (ParseException e) {
				System.out.println(" Date format exception..");
			}
		}

		if (!recArr[14].equals("")) {

			try {
				dob3 = new SimpleDateFormat("dd/MMM/yyyy").parse(recArr[14]);
				authorised_date = new java.sql.Date((dob3).getTime());
			} catch (ParseException e) {

			}

		}

		// -----insert records here----

		String customer_code = recArr[0];
		String customer_name = recArr[1];
		String customer_add1 = recArr[2];
		String customer_add2 = recArr[3];
		int customer_pin_code = Integer.parseInt(recArr[4]);
		String email_add = recArr[5];
		long contact_num = Long.parseLong(recArr[6]);
		String primary_CP = recArr[7];
		String record_status = recArr[8];
		String AI_flag = recArr[9];
		String created_by = recArr[11];
		String modified_by = recArr[13];
		String authorised_by = recArr[15];

		String insertTableSQL = "INSERT INTO Shilpa_Customer_Master4"
				+ "(customer_id,Customer_Code ,Customer_name,Customer_Address1 ,Customer_Address2,"
				+ "Customer_pin_code,E_Mail_Address,Contact_number ,Primary_Contact_Person,Record_Status,"
				+ "Active_Inactive_Flag,Create_Date ,Created_By,Modify_Date ,Modified_By,Authorized_date ,Authorized_By)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			dbConnection = getDBConnection();
			update = dbConnection.prepareStatement(insertTableSQL);

			update.setInt(1, rowcount);
			update.setString(2, customer_code);
			update.setString(3, customer_name);
			update.setString(4, customer_add1);
			update.setString(5, customer_add2);
			update.setInt(6, customer_pin_code);
			update.setString(7, email_add);
			update.setLong(8, contact_num);
			update.setString(9, primary_CP);
			update.setString(10, record_status);
			update.setString(11, AI_flag);
			update.setDate(12, (java.sql.Date) create_date);
			update.setString(13, created_by);
			update.setDate(14, (java.sql.Date) modified_date);
			update.setString(15, modified_by);
			update.setDate(16, (java.sql.Date) authorised_date);
			update.setString(17, authorised_by);

			// execute insert SQL statement...
			update.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
	}

	
	//----------read from table-------------------------------------
		public static void readTable(){
			Connection dbConnection = null;
			Statement update = null; // create a statement
			// -----insert records here----
			String readTableSQL = "Select Customer_Code from  Shilpa_Customer_Master4";

			try {
				dbConnection = getDBConnection();
				update = dbConnection.createStatement();
			    ResultSet m_ResultSet = update.executeQuery(readTableSQL);

			    while (m_ResultSet.next()) {
			        FileUpload.hm.put(m_ResultSet.getString(1), 0);
			      }
				} 
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}

			
		
		
		}
	// ------------------------------making error file-----------------------------------------------

	public static void logtable(String logerror) throws IOException {
		//String patherr = "C:\\Documents and Settings\\shilpa.rana\\Desktop\\BRD3\\Test Cases\\";
		String patherr=FileUpload.path;
		File f = new File(patherr + "logerror.txt");

		FileWriter fout = new FileWriter(f, true);
		BufferedWriter bout = new BufferedWriter(fout);
		bout.newLine();
		bout.write(logerror);
		// bout.write("");
		// bout.newLine();
		bout.flush();

		// bout.write("");
		// bout.newLine();
		bout.close();
		// fout.close();

	}

	// ------------------------------------inserting record in error table------------------------------
	public static void insertRecordIntoSPTable(String s1, String s2, String s3) {

		Connection dbConnection = null;
		PreparedStatement update = null; // create a prepared statement
		// -----insert records here----
		String insertTableSQL = "INSERT INTO Shilpa_System_Parameter_Table"
				+ "(file_location,file_name,rejection_level) VALUES"
				+ "(?,?,?)";

		try {
			dbConnection = getDBConnection();
			update = dbConnection.prepareStatement(insertTableSQL);
			update.setString(1, s1);
			update.setString(2, s2);
			update.setNString(3, s3);
			// execute insert SQL statement...
			update.executeUpdate();
			System.out.println("Record is inserted into table...");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// ------------------------------if rejection level = F, delete all data---------------------------------

	public static void delFromDataTable() {

		Connection dbConnection = null;
		Statement update = null; // create a statement
		// -----insert records here----
		String deleteTableSQL = "truncate table Shilpa_Customer_Master4";

		try {
			dbConnection = getDBConnection();
			update = dbConnection.createStatement();
			update.executeUpdate(deleteTableSQL);
			System.out.println("All records are deleted from table...");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// -------------------------------create Connection-------------------------------------

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;

	}

}