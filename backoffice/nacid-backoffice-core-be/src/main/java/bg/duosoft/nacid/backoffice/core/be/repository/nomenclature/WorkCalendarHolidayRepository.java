package bg.duosoft.nacid.backoffice.core.be.repository.nomenclature;

import bg.duosoft.nacid.backoffice.core.data.domain.entity.nomenclatures.WorkCalendarHolidayEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WorkCalendarHolidayRepository extends BaseRepository<WorkCalendarHolidayEntity, LocalDate> {

    @Query(value = "SELECT w FROM WorkCalendarHolidayEntity w where w.id >= :startDate and w.id <= :endDate ORDER BY w.id")
    List<WorkCalendarHolidayEntity> selectByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "select DISTINCT EXTRACT('Year' FROM w.id ) FROM nomenclatures.work_calendar_holiday w", nativeQuery = true)
    List<Integer> selectAllYears();


}
