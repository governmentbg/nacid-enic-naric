package bg.duosoft.nacid.backoffice.abdocs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocActionRequest {
    private Integer docId;
    private Integer type; // 12 - задача, 13 - насочване
	private Boolean addRootDocPermissions; // Освен права по документа, дава права и по преписката на служителите към които е насочена задачата/насочването
    private Integer unitId; // Служител, който създава задачата/насочването
	private Integer fromUnitId; // Ако служителят е упълномощен и работи от името на упълномощителя се слага id на упълномощителя, иначе се слага id на служителя който създава задачата/насочването
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date createDate; // Дата и час в момента
	private String note; //Относно
	private Integer expectedResultId; // Очакван резултат (за какво е задачата/насочването) - номенклатура
	private List<DocUnit> docUnits; // списък с хората, към които е насочена задачата/насочването
}

//	List<DocAction> actions
//	Date assignmentDeadline
//	Boolean isFinished
//	Boolean canCreateAction
//	Boolean hasRead
//	Boolean highPriority