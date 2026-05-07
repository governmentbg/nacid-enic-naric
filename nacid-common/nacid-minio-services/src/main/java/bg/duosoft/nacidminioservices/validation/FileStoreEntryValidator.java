package bg.duosoft.nacidminioservices.validation;



import bg.duosoft.nacidminiodto.FileStoreEntryBaseDTO;
import bg.duosoft.nacidminioservices.property.FileConfig;
import bg.duosoft.nacidminioservices.property.FileConfigProperties;
import bg.duosoft.nacidshareddata.util.MimeTypeUtils;
import bg.duosoft.nacidshareddata.validation.config.ValidationError;
import bg.duosoft.nacidshareddata.validation.config.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.06.2022
 * Time: 17:07
 */
@Slf4j
@Component
public class FileStoreEntryValidator implements Validator<FileStoreEntryBaseDTO> {

    @Autowired
    private FileConfigProperties fileConfigProperties;

    @Override
    public List<ValidationError> validate(FileStoreEntryBaseDTO fileStoreEntry, Object... args) {
        List<ValidationError> validationErrors = new ArrayList<>();
        FileConfig fileConfig = fileConfigProperties.getFileGroupConfig().get((String)args[0]);
        String pointer =  (String)args[1];

        if(fileConfig == null){
            log.error("Bad file group "+args[0]);
            throw new RuntimeException("Bad file group "+args[0]);
        }

        rejectIfEmptyString(validationErrors, fileStoreEntry.getRootDirectory(), pointer, "validation.bad.fileStoreEntry.rootDirectory");
        rejectIfEmptyString(validationErrors, fileStoreEntry.getRelativePath(), pointer, "validation.bad.fileStoreEntry.relativePath");
        rejectIfEmptyString(validationErrors, fileStoreEntry.getContentType(), pointer, "validation.bad.fileStoreEntry.contentType");

        if(fileConfig.getMaxSize() > -1 && fileConfig.getMaxSize() < fileStoreEntry.getContent().length){
            validationErrors.add(ValidationError.builder().pointer(pointer).message("validation.file.size").params(Map.of("allowedFileSize", fileConfig.getMaxSizeInMb().toPlainString())).build());
        }
        String guessedMimeFromBytes = MimeTypeUtils.guessMimeFromBytes(fileStoreEntry.getContent(), fileStoreEntry.getFileName());
        if(StringUtils.hasText(fileConfig.getAllowedTypes()) && !fileConfig.getAllowedTypes().contains(guessedMimeFromBytes)){
            validationErrors.add(ValidationError.builder().pointer(pointer).message("validation.file.type").params(Map.of("allowedFileExtensions", String.join(",", fileConfig.getExtensions()))).build());
        }
        if(!MimeTypeUtils.areMimesEquivalent(fileStoreEntry.getContentType(), guessedMimeFromBytes)){
            validationErrors.add(ValidationError.builder().pointer(pointer).message("validation.file.type.mismatch").build());
        }
        if (ObjectUtils.isEmpty(fileStoreEntry.getFileName()) || fileStoreEntry.getFileName().length() > 128) {
            validationErrors.add(ValidationError.builder().pointer(pointer).message("validation.file.name").params(Map.of("allowedLength", "128")).build());
        }
        return validationErrors;
    }
}
