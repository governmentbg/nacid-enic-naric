package bg.duosoft.nacidcoredata.util.json.model.contacts;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDataModel {
    private String address;
    private String infoCenter;
    private String telephone;
    private String fax;
    private String email;
    private String website;
}
