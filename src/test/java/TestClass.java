import data.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HoversPage;
import pages.LoginPage;
import pages.TablesPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestClass extends BaseTest {

    private String INVALID_USERNAME_MESSAGE = "Your username is invalid";
    private String INVALID_PASSWORD_MESSAGE = "Your password is invalid";
    private String SUCCESS_MESSAGE = "You logged into a secure area";

    private static final User VALID_USER = User.builder()
            .username("tomsmith")
            .password("SuperSecretPassword!")
            .build();

    private static final User INVALID_USERNAME_USER = User.builder()
            .username("abcdef")
            .password("sokofomo!")
            .build();

    private static final User INVALID_PASSWORD_USER = User.builder()
            .username("tomsmith")
            .password("sokofomo!")
            .build();


    @Parameters(value = {"browser"})
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String browser) {
        getDriver(browser);
    }

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();

        loginPage
                .openAndLogin(VALID_USER);

        assertThat("Success login message is displayed", loginPage.isSuccessMessageDisplayed(), is(true));
        assertThat("Success message is incorrect", loginPage.getSuccessMessage(), is(containsString(SUCCESS_MESSAGE)));
    }

    @Test
    public void testFailedLoginWithWrongUsername() {
        LoginPage loginPage = new LoginPage();

        loginPage
                .openAndLogin(INVALID_USERNAME_USER);

        assertThat("Error message is displayed", loginPage.isErrorMessageDisplayed(), is(true));
        assertThat("Error message is incorrect", loginPage.getErrorMessage(), is(containsString(INVALID_USERNAME_MESSAGE)));
    }

    @Test
    public void testFailedLoginWithWrongPassword() {
        LoginPage loginPage = new LoginPage();

        loginPage
                .openAndLogin(INVALID_PASSWORD_USER);

        assertThat("Error message is displayed", loginPage.isErrorMessageDisplayed(), is(true));
        assertThat("Error message is incorrect", loginPage.getErrorMessage(), is(containsString(INVALID_PASSWORD_MESSAGE)));
    }

    @Test
    public void testHovers() {
        HoversPage hoversPage = new HoversPage();

        hoversPage
                .open()
                .moveToFigureByIndex(2);

        assertThat("Figure caption is not displayed.", hoversPage.isCaptionByIndexDisplayed(2), is(true));
    }

    @Test
    public void testTableItemsOrder() {
        TablesPage tablesPage = new TablesPage();

        tablesPage
                .open()
                .clickOnLastNameHeader();

        assertThat("Table is not sorted in and descending order", tablesPage.getLastNamesListInTable(),
                is(tablesPage.getSortedLastNamesList()));

        tablesPage
                .clickOnLastNameHeader();

        assertThat("Table is not sorted in an ascending order", tablesPage.getLastNamesListInTable(),
                is(tablesPage.getSortedReversedLastNamesList()));
    }
}