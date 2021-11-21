package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApplicationListPage  extends  PageBase{

    @FindBy (xpath = "//a[@href='applications.php']" )
    public WebElement applicationListLink;

    @FindBy (xpath = "//select[@name='DataTables_Table_0_length']")
    public WebElement showEntriesDropdown;

    @FindBy (xpath = "//input[@type='search']")
    public WebElement searchBar;

    @FindBy ( id = "DataTables_Table_0_next")
    public WebElement nextButton;


}
