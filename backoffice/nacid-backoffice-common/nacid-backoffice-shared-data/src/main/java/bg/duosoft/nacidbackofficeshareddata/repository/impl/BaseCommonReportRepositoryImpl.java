package bg.duosoft.nacidbackofficeshareddata.repository.impl;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.base.IntegerIdDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.application.ApplicationReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.status.StatusDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.status.StatusReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.submission_method.SubmissionMethodReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.report.filter.type.DocumentTypeReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.applicant.legal.LegalApplicantReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.applicant.natural_person.NaturalPersonApplicantReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.responsible_user.ApplicationResponsibleUserReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.service_type.ServiceTypeReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.application.user_created.ApplicationUserCreatedReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.common.CommonReportFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.common.NaturalPersonNamesReportDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.common.NaturalPersonReportDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.document.receive_method.DocumentReceiveMethodReportSectionDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.report.filter.representative.RepresentativeReportSectionDTO;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static bg.duosoft.nacidbackofficeshareddata.repository.impl.BaseReportRepositoryImpl.BASE_JOIN_TABLES.*;

/**
 * User: ggeorgiev
 * Date: 03.09.2023
 * Time: 16:15
 */
public abstract class BaseCommonReportRepositoryImpl<E extends Serializable, F extends CommonReportFilterDTO> extends BaseReportRepositoryImpl<E, F>{
    protected BaseCommonReportRepositoryImpl(Class<E> entityClass) {
        super(entityClass);
    }

    @Override
    protected void prepareWhereClauseAndParameters(F filter, WhereClauseAndParameters where) {
        addSubmissionMethodCriteria(filter.getSubmissionMethod(), where);
        addServiceTypeCriteria(filter.getServiceType(), where);
        addStatusCriteria(filter.getStatus(), where);
        addApplicationReportCriteria(filter.getApplication(), where);
        addDocumentTypeCriteria(filter.getDocumentType(), where);
        addDocumentReceiveMethodCriteria(filter.getDocumentReceiveMethod(), where);
        addUserCreatedCriteria(filter.getApplicationUserCreated(), where);
        addResponsibleUserCriteria(filter.getApplicationResponsibleUser(), where);
        addLegalApplicantFilter(filter.getLegalApplicant(), where);
        addApplicantNaturalPersonFilter(filter.getNaturalPersonApplicant(), where);
        addNaturalPersonNamesCriteria(filter.getDiplomaName(), where, BASE_JOIN_TABLES.APPLICANT_DIPLOMA_NAMES);
        addRepresentativeCriteria(filter.getRepresentative(), where);

        addAdditionalCriteria(filter, where);
    }
    protected abstract void addAdditionalCriteria(F filter, WhereClauseAndParameters where);


