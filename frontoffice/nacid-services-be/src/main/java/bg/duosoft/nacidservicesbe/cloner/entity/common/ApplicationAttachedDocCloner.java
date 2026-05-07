package bg.duosoft.nacidservicesbe.cloner.entity.common;

import bg.duosoft.nacidfrontofficedto.file.FileStoreEntryDTO;
import bg.duosoft.nacidminiodto.util.FileConstants;
import bg.duosoft.nacidservicesbe.domain.entity.common.ApplicationAttachedDocEntity;
import bg.duosoft.nacidservicesbe.domain.entity.common.AttachmentEntity;
import bg.duosoft.nacidservicesbe.service.FileService;
import bg.duosoft.nacidservicesbe.utils.FileRelativePathUtils;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 07.04.2023
 * Time: 15:25
 */
@Mapper(componentModel = "spring")
public abstract class ApplicationAttachedDocCloner {

    @Autowired
    private FileService fileService;

    public List<ApplicationAttachedDocEntity> cloneList(List<ApplicationAttachedDocEntity> source, Integer applicationId, LocalDate createdDate){
        if(source != null){
            return source.stream().map(s -> clone(s, applicationId, createdDate)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public ApplicationAttachedDocEntity clone(ApplicationAttachedDocEntity source, Integer applicationId, LocalDate createdDate){
        ApplicationAttachedDocEntity cloned = clone(source);
        AttachmentEntity attachmentCloned = new AttachmentEntity();
        attachmentCloned.setFileName(source.getAttachment().getFileName());
        FileStoreEntryDTO originalFile = fileService.getFileDetailsAndContent(source.getAttachment().getRootDirectory(), source.getAttachment().getRelativePath(), source.getAttachment().getFileId());
        FileStoreEntryDTO clonedFile = fileService.moveFileToPersistentStore(FileConstants.SERVICES_ROOT_DIRECTORY, FileRelativePathUtils.createRelativeFilePath(applicationId, createdDate), false, originalFile);
        attachmentCloned.setRootDirectory(clonedFile.getRootDirectory());
        attachmentCloned.setRelativePath(clonedFile.getRelativePath());
        attachmentCloned.setFileId(clonedFile.getFileId());
        cloned.setAttachment(attachmentCloned);
        return cloned;
    }

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attachment", ignore = true)
    public abstract ApplicationAttachedDocEntity clone(ApplicationAttachedDocEntity source);

}
