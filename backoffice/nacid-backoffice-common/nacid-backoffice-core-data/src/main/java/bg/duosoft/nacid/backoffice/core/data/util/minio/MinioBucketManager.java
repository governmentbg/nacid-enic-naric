package bg.duosoft.nacid.backoffice.core.data.util.minio;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AttachmentDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.enums.ApplicationType;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ApplicationTypeDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidshareddata.util.DefaultValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
public class MinioBucketManager {
    private static final String APPLICATIONS_PATTERN = "applications/{year}/{month}/{appNumber}";

    public static final String RUDI_BUCKET_NAME = "rudi";
    public static final String REGPROF_BUCKET_NAME = "regprof";
    public static final String LIBSERV_BUCKET_NAME = "libserv";
    public static final String SE_BUCKET_NAME = "secondary";

    public static String getFileId(AttachmentDTO attachment) {
        if (Objects.isNull(attachment)) {
            throw new RuntimeException("[MinioBucketManager] Cannot get file id, because attachment object is empty !");
        }

        String fileLocation = attachment.getFileLocation();
        if (!StringUtils.hasText(fileLocation)) {
            throw new RuntimeException("[MinioBucketManager] Attachment file location is empty ! Attachment ID: " + attachment.getId());
        }

        return fileLocation.substring(fileLocation.lastIndexOf(DefaultValue.MINIO_SEPARATOR) + 1);
    }

    public static String getRelativePath(AttachmentDTO attachment) {
        if (Objects.isNull(attachment)) {
            throw new RuntimeException("[MinioBucketManager] Cannot get relative path, because attachment object is empty !");
        }

        String fileLocation = attachment.getFileLocation();
        if (!StringUtils.hasText(fileLocation)) {
            throw new RuntimeException("[MinioBucketManager] Attachment file location is empty ! Attachment ID: " + attachment.getId());
        }

        return fileLocation.replace(DefaultValue.MINIO_SEPARATOR + getFileId(attachment), "");
    }

    public static void setOriginalPath(List<AttachmentDTO> attachments, ApplicationDTO application) {
        if (!CollectionUtils.isEmpty(attachments)) {
            attachments.forEach(a -> {
                a.setBucketName(selectBucketName(application));
                a.setFileLocation(buildAppsRelativePath(application) + DefaultValue.MINIO_SEPARATOR + getFileId(a));
            });
        }
    }

    public static String buildAppsPath(ApplicationDTO application, String baseBucket) {
        if (!StringUtils.hasText(baseBucket)) {
            throw new RuntimeException("[MinioBucketManager] Base bucket is empty !");
        }

        String relativePath = buildAppsRelativePath(application);
        return baseBucket + DefaultValue.MINIO_SEPARATOR + relativePath;
    }

    public static String buildAppsRelativePath(ApplicationDTO application) {
        if (Objects.isNull(application)) {
            throw new RuntimeException("[MinioBucketManager] Application is empty !");
        }

        return buildAppsRelativePath(application.getEntryNumber(), application.getEntryDate(), application.getId());
    }

    public static String buildAppsRelativePath(String entryNumber, LocalDate entryDate, Integer appId) {
        if (!StringUtils.hasText(entryNumber)) {
            throw new RuntimeException("[MinioBucketManager] Entry number is empty ! App ID: " + appId);
        }

        if (Objects.isNull(entryDate)) {
            throw new RuntimeException("[MinioBucketManager] Created date is empty ! App ID: " + appId);
        }

        String entryNumberRevised = entryNumber.replaceAll("[/\\\\ ]+", "_");
        return APPLICATIONS_PATTERN
                .replace("{year}", String.valueOf(entryDate.getYear()))
                .replace("{month}", String.valueOf(entryDate.getMonthValue()))
                .replace("{appNumber}", entryNumberRevised);
    }

    public static String selectBucketName(ApplicationDTO application) {
        ApplicationTypeDTO applicationType = application.getApplicationType();
        if (Objects.isNull(applicationType) || !StringUtils.hasText(applicationType.getId())) {
            throw new RuntimeException("[MinioBucketManager] Application type is empty ! App ID: " + application.getId());
        }

        ApplicationType type = ApplicationType.selectByCode(applicationType.getId());
        return switch (type) {
            case RUDI -> RUDI_BUCKET_NAME;
            case REGPROF -> REGPROF_BUCKET_NAME;
            case LIBSERV -> LIBSERV_BUCKET_NAME;
            case SE_RECOGNITION -> SE_BUCKET_NAME;
            default -> throw new RuntimeException("[MinioBucketManager] Unknown application type ! App ID: " + application.getId());
        };
    }

    public static MinioFilePath selectMinioFilePath(AttachmentDTO attachment) {
        return selectMinioFilePath(attachment.getFileLocation(), attachment.getBucketName());
    }

    public static MinioFilePath selectMinioFilePath(String fileLocation, String bucket) {
        String relativePath = fileLocation.substring(0, fileLocation.lastIndexOf(FileConstants.PATH_SEPARATOR));
        String fileId = fileLocation.substring(fileLocation.lastIndexOf(FileConstants.PATH_SEPARATOR) + 1);
        return MinioFilePath.of(bucket, relativePath, fileId);
    }

}
