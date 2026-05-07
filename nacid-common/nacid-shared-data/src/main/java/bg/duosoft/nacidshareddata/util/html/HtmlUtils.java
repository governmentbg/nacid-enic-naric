package bg.duosoft.nacidshareddata.util.html;

import org.springframework.util.StringUtils;


public class HtmlUtils {

    public static String transformHtmlToSortPatternText(String htmlText, Integer substringLength) {
        if (StringUtils.hasText(htmlText)) {
            String text = htmlText.replaceAll("\\<[^>]*>", "").replaceAll("&nbsp;","").replaceAll("\n", "").replaceAll(" ", "").trim();
            return text.length() > substringLength ? text.substring(0, substringLength) : text;
        }

        return null;
    }
}
