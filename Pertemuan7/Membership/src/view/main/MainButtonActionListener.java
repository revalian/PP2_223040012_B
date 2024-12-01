package view.main;

import java.awt.event.*;

import javax.swing.JButton;

class MainButtonActionListener implements ActionListener {
    private MainFrame mainFrame;

    public MainButtonActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source == mainFrame.getButtonJenisMember()) {
            mainFrame.showJenisMemberFrame();
        } else if (source == mainFrame.getButtonMember()) {
            mainFrame.showMemberFrame();
        }
    }
}

