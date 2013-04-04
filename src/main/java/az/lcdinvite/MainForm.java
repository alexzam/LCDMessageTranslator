package az.lcdinvite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.Charset;

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

    private final static int screenWidth = 16;

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
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String in = taSource.getText();
                String out;
                try {
                    out = Encoder.encode(in);
                } catch (Exception e1) {
                    out = e1.getMessage();
                }
                taEncoded.setText(out);
            }
        });
    }

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            dialog = new MainForm();
            dialog.pack();
            dialog.setVisible(true);
            System.exit(0);
        } else {
            try {
                String inFilename = args[0];
                System.out.println("Opening file " + inFilename);
                FileInputStream inFile = new FileInputStream(inFilename);
                BufferedReader bufR = new BufferedReader(new InputStreamReader(inFile, Charset.forName("windows-1251")));

                String outFilename = inFilename.replaceFirst("\\.[^\\.]+$", ".out");
                System.out.println("Writing file " + outFilename);
                FileOutputStream outfile = new FileOutputStream(outFilename);
                PrintStream bufW = new PrintStream(outfile);

                String inStr;
                int strCount = 0;
                while ((inStr = bufR.readLine()) != null) {
                    strCount++;
                    String outStr = Encoder.encode(inStr.trim());
                    int len = inStr.replaceAll("\\\\..", "C").length();
                    bufW.print(".db ");

                    int num = (int) Math.floor(.5 * (screenWidth - len));
                    for (int i = 0; i < num; i++) {
                        bufW.print("0x20,");
                    }
                    bufW.print(outStr);
                    num = (int) Math.ceil(.5 * (screenWidth - len));
                    for (int i = 0; i < num; i++) {
                        bufW.print(",0x20");
                    }
                    bufW.println();
                }
                bufW.println("// Total encoded: " + strCount + " lines");
                System.out.println("Total encoded: " + strCount + " lines");
                bufR.close();
                bufW.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
