package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreApprovalDetaisPage extends PageBase {

    @FindBy(xpath = "//*[@id='realtor1']")
    public WebElement checkBoxrealtor1;

    @FindBy(xpath = "//*[@id='realtor2']")
    public WebElement checkBoxrealtor2;

    @FindBy(id = "realtorinfo")
    public WebElement realtorInfo;

    @FindBy(xpath = "//*[@id='loanofficer1']")
    public WebElement checkBoxLoanOfficer1;

    @FindBy(xpath = "//*[@id='loanofficer2']")
    public WebElement checkBoxLoanOfficer2;

    @FindBy(id = "select2-purpose_loan-ng-container")
    public WebElement purposeOfLoan;

    @FindBy(id = "estimatedprice")
    public WebElement estimatedPrice;

    @FindBy(id = "downpayment")
    public WebElement downPayment;

    @FindBy(name = "down_payment_percent")
    public WebElement downPaymentPercent;

    @FindBy(xpath = "//*[@id='steps-uid-0-p-0']/div[5]/div/span")
    public WebElement loanAmount;

    @FindBy(xpath = "//*[@id='select2-src_down_payment-ii-container']")
    public WebElement downPaymentSource;

    @FindBy(id = "select2-src_down_payment-8f-container")
    public WebElement additionalFunds;

    @FindBy(xpath = "//*[@id='steps-uid-0']/div[3]/ul/li[2]/a")
    public WebElement next;

    @FindBy(xpath = "//*[@id='realtorinfo-error']")
    public WebElement realtorRequired;

    @FindBy(xpath = "//*[@id='estimatedprice-error']")
    public WebElement estimatedPriceRequired;

    @FindBy(xpath = "//*[@id='downpayment-error']")
    public WebElement downPaymentRequired;

    @FindBy(xpath = "//*[@id='downpaymentpercentage-error']")
    public WebElement downPaymentPercentRequired;


}