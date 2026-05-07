package bg.duosoft.nacidfrontofficedto.feedback.filter;

import bg.duosoft.nacidfrontofficedto.Pageable;
import bg.duosoft.nacidfrontofficedto.Sortable;
import bg.duosoft.nacidfrontofficedto.utils.constants.FeedbackSortFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedbackFilterDTO implements Sortable, Pageable {
    private String order = this.DESC_ORDER;
    private String orderBy = FeedbackSortFields.DATE_CREATED;
    private Integer page = this.DEFAULT_PAGE;
    private Integer pageSize = this.DEFAULT_PAGE_SIZE;
    private String name;
    private Date createdDateFrom;
    private Date createdDateTo;
}
