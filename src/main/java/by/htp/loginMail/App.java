package by.htp.loginMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
	private static final String CHROME = "webdriver.chrome.driver";
	private static final String CHROME_PATH = "src/main/resoursces/chromedriver.exe";
	
	public static void main(String[] args) throws InterruptedException {
		String emailTo = "sviatlana.zakharenka@gmail.com";
		
		System.setProperty(CHROME, CHROME_PATH);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://mail.ru");

		//--- login page ---

//		String xpathTxtLogin = "//input[@id='mailbox:login']";
//		By txtLogin = By.xpath(xpathTxtLogin);
//		WebElement element =  driver.findElement(txtLogin);

		WebElement txtLogin =  driver.findElement(By.xpath("//child::input[attribute::id='mailbox:login']"));//input[@id='mailbox:login']
		txtLogin.clear();
		txtLogin.sendKeys("tathtp");

		WebElement txtPassword =  driver.findElement(By.xpath("//child::input[attribute::id='mailbox:password']"));//input[@id='mailbox:password']
		txtPassword.clear();
		txtPassword.sendKeys("Klopik123");

		WebElement buttonEnter =  driver.findElement(By.xpath("//child::input[attribute::class='o-control']"));//input[@class='o-control']
		buttonEnter.click();

		//--- main email page ---
		Thread.sleep(10000); //wait open page 
		WebElement buttonWriteEmail =  driver.findElement(By.xpath("//child::div[attribute::id='b-toolbar__left']//child::span")); //div[@id='b-toolbar__left']//span
		buttonWriteEmail.click();
		
		Thread.sleep(250); //wait open page writte new email

		WebElement txtTo =  driver.findElement(By.xpath("//child::textarea[attribute::data-original-name='To']"));
		txtTo.clear();
		txtTo.sendKeys(emailTo);
		
		WebElement txtSubject =  driver.findElement(By.xpath("//child::input[attribute::name='Subject']"));
		txtSubject.clear();
		txtSubject.sendKeys("My email for you!!");
		
		WebElement elementIframe = driver.findElement(By.xpath("//child::td/child::iframe"));
		driver.switchTo().frame(elementIframe);
		
		WebElement txtBody =  driver.findElement(By.xpath("//child::body[attribute::id='tinymce']"));
		txtBody.sendKeys("HTP_TAT9 Bialko Natasha: I have done it!!");
		
		//https://www.guru99.com/handling-iframes-selenium.html
		//driver.switchTo().defaultContent();
		driver.switchTo().parentFrame();
		
		WebElement buttonSent =  driver.findElement(By.xpath("//child::div[attribute::class='b-toolbar']//child::span[attribute::class='b-toolbar__btn__text'][1]"));
		buttonSent.click();
		
		Thread.sleep(10000);
		driver.close();
	}
}
