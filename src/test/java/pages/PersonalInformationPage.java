package pages;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalInformationPage extends PageBase {

    @FindBy(xpath = "//*[@id='coborrower2']")
    public WebElement coBorrowerNoCheckBox;

    @FindBy(xpath = "//input[@name='b_firstName']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='b_middleName']")
    public WebElement middleName;

    @FindBy(xpath = "//input[@name='b_lastName']")
    public WebElement lastName;

    @FindBy(xpath = "//select[@id='b_suffix']")
    public WebElement suffixDropDownList;

    @FindBy(xpath = "//input[@name='b_email']")
    public WebElement email;

    @FindBy(xpath = "//input[@id='b_dob']")
    public WebElement dateOfBirth;

    @FindBy(xpath = "//input[@id='b_ssn']")
    public WebElement ssn;

    @FindBy(xpath = "//select[@id='b_marital']")
    public WebElement martialStatus;

    @FindBy(xpath = "//input[@id='b_cell']")
    public WebElement cellPhone;

    @FindBy(xpath = "//input[@id='b_home']")
    public WebElement homePhone;

    @FindBy(xpath = "//a[@href='#next']")
    public WebElement nextButton;

    @FindBy(xpath = "//*[@id='privacypolicy']")
    public WebElement privacyPolicyCheckBox;



}