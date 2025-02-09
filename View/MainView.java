package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


//หน้าต่างหลักของระบบ Magic Pet System

public class MainView extends JFrame {
    private JButton addPhoenixButton, addDragonButton, addOwlButton, reportButton, exitButton;

    public MainView() {
        setTitle("Magic Pet System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // สร้างปุ่มต่างๆ
        addPhoenixButton = new JButton("เพิ่มนกฟินิกซ์");
        addDragonButton = new JButton("เพิ่มมังกร");
        addOwlButton = new JButton("เพิ่มนกฮูก");
        reportButton = new JButton("รายงานสรุป");
        exitButton = new JButton("ออกจากระบบ");

        // เพิ่มปุ่มลงในหน้าต่าง
        add(addPhoenixButton);
        add(addDragonButton);
        add(addOwlButton);
        add(reportButton);
        add(exitButton);

        setVisible(true); // แสดงหน้าต่าง
    }

     // แสดงข้อความแจ้งเตือน
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "แจ้งเตือน", JOptionPane.INFORMATION_MESSAGE);
    }
    // แสดงข้อความข้อผิดพลาด
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
    }
    
    // เมธอดเพิ่มตัวฟังเหตุการณ์ให้ปุ่มต่างๆ
    public void addPhoenixButtonListener(ActionListener listener) {
        addPhoenixButton.addActionListener(listener);
    }

    public void addDragonButtonListener(ActionListener listener) {
        addDragonButton.addActionListener(listener);
    }

    public void addOwlButtonListener(ActionListener listener) {
        addOwlButton.addActionListener(listener);
    }

    public void addReportButtonListener(ActionListener listener) {
        reportButton.addActionListener(listener);
    }

    public void addExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

}
