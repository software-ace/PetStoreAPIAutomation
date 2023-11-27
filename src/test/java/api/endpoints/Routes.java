package api.endpoints;

public class Routes {
    public static String base_url = "https://petstore.swagger.io/v2";
    // User module
    public static String post_user_url = base_url + "/user";
    public static String get_user_url = base_url + "/user/{username}";
    public static String update_user_url = base_url + "/user/{username}";
    public static String delete_user_url = base_url + "/user/{username}";

    // Pet module

    // Store module
    public static String post_store_url = base_url + "/store/order";
    public static String get_store_url = base_url + "/store/order/{orderId}";
    public static String delete_store_url = base_url + "/store/order/{orderId}";
    public static String getInventory_store_url = base_url + "/store/inventory";
}
