package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JButton addPhoenixButton, addDragonButton, addOwlButton, reportButton, exitButton;

    public MainView() {
        setTitle("Magic Pet System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        addPhoenixButton = new JButton("เพิ่มนกฟินิกซ์");
        addDragonButton = new JButton("เพิ่มมังกร");
        addOwlButton = new JButton("เพิ่มนกฮูก");
        reportButton = new JButton("รายงานสรุป");
        exitButton = new JButton("ออกจากระบบ");

        add(addPhoenixButton);
        add(addDragonButton);
        add(addOwlButton);
        add(reportButton);
        add(exitButton);

        setVisible(true);
    }

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

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
