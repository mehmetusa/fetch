package com.fetch.pages;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import com.fetch.utilities.Driver;

public class FetchPage extends BasePage {

	int WAIT_TIME_OUT = 1000;
	static String fakeGold = null;
	static String greeting = "Yay! You find it!";

	@FindBy(xpath = "//div[@class='coins']/button")
	public List<WebElement> numbers;

	@FindBy(id = "weigh")
	public WebElement weigh;

	@FindBy(xpath = "//li")
	public WebElement result;

	@FindBy(xpath = "//li")
	public static List<WebElement> results;

	@FindBy(xpath = "(//button[@id='reset'])[2]")
	public static WebElement reset;

	@FindBy(xpath = "//input[@value !='']")
	public List<WebElement> clear;

	void fakeNumber(String number) {
		System.out.println("number----"+number);
		Driver.get().findElement(By.xpath("//button[@id='coin_" + number + "']")).click();
	}

	void element(String i, String direction) {
		Driver.get().findElement(By.id(direction + i)).sendKeys(i);
	}

	boolean resultElement(String i,String step) throws InterruptedException {
   		Boolean res = Driver.get().findElement(By.xpath("//li[" + step + "]")).getText().contains(i);
		By icon = By.xpath("//li[" + results.size() + "][contains(.,'"+i+"')]");
	    
		WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
	    wait.until(ExpectedConditions.invisibilityOfElementWithText(icon, i));
	//	Thread.sleep(500);

		return res;
	}

	
	public String userFindsTheBestAlgorithmToFindTheFakeGoldBar() throws InterruptedException {
		String[] left_Numbers = { "0", "1", "2", "3" };
		String[] right_Numbers = { "5", "6", "7", "8" };
		String LEFT_SIDE =  "left_";
		String RIGHT_SIDE= "right_";
		String SMALLER =  "<";
		String BIGGER= ">";
		String EQUAL_SYMBOL= "=";
		
		
		for (String i : left_Numbers) {
			element(i, LEFT_SIDE);
		}

		for (String i : right_Numbers) {
			element(i, RIGHT_SIDE);
		}

		weigh.click();
		if (result.getText().contains(EQUAL_SYMBOL)) {
			fakeGold = "4";
			fakeNumber(fakeGold);
			return fakeGold;
		}

		if (resultElement(SMALLER,"1")) {
			reset.click();
			element(left_Numbers[0], LEFT_SIDE);
			element(left_Numbers[1], LEFT_SIDE);
			element(left_Numbers[2], RIGHT_SIDE);
			element(left_Numbers[3], RIGHT_SIDE);
			weigh.click();
			if (resultElement(SMALLER,"2")) {
				reset.click();
			
				element(left_Numbers[0], LEFT_SIDE);
				element(left_Numbers[1], RIGHT_SIDE);
				weigh.click();

				if (resultElement(SMALLER,"3")) {
					fakeGold = left_Numbers[0];
					fakeNumber(fakeGold);
					System.out.println(fakeGold);
					return fakeGold;
				} 
				if (resultElement(BIGGER,"3")){
					fakeGold = left_Numbers[1];
					fakeNumber(fakeGold);
					System.out.println(fakeGold);
					return fakeGold;
				}
			}
			if (resultElement(BIGGER,"2")) {
				reset.click();
				element(left_Numbers[2], LEFT_SIDE);
				element(left_Numbers[3], RIGHT_SIDE);
				weigh.click();

				if (resultElement(SMALLER,"3")) {
					fakeGold = left_Numbers[2];
					fakeNumber(fakeGold);
					return fakeGold;
				} 
				if (resultElement(BIGGER,"3")){
					fakeGold = left_Numbers[3];
					fakeNumber(fakeGold);
					return fakeGold;
				}
			}
		}

		if (resultElement(BIGGER,"1")) {
			reset.click();
			element(right_Numbers[0], LEFT_SIDE);
			element(right_Numbers[1], LEFT_SIDE);
			element(right_Numbers[2], RIGHT_SIDE);
			element(right_Numbers[3], RIGHT_SIDE);
			weigh.click();

			if (resultElement(SMALLER,"2")) {
				reset.click();
				element(right_Numbers[0], LEFT_SIDE);
				element(right_Numbers[1], RIGHT_SIDE);
				weigh.click();

				if (resultElement(SMALLER,"3")) {
					fakeGold = right_Numbers[0];
					fakeNumber(fakeGold);
					return fakeGold;
				} 
				
				if (resultElement(BIGGER,"3")) {
					fakeGold =right_Numbers[1];
					fakeNumber(fakeGold);
					return fakeGold;
				}
			}
			if (resultElement(BIGGER,"2")) {
				reset.click();
				element(right_Numbers[2], LEFT_SIDE);
				element(right_Numbers[3], RIGHT_SIDE);
				weigh.click();

				if (resultElement(SMALLER,"3")) {
					fakeGold = right_Numbers[2];
					fakeNumber(fakeGold);
					return fakeGold;
				} 
				if (resultElement(BIGGER,"3")) {
					fakeGold = right_Numbers[3];
					fakeNumber(fakeGold);
					return fakeGold;
				}
			}
		}
		
		System.out.println("fakeGold............."+fakeGold);
		return fakeGold;
	}

	public static void user_verify_result_and_close_the_browser() throws UnhandledAlertException {
		Alert alert = Driver.get().switchTo().alert();
		assertEquals(alert.getText(), greeting);
		System.out.println("FAKE GOLD BAR IS " + fakeGold);
		alert.accept();
		Driver.get().close();
	}
}
