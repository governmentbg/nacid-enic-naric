package bg.duosoft.nacidcoreapi.service.nomenclature.base;

import bg.duosoft.nacidcoreapi.repository.nomenclatures.base.NomenclatureBaseRepository;
import bg.duosoft.nacidcoredata.domain.entity.nomenclature.base.NomenclatureEntityBase;
import bg.duosoft.nacidcoredata.mapper.nomenclature.BaseNomenclatureMapper;
import bg.duosoft.nacidfrontofficedto.nomenclature.base.NomenclatureBase;
import bg.duosoft.nacidfrontofficedto.nomenclature.filter.base.BaseNomenclatureFilterDTO;
import bg.duosoft.nacidshareddata.exception.BadRequestException;
import bg.duosoft.nacidshareddata.exception.ResourceNotFoundException;
import bg.duosoft.nacidshareddata.validation.config.BadRequestValidator;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class NomenclatureServiceBaseImpl<ID extends Serializable, D extends NomenclatureBase<ID>, F extends BaseNomenclatureFilterDTO<ID>> implements NomenclatureServiceBase<ID, D, F> {

    protected abstract <E extends NomenclatureEntityBase<ID>> NomenclatureBaseRepository<ID, E, F> getRepository();
    protected abstract <E extends NomenclatureEntityBase<ID>> BaseNomenclatureMapper<E, D> getMapper();

    public List<D> searchRecords(F filter) {
        List<NomenclatureEntityBase<ID>> result = getRepository().searchRecords(filter);
        return getMapper().toDtoList(result);
    }

    public int getRecordsCount(F filter) {
        return getRepository().getRecordsCount(filter);
    }

    public List<D> selectAll(boolean onlyActive) {
        List<NomenclatureEntityBase<ID>> entities = onlyActive ? getRepository().getAllByActiveOrderByNameAsc(1) : getRepository().getAllByOrderByNameAsc();
        return getMapper().toDtoList(entities);
    }

    public D selectById(ID objectId) {
        NomenclatureEntityBase<ID> e = getRepository().findById(objectId).orElse(null);
        return getMapper().toDto(e);
    }

    protected void beforeSave(D dto){

    }
    protected void beforeUpdate(D dto){

    }

    protected void beforeDelete(ID objectId){

    }

    public D save(D dto) {
        if (Objects.isNull(dto)) {
            throw new BadRequestException();
        }
        BadRequestValidator.validateRequest(getValidator(), dto, true, this);
        beforeSave(dto);
        NomenclatureEntityBase<ID> e = getRepository().save(getMapper().toEntity(dto));
        return getMapper().toDto(e);
    }

    public D update(D dto) {
        if (Objects.isNull(dto) || Objects.isNull(dto.getId())) {
            throw new BadRequestException();
        }
        BadRequestValidator.validateRequest(getValidator(), dto, false, this);
        beforeUpdate(dto);
        NomenclatureEntityBase<ID> e = getRepository().save(getMapper().toEntity(dto));
        return getMapper().toDto(e);
    }

    public void delete(ID objectId) {
        NomenclatureEntityBase<ID> e = getRepository().findById(objectId).orElse(null);

        if (Objects.isNull(e)) {
            throw new ResourceNotFoundException();
        }
        beforeDelete(objectId);
        getRepository().delete(e);
    }

    public void deleteAll() {
        getRepository().deleteAll();
    }

}
