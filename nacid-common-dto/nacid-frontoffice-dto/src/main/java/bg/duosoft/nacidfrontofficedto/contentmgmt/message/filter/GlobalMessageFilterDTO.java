package bg.duosoft.nacidfrontofficedto.contentmgmt.message.filter;

import bg.duosoft.nacidfrontofficedto.Pageable;
import bg.duosoft.nacidfrontofficedto.Sortable;
import bg.duosoft.nacidfrontofficedto.utils.constants.GlobalMessageSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalMessageFilterDTO implements Sortable, Pageable {
    private String order = this.DESC_ORDER;
    private String orderBy = GlobalMessageSortFields.DATE_CREATED;
    private Integer page = this.DEFAULT_PAGE;
    private Integer pageSize = this.DEFAULT_PAGE_SIZE;
    private String name;
    private String nameEn;
    private String text;
    private String textEn;
    private Date createdDateFrom;
    private Date createdDateTo;
    private Date lastUpdateDateFrom;
    private Date lastUpdateDateTo;
    private String globalMessageType;
    private Boolean enabled;
}
