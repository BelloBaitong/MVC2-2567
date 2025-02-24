package Model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//ใช้สำหรับจัดเก็บข้อมูลสัตว์เวทมนตร์และบันทึก/โหลดข้อมูลจากไฟล์ CSV
public class PetDatabase {
    private List<Pet> pets;// รายการสัตว์ทั้งหมด
    private int acceptedCount = 0;  //ตัวแปรนับสัตว์ที่รับเข้า
    private int rejectedCount = 0;  //ตัวแปรนับสัตว์ที่ถูกปฏิเสธ
    private static final String CSV_FILE = "pets_data.csv"; // ไฟล์เก็บข้อมูล

    //คอนสตรักเตอร์ โหลดข้อมูลสัตว์จาก CSV หากมีไฟล์อยู่
    public PetDatabase() {
        this.pets = new ArrayList<>();
        loadFromCSV(); 
    }
    //เพิ่มจำนวนสัตว์ที่รับเข้า
    public void incrementAcceptedCount() {
        acceptedCount++; 
    }
    //เพิ่มจำนวนสัตว์ที่ถูกปฏิเสธ
    public void incrementRejectedCount() {
        rejectedCount++; 
    }
    //เพิ่มสัตว์ลงฐานข้อมูล และบันทึกลง CSV
    public void addPet(Pet pet) {
        pets.add(pet); //เก็บข้อมูลสัตว์
        saveToCSV();   //บันทึกลงไฟล์ CSV
    }
    
    public List<Pet> getPets() {
        return pets;
    }
//สร้างรายงานจำนวนสัตว์แต่ละประเภท และสถิติการรับเข้า/ปฏิเสธ
    public String report() {
        StringBuilder report = new StringBuilder();
        report.append("\n📊 รายงานการนำเข้าสัตว์เวทมนตร์\n");
        report.append("Phoenix: ").append(pets.stream().filter(p -> p instanceof Phoenix).count()).append("\n");
        report.append("Dragon: ").append(pets.stream().filter(p -> p instanceof Dragon).count()).append("\n");
        report.append("Owl: ").append(pets.stream().filter(p -> p instanceof Owl).count()).append("\n");
        report.append("\nสัตว์ที่รับเข้า: ").append(acceptedCount); // เพิ่มจำนวนสัตว์ที่รับเข้า
        report.append("\nสัตว์ที่ถูกปฏิเสธ: ").append(rejectedCount); // เพิ่มจำนวนสัตว์ที่ถูกปฏิเสธ
        return report.toString();
    }

// เมธอดสำหรับบันทึกข้อมูลลงไฟล์ CSV
public void saveToCSV() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
        writer.println("ID,Type,HealthCheckDate,VaccineCount,AdditionalInfo");
        for (Pet pet : pets) {
            writer.println(pet.toCSV()); // ใช้เมธอด `toCSV()` จาก `Pet`
        }
        writer.println("\nAcceptedCount," + acceptedCount); // บันทึกจำนวนสัตว์ที่รับเข้า
        writer.println("RejectedCount," + rejectedCount); // บันทึกจำนวนสัตว์ที่ถูกปฏิเสธ
        System.out.println(" บันทึกข้อมูลสัตว์ลงไฟล์ CSV เรียบร้อยแล้ว!");
    } catch (IOException e) {
        System.out.println("เกิดข้อผิดพลาดในการบันทึกไฟล์ CSV: " + e.getMessage());
    }
}

// เมธอดสำหรับโหลดข้อมูลจากไฟล์ CSV
public void loadFromCSV() {
    File file = new File(CSV_FILE);
    if (!file.exists()) return; // ถ้าไฟล์ไม่มี ไม่ต้องโหลด

    try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
        String line;
        reader.readLine(); // ข้ามบรรทัดหัวข้อ
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("AcceptedCount")) {
                acceptedCount = Integer.parseInt(line.split(",")[1].trim());
            } else if (line.startsWith("RejectedCount")) {
                rejectedCount = Integer.parseInt(line.split(",")[1].trim());
            } else {
                String[] data = line.split(",");
                if (data.length < 5) continue;

                String id = data[0];
                String type = data[1];
                LocalDate date = LocalDate.parse(data[2]);
                int vaccineCount = Integer.parseInt(data[3]);
                String additionalInfo = data[4];

                switch (type) {
                    case "Phoenix":
                        pets.add(new Phoenix(id, date, vaccineCount, Boolean.parseBoolean(additionalInfo)));
                        break;
                    case "Dragon":
                        pets.add(new Dragon(id, date, vaccineCount, Double.parseDouble(additionalInfo)));
                        break;
                    case "Owl":
                        pets.add(new Owl(id, date, vaccineCount, Integer.parseInt(additionalInfo)));
                        break;
                }
            }
        }
        System.out.println(" โหลดข้อมูลจากไฟล์ CSV สำเร็จ!");
    } catch (IOException e) {
        System.out.println(" เกิดข้อผิดพลาดในการโหลดไฟล์ CSV: " + e.getMessage());
    }
}
}
