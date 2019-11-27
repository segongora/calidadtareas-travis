package com.mayab.calidad.funcionales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parcial2 {

	private WebDriver driver;
	private String url;

	@Before
	public void beforeTest() {
		// URL proporcionado por la maestra.
		url = "https://mern-crud.herokuapp.com";
		
		// Indicarle al sistema donde tengo almacenado MI chromedriver. Debe cambiarse el segundo valor.
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		
		// Crear nuevo driver.
		driver = new ChromeDriver();
		
		// Borrar todas las cookies.
		driver.manage().deleteAllCookies();
		
		// Esperar si el driver no ha cargado.
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
	}
	
	@After
	public void afterTest() {
		// Al terminar la prueba, cerrar el driver.
		driver.close();
	}

	// Test para agregar CORRECTAMENTE un usuario.
	@Test
	public void testAggregate() {
		
		// Iniciar driver con URL dada.
		driver.get(url);
		
		// Elemento de boton verde Add New.
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/button"));
		elementBtn.click();
		
		// Elemento de input Name.
		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.sendKeys("Elon Musk");
		
		// Elemento de input Email.
		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.sendKeys("elon@musk.com");
		
		// Elemento de input Age (solo acepta numeros).
		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.sendKeys("43");
		
		// Elemento de lista Gender (despliega una lista con Male, Female, Do Not Disclose).
		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();
		
		// Pre-seleccionamos la opcion de male
		WebElement elementOptionMale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[1]"));
		elementOptionMale.click();
		
		// Elemento boton verde Add.
		WebElement elementAdd = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementAdd.click();
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Elemento de texto que indica que se añadio exitosamente un usuario.
		String correct = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
		
		// Comprobamos que fue exitoso el registro.
		assertEquals("Successfully added!", correct);
		System.out.print(correct + "\nUsuario correctamente añadido.");
		
		// Al finalizar aparecera un nuevo usuario (Elon Musk).
		
	}
	
	// Test para agregar INCORRECTAMENTE un usuario.
	@Test
	public void testAggregateFail() {
		
		// Iniciar driver con URL dada.
		driver.get(url);
		
		// Elemento de boton verde Add New.
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/button"));
		elementBtn.click();
		
		// Elemento de input Name.
		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.sendKeys("Elon Musk");
		
		// Elemento de input Email (damos un email invalido).
		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.sendKeys("elon@musk.2");
		
		// Elemento de input Age (damos letras en vez de numeros).
		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.sendKeys("45");
		
		// Elemento de lista Gender (despliega una lista con Male, Female, Do Not Disclose).
		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();
		
		// Pre-seleccionamos la opcion de male
		WebElement elementOptionMale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[1]"));
		elementOptionMale.click();
		
		// Elemento boton verde Add.
		WebElement elementAdd = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementAdd.click();
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Texto de email incorrecto.
		String incorrecto = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[5]/div/p")).getText();
		
		// Comprobamos el error de registro.
		assertEquals("Email must be valid.", incorrecto);
		System.out.print(incorrecto + "\nUsuario NO agregado correctamente.");
		
		// Al finalizar no se creara un usuario porque dimos email y edad invalidos.
		
	}
	
	// Test para editar CORRECTAMENTE un usuario.
	@Test
	public void testEdit() {
		
		// Funcion para siempre tener un usuario, y no tener error en las pruebas de Edit, Delete
		fallbackUser();
		
		// Iniciar driver con URL dada.
		driver.get(url);
		
		// Elemento de boton azul Edit (debe existir al menos un usuario para que funcione).
		// Se editara el registro mas reciente.
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/table/tbody/tr[1]/td[5]/button[1]"));
		elementBtn.click();
		
		// Esperamos 1.2 segs para que aparezca la informacion previa.
		sleep(1.2);
		
		// Elemento de input Name (lo seleccionamos y borramos todo su interior).
		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.click();
		elementNameInput.clear();
		elementNameInput.sendKeys("Jeff Bezos");
		
		// Elemento de input Email (lo seleccionamos y borramos todo su interior).
		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.click();
		elementEmailInput.clear();
		elementEmailInput.sendKeys("jeff@bezos.com");
		
		// Elemento de input Age (lo seleccionamos y borramos todo su interior).
		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.click();
		elementAge.clear();
		elementAge.sendKeys("54");
		
		// Elemento de lista Gender (despliega una lista con Male, Female, Do Not Disclose).
		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();
		
		// Pre-seleccionamos la opcion de female (para cambiar).
		WebElement elementOptionFemale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[2]"));
		elementOptionFemale.click();
		
		// Elemento boton azul Save (guardamos al usuario editado).
		WebElement elementSave = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementSave.click();
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Elemento de texto que indica que se actualizo exitosamente un usuario.
		String correct = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
		
		// Comprobamos que fue exitoso la actualizacion del registro.
		assertEquals("Successfully updated!", correct);
		System.out.print(correct + "\nUsuario correctamente actualizado.");
		
		// Al finalizar el usuario cambiara de ser Elon Musk a Jeff Bezos.
		
	}
	
	// Test para editar INCORRECTAMENTE un usuario.
	@Test
	public void testEditFail() {
		// Funcion para siempre tener un usuario, y no tener error en las pruebas de Edit, Delete
		fallbackUser();
		
		// Iniciar driver con URL dada.
		driver.get(url);
		
		// Elemento de boton azul Edit (debe existir al menos un usuario para que funcione).
		// Se editara el registro mas reciente
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/table/tbody/tr[1]/td[5]/button[1]"));
		elementBtn.click();
		
		// Esperamos 1.2 segs para que aparezca la informacion previa.
		sleep(1.2);
		
		// Elemento de input Name (lo seleccionamos y borramos todo su interior).
		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.click();
		elementNameInput.clear();
		elementNameInput.sendKeys("Mark Zuckerberg");
		
		// Elemento de input Email (lo seleccionamos y borramos todo su interior).
		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.click();
		elementEmailInput.clear();
		elementEmailInput.sendKeys("markzuckerberg@com.2");
		
		// Elemento de input Age (lo seleccionamos y borramos todo su interior).
		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.click();
		elementAge.clear();
		elementAge.sendKeys("34");
		
		// Elemento de lista Gender (despliega una lista con Male, Female, Do Not Disclose).
		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();
		
		// Pre-seleccionamos la opcion de female (para cambiar).
		WebElement elementOptionFemale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[2]"));
		elementOptionFemale.click();
		
		// Elemento boton azul Save (guardamos al usuario editado).
		WebElement elementSave = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementSave.click();
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Elemento de texto que indica que se actualizo incorrectamente el usuario.
		String incorrect = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[5]/div/p")).getText();
		
		// Comprobemos que fue exitoso la actualizacion erronea.
		assertEquals("Email must be valid.", incorrect);
		System.out.print("Usuario NO actualizado correctamente.");
		// Al finalizar el usuario NO se vera editado, porque le hemos dado un email y una edad invalida.
	}
	
	// Test para borrar CORRECTAMENTE a un usuario.
	@Test
	public void testDelete() {
		
		// Funcion para siempre tener un usuario, y no tener error en las pruebas de Edit, Delete
		fallbackUser();
		
		// Iniciar driver con URL dada.
		driver.get(url);
		
		// Elemento de la tabla
		WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody"));
		WebElement registro = table.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr"));
		WebElement email = registro.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr/td[2]"));
		
		// El email que vamos a borrar
		String emailABorrar = email.getText();
		System.out.print("Email a borrar: " + emailABorrar);
		
		
		// Elemento de boton negro Delete (debe existir al menos un usuario para que funcione).
		// Se borrara el registro mas reciente.
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/table/tbody/tr[1]/td[5]/button[2]"));
		elementBtn.click();
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Elemento de boton rojo Yes para confirmar el borrado.
		WebElement elementConfirm = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]"));
		elementConfirm.click();
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Bool de lo que vamos a borrar.
		boolean deleted = false;
		
		// Si la tabla NO contiene el email borrado, entonces se borro.
		if (!table.getText().contains(emailABorrar)) {
			
			// Cambiamos el bool a true.
			deleted = true;
			
			// Imprimimos lo que sucede.
			System.out.print("\nUsuario borrado correctamente.");
		}
		
		// Hacemos un assertTrue con el bool creado.
		assertTrue(deleted);
	
		
		// Al finalizar el usuario deberá dejar de existir.
	}
	
	// Test para borrar INCORRECTAMENTE a un usuario.
	@Test
	public void testDeleteFail() {
		
		// Funcion para siempre tener un usuario, y no tener error en las pruebas de Edit, Delete
		fallbackUser();
		
		// Iniciar driver con URL dada.
		driver.get(url);
		
		// Elemento de la tabla
		WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody"));
		WebElement registro = table.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr"));
		WebElement email = registro.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr/td[2]"));
		// El email que se va a borrar
		String emailABorrar = email.getText();
		System.out.print("Email a borrar: " + emailABorrar);
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Elemento de boton negro Delete (debe existir al menos un usuario para que funcione).
		// Se borrara el registro mas reciente.
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/table/tbody/tr[1]/td[5]/button[2]"));
		elementBtn.click();
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Elemento de boton negro NO para no realizar el borrado.
		WebElement elementConfirm = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[2]"));
		elementConfirm.click();
		
		// bool cuando NO esta eliminado.
		boolean notDeleted = false;
		
		// Si la tabla contiene el email borrado, entonces no se borro.
		if(table.getText().contains(emailABorrar)) {
			
			// Cambiamos el bool NO esta eliminado, se cambia a true.
			notDeleted = true;
			
			// Imprimimos que no se borro.
			System.out.print("\nUsuario NO borrado.");
		}
		
		// Hacemos un assertTrue con el bool creado.
		assertTrue(notDeleted);
		
		
		// Al finalizar el usuario seguira existiendo
	}
	
	public void fallbackUser() {
		
		// Añadir un usuario siempre, para nunca dar error.
		driver.get(url);
		
		// Elemento de boton verde Add New.
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/button"));
		elementBtn.click();
		
		// Elemento de input Name.
		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.sendKeys("Elon Musk Fallback");
		
		// Elemento de input Email.
		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.sendKeys("elon-fallback@musk.com");
		
		// Elemento de input Age (solo acepta numeros).
		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.sendKeys("43");
		
		// Elemento de lista Gender (despliega una lista con Male, Female, Do Not Disclose).
		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();
		
		// Pre-seleccionamos la opcion de male
		WebElement elementOptionMale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[1]"));
		elementOptionMale.click();
		
		// Elemento boton verde Add.
		WebElement elementAdd = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementAdd.click();
		
		WebElement closeBtn = driver.findElement(By.xpath("/html/body/div[2]/div/i"));
		closeBtn.click();
	}
	
	// Funcion para detener el selenium y ver lo que esta sucediendo.
	public void sleep(double d) {
		
		try {
	        // Tiempo que va a dormir las funciones en milisegundos
	        Thread.sleep((long) (d * 1000));
	    } catch (InterruptedException ex) {}
	    
	}
	
	
}

