package bg.duosoft.nacid.backoffice.rudi.be.repository.custom.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.StringIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationSubType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ConnectionType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.SarApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.filter.sort.RudiReportSortUtils;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.RudiCommonReportFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.type.ApplicationTypeReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.commission.CommissionReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.commission.CommissionStatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.common.NaturalPersonNamesReportDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.common.NaturalPersonReportDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.DiplomaReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.DiplomaSpecialityReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.RecognizedDiplomaSpecialityReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.diploma_owner.DiplomaOwnerSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.edu_level.EduLevelDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.qualification.QualificationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.diploma.speciality.SpecialityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.institution.TrainingInstitutionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.institution.TrainingInstitutionReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.university.UniversityDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.university.UniversityReportSectionDTO;
import bg.duosoft.nacid.backoffice.rudi.be.domain.entity.VRudiApplicationsEntity;
import bg.duosoft.nacid.backoffice.rudi.be.repository.CommonReportRepository;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseCommonReportRepositoryImpl;
import bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseReportRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 30.08.2023
 * Time: 14:58
 */
@Repository
public class CommonReportRepositoryImpl extends BaseCommonReportRepositoryImpl<VRudiApplicationsEntity, RudiCommonReportFilterDTO> implements CommonReportRepository {
    public CommonReportRepositoryImpl() {
        super(VRudiApplicationsEntity.class);
    }

    @Override
    protected void addAdditionalCriteria(RudiCommonReportFilterDTO filter, BaseReportRepositoryImpl<VRudiApplicationsEntity, RudiCommonReportFilterDTO>.WhereClauseAndParameters where) {
        if (filter.getApplicationType() != null) {
            addApplicationTypeCriteria(filter.getApplicationType(), where);
        }
        CommissionReportSectionDTO commission = filter.getCommission();
        addCommissionCalendarBaseCriteria(commission, where);
        addCommissionStatusCriteria(commission, where);
        addDiplomaCriteria(filter.getDiploma(), where);
        addDiplomaOwnerCriteria(filter.getDiplomaOwner(), where);
        addDocumentReceiveMethodCriteria(filter.getDocumentReceiveMethod(), where);
        addTrainingInstitutionCriteria(filter.getTrainingInstitution(), where);
        addSpecialitiesCriteria(filter.getDiplomaSpeciality(), where);
        addRecognitionDetailsCriteria(filter.getRecognizedDiplomaSpeciality(), where);
        addUniversitiesCriteria(filter.getUniversity(), where);

    }

    @Override
    protected String getOrderByColumns(RudiCommonReportFilterDTO filter) {
        if (ObjectUtils.isEmpty(filter.getOrderBy())) {
            return null;
        }
        return RudiReportSortUtils.sorterColumnMap().get(filter.getOrderBy());
    }

