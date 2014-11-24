package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.HistoryModel;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "Point-of-sale" in the menu).
 */
public class PurchaseTab extends PurchaseInfoTableModel{

  private static final Logger log = Logger.getLogger(PurchaseTab.class);

  private final SalesDomainController domainController;

  private JButton newPurchase;

  private JButton submitPurchase;

  private JButton cancelPurchase;

  private PurchaseItemPanel purchasePane;

  private SalesSystemModel model;
  
  double change;


  public PurchaseTab(SalesDomainController controller,
      SalesSystemModel model)
  {
    this.domainController = controller;
    this.model = model;
  }


  /**
   * The purchase tab. Consists of the purchase menu, current purchase dialog and
   * shopping cart table.
   */
  public Component draw() {
    JPanel panel = new JPanel();

    // Layout
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setLayout(new GridBagLayout());

    // Add the purchase menu
    panel.add(getPurchaseMenuPane(), getConstraintsForPurchaseMenu());

    // Add the main purchase-panel
    purchasePane = new PurchaseItemPanel(model);
    panel.add(purchasePane, getConstraintsForPurchasePanel());

    return panel;
  }




  // The purchase menu. Contains buttons "New purchase", "Submit", "Cancel".
  private Component getPurchaseMenuPane() {
    JPanel panel = new JPanel();

    // Initialize layout
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gc = getConstraintsForMenuButtons();

    // Initialize the buttons
    newPurchase = createNewPurchaseButton();
    submitPurchase = createConfirmButton();
    cancelPurchase = createCancelButton();

    // Add the buttons to the panel, using GridBagConstraints we defined above
    panel.add(newPurchase, gc);
    panel.add(submitPurchase, gc);
    panel.add(cancelPurchase, gc);

    return panel;
  }


  // Creates the button "New purchase"
  private JButton createNewPurchaseButton() {
    JButton b = new JButton("New purchase");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        newPurchaseButtonClicked();
      }
    });

    return b;
  }

  // Creates the "Confirm" button
  private JButton createConfirmButton() {
    JButton b = new JButton("Confirm");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        submitPurchaseButtonClicked();
      }
    });
    b.setEnabled(false);

    return b;
  }


  // Creates the "Cancel" button
  private JButton createCancelButton() {
    JButton b = new JButton("Cancel");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancelPurchaseButtonClicked();
      }
    });
    b.setEnabled(false);

    return b;
  }





  /* === Event handlers for the menu buttons
   *     (get executed when the buttons are clicked)
   */


  /** Event handler for the <code>new purchase</code> event. */
  protected void newPurchaseButtonClicked() {
    log.info("New sale process started");
    try {
      domainController.startNewPurchase();
      startNewSale();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }


  /**  Event handler for the <code>cancel purchase</code> event. */
  protected void cancelPurchaseButtonClicked() {
    log.info("Sale cancelled");
    try {
      domainController.cancelCurrentPurchase();
      endSale();
      model.getCurrentPurchaseTableModel().clear();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }

  
  /** Event handler for the <code>submit purchase</code> event. */
  

  
  /*Object[] options = {"Yes, please",
  "No way!"};*/
  // tekitab paned ja peaks arvutama vahetusraha ja salvestama ostu....
  public void Payment() {
	  double summa; 
	  double x;
	  boolean outOfRek = false;
	  summa = getTotalSum(); 
	  	String s = (String)JOptionPane.showInputDialog(
	  			null,
	  			"Total: " + summa,
	  			"Payment",
	  			JOptionPane.PLAIN_MESSAGE,
	  			null,
	  			null, null
	  			);
	  	if ((s != null) && (s.length() > 0)) {
	  		try {
	  			x =Double.parseDouble(s);
	  			if (x < summa){
	  				log.error("Not enough money");
	  				outOfRek = true;
	  				Payment();
	  			}
	  		}
	  		catch (IllegalArgumentException ex){
	  			log.error("That is not valid amount");
	  			x = 0;
	  			Payment();
	  			outOfRek = true;
	  			
	  		}
	  		if (!outOfRek){
	  			setChange(x - summa);
		  		JOptionPane.showMessageDialog(null,"Change: "+change);
		  		model.getCurrentPurchaseTableModel().clear();
		  		ArrayList<SoldItem> kloon = new ArrayList<SoldItem>();
		  		for (SoldItem item: model.getCurrentPurchaseTableModel().solditems){
		  			kloon.add(item);
		  		}
		  		HistoryItem hitem = new HistoryItem(summa, kloon );
		  		model.getHistoryModel().addItem(hitem);
		  		try {
					model.getDomainController().submitCurrentPurchase2(hitem);
				} catch (VerificationFailedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  		//System.out.println(model.getCurrentPurchaseTableModel().solditems.size());
		  		for (SoldItem item: model.getCurrentPurchaseTableModel().solditems){
		  			
		  			int quant = model.getWarehouseTableModel().getItemById(item.getId()).getQuantity();
		  			
		  			model.getWarehouseTableModel().getItemById(item.getId()).setQuantity(quant-item.getQuantity());
		  		}
		  		model.getCurrentPurchaseTableModel().solditems.clear();
		  		
		  		endSale();	  			
	  		}
	  		
}
  }
  
  
  protected void submitPurchaseButtonClicked() {
    
    try {
      log.info("Contents of the current basket:\n" + model.getCurrentPurchaseTableModel());
      domainController.submitCurrentPurchase(
          model.getCurrentPurchaseTableModel().getTableRows()
      );
      
      endSale();
      Payment();
      model.getCurrentPurchaseTableModel().clear();
      log.info("Sale complete");
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
    
    
   

  
   
  }



  /* === Helper methods that bring the whole purchase-tab to a certain state
   *     when called.
   */

  public double getChange() {
	return change;
}


public void setChange(double change) {
	this.change = change;
}


// switch UI to the state that allows to proceed with the purchase
  private void startNewSale() {
    purchasePane.reset();

    purchasePane.setEnabled(true);
    submitPurchase.setEnabled(true);
    cancelPurchase.setEnabled(true);
    newPurchase.setEnabled(false);
  }

  // switch UI to the state that allows to initiate new purchase
  private void endSale() {
    purchasePane.reset();

    cancelPurchase.setEnabled(false);
    submitPurchase.setEnabled(false);
    newPurchase.setEnabled(true);
    purchasePane.setEnabled(false);
  }




  /* === Next methods just create the layout constraints objects that control the
   *     the layout of different elements in the purchase tab. These definitions are
   *     brought out here to separate contents from layout, and keep the methods
   *     that actually create the components shorter and cleaner.
   */

  private GridBagConstraints getConstraintsForPurchaseMenu() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    return gc;
  }


  private GridBagConstraints getConstraintsForPurchasePanel() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.BOTH;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 1.0;

    return gc;
  }


  // The constraints that control the layout of the buttons in the purchase menu
  private GridBagConstraints getConstraintsForMenuButtons() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.weightx = 0;
    gc.anchor = GridBagConstraints.CENTER;
    gc.gridwidth = GridBagConstraints.RELATIVE;

    return gc;
  }
  public double getTotalSum(){
	  double sum = 0;
	  if (!(model.getCurrentPurchaseTableModel().getRows()==null)){
		  
		  for (int i =0; i<model.getCurrentPurchaseTableModel().getRows().size(); i++){
			  sum = sum + model.getCurrentPurchaseTableModel().getRows().get(i).getSum();
		  }		  
	  }
	  
	  return sum;
	  
  }

}
