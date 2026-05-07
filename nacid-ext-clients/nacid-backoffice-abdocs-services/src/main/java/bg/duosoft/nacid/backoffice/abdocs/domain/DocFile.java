package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocFile {
    private Integer Id; // Id на файла в нашата база (необходим е, когато трябва да се редактира или трие файл към документ)
    private Integer docId; // Id към кой документ е файла
    private Integer dbId; // Id на базата, в която е записан файла (възможно е да имаме повече от 1 различни бази за запис на файловете)
    private UUID key;
    private String name; // Пълно име на файла
    private DocFileVisibility docFileVisibility; // Дали е публичен или вътрешен файл
    private String description; // Описание
    private boolean isPrimary; // Дали файла е основен (в един документ, само 1 файл може да е основен)
}

// Other fields from doc.java
//    String mimeType; // Вид на файла
//    DocFileType docFileType
//    Integer electronicDocumentId // nullable
//    TextExtractStatus textExtractStatus
//    DocFileTextContent docFileTextContent
//    String textContentSearchResult
//    Byte[] signature
//    Integer insertOrder // nullable
//    String signatureData
//    Date signDate // nullable
//    Boolean isSigned
//    Boolean isActive
//    Boolean isHidden
//    Boolean isCorrupted
//    Boolean manuallyInserted
//    Boolean isSearchResult
//    List<DocFileHistoryRecord> docFileHistoryRecords;
//    List<SignatureInfo> signatureInfos;
//    List<DocFileLink> docFileLinks;