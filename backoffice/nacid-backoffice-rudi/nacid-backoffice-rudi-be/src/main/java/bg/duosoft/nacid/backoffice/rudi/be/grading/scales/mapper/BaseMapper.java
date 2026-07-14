package bg.duosoft.nacid.backoffice.rudi.be.grading.scales.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

public abstract class BaseMapper<E, D> {
    public abstract D toDto(E e);

    @IterableMapping(
            nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
    )
    public abstract List<D> toDtoList(List<E> eList);

    @InheritInverseConfiguration(name = "toDto")
    public abstract E toEntity(D d);

    @IterableMapping(
            nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
    )
    @InheritInverseConfiguration(name = "toDtoList")
    public abstract List<E> toEntityList(List<D> dList);


}
