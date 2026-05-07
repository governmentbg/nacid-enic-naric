package bg.duosoft.nacidservicesbe.controller.v1;

import bg.duosoft.nacidfrontofficedto.services.common.application.ApplicationNoteDTO;
import bg.duosoft.nacidservicesbe.service.ApplicationNoteService;
import bg.duosoft.nacidservicesbe.utils.swagger.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = Tags.APP_NOTES)
@RestController
@RequestMapping("/api/v1/app-notes")
@RequiredArgsConstructor
@PreAuthorize("hasRole(T(bg.duosoft.nacidcoredata.util.security.SecurityRole).SERVICES_ACCEPT)")
public class ApplicationNoteController {

    private final ApplicationNoteService applicationNoteService;

    @GetMapping("/app/{id}")
    @ApiOperation("Select all notes for application")
    public List<ApplicationNoteDTO> selectAllByApplication(@PathVariable Integer id){
        return applicationNoteService.selectAllByApplication(id);
    }

    @GetMapping("/{id}")
    @ApiOperation("Select application note by id")
    public ApplicationNoteDTO selectById(@PathVariable Integer id){
        return applicationNoteService.selectById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete application note by id")
    public void delete(@PathVariable Integer id) {
        applicationNoteService.delete(id);
    }

    @PostMapping
    @ApiOperation("Create application note")
    public ApplicationNoteDTO create(@RequestBody ApplicationNoteDTO applicationNote) {
       return applicationNoteService.create(applicationNote);
    }

    @PatchMapping
    @ApiOperation(value = "Update application note data")
    public ApplicationNoteDTO update(@RequestBody ApplicationNoteDTO applicationNote) {
        return applicationNoteService.update(applicationNote);
    }
}
