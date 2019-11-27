package com.mayab.calidad.funcionales;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTestBlackboard {
	
	private WebDriver driver;
	private String url;
	
	@Before
	public void beforeTest() {
		
		url = "https://anahuac.blackboard.com/webapps/login/";
		System.setProperty("webdriver.chrome.driver", "/Users/segongora/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	@After
	public void afterTest() {
		driver.close();
	}

	//@Test
	public void testSuccess() {
		driver.get(url);
		
		WebElement elementUser = driver.findElement(By.id("user_id"));
		elementUser.sendKeys("00317268");
		
		WebElement elementPass = driver.findElement(By.id("password"));
		elementPass.sendKeys("00317268");
		elementPass.submit();
		
		WebElement elementSuccess = driver.findElement(By.id("anonymous_element_5"));
		String success = elementSuccess.getText();
		assertEquals("Cursos en los que usted es: Alumno", success);
		System.out.print("¡Exito! Ha logrado iniciar sesión en Blackboard:\n" + success);
		
	}
	
	//@Test
	public void testDenied() {
		driver.get(url);
		
		WebElement elementUser = driver.findElement(By.id("user_id"));
		elementUser.sendKeys("00317268a");
		
		WebElement elementPass = driver.findElement(By.id("password"));
		elementPass.sendKeys("00317268");
		elementPass.submit();
		
		WebElement elementDenied = driver.findElement(By.id("loginErrorMessage"));
		String denied = elementDenied.getText();
		assertEquals("El nombre de usuario o contraseña que ha introducido no son correctos. Inténtelo de nuevo. Si aún no puede iniciar sesión, comuníquese con su administrador del sistema.", denied);
		System.out.print("¡Incorrecto! El inicio de sesión no ha sido exitoso.");
	}

	@Test
	public void testBlank()  {
		driver.get(url);
		
		WebElement elementUser = driver.findElement(By.id("user_id"));
		elementUser.sendKeys("");
		
		WebElement elementPass = driver.findElement(By.id("password"));
		elementPass.sendKeys("");
		elementPass.submit();
		
		Alert alert = driver.switchTo().alert();		
		String alertText = driver.switchTo().alert().getText();		
	    System.out.println("Texto de la alerta: " + alertText);	
        
        alert.accept();		
        assertEquals("Introduzca un nombre de usuario y una contraseña.", alertText);
		
		System.out.print("Introduce tus datos");
	}
}