    protected void addSubmissionMethodCriteria(SubmissionMethodReportSectionDTO submissionSection, WhereClauseAndParameters where) {
        if (submissionSection != null && Objects.equals(true, submissionSection.getOnlyElectronic())) {
            addWhereClauseAndValue(where, APPLICATION, WHERE_OPERATION_TYPE.NOT_EQUAL,  "efiling_id", null, null);
            if (Objects.equals(true, submissionSection.getOnlyDigitalSignature())) {
                addWhereClauseAndValue(where, APPLICATION, WHERE_OPERATION_TYPE.NOT_EQUAL,  "efiling_signed_flag", 1, null);
            }
        }
    }
    protected void addServiceTypeCriteria(ServiceTypeReportSectionDTO serviceType, WhereClauseAndParameters where) {
        if (serviceType != null && serviceType.getServiceType() != null && serviceType.getServiceType().getId() != null) {
            addWhereClauseAndParam(where, APPLICATION, WHERE_OPERATION_TYPE.EQUAL, "service_type", "serviceType", serviceType.getServiceType().getId(), null);
        }
    }
    protected void addStatusCriteria(StatusReportSectionDTO statusSection, WhereClauseAndParameters where) {
        if (statusSection != null && !ObjectUtils.isEmpty(statusSection.getStatuses())) {
            int cnt = 1;
            List<String> sql = new ArrayList<>();
            for (StatusDTO status : statusSection.getStatuses()) {
                List<String> subSql = new ArrayList<>();

                JoinTable statusTable = BASE_JOIN_TABLES.STATUS_HISTORY;
                if (Objects.equals(true, status.getOnlyActualStatus())) {
                    statusTable = BASE_JOIN_TABLES.CURRENT_STATUS_HISTORY;
                }

                if (!ObjectUtils.isEmpty(status.getStatus())) {

                    addWhereClauseAndParam(where, statusTable, WHERE_OPERATION_TYPE.EQUAL, "status_code", "statusCode" + cnt, status.getStatus(), subSql);
                    if (status.getStatusDateFrom() !=  null) {

                        addWhereClauseAndParam(where, statusTable, WHERE_OPERATION_TYPE.GREATER_OR_EQUAL, "date_created", "statusDateCreatedFrom" + cnt, status.getStatusDateFrom(), subSql);
                    }
                    if (status.getStatusDateTo() !=  null) {
                        addWhereClauseAndParam(where, statusTable, WHERE_OPERATION_TYPE.LESS_OR_EQUAL, "date_created", "statusDateCreatedTo" + cnt, status.getStatusDateTo().plusDays(1), subSql);
                    }
                }

                if (!ObjectUtils.isEmpty(status.getActualLegalStatus())) {
                    addWhereClauseAndParam(where, BASE_JOIN_TABLES.FINAL_STATUS_HISTORY, WHERE_OPERATION_TYPE.EQUAL, "status_code", "finalStatus" + cnt, status.getActualLegalStatus(), subSql);

                    List<IntegerIdDTO> lrs = status.getActualLegalStatusLegalReasons();
                    if (lrs != null && lrs.size() > 0) {
                        addWhereClauseAndParam(where, BASE_JOIN_TABLES.FINAL_STATUS_HISTORY, WHERE_OPERATION_TYPE.IN, "legal_reason_id", "finalStatusLegalReasons" + cnt, toIntegerList(status.getActualLegalStatusLegalReasons()), subSql);
                    }
                    if (status.getActualLegalStatusDateFrom() != null) {
                        addWhereClauseAndParam(where, BASE_JOIN_TABLES.FINAL_STATUS_HISTORY, WHERE_OPERATION_TYPE.GREATER_OR_EQUAL, "date_created", "finalStatusDateCreatedFrom" + cnt, status.getActualLegalStatusDateFrom(), subSql);
                    }
                    if (status.getActualLegalStatusDateTo() != null) {
                        addWhereClauseAndParam(where, BASE_JOIN_TABLES.FINAL_STATUS_HISTORY, WHERE_OPERATION_TYPE.LESS, "date_created", "finalStatusDateCreatedTo" + cnt, status.getActualLegalStatusDateTo().plusDays(1), subSql);
                    }
                }

                if (!ObjectUtils.isEmpty(status.getActualDocflowStatus())) {
                    addWhereClauseAndParam(where, BASE_JOIN_TABLES.APPLICATION, WHERE_OPERATION_TYPE.EQUAL, "docflow_status_code", "docflowStatus" + cnt, status.getActualDocflowStatus(), subSql);
                    if (status.getActualDocflowStatusDateFrom() != null) {
                        addWhereClauseAndParam(where, BASE_JOIN_TABLES.CURRENT_DOCFLOW_STATUS_HISTORY, WHERE_OPERATION_TYPE.GREATER_OR_EQUAL, "date_created", "docflowStatusDateFrom" + cnt, status.getActualDocflowStatusDateFrom(), subSql);
                    }
                    if (status.getActualDocflowStatusDateTo() != null) {
                        addWhereClauseAndParam(where, BASE_JOIN_TABLES.CURRENT_DOCFLOW_STATUS_HISTORY, WHERE_OPERATION_TYPE.LESS, "date_created", "docflowStatusDateTo" + cnt, status.getActualDocflowStatusDateTo().plusDays(1), subSql);
                    }
                }
                if (subSql.size() > 0) {
                    sql.add(subSql.stream().collect(Collectors.joining(") AND (", "(", ")")));
                }

                cnt++;
            }
            if (sql.size() > 0) {
                where.addWhereClause(sql.stream().collect(Collectors.joining(") OR (", "(", ")")));
            }

        }
    }
    protected void addApplicationReportCriteria(ApplicationReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter != null) {
            if (!ObjectUtils.isEmpty(filter.getEntryNumber())) {
                addWhereClauseAndParam(where, APPLICATION, WHERE_OPERATION_TYPE.EQUAL, "entry_num", "entryNum", filter.getEntryNumber(), null);
            }
            if (filter.getApplicationDateFrom() != null) {
                addWhereClauseAndParam(where, APPLICATION, WHERE_OPERATION_TYPE.GREATER_OR_EQUAL, "entry_date", "entryDateFrom", filter.getApplicationDateFrom(), null);
            }
            if (filter.getApplicationDateTo() != null) {
                addWhereClauseAndParam(where, APPLICATION, WHERE_OPERATION_TYPE.LESS_OR_EQUAL, "entry_date", "entryDateTo", filter.getApplicationDateTo(), null);
            }

            if (filter.getBackofficeDateFrom() != null) {
                addWhereClauseAndParam(where, APPLICATION, WHERE_OPERATION_TYPE.GREATER_OR_EQUAL, "date_created", "backofficeDateFrom", filter.getBackofficeDateFrom(), null);
            }
            if (filter.getBackofficeDateTo() != null) {
                addWhereClauseAndParam(where, APPLICATION, WHERE_OPERATION_TYPE.LESS_OR_EQUAL, "date_created", "backofficeDateTo", filter.getBackofficeDateTo(), null);
            }
        }
    }

    protected void addDocumentTypeCriteria(DocumentTypeReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter != null && !ObjectUtils.isEmpty(filter.getDocumentTypes())) {
            addWhereClauseAndParam(where, BASE_JOIN_TABLES.ATTACHED_DOCS, WHERE_OPERATION_TYPE.IN, "doc_type_id", "documentTypes", toIntegerList(filter.getDocumentTypes()), null);
        }
    }
    protected void addDocumentReceiveMethodCriteria(DocumentReceiveMethodReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter != null && !ObjectUtils.isEmpty(filter.getDocumentReceiveMethods())) {
            addWhereClauseAndParam(where, APPLICATION, WHERE_OPERATION_TYPE.IN, "document_receive_method_code", "personalDocumentTypes", toStringList(filter.getDocumentReceiveMethods()), null);
        }
    }

    protected void addUserCreatedCriteria(ApplicationUserCreatedReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter != null && !ObjectUtils.isEmpty(filter.getUsers())) {
            addWhereClauseAndParam(where, APPLICATION, WHERE_OPERATION_TYPE.IN, "user_created", "usersCreated", toStringList(filter.getUsers()), null);
        }
    }
    protected void addResponsibleUserCriteria(ApplicationResponsibleUserReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter != null && !ObjectUtils.isEmpty(filter.getUsers())) {
            if (Objects.equals(true, filter.getOnlyActiveResponsibleUsers())) {
                where.addJoinTable(RESPONSIBLE_USERS);
                where.addWhereClause("rur.responsible_user in :responsibleUsers and rur.date_to is null");
                where.addParam("responsibleUsers", toStringList(filter.getUsers()));
            } else {
                addWhereClauseAndParam(where, RESPONSIBLE_USERS, WHERE_OPERATION_TYPE.IN, "responsible_user", "responsibleUsers", toStringList(filter.getUsers()), null);
            }

        }
    }
    protected void addApplicantNaturalPersonFilter(NaturalPersonApplicantReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter == null) {
            return;
        }
        if (!ObjectUtils.isEmpty(filter.getPersonalDocumentTypes())) {
            addWhereClauseAndParam(where, APPLICATION, WHERE_OPERATION_TYPE.IN, "personal_document_type_code", "documentReceiveMethods", toStringList(filter.getPersonalDocumentTypes()), null);
        }
        addNaturalPersonCriteria(filter, where, APPLICANT);
    }

    protected void addRepresentativeCriteria(RepresentativeReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter == null) {
            return;
        }
        addNaturalPersonCriteria(filter.getNaturalPerson(), where, BASE_JOIN_TABLES.REPRESENTATIVE);
        if (!ObjectUtils.isEmpty(filter.getCompanies())) {
            addWhereClauseAndParam(where, BASE_JOIN_TABLES.APPLICATION, WHERE_OPERATION_TYPE.IN, "representative_company_id", "representativeCompanyIds", toIntegerList(filter.getCompanies()), null);
        }
    }
    protected void addLegalApplicantFilter(LegalApplicantReportSectionDTO filter, WhereClauseAndParameters where) {
        if (filter != null) {
            addInOrILikeCriteriaToDifferentFields(where, APPLICANT, "id", "legal_name", "applicant", filter.getLegalApplicants(), filter.getLegalApplicantNames());
        }
    }

    protected void addNaturalPersonCriteria(NaturalPersonReportDTO filter, WhereClauseAndParameters where, JoinTable table) {
        if (filter == null) {
            return;
        }
        if (!ObjectUtils.isEmpty(filter.getIdentifier())) {
            addWhereClauseAndParam(where, table, WHERE_OPERATION_TYPE.ILIKE, "civil_id", table.getAlias() + "CivilId", filter.getIdentifier() + "%", null);
        }
        addNaturalPersonNamesCriteria(filter, where, table);

    }
    protected void addNaturalPersonNamesCriteria(NaturalPersonNamesReportDTO filter, WhereClauseAndParameters where, JoinTable table) {
        if (filter == null) {
            return;
        }
        if (!ObjectUtils.isEmpty(filter.getFirstName())) {
            addWhereClauseAndParam(where, table, WHERE_OPERATION_TYPE.ILIKE, "first_name", table.getAlias() + "FirstName", filter.getFirstName() + "%", null);
        }
        if (!ObjectUtils.isEmpty(filter.getMiddleName())) {
            addWhereClauseAndParam(where, table, WHERE_OPERATION_TYPE.ILIKE, "second_name", table.getAlias() + "SecondName", filter.getMiddleName() + "%", null);
        }
        if (!ObjectUtils.isEmpty(filter.getLastName())) {
            addWhereClauseAndParam(where, table, WHERE_OPERATION_TYPE.ILIKE, "last_name", table.getAlias() + "LastName", filter.getLastName() + "%", null);
        }

    }
}
