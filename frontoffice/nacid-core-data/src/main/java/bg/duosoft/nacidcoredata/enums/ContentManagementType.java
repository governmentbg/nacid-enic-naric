package bg.duosoft.nacidcoredata.enums;

import bg.duosoft.nacidcoredata.util.security.SecurityRole;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ContentManagementType {
    Contacts("contacts"),
    Law("law"),
    Sitemap("sitemap"),
    HomePageCategoryControl("homePageCategoryControl"),
    ServiceDefinition("serviceDefinition"),
    AdminConsole("adminConsole");


    ContentManagementType(String code) {
        this.code = code;

    }

    private final String code;


    public static ContentManagementType selectByCode(String code) {
        if (!StringUtils.hasText(code)) {
            return null;
        }

        return Arrays.stream(ContentManagementType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
