/*

http://172.0.0.15:5560/isqlplus/
select * from certificate_details;

set path=C:\Program Files\Java\jdk1.6.0_21\bin
set classpath=.;C:\classes12.jar

javac Project9.java
java Project9

*/
//certificate_details table Using stmt and separate classes


import java.text.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class Project9
{

 InputStreamReader ireader = new InputStreamReader(System.in);
 BufferedReader breader = new BufferedReader(ireader);
 Scanner s = new Scanner(System.in);

 int row_no = 0;
 int roll_number=0, certificate_number=0;
 String student_name, grade, stream, date_of_receive, date_of_issue ;

/************************************************************************************************************************************/

	public static void main(String[] args)
	{
	 Connection con=null;
	 Statement stmt=null;
	 ResultSet rs=null;	 

	 InputStreamReader ireader = new InputStreamReader(System.in);
 	 BufferedReader breader = new BufferedReader(ireader);
	 int ch=0;

		try
		{
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 con = DriverManager.getConnection("jdbc:oracle:thin:@172.0.0.15:1521:orcl", "scott", "tiger");

	 	 Jdbc j = new Jdbc(); 
	 	 Menu m = new Menu();
	 	 Insert i = new Insert();
	 	 Delete d = new Delete();
	 	 Retriever rr = new Retriever();
	 	 Retrievem rm = new Retrievem();
	 	 Retrievea ra = new Retrievea();

	 	 j.jdbc_connect();

		 	while(true)
			{
			 m.menu();

			 System.out.println("Enter your choice : ");
			 ch = Integer.parseInt(breader.readLine());

				switch(ch)
				{
				 case 1 :
					{
			 		 i.insert();
					 break;
					}

				 case 2 : 
					{
					 d.delete();
					 break;
					}

				 case 3 : 
					{
					 rr.retrieve_roll();
					 break;
					}

				 case 4 :
					{
					 rm.retrieve_month();
					 break;
					}

				 case 5 :
					{
					 ra.retrieve_all();
					 break;
					}

				 case 6 :
					{
					 System.out.println("EXIT");
					 System.exit(0);
					}
				}
			}
		}

		catch(NullPointerException npe) {npe.printStackTrace();}
		//catch(ParseException pe) {pe.printStackTrace();}
		catch(IOException ioe) { ioe.printStackTrace (); }
		catch(ClassNotFoundException cfe) { cfe.printStackTrace (); }
		catch(SQLException sqe) { sqe.printStackTrace (); }
		catch(NumberFormatException ne) { ne.printStackTrace (); }		

	}
}

/************************************************************************************************************************************/	

class Jdbc
{
	public void jdbc_connect()
	{
	 Connection con=null;
	 Statement stmt=null;
	 ResultSet rs=null;

		try
		{
	 	 Class.forName("oracle.jdbc.driver.OracleDriver");
	 	 con = DriverManager.getConnection("jdbc:oracle:thin:@172.0.0.15:1521:orcl", "scott", "tiger");
		}

		//catch(NullPointerException npe) {npe.printStackTrace();}
		//catch(ParseException pe) {pe.printStackTrace();}
		//catch(IOException ioe) { ioe.printStackTrace (); }
		catch(ClassNotFoundException cfe) { cfe.printStackTrace (); }
		catch(SQLException sqe) { sqe.printStackTrace (); }
		//catch(NumberFormatException ne) { ne.printStackTrace (); }
	}
}

/************************************************************************************************************************************/	

class Menu
{
	public void menu()
	{
	 System.out.println("MENU");
	 System.out.println("Choice 1 : INSERT details into certificate_details table");
	 System.out.println("Choice 2 : DELETE a particular record from certificate_details table");
	 System.out.println("Choice 3 : RETRIEVE details of a particular student from certificate_details table");
	 System.out.println("Choice 4 : RETRIEVE monthly view of received and issued certificates from certificate_details table");
	 System.out.println("Choice 5 : RETRIEVE all details from certificate_details table");
	 System.out.println("Choice 6 : EXIT");	 
	}
}

/************************************************************************************************************************************/	

