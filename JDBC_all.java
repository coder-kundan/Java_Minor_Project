import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

 public class JDBC_all { //This is main class
	
	 public String table=new String();
	 JDBC_all(){
		 Scanner sc=new Scanner(System.in);
			System.out.println("Enter Table name");
			table=sc.nextLine();
	
	}
			
  void insert_value() { // this function uses for insert the value.
   {
	 try
       {
	   Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
          PreparedStatement psmt= con.prepareStatement("insert into "+table+" values(?,?,?)");
          BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
   while(true)
   {
 	System.out.println("Enter Emp Id");
 	int eno =Integer.parseInt(br.readLine());
 	
 	System.out.println("Enter Emp name: ");
 	String ename=br.readLine();
 	
 	System.out.println("Enter Emp salary: "); 
     double sal=Double.parseDouble(br.readLine());
     psmt.setInt(1,eno);
     psmt.setString(2,ename);
     psmt.setDouble(3,sal);
     int count=psmt.executeUpdate();
     if(count>0)
     {
     	System.out.println(count+"record Inserted");
     }
     else
     	System.out.println("No record Inserted");
     System.out.println("Do you want to more Record...");
     String ch=br.readLine();
     if (ch.equalsIgnoreCase("NO"))
     break;
 }
}
  catch(Exception e)
    {
	  System.out.println(e);
     }

}
  }

	
 void create_connection() // use of it's fuction for create new table
	{
	 
   try
   {
	Class.forName("oracle.jdbc.driver.OracleDriver");
     Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
      Statement smt=con.createStatement();
      smt.executeUpdate("create table "+table+"(eno number,ename varchar(12),esal number)");
     System.out.println("Table created Successfully....");
   }
   catch(Exception e1)
   {
	System.out.print(e1); 
   }
	
  }
 void select() {//this Method is used to select the table 
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
		Statement smt=con.createStatement();
		ResultSet rs=smt.executeQuery("select * from "+table);
		while(rs.next())
		{
			int eid=rs.getInt(1);
			String ename=rs.getString(2);
			double esal=rs.getDouble(3);
			System.out.println("Employee Id: "+eid);
			System.out.println("Employee Ename "+ename);
			System.out.println("Employee Salary "+esal);
		}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
 }


	void Upadate() {
		String value; int eid; double esal;
		 try
		   {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		   Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
		   Statement smt= con.createStatement();
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      
         {
         	System.out.println("Enter Emp Id");
         	 value=br.readLine();
         	eid=Integer.parseInt(value);
         	
         	
         	System.out.println("Enter Emp salary: ");
         	value=br.readLine();
             esal=Double.parseDouble(value);
             
             
             int count=smt.executeUpdate("update "+table+" set esal="+esal+"where eno="+eid);
             if(count>0)
             {
             	System.out.println(count+"One row Updated");
             }
             else
             	System.out.println("No rows found");
             
         }
	      }
		  catch(Exception e)
		 {
			  System.out.println(e);
		 }

}

	 void JDBC_delete() {
	
		 try
		   {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		   Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
		   Statement smt= con.createStatement();
		   
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            
           while(true)
           {
            	System.out.println("Enter Emp Id which yoy want to delete");
            	int eno= Integer.parseInt(br.readLine());
    
               
                int count=smt.executeUpdate("delete from employee where eno="+eno);
                if(count>=1)
                {
                	System.out.println(count+"record Deleted");
                }
                else
                	System.out.println("No record deleted: ");
                System.out.println("Do you want to more recod delete:[yes/no] ");
            String ch= br.readLine();
            if(ch.equalsIgnoreCase("no"))
                 break;
           }
 }     
		  catch(Exception e)
		 {
			  System.out.println(e);
		 }
		   
}	

	public static void main(String[] args) {
		Scanner sm=new Scanner(System.in);
   
     
     System.out.println("\n*********Welcome to the Employee Management System**********\n");
		System.out.println("1). Create Table to the DataBase\n" +
							"2).select and show for Column\n" +
							"3). Edit Employee details\n" +
							"4). Insert the value in Database in table\n" +
							"5). Delete the colum in the table\n" +
							"6). EXIT\n");
		do {
			  JDBC_all jb=new JDBC_all();
		System.out.println("Enter your choice : ");
		int ch = sm.nextInt();
		switch (ch)
		{
		case 1:
		{
			jb.create_connection();
			break;
		}
		case 2:
		{
			jb.select();
			break;
		}
		case 3:
		{
			jb.Upadate();
			break;
		}
		case 4:
		{
			jb.insert_value();
			break;
		}
		case 5:
		{
			jb.JDBC_delete();
			break;
		}
		default:
		{
			break;
		}
		}
	}
	
  while(true);
	}
}