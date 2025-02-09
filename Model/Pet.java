package Model;

import java.time.LocalDate;

//คลาสนามธรรม Pet แทนสัตว์เลี้ยงเวทมนตร์แต่ละประเภท

public abstract class Pet {
    protected String id;// รหัสสัตว์เลี้ยง
    protected LocalDate healthCheckDate; // วันที่ตรวจสุขภาพ
    protected int vaccineCount; // จำนวนวัคซีนที่ได้รับ

    //คอนสตรักเตอร์สำหรับกำหนดข้อมูลสัตว์เลี้ยง
    public Pet(String id, LocalDate healthCheckDate, int vaccineCount) {
        this.id = id;
        this.healthCheckDate = healthCheckDate;
        this.vaccineCount = vaccineCount;
    }
    //ตรวจสอบความถูกต้องของข้อมูลสัตว์
    public boolean validate() {
        return true; 
    }
    
    public abstract String getType();

    public abstract String getAdditionalInfo();

    //แปลงข้อมูลสัตว์ให้อยู่ในรูปแบบ CSV
    public String toCSV() {
        return id + "," + getType() + "," + healthCheckDate + "," + vaccineCount + "," + getAdditionalInfo();
    }
}
