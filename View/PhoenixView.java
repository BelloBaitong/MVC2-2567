package View;

import javax.swing.*;
import java.awt.*;

public class PhoenixView extends JFrame {
    private JTextField healthCheckDateField;
    private JTextField vaccineCountField;
    private JCheckBox fireproofCertificateCheckbox;
    private JButton submitButton;

    public PhoenixView() {
        setTitle("เพิ่มนกฟินิกซ์");
        setSize(400, 250); // ปรับขนาดให้กว้างขึ้น
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // เพิ่มระยะห่าง

        // 🔹 Label & Input: วันที่ตรวจสุขภาพ
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("วันที่ตรวจสุขภาพ (วัน/เดือน/ปี):"), gbc);

        gbc.gridx = 1;
        healthCheckDateField = new JTextField();
        healthCheckDateField.setPreferredSize(new Dimension(150, 25));
        add(healthCheckDateField, gbc);

        // 🔹 Label & Input: จำนวนวัคซีน
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("จำนวนวัคซีนที่ได้รับ:"), gbc);

        gbc.gridx = 1;
        vaccineCountField = new JTextField();
        vaccineCountField.setPreferredSize(new Dimension(150, 25));
        add(vaccineCountField, gbc);

        // 🔹 Checkbox: ใบรับรองไฟไม่ลาม
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        fireproofCertificateCheckbox = new JCheckBox("มีใบรับรองไฟไม่ลาม");
        add(fireproofCertificateCheckbox, gbc);

        // 🔹 ปุ่มยืนยัน
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        submitButton = new JButton("ยืนยัน");
        add(submitButton, gbc);

        setVisible(true);
    }

    // ✅ Getter สำหรับดึงค่าที่ผู้ใช้ป้อน
    public String getHealthCheckDate() {
        return healthCheckDateField.getText();
    }

    public String getVaccineCount() {
        return vaccineCountField.getText();
    }

    public boolean getFireproofCertificate() {
        return fireproofCertificateCheckbox.isSelected();
    }

    public void addSubmitListener(java.awt.event.ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}
