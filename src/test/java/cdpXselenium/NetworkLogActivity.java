package cdpXselenium;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.Request;
import org.openqa.selenium.devtools.v132.network.model.Response;

public class NetworkLogActivity {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();

		DevTools devtools = driver.getDevTools();
		devtools.createSession(); 
		devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		devtools.addListener(Network.requestWillBeSent(), request ->{
			Request req =request.getRequest();
			System.out.println(req.getUrl());
			
			
			
		});
		devtools.addListener(Network.responseReceived(), response ->{
			
			Response res =response.getResponse();
//			System.out.println(res.getUrl());
//			System.out.println(res.getStatus());
			if(res.getStatus().toString().startsWith("4")) {
				
				System.out.println(res.getUrl()+ "is failling with status code:"+res.getStatus());
				
			}
			
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
	}	
		
		
	}


