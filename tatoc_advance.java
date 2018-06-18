import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;

public class tatoc_advance {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\DeadpooL\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		String url = "http://10.0.1.86/tatoc";
		
		driver.get(url);
		

		driver.findElement(By.linkText("Advanced Course")).click();
		
		Actions builder = new Actions(driver);
		WebElement menu2 = driver.findElement(By.cssSelector("body > div.wrapper > div.page > div.menutop.m2"));
		WebElement go_next = driver.findElement(By.cssSelector("body > div.wrapper > div.page > div.menutop.m2 > span:nth-child(5)"));
		Action mouseovermenu2 = builder.moveToElement(menu2).click(go_next).build();
		mouseovermenu2.perform();
//		Select drpdown = new Select(driver.findElement(By.cssSelector("body > div.wrapper > div.page > div.menutop.m2")));
	//	Select drpdown = new Select(menu2);
		//drpdown.selectByVisibleText("Go Next");
		
		String symbol = driver.findElement(By.id("symbol")).getAttribute("value");
		
		String dbUrl = "jdbc:mysql://10.0.1.86/tatoc";
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(dbUrl,"tatocuser","tatoc01");
		
		PreparedStatement stmt=con.prepareStatement("select * from identity");  
		PreparedStatement stmt1=con.prepareStatement("select * from credentials");  
		
		ResultSet rs= stmt.executeQuery();	
 		ResultSet rs1= stmt1.executeQuery();
 		
 		int index =0;
 		String user, pass;
 		
 		while(rs.next()) {
 			if(symbol.equals(rs.getString(2))) {
 				index = rs.getInt(1);
 				break;
 			}
 		}
 		
 		rs1.absolute(index);
 		user = rs1.getString(2);
 		pass = rs1.getString(3);
 		driver.findElement(By.id("name")).sendKeys(user);
 		driver.findElement(By.id("passkey")).sendKeys(pass);
 		driver.findElement(By.id("submit")).click();
 		
        
		
		
		
		
		
		
		
		
		
	
	}
}
