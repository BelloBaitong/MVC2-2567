package Model;

import java.time.LocalDate;

//คลาส Dragon แทนข้อมูลของมังกร ซึ่งเป็นสัตว์เลี้ยงเวทมนตร์ชนิดหนึ่ง

public class Dragon extends Pet {
    private double smokePollution; //ปริมาณควันพิษที่ปล่อยออกมา

    //คอนสตรักเตอร์สำหรับสร้างมังกร
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
