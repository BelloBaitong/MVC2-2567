package Model;

import java.time.LocalDate;

public class Phoenix extends Pet {
    private boolean fireproofCertificate;

    public Phoenix(String id, LocalDate healthCheckDate, int vaccineCount, boolean fireproofCertificate) {
        super(id, healthCheckDate, vaccineCount);
        this.fireproofCertificate = fireproofCertificate;
    }

    @Override
    public String getType() {
        return "Phoenix";
    }

    @Override
    public String getAdditionalInfo() {
        return String.valueOf(fireproofCertificate);
    }
    public boolean validate() {
        return fireproofCertificate; // ต้องมีใบรับรองไฟไม่ลามเท่านั้นถึงจะผ่าน
    }
}

