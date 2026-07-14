package bg.duosoft.nacid.backoffice.core.data.domain.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    private int totalPages;
    private long totalElements;
    private List<T> content;

    public Page(long totalElements, List<T> content, int pageSize) {
        this.totalElements = totalElements;
        this.content = content;
        this.totalPages = calculateTotalPages(totalElements, pageSize);
    }

    private int calculateTotalPages(long totalElements, int pageSize) {
        if (totalElements < 0) {
            return 0;
        }

        long totalPages = totalElements % pageSize == 0 ? (totalElements / pageSize) : (totalElements / pageSize + 1);
        return Math.toIntExact(totalPages);
    }
}
