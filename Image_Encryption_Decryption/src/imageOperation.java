import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class imageOperation extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    JTextField textfield;
    imageOperation()
    {
        frame.setSize(300,400);
        frame.setTitle("Image Encryption and Decryption");
        frame.setLocationRelativeTo(null);

        Font font = new Font("Roboto",Font.BOLD,25);
//        Creating Button
        JButton btn = new JButton("Open Image");
        btn.setFont(font);
        btn.addActionListener(e -> {
            String str = textfield.getText();
            int temp = Integer.parseInt(str);
            try {
                operate(temp);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

//        Creating Text Field

        textfield = new JTextField(10);
        textfield.setFont(font);

        frame.setLayout(new FlowLayout());




        frame.add(btn);
        frame.add(textfield);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new imageOperation();
    }

    public static void operate(int key) throws IOException
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();


        // File reading and writing
        FileInputStream fin = new FileInputStream(file);
        byte[] data = new byte[fin.available()];
        fin.read(data);
        int i = 0;
        for(byte b: data)
        {
            data[i] =(byte)(b^key);
            i++;
        }
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
        fin.close();
        JOptionPane.showMessageDialog(null,"Done");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
