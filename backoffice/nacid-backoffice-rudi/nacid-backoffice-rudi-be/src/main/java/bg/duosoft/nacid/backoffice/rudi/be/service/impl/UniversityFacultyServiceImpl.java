package bg.duosoft.nacid.backoffice.rudi.be.service.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.FacultyDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.UniversityDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.FacultyEntity;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.FacultyMapper;
import bg.duosoft.nacid.backoffice.rudi.be.mapper.UniversityMapper;
import bg.duosoft.nacid.backoffice.rudi.be.repository.UniversityFacultyRepository;
import bg.duosoft.nacid.backoffice.rudi.be.service.UniversityFacultyService;
import bg.duosoft.nacid.backoffice.rudi.be.validator.UniversityFacultyValidator;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UniversityFacultyServiceImpl implements UniversityFacultyService {

    private final FacultyMapper facultyMapper;
    private final UniversityMapper universityMapper;
    private final UniversityFacultyValidator validator;
    private final UniversityFacultyRepository universityFacultyRepository;


    public FacultyDTO create(FacultyDTO faculty, UniversityDTO university) {
        if (Objects.isNull(faculty)) {
            throw new BadRequestException();
        } else {
            if (validator != null) {
                BadRequestValidator.validateRequest(validator, faculty, university);
            }
            FacultyEntity facultyEntity = facultyMapper.toEntity(faculty);
            facultyEntity.setUniversity(universityMapper.toEntity(university));
            return facultyMapper.toDto(universityFacultyRepository.save(facultyEntity));
        }
    }

    @Override
    public List<FacultyDTO> selectUniversityFacultiesByUniversityIdAndName(Integer universityId, String facultyName) {
        return facultyMapper.toDtoList(universityFacultyRepository.selectFacultiesByUniversityIdAndFacultyName(universityId, facultyName));
    }

    @Override
    public FacultyDTO selectFacultyById(Integer facultyId) {
        return facultyMapper.toDto(universityFacultyRepository.selectFacultyById(facultyId));
    }

}
