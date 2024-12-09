import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class FirstTest extends CoreTestCase {

    @Test
    public void testSearchElement() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("DNS");
        searchPageObject.waitForSearchResult("российская торговая сеть по продаже бытовой техники и электроники");
        searchPageObject.clickSearchResult("российская торговая сеть по продаже бытовой техники и электроники");
    }
}
