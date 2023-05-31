import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    private JLabel label1=new JLabel("Departure City: ");
    private JLabel label2=new JLabel("Class ");
    private JLabel label3=new JLabel("Arrival City: ");
    private JLabel label4=new JLabel("Total Seats: ");
    private JTextField textField1=new JTextField("Lahore");
    private JTextField textField2=new JTextField();
    private JTextField textField3=new JTextField();
    private JRadioButton radioButton1=new JRadioButton("Economy");
    private JRadioButton radioButton2=new JRadioButton("AC");
    private JComboBox<String> comboBox=new JComboBox<>();
    private DefaultComboBoxModel<String> model =new DefaultComboBoxModel<>();
    private String[] cities = {"Gujrat", "Gujranwala", "Faisalabad"};
    private String[] cities2 = {"Islamabad", "Murree", "Multan"};
    private Integer[] economyFares={1000,500,1500};
    private Integer[] acFares={5000,7000,3000};
    private JButton button=new JButton("Compute Fare");
    public MyFrame(){
        setSize(230,300);
        setTitle("Travel Fare Calculator!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);

        textField1.setEditable(false);
        textField1.setFocusable(false);
        textField1.setPreferredSize(new Dimension(100,20));

        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        radioButton1.setFocusable(false);
        radioButton2.setFocusable(false);
        radioButton1.addActionListener(this);
        radioButton2.addActionListener(this);

        comboBox.setEnabled(true);
        comboBox.setFocusable(false);
        comboBox.setPreferredSize(new Dimension(100, 20));

        textField2.setEditable(true);
        textField2.setPreferredSize(new Dimension(100, 20));

        textField3.setVisible(false);
        textField3.setEditable(false);
        textField3.setPreferredSize(new Dimension(180,20));

        button.addActionListener(this);
        button.setPreferredSize(new Dimension(180,20));
        button.setFocusable(false);

        add(label1);
        add(textField1);
        add(label2);
        add(radioButton1);
        add(radioButton2);
        add(label3);
        add(comboBox);
        add(label4);
        add(textField2);
        add(button);
        add(textField3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == radioButton1) {
            model.removeAllElements();
            for (String city : cities) {
                model.addElement(city);
            }
            comboBox.setModel(model);
        } else if (e.getSource() == radioButton2) {
            model.removeAllElements();
            for (String city : cities2) {
                model.addElement(city);
            }
            comboBox.setModel(model);
        }
        if (e.getSource() == button) {
            try {
                String n = textField2.getText();
                int number = Integer.parseInt(n);
                if (number < 0) {
                    JOptionPane.showMessageDialog(this, "Number of seats cannot be negative.");
                    return;
                }

                int cityIndex = comboBox.getSelectedIndex();
                int fare = 0;

                if (radioButton1.isSelected()) {
                    if (cityIndex >= 0 && cityIndex < cities.length) {
                        fare = economyFares[cityIndex];
                    }
                } else if (radioButton2.isSelected()) {
                    if (cityIndex >= 0 && cityIndex < cities2.length) {
                        fare = acFares[cityIndex];
                    }
                }

                int totalFare = fare * number;
                textField3.setText("$" + totalFare);
                textField3.setVisible(true);

                // Trigger repainting of the frame to update the UI
                repaint();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number of seats. Please enter a valid integer.");
            }
        }
    }
}
