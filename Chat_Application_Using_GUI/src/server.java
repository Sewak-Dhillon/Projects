import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class server implements ActionListener{
    JTextField text = new JTextField();
    JPanel chat = new JPanel();
    static  Box vertical = Box.createVerticalBox();
    static JFrame frame = new JFrame();
    static DataOutputStream dout;
    server()
    {
        frame.setLayout(null);

        // Top-Most Panel

        JPanel panel = new JPanel();
        panel.setBackground(new Color(7, 94, 84));
        panel.setBounds(0,0,450,60);
        panel.setLayout(null);
        frame.add(panel);
        //setBounds will work only if setLayout is null;

//      Back Button

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image img2 = image.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel back = new JLabel(img3);
        back.setBounds(5,20,25,25);
        panel.add(back);// Anything you want to add,then you have to call add() with that specific object.

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                setVisible(false);
//                or
                    System.exit(0);
            }
        });

        //      Profile picture
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/shahrukh.jfif"));
        Image img4 = image1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon img5 = new ImageIcon(img4);
        JLabel profile = new JLabel(img5);
        profile.setBounds(40,6,50,50);
        panel.add(profile);

        //      video picture
        ImageIcon image2 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image img6 = image2.getImage().getScaledInstance(28,28,Image.SCALE_DEFAULT);
        ImageIcon img7 = new ImageIcon(img6);
        JLabel video = new JLabel(img7);
        video.setBounds(300,17,28,28);
        panel.add(video);


        //      call picture
        ImageIcon image3 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image img8 = image3.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon img9 = new ImageIcon(img8);
        JLabel phone = new JLabel(img9);
        phone.setBounds(355,17,25,25);
        panel.add(phone);

        //      three dots picture
        ImageIcon image4 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image img10 = image4.getImage().getScaledInstance(18,20,Image.SCALE_DEFAULT);
        ImageIcon img11 = new ImageIcon(img10);
        JLabel dots = new JLabel(img11);
        dots.setBounds(400,20,18,20);
        panel.add(dots);

        // Name and status
        JLabel name = new JLabel("Shahrukh");
        name.setBounds(100,15,110,20);
        name.setForeground(Color.white);
        name.setFont(new Font("san serif",Font.BOLD,18));
        panel.add(name);

        JLabel status = new JLabel("Active now");
        status.setBounds(100,32,110,20);
        status.setForeground(Color.white);
        status.setFont(new Font("san serif",Font.BOLD,12));
        panel.add(status);

        // Chat Area
        chat.setBounds(5,65,440,580);
        frame.add(chat);

//        Message input text area
        text.setBounds(5,655,310,40);
        text.setFont(new Font("san  serif",Font.BOLD,16));
        frame.add(text);

        // send button
        JButton send = new JButton("SEND");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.addActionListener(this);
        send.setFont(new Font("san  serif",Font.BOLD,16));
        send.setForeground(Color.white);
        frame.add(send);

        frame.setSize(450,700);
        frame.setLocation(200,50);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.white);
        frame.setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        new server();

        ServerSocket server = new ServerSocket(6001);
        while (true)
        {
            Socket socket = server.accept();
            DataInputStream din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());


            // receiving message
            while (true)
            {
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);

                JPanel left = new JPanel( new BorderLayout());
                left.add(panel,BorderLayout.LINE_START);
                vertical.add(left);
                frame.validate();
            }

        }





    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String output = text.getText();
//        System.out.println(output); //code debugging only

        JPanel panel = formatLabel(output);

        chat.setLayout(new BorderLayout());

        JPanel right = new JPanel(new BorderLayout());

        right.add(panel,BorderLayout.LINE_END);

        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));

        chat.add(vertical,BorderLayout.PAGE_START);


        // Sending message
        try {
            dout.writeUTF(output);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


        text.setText("");
        frame.repaint();
        frame.invalidate(); // all used to paint
        frame.validate();
    }

    public static JPanel formatLabel(String str)
    {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JLabel label = new JLabel("<html><p style = \"width : 150px\">" + str + "</p></html>");

        label.setFont(new Font("Tahoma",Font.BOLD,15));

        label.setBackground(new Color(37,211,102));
        label.setOpaque(true);
        label.setBorder(new EmptyBorder(15,15,15,50));

        panel.add(label);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(("HH:mm"));

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }
}
