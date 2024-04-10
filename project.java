import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.lang.*;
import java.awt.print.PrinterException;
import java.awt.print.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class project{
    public static void main(String[] args){
        new MyScreen("Project");
    }
}

class MyScreen extends JFrame{
    JLabel lname, lprice, lquantity, ltotal;
    JTextField tname, tprice, tquantity, ttotal;
    JButton addbtn, updbtn, delbtn, totalbtn, printbtn;
    JTable tbl;
    JScrollPane sp;
    JTextArea ta;
    DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime anow = LocalDateTime.now();

    MyScreen(String title){
        super(title);
        lname = new JLabel("Name:"); lprice = new JLabel("Price:"); lquantity = new JLabel("Quantity"); ltotal = new JLabel("Total Amount Payable : ");
        tname = new JTextField(); tprice = new JTextField(); tquantity = new JTextField(); ttotal = new JTextField();
        addbtn = new JButton("ADD"); updbtn = new JButton("UPDATE"); delbtn = new JButton("DELETE"); totalbtn = new JButton("TOTAL"); printbtn = new JButton("PRINT");


        lname.setBounds(100,50,100,30);
        lprice.setBounds(100,100,100,30);
        lquantity.setBounds(100,150,100,30);
        ltotal.setBounds(100,550,200,30);

        tname.setBounds(300,50,100,30);
        tprice.setBounds(300,100,100,30);
        tquantity.setBounds(300,150,100,30);
        ttotal.setBounds(300,550,100,30);

        addbtn.setBounds(100,300,100,50);
        updbtn.setBounds(250,300,100,50);
        delbtn.setBounds(400,300,100,50);
        totalbtn.setBounds(550,300,100,50);
        printbtn.setBounds(500,550,100,50);

        Object[][] data = {};

        String[] col = {"Name","Price","Quantity"};

        DefaultTableModel model = new DefaultTableModel(data, col);

        tbl = new JTable(model);
        sp = new JScrollPane(tbl);
        sp.setBounds(100,400,600,150);

        addbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tname.getText().isEmpty() || tprice.getText().isEmpty() || tquantity.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please Enter All Details","ERR",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = tname.getText();
                int price = Integer.parseInt(tprice.getText());
                int quantity = Integer.parseInt(tquantity.getText());

                Object[] row = {name,price,quantity};
                model.addRow(row);

                tname.setText(null);
                tprice.setText(null);
                tquantity.setText(null);
            }
        });

        tbl.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){

                int rowIndex = tbl.getSelectedRow();

                String name = (String)model.getValueAt(rowIndex,0);
                int price = (int)model.getValueAt(rowIndex,1);
                int quantity = (int)model.getValueAt(rowIndex,2);

                tname.setText(String.valueOf(name));
                tprice.setText(String.valueOf(price));
                tquantity.setText(String.valueOf(quantity));

            }
        });

        updbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tname.getText().isEmpty() || tprice.getText().isEmpty() || tquantity.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please Select Something To Update","ERR",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = tname.getText();
                int price = Integer.parseInt(tprice.getText());
                int quantity = Integer.parseInt(tquantity.getText());

                int rw = tbl.getSelectedRow();

                model.setValueAt(name,rw,0);
                model.setValueAt(price,rw,1);
                model.setValueAt(quantity,rw,2);

                tname.setText(null);
                tprice.setText(null);
                tquantity.setText(null);
            }
        });

        delbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tbl.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null,"Please Select Something To DELETE!","ERR",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int selection = JOptionPane.showConfirmDialog(null,"Do you really want to delete??","Confirm",JOptionPane.YES_NO_OPTION);

                if(selection == JOptionPane.YES_OPTION){
                    model.removeRow(tbl.getSelectedRow());
                    tname.setText(null);
                    tprice.setText(null);
                    tquantity.setText(null);
                }
            }
        });

        totalbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                double sum=0;
                int trws = model.getRowCount();

                if(trws > 0){
                    for(int i=0;i<trws;i++){
                        int p = (int)model.getValueAt(i,1);
                        int q = (int)model.getValueAt(i,2);
                        sum += (p*q);
                    }
                }

                String tt = String.valueOf(sum);
                ttotal.setText(tt);
            }
        });

        printbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int trws = model.getRowCount();
                if(trws > 0){
                    StringBuilder printcontent = new StringBuilder("\n******************************INVOICE*****************************\n");
                    printcontent.append("\n\nDate & Time :- " + date.format(anow)+"\n\n");
                    printcontent.append("\n=================================================================");
                    printcontent.append("\n\n\t\tName\t\tPrice\t\tQuantity\n\n");
                    for(int i=0;i<trws;i++){
                        String n = (String)model.getValueAt(i,0);
                        int p = (int)model.getValueAt(i,1);
                        int q = (int)model.getValueAt(i,2);
                        printcontent.append("\n"+(i+1)+")\t\t"+n+"\t\t"+p+"\t\t"+q+"\n");
                    }
                    printcontent.append("\n\n\n\n\t\t\t\tTotal Payable:  "+ttotal.getText());
                    printcontent.append("\n\n=================================================================\n");
                    printcontent.append("\n\t\t\tTHANK  YOU :) COME AGAIN ! ! \n");
                    printcontent.append("\n******************************************************************\n");
                    // System.out.println(printcontent);

                    ta = new JTextArea();
                    ta.append(printcontent.toString());
                        try{
                            ta.print();
                        }catch(PrinterException pe){}
                }
            }
        });

        setLayout(null);
        add(lname);add(tname);
        add(lprice);add(tprice);
        add(lquantity);add(tquantity);
        add(addbtn);add(updbtn);add(delbtn);add(totalbtn);
        add(sp);
        add(ltotal);add(ttotal);add(printbtn);

        setSize(800,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        validate();
    }
}