package com.bstmexico.tests;

import org.junit.*;
import org.openqa.selenium.*;

public class TestLogin {
	
	protected static final String J_PASSWORD_ERROR = "j_password-error";
	protected static final String J_USERNAME_ERROR = "j_username-error";
	protected static final String password = "123";
	protected static final String user = "tsocial";
	protected static final String J_PASSWORD = "j_password";
	protected static final String J_USERNAME = "j_username";
	protected static final String BTN_LOGIN = "btn-login";
	protected static final String BASE_URL = "http://localhost:8070";

	protected WebDriver driver;

	@Test
	public void testLogin() throws Exception {
		loginComun(user , password);
	}

	@Test
	public void testLoginError() throws Exception {
		loginComun(user, "7787123");
		Assert.assertEquals("Usuario o password incorrecto.", driver.findElement(By.cssSelector("span")).getText());
		
	}

	@Test
	public void testLoginRequeridos() throws Exception {
		loginComun("", "");
		Assert.assertEquals("El nombre de usuario es requerido.", driver.findElement(By.id(J_USERNAME_ERROR)).getText());
		Assert.assertEquals("El password es requerido.", driver.findElement(By.id(J_PASSWORD_ERROR)).getText());
	}

	@Test
	public void testLoginUserRequerido() throws Exception {
		loginComun("", password);
		Assert.assertEquals("El nombre de usuario es requerido.", driver.findElement(By.id(J_USERNAME_ERROR)).getText());
	}
	
	@Test
	public void testLoginRequeridoPass() throws Exception {
		loginComun(user, "");
		Assert.assertEquals("El password es requerido.", driver.findElement(By.id(J_PASSWORD_ERROR)).getText());
	}
	
	@Test
	public void testLoginErrorUser() throws Exception {
		loginComun("jasdjfkasfdkj", password);
		Assert.assertEquals("Usuario o password incorrecto.", driver.findElement(By.cssSelector("span")).getText());
	}
	
	private void loginComun(String usuario, String pas) {
		driver.get(BASE_URL);
		driver.findElement(By.name(J_USERNAME)).clear();
		driver.findElement(By.name(J_USERNAME)).sendKeys(usuario);
		driver.findElement(By.name(J_PASSWORD)).clear();
		driver.findElement(By.name(J_PASSWORD)).sendKeys(pas);
		driver.findElement(By.id(BTN_LOGIN)).click();
	}
}
