package com.fetch.pages;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fetch.utilities.Driver;

public class FetchPage extends BasePage {

	int WAIT_TIME_OUT = 1000;
	static String fakeGold = null;

	@FindBy(xpath = "//div[@class='coins']/button")
	public List<WebElement> numbers;

	@FindBy(id = "weigh")
	public WebElement weigh;

	@FindBy(xpath = "//li")
	public WebElement result;

	@FindBy(xpath = "(//button[@id='reset'])[2]")
	public static WebElement reset;

	public static void fakeNumber(String number) {
		Driver.get().findElement(By.xpath("//button[@id='coin_" + number + "']")).click();
	}

	public static void wait1() {
		WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(reset));
	}

	public String userFindsTheBestAlgorithmToFindTheFakeGoldBar() {
		int left = numbers.size() / 2;
		int right = numbers.size() - numbers.size() / 2; // 4
	
		// First step left
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

		// Second Step left
		wait1();
		if (result.getText().contains("<")) {
			wait1();
			reset.click();
			wait1();
			Driver.get().findElement(By.id("left_0")).sendKeys("0");
			Driver.get().findElement(By.id("left_1")).sendKeys("1");
			Driver.get().findElement(By.id("right_2")).sendKeys("2");
			Driver.get().findElement(By.id("right_3")).sendKeys("3");
			wait1();
			weigh.click();
			wait1();
			if (Driver.get().findElement(By.xpath("//li[2]")).getText().contains("<")) {
				reset.click();
				wait1();
				Driver.get().findElement(By.id("left_" + "0")).sendKeys("0");
				Driver.get().findElement(By.id("right_" + "1")).sendKeys("1");
				weigh.click();
				wait1();
				if (Driver.get().findElement(By.xpath("//li[3]")).getText().contains(">")) {
					fakeGold = "1";
					fakeNumber(fakeGold);
					return fakeGold;
				} else {
					fakeGold = "0";
					fakeNumber(fakeGold);
					return fakeGold;
				}
			}
			if (Driver.get().findElement(By.xpath("//li[2]")).getText().contains(">")) {
				reset.click();
				wait1();
				Driver.get().findElement(By.id("left_" + "2")).sendKeys("2");
				Driver.get().findElement(By.id("right_" + "3")).sendKeys("3");
				weigh.click();
				wait1();

				if (Driver.get().findElement(By.xpath("//li[3]")).getText().contains(">")) {
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

		if (Driver.get().findElement(By.xpath("//li")).getText().contains(">")) {
			wait1();
			reset.click();
			wait1();
			Driver.get().findElement(By.id("left_5")).sendKeys("5");
			Driver.get().findElement(By.id("left_6")).sendKeys("6");
			Driver.get().findElement(By.id("right_7")).sendKeys("7");
			Driver.get().findElement(By.id("right_8")).sendKeys("8");
			weigh.click();
			wait1();
			if (Driver.get().findElement(By.xpath("//li[2]")).getText().contains("<")) {
				reset.click();
				wait1();
				Driver.get().findElement(By.id("left_" + "5")).sendKeys("5");
				Driver.get().findElement(By.id("right_" + "6")).sendKeys("6");
				weigh.click();
				wait1();

				if (Driver.get().findElement(By.xpath("//li[3]")).getText().contains(">")) {
					fakeGold = "6";
					fakeNumber(fakeGold);
					return fakeGold;
				} else {
					fakeGold = "5";
					fakeNumber(fakeGold);
					return fakeGold;
				}
			}
			if (Driver.get().findElement(By.xpath("//li[2]")).getText().contains(">")) {
				reset.click();
				wait1();
				Driver.get().findElement(By.id("left_7")).sendKeys("7");
				Driver.get().findElement(By.id("right_8")).sendKeys("8");
				weigh.click();
				wait1();

				if (Driver.get().findElement(By.xpath("//li[3]")).getText().contains(">")) {
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
		assertEquals(alert.getText(), "Yay! You find it!");
		System.out.println("FAKE GOLD BAR IS "+fakeGold);
		alert.accept();
		Driver.get().close();
	}
}
