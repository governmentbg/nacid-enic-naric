package bg.duosoft.nacidservicesbe.cloner.entity.base;

import org.mapstruct.IterableMapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.04.2023
 * Time: 17:02
 */
public abstract class BaseCloner<E> {

    public abstract E clone(E source);

    @IterableMapping(
            nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
    )
    public abstract List<E> cloneList(List<E> sourceList);
}
