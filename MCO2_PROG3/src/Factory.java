
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kriastiankintanar
 */
public class Factory implements ActionListener{
    private mainMenu mainMenu;
    private CreateVending createvending;
    private CreateRegularView createregularview;
    private CreateSpecialView createspecialview;
    private TestVending testvending;
    private TestVendFeatures testvendfeatures;
    private TestMaintenance testmaintenance;
    private RestockProduct restockproduct;
    private ChangePrice changeprice;
    private CollectBalance collectbalance;
    private Inventory inventory;
    private PrintSales printsales;
    private ReplenishBalance replenishbalance;
    
    public Factory (){
        this.mainMenu= new mainMenu();
        this.createvending = new CreateVending();
        this.createregularview = new CreateRegularView();
        this.createspecialview = new CreateSpecialView();
        this.testvending = new TestVending();
        this.testvendfeatures = new TestVendFeatures();
        this.testmaintenance = new TestMaintenance();
        this.restockproduct = new RestockProduct();
        this.changeprice = new ChangePrice();
        this.collectbalance = new CollectBalance();
        this.inventory = new Inventory();
        this.printsales = new PrintSales();
        this.replenishbalance = new ReplenishBalance();

        this.mainMenu.setVisible(true);
        
        // CREATE FEATURES
        this.mainMenu.setCreateBtnListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                createvending.setVisible(true);
                mainMenu.setVisible(false);
            }
        });

        this.createvending.setBackBtnListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                mainMenu.setVisible(true);
                createvending.setVisible(false);
            }
        });

        this.createvending.setCreateRegularListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                createregularview.setVisible(true);
                createvending.setVisible(false);
            }
        });

        this.createregularview.setSaveBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                mainMenu.setVisible(true);
                createregularview.setVisible(false);
            }
        });
        
        this.createvending.setCreateSpecialListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                createspecialview.setVisible(true);
                createvending.setVisible(false);
            }
        });
        
        this.createspecialview.setSaveBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                mainMenu.setVisible(true);
                createspecialview.setVisible(false);
            }
        });
        
        // TEST FEATURES   
        
        this.mainMenu.setTestBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testvending.setVisible(true);
                mainMenu.setVisible(false);
            }
        });

        this.testvending.setTestVendingListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testvendfeatures.setVisible(true);
                testvending.setVisible(false);
            }
        });

        this.testvendfeatures.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testvending.setVisible(true);
                testvendfeatures.setVisible(false);
            }
        });

        this.testvending.setTestMaintenanceListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(true);
                testvending.setVisible(false);
            }
        });

        this.testmaintenance.setBackBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testvending.setVisible(true);
                testmaintenance.setVisible(false);
            }
        });

        this.testvending.setBackBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                mainMenu.setVisible(true);
                testvending.setVisible(false);
            }
        });

        // MAINTENANCE PART

        this.testmaintenance.setRestockProductBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                restockproduct.setVisible(true);
                testmaintenance.setVisible(false);
            }
        });

        this.restockproduct.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(true);
                restockproduct.setVisible(false);
            }
        });

        this.testmaintenance.setReplenishBalanceBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                replenishbalance.setVisible(true);
                testmaintenance.setVisible(false);
            }
        });

        this.replenishbalance.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(true);
                replenishbalance.setVisible(false);
            }
        });

        this.testmaintenance.setCollectBalanceBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                collectbalance.setVisible(true);
                testmaintenance.setVisible(false);
            }
        });

        this.collectbalance.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(true);
                collectbalance.setVisible(false);
            }
        });

        this.testmaintenance.setChangePriceBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                changeprice.setVisible(true);
                testmaintenance.setVisible(false);
            }
        });

        this.changeprice.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(true);
                changeprice.setVisible(false);
            }
        });

        this.testmaintenance.setPrintSalesBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                printsales.setVisible(true);
                testmaintenance.setVisible(false);
            }
        });

        this.printsales.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(true);
                printsales.setVisible(false);
            }
        });

        this.testmaintenance.setInventoryBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                inventory.setVisible(true);
                testmaintenance.setVisible(false);
            }
        });

        this.inventory.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(true);
                inventory.setVisible(false);
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Create")){
            System.out.print("Create");
        }
    }
}
