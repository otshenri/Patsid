package ee.ut.math.tvt.salessystem.ui.tabs;
 
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
 
 
 
 
 
 
 
 
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
 
 
 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
 
 
public class StockTab{
 
  private JButton addItem;
 
  private SalesSystemModel model;
 
  public StockTab(SalesSystemModel model) {
    this.model = model;
  }
 
  // warehouse stock tab - consists of a menu and a table
  public Component draw() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
 
    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints gc = new GridBagConstraints();
    panel.setLayout(gb);
 
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;
 
    panel.add(drawStockMenuPane(), gc);
 
    gc.weighty = 1.0;
    gc.fill = GridBagConstraints.BOTH;
    panel.add(drawStockMainPane(), gc);
   
    return panel;
  }
 
  // warehouse menu
  private Component drawStockMenuPane() {
    JPanel panel = new JPanel();
 
    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
 
    panel.setLayout(gb);
 
    gc.anchor = GridBagConstraints.NORTHWEST;
    gc.weightx = 0;
 
    addItem = new JButton("Add");
    gc.gridwidth = GridBagConstraints.RELATIVE;
    gc.weightx = 1.0;
    panel.add(addItem, gc);
   
    addItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addItemEventHandler();
        }
    });
 
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return panel;
  }
 
 
  // table of the wareshouse stock
  private Component drawStockMainPane() {
    JPanel panel = new JPanel();
 
    JTable table = new JTable(model.getWarehouseTableModel());
 
    JTableHeader header = table.getTableHeader();
    header.setReorderingAllowed(false);
 
    JScrollPane scrollPane = new JScrollPane(table);
 
    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    gc.fill = GridBagConstraints.BOTH;
    gc.weightx = 1.0;
    gc.weighty = 1.0;
 
    panel.setLayout(gb);
    panel.add(scrollPane, gc);
 
    panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
    return panel;
  }
 
  public void addItemEventHandler() {
          JTextField name = new JTextField();
          JTextField description = new JTextField();
          JTextField price = new JTextField();
          JTextField quantity = new JTextField();
          JTextField id = new JTextField();
          Object[] message = {
              "Product name:", name,
              "Description:", description,
              "Price", price,
              "Quantity:", quantity,
              "Id:", id
          };
 
          int option = JOptionPane.showConfirmDialog(null, message, "Add item", JOptionPane.OK_CANCEL_OPTION);
         
          if (option == JOptionPane.OK_OPTION) {
                  StockItem uus = new StockItem(Long.parseLong(id.getText()), name.getText(), description.getText(), Double.parseDouble(price.getText()), Integer.parseInt(quantity.getText()));
                  ObjectOutputStream output;
                try {
                        output = new ObjectOutputStream(
                                    new FileOutputStream("stockitems.data"));
                        output.writeObject(uus);
 
                        output.close();
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
 
                       
                 
          } else {
              System.out.println("Adding item cancelled");
          }
//          final JPanel panel = new JPanel();
//          JOptionPane.showMessageDialog(panel, "Not enough items in warehouse!", "Warning",
//                          JOptionPane.WARNING_MESSAGE);
//          JTextFieldDemo test = new JTextFieldDemo();
//          test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
 
}