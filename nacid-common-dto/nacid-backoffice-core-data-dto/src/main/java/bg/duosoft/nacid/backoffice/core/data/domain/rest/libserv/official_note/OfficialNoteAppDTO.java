package bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.official_note;

import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.LibservAppDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.libserv.LibservObject;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.ReferenceDataDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfficialNoteAppDTO implements Serializable, LibservObject {

    private LibservAppDTO libservApp;
    private ReferenceDataDTO kind;
    private String detailedInformation;
    private String dissertField;
    private String akadField;
    private String nirField;
    private String firstApplicant;
    private String lastApplicant;
    private String draftTitle;
    private String draftLength;
    private String draftPresented;
    private String draftNum;
    private String draftApplicant;
    private String draftProtocol;
    private String draftDate;

    public OfficialNoteAppDTO(LibservAppDTO libservApp) {
        this.libservApp = libservApp;
    }

}
