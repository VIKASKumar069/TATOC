import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;

public class tatoc {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\DeadpooL\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		String url = "http://10.0.1.86/tatoc";
		boolean check = true;
		
		driver.get(url);
		
		List<WebElement> res = driver.findElements(By.tagName("a"));
		res.get(0).click();
		
		driver.findElement(By.className("greenbox")).click();
		
		driver.switchTo().frame("main");
		WebElement box1 = driver.findElement(By.id("answer"));
		String box1_color = box1.getAttribute("class");
		
		while(check) {
		driver.switchTo().frame("child");
		if(box1_color.equals(driver.findElement(By.id("answer")).getAttribute("class")))
		check = false;
		else {
			driver.switchTo().parentFrame();
		    driver.findElement(By.linkText("Repaint Box 2")).click();
		}
		}
		driver.switchTo().parentFrame();
		driver.findElement(By.linkText("Proceed")).click();
		
		WebElement source = driver.findElement(By.id("dragbox"));
		WebElement target = driver.findElement(By.id("dropbox"));
		
		Actions act = new Actions(driver);
		act.dragAndDrop(source, target).build().perform();
		driver.findElement(By.cssSelector("body > div > div.page > a")).click();
		
		driver.findElement(By.linkText("Launch Popup Window")).click();
		
		String parentWindowHandle = driver.getWindowHandle(); // save the current window handle.
		  WebDriver popup = null;
		  Set<String> handles = driver.getWindowHandles();
		  Iterator<String> windowIterator = handles.iterator();
		  while(windowIterator.hasNext()) { 
		    String windowHandle = windowIterator.next(); 
		    popup = driver.switchTo().window(windowHandle);  
		  if (popup.getTitle().equals("Popup - Basic Course - T.A.T.O.C")) {
		    	driver.findElement(By.id("name")).sendKeys("VIKAS Kumar");
		    	driver.findElement(By.id("submit")).click();
		    }
		  }
		driver.switchTo().window(parentWindowHandle);
		driver.findElement(By.linkText("Proceed")).click();
		
		driver.findElement(By.linkText("Generate Token")).click();
		String token = driver.findElement(By.id("token")).getText();
		String token_value = token.substring(7, token.length());
		Cookie name = new Cookie("Token",token_value);
		driver.manage().addCookie(name);
		driver.findElement(By.linkText("Proceed")).click();
	
	}

}
