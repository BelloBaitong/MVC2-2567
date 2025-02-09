package View;

import javax.swing.*;
import java.awt.*;

//ReportView ใช้แสดงหน้าต่างรายงานผล โดยสืบทอดจาก JFrame

public class ReportView extends JFrame {
    private JTextArea reportArea; // พื้นที่สำหรับแสดงข้อความรายงาน

    public ReportView(String reportText) {
        setTitle("📊 รายงานสรุป"); // ตั้งชื่อหน้าต่าง
        setSize(400, 300); // กำหนดขนาดของหน้าต่าง
        setLayout(new BorderLayout()); // กำหนด Layout เป็น BorderLayout
        // สร้าง JTextArea เพื่อแสดงเนื้อหารายงาน
        reportArea = new JTextArea();
        reportArea.setText(reportText);
        reportArea.setEditable(false);

        // เพิ่ม JTextArea ลงใน JScrollPane เพื่อรองรับการเลื่อนดูข้อมูล
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        // แสดงหน้าต่าง
        setVisible(true);
    }
}

