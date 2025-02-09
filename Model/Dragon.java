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
    public double getSmokePollution() {
        return smokePollution;
}
    
}
