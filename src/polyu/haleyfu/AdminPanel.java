package polyu.haleyfu;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class AdminPanel extends JDialog implements ActionListener {
    private final String CONFIRM = "confirm";
    private final String CLEAR = "clear";
    JTextField mFlavorName = new JTextField();
    JTextField mFlavorPrice = new JTextField();
    JTextField mDecoratorName = new JTextField();
    JTextField mDecoratorPrice = new JTextField();

    AdminPanel() {
        setTitle("Ice Cream vendor control panel");
        setLayout(new FlowLayout());
        Panel flavorPanel = new Panel(new GridLayout(2, 4));

        add(flavorPanel);

        flavorPanel.add(new JLabel("flavor name"));
        flavorPanel.add(mFlavorName);
        flavorPanel.add(new JLabel("flavor price"));
        flavorPanel.add(mFlavorPrice);

        flavorPanel.add(new JLabel("decorator name"));
        flavorPanel.add(mDecoratorName);
        flavorPanel.add(new JLabel("decorator price"));
        flavorPanel.add(mDecoratorPrice);


        JButton confirm = new JButton("confirm");
        confirm.addActionListener(this);
        confirm.setActionCommand(CONFIRM);
        add(confirm);

        JButton clear = new JButton("clear");
        clear.addActionListener(this);
        clear.setActionCommand(CLEAR);
        add(clear);

    }

    private void add() {

        String flavorName = mFlavorName.getText();
        int flavorPrice;
        try {
            flavorPrice = Integer.parseInt(mFlavorPrice.getText());
        } catch (Exception ex) {
            flavorPrice = 0;
        }
        String decoratorName = mDecoratorName.getText();
        int decoratorPrice;
        try {
            decoratorPrice = Integer.parseInt(mDecoratorPrice.getText());
        } catch (Exception ex) {
            decoratorPrice = 0;
        }

        boolean skipFlavor = false;
        boolean skipDecorator = false;
        if (flavorName.equals("") || flavorPrice == 0) {
            skipFlavor = true;
        }
        if (decoratorName.equals("") || decoratorPrice == 0) {
            skipDecorator = true;
        }
        if (skipFlavor && skipDecorator) {
            JOptionPane.showMessageDialog(null, "data is not complete,either flavor data or decorator data should be compelte");
            return;
        }
        boolean flavorSetted = false;
        boolean decoratorSetted = false;
        if (!skipFlavor) {
            flavorSetted = DataStore.getInstance().addFlavor(flavorName, flavorPrice);
        }
        if (!skipDecorator) {
            decoratorSetted = DataStore.getInstance().addDecorator(decoratorName, decoratorPrice);
        }
        if (flavorSetted || decoratorSetted) {
            JOptionPane.showMessageDialog(null, "add or reset successful");
        } else {
            JOptionPane.showMessageDialog(null, "add or rest failure,item with same name & price is already in data store");
        }

    }

    private void clear() {
        mFlavorName.setText("");
        mFlavorPrice.setText("");
        mDecoratorName.setText("");
        mDecoratorPrice.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e == null) {
            return;
        }
        if (e.getActionCommand().equals(CONFIRM)) {
            add();
        } else if (e.getActionCommand().equals(CLEAR)) {
            clear();
        }
    }

}
