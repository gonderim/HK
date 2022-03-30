package com.hangikredi.pages;

import com.hangikredi.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ihtiyacPage extends com.hangikredi.pages.BasePage {

    public ihtiyacPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "// form")
    public WebElement form;

    @FindBy(xpath = "//section[@class='navigation-category']//p[.='İhtiyaç Kredisi']")
    public WebElement ihtiyac;

    @FindBy(xpath = "//p[.='Kredi Kartı']")
    public WebElement krediKartlariButton;

    @FindBy(xpath = "//b[.='Tüm Kredi Kartları']")
    public WebElement tumkrediKartlariButton;

    @FindBy(xpath = "//input[@id='amount']")
    public WebElement krediMiktar;


    @FindBy(xpath = " //select[@id='maturity']")
    public WebElement vadeDropDown;

    @FindBy(xpath = "//a[@id='CalculateCta']")
    public WebElement hesaplaButon;

    @FindBy(xpath = " //h1[contains(.,'İhtiyaç Kredisi')]")
    public WebElement ihtiyacPbaslik;

    @FindBy(css = "h1")
    public WebElement ihtiyacKarsilatirmaHeadln;

    @FindBy(xpath = "//a[contains(.,'Hemen Başvur')]")
    public WebElement hemenBasvurCta;

    @FindBy(xpath = "//a[contains(@id,'creditCardDetailBankName-')]")
    public List<WebElement> bankNames;

    @FindBy(xpath = "//a[contains(@id,'creditCardDetailName-')]")
    public List<WebElement> cardNames;

    @FindBy(xpath = "//a[contains(@id,'creditCardAddToCard-')]")
    public List<WebElement> basvurButtons;

    public WebElement getBankNameWithText(String str){
        return Driver.get().findElement(By.xpath("//img[contains(@src,'"+str+"')]"));
    }




}
