package bg.duosoft.nacidkeycloakservices.model.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupFilter extends BaseFilter {
    private String name;
    private String excludedUserId;
    private String excludedGroupId;
}
