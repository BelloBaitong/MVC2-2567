package View;

import javax.swing.*;
import java.awt.*;

public class ReportView extends JFrame {
    private JTextArea reportArea;

    public ReportView(String reportText) {
        setTitle("📊 รายงานสรุป");
        setSize(400, 300);
        setLayout(new BorderLayout());

        reportArea = new JTextArea();
        reportArea.setText(reportText);
        reportArea.setEditable(false);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);
        setVisible(true);
    }
}

