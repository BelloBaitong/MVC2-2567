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
public int getFlightDistance() {
    return flightDistance;
}
    
}
