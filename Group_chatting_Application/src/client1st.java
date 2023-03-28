import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class client1st implements ActionListener,Runnable{
    JTextField text = new JTextField();
    JPanel chat = new JPanel();
    static  Box vertical = Box.createVerticalBox();
    static JFrame frame = new JFrame();
    static DataOutputStream dout;
    BufferedReader reader;
    BufferedWriter writer;
    String name = "Kaleen Bhaiya";
    static Random rm = new Random();
    int x = rm.nextInt(255);
    int y = rm.nextInt(255);
    int z = rm.nextInt(255);



    client1st() throws IOException {
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
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/mirzapur1.jfif"));
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
        JLabel name = new JLabel("Mirzapur");
        name.setBounds(100,15,110,20);
        name.setForeground(Color.white);
        name.setFont(new Font("san serif",Font.BOLD,18));
        panel.add(name);

        JLabel status = new JLabel("Kaleen, Guddu, Bablu,Sweety");
        status.setBounds(100,32,110,20);
        status.setForeground(Color.white);
        status.setFont(new Font("san serif",Font.BOLD,12));
        panel.add(status);

        // Chat Area
        chat.setBounds(5,65,440,580);
        chat.setBackground(Color.WHITE);
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
        frame.setLocation(20,50);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.white);
        frame.setVisible(true);

        //Connecting with server.

        Socket socket = new Socket("localhost",2003);
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String output = "<html> <p style=\"color:rgb("+x+","+y+","+z+");\">"+name+"</p></br></br><p style=\"width : 150px;color : white;\">"+text.getText()+"</p><html>";
//        String output = "<html> <p>"+name+"</p></br></br></br></br><p style=\"width : 150px;\">"+text.getText()+"</p><html>";
//        System.out.println(output); //code debugging only

        JPanel panel = formatLabel(output);

        chat.setLayout(new BorderLayout());

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.WHITE);
        right.add(panel,BorderLayout.LINE_END);

        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));

        chat.add(vertical,BorderLayout.PAGE_START);


        // Sending message
        try {
            writer.write(output);
            writer.write("\r\n");
            writer.flush();
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
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JLabel label = new JLabel("<html><p style = \"width : 150px\">" + str + "</p></html>");

        label.setFont(new Font("Tahoma",Font.BOLD,15));
        label.setBackground(new Color(7,94,84));
        label.setOpaque(true);
        label.setBorder(new EmptyBorder(0,15,8,50));

        panel.add(label);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(("HH:mm"));

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }

    @Override
    public void run() {
        try {
            String msg = "";
            while (true) {
                msg = reader.readLine();
                if(msg.contains(name))
                {
                    continue;
                }
                JPanel panel = formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());
                left.setBackground(Color.white);
                left.add(panel,BorderLayout.LINE_START);

                vertical.add(left);
                chat.add(vertical,BorderLayout.PAGE_START);
                frame.repaint();
                frame.invalidate(); // all used to paint
                frame.validate();

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        client1st first = new client1st();
        Thread thread = new Thread(first);
        thread.start();

    }
}