class Insert
{
	public void insert()
	{
	 InputStreamReader ireader = new InputStreamReader(System.in);
 	 BufferedReader breader = new BufferedReader(ireader);
 	 Scanner s = new Scanner(System.in);

	 Connection con=null;
	 Statement stmt=null;
	 ResultSet rs=null;
	 int row_no=0;

		try
		{
 	 	 int roll_number=0, certificate_number=0;
 	 	 String student_name, grade, stream, date_of_issue=null, date_of_receive=null;

 		 boolean flag1=false; 
		 String d;
		 java.util.Date now = new java.util.Date();

		 Class.forName("oracle.jdbc.driver.OracleDriver");
	 	 con = DriverManager.getConnection("jdbc:oracle:thin:@172.0.0.15:1521:orcl", "scott", "tiger");

	 	 System.out.println("INSERT details into certificate_details table");

		 stmt = con.createStatement();

	 	 System.out.println("Enter roll_number (primary key. It must be an integer number) : ");
	 	 roll_number = Integer.parseInt(breader.readLine());

	 	 System.out.println("Enter certificate_number (It must be an integer number) : ");
	 	 certificate_number = Integer.parseInt(breader.readLine());

	 	 System.out.println("Enter student_name (It must be a String): ");
	 	 student_name = s.nextLine();

	 	 System.out.println("Enter grade (It can be any of these : O, E, A, B, C, D, F) : ");
	 	 grade = s.nextLine();

	 	 System.out.println("Enter stream (It can be any of these : CSE, IT, ECE, EE, FT, ME) : ");
	 	 stream = s.nextLine();
		 
/*date_of_receive*/		
		 System.out.println("Todays date is : " +now );
	 	 System.out.println("Enter date_of_receive (date format yyyy-MM-dd) (It must be less than or equal to today's date) : ");
	 	 d = s.nextLine();	
			while(flag1==false)
			{
			 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 java.util.Date date = (java.util.Date)formatter.parse(d);
			 System.out.println("Date entered is " +date );
				if(date.compareTo(now) <= 0)
				{
			 	 System.out.println("Entered date is smaller than or equal to today's date. ACCEPTED");
				 date_of_receive = d;
				 flag1=true;
				 }
				else
				{
			 	 System.out.println("Entered date is larger than today's date . REJECTED");
				}
			}

/*date_of_issue*/ 
		  System.out.println("\n \n Todays date is : " +now );
	 	  System.out.println("Enter date_of_issue (date format yyyy-MM-dd) (It must be after date_of_receive, but less than or equal to today's date) : ");
	 	  d = s.nextLine();
		  flag1=false;
				if(d.equals("") || d == null)
				{ 
                                 String sql="insert into certificate_details values("+roll_number+","+certificate_number+",'"+student_name+"','"+grade+"','"+stream+"', to_date('"+date_of_receive+"', 'yyyy-MM-dd'),'')";
		 	 	 row_no = stmt.executeUpdate(sql);
				}
	 	 	 
				else
				{
				 	while(flag1==false)
				 	{
			 		 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 		 java.util.Date date = (java.util.Date)formatter.parse(d);
			 		 System.out.println("Date entered is " +date );
						if(date.compareTo(now) <= 0)
						{
			 	 		 System.out.println("Entered date is smaller than or equal to today's date. ACCEPTED");
				 		 date_of_issue = d;
				 		 flag1=true;
				 		}
						else
						{
			 	 		 System.out.println("Entered date is larger than today's date . REJECTED");
						}
					}
			 	 row_no = stmt.executeUpdate("insert into certificate_details values("+roll_number+","+certificate_number+",'"+student_name+"','"+grade+"','"+stream+"', to_date('"+date_of_receive+"', 'yyyy-MM-dd'), to_date('"+date_of_issue+"', 'yyyy-MM-dd'))");
				}


					 
	 	 System.out.println(row_no);
		}

		//catch(NullPointerException npe) {npe.printStackTrace();}
		catch(ParseException pe) {pe.printStackTrace();}
		catch(IOException ioe) { ioe.printStackTrace (); }
		catch(ClassNotFoundException cfe) { cfe.printStackTrace (); }
		catch(SQLException sqe) { sqe.printStackTrace (); }
		catch(NumberFormatException ne) { ne.printStackTrace (); }

	}
}
					 
/************************************************************************************************************************************/	

