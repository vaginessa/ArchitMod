package com.whatsapp.architjn;

import android.graphics.Color;

/**
 * Created by architjn on 22/01/15.
 */
public class ColorStore {

    private static String ConversationsBackgroundColor = "#ffffff";
    private static String ContactPickerBackgorundColor = "#ffffff";
    private static String ActionBarColor = "#214545";
    private static String StatusBarColor = "#1d3838";
    private static String FabColorNormal = "#4db6ac";
    private static String FabColorPressed = "#4db6ac";
    private static String NavigationBarColor = "#000000";
    private static String FabBackgoundColor = "#1Affffff";
    private static String ChatBubbleLeftColor = "#ffffff";
    private static String ChatBubbleRightColor = "#d8f8c6";
    private static String UniversalBackgroundColor = "#ffffff";
    private static String UniversalStatColor = "#1d3838";
    private static String UniversalNavigationBarColor = "#000000";
    private static String UniversalActionBarColor = "#214545";

    public static int getConsBackColor() {
        return Color.parseColor(ConversationsBackgroundColor);
    }

    public static int getConPickBackColor() {
        return Color.parseColor(ContactPickerBackgorundColor);
    }

    public static int getActionBarColor() {
        return Color.parseColor(ActionBarColor);
    }

    public static int getStatusBarColor() {
        return Color.parseColor(StatusBarColor);
    }

    public static int getFabColorNormal() {
        return Color.parseColor(FabColorNormal);
    }

    public static int getFabColorPressed() {
        return Color.parseColor(FabColorPressed);
    }

    public static int getNavigationBarColor() {
        return Color.parseColor(NavigationBarColor);
    }

    public static int getFabBgColor() {
        return Color.parseColor(FabBackgoundColor);
    }

    public static int getChatBubbleLeftColor() {
        return Color.parseColor(ChatBubbleLeftColor);
    }

    public static int getChatBubbleRightColor() {
        return Color.parseColor(ChatBubbleRightColor);
    }

    public static int getChatBubbleRightTextColor() {
        return Color.parseColor("#000000");
    }

    public static int getChatBubbleLeftTextColor() {
        return Color.parseColor("#ffffff");
    }

    public static int getUniBackColor() {
        return Color.parseColor(UniversalBackgroundColor);
    }

    public static int getUniStatColor() {
        return Color.parseColor(UniversalStatColor);
    }

    public static int getUniNavColor() {
        return Color.parseColor(UniversalNavigationBarColor);
    }

    public static int getUniActionColor() {
        return Color.parseColor(UniversalActionBarColor);
    }

}
