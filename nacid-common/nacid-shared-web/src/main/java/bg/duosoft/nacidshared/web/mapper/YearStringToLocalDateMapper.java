package bg.duosoft.nacidshared.web.mapper;

import bg.duosoft.nacidshareddata.util.date.DateUtils;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public class YearStringToLocalDateMapper {

    public String toYearString(LocalDate localDate) {
        return DateUtils.convertLocalDateToYear(localDate);
    }

    public LocalDate toLocalDate(String year) {
        return DateUtils.convertYearToLocalDate(year);
    }
}