class Delete
{	
	public void delete()
	{
 	 int roll_number=0, certificate_number=0;
 	 String student_name, grade, stream, date_of_issue, date_of_receive;

	 InputStreamReader ireader = new InputStreamReader(System.in);
 	 BufferedReader breader = new BufferedReader(ireader);

	 Connection con=null;
	 Statement stmt=null;
	 ResultSet rs=null;
	 int row_no=0;
	 int n=0;

		try
		{
		 Class.forName("oracle.jdbc.driver.OracleDriver");
	 	 con = DriverManager.getConnection("jdbc:oracle:thin:@172.0.0.15:1521:orcl", "scott", "tiger");

	 	 System.out.println("DELETE a particular record from certificate_details table");

	 	 System.out.println("Enter the roll_number whose entire record you want to delete : ");
	 	 n = Integer.parseInt(breader.readLine());

	 	 stmt = con.createStatement();
	 	 row_no = stmt.executeUpdate("delete from certificate_details where roll_number="+n);

	 	 System.out.println(row_no);
		}

		//catch(NullPointerException npe) {npe.printStackTrace();}
		//catch(ParseException pe) {pe.printStackTrace();}
		catch(IOException ioe) { ioe.printStackTrace (); }
		catch(ClassNotFoundException cfe) { cfe.printStackTrace (); }
		catch(SQLException sqe) { sqe.printStackTrace (); }
		catch(NumberFormatException ne) { ne.printStackTrace (); }
	}
}
	
/************************************************************************************************************************************/	

class Retriever
{
	public void retrieve_roll()
	{
 	 int roll_number=0, certificate_number=0;
 	 String student_name, grade, stream, date_of_issue, date_of_receive;

	 InputStreamReader ireader = new InputStreamReader(System.in);
 	 BufferedReader breader = new BufferedReader(ireader);

	 Connection con=null;
	 Statement stmt=null;
	 ResultSet rs=null;
	 int n=0, flag=0;

		try
		{
		 Class.forName("oracle.jdbc.driver.OracleDriver");
	 	 con = DriverManager.getConnection("jdbc:oracle:thin:@172.0.0.15:1521:orcl", "scott", "tiger");

	 	 System.out.println("RETRIEVE details of a particular student from certificate_details table");

		 String m;
	 	 System.out.println("Enter the roll_number whose entire record you want to view : ");
	 	 n = Integer.parseInt(breader.readLine());
					 
	 	 stmt = con.createStatement();
	 	 rs = stmt.executeQuery("select * from certificate_details where roll_number="+n);
					 
	 	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					 
	 		while(rs.next())
			{
		 	 String d1 = rs.getString(6);
		 	 java.util.Date today1 = df.parse(d1);
		 	 String mydate1 = df.format(today1);
						 
		 	 String d2 = rs.getString(7);

				if(d2!=null)
				{
		 	 	 java.util.Date today2 = df.parse(d2);
		 	 	 String mydate2 = df.format(today2);
			 	 System.out.println(rs.getInt(1) +"\t"+ rs.getInt(2) +"\t"+ rs.getString(3) +"\t\t"+ rs.getString(4) +"\t"+ rs.getString(5) +"\t"+ mydate1 +"\t"+ mydate2);
							 
			 	 flag=1;
				}

				else
				{
		 	 	 System.out.println(rs.getInt(1) +"\t"+ rs.getInt(2) +"\t"+ rs.getString(3) +"\t\t"+ rs.getString(4) +"\t"+ rs.getString(5) +"\t"+ mydate1);

			 	 flag=1;
				}
			}

		 if(flag==0) { System.out.println("This roll_number does not exist in the database."); }
		}

		//catch(NullPointerException npe) {npe.printStackTrace();}
		catch(ParseException pe) {pe.printStackTrace();}
		catch(IOException ioe) { ioe.printStackTrace (); }
		catch(ClassNotFoundException cfe) { cfe.printStackTrace (); }
		catch(SQLException sqe) { sqe.printStackTrace (); }
		catch(NumberFormatException ne) { ne.printStackTrace (); }

	}
}

/************************************************************************************************************************************/	

