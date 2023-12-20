package gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import backend.MyJDBC;
import constants.CommonConstants;

public class RegisterFormGui extends Form {
    public RegisterFormGui() {
        super("Login");
        addGuiComponents();
    }

    public void addGuiComponents() {
        /* Create a register Label */
        JLabel registerLabel = new JLabel("Register");
        // configure components x, y position and width/height valuesrelatively
        registerLabel.setBounds(0, 25, 520, 100);
        // change the font color
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        // change the font size
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        // center text
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        /* CREATE A USER NAME LABEL */
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        /* CRAETE A USER NAME TEXT FIELD */
        JTextField userenameTextField = new JTextField();
        userenameTextField.setBounds(30, 185, 450, 55);
        userenameTextField.setBackground(CommonConstants.SECONDARY_COLOR);
        userenameTextField.setForeground(CommonConstants.TEXT_COLOR);
        userenameTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        /* CREATE A PASSWORD LABEL */
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 255, 400, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(30, 300, 450, 55);
        passwordTextField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordTextField.setForeground(CommonConstants.TEXT_COLOR);
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        /* RE-ENTER PASSWORD LABEL */
        JLabel reenterpasswordLabel = new JLabel("Re-Enter Password:");
        reenterpasswordLabel.setBounds(30, 365, 400, 25);
        reenterpasswordLabel.setForeground(CommonConstants.TEXT_COLOR);
        reenterpasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JPasswordField reenterpasswordTextField = new JPasswordField();
        reenterpasswordTextField.setBounds(30, 395, 450, 55);
        reenterpasswordTextField.setBackground(CommonConstants.SECONDARY_COLOR);
        reenterpasswordTextField.setForeground(CommonConstants.TEXT_COLOR);
        reenterpasswordTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        /* CREATE A REGISTER BUTTON */
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));
        registerButton.setBounds(125, 520, 250, 50);
        registerButton.setBackground(CommonConstants.TEXT_COLOR);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        /* backend code goes here */
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = userenameTextField.getText();

                // get password
                String password = new String(passwordTextField.getPassword());

                // get Re enter password
                String rePassword = new String(passwordTextField.getPassword());

                // validate user input
                if (validateUserInput(username, password, rePassword)) {
                    // register the user to the DB
                    if (MyJDBC.register(username, rePassword)) {
                        // dispose of this gui
                        RegisterFormGui.this.dispose();

                        // take user back to the login gui
                        LoginFormGui loginFormGui = new LoginFormGui();
                        loginFormGui.setVisible(true);

                        // create a result dialog box
                        JOptionPane.showMessageDialog(loginFormGui, "Registered Account Succesfull!..");
                    } else {
                        // register failed(likely due to the user alredy exist in the data base)
                        JOptionPane.showMessageDialog(RegisterFormGui.this, "Error: Username alraedy taken");
                    }
                } else {
                    // Invalid user Input
                    JOptionPane.showMessageDialog(RegisterFormGui.this,
                            "Error: Username must be at least 6 charcters long \n and or password must match");
                }
            }
        });

        /* CREATE A LOGIN LABEL(WHICH IS USED TO LOAD THE LOGIN GUI) */
        JLabel loginLabel = new JLabel("Have an account? Login Here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBounds(125, 600, 250, 30);
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        /* ADD FUNCTIONALITY SO THAT WHEN CLICKED IT WILL LAUNCH THE LOGIN FORM GUI */
        loginLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose login gui
                RegisterFormGui.this.dispose();
                // launch the register gui
                new LoginFormGui().setVisible(true);
            }

        });

        // add components
        add(registerLabel);
        add(usernameLabel);
        add(userenameTextField);
        add(passwordLabel);
        add(passwordTextField);
        add(reenterpasswordLabel);
        add(reenterpasswordTextField);
        add(registerButton);
        add(loginLabel);
    }

    // so here we are going to validate our user input, making sure that user has
    // placed a valid username and password
    private boolean validateUserInput(String username, String password, String rePassword) {
        // all fields must have a value
        if (username.length() == 0 || password.length() == 0 || rePassword.length() == 0) {
            return false;
        }

        // username has to be atleast a 6 characters long
        if (username.length() < 6) {
            return false;
        }

        // password and rePassword must be the same like username
        if (!password.equals(rePassword)) {
            return false;
        }

        return true;
    }
}