    private void addApplicationTypeCriteria(ApplicationTypeReportSectionDTO applicationType, WhereClauseAndParameters where) {
        if (!ObjectUtils.isEmpty(applicationType.getApplicationTypes())) {
            addWhereClauseAndParam(where, BASE_JOIN_TABLES.APPLICATION, WHERE_OPERATION_TYPE.IN, "ase_code", "aseCodes", applicationType.getApplicationTypes().stream().map(StringIdDTO::getId).collect(Collectors.toList()), null);
        }
        if (!ObjectUtils.isEmpty(applicationType.getApplicationTypes()) && applicationType.getApplicationTypes().stream().map(r -> r.getId()).anyMatch(r -> r.equals(ApplicationSubType.RUDI_SAR.appSubType())) && !ObjectUtils.isEmpty(applicationType.getSarServices()) && applicationType.getSarServicesJoin() != null) {
            Map<String, Integer> sarParams = null;
            String joinOperation = null;
            switch (applicationType.getSarServicesJoin()) {
                case ALL -> {
                    sarParams = applicationType.getSarServices().stream().map(r -> r.getId()).collect(Collectors.toMap(ss -> sarTypeToColumnName(ss), ss -> 1));
                    joinOperation = "AND";
                }
                case ANY -> {
                    sarParams = applicationType.getSarServices().stream().map(r -> r.getId()) .collect(Collectors.toMap(ss -> sarTypeToColumnName(ss), ss -> 1));
                    joinOperation = "OR";
                }
                case ONLY_ALL -> {
                    Set<SarApplicationType> selectedSarTypes = applicationType.getSarServices().stream().map(StringIdDTO::getId).map(SarApplicationType::selectByCode).collect(Collectors.toSet());
                    sarParams = Arrays.stream(SarApplicationType.values()).collect(Collectors.toMap(sat -> sarTypeToColumnName(sat.code()), sat -> selectedSarTypes.contains(sat) ? 1 : 0));
                    joinOperation = "AND";
                }
            }
            List<String> sarWhereParts = new ArrayList<>();
            sarParams.entrySet().forEach(sp -> addWhereClauseAndValue(where, RUDI_JOIN_TABLES.RUDI_SAR_APPLICATION, WHERE_OPERATION_TYPE.EQUAL, sp.getKey(), sp.getValue(), sarWhereParts));
            where.addWhereClause(sarWhereParts.stream().collect(Collectors.joining(" " + joinOperation + " ")));
        }

    }
    private String sarTypeToColumnName(String sarType) {
        return switch (SarApplicationType.selectByCode(sarType)) {
            case STATUTE -> "statute_flag";
            case AUTHENTICITY -> "authenticity_flag";
            case RECOMMENDATION -> "recommendation_flag";
        };
    }

