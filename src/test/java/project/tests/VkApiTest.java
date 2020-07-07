package project.tests;

import aquality.selenium.browser.AqualityServices;
import framework.utils.ReadPropertyUtil;
import project.utils.VkApiUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import project.pages.LoginPage;
import project.pages.MainPage;

public class VkApiTest extends BaseTest {

    private static final String URI = new ReadPropertyUtil(BaseTest.RESOURCES_PATH, BaseTest.VK_API_PROPERTIES)
            .getProperty("UriApi");
    private static final String IS_LIKED = "1";
    private String postId;
    private String commentId;

    @Test
    @Parameters({"userId", "login", "password", "type", "postBody", "editedPostBody", "commentBody"})
    public void testPostOnTheWallVk(String userId, String login, String password, String type, String postBody,
                                    String editedPostBody, String commentBody)
            throws NumberFormatException {
        AqualityServices.getLogger().info("Step one - open login page and log in");
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page didn't load");
        loginPage.enterLogin(login);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isPageLoaded(), "Main page didn't load");

        AqualityServices.getLogger().info("Step two - make the post");
        postId = VkApiUtils.makePostAndGetPostId(URI, userId, postBody);
        mainPage.clickOnShowNewButton();
        Assert.assertTrue(mainPage.isPostTextAndUserRight(userId, postId, postBody), "Post from user N " + userId +
                " with text - " + postBody + " doesn't exist");

        AqualityServices.getLogger().info("Step three - edit the post");
        VkApiUtils.editPost(URI, userId, postId, editedPostBody);
        Assert.assertTrue(mainPage.isPostTextAndUserRight(userId, postId, editedPostBody), "Post from user N " + userId
                        + " with text - " + editedPostBody + " doesn't exist");

        AqualityServices.getLogger().info("Step four - adding a comment");
        commentId = VkApiUtils.addCommentAndReturnId(URI, userId, postId, commentBody);
        mainPage.clickOnShowNewCommentButton();
        Assert.assertTrue(mainPage.isPostTextAndUserRight(userId, commentId, commentBody), "Comment from user N "
                + userId + " with text - " + commentBody + " doesn't exist");

        AqualityServices.getLogger().info("Step five - putting like");
        mainPage.clickOnLikeButton(userId, postId);
        Assert.assertEquals(VkApiUtils.isLiked(URI, type, userId, postId), IS_LIKED, "Post doesn't liked");

        AqualityServices.getLogger().info("Step six - deleting post");
        VkApiUtils.deletePost(URI, userId, postId);
        Assert.assertFalse(mainPage.isPostDeleted(userId, postId));
    }
}