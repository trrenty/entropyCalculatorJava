import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class AppGUI {
    private JButton igButton;
    private JPanel panelMain;
    private JTextField logField;
    private JTextField logResult;
    private JTextField entropyFieldB;
    private JTextField entropyResult;
    private JLabel logxLabel;
    private JTextField entropyFieldA;
    private JTextField igFieldA;
    private JTextField igFieldB;
    private JTextField igFieldE;
    private JTextField igFieldF;
    private JTextField igFieldD;
    private JTextField igFieldC;
    private JTextField igResult;
    private JLabel logLable;
    private JLabel entropyLabel;
    private JLabel igLabel;
    private JLabel entropyALabel;
    private JLabel entropyBLabel;
    private JLabel logResultLabel;
    private JLabel entropyResultLabel;
    private JLabel igResultLabel;
    private JLabel igALabel;
    private JLabel igBLabel;
    private JLabel igCLabel;
    private JLabel igDLabel;
    private JLabel igELabel;
    private JLabel igFLabel;
    private JButton cpyLogButton;
    private JButton cpyEntropyButton;
    private JButton cpyIGButton;
    private JTextField ceResult;
    private JLabel ceResultLabel;
    private JButton cpyCEButton;


    public AppGUI() {
//        panelMain.setMinimumSize(new Dimension(950, 450));
//        logResult.setEnabled(false);
        cpyLogButton.setName("CopyLog");
        cpyEntropyButton.setName("CopyEntropy");
        cpyIGButton.setName("CopyIG");
        cpyCEButton.setName("CopyCE");
        System.out.println(cpyLogButton.getName());
        logField.addActionListener(actionEvent -> {
            String value = logField.getText();
            if (!value.isEmpty()) {
                double number;
                try {
                    number = Double.parseDouble(value);
                }
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number");
                    return;
                }
                if (number <= 0) {
                    JOptionPane.showMessageDialog(null, "Number must be greater than 0");
                }
                else {
                    logResult.setText(String.valueOf(Calculator.logBase2(number)));
                }
            }
        });
        Action entropyEvent = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String value1 = entropyFieldA.getText();
                String value2 = entropyFieldB.getText();

                if (!value1.isEmpty() && !value2.isEmpty()) {
                    int a, b;
                    try {
                        a = Integer.parseInt(value1);
                        b = Integer.parseInt(value2);
                    }
                    catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "You entered an invalid number");
                        return;
                    }
                    entropyResult.setText(String.valueOf(Calculator.calculateEntropy(a, b)));
                }
            }
        };

        entropyFieldA.addActionListener(entropyEvent);
        entropyFieldB.addActionListener(entropyEvent);

        igButton.addActionListener(actionEvent -> {
            if (!igFieldA.getText().isEmpty() &&
                !igFieldB.getText().isEmpty() &&
                !igFieldC.getText().isEmpty() &&
                !igFieldD.getText().isEmpty() &&
                !igFieldE.getText().isEmpty() &&
                !igFieldF.getText().isEmpty()) {
                int a, b, c, d, e, f;
                try {
                    a = Integer.parseInt(igFieldA.getText());
                    b = Integer.parseInt(igFieldB.getText());
                    c = Integer.parseInt(igFieldC.getText());
                    d = Integer.parseInt(igFieldD.getText());
                    e = Integer.parseInt(igFieldE.getText());
                    f = Integer.parseInt(igFieldF.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "You entered an invalid number");
                    return;
                }
                if (a != (c+e) || b!= (d + f)) {
                    JOptionPane.showMessageDialog(null, "Bad partition");
                }
                else {
                    igResult.setText(String.valueOf(Calculator.calculateIG(c, d, e, f)));
                    ceResult.setText(String.valueOf(Calculator.calculateConditionedEntropy(c,d,e,f)));
                }
            }
        });

        Action cpyClipBoard = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (((JButton)actionEvent.getSource()).getName().equals("CopyLog") && !logResult.getText().isEmpty()) {
                    StringSelection stringSelection = new StringSelection(logResult.getText());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    logResult.setText("Copied!");

                }
                else if (((JButton)actionEvent.getSource()).getName().equals("CopyEntropy") && !entropyResult.getText().isEmpty()) {
                    StringSelection stringSelection = new StringSelection(entropyResult.getText());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    entropyResult.setText("Copied!");
                }
                else if (((JButton)actionEvent.getSource()).getName().equals("CopyIG") && !igResult.getText().isEmpty()) {
                    StringSelection stringSelection = new StringSelection(igResult.getText());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    igResult.setText("Copied!");
                }
                else if (((JButton)actionEvent.getSource()).getName().equals("CopyCE") && !ceResult.getText().isEmpty()) {
                    StringSelection stringSelection = new StringSelection(ceResult.getText());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    ceResult.setText("Copied!");
                }

            }
        };

        cpyLogButton.addActionListener(cpyClipBoard);
        cpyIGButton.addActionListener(cpyClipBoard);
        cpyEntropyButton.addActionListener(cpyClipBoard);
        cpyCEButton.addActionListener(cpyClipBoard);

        }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setMinimumSize(new Dimension(1000, 450));
        frame.setContentPane(new AppGUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        System.out.println(Calculator.calculateEntropy(10, 6));
        System.out.println(Calculator.calculateIG(6, 0, 4, 6));
    }
}
