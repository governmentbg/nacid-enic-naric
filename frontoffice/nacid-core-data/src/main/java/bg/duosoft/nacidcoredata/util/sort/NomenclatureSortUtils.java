package bg.duosoft.nacidcoredata.util.sort;

import bg.duosoft.nacidfrontofficedto.utils.constants.NomenclatureSortFields;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.09.2022
 * Time: 17:53
 */
public class NomenclatureSortUtils {

    public static String getQuerySortField(String sort){
        String querySort = switch (sort){
            case NomenclatureSortFields.ID -> "r.id";
            case NomenclatureSortFields.ID_PK -> "r.pk.id";
            case NomenclatureSortFields.NAME -> "r.name";
            case NomenclatureSortFields.ACTIVE -> "r.active";
            case NomenclatureSortFields.OFFICIAL_NAME -> "r.officialName";
            case NomenclatureSortFields.DOCUMENT_RECIPIENT -> "r.documentRecipient";
            case NomenclatureSortFields.SETTLEMENT_CODE -> "r.settlementCode";
            case NomenclatureSortFields.ADDRESS -> "r.address";
            case NomenclatureSortFields.ZIP_CODE -> "r.zipCode";
            case NomenclatureSortFields.WEBSITE -> "r.website";
            case NomenclatureSortFields.INDEX -> "r.index";
            case NomenclatureSortFields.DOMAIN -> "r.pk.domain";
            case NomenclatureSortFields.EDUCATION_AREA -> "r.educationArea.pk.id";
            case NomenclatureSortFields.COUNTRY_CODE -> "r.countryCode.code";
            case NomenclatureSortFields.COUNTRY_NAME -> "r.countryCode.name";
            case NomenclatureSortFields.EDUCATION_LEVEL -> "r.educationLevel.pk.id";
            case NomenclatureSortFields.SETTLEMENT -> "r.settlement.name";
            default -> "r.id";
        };
        return querySort;
    }
}
