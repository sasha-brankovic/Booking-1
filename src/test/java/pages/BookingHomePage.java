package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingHomePage extends BasePage{
    WebDriver driver;
    WebDriverWait wdWait;
    public BookingHomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wdWait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@data-modal-id, 'language-selection')]")
    WebElement languageIcon;

    String languageXpath = "(//div[contains(text(), '$')])[2]";
    public void selectLanguage(String language){
        click(languageIcon);
        click(driver.findElement(By.xpath(languageXpath.replace("$",language))));
    }
    @FindBy(xpath = "(//div[contains(text(), 'Srpski')])[2]")
    WebElement selectedLanguageIcon;


}
