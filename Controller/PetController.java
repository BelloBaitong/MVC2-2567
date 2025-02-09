package Controller;

import Model.*;
import View.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

//ควบคุมการทำงานระหว่าง View และ Model ของระบบสัตว์เวทมนตร์
public class PetController {
    private PetDatabase database; // ฐานข้อมูลสัตว์
    private MainView mainView; // หน้าหลักของระบบ
    private Random random = new Random(); // ใช้สร้าง ID สุ่ม
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy"); // ฟอร์แมตวันที่

//กำหนด EventListener ให้ปุ่มต่างๆ ใน MainView
    public PetController(PetDatabase database, MainView mainView) {
        this.database = database;
        this.mainView = mainView;

        mainView.addPhoenixButtonListener(e -> addPhoenix());
        mainView.addDragonButtonListener(e -> addDragon());
        mainView.addOwlButtonListener(e -> addOwl());
        mainView.addReportButtonListener(e -> new ReportView(database.report()));
        mainView.addExitButtonListener(e -> System.exit(0));
    }

    //สร้าง ID สัตว์แบบสุ่ม

    private String generateId() {
        return String.format("%08d", random.nextInt(90000000) + 10000000);
    }
//แปลงวันที่จาก String เป็น LocalDate
    private LocalDate parseDate(String dateText) {
        try {
            return LocalDate.parse(dateText, dateFormatter);
        } catch (DateTimeParseException e) {
            mainView.showMessage("รูปแบบวันที่ไม่ถูกต้อง! กรุณาใช้รูปแบบ วัน/เดือน/ปี");
            return null;
        }
    }
//แปลงข้อความเป็นจำนวนวัคซีน
    private Integer parseVaccineCount(String vaccineText) {
        try {
            int vaccineCount = Integer.parseInt(vaccineText);
            if (vaccineCount <= 0) throw new NumberFormatException();
            return vaccineCount;
        } catch (NumberFormatException e) {
            mainView.showMessage( "จำนวนวัคซีนต้องเป็นเลขจำนวนเต็มบวก!");
            return null;
        }
    }
//แปลงข้อความเป็นระยะทางบินของนกฮูก
    private Integer parseFlightDistance(String flightText) {
        try {
            int distance = Integer.parseInt(flightText);
            if (distance < 0) throw new NumberFormatException(); 
            return distance;
        } catch (NumberFormatException e) {
            mainView.showErrorMessage("โปรดป้อนค่าระยะทางบินเป็นตัวเลขที่ถูกต้อง!");
            return null;
        }
    }
   // แปลงข้อความเป็นค่ามลพิษของมังกร
    private Double parseSmokePollution(String smokeText) {
        try {
            double pollution = Double.parseDouble(smokeText);
            if (pollution < 0) throw new NumberFormatException(); // ไม่ให้ค่าติดลบ
            return pollution;
        } catch (NumberFormatException e) {
            mainView.showErrorMessage( "โปรดป้อนค่าระดับมลพิษเป็นตัวเลขที่ถูกต้อง!");
            return null;
        }
    }
//ตรวจสอบเงื่อนไขของนกฟินิกซ์
    private boolean validatePhoenix(boolean fireproofCertificate) {
        if (!fireproofCertificate) {
            mainView.showErrorMessage("นกฟินิกซ์ต้องมีใบรับรองไฟไม่ลาม!");
            database.incrementRejectedCount(); 
            database.saveToCSV();
            return false;
        }
        return true;
    }
//ตรวจสอบเงื่อนไขของนกฮูก
    private boolean validateOwl(int flightDistance) {
        if (flightDistance < 100) {
            mainView.showErrorMessage( "นกฮูกต้องบินได้อย่างน้อย 100 km!");
            database.incrementRejectedCount();
            database.saveToCSV();
            return false;
        }
        return true;
    }
//ตรวจสอบเงื่อนไขของมังกร 
    private boolean validateDragon(double smokePollution) {
        if (smokePollution > 70) {
            mainView.showErrorMessage("มังกรต้องมีระดับมลพิษควันไม่เกิน 70%!");
            database.incrementRejectedCount(); 
            database.saveToCSV();
            return false;
        }
        return true;
    }
    
//เพิ่มสัตว์เข้าไปในฐานข้อมูล หากผ่านการตรวจสอบ    
    public void addPet(Pet pet) {
        if (pet.validate()) {
            database.addPet(pet);
            database.incrementAcceptedCount();
            database.saveToCSV();
        } else {
            database.incrementRejectedCount();
        }
    }

//เปิดฟอร์มเพิ่มนกฟินิกซ์ และตรวจสอบข้อมูลก่อนบันทึก
    public void addPhoenix() {
        PhoenixView phoenixView = new PhoenixView();
        phoenixView.addSubmitListener(e -> {
            LocalDate healthCheckDate = parseDate(phoenixView.getHealthCheckDate());
            Integer vaccineCount = parseVaccineCount(phoenixView.getVaccineCount());
            boolean fireproof = phoenixView.getFireproofCertificate();

            if (healthCheckDate == null || vaccineCount == null) return;
            if (!validatePhoenix(fireproof)) return;

            Phoenix phoenix = new Phoenix(generateId(), healthCheckDate, vaccineCount, fireproof);
            addPet(phoenix);
            database.saveToCSV();
            mainView.showMessage(phoenix.validate() ? "นกฟินิกซ์ถูกเพิ่มแล้ว!" : " นกฟินิกซ์ไม่ผ่านเงื่อนไข!");
            phoenixView.dispose();
        });
    }
//เปิดฟอร์มเพิ่มมังกร และตรวจสอบข้อมูลก่อนบันทึก
    public void addDragon() {
        DragonView dragonView = new DragonView();
        dragonView.addSubmitListener(e -> {
            LocalDate healthCheckDate = parseDate(dragonView.getHealthCheckDate());
            Integer vaccineCount = parseVaccineCount(dragonView.getVaccineCount());
            Double smokePollution = parseSmokePollution(dragonView.getSmokePollution());

            if (healthCheckDate == null || vaccineCount == null) return;

            if (!validateDragon(smokePollution)) return;


            Dragon dragon = new Dragon(generateId(), healthCheckDate, vaccineCount, smokePollution);
            addPet(dragon);
            database.saveToCSV();
            mainView.showMessage(dragon.validate() ? "มังกรถูกเพิ่มแล้ว!" : "มังกรไม่ผ่านเงื่อนไข!");
            dragonView.dispose();
        });
    }
//เปิดฟอร์มเพิ่มนกฮูก และตรวจสอบข้อมูลก่อนบันทึก
    public void addOwl() {
        OwlView owlView = new OwlView();
        owlView.addSubmitListener(e -> {
            LocalDate healthCheckDate = parseDate(owlView.getHealthCheckDate());
            Integer vaccineCount = parseVaccineCount(owlView.getVaccineCount());
            Integer flightDistance = parseFlightDistance(owlView.getFlightDistance());


            if (healthCheckDate == null || vaccineCount == null) return;

            if (!validateOwl(flightDistance)) return;

            Owl owl = new Owl(generateId(), healthCheckDate, vaccineCount, flightDistance);
            addPet(owl);
            database.saveToCSV();
            mainView.showMessage(owl.validate() ? "นกฮูกถูกเพิ่มแล้ว!" : "นกฮูกไม่ผ่านเงื่อนไข!");
            owlView.dispose();
        });
    }
}
