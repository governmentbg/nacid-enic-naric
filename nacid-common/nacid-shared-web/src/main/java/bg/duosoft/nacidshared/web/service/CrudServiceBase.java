package bg.duosoft.nacidshared.web.service;

import java.io.Serializable;
import java.util.List;

/**
 * User: ggeorgiev
 * Date: 03.10.2022
 * Time: 15:34
 */
public interface CrudServiceBase<ID extends Serializable, DTO> {
    public List<DTO> selectAll();
    public DTO selectById(ID objectId);

    public DTO create(DTO dto);

    public DTO update(DTO dto);

    public void delete(ID objectId);
}
