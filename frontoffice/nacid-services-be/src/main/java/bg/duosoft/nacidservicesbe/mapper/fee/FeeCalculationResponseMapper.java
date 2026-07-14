package bg.duosoft.nacidservicesbe.mapper.fee;

import bg.duosoft.nacid.payments.dto.payments.FeeCalculationResponse;
import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationFeeDTO;
import bg.duosoft.nacidfrontofficedto.services.common.application.CalculatedFeesDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 13.04.2023
 * Time: 12:01
 */
@Mapper(componentModel = "spring")
public abstract class FeeCalculationResponseMapper {

    public CalculatedFeesDTO toDto(FeeCalculationResponse response){
        CalculatedFeesDTO calculatedFees = new CalculatedFeesDTO();
        if(response != null) {
            calculatedFees.setForApproval(response.isForApproval());
            calculatedFees.setTotal(fromBigDecimal(response.getTotalAmount()));
            calculatedFees.setCurrencyCode(response.getCurrency() != null? response.getCurrency().getId(): null);
            List<ApplicationFeeDTO> feesResultList = new ArrayList<>();
            if(response.getOriginalFees() != null){
                feesResultList.addAll(response.getOriginalFees().stream().map(of -> toDTO(of)).collect(Collectors.toList()));
            }
            if(response.getAdditions() != null){
                feesResultList.addAll(response.getAdditions().stream().map(add -> toDTO(add)).collect(Collectors.toList()));
            }
            if(response.getDiscounts() != null){
                feesResultList.addAll(response.getDiscounts().stream().map(add -> toDTO(add)).collect(Collectors.toList()));
            }
            calculatedFees.setFees(feesResultList);
        }
        return calculatedFees;
    }

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "feeKey", source = "code")
    @Mapping(target = "feeName", source = "name")
    @Mapping(target = "feeAmount", source = "amount")
    public abstract ApplicationFeeDTO toDTO(FeeCalculationResponse.Fee fee);

    public Double fromBigDecimal(BigDecimal amount){
        if(amount != null) {
            return amount.doubleValue();
        }
        return null;
    }
}
