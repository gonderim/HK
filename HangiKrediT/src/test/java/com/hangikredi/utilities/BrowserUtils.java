package com.hangikredi.utilities;


import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BrowserUtils {


    public static void switchToWindow(String targetTitle) {
        String origin = com.hangikredi.utilities.Driver.get().getWindowHandle();
        for (String handle : com.hangikredi.utilities.Driver.get().getWindowHandles()) {
            com.hangikredi.utilities.Driver.get().switchTo().window(handle);
            if (com.hangikredi.utilities.Driver.get().getTitle().equals(targetTitle)) {
                return;
            }
        }
        com.hangikredi.utilities.Driver.get().switchTo().window(origin);
    }


    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }


    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) com.hangikredi.utilities.Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) com.hangikredi.utilities.Driver.get()).executeScript("arguments[0].click();", element);
    }

    public static void waitUntilAttributeContains(WebElement element, String attibute, String value) {
        // new WebDriverWait(MyDriver.get(), Duration.ofSeconds(5)) //Selenium 4
        new WebDriverWait(Driver.get(), 10)
                .until(ExpectedConditions.attributeContains(element, attibute, value));
    }
    public static String removeSpacesInText(String text){
        String[] split = text.split(" ");
        String str="";
        for (String s : split) {
            str=str+s;
        }
    return str;
    }

    public static String convertNonEnglishChars(String str) {
        String str1 = str.toLowerCase();
        String str2 = "";
        int length = str1.length();
        List<String> nonEnglish = new ArrayList<>(Arrays.asList("ı","ç","Ç","Ü","ü","I","İ","Ş","ş","ğ","Ğ","ö","Ö"));
        for (int i = 0; i < length; i++) {
            String chr = str1.substring(i, i + 1);
            if (nonEnglish.contains(chr)) {
                if (chr.equals("ı")||chr.equals("I")) { chr = "i";}
                if (chr.equals("ü")||chr.equals("Ü")) { chr = "u";}
                if (chr.equals("ç")||chr.equals("Ç")) { chr = "c";}
                if (chr.equals("ş")||chr.equals("Ş")) { chr = "s";}
                if (chr.equals("ğ")||chr.equals("Ğ")) { chr = "g";}
                if (chr.equals("ö")||chr.equals("Ö")) { chr = "o";}
            }
            if (!(chr.equals(" "))) {
                str2 = str2 + chr;
            }
        }
        return str2;
    }

}
