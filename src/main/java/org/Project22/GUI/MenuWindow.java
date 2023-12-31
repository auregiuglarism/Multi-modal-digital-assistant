package org.Project22.GUI;

import org.Project22.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
// import org.opencv.core.Core;
// import org.opencv.core.Mat;
// import org.opencv.videoio.VideoCapture;
// import org.opencv.imgcodecs.Imgcodecs;

public class MenuWindow {
    public JFrame frame;
    public JPanel menuPanel;
    private JButton authenticationButton;
    private JButton signupButton;
    private JButton welcomeButton;
    private JButton detectedButton;
    private JButton welcomeLabel;
    // private JButton nameLabel;
    // private JTextField text_name;
    private static ArrayList<String> name_lists = new ArrayList<>();
    // private static String userInput;
    private JButton noAccessButton;
    private JButton noWebCamButton;
    private JButton continueButton1,continueButton2;
    private BufferedImage background, resized_b;
    private JLabel background_label;
    private JPasswordField passwordField;
    private ImageIcon icon;
    private JButton signupButton2;
    private JButton backButton;
    private JButton backButton2;
    private JTextField usernameField;
    private JLabel usernameLabel;

    public MenuWindow() {
        initializeMenu();
    }
    public ArrayList<String> getName_lists() {
        return name_lists;
    }

