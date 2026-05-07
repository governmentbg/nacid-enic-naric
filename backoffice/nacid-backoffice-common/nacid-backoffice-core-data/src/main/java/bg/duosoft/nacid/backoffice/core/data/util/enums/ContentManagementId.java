package bg.duosoft.nacid.backoffice.core.data.util.enums;

import bg.duosoft.nacid.backoffice.core.data.util.security.SecurityRole;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ContentManagementId {
    Sitemap("sitemap", null, List.of(SecurityRole.SITEMAP_ACCESS));

    ContentManagementId(String code) {
        this.code = code;
    }

    ContentManagementId(String code, List<String> accessRolesOnView, List<String> accessRolesOnEdit) {
        this.code = code;
        this.accessRolesOnView = accessRolesOnView;
        this.accessRolesOnEdit = accessRolesOnEdit;
    }

    private final String code;
    private List<String> accessRolesOnView;

    private List<String> accessRolesOnEdit;

    public static ContentManagementId selectByCode(String code) {
        if (!StringUtils.hasText(code)) {
            return null;
        }

        return Arrays.stream(ContentManagementId.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
