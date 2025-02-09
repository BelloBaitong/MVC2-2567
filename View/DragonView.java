package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DragonView extends JFrame {
    private JTextField healthCheckDateField;
    private JTextField vaccineCountField;
    private JTextField smokePollutionField;
    private JButton submitButton;

    public DragonView() {
        setTitle("เพิ่มมังกร");
        setSize(400, 250); // ปรับขนาดหน้าต่างให้เหมาะสม
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

        // 🔹 Label & Input: ระดับมลพิษควัน
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("ระดับมลพิษควัน (%):"), gbc);

        gbc.gridx = 1;
        smokePollutionField = new JTextField();
        smokePollutionField.setPreferredSize(new Dimension(150, 25));
        add(smokePollutionField, gbc);

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

    public double getSmokePollution() {
        try {
            return Double.parseDouble(smokePollutionField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "โปรดป้อนค่าระดับมลพิษเป็นตัวเลข!", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
            return -1; // คืนค่าไม่ถูกต้อง
        }
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}
