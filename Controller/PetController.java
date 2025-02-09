package Controller;

import Model.*;
import View.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import javax.swing.JOptionPane;

public class PetController {
    private PetDatabase database;
    private MainView mainView;
    private Random random = new Random();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy"); // ฟอร์แมตวันที่

    private int acceptedCount = 0; // ตัวแปรนับสัตว์ที่รับเข้า
    private int rejectedCount = 0; // ตัวแปรนับสัตว์ที่ถูกปฏิเสธ


    public PetController(PetDatabase database, MainView mainView) {
        this.database = database;
        this.mainView = mainView;

        mainView.addPhoenixButtonListener(e -> addPhoenix());
        mainView.addDragonButtonListener(e -> addDragon());
        mainView.addOwlButtonListener(e -> addOwl());
        mainView.addReportButtonListener(e -> new ReportView(database.report()));
        mainView.addExitButtonListener(e -> System.exit(0));
    }

    private String generateId() {
        return String.format("%08d", random.nextInt(90000000) + 10000000);
    }

    private LocalDate parseDate(String dateText) {
        try {
            return LocalDate.parse(dateText, dateFormatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "รูปแบบวันที่ไม่ถูกต้อง! กรุณาใช้รูปแบบ วัน/เดือน/ปี");
            return null;
        }
    }

    private Integer parseVaccineCount(String vaccineText) {
        try {
            int vaccineCount = Integer.parseInt(vaccineText);
            if (vaccineCount <= 0) throw new NumberFormatException();
            return vaccineCount;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "จำนวนวัคซีนต้องเป็นเลขจำนวนเต็มบวก!");
            return null;
        }
    }

    private Integer parseFlightDistance(String flightText) {
        try {
            int distance = Integer.parseInt(flightText);
            if (distance < 0) throw new NumberFormatException(); 
            return distance;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "โปรดป้อนค่าระยะทางบินเป็นตัวเลขที่ถูกต้อง!", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private Double parseSmokePollution(String smokeText) {
        try {
            double pollution = Double.parseDouble(smokeText);
            if (pollution < 0) throw new NumberFormatException(); // ไม่ให้ค่าติดลบ
            return pollution;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "โปรดป้อนค่าระดับมลพิษเป็นตัวเลขที่ถูกต้อง!", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private boolean validatePhoenix(boolean fireproofCertificate) {
        if (!fireproofCertificate) {
            JOptionPane.showMessageDialog(null, "นกฟินิกซ์ต้องมีใบรับรองไฟไม่ลาม!", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
            database.incrementRejectedCount(); 
            database.saveToCSV();
            return false;
        }
        return true;
    }

    private boolean validateOwl(int flightDistance) {
        if (flightDistance < 100) {
            JOptionPane.showMessageDialog(null, "นกฮูกต้องบินได้อย่างน้อย 100 km!", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
            database.incrementRejectedCount();
            database.saveToCSV();
            return false;
        }
        return true;
    }
    
    private boolean validateDragon(double smokePollution) {
        if (smokePollution > 70) {
            JOptionPane.showMessageDialog(null, "มังกรต้องมีระดับมลพิษควันไม่เกิน 70%!", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
            database.incrementRejectedCount(); 
            database.saveToCSV();
            return false;
        }
        return true;
    }
    
    public int getAcceptedCount() {
        return acceptedCount;
    }

    public int getRejectedCount() {
        return rejectedCount;
    }
    
    public void addPet(Pet pet) {
        if (pet.validate()) {
            database.addPet(pet);
            database.incrementAcceptedCount();
            database.saveToCSV();
        } else {
            database.incrementRejectedCount();
        }
    }


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

    public void addOwl() {
        OwlView owlView = new OwlView();
        owlView.addSubmitListener(e -> {
            LocalDate healthCheckDate = parseDate(owlView.getHealthCheckDate());
            Integer vaccineCount = parseVaccineCount(owlView.getVaccineCount());
            Integer flightDistance = parseFlightDistance(owlView.getFlightDistance());


            if (healthCheckDate == null || vaccineCount == null) return;

            if (!validateOwl(flightDistance)) return;

            Owl owl = new Owl(generateId(), healthCheckDate, vaccineCount, flightDistance);
            database.addPet(owl);
            database.saveToCSV();
            mainView.showMessage(owl.validate() ? "นกฮูกถูกเพิ่มแล้ว!" : "นกฮูกไม่ผ่านเงื่อนไข!");
            owlView.dispose();
        });
    }
}
