package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends Base {

    // var (Web element)
    @FindBy(name = "username")
    WebElement inputUsername;


    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    // methods (actions)
    public void enterUserName(String username){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        typeText(inputUsername,username);
    }

}
