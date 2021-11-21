package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends PageBase {

    @FindBy(id = "inputfirstname4")
    public WebElement firstName;

    @FindBy(id = "inputlastname4")
    public WebElement lastName;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "exampleInputPassword1")
    public WebElement password;

    @FindBy(name = "register")
    public WebElement registerButton;


}