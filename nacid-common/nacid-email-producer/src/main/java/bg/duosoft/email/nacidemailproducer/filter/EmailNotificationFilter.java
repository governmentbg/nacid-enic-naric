package bg.duosoft.email.nacidemailproducer.filter;

import bg.duosoft.email.nacidemailproducer.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailNotificationFilter implements Sortable, Pageable {
    private String order = this.DESC_ORDER;
    private String orderBy = EmailNotificationSorterUtils.CREATED_DATE;
    private Integer page = this.DEFAULT_PAGE;
    private Integer pageSize = this.DEFAULT_PAGE_SIZE;
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT_DOT)
    private Date createdDateFrom;
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT_DOT)
    private Date createdDateTo;
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT_DOT)
    private Date sentDateFrom;
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT_DOT)
    private Date sentDateTo;
    private String recipients;
    private String status;
}
