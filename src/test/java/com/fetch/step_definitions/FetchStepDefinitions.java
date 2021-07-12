package com.fetch.step_definitions;

import com.fetch.pages.FetchPage;
import com.fetch.utilities.ConfigurationReader;
import com.fetch.utilities.Driver;

import io.cucumber.java.en.*;

public class FetchStepDefinitions {
	FetchPage fetchPage = new FetchPage();

	@Given("user is on the page")
	public void user_is_on_the_login_page() {
		//System.out.println("I am on the Fetch page");
		Driver.get().get(ConfigurationReader.getProperty("url"));
	}

	@Then("user finds the best algorithm to find the fake gold bar")
	public void userFindsTheBestAlgorithmToFindTheFakeGoldBar() throws InterruptedException {
	//	System.out.println("user finds the best algorithm to find the fake gold bar");
		fetchPage.userFindsTheBestAlgorithmToFindTheFakeGoldBar();
	}

	@Then("user verify result and close the browser")
	public void user_verify_result_and_close_the_browser() throws InterruptedException {
	//	System.out.println("user verify result and close the browser");
		FetchPage.user_verify_result_and_close_the_browser();
	}
}