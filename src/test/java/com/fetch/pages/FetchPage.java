package com.fetch.pages;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

	public static void fakeNumber(String number) {
		Driver.get().findElement(By.xpath("//button[@id='coin_" + number + "']")).click();
	}

	public static void leftElement(String i) {
		Driver.get().findElement(By.id("left_" + i)).sendKeys(i);
	}

	public static void rightElement(String i) {
		Driver.get().findElement(By.id("right_" + i)).sendKeys(i);
	}

	public static Boolean resultElement(String i) {
		Boolean res = Driver.get().findElement(By.xpath("//li[" + results.size() + "]")).getText().contains(i);
		return res;
	}

	public String userFindsTheBestAlgorithmToFindTheFakeGoldBar() {
		int left = numbers.size() / 2;
		int right = numbers.size() - numbers.size() / 2; // 4

		for (int i = 0; i < left; i++) {
			String i2 = i + "";
			Driver.get().findElement(By.id("left_" + i)).sendKeys(i2);
		}

		for (int i = right; i < numbers.size(); i++) {
			String i2 = i + "";
			Driver.get().findElement(By.id("right_" + i)).sendKeys(i2);
		}

		weigh.click();
		if (result.getText().contains("=")) {
			fakeGold = "4";
			fakeNumber(fakeGold);
			return fakeGold;
		}

		if (resultElement("<")) {
			reset.click();
			leftElement("0");
			leftElement("1");
			rightElement("2");
			rightElement("3");
			weigh.click();
			if (resultElement("<")) {
				reset.click();
				leftElement("0");
				rightElement("1");
				weigh.click();
				if (resultElement(">")) {
					fakeGold = "1";
					fakeNumber(fakeGold);
					return fakeGold;
				} else {
					fakeGold = "0";
					fakeNumber(fakeGold);
					return fakeGold;
				}
			}
			if (resultElement(">")) {
				reset.click();
				leftElement("2");
				rightElement("3");
				weigh.click();
				if (resultElement(">")) {
					fakeGold = "3";
					fakeNumber(fakeGold);
					return fakeGold;
				} else {
					fakeGold = "2";
					fakeNumber(fakeGold);
					return fakeGold;
				}
			}
		}

		if (resultElement(">")) {
			reset.click();
			leftElement("5");
			leftElement("6");
			rightElement("7");
			rightElement("8");
			weigh.click();
			if (resultElement("<")) {
				reset.click();
				leftElement("5");
				rightElement("6");
				weigh.click();
				if (resultElement(">")) {
					fakeGold = "6";
					fakeNumber(fakeGold);
					return fakeGold;
				} else {
					fakeGold = "5";
					fakeNumber(fakeGold);
					return fakeGold;
				}
			}
			if (resultElement(">")) {
				reset.click();
				leftElement("7");
				rightElement("8");
				weigh.click();
				if (resultElement(">")) {
					fakeGold = "8";
					fakeNumber(fakeGold);
					return fakeGold;
				} else {
					fakeGold = "7";
					fakeNumber(fakeGold);
					return fakeGold;
				}
			}
		}
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
