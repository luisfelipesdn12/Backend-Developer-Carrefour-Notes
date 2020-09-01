public class BlankString {
    public static void main(String[] args) {
        String nonBlankString = "Luis";
        String blankString = "              ";
        String otherBlakString = "";
        String oneMoreBlakString = null;

        System.out.println(nonBlankString.isBlank());
        System.out.println(blankString.isBlank());
        System.out.println(otherBlakString.isBlank());
        System.out.println(oneMoreBlakString.isBlank());

        /**
         * OUTPUT IN TERMINAL:
         * false
         * true
         * true
         * NullPointerException
         */
    }
}