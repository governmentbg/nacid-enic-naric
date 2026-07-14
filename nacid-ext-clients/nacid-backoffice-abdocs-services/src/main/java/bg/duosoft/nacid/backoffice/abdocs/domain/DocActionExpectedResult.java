package bg.duosoft.nacid.backoffice.abdocs.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum DocActionExpectedResult {
    WithoutResult(1, Collections.singletonList(DocActionType.Task)),
    Registration(2, Collections.singletonList(DocActionType.Task)),
    Sign(3, List.of(DocActionType.Task)),
    ExecutionTask(4, Collections.singletonList(DocActionType.Task)),
    ExecutionTargeting(5, Collections.singletonList(DocActionType.Targeting)),
    Coordination(10, Arrays.asList(DocActionType.Task, DocActionType.Targeting)),
    ;

    DocActionExpectedResult(int value, List<DocActionType> docActionTypes) {
        this.value = value;
        this.docActionTypes = docActionTypes;
    }

    private final int value;
    private final List<DocActionType> docActionTypes;

    public int value() {
        return this.value;
    }

    public List<DocActionType> docActionTypes() {
        return this.docActionTypes;
    }

}