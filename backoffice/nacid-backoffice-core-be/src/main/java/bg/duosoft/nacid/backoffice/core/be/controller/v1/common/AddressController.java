package bg.duosoft.nacid.backoffice.core.be.controller.v1.common;

import bg.duosoft.nacid.backoffice.core.be.service.common.AddressService;
import bg.duosoft.nacid.backoffice.core.be.util.swagger.Tags;
import bg.duosoft.nacid.backoffice.core.be.validation.common.AddressValidator;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.Sortable;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.AddressDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.filter.AddressFilterDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.nomenclatures.filter.sort.NomenclatureSortFields;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


//TODO Edit role for
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = Tags.PERSONS)
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressValidator addressValidator;

    @GetMapping({"/{id}"})
    @ApiOperation("Select address by id")
    public AddressDTO getById(@PathVariable("id") Integer id) {
        AddressDTO result = addressService.selectById(id);
        if (Objects.isNull(result)) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @PostMapping({"/search-for-applications-use"})
    @ApiOperation("Search addresses for applications use")
    public List<AddressDTO> searchAddressesForApplicationsUse(@RequestBody AddressFilterDTO filter) {
        if (Objects.isNull(filter.getAddressType())) {
            throw new ResourceNotFoundException("Address type field is not present !");
        }

        setDefaultFilterData(filter);
        return searchRecords(filter);
    }

    @PostMapping({"/search"})
    @ApiOperation("Search addresses")
    public List<AddressDTO> searchRecords(@RequestBody AddressFilterDTO filter) {
        List<AddressDTO> result = addressService.searchRecords(filter);
        if (CollectionUtils.isEmpty(result)) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @PutMapping
    @ApiOperation(value = "Save address")
    public AddressDTO save(@RequestBody AddressDTO requestData) {
        AddressDTO save = addressService.save(requestData, addressValidator);
        if (Objects.isNull(save)) {
            throw new ResourceNotFoundException();
        }
        return save;
    }

    private static void setDefaultFilterData(AddressFilterDTO filter) {
        if (Objects.nonNull(filter)) {
            filter.setPage(1);
            filter.setPageSize(50);
            filter.setOrderBy(NomenclatureSortFields.COUNTRY_NAME_CITY_NAME_LEFT_JOINED);
            filter.setOrder(Sortable.ASC_ORDER);
        }
    }
}
