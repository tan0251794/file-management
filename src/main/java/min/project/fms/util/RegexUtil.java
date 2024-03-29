package min.project.fms.util;

import java.util.regex.Pattern;

public class RegexUtil {

    private static final String REGEX_UUID = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$";

    public static boolean isUuid(final CharSequence input) {
        return isMatch(REGEX_UUID, input);
    }
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }
}
