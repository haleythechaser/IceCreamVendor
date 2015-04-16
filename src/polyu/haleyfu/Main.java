package polyu.haleyfu;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Main extends JFrame implements ActionListener, DataStore.FlavorChangedListener, DataStore.DecoratorChangedListener {
    private final String ADMIN_COMMAND = "admin";
    private final String FLAVOR = "flavor";
    private final String DECORATOR = "decorator";
    private final String CONFIRM = "confirm";
    private final String CLEAR = "clear";

    private final Dimension ItemDimension = new Dimension(120, 30);

    JPanel mFlavorPanel;
    JPanel mDecoratorPanel;
    JPanel mAdminPanel;
    JPanel mIceCreamPanel;
    IceCream mIceCream = new IceCream();


    public Main() {
        super("ICE CREAM SALE ASSISTANT");
        setLayout(new java.awt.GridLayout(1, 3));
        //add there main panel with flowlayout into frame
        mFlavorPanel = new JPanel(new FlowLayout());
        add(mFlavorPanel);
        mDecoratorPanel = new JPanel(new FlowLayout());
        add(mDecoratorPanel);
        mAdminPanel = new JPanel(new FlowLayout());
        add(mAdminPanel);

        JButton admin = new JButton("ADMIN");
        admin.addActionListener(this);
        admin.setActionCommand(ADMIN_COMMAND);
        admin.setPreferredSize(ItemDimension);
        mAdminPanel.add(admin);
        JButton confirm = new JButton("CONFIRM");
        confirm.addActionListener(this);
        confirm.setActionCommand(CONFIRM);
        confirm.setPreferredSize(ItemDimension);
        mAdminPanel.add(confirm);
        JButton clear = new JButton("CLEAR");
        clear.addActionListener(this);
        clear.setActionCommand(CLEAR);
        clear.setPreferredSize(ItemDimension);
        mAdminPanel.add(clear);

        mIceCreamPanel = new JPanel();
        mIceCreamPanel.setLayout(new BoxLayout(mIceCreamPanel, BoxLayout.Y_AXIS));
        mAdminPanel.add(mIceCreamPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 500);
        setVisible(true);

        updateFlavorPanel();
        updateDecoratorPanel();

        DataStore.getInstance().mFlavorListener = this;
        DataStore.getInstance().mDecoratorListener = this;
    }


    public void actionPerformed(ActionEvent evt) {
        if (evt == null) {
            return;
        }
        if (evt.getActionCommand().equals(DECORATOR)) {
            JButton button = (JButton) evt.getSource();
            int i = Integer.parseInt(button.getName());
            mIceCream.setDecorator(DataStore.getInstance().mDecorators.get(i));
            updateIceCreamPanel();
        } else if (evt.getActionCommand().equals(FLAVOR)) {
            JButton button = (JButton) evt.getSource();
            int i = Integer.parseInt(button.getName());
            mIceCream.setFlavor(DataStore.getInstance().mFlavors.get(i));
            updateIceCreamPanel();
        } else if (evt.getActionCommand().equals(CONFIRM)) {
            if (mIceCream.mFlavor == null) {
                JOptionPane.showMessageDialog(null, "flavor is necessary if you really want a ice cream");
                return;
            }
            int total = mIceCream.mFlavor.mPrice;
            for (IceCream.Decorator decorator : mIceCream.mDecorator) {
                total += decorator.mPrice;
            }
            mIceCream.mPrice = total;
            updateIceCreamPanel();
        } else if (evt.getActionCommand().equals(CLEAR)) {
            mIceCream.clear();
            updateIceCreamPanel();
        } else if (evt.getActionCommand().equals(ADMIN_COMMAND)) {
            Dialog dialog = new AdminPanel();
            dialog.setSize(550, 100);
            dialog.setVisible(true);
        }
    }


    private void updateIceCreamPanel() {
        mIceCreamPanel.removeAll();
        if (mIceCream.mFlavor != null) {
            JLabel label = new JLabel("Flavor:");
            label.setForeground(Color.BLACK);
            label.setPreferredSize(ItemDimension);
            mIceCreamPanel.add(label);
            JLabel name = new JLabel(mIceCream.mFlavor.mName);
            name.setPreferredSize(ItemDimension);
            mIceCreamPanel.add(name);
        }
        if (mIceCream.mDecorator.size() > 0) {
            JLabel label = new JLabel("Decorator:");
            label.setForeground(Color.BLACK);
            label.setPreferredSize(ItemDimension);
            mIceCreamPanel.add(label);
            for (IceCream.Decorator decorator : mIceCream.mDecorator) {
                JLabel name = new JLabel(decorator.mName);
                name.setPreferredSize(ItemDimension);
                mIceCreamPanel.add(name);
            }
        }
        if (mIceCream.mPrice != 0) {
            JLabel label = new JLabel("Total Price:$" + mIceCream.mPrice);
            label.setForeground(Color.BLACK);
            label.setPreferredSize(ItemDimension);
            mIceCreamPanel.add(label);
        }

        mIceCreamPanel.revalidate();
    }

    private void updateDecoratorPanel() {
        mDecoratorPanel.removeAll();
        mDecoratorPanel.add(new JLabel("DECORATOR"));
        List<IceCream.Decorator> decorators = DataStore.getInstance().mDecorators;
        int i = 0;
        for (IceCream.Decorator decorator : decorators) {
            JButton button = new JButton(decorator.mName + ",$" + decorator.mPrice);
            button.setPreferredSize(ItemDimension);
            button.setName(String.valueOf(i));
            button.addActionListener(this);
            button.setActionCommand(DECORATOR);
            mDecoratorPanel.add(button);
            i++;
        }
        mDecoratorPanel.revalidate();
    }

    private void updateFlavorPanel() {
        mFlavorPanel.removeAll();
        mFlavorPanel.add(new JLabel("FLAVOR"));
        List<IceCream.Flavor> flovers = DataStore.getInstance().mFlavors;
        int i = 0;
        for (IceCream.Flavor flavor : flovers) {
            JButton button = new JButton(flavor.mName + ",$" + flavor.mPrice);
            button.setPreferredSize(ItemDimension);
            button.setName(String.valueOf(i));
            button.addActionListener(this);
            button.setActionCommand(FLAVOR);
            mFlavorPanel.add(button);
            i++;
        }
        mFlavorPanel.revalidate();
    }

    @Override
    public void decoratorChanged() {
        updateDecoratorPanel();
    }

    @Override
    public void flavorChanged() {
        updateFlavorPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
