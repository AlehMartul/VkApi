package framework.utils;

import aquality.selenium.browser.AqualityServices;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class VkApiUtils {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String VK_API_PROPERTIES = "config.properties";
    private static final String ACCESS_TOKEN = new ReadPropertyUtil(VkApiUtils.RESOURCES_PATH,
            VkApiUtils.VK_API_PROPERTIES).getProperty("accessToken");
    private static final String VERSION = new ReadPropertyUtil(VkApiUtils.RESOURCES_PATH, VkApiUtils.VK_API_PROPERTIES)
            .getProperty("version");

    public static String isLiked(String uri, String type, String ownerID, String postId) {
        String uri1 = uri + "likes.isLiked?" + type + "&user_ids=" + ownerID + "&item_id=" + postId + "&access_token="
                + ACCESS_TOKEN + "&v=" + VERSION;
        String json = ApiUtil.get(uri1).getBody().asString().replaceAll("\\D+", "");
        String ch = json.substring(0, 1);
        return ch;
    }

    public static String addCommentAndReturnId(String uri, String ownerID, String postId, String postBody) {
        String uri1 = uri + "/wall.createComment?user_ids=" + ownerID + "&post_id=" + postId + "&message="
                + postBody + "&access_token=" + ACCESS_TOKEN + "&v=" + VERSION;
        Response response = ApiUtil.post(uri1);
        String commentId = response.getBody().asString().replaceAll("\\D+", "");
        return commentId;
    }

    public static void editPost(String uri, String ownerID, String postId, String postBody) {
        String uri1 = uri + "/wall.edit?user_ids=" + ownerID + "&post_id=" + postId + "&message="
                + postBody + "&access_token=" + ACCESS_TOKEN + "&v=" + VERSION;
        ApiUtil.post(uri1);
    }

    public static String deletePost(String uri, String ownerID, String postId) {
        String uri1 = uri + "wall.delete?owner_id=" + ownerID + "&post_id=" + postId + "&access_token="
                + ACCESS_TOKEN + "&v=" + VERSION;
        AqualityServices.getLogger().info("Deleting post N" + postId);
        Response response = RestAssured.given().contentType(ContentType.JSON).post(uri1);
        AqualityServices.getLogger().info("Getting response");
        String result = response.getBody().asString().replaceAll("\\D+", "");
        return result;
    }

    public static Response getAddressForPostPhoto(String uri, String ownerID) {
        String uri1 = uri + "/photos.getWallUploadServer?uid=" + ownerID + "&access_token=" + ACCESS_TOKEN + "&v="
                + VERSION;
        Response response = ApiUtil.get(uri1);
        System.out.println(response.getBody().asString());
        return response;
    }

    public static String makePostAndGetPostId(String uri, String ownerID, String postBody) {
        String uri1 = uri + "/wall.post?user_ids=" + ownerID + "&message=" + postBody + "&access_token="
                + ACCESS_TOKEN + "&v=" + VERSION;
        Response response = ApiUtil.post(uri1);
        String postId = response.getBody().asString().replaceAll("\\D+", "");
        return postId;
    }
}
