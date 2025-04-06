package cdpXselenium;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.Request;
import org.openqa.selenium.devtools.v132.network.model.Response;

import org.openqa.selenium.devtools.v132.fetch.Fetch;


public class NetworkMocking {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		

		
		ChromeDriver driver = new ChromeDriver();
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
		devTools.addListener(Fetch.requestPaused(), request->
		{
			if(request.getRequest().getUrl().contains("shetty"))
			{
				String mockedUrl =request.getRequest().getUrl().replace("=shetty", "=BadGuy");
				
				System.out.println(mockedUrl);
				
				
				devTools.send(Fetch.continueRequest(
					    request.getRequestId(),
					    Optional.of(mockedUrl),
					    Optional.of(request.getRequest().getMethod()),
					    Optional.empty(),
					    Optional.empty(),
					    Optional.of(true) 
					));
			}
			else {
				
				devTools.send(Fetch.continueRequest(
					    request.getRequestId(),
					    Optional.of(request.getRequest().getUrl()),
					    Optional.of(request.getRequest().getMethod()),
					    Optional.empty(),
					    Optional.empty(),
					    Optional.of(true) 
					));

				
			}
			
		});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
		Thread.sleep(3000);
		
		System.out.println(driver.findElement(By.cssSelector("p")).getText());
		
		
		
		
		
		
		
		
		
		
	}

}