    public void initializeMenu(){

        //TAKES DIMENSION OF THE SCREEN. The dimensions are used to set the gameFrame and the image size.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = (int) (screenSize.getWidth());
        int h = (int) (screenSize.getHeight());

        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        Color lightBlue = new Color(173, 216, 230); // RGB values for light blue
        //menuPanel.setBackground(lightBlue);

        welcomeButton = new JButton("Digital Assistant");
        menuPanel.add(welcomeButton);
        welcomeButton.setBounds(0,60,768,80);
        welcomeButton.setForeground(Color.red.darker());
        welcomeButton.setFont(new Font("Sans-serif",Font.BOLD,60));
        welcomeButton.setOpaque(false);
        welcomeButton.setContentAreaFilled(false);
        welcomeButton.setBorderPainted(false);
        welcomeButton.setFocusPainted(false);

        welcomeLabel = new JButton("Hello DACS Student!");
        menuPanel.add(welcomeLabel);
        welcomeLabel.setFont(new Font("Sans-serif",Font.BOLD,30));
        welcomeLabel.setBounds(0,150,768,100);
        welcomeLabel.setForeground(Color.black.darker());
        welcomeLabel.setOpaque(false);
        welcomeLabel.setContentAreaFilled(false);
        welcomeLabel.setBorderPainted(false);
        welcomeLabel.setFocusPainted(false);

        // nameLabel = new JButton("What is your name?");
        // nameLabel.setBounds(240,485,300,21);
        // nameLabel.setFont(new Font("Sans-serif",Font.BOLD,20));
        // nameLabel.setForeground(Color.black.darker());
        // nameLabel.setOpaque(false);
        // nameLabel.setContentAreaFilled(false);
        // nameLabel.setBorderPainted(false);
        // nameLabel.setFocusPainted(false);
        // menuPanel.add(nameLabel);

        // text_name = new JTextField();
        // text_name.setBounds(279,520,150,30);
        // text_name.setEnabled(true);
        // menuPanel.add(text_name);

        // continueButton2 = new JButton("Continue");
        // continueButton2.setBounds(435,520,100,30);
        // continueButton2.setFont(new Font("Sans-serif",Font.BOLD,10));
        // menuPanel.add(continueButton2);

        // continueButton2.addActionListener(e -> {
        //     userInput = text_name.getText().toLowerCase();
        //     System.out.println("User name: " + userInput);
        // });

        //MENU LABEL
        authenticationButton = new JButton("Log in with FaceID");
        authenticationButton.setBounds(109,520,250,45);
        authenticationButton.setForeground(Color.black.brighter());
        authenticationButton.setFont(new Font("Sans-serif",Font.BOLD,24));
        authenticationButton.setOpaque(true);
        authenticationButton.setContentAreaFilled(true);
        authenticationButton.setBackground(lightBlue);
        authenticationButton.setBorderPainted(true);
        authenticationButton.setFocusPainted(false);
        menuPanel.add(authenticationButton);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(409,520,250,45);
        signupButton.setForeground(Color.black.brighter());
        signupButton.setFont(new Font("Sans-serif",Font.BOLD,24));
        signupButton.setOpaque(true);
        signupButton.setContentAreaFilled(true);
        signupButton.setBackground(lightBlue);
        signupButton.setBorderPainted(true);
        signupButton.setFocusPainted(false);
        menuPanel.add(signupButton);

        signupButton2 = new JButton("Sign up with FaceID");
        signupButton2.setBounds(134,500,500,45);
        signupButton2.setForeground(Color.black.brighter());
        signupButton2.setFont(new Font("Sans-serif",Font.BOLD,24));
        signupButton2.setOpaque(true);
        signupButton2.setContentAreaFilled(true);
        signupButton2.setBackground(lightBlue);
        signupButton2.setBorderPainted(true);
        signupButton2.setFocusPainted(false);
        signupButton2.setVisible(false);
        menuPanel.add(signupButton2);

        backButton = new JButton("Back");
        backButton.setBounds(334,560,100, 30);
        backButton.setForeground(Color.black.brighter());
        backButton.setFont(new Font("Sans-serif",Font.BOLD,15));
        backButton.setOpaque(true);
        backButton.setContentAreaFilled(true);
        backButton.setBackground(lightBlue);
        backButton.setBorderPainted(true);
        backButton.setFocusPainted(false);
        backButton.setVisible(false);
        menuPanel.add(backButton);

        backButton2 = new JButton("Back");
        backButton2.setBounds(334,460,100, 30);
        backButton2.setForeground(Color.black.brighter());
        backButton2.setFont(new Font("Sans-serif",Font.BOLD,15));
        backButton2.setOpaque(true);
        backButton2.setContentAreaFilled(true);
        backButton2.setBackground(lightBlue);
        backButton2.setBorderPainted(true);
        backButton2.setFocusPainted(false);
        backButton2.setVisible(false);
        menuPanel.add(backButton2);

        usernameField = new JTextField();
        usernameField.setBounds(134,425,500,45);
        usernameField.setForeground(Color.black.brighter());
        usernameField.setFont(new Font("Sans-serif",Font.BOLD,20));
        usernameField.setOpaque(true);
        usernameField.setBackground(lightBlue);
        usernameField.setVisible(false);
        menuPanel.add(usernameField);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Sans-serif",Font.BOLD,20));
        usernameLabel.setBounds(134,380,500,45);
        usernameLabel.setForeground(Color.black.darker());
        usernameLabel.setOpaque(false);
        usernameLabel.setVisible(false);
        menuPanel.add(usernameLabel);

        noWebCamButton = new JButton("No access to webcam");
        noWebCamButton.setBounds(200,600,368,50);
        noWebCamButton.setFont(new Font("Sans-serif",Font.BOLD,15));
        Color darkBlue = new Color(53, 98, 189); // RGB values for light blue
        noWebCamButton.setForeground(darkBlue);
        Font font = noWebCamButton.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        noWebCamButton.setFont(font.deriveFont(attributes));
        noWebCamButton.setOpaque(false);
        noWebCamButton.setContentAreaFilled(false);
        noWebCamButton.setBorderPainted(false);
        noWebCamButton.setFocusPainted(false);
        menuPanel.add(noWebCamButton);

        passwordField = new JPasswordField();
        passwordField.setBounds(284,662,200,30);
        passwordField.setVisible(false);
        menuPanel.add(passwordField);

        continueButton1 = new JButton("Continue");
        continueButton1.setBounds(330,695,100,30);
        continueButton1.setFont(new Font("Sans-serif",Font.BOLD,10));
        continueButton1.setVisible(false);
        menuPanel.add(continueButton1);

        detectedButton = new JButton("Hello human! I'd love to help you.");
        detectedButton.setVisible(false);
        detectedButton.setBounds(150,490,500,100);
        detectedButton.setForeground(Color.black.brighter());
        detectedButton.setFont(new Font("Sans-serif",Font.BOLD,20));
        detectedButton.setOpaque(true);
        detectedButton.setContentAreaFilled(true);
        detectedButton.setBorderPainted(false);
        detectedButton.setFocusPainted(false);
        menuPanel.add(detectedButton);

        noAccessButton = new JButton("Your face is not recognized. Please try again or sign up with FaceID.");
        noAccessButton.setVisible(false);
        noAccessButton.setForeground(Color.black.brighter());
        noAccessButton.setBounds(200,530,368,50);
        noAccessButton.setFont(new Font("Sans-serif",Font.BOLD,20));
        // noAccessButton.setOpaque(true);
        // noAccessButton.setContentAreaFilled(true);
        // noAccessButton.setBorderPainted(false);
        // noAccessButton.setFocusPainted(false);

        authenticationButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {

                webcamButtonActionPerformed(event);

            }
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        signupButton.addActionListener(e -> {
            signupButton2.setVisible(true);
            backButton.setVisible(true);
            usernameField.setVisible(true);
            usernameLabel.setVisible(true);
            authenticationButton.setVisible(false);
            signupButton.setVisible(false);
            background_label.setVisible(false);
        });

        backButton.addActionListener(e -> {
            signupButton2.setVisible(false);
            backButton.setVisible(false);
            usernameField.setVisible(false);
            usernameLabel.setVisible(false);
            authenticationButton.setVisible(true);
            signupButton.setVisible(true);
            background_label.setVisible(true);
        });

        backButton2.addActionListener(e -> {
            frame.setVisible(false);
            Main.menu = new MenuWindow();
        });

        signupButton2.addActionListener(e -> {
            String username = usernameField.getText();
            System.out.println("Signing up with name " + username.toLowerCase());
        
            SignUpThread captureThread = new SignUpThread(username);
            captureThread.start();
        });

        noWebCamButton.addActionListener(e -> {

            noWebCamButton.setText("Enter your password:");
            noWebCamButton.setForeground(Color.BLACK);
            passwordField.setVisible(true);
            continueButton1.setVisible(true);

        });
        continueButton1.addActionListener(e -> {
            try {
                Thread.sleep(1000); // delay for 1000 milliseconds (1 second)
            } catch (InterruptedException et) {
                // handle the exception
            }
            frame.setVisible(false);
            Main.ui = new UI();
        });


        try {
            File imageFile = new File("resources/image.png");
            BufferedImage originalImage = ImageIO.read(imageFile);

            int scaledWidth = 180; // set the width to which you want to resize the image
            int scaledHeight = 180; // set the height to which you want to resize the image
            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);

            Graphics2D graphics2D = resizedImage.createGraphics();
            graphics2D.drawImage(scaledImage, 0, 0, null);
            graphics2D.dispose();

            background_label = new JLabel(new ImageIcon(resizedImage));
            menuPanel.add(background_label, BorderLayout.CENTER);

            // use the resized image as needed
        } catch (IOException ex) {
            System.err.println("Error reading image file: " + ex.getMessage());
            ex.printStackTrace();
        }

        frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds((w -768)/2,(h -767)/2,768,765);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(menuPanel);

    }
    class SignUpThread extends Thread {
        private String username;
    
        SignUpThread(String username) {
            this.username = username;
        }
    
        @Override
        public void run() {
            try {
                String[] source = {"python3", "src/main/java/org/Project22/GUI/Webcam2Dataset.py", username.toLowerCase()};
                Process process = Runtime.getRuntime().exec(source);
    
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    
                if (reader.readLine() == null) {
                    System.out.println("Image captured and saved successfully.");
                    
                } else {
                    System.out.println("Error occurred while capturing image.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    private void webcamButtonActionPerformed(MouseEvent evt) {
        System.out.println("Webcam Detection is starting...");
        new WebCamThread().start();
    }

    class WebCamThread extends Thread {

        private static final String[] sources = {"src/main/java/org/Project22/FaceDetection/WebcamDetection.py",
                                                 "src/main/java/org/Project22/FaceRecognition/Recognition.py"};
        
        private static String source_index = sources[1]; 
        private static String[] source = {"python3", source_index};
        
        @Override
        public void run() {
            try {
                Process process = Runtime.getRuntime().exec(source);

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output;

                if(source_index.equals(sources[0])){

                    while ((output = reader.readLine()) != null) {
                        System.out.println(output);
                        if(output.equals("A human was detected")){
                            
                            detectedButton.setVisible(true);
    
                            authenticationButton.setVisible(false);
                            signupButton.setVisible(false);
                            menuPanel.remove(authenticationButton);
                            menuPanel.add(detectedButton);
    
        
                            try {
                                Thread.sleep(5000); // delay for 1000 milliseconds (1 second)
                            } catch (InterruptedException et) {
                                // handle the exception
                            }
                            
                            frame.setVisible(false);
                            Main.ui = new UI();
        
                        }else{
                            authenticationButton.setVisible(false);
                            signupButton.setVisible(false);
                            menuPanel.remove(authenticationButton);

                            noAccessButton.setVisible(true);
                            backButton2.setVisible(true);
                            menuPanel.add(noAccessButton);
                        }
                    }
                }
                if(source_index.equals(sources[1])){
                    System.out.println("Webcam Recognition is starting...");
                    while ((output = reader.readLine()) != null) {
                        System.out.println(output);
                        if(output.contains("A human was detected")){

                            String message = "A human was detected: ";
                            int message_len = message.length();
                            int message_index = output.indexOf("A human was detected: ");
                            
                            String name  = output.substring(message_index + message_len, output.length()-8);

                            authenticationButton.setVisible(false);
                            signupButton.setVisible(false);
                            menuPanel.remove(authenticationButton);
                            
                            detectedButton.setText("Hello " + name + "! I'd love to help you.");
                            detectedButton.setVisible(true);
    
                            menuPanel.add(detectedButton);
    
        
                            try {
                                Thread.sleep(5000); // delay for 1000 milliseconds (1 second)
                            } catch (InterruptedException et) {
                                // handle the exception
                            }
                            
                            frame.setVisible(false);
                            Main.ui = new UI();
        
                        }else{
                            authenticationButton.setVisible(false);
                            signupButton.setVisible(false);
                            menuPanel.remove(authenticationButton);
                            background_label.setVisible(false);
                            welcomeButton.setVisible(false);
                            welcomeLabel.setVisible(false);
                            noWebCamButton.setVisible(false);

                            
                            noAccessButton.setVisible(true);
                            backButton2.setVisible(true);
                            menuPanel.add(noAccessButton);
                        }
                    }
                }
                

                
            } catch (IOException e) {e.printStackTrace();}
        }
    }
}
