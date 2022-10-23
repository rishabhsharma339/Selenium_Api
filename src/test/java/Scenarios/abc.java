package Scenarios;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class abc {
	static WebDriver driver;

	public static String UniqueNumber(int length) {
		String UniqueNumber = RandomStringUtils.randomNumeric(length);
		return UniqueNumber;
	}

	public static String[] get_user() {
		// Parsing the response
		Response response = (Response) RestAssured.given().contentType("application/json").when()
				.get("https://randomuser.me/api/?results=1");
		System.out.println(response.prettyPrint());
		String first_name = response.jsonPath().getString("results.name.first");
		String last_name = response.jsonPath().getString("results.name.last");
		String street_number = response.jsonPath().getString("results.location.street.number");
		String street_name = response.jsonPath().getString("results.location.street.name");
		String city = response.jsonPath().getString("results.location.city");
		String state = response.jsonPath().getString("results.location.state");
		String country = response.jsonPath().getString("results.location.country");
		String postcode = response.jsonPath().getString("results.location.postcode");
		String email = response.jsonPath().getString("results.email");
		String phone = response.jsonPath().getString("results.cell");
		first_name = first_name.replaceAll("\\p{P}", "");
		last_name = last_name.replaceAll("\\p{P}", "");
		street_number = street_number.replaceAll("\\p{P}", "");
		street_name = street_name.replaceAll("\\p{P}", "");
		city = city.replaceAll("\\p{P}", "");
		state = state.replaceAll("\\p{P}", "");
		country = country.replaceAll("\\p{P}", "");
		postcode = postcode.replaceAll("\\p{P}", "");
		email = email.replaceAll("[\\[\\](){}]", "");
		phone = phone.replaceAll("\\p{P}", "");
		System.out.println(first_name);
		System.out.println(last_name);
		System.out.println(street_number);
		System.out.println(street_name);
		System.out.println(city);
		System.out.println(state);
		System.out.println(country);
		System.out.println(postcode);
		System.out.println(email);
		System.out.println(phone);
		return new String[] { first_name, last_name, street_number, street_name, city, state, country, postcode, email,
				phone };
	}

	@Test
	public static void verify_purchase() throws InterruptedException {
		String[] get_detail = get_user();
		String firstname = get_detail[0].toString() + UniqueNumber(5);
		String last_name = get_detail[1].toString() + UniqueNumber(5);
		String street_number = get_detail[2].toString();
		String street_name = get_detail[3].toString();
		String city = get_detail[4].toString();
		String state = get_detail[5].toString();
		String country = get_detail[6].toString();
		String postcode = get_detail[7].toString();
		String email = firstname + last_name;
		email = email.replaceAll("\\p{P}", "");
		email = email + "@example.com";
		String phone = get_detail[9].toString();
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://magento.softwaretestingboard.com/");
		System.out.println("navigated to site");
		Thread.sleep(10000);
		driver.findElement(By
				.xpath("//li/a[@href='https://magento.softwaretestingboard.com/customer/account/create/' and text()='Create an Account']"))
				.click();
		System.out.println("click on create new customer");
		Thread.sleep(5000);
		Assert.assertTrue(
				driver.findElement(By.xpath("//h1/span[text()='Create New Customer Account']")).isDisplayed());
		System.out.println("verified Heading text: Create New Customer Account");
		driver.findElement(By.xpath("//*[@id='firstname']")).sendKeys(firstname);
		System.out.println("enter first name: " + firstname);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='lastname']")).sendKeys(last_name);
		System.out.println("enter last name: " + last_name);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='email_address']")).sendKeys(email);
		System.out.println("enter email: " + email);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("RSssdf@1234");
		System.out.println("enter password: " + "RSssdf@1234");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='password-confirmation']")).sendKeys("RSssdf@1234");
		System.out.println("enter confirmed password: " + "RSssdf@1234");
		Thread.sleep(7000);
		driver.findElement(By.xpath("//button/span[text()='Create an Account']")).click();
		System.out.println("click on create new account ");
		Thread.sleep(7000);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"))
						.isDisplayed());
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Men']")).click();
		System.out.println("click on Men drop down");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[text()='Tops']")).click();
		System.out.println("click on tops");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@class='product-item-link' and contains(text(),'Cassius Sparring Tank')]"))
				.click();
		System.out.println("click on item");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='option-label-size-143-item-170' and text()='XL']")).click();
		System.out.println("select item size: XL");
		Thread.sleep(5000);
		String text = driver
				.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper' and text()='Cassius Sparring Tank']"))
				.getText();
		System.out.println("get item Text");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='option-label-color-93-item-50' and @option-label='Blue']")).click();
		System.out.println("select item color: Blue");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='product-addtocart-button']")).click();
		System.out.println("click on add to cart button");
		Thread.sleep(5000);
		driver.findElement(By
				.xpath("//a[@href='https://magento.softwaretestingboard.com/checkout/cart/' and @class='action showcart']"))
				.click();
		System.out.println("click on show carrt button");
		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + text + "']")).isDisplayed());
		System.out.println("verified item text on show cart");
		driver.findElement(By.xpath("//button[@id='top-cart-btn-checkout' and text()='Proceed to Checkout']")).click();
		System.out.println("click on proceed to check out button");
		Thread.sleep(20000);
		driver.findElement(By.xpath("//input[@type='text' and @name='street[0]']"))
				.sendKeys(street_number + " " + street_name);
		System.out.println("enter street name: " + street_name);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@type='text' and @name='city']")).sendKeys(city);
		System.out.println("enter city: " + city);
		Thread.sleep(5000);
		Select obj_country = new Select(driver.findElement(By.xpath("//*[@name='country_id']")));
		obj_country.selectByVisibleText(country);
		System.out.println("select country: " + country);
		Thread.sleep(5000);
		if (driver
				.findElements(
						By.xpath("//*[contains(@name,'region') and @aria-invalid='false' and @aria-required='true']"))
				.size() > 0) {
			Select obj_state = new Select(driver.findElement(By.xpath("//*[@name='region_id']")));
			obj_state.selectByVisibleText(state);
			System.out.println("select state: " + state);
			Thread.sleep(5000);
		} else {
			driver.findElement(By.xpath("//*[@name='region']")).sendKeys(state);
			System.out.println("enter state: " + state);
			Thread.sleep(5000);
		}
		driver.findElement(By.xpath("//*[@name='postcode']")).sendKeys(postcode);
		System.out.println("enter postal code: " + postcode);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@name='telephone']")).sendKeys(phone);
		System.out.println("enter phone: " + phone);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@value='flatrate_flatrate']")).click();
		System.out.println("click on flat rate");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[text()='Next']")).click();
		System.out.println("click on next buton");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[text()='Place Order']")).click();
		System.out.println("click on place order");
		Thread.sleep(10000);
		Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Thank you for your purchase!']")).isDisplayed());
		System.out.println("verified text: for purcahse");
		String order_number = driver.findElement(By.xpath("//*[@class='order-number']/strong")).getText();
		System.out.println("order_number for order is: " + order_number);
		driver.findElement(By.xpath("//*[@class='order-number']/strong")).click();
		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Order # " + order_number + "']")).isDisplayed());

	}
}
