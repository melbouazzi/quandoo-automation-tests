package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static data.Constants.TABLES_PAGE_URL;
import static java.lang.String.CASE_INSENSITIVE_ORDER;

public class TablesPage extends BasePage<TablesPage> {

    private final By lastNameTableHeader = By.xpath("//*[@id='table2']//span[@class='last-name'] ");
    private final By lastNameTableItems = By.xpath("//*[@id='table2']//td[@class='last-name'] ");


    @Override
    protected String getPageUrl() {
        return TABLES_PAGE_URL;
    }

    public void clickOnLastNameHeader() {
        click(lastNameTableHeader);
    }

    public List<String> getLastNamesListInTable() {
        return findElements(lastNameTableItems).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getSortedLastNamesList() {
        List<String> sortedList = new ArrayList<>(getLastNamesListInTable());
        sortedList.sort(CASE_INSENSITIVE_ORDER);
        return sortedList;
    }

    public List<String> getSortedReversedLastNamesList() {
        List<String> sortedList = new ArrayList<>(getLastNamesListInTable());
        sortedList.sort(Comparator.reverseOrder());
        return sortedList;
    }
}