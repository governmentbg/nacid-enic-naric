package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.impl;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaSubjectDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.response.SchoolSubjectDto;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.entity.SchoolSubjectEntity;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.mapper.SchoolSubjectMapper;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.repository.SchoolSubjectRepository;
import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service.SchoolSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SchoolSubjectServiceImpl implements SchoolSubjectService {

    private final SchoolSubjectRepository schoolSubjectRepository;
    private final SchoolSubjectMapper schoolSubjectMapper;

    @Override
    public List<SchoolSubjectDto> getAutocompleteSchoolSubjects(String name, Integer page, Integer pageSize) {

        int defaultPageSize = (pageSize == null || pageSize <= 0) ? 10 : pageSize;
        int defaultPage = (page == null || page <= 0) ? 0 : page - 1;

        Pageable pageable = PageRequest.of(defaultPage, defaultPageSize);

        List<SchoolSubjectEntity> schoolSubjectEntities = this.schoolSubjectRepository.findAutocompleteBySubjectBg(name, pageable);


        return this.schoolSubjectMapper.toDtoList(schoolSubjectEntities);
    }

    @Override
    public void saveNonExistSchoolSubjects(List<DiplomaSubjectDto> diplomaSubjects) {

        List<SchoolSubjectEntity> newSubjectsEntities = diplomaSubjects.stream().filter(s -> Objects.nonNull(s.getSubjectName()) && !s.getSubjectName().isBlank())
                .map(subjectDto -> normalizeSubject(subjectDto.getSubjectName()))
                .filter(normalized -> this.schoolSubjectRepository.getSubjectBySubjectBg(normalized) == null)
                .map(normalized -> {
                    SchoolSubjectEntity newSubject = new SchoolSubjectEntity();
                    newSubject.setSubjectBg(normalized);
                    return newSubject;
                }).collect(Collectors.toList());

        if (!newSubjectsEntities.isEmpty()) {
            this.schoolSubjectRepository.saveAll(newSubjectsEntities);
        }
    }

    private String normalizeSubject(String subject) {
        return String.join(" ", subject.trim().split("\\s+"));
    }
}
