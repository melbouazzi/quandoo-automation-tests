package pages;

import org.openqa.selenium.By;

import static data.Constants.HOVERS_PAGE_URL;

public class HoversPage extends BasePage<HoversPage> {

    final protected By avatarImage = By.xpath("//*[@class='figure']");
    final protected By avatarCaption = By.xpath("//*[@class='figcaption']//h5");


    @Override
    protected String getPageUrl() {
        return HOVERS_PAGE_URL;
    }

    public void moveToFigureByIndex(int index) {
        moveToElementByIndex(avatarImage, index);
    }

    public boolean isCaptionByIndexDisplayed(int index) {
        return findPresentElements(avatarCaption).get(index).isDisplayed();
    }
}