class Retrievem
{
	public void retrieve_month()
	{
 	 int roll_number=0, certificate_number=0;
 	 String student_name, grade, stream, date_of_issue, date_of_receive;

	 InputStreamReader ireader = new InputStreamReader(System.in);
 	 BufferedReader breader = new BufferedReader(ireader);
	 Scanner s = new Scanner(System.in);

	 Connection con=null;
	 Statement stmt=null;
	 ResultSet rs=null;
	 String m;
	 int flag=0;


		try
		{
		 Class.forName("oracle.jdbc.driver.OracleDriver");
	 	 con = DriverManager.getConnection("jdbc:oracle:thin:@172.0.0.15:1521:orcl", "scott", "tiger");

		 System.out.println("RETRIEVE monthly view of received and issued certificates from certificate_details table");

		 System.out.println("Enter the month of date_of_receive whose entire record you want to view (month format is mm) : ");
		 m = s.nextLine();

		 stmt = con.createStatement();
		 rs = stmt.executeQuery("select * from certificate_details where to_char(date_of_receive, 'mm')='"+m+"'");

		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		 	while(rs.next())
			{
			 String d1 = rs.getString(6);
			 java.util.Date today1 = df.parse(d1);
			 String mydate1 = df.format(today1);
						 
			 String d2 = rs.getString(7);
				if(d2!=null)
				{
			 	 java.util.Date today2 = df.parse(d2);
			 	 String mydate2 = df.format(today2);
							 
				 System.out.println(rs.getInt(1) +"\t"+ rs.getInt(2) +"\t"+ rs.getString(3) +"\t\t"+ rs.getString(4) +"\t"+ rs.getString(5) +"\t"+ mydate1 +"\t"+ mydate2);
				}

				else
				{
			 	 System.out.println(rs.getInt(1) +"\t"+ rs.getInt(2) +"\t"+ rs.getString(3) +"\t\t"+ rs.getString(4) +"\t"+ rs.getString(5) +"\t"+ mydate1);
				}
			}

		System.out.println("Enter the month of date_of_issue whose entire record you want to view (month format is mm) : ");
		m = s.nextLine();

		rs = stmt.executeQuery("select * from certificate_details where to_char(date_of_issue, 'mm')='"+m+"'");

		df = new SimpleDateFormat("yyyy-MM-dd");

		 	while(rs.next())
			{
			 String d1 = rs.getString(6);
			 java.util.Date today1 = df.parse(d1);
			 String mydate1 = df.format(today1);
						 
			 String d2 = rs.getString(7);
				if(d2!=null)
				{
			 	 java.util.Date today2 = df.parse(d2);
			 	 String mydate2 = df.format(today2);
							 
				 System.out.println(rs.getInt(1) +"\t"+ rs.getInt(2) +"\t"+ rs.getString(3) +"\t\t"+ rs.getString(4) +"\t"+ rs.getString(5) +"\t"+ mydate1 +"\t"+ mydate2);
				}

				else
				{
			 	 System.out.println(rs.getInt(1) +"\t"+ rs.getInt(2) +"\t"+ rs.getString(3) +"\t\t"+ rs.getString(4) +"\t"+ rs.getString(5) +"\t"+ mydate1);
				}
			}
		}

		//catch(NullPointerException npe) {npe.printStackTrace();}
		catch(ParseException pe) {pe.printStackTrace();}
		//catch(IOException ioe) { ioe.printStackTrace (); }
		catch(ClassNotFoundException cfe) { cfe.printStackTrace (); }
		catch(SQLException sqe) { sqe.printStackTrace (); }
		catch(NumberFormatException ne) { ne.printStackTrace (); }

	}
}

/************************************************************************************************************************************/	

class Retrievea
{
	public void retrieve_all()
	{
 	 int roll_number=0, certificate_number=0;
 	 String student_name, grade, stream, date_of_issue, date_of_receive;

	 Connection con=null;
	 Statement stmt=null;
	 ResultSet rs=null;
		try
		{
		 Class.forName("oracle.jdbc.driver.OracleDriver");
	 	 con = DriverManager.getConnection("jdbc:oracle:thin:@172.0.0.15:1521:orcl", "scott", "tiger");
		 
	 	 System.out.println("RETRIEVE all details from certificate_details table");

	 	 stmt = con.createStatement();
	 	 rs = stmt.executeQuery("select * from certificate_details");

	 	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	 		while(rs.next())
			{
		 	 String d1 = rs.getString(6);
		 	 java.util.Date today1 = df.parse(d1);
		 	 String mydate1 = df.format(today1);
						 
		 	 String d2 = rs.getString(7);

				if(d2!=null)
				{
		 	 	 java.util.Date today2 = df.parse(d2);
		 	 	 String mydate2 = df.format(today2);
							 
			 	 System.out.println(rs.getInt(1) +"\t"+ rs.getInt(2) +"\t"+ rs.getString(3) +"\t\t"+ rs.getString(4) +"\t"+ rs.getString(5) +"\t"+ mydate1 +"\t"+ mydate2);
				}

				else
				{
		 	 	 System.out.println(rs.getInt(1) +"\t"+ rs.getInt(2) +"\t"+ rs.getString(3) +"\t\t"+ rs.getString(4) +"\t"+ rs.getString(5) +"\t"+ mydate1);
				}
			}
		}

		catch(NullPointerException npe) {npe.printStackTrace();}
		catch(ParseException pe) {pe.printStackTrace();}
		//catch(IOException ioe) { ioe.printStackTrace (); }
		catch(ClassNotFoundException cfe) { cfe.printStackTrace (); }
		catch(SQLException sqe) { sqe.printStackTrace (); }
		catch(NumberFormatException ne) { ne.printStackTrace (); }
	
	}
}
