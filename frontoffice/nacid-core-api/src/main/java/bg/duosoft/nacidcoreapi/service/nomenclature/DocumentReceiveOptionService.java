package bg.duosoft.nacidcoreapi.service.nomenclature;

import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveOptionDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 27.01.2023
 * Time: 13:23
 */
public interface DocumentReceiveOptionService {
    List<DocumentReceiveOptionDTO> selectByKind(String kind);
}
