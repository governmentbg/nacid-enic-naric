package bg.duosoft.nacid.backoffice.core.data.domain.rest.common;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
public class InitialConstraintDTO {
    private List<Tab> tabs;
    private boolean isAllAccomplished;

    private InitialConstraintDTO(List<Tab> tabs) {
        this.tabs = tabs;
    }

    public static InitialConstraintDTO newInstance() {
        return new InitialConstraintDTO(new ArrayList<>());
    }

    public void fillIsAllAccomplished() {
        boolean allAccomplished = true;

        for (Tab tab : this.tabs) {
            if (!allAccomplished) {
                break;
            }
                List<Constraint> constraintList = tab.getConstraintList();
                if (Objects.nonNull(constraintList) && constraintList.size() > 0) {
                    for (Constraint constraint : constraintList) {
                        if (Objects.nonNull(constraint) && !constraint.accomplished) {
                            allAccomplished = false;
                            break;
                        }
                    }
                }
        }

        isAllAccomplished = allAccomplished;
    }

    @Getter
    @Setter
    public static class Tab {
        private String name;
        private List<Constraint> constraintList;

        private Tab(List<Constraint> constraintList, String name) {
            this.constraintList = constraintList;
            this.name = name;
        }

        public static Tab newInstance(String name) {
            return new Tab(new ArrayList<>(), name);
        }

        public void addConstraint(Constraint constraint) {
            this.constraintList.add(constraint);
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Constraint {
        private boolean accomplished;
        private String message;
    }
}