    private void addCommissionCalendarBaseCriteria(CommissionReportSectionDTO commission, WhereClauseAndParameters where) {
        if (commission == null || ((commission.getIsCommissionReviewed() == null || !commission.getIsCommissionReviewed()) && (commission.getIsNotCommissionReviewed() == null || !commission.getIsNotCommissionReviewed()))) {
            return;
        }
        if (Objects.equals(true, commission.getIsNotCommissionReviewed())) {
            where.addJoinTable(BASE_JOIN_TABLES.APPLICATION);
            where.addWhereClause("apn.id not in (select apn_id from rudi.commission_applications)");
        } else {
            addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_COMMISSION_CALENDAR, WHERE_OPERATION_TYPE.EQUAL, "session_status_code", "processedCommissionCalendarStatus", "COD", null);
            if (commission.getSessionDateFrom() != null) {
                addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_COMMISSION_CALENDAR, WHERE_OPERATION_TYPE.GREATER_OR_EQUAL, "session_time", "ccrSessionDateFrom", commission.getSessionDateFrom(), null);
            }
            if (commission.getSessionDateTo() != null) {
                addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_COMMISSION_CALENDAR, WHERE_OPERATION_TYPE.LESS, "session_time", "ccrSessionDateTo", commission.getSessionDateTo().plusDays(1), null);
            }
            if (commission.getSessionNumberFrom() != null) {
                addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_COMMISSION_CALENDAR, WHERE_OPERATION_TYPE.GREATER_OR_EQUAL, "session_num", "ccrSessionNumFrom", commission.getSessionNumberFrom(), null);
            }
            if (commission.getSessionNumberTo() != null) {
                addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_COMMISSION_CALENDAR, WHERE_OPERATION_TYPE.LESS_OR_EQUAL, "session_num", "ccrSessionNumTo", commission.getSessionNumberTo(), null);
            }
        }



    }
    private void addCommissionStatusCriteria(CommissionReportSectionDTO commission, WhereClauseAndParameters where) {
        if (commission != null && Objects.equals(true, commission.getIsCommissionReviewed()) && !CollectionUtils.isEmpty(commission.getCommissionStatuses())) {
            int cnt = 1;
            List<String> unionSqls = new ArrayList<>();
            for (CommissionStatusDTO cs : commission.getCommissionStatuses()) {
                String firstStatus = cs.getCommissionStatus();
                if (ObjectUtils.isEmpty(firstStatus)) {
                    continue;
                }
                String firstPart = addCommissionStatusFilterPart0(firstStatus, cs.getLegalReasons(), where, cnt * 10 + 1);
                unionSqls.add(firstPart);
            }
            if (unionSqls.size() > 0) {
                where.addWhereClause("apn.id in (" + unionSqls.stream().collect(Collectors.joining(") UNION (", "(", ")")) + ")");
            }
        }
    }
    private String addCommissionStatusFilterPart0(String status, List<IntegerIdDTO> legalReasons, WhereClauseAndParameters where, Integer id) {
        String sql = "select ash.apn_id from common.app_status_history ash\n" +
                "join rudi.commission_calendar ccr2 on ash.commission_calendar_id = ccr2.id\n" +
                "where 1 = 1 and ccr2.id = ccr.id ";
        List<String> sqlParts = new ArrayList<>();
        sqlParts.add(String.format("ash.status_code = :status%d", id));
        where.addParam("status" + id, status);
        if (!ObjectUtils.isEmpty(legalReasons)) {
            sqlParts.add(String.format("ash.legal_reason_id in :legalReasonId%d", id));
            where.addParam("legalReasonId" + id, legalReasons.stream().map(r -> r.getId()).collect(Collectors.toList()));
        }
        sql += " AND (" + sqlParts.stream().collect(Collectors.joining(") AND (", "(", ")")) + ")";
        return "(" + sql + ")\n";
    }

    private void addDiplomaOwnerCriteria(DiplomaOwnerSectionDTO filter, WhereClauseAndParameters where) {
        if (filter == null) {
            return;
        }
        if (!ObjectUtils.isEmpty(filter.getCountries() )) {
            addWhereClauseAndParam(where,  RUDI_JOIN_TABLES.RUDI_DIPLOMA_OWNER, WHERE_OPERATION_TYPE.IN, "citizenship_id", "ownerCitizenship", filter.getCountries().stream().map(r -> r.getId()).toList(), null);
        }
        addNaturalPersonCriteria(filter, where, RUDI_JOIN_TABLES.RUDI_DIPLOMA_OWNER);
    }
    private void addDiplomaCriteria(DiplomaReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter != null) {
            if (Objects.equals(true, filter.getIsStateApproved())) {
                addWhereClauseAndValue(where, RUDI_JOIN_TABLES.RUDI_TRAINING_COURSE_DIPLOMA_EXAMINATION, WHERE_OPERATION_TYPE.EQUAL, "state_approved_flag", 1, null);
            }
            if (filter.getDiplomaYearFrom() != null) {
                addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_TRAINING_COURSE, WHERE_OPERATION_TYPE.GREATER_OR_EQUAL, "diploma_date", "diplomaDateFrom", LocalDate.of(filter.getDiplomaYearFrom(), 1, 1), null);
            }
            if (filter.getDiplomaYearTo() != null) {
                addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_TRAINING_COURSE, WHERE_OPERATION_TYPE.LESS, "diploma_date", "diplomaDateTo", LocalDate.of(filter.getDiplomaYearTo(), 1, 1).plusYears(1), null);
            }

        }
    }
    private void addTrainingInstitutionCriteria(TrainingInstitutionReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter != null && !ObjectUtils.isEmpty(filter.getTrainingInstitutions())) {
            List<String> sqlParts = new ArrayList<>();
            List<Integer> ids = filter
                    .getTrainingInstitutions()
                    .stream()
                    .map(r -> r.getTrainingInstitutions())
                    .filter(Objects::nonNull)
                    .<Integer>mapMulti((tins, mapper) -> tins.forEach(t -> mapper.accept(t.getId())))
                    .toList();
            if (ids.size() > 0) {
                addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_TRAINING_LOCATION_EXAMINATION_LOCATIONS,  WHERE_OPERATION_TYPE.IN, "training_institution_id", "trainingInstitutionIds", ids, sqlParts);
            }
            AtomicInteger cnt = new AtomicInteger(1);
            for (TrainingInstitutionDTO tin : filter.getTrainingInstitutions()) {
                List<String> sqlSubParts = new ArrayList<>();
                if (tin.getCountry() != null && tin.getCountry().getId() != null) {
                    addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_TRAINING_INSTITUTION, WHERE_OPERATION_TYPE.EQUAL, "country_code",  "trainingInstitutionCountryCode" + cnt.get(), tin.getCountry().getId(), sqlSubParts);
                }
                if (!ObjectUtils.isEmpty(tin.getTrainingInstitutionNames())) {
                    AtomicInteger tinNameCnt = new AtomicInteger(1);
                    List<String> tinNamesSql = new ArrayList<>();
                    tin.getTrainingInstitutionNames().forEach(str -> addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_TRAINING_INSTITUTION, WHERE_OPERATION_TYPE.ILIKE, "name", "tinName" + (cnt.get() * 100 + tinNameCnt.getAndIncrement()), "%" + str + "%", tinNamesSql));
                    sqlSubParts.add(tinNamesSql.stream().collect(Collectors.joining(") OR (", "(", ")")));
                }
                if (sqlSubParts.size() > 0) {
                    sqlParts.add(sqlSubParts.stream().collect(Collectors.joining(") AND (", "(", ")")));
                }

                cnt.incrementAndGet();
            }
            if (sqlParts.size() > 0) {
                where.addWhereClause(sqlParts.stream().collect(Collectors.joining(") OR (", "(", ")")));
            }
        }
    }
    private void addRecognitionDetailsCriteria(RecognizedDiplomaSpecialityReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter == null) {
            return;
        }
        addInOrILikeCriteriaToSingleField(where, RUDI_JOIN_TABLES.RUDI_RECOGNIZED_SPECIALITIES, "speciality", "recognizedSpeciality", filter.getSpecialities(), filter.getSpecialityNames());
        addInOrILikeCriteriaToSingleField(where, RUDI_JOIN_TABLES.RUDI_RECOGNITION_DETAILS, "recognized_qualification", "recognizedQualification", filter.getQualifications(), filter.getQualificationNames());
        if (!ObjectUtils.isEmpty(filter.getEduLevels())) {
            addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_RECOGNITION_DETAILS, WHERE_OPERATION_TYPE.IN, "recognized_edu_level", "recognizedEduLevels", toStringList(filter.getEduLevels()), null);
        }
    }


    private void addSpecialitiesCriteria(DiplomaSpecialityReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter == null) {
            return;
        }
        if (filter.getSpeciality() != null) {
            SpecialityDTO spec = filter.getSpeciality();
            addInOrILikeCriteriaToSingleField(where, RUDI_JOIN_TABLES.RUDI_DIPLOMA_SPECIALITIES, "speciality", "speciality", spec.getSpecialities(), spec.getSpecialityNames());
            addInOrILikeCriteriaToSingleField(where, RUDI_JOIN_TABLES.RUDI_DIPLOMA_SPECIALITIES, "original_speciality", "originalSpeciality", spec.getOriginalSpecialities(), spec.getOriginalSpecialityNames());
        }
        if (filter.getQualification() != null) {
            QualificationDTO qual = filter.getQualification();
            addInOrILikeCriteriaToSingleField(where, RUDI_JOIN_TABLES.RUDI_TRAINING_COURSE, "qualification", "qualification", qual.getQualifications(), qual.getQualificationNames());
            addInOrILikeCriteriaToSingleField(where, RUDI_JOIN_TABLES.RUDI_TRAINING_COURSE, "original_qualification", "originalQualification", qual.getOriginalQualifications(), qual.getOriginalQualificationNames());
        }
        if (filter.getEduLevel() != null) {
            EduLevelDTO ell = filter.getEduLevel();
            addInOrILikeCriteriaToSingleField(where, RUDI_JOIN_TABLES.RUDI_TRAINING_COURSE, "original_edu_level_name", "originalEduLevelName", ell.getOriginalEduLevels(), ell.getOriginalEduLevelNames());
        }

    }

    /**
     * generira SQL ot vida na
     *
     * universityIds in .... or (uny.country_code = .... and (uny.bg_name ilike ... or uny.bg_name ilike ... or uny.org_name ilike ... or uny.org_name ilike ...)) or (uny.country_code = .... and (uny.bg_name ilike ... or uny.bg_name ilike ... or uny.org_name ilike ... or uny.org_name ilike ...)) i t.n.
     * @param filter
     * @param where
     */

    private void addUniversitiesCriteria(UniversityReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter == null) {
            return;
        }
        if (!ObjectUtils.isEmpty(filter.getUniversities())) {
            List<String> unisSql = new ArrayList<>();
            Set<Integer> uniIds = filter.getUniversities().stream().map(r -> r.getUniversities()).filter(Objects::nonNull).flatMap(r -> r.stream()).map(r -> r.getId()).filter(Objects::nonNull).collect(Collectors.toSet());
            if (uniIds.size() > 0) {
                addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_TRAINING_COURSE_UNIVERSITIES, WHERE_OPERATION_TYPE.IN, "uny_id", "universityIds", uniIds, unisSql);
            }

            AtomicInteger cnt = new AtomicInteger(1);
            for (UniversityDTO u : filter.getUniversities()) {
                List<String> singleUniSql = new ArrayList<>();
                if (u.getCountry() != null && !ObjectUtils.isEmpty(u.getCountry().getId())) {
                    addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_UNIVERSITIES, WHERE_OPERATION_TYPE.EQUAL, "country_code", "unyCountryCode" + cnt.get(), u.getCountry().getId(), singleUniSql);
                }

                List<String> ilikeParts = new ArrayList<>();
                if (!ObjectUtils.isEmpty(u.getUniversityNames())) {
                    AtomicInteger uniNameCnt = new AtomicInteger(1);
                    u.getUniversityNames()
                            .forEach(r -> addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_UNIVERSITIES, WHERE_OPERATION_TYPE.ILIKE, "bg_name", "universityName" + (cnt.get() * 100 + uniNameCnt.getAndIncrement()), "%" + r + "%", ilikeParts));
                }

                if (!ObjectUtils.isEmpty(u.getOrgUniversityNames())) {
                    AtomicInteger uniNameCnt = new AtomicInteger(1);
                    u.getOrgUniversityNames()
                            .forEach(r -> addWhereClauseAndParam(where, RUDI_JOIN_TABLES.RUDI_UNIVERSITIES, WHERE_OPERATION_TYPE.ILIKE, "org_name", "universityOrgName" + (cnt.get() * 100 + uniNameCnt.getAndIncrement()), "%" + r + "%", ilikeParts));
                }
                if (ilikeParts.size() > 0) {
                    singleUniSql.add(ilikeParts.stream().collect(Collectors.joining(") OR (", "(", ")")));
                }
                if (singleUniSql.size() > 0) {
                    unisSql.add(singleUniSql.stream().collect(Collectors.joining(") AND (", "(", ")")));
                }
                cnt.incrementAndGet();
            }
            if (unisSql.size() > 0) {
                where.addWhereClause(unisSql.stream().collect(Collectors.joining(") OR (", "(", ")")));
            }
        }
        if (Objects.equals(true, filter.getOnlyWithDiplomaRegisters())) {
            addWhereClauseAndValue(where, RUDI_JOIN_TABLES.RUDI_UNIVERSITIES, WHERE_OPERATION_TYPE.NOT_EQUAL, "url_diploma_register", null, null);
        }
        if (filter.getOnlyJointDegree() != null) {
            where.addJoinTable(RUDI_JOIN_TABLES.RUDI_TRAINING_COURSE);
            if (filter.getOnlyJointDegree()) {
                where.addWhereClause("((select count(*) from rudi.training_course_universities tcu where tcu.tce_id = tce.id) > 1)");
            } else {
                where.addWhereClause("((select count(*) from rudi.training_course_universities tcu where tcu.tce_id = tce.id) = 1)");
            }
        }
    }

    protected enum RUDI_JOIN_TABLES implements JoinTable {
        RUDI_APPLICATION("ran", "rudi.rudi_application ran on ran.apn_id = apn.id", BASE_JOIN_TABLES.APPLICATION),
        RUDI_TRAINING_COURSE("tce", "rudi.training_course tce on tce.apn_id = apn.id", BASE_JOIN_TABLES.APPLICATION),
        RUDI_DIPLOMA_OWNER("owr", "common.person owr on owr.id = tce.owner_id", RUDI_TRAINING_COURSE),
        RUDI_TRAINING_COURSE_DIPLOMA_EXAMINATION("tcde", "rudi.training_course_diploma_examination tcde on tcde.tce_id = tce.id ", RUDI_TRAINING_COURSE),
        RUDI_COMMISSION_APPLICATIONS("can", "rudi.commission_applications can on can.apn_id = apn.id", BASE_JOIN_TABLES.APPLICATION),
        RUDI_COMMISSION_CALENDAR("ccr", "rudi.commission_calendar ccr on ccr.id = can.calendar_id", RUDI_COMMISSION_APPLICATIONS),
        RUDI_SAR_APPLICATION("san", " rudi.sar_application san on san.apn_id = apn.id", BASE_JOIN_TABLES.APPLICATION),
        RUDI_TRAINING_LOCATION("tln", " rudi.training_location tln on tln.tce_id = tce.id", RUDI_TRAINING_COURSE),
        RUDI_TRAINING_LOCATION_EXAMINATION_LOCATIONS("tlel", "rudi.training_location_examination_locations tlel on tlel.training_location_id = tln.id", RUDI_TRAINING_LOCATION),
        RUDI_TRAINING_INSTITUTION("tin", "rudi.training_institution tin on tin.id = tlel.training_institution_id", RUDI_TRAINING_LOCATION_EXAMINATION_LOCATIONS),
        RUDI_RECOGNIZED_SPECIALITIES("ars", "rudi.application_recognized_speciality ars on ars.apn_id = apn.id", BASE_JOIN_TABLES.APPLICATION),
        RUDI_RECOGNITION_DETAILS("ard", " rudi.application_recognition_details ard on ard.apn_id = apn.id", BASE_JOIN_TABLES.APPLICATION),
        RUDI_DIPLOMA_SPECIALITIES("tcs", "rudi.training_course_speciality tcs on tcs.tce_id = tce.id", RUDI_TRAINING_COURSE),
        RUDI_TRAINING_COURSE_UNIVERSITIES("tcu", "rudi.training_course_universities tcu on tcu.tce_id = tce.id", RUDI_TRAINING_COURSE),
        RUDI_UNIVERSITIES("uny", "rudi.university uny on tcu.uny_id = uny.id", RUDI_TRAINING_COURSE_UNIVERSITIES),

;
        private final String alias;
        private final List<JoinTable> relations;
        private final String joinExpression;
        private final boolean isLeftJoin;
        RUDI_JOIN_TABLES(String alias, String joinExpression, JoinTable... relations) {
            this.isLeftJoin = false;
            this.alias = alias;
            this.joinExpression = joinExpression;
            this.relations = relations == null || relations.length == 0 ? null : Arrays.asList(relations);
        }
        @Override
        public String getAlias() {
            return alias;
        }
        @Override
        public List<JoinTable> getRelations() {
            return relations;
        }
        @Override
        public String getJoinExpression() {
            return joinExpression;
        }

        @Override
        public boolean getIsLeftJoin() {
            return isLeftJoin;
        }
    }
}
