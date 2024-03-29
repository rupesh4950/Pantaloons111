package test_Scripts.PLP_Web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Base_Test;
import generic.StepGroups;
import pom_scripts.web.Home;
import pom_scripts.web.Header.Bag;
import pom_scripts.web.Header.Header;
import pom_scripts.web.Header.PLP;
import pom_scripts.web.Header.Wishlist;
import pom_scripts.web.Header.Plp.PDP;
import pom_scripts.web.Header.Plp.Quick_View;

public class PLP091 extends Base_Test {

	@Test
	public void main() throws Exception {
		Home home = new Home(driver);
		Header header = new Header(driver);
		PLP plp = new PLP(driver);
		js = (JavascriptExecutor) driver;
		PDP pdp = new PDP(driver);
		StepGroups sg = new StepGroups(driver);
		Wishlist wishlist = new Wishlist(driver);
		Quick_View quickView=new Quick_View(driver);
		Bag bag=new Bag(driver);
		WebDriverWait w = new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.elementToBeClickable(home.getIlldothislater()));
		home.getIlldothislater().click();
		boolean b = header.getPantaloons().isDisplayed();
		Assert.assertEquals(b, true);
		// image verifcation completed
		sg.Delete_Multiple_Products_From_Bag();
		//Move mouse pointer on category link in Home page
		action.moveToElement(home.getcategory("WOMEN")).perform();
		//Wait till Sub Catgeory link in Header page is clickable
		wait.until(ExpectedConditions.visibilityOf(header.getSub_Catgeory_Link("Skirts")));
		header.getSub_Catgeory_Link("Skirts").click();
		//Refresh browser window
		driver.navigate().refresh();
		//Wait till FILTER BY text in PLP page is visible
		wait.until(ExpectedConditions.visibilityOf(plp.getFILTER_BY_text()));
		//Scroll page vertically until visibility of FILTER BY text in PLP page
		js.executeScript("arguments[0].scrollIntoView(true);", plp.getFILTER_BY_text());
		//Wait till First Product image in PLP page is visible
		wait.until(ExpectedConditions.visibilityOf(plp.getFirst_Product_Image()));
		//Move mouse pointer on First Product image in PLP page
		action.moveToElement(plp.getFirst_Product_Image()).perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='QUICK VIEW']")));
		wait.until(ExpectedConditions.elementToBeClickable(plp.getQUICK_VIEW_button())).click();
		//Verify if Product image is displayed in Quick View page
		b=quickView.getProduct_image().isDisplayed();
		Assert.assertEquals(b, true);
		//Get Product Details in Quick View
			sg.Get_Product_Details_in_Quick_View();
		//Verify if Available_Size_button in Quick View page is clickable
		WebDriverWait wa=new WebDriverWait(driver,25);
		wa.until(ExpectedConditions.elementToBeClickable(quickView.getAvailable_Size_button()));
		//Click on Available Size button in Quick View page
		quickView.getAvailable_Size_button().click();
		//Verify if ADD_TO_BAG_button is displayed in Quick View page
		b=quickView.getADD_TO_BAG_button().isDisplayed();
		Assert.assertEquals(b, true);
		wa.until(ExpectedConditions.elementToBeClickable(quickView.getADD_TO_BAG_button()));
		action.moveToElement(quickView.getADD_TO_BAG_button()).perform();
		action.click().perform();
		//Wait till GO_TO_BAG_button in Quick View page is clickable
		wait.until(ExpectedConditions.elementToBeClickable(quickView.getGO_TO_BAG_button())).click();
		//Verify if My_Bag_Page_text is displayed in Bag page
		b=bag.getMy_Bag_Page_text().isDisplayed();
		Assert.assertEquals(b, true);
		sg.Get_Product_Details_From_Bag_Page();
		sg.Verify_Quick_View_and_Bag_Products_are_same();
	}
}
