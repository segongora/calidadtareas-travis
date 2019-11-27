package com.mayab.calidad.funcionales;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class FacebookTest {

	private WebDriver driver;
	private String url;

	@Before
	public void beforeTest() {
		url = "https://www.facebook.com/login";
		System.setProperty("webdriver.chrome.driver", "/Users/segongora/chromedriver");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@After
	public void afterTest() {
		driver.close();
	}

	/*@Test
	public void testLogin() {
		driver.get(url);
		
		WebElement elementUser = driver.findElement(By.id("email"));
		elementUser.sendKeys("segongora9@gmail.com");
		
		WebElement elementPass = driver.findElement(By.id("pass"));
		elementPass.sendKeys("*****");
		elementPass.submit();
		
		WebElement elementSuccess = driver.findElement(By.className("linkWrap"));
		
		String success = elementSuccess.getText();
		assertEquals("Sergio Gongora", success);
		System.out.print("¡Exito! Ha logrado iniciar sesión en Facebook:\n" + success);
		
	}*/
	
	@Test
	public void testLoginDenied() {
		driver.get(url);
		
		WebElement elementUser = driver.findElement(By.id("email"));
		elementUser.sendKeys("segongora9@gmail.com");
		
		WebElement elementPass = driver.findElement(By.id("pass"));
		elementPass.sendKeys("Password1234");
		elementPass.submit();
		
		WebElement elementDenied = driver.findElement(By.xpath("//*[@id=\"globalContainer\"]/div[3]/div/div/div"));
		String denied = elementDenied.getText();
		assertEquals("La contraseña que ingresaste es incorrecta. ¿Olvidaste tu contraseña?", denied);
		System.out.print("¡Incorrecto! La contraseña ingresada es incorrecta:\n" + denied);
	}
	
	@Test
	public void testLoginDenied2() {
		driver.get(url);
		
		WebElement elementUser = driver.findElement(By.id("email"));
		elementUser.sendKeys("segongora_fake@gmail.com");
		
		WebElement elementPass = driver.findElement(By.id("pass"));
		elementPass.sendKeys("Password1234");
		elementPass.submit();
		
		WebElement elementDenied = driver.findElement(By.xpath("//*[@id=\"globalContainer\"]/div[3]/div/div/div"));
		String denied = elementDenied.getText();
		assertEquals("El correo electrónico que ingresaste no coincide con ninguna cuenta. Regístrate para crear una cuenta.", denied);
		System.out.print("¡Incorrecto! El email ingresado es incorrecto:\n" + denied);
	}
}
