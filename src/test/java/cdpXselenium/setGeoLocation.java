package cdpXselenium;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.emulation.Emulation;

public class setGeoLocation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeDriver driver = new ChromeDriver();
		DevTools devtools = driver.getDevTools();
		devtools.createSession();
		double latitude = 40.0;
		double longitude = 3.0;
		double accuracy = 1.0;

		devtools.send(
				Emulation.setGeolocationOverride(Optional.of(latitude), Optional.of(longitude), Optional.of(accuracy)));

		driver.get("https://google.com");
		driver.findElement(By.xpath("//textarea[@title='Search']")).sendKeys("netflix", Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='byrV5b']")).click();
		String title = driver.findElement(By.xpath("//div[@class='default-ltr-cache-1d3w5wq'] //h1")).getText();
		System.out.println(title);

	}

}
