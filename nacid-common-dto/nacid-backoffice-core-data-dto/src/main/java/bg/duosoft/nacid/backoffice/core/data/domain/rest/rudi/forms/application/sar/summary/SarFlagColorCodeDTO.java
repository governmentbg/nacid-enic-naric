package bg.duosoft.nacid.backoffice.core.data.domain.rest.rudi.forms.application.sar.summary;

import lombok.*;

@Data
public class SarFlagColorCodeDTO {

    private FlagData statute;
    private FlagData authenticity;
    private FlagData recommendation;

    private SarFlagColorCodeDTO() {
        this.statute = new FlagData();
        this.authenticity = new FlagData();
        this.recommendation = new FlagData();
    }

    public static SarFlagColorCodeDTO newInstance() {
        return new SarFlagColorCodeDTO();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FlagData {
        private String name;
        private Color color;
    }

    public enum Color {
        BLUE, RED, GREY, GREEN
    }

}
