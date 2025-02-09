package Model;

import java.time.LocalDate;

//คลาส Phoenix แทนข้อมูลของนกฟินิกซ์ ซึ่งเป็นสัตว์เลี้ยงชนิดหนึ่ง

public class Phoenix extends Pet {
    private boolean fireproofCertificate; // ใบรับรองป้องกันไฟไหม้

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
    public boolean getFireproofCertificate() {
    return fireproofCertificate;
}
}

