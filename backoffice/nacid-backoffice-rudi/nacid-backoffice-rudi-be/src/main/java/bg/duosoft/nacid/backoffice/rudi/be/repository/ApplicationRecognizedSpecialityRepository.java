package bg.duosoft.nacid.backoffice.rudi.be.repository;

import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.ApplicationRecognizedSpecialityEntity;
import bg.duosoft.nacidshared.web.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRecognizedSpecialityRepository extends BaseRepository<ApplicationRecognizedSpecialityEntity, Integer> {
    @Query("SELECT r from ApplicationRecognizedSpecialityEntity r where r.application.id = :applicationId")
    List<ApplicationRecognizedSpecialityEntity> selectByApplicationId(Integer applicationId);

    @Query("SELECT r.speciality from ApplicationRecognizedSpecialityEntity r group by r.speciality order by r.speciality")
    List<String> selectAllDistinctSpecialities();

    @Modifying
    @Query(value = "DELETE from ApplicationRecognizedSpecialityEntity r where r.application.id = :applicationId")
    void deleteAllByApplicationId(Integer applicationId);
}
