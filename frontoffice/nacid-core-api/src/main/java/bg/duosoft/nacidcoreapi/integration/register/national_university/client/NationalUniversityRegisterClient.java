package bg.duosoft.nacidcoreapi.integration.register.national_university.client;

import bg.duosoft.nacidcoreapi.integration.register.national_university.domain.NationalUniversityRequest;
import bg.duosoft.nacidcoreapi.integration.register.national_university.domain.NationalUniversityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "NationalUniversityRegisterClient", url = "${feign.integration.registers.national-universities}")
public interface NationalUniversityRegisterClient {

    @PostMapping
    NationalUniversityResponse selectNationalUniversitiesInfo(@RequestBody NationalUniversityRequest request);
}
