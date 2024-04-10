import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.lang.*;

public class BillingSystem extends JFrame
{
    ArrayList<Double> amount = new ArrayList<Double>();
    public JTextArea textArea;
    JButton submitdata, download_pdf, doneTextArea, intotextarea, clearpanel,done;
    JLabel customername,customerphone,item_Id,item_name, quantity, price;
    JTextField entername, enterphone,enterID,enter_productName,enterQuantity,enter_price;
    //JTextArea textArea;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    BillingSystem()
    {
        setBounds(300,100,1020,740);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel mainheading = new JLabel("XYZ Shop");
        mainheading.setBounds(350,20,500,50);
        mainheading.setFont(new Font("Rockwell Extra Bold",Font.PLAIN,50));
        add(mainheading);

        customername = new JLabel("Enter Name");
        customername.setBounds(20,86,500,50);
        customername.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        add(customername);

        entername = new JTextField();
        entername.setBounds(150,96,180,40);
        entername.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        add(entername);

        customerphone = new JLabel("Phone number");
        customerphone.setBounds(380,86,200,50);
        customerphone.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        add(customerphone);

        enterphone = new JTextField();
        enterphone.setBounds(530,96,180,40);
        enterphone.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        add(enterphone);

        submitdata = new JButton("Submit Data");
        submitdata.setBounds(770,96,200,50);
        submitdata.setFocusable(false);
        submitdata.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        submitdata.setBackground(Color.blue);
        submitdata.setForeground(Color.WHITE);
        add(submitdata);
        submitdata.addActionListener(this::submit_Button_Action);

        textArea = new JTextArea();
        textArea.setBounds(590,170,360,450);
        textArea.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        add(textArea);

        doneTextArea = new JButton("Done");
        doneTextArea.setBounds(600,640,130,50);
        doneTextArea.setFocusable(false);
        doneTextArea.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        doneTextArea.setBackground(Color.blue);
        doneTextArea.setForeground(Color.WHITE);
        doneTextArea.addActionListener(this::done_TextArea_Action);
        add(doneTextArea);

        download_pdf = new JButton("Print Bill");
        download_pdf.setBounds(750,640,200,50);
        download_pdf.setFocusable(false);
        download_pdf.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        download_pdf.setBackground(Color.blue);
        download_pdf.setForeground(Color.WHITE);
        download_pdf.addActionListener(this::download_pdf_Button_Action);
        add(download_pdf);

        JPanel panel = new JPanel();
        panel.setBounds(20,170,460,450);
        panel.setBorder(BorderFactory.createBevelBorder(1));
        panel.setLayout(null);
        add(panel);

        item_Id = new JLabel("Product ID:-");
        item_Id.setBounds(10,30,200,50);
        item_Id.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        panel.add(item_Id);

        enterID = new JTextField();
        enterID.setBounds(170,30,250,40);
        enterID.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        panel.add(enterID);

        item_name = new JLabel("Product Name:-");
        item_name.setBounds(10,110,200,50);
        item_name.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        panel.add(item_name);

        enter_productName = new JTextField();
        enter_productName.setBounds(170,110,250,40);
        enter_productName.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        panel.add(enter_productName);

        quantity = new JLabel("Enter Quantity");
        quantity.setBounds(10,190,200,50);
        quantity.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        panel.add(quantity);

        enterQuantity = new JTextField();
        enterQuantity.setBounds(170,190,250,40);
        enterQuantity.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        panel.add(enterQuantity);

        price = new JLabel("Enter Price:-");
        price.setBounds(10,270,200,50);
        price.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        panel.add(price);

        enter_price = new JTextField();
        enter_price.setBounds(170,270,250,40);
        enter_price.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        panel.add(enter_price);

        clearpanel= new JButton("Clear");
        clearpanel.setBounds(20,380,150,50);
        clearpanel.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        clearpanel.setBackground(Color.blue);
        clearpanel.setForeground(Color.WHITE);
        clearpanel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                clearpanel_Button_Action(evt);
            }
        });
        panel.add(clearpanel);

        done= new JButton("Add");
        done.setBounds(250,380,150,50);
        done.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        done.setBackground(Color.blue);
        done.setForeground(Color.WHITE);
        done.addActionListener(new java.awt.event.ActionListener()
                            {
                                public void actionPerformed(ActionEvent evt)
                                {
                                    done_Button_Action(evt);
                                }
                            });
        panel.add(done);

        intotextarea = new JButton("Exit");
        intotextarea.setBounds(100,640,300,50);
        intotextarea.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        intotextarea.setBackground(Color.blue);
        intotextarea.setForeground(Color.WHITE);
        intotextarea.addActionListener(this::Exit_Button_Action);
        add(intotextarea);


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }



    private void clearpanel_Button_Action(ActionEvent evt)
    {
        enter_price.setText(null);
        enterID.setText(null);
        enter_productName.setText(null);
        enterQuantity.setText(null);
    }

    private void Exit_Button_Action(ActionEvent evt)
    {
        System.exit(0);
    }


    private void done_Button_Action(ActionEvent evt)
    {
        if (enterID.getText().isEmpty() && enter_productName.getText().isEmpty() && enterQuantity.getText().isEmpty() && enter_price.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,  "Please Enter Products Details","Getting Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String productId = String.format(enterID.getText());
            String productname = String.format(enter_productName.getText());
            String productprice = String.format(enter_price.getText());
            int productquantity = Integer.parseInt(enterQuantity.getText());
            amount.add(Double.parseDouble(enter_price.getText()));
            textArea.append("\n" + productId + "   " + productname + "-" + productquantity + "\t  " + productprice);
        }
    }

    private void done_TextArea_Action(ActionEvent evt)
    {
        Double totalamount=0.0;
        for (Double i : amount) {
            totalamount += i;
        }
        textArea.append("\n============================================" +
                "\n\t Total Amount:-"+totalamount+
                "\n=============================================");

    }

    private void submit_Button_Action(ActionEvent evt)
    {
        if (entername.getText().isEmpty() && enterphone.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,  "Please Enter Customer Details","Getting Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            String name = String.format(entername.getText());
            String phone = String.format(enterphone.getText());
            textArea.append("************************************************" +
                    "\n\tXYZ Shop\t" +
                    "\n************************************************" +
                    "\n Date & Time :- " + dtf.format(now) +
                    "\n Name:- " + name +
                    "\n Phone Number:- " + phone +
                    "\n************************************************" +
                    "\nID   Name-Quantity\tPrice");
        }
    }

    private void download_pdf_Button_Action(ActionEvent evt)
    {
        try {
            textArea.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new BillingSystem();


    }

}

