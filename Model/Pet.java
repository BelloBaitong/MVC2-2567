package Model;

import java.time.LocalDate;

public abstract class Pet {
    protected String id;
    protected LocalDate healthCheckDate;
    protected int vaccineCount;

    public Pet(String id, LocalDate healthCheckDate, int vaccineCount) {
        this.id = id;
        this.healthCheckDate = healthCheckDate;
        this.vaccineCount = vaccineCount;
    }

    public abstract boolean validate();

    public abstract String getType();

    public abstract String getAdditionalInfo();

    // เมธอดสำหรับบันทึกลง CSV
    public String toCSV() {
        return id + "," + getType() + "," + healthCheckDate + "," + vaccineCount + "," + getAdditionalInfo();
    }
}
