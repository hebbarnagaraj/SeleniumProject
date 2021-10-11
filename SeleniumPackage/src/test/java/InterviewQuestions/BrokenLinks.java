package InterviewQuestions;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import basePackage.baseClass;

public class BrokenLinks extends baseClass{
	
	String URLhome=System.getProperty("homeURL");
	
	@Test
	public void verifyBrokenlinks() {
		
		String url="";
		int brokenCount=0;
		
		driver.get(URLhome);
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		System.out.println("Number of links present in domain is :"+links.size());
		System.out.println("========================================");
		
		Iterator<WebElement> itr = links.iterator();
		
		while(itr.hasNext()) {
			
			url=itr.next().getAttribute("href");
			
			if(url.isEmpty() || url.equals(null)) {
				System.out.println("URL is not configured");
			}
			if(!url.startsWith(URLhome)) {
				System.out.println("URL \t "+url+"\t belongs to other domain");
				System.out.println("========================================");
			}
			
			try {
				 HttpsURLConnection conction = (HttpsURLConnection)new URL(url).openConnection();
				 conction.setRequestMethod("HEAD");
				 
				 conction.connect();
				 
				 int responsestatus = conction.getResponseCode();
				 
				 if(responsestatus!=200) {
					 System.out.println("URL \t "+url+"is broken and status code is :\t "+responsestatus);
					 System.out.println("========================================");
					 brokenCount++;
				 }
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(brokenCount==0) {
			System.out.println("=================No URL is broken in the domain=====================");
		}
		else {
			System.out.println("Number of broken URls is :"+brokenCount);
			System.out.println("========================================");
		}
	}

}
