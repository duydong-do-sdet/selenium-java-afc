package javaDemo;

public class J_01_String_Format {

    public static void main(String[] args) {
        String dynamicLocator = "//id[@name='%s' and @email='%s']";

        System.out.println(getXPathLocator(dynamicLocator, "Dong", "dongafc@gmail.com"));
    }

    public static String getXPathLocator(String xpathLocator, String... dynamicValues) {
        return String.format(xpathLocator, (Object[]) dynamicValues);
    }

}
