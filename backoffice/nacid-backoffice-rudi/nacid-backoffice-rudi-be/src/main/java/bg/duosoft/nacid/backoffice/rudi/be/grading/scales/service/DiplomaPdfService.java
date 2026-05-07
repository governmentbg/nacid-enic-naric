package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.service;

import bg.duosoft.nacid.backoffice.rudi.be.grading.scales.dto.request.DiplomaDetailsDto;
import org.springframework.http.ResponseEntity;

public interface DiplomaPdfService {

    ResponseEntity<byte[]> createPdfFile(DiplomaDetailsDto diplomaDetailsDto);
}