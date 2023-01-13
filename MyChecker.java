import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class MyChecker {

	public static void main(String args[])
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getPNR());
	}
	public static boolean checkName(String s)
	{
		s=s.trim();
		int pos=0,count=0;
		while(s.indexOf(" ",pos)!=-1) {
			pos=s.indexOf(" ",pos);
			pos++;
			count++;
		}
		if(count>2||count<2) {
			return false;
		}
		else
		return true;
	}
	public static boolean checkEmail(String s)
	{
		s=s.trim();
		String regex="[a-z A-Z 0-9+_.-]+[@]{1}[a-z A-Z]+[.]{1}[a-z A-Z]{1,3}";
		return s.matches(regex);
	}
	public static boolean checkMo(String s)
	{
		if(s.length()!=10)return false;
		else
		try {
			long num=Long.parseLong(s);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	public static boolean checkUserid(String s)
	{
		try{

			Connection	conn=DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6524224","sql6524224","9FcbyfM3Nw");
			PreparedStatement stat=conn.prepareStatement("select * from Login_Info where User_id=?");
			stat.setString(1,s);
			ResultSet rs=stat.executeQuery();
			if(rs.next()) return true;
			else return false;
		}catch(Exception e1){
			System.out.println(e1);
			return false;
		}
	}
	public static boolean checkPass(String s)
	{
		String regex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$^&+=_-])(?=\\S+$).{8,20}$";
		return s.matches(regex);
	}
	public static int getPNR()
	{
		int result=0;
		Random r=new Random();
		for(int i=0;i<=11;i++)
		{
			result+=r.nextInt()*(10^i);
		}
		result=Math.abs(result);
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6524224","sql6524224","9FcbyfM3Nw");
			Statement stat=conn.createStatement();
			ResultSet rs=stat.executeQuery("select * from PNR_Details where PNR="+result);
			if(rs.next())
				return getPNR();
			else
				return result;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;

	}
	public static boolean checkDate(String s)
	{
		if(s.length()!=10) return false;
		else
		{
			try {
			int date=Integer.parseInt(s.substring(0,2)+s.substring(3,5)+s.substring(6,10));
			return true;
			}catch(Exception e) {return false;}
		}
	}

}
