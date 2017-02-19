package appiumDemo;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class AppiumTest {
	DesiredCapabilities capacidade;
	AndroidDriver driver;

	@Before
	public void inicializar() {
		//definindo caminho para o aplicativo a ser testado.
		File dirAplicativo = new File("c:\\TrianguloApp");
		File arqAplicativo = new File(dirAplicativo, "TrianguloApp.apk");
		capacidade = new DesiredCapabilities();
		//definindo a plataforma que será testada.
		capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		//definindo o dispositivo que será testado.
		//capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, "0018903858");
		capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Simulator");
		//definindo o aplicativo a ser testado.
		capacidade.setCapability(MobileCapabilityType.APP, arqAplicativo.getAbsolutePath());

	}

	
	public void cenarioPositivo() throws Exception {
		//abrindo conexão com servidor.
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidade);
		//inserindo o valor 3 no campo Lado 1.
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txt_lado1")).sendKeys("3");
	
		//inserindo o valor 3 no campo Lado 2.
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txt_lado2")).sendKeys("3");
		//inserindo o valor 3 no campo Lado 3.
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txt_lado3")).sendKeys("3");
		//clicando no botão calcular.
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/btn_Calcular")).click();
		//validando que mensagem "O triângulo é Equilátero" será exibida.
		assertEquals("O triângulo é Equilátero",
				driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txt_triangulo")).getText());
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //Taking screenshot
		FileUtils.copyFile(scrFile, new File("c:\\Temp\\screenshotGK.png"));
	}

	@Test
	public void cenarioNegativo() throws Exception {
		//abrindo conexão com servidor.
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidade);
		//inserindo o valor 2 no campo Lado 1.
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txt_lado1")).sendKeys("2");
		//inserindo o valor 1 no campo Lado 2.
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txt_lado2")).sendKeys("1");
		//inserindo o valor 2 no campo Lado 3.
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/txt_lado3")).sendKeys("2");
		//clicando no botão calcular.
		driver.findElement(By.id("com.eliasnogueira.trianguloapp:id/btn_Calcular")).click();
		
		//validando que mensagem "O triângulo é Equilátero" não será exibida.
		boolean resultado = driver.getPageSource().contains("O triângulo é Equilátero");
		assertFalse(resultado);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //Taking screenshot
		FileUtils.copyFile(scrFile, new File("c:\\Temp\\cenarioNegativo.png"));

	}

	@After
	public void finalizar() {
		driver.quit();
	}

}
