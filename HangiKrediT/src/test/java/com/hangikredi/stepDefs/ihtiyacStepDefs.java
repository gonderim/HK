package com.hangikredi.stepDefs;

import com.hangikredi.pages.ihtiyacPage;
import com.hangikredi.utilities.BrowserUtils;
import com.hangikredi.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

import static java.lang.Thread.*;


public class ihtiyacStepDefs {

    ihtiyacPage ihtiyacpage = new ihtiyacPage();
    Logger logger = LoggerFactory.getLogger(ihtiyacStepDefs.class);

    SoftAssertions softAssertions = new SoftAssertions();

    @Given("the user clicks on ihtiyacKredi button")
    public void the_user_clicks_on_ihtiyacKredi_button() {
        ihtiyacpage.ihtiyac.click();
        logger.info("User clicked on ihtiyac button");
    }

    @When("the user should display ihtiyac Kredisi on the page")
    public void the_user_should_display_ihtiyac_Kredisi_on_the_page() {
        ihtiyacpage.ihtiyacPbaslik.isDisplayed();
        logger.info("Verified that ihtiyacPBaslik is displayed");
    }

    @When("the user enters kredi tutari")
    public void the_user_enters_kredi_tutari() {
        ihtiyacpage.krediMiktar.sendKeys("10000");
        logger.info("User entered {} krediMiktar", "10000");
    }

    @When("the user selects kredi vadesi")
    public void the_user_selects_kredi_vadesi() {
        Select select = new Select(ihtiyacpage.vadeDropDown);
        select.getFirstSelectedOption();
        logger.info("User selected first option");

    }

    @When("the user clicks Hesapla button")
    public void the_user_clicks_Hesapla_button() {
        ihtiyacpage.hesaplaButon.click();
        logger.info("User clicked on hesapla button");
    }

    @Then("the user should display  TL AY Vadeli ihtiyac Kredileri headline")
    public void the_user_should_display_TL_AY_Vadeli_ihtiyac_Kredileri_headline() throws InterruptedException {
        String expected = "10000 TL 36 Ay Vadeli İhtiyaç Kredisi Faiz Oranları";
        sleep(5000);
        String actual = ihtiyacpage.ihtiyacKarsilatirmaHeadln.getText();
        Assert.assertEquals("HEADLINE IS NOT VERIFIED!", expected, actual);
        logger.info("Verified that Page Headline is --> {}", expected);

    }

    @Then("the user should display Hemen Basvur button")
    public void the_user_should_display_Hemen_Basvur_button() {
        String expected = "Hemen Başvur";
        String actual = ihtiyacpage.hemenBasvurCta.getText();
        Assert.assertEquals("HEMENBASVUR CTA  IS NOT VERIFIED!", expected, actual);

    }

    @Then("the user clicks Hemen Basvur Button")
    public void the_user_clicks_Hemen_Basvur_Button() {
        ihtiyacpage.hemenBasvurCta.click();
        logger.info("User clicked on hemen basvur button");

    }

    @Then("Redirected page should be displayed properly")
    public void redirected_page_should_be_displayed_properly() {
        String currentUrl = Driver.get().getCurrentUrl();
        String expected = "https://www.hangikredi.com/basvuru/ihtiyac/yonlendirme?id=1214&amount=10000&maturity=36&ct=List";
        Assert.assertEquals("URL CAN NOT BE VERIFIED!", expected, currentUrl);
        logger.info("Current URL is verified as --> {}", currentUrl);
    }

    @Then("Pageview-Event items should be present on the page")
    public void pageview_Event_items_should_be_present_on_the_page() {
        String pageSource = Driver.get().getPageSource();


    }

    @When("the user navigates kredi karti page")
    public void the_user_navigates_kredi_karti_page() {
        ihtiyacpage.krediKartlariButton.click();
        logger.info("User clicked on Kredi kartlari button");
    }

    @Then("the user should land on {string}")
    public void the_user_should_land_on(String expectedUrl) {

        String actualUrl = Driver.get().getCurrentUrl();
        Assert.assertEquals("URL is not verified!", expectedUrl, actualUrl);
        logger.info("Current URL is verified as --> {}", actualUrl);
    }

