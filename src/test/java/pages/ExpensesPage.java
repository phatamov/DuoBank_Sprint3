package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExpensesPage extends PageBase {

    @FindBy(xpath = "//*[@for='expense1']")
    public WebElement rentChekBox;

    @FindBy(xpath = "//*[@for='expense2']")
    public WebElement ownChekBox;

    @FindBy(xpath="//input[@name='monthly_rental_payment']")
    public WebElement monthlyRentalPayment;

    @FindBy(xpath = "//input[@id='firtmortagagetotalpayment']")
    public WebElement firstMortgageTotalPayment;

    @FindBy(xpath = "//a[@href='#next']")
    public WebElement nextButton;




}

