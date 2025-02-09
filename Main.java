import Controller.PetController;
import Model.PetDatabase;
import View.MainView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // ใช้ SwingUtilities.invokeLater เพื่อให้ GUI รันใน Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            PetDatabase database = new PetDatabase(); // สร้างอ็อบเจ็กต์ของฐานข้อมูลสัตว์เลี้ยง
            MainView mainView = new MainView(); // สร้างอ็อบเจ็กต์ของหน้าจอหลักของแอปพลิเคชัน
            new PetController(database, mainView); // สร้าง Controller เพื่อเชื่อมต่อ Model และ View
        });
    }
}
