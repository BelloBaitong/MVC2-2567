package Model;

import java.time.LocalDate;

public class Owl extends Pet {
    private int flightDistance;

    public Owl(String id, LocalDate healthCheckDate, int vaccineCount, int flightDistance) {
        super(id, healthCheckDate, vaccineCount);
        this.flightDistance = flightDistance;
    }

    @Override
    public String getType() {
        return "Owl";
    }

    @Override
    public String getAdditionalInfo() {
        return String.valueOf(flightDistance);
    }
    public boolean validate() {
        return flightDistance >= 100; // บินได้เกิน 100 km เท่านั้นถึงผ่าน
    }
    
}