    @When("the user clicks on tum kredi kartlari button")
    public void the_user_clicks_on_tum_kredi_kartlari_button() {
        ihtiyacpage.tumkrediKartlariButton.click();
        logger.info("User clicked on Tum Kredi kartlari button");
    }


    @Then("the user should be able to reach to the page of bank by clicking basvur button, except {string}")
    public void the_user_should_be_able_to_reach_to_the_page_of_bank_by_clicking_basvur_button_except(String name) throws InterruptedException {
        List<WebElement> bankNames = ihtiyacpage.bankNames;
        List<String> bankNamesText = BrowserUtils.getElementsText(bankNames);
        List<WebElement> basvurButtons = ihtiyacpage.basvurButtons;
        for (int i = 0; i < basvurButtons.size(); i++) {
            String bankName = bankNamesText.get(i);
            String cardName = ihtiyacpage.cardNames.get(i).getText();
            if (!bankName.equalsIgnoreCase(name)) {
                WebElement currentButton = basvurButtons.get(i);
                BrowserUtils.clickWithJS(currentButton);
                logger.info("User clicked on basvur button of {} ", bankName);
                String transitionUrl = Driver.get().getCurrentUrl();
                //                String bankNameWithoutSpace = BrowserUtils.removeSpacesInText(bankName);
                String nameWithEngChars = BrowserUtils.convertNonEnglishChars(bankName.toLowerCase());
                String bank = nameWithEngChars.split(" ")[0];



                if (bank.equals("garantibbva")) {
                    if (cardName.equals("Shop&Fly Platinum")) {
                        bank = "garanti";
                        cardName = "shopandfly";
                    } else if (cardName.contains("Bonus")) {
                        bank = "garanti";
                        cardName = "bonus";
                    } else if (cardName.contains("American Express")) {
                        bank = "garanti";
                        cardName = "americanexpress";
                    }
                }

                if (bank.equals("enpara.com")) {
                    bank = "enpara";
                    cardName = "enpara";
                }

                if (bank.equals("isbankasi")) {
                    bank = "is-bankasi";
                    cardName = "isbank";
                }
                if (bank.equals("teb-turkekonomibankasi")) {
                    bank = "teb";
                    cardName = "teb";
                }

                if (bank.equals("denizbank")) {
                    bank = "denizbank";
                    cardName = "denizbank";
                }
                if (bank.equals("cepteteb")) {
                    bank = "cepteteb";
                    cardName = "cepteteb";
                }
                if (bank.equals("yapikredi")) {
                    bank = "yapikredi";
                    cardName = "yapikredi";
                }
                if (bank.equals("qnbfinansbank")) {
                    bank = "qnb";
                    cardName = "cardfinans";
                }

                String srcText = ihtiyacpage.getBankNameWithText(bank).getAttribute("src");
                softAssertions.assertThat(srcText.contains(bank)).as("Bank Name is not verified in src").isTrue();

                //Assert.assertTrue("Bank Name is not verified in src", srcText.contains(bank));
                logger.info("Bank name is verified on redirected page");


                Thread.sleep(3000);
                //  BrowserUtils.waitUntilAttributeContains(ihtiyacpage.form,"action",bank);
                String redirectedUrl = Driver.get().getCurrentUrl();
                softAssertions.assertThat(redirectedUrl.contains(cardName)).as((i + 1) + ". " + bankName + "  is not verified as bank name in redirected URL").isTrue();
                //Assert.assertTrue("Bank Name is not verified in redirected URL", redirectedUrl.contains(cardName));
                logger.info("Bank Name is verified in redirected URL");

                softAssertions.assertThat(redirectedUrl).as("Both URLs are the same!").isNotEqualTo(transitionUrl);
                // Assert.assertNotEquals("Both URLs are the same!",transitionUrl,redirectedUrl);
                logger.info("Verified that Transition and Redirected URLs are different");

                System.out.println((i + 1) + ". Bank : " + bankName + " --> Redirected URL : " + redirectedUrl);
                Driver.get().get("https://www.hangikredi.com/kredi-karti/sorgulama");
                logger.info("User navigated back to previous page");
            }

        }




        softAssertions.assertAll();
        logger.info("All banks are verified for credit card application");
    }


}




