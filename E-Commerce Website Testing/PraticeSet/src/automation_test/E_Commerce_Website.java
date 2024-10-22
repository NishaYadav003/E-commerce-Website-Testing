package automation_test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class E_Commerce_Website {

	public static void main(String[] args) throws InterruptedException {
		// Set up the WebDriver
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		// Open the e-commerce website
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

		// List of products to add to the cart
		List<String> names = Arrays.asList("Brocolli", "Cucumber", "Cauliflower", "Tomato", "Pumpkin");

		// Locate all product names
		List<WebElement> productNames = driver.findElements(By.xpath("//h4[@class='product-name']"));

		for (int i = 0; i < productNames.size(); i++) {
			// Get the product name and trim any extra text (like weight)
			String productName = productNames.get(i).getText().split("-")[0].trim();

			// If the product is in the list, click the "Add to Cart" button
			if (names.contains(productName)) {
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				Thread.sleep(2000); // Sleep to allow for the button click to process
			}
		}

		// Proceed to the cart
		driver.findElement(By.xpath("//a/img[@alt='Cart']")).click();
		driver.findElement(By.cssSelector(".action-block")).click();

		// Place the order
		driver.findElement(By.xpath("//button[contains(.,'Place Order')]")).click();

		// Select the country "India" from the drop-down
		WebElement element = driver.findElement(By.tagName("select"));
		Select s = new Select(element);
		s.selectByVisibleText("India");

		// Agree to the terms and conditions
		driver.findElement(By.tagName("input")).click();

		// Click the "Proceed" button
		driver.findElement(By.tagName("button")).click();

		// Close the browser
		driver.quit();
	}
}
