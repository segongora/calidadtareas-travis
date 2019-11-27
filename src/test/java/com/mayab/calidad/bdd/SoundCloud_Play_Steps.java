package com.mayab.calidad.bdd;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SoundCloud_Play_Steps {

	private static WebDriver driver;
	private WebElement button;

	@Given("I launch ChromeDriver")
	public void i_launch_ChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users/segongora/chromedriver");	
		driver = new ChromeDriver(); //Launch chrome browser	   
	}

	@When("I open the SoundCloud song URL")
	public void i_open_the_SoundCloud_song_URL() {
		driver.get("https://soundcloud.com/1991_music/steezy");
		pause(6000);
	}

	@When("I click the pause_play button")
	public void i_click_the_pause_play_button() {
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div[2]/div[2]/div/div/div[1]/a")).click(); 
		pause(6000);
	}

	@Then("I verify if the song is either paused or playing")
	public void i_verify_if_the_song_is_either_paused_or_playing() {
		button=driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div[2]/div[2]/div/div/div[1]/a"));
		System.out.println(button.getText()); //"Reproducir" "Pausar", "Play" "Pause"
		String msg=button.getText();
		assertThat(msg, isOneOf("Play", "Pause"));
	}

	@Then("I close the browser")
	public void i_close_the_browser() {
		pause(6000);
		driver.quit();	  
	}

	public void pause(long millis) {
		try {
			Thread.sleep(millis);		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
