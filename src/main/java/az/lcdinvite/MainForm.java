package az.lcdinvite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton btClose;
    private JTextArea taSource;
    private JTextArea taEncoded;
    private JTextArea taCode;
    private JButton encodeButton;
    private JButton decodeButton;
    private static MainForm dialog;

    public MainForm() {
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        btClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
    }

    public static void main(String[] args) {
        dialog = new MainForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
