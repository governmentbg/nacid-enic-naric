package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocCreation {
    private Integer parentDocId; // Към преписка
    private DocDirection docDirection = DocDirection.Incoming; // Посока на документа
    private ReceivedOriginalState receivedOriginalState; // Получен оригинал
    // = ReceivedOriginalState.WaitingForOriginal.value() - Ако е в изчакване
    // = ReceivedOriginalState.ReceivedOriginal.value() - Ако е получен оригинал
    private Integer docSourceTypeId; // Начин на постъпване в системата (номенклатура)
    private Integer docTypeId; // Вид на документ (номенклатура)
    private String docSubject; // Относно
    private RegistrationDto registration; // Това Dto се използва за начина на регистриране на документа !!! Ако не искате да се регистрира (а да е чернова) трябва да се прати null
    private List<DocCorrespondent> correspondents; // Кореспонденти към документа
    private boolean fromExternalApi = true; // Слагайте я true, за да ни се логва в нашата система, че документа е създаден от външно API
    private boolean docStatusProcessed = true; // За да не се редактира "Получен оригинал", документа трябва да е в различен статус от Чернова.
    // По този начин се създава документа в статус Обработен.
    private List<Attachments> incomingEmailAttachments; // За създаване на файлове към документа
    private DocCaseLinkDO docCaseLink; // Необходимо е за да създаде връзка към IPAS при регистрацията
    private Integer incomingEmailId; // ИД на имейл, от който се регистира документ
    private DocFileVisibility docFileVisibility; // Тип на създадените файлове към документа
    private List<AdditionalDocUnit> additionalDocUnits; // Списък с потребителски имена и техните роли

}

// Additional fields in BPO
//    private boolean createFromDocUnit;// true - да се попълва полето 'От' на базата на потребителя
//    private String expectedResultAlias; // Default - изпълнение, Sign - подписване
//    private DocFileVisibility docFileVisibility; // Тип на създадените файлове към документа
//    private Integer additionalDocUnitsRole; // Ако са подадени additionalDocUnitsUsernames това поле може да смени ролята от ДО на някаква друга. (ОТ - 1, ДО - 2, Въвел - 3 и др.)
//    private List<String> additionalDocUnitsUsernames; // При подаване на списък с потребителски имена в документа се добавят служители с роля До отговарящи на подадените потребителски имена.


// Other fields from doc.java
//    Integer docCreationPreferenceId
//    Integer archiveIndexId
//    Integer docNumbers
//    String corrRegNumber
//    Date corrRegDate
//    Integer tenantId
//    Integer linkCaseId
//    Date dateReceived
//    List<DocFile> docFiles;