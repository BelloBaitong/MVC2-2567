package Model;

import java.time.LocalDate;

// คลาส Owl แทนข้อมูลของนกฮูก ซึ่งเป็นสัตว์เลี้ยงเวทมนตร์ชนิดหนึ่ง
public class Owl extends Pet {
    private int flightDistance; // ระยะทางการบินสูงสุด

    //คอนสตรักเตอร์สำหรับสร้างนกฮูก
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
