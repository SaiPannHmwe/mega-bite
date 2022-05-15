package mm.com.mtp.utils;

/**
 * Created by Sai Pann Hmwe on 05/01/2021.
 */
public interface PageUrl {

    // ---------- Login ----------
    String INDEX = "index";
    String LOGIN = "login/login";
    String DASHBOARD = "dashboard";
    String CHANGE_PASSWORD = "change-password";
    String STAFF_CHANGE_PASSWORD = "office/staff-change-password";

    //-----Admin-----
    //String ADMIN = "Admin/account";
    String ADMIN = "change-password";

    String EDIT_ACCOUNT = "Admin/edit-account";

    String DELIVERY_ACCOUNT = "Admin/delivery-account";
    String VIEW_DELIVERY_ACCOUNT = "Admin/view-delivery-account";
    String EDIT_DELIVERY_ACCOUNT = "Admin/edit-delivery-account";

    String OFFICE_ACCOUNT = "Admin/office-account";
    String VIEW_OFFICE_ACCOUNT = "Admin/view-office-account";
    String EDIT_OFFICE_ACCOUNT = "Admin/edit-office-account";

    //-------Shop-------
    String SHOP = "Shop/shop";
    String VIEW_SHOP = "Shop/view-shop";
    String NEW_SHOP = "Shop/new-shop";
    String NEW_POPULAR_SHOP = "Shop/new-popular-shop";

    //-------Shop Menu-------
    String SHOP_MENU = "ShopMenu/shop-menu";
    String NEW_SHOP_MENU = "ShopMenu/new-shop-menu";
    String VIEW_SHOP_MENU = "ShopMenu/view-shop-menu";

    //-------Menu-------
    String MENU = "Menu/menu";
    String VIEW_MENU = "Menu/view-menu";
    String NEW_MENU = "Menu/new-menu";

    //-------Order-------
    String ORDER = "Order/order";
    String NEW_ORDER = "Order/new-order";
    String VIEW_ORDER = "Order/view-order";

    //-------Version-------
    String VERSION = "Admin/version";
    String VIEW_VERSION = "Admin/version/view-version";

    // ---------- Error ----------
    String ERROR = "error";

}
