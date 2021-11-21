package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmploymentAndIncomePage extends PageBase{

    @FindBy(id = "currentjob1")
    public WebElement currentJob;

    @FindBy(id = "employername1")
    public WebElement employName;

    @FindBy(id = "position1")
    public WebElement position;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "state1")
    public WebElement state;

    @FindBy(id = "start_date1")
    public WebElement startDate;

    @FindBy(id = "grossmonthlyincome")
    public WebElement monthlyGrossIncome;

    @FindBy(id = "monthlyovertime")
    public WebElement monthlyOvertime;

    @FindBy(id = "monthlybonuses")
    public WebElement monthlyBonus;

    @FindBy(id = "monthlycommission")
    public WebElement monthlyCommission;

    @FindBy(id = "monthlydividents")
    public WebElement monthlyDivident;

    @FindBy(xpath = "//a[@href='#next']")
    public WebElement nextButton;
}