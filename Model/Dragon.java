package Model;

import java.time.LocalDate;

public class Dragon extends Pet {
    private double smokePollution;

    public Dragon(String id, LocalDate healthCheckDate, int vaccineCount, double smokePollution) {
        super(id, healthCheckDate, vaccineCount);
        this.smokePollution = smokePollution;
    }

    @Override
    public String getType() {
        return "Dragon";
    }

    @Override
    public String getAdditionalInfo() {
        return String.valueOf(smokePollution);
    }
    public boolean validate() {
        return smokePollution <= 70; // ระดับควันไม่เกิน 70% เท่านั้นถึงผ่าน
    }
    
}
