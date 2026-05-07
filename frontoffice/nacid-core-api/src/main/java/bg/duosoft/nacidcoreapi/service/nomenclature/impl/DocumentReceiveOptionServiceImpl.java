package bg.duosoft.nacidcoreapi.service.nomenclature.impl;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.DocumentReceiveOptionRepository;
import bg.duosoft.nacidcoreapi.service.nomenclature.DocumentReceiveOptionService;
import bg.duosoft.nacidcoredata.mapper.nomenclature.DocumentReceiveOptionMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.DocumentReceiveOptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentReceiveOptionServiceImpl implements DocumentReceiveOptionService {

    private final DocumentReceiveOptionRepository documentReceiveOptionRepository;
    private final DocumentReceiveOptionMapper documentReceiveOptionMapper;


    @Override
    public List<DocumentReceiveOptionDTO> selectByKind(String kind) {
        return documentReceiveOptionMapper.toDtoList(documentReceiveOptionRepository.selectByKind(kind));
    }
}
