// Controller/FirstPageController.java
package controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.FirstPageView;
import view.LoginPage;
import view.Signuppage;

public class FirstPageController {
    private FirstPageView view;

    public FirstPageController(FirstPageView view) {
        this.view = view;
        this.view.loginButton.addActionListener(new LoginButtonListener());
        this.view.signupButton.addActionListener(new SignupButtonListener());
    }

    // Inner class to handle login button click
    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            new LoginPage();
        }
    }

    // Inner class to handle signup button click
    class SignupButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            new Signuppage();
        }
    }
}
