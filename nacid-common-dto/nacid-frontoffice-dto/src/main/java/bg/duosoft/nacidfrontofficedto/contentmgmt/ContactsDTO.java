package bg.duosoft.nacidfrontofficedto.contentmgmt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactsDTO {
    private String address;
    private String infoCenter;
    private String telephone;
    private String fax;
    private String email;
    private String website;
}
