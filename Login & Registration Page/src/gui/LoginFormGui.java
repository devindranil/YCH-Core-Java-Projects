package gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import constants.CommonConstants;

public class LoginFormGui extends Form{
    public LoginFormGui(){
        super("Login");
        addGuiComponents();
    }

    public void addGuiComponents(){
        /*Create a Login Label */
        JLabel loginLabel = new JLabel("Login");
        //configure components x, y position and width/height valuesrelatively
        loginLabel.setBounds(0, 25, 520, 100);
        //change the font color
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        // change the font size
        loginLabel.setFont(new Font("Dialog",Font.BOLD,40));
        //center text
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        /* CREATE A USER NAME LABEL */
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog",Font.PLAIN,18));

        /*CRAETE A USER NAME TEXT FIELD */
        JTextField userenameTextField = new JTextField();
        userenameTextField.setBounds(30, 185, 450, 55);
        userenameTextField.setBackground(CommonConstants.SECONDARY_COLOR);
        userenameTextField.setForeground(CommonConstants.TEXT_COLOR);
        userenameTextField.setFont(new Font("Dialog",Font.PLAIN,24));

        /*CREATE A PASSWORD LABEL */
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 335, 400, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog",Font.PLAIN,18));

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(30, 365, 450, 55);
        passwordTextField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordTextField.setForeground(CommonConstants.TEXT_COLOR);
        passwordTextField.setFont(new Font("Dialog",Font.PLAIN,24));

        /*CREATE A LOG IN BUTTON */
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog",Font.BOLD,18));
        loginButton.setBounds(125, 520, 250, 50);
        loginButton.setBackground(CommonConstants.TEXT_COLOR);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        /*CREATE A REGISTER LABEL(WHICH IS USED TO LOAD THE REGISTER GUI) */
        JLabel registerLabel = new JLabel("Not a User? Register Here");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(125, 600, 250, 30);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        /*ADD FUNCTIONALITY SO THAT WHEN CLICKED IT WILL LAUNCH THE REGSITER FORM GUI*/
        registerLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //dispose login gui
                LoginFormGui.this.dispose();
                //launch the register gui
                new RegisterFormGui().setVisible(true);
            }
            
        });


        //add components
        add(loginLabel);
        add(usernameLabel);
        add(userenameTextField);
        add(passwordLabel);
        add(passwordTextField);
        add(loginButton);
        add(registerLabel);
    }
}
