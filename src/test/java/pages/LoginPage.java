package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {


    @FindBy(id = "exampleInputEmail1")
    public WebElement usernameField;

    @FindBy(id = "exampleInputPassword1")
    public WebElement passwordField;

    @FindBy(name = "login")
    public WebElement loginButton;

    @FindBy(xpath = "//a[@href='register.php']")
    public WebElement signUpLink;

    @FindBy(xpath = "//a[@href='mortagage.php']")
    public WebElement mortgageApplicationMenu;


    public void login(String username, String pass) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(pass);
        loginButton.click();
    }


}