package bg.duosoft.nacidcoreclient.client.contentManagement;

import bg.duosoft.nacidfrontofficedto.contentmgmt.ContentManagementDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 29.06.2022
 * Time: 17:53
 */
public interface BaseContentManagementClient {

    @GetMapping("/{id}")
    String selectById(@PathVariable("id") String id);

    @GetMapping
    public List<ContentManagementDTO> selectByType(@RequestParam("type") String type);

    @PutMapping("/{id}")
    ContentManagementDTO update(@PathVariable("id") String id, @RequestBody String data);
}
