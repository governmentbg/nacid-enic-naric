package bg.duosoft.nacidservicesbe.validation.common;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidservicesbe.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 31.03.2023
 * Time: 14:51
 */
@Component
@RequiredArgsConstructor
public class FileValidator {

    private final FileService fileService;

    public boolean fileIsValid(FileStoreEntryDTO file){
        if(file == null){
            return false;
        }
        if(!StringUtils.hasText(file.getFileId())
                || !StringUtils.hasText(file.getRelativePath())
                || !StringUtils.hasText(file.getRootDirectory())) {
            return false;
        }

        if(!fileService.fileExists(file.getRootDirectory(), file.getRelativePath(), file.getFileId())){
            return false;
        }

        return true;
    }

}
