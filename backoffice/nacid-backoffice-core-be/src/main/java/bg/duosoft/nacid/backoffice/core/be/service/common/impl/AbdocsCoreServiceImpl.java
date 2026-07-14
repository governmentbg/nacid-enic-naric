package bg.duosoft.nacid.backoffice.core.be.service.common.impl;

import bg.duosoft.nacid.backoffice.abdocs.domain.Doc;
import bg.duosoft.nacid.backoffice.abdocs.domain.DocFile;
import bg.duosoft.nacid.backoffice.abdocs.domain.response.DownloadFileResponse;
import bg.duosoft.nacid.backoffice.abdocs.service.main.AbdocsAdminService;
import bg.duosoft.nacid.backoffice.core.be.service.common.AbdocsCoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbdocsCoreServiceImpl implements AbdocsCoreService {

    private final AbdocsAdminService abdocsAdminService;

    @Override
    public Doc selectAbdocsDoc(Integer docflowId) {
        Doc abdocsDoc = abdocsAdminService.getDocumentById(docflowId);
        if (Objects.isNull(abdocsDoc)) {
            throw new RuntimeException("Cannot find abdocs document with ID = " + docflowId);
        }

        return abdocsDoc;
    }

    @Override
    public DocFile selecAbdocsDocFile(Integer docflowId, Integer fileId) {
        Doc abdocsDoc = abdocsAdminService.getDocumentById(docflowId);
        if (Objects.isNull(abdocsDoc)) {
            throw new RuntimeException("Cannot find abdocs document with ID = " + docflowId);
        }

        List<DocFile> docFiles = abdocsDoc.getDocFiles();
        if (CollectionUtils.isEmpty(docFiles)) {
            throw new RuntimeException("Empty docFiles list! Cannot find abdocs document file with ID = " + fileId);
        }

        return docFiles.stream().filter(e -> e.getId().equals(fileId)).findFirst().orElseThrow(() -> new RuntimeException("Cannot find abdocs document file with ID = " + fileId));
    }

    @Override
    public DownloadFileResponse getAbdocsDownloadFile(DocFile docFile) {
        if (Objects.isNull(docFile)) {
            throw new RuntimeException("Empty docFile! Cannot download abdocs docFile!");
        }

        return abdocsAdminService.downloadFile(docFile.getKey().toString(), docFile.getName(), docFile.getDbId());
    }

}
