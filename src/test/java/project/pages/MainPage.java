package project.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;

public class MainPage extends BasePage {

    private static final String XPATH_OF_USER = "//div[@class='top_profile_name']";
    private static final String ID_OF_LAST_COMMENT = "wpt%s_%s";
    private static final String XPATH_OF_LIKE_BUTTON =
            "//a[contains(@onclick, 'wall%s_%s')]//div[@class='like_button_icon']";
    private static final String ID_OF_SHOW_NEW_BUTTON = "feed_new_posts";
    private static final String XPATH_OF_SHOW_COMMENT = "//a[@class='replies_next  replies_next_main']";

    public MainPage() {
        super(By.xpath(XPATH_OF_USER), "MainPage");
    }

    public boolean isPostTextAndUserRight(String userId, String postId, String expectedText) {
        String actualText = AqualityServices.getElementFactory().getLabel
                (By.id(String.format(ID_OF_LAST_COMMENT, userId, postId)), "Last comment").getText();
        return expectedText.equals(actualText);
    }

    private IButton showNewButton() {
        IButton showNewButton = AqualityServices.getElementFactory()
                .getButton(By.xpath(ID_OF_SHOW_NEW_BUTTON), "show new button");
        return showNewButton;
    }

    public void clickOnShowNewButton(){
        showNewButton().click();
    }

    private IButton showNewCommentButton() {
        IButton showNewCommentButton = AqualityServices.getElementFactory()
                .getButton(By.xpath(XPATH_OF_SHOW_COMMENT), "show new button");
        return showNewCommentButton;
    }

    public void clickOnShowNewCommentButton(){
        showNewCommentButton().click();
    }

    public boolean isPostDeleted(String userId, String postId) {
        return AqualityServices.getElementFactory().getLabel(By.id
                (String.format(ID_OF_LAST_COMMENT, userId, postId)), "Desired comment").state().isExist();
    }

    private IButton likeButton(String userId, String postId) {
        IButton likeButton = AqualityServices.getElementFactory()
                .getButton(By.xpath((String.format(XPATH_OF_LIKE_BUTTON, userId, postId))), "Pushing like button");
        return likeButton;
    }

    public void clickOnLikeButton(String userId, String postId){
        likeButton(userId, postId).click();
    }

}
