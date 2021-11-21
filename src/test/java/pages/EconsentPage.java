package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EconsentPage extends PageBase {

    @FindBy(id="eConsentdeclarerFirstName")
    public WebElement firsName;
    @FindBy(id="eConsentdeclarerLastName")
    public WebElement lastName;
    @FindBy(id="eConsentdeclarerEmail")
    public WebElement email;
    @FindBy(xpath="//div[@class='custom-control custom-radio'][input[@id='agree']]")
    public WebElement agreeButton;
    @FindBy(xpath = "//div[@class='custom-control custom-radio'][input[@id='dontagree']]")
    public WebElement dontAgreeButton;
    @FindBy(xpath = "//a[@href='#next']")
    public WebElement nextButton;
}