
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
    private TestVending testvending;
    private TestVendFeatures testvendfeatures;
    private TestMaintenance testmaintenance;
    private RestockProduct restockproduct;
    private ChangePrice changeprice;
    private CollectBalance collectbalance;
    private Inventory inventory;
    private PrintSales printsales;
    private ReplenishBalance replenishbalance;

    private RegularMachine machine;
    private Slot slot;
    
    public Factory (){
        this.mainMenu= new mainMenu();
        this.createvending = new CreateVending();
        this.testvending = new TestVending();
        this.testvendfeatures = new TestVendFeatures();
        this.testmaintenance = new TestMaintenance();
        this.restockproduct = new RestockProduct();
        this.changeprice = new ChangePrice();
        this.collectbalance = new CollectBalance();
        this.inventory = new Inventory();
        this.printsales = new PrintSales();
        this.replenishbalance = new ReplenishBalance();
        this.slot = new Slot();

        this.mainMenu.setVisible(true);
        this.mainMenu.setTestEnable(false);
        
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
                JOptionPane.showMessageDialog(createvending, "Regular Vending Machine is successfully created!");
                machine = new RegularMachine(slot.regularItems());
                mainMenu.setTestEnable(true);
                mainMenu.setVisible(true);
                createvending.setVisible(false);
            }
        });
        
        this.createvending.setCreateSpecialListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(createvending, "Special Vending Machine is successfully created!");
                mainMenu.setVisible(true);
                createvending.setVisible(false);
            }
        });
        
        // TEST FEATURES  
        
        this.mainMenu.setTestBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // Add an conditional machine instanceof special
                loadRegularVending();
                testvending.setVisible(true);
                mainMenu.setVisible(false);
            }
        });


        this.testvending.setTestVendingListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testvendfeatures.setInsertCashEnable(false);
                testvendfeatures.setDispenseBtnEnable(false);
                testvendfeatures.setVisible(true);
                testvending.setVisible(false);
            }
        });

        this.testvendfeatures.setInsertCashListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int totalPayable = machine.getCashHandler().getTotalPayable();
                int getDenom = (Integer)testvendfeatures.getSelectedDenom();
                testvendfeatures.addInfoInMessageLbl("Inserting ₱" + getDenom + "...");
                totalPayable -= getDenom;
                machine.getCashHandler().setTotalPayable(totalPayable);
                if(totalPayable <= 0)
                {
                    testvendfeatures.addInfoInMessageLbl("Please click 'dispense'.");
                    testvendfeatures.setInsertCashEnable(false);
                    testvendfeatures.setDispenseBtnEnable(true);
                }
                else
                {
                    testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                }
            }
        });

        this.testvendfeatures.setPickSlot1BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // Add an conditional machine instanceof special
                disableAllPickBtns();
                Item item1 = machine.getArraySlots().get(0).checkItem();
                testvendfeatures.setOrderDetailsLbl(item1.getItemName());
                testvendfeatures.setMessageLbl("Please insert your payment.");
                testvendfeatures.setInsertCashEnable(true);
                machine.getCashHandler().setTotalPayable(((Milktea) item1).getPrice());
                int totalPayable = machine.getCashHandler().getTotalPayable();
                testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
            }
        });

        this.testvendfeatures.setPickSlot2BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                disableAllPickBtns();
                String itemName2 = machine.getArraySlots().get(1).checkItem().getItemName();
                testvendfeatures.setOrderDetailsLbl(itemName2);
                testvendfeatures.setMessageLbl("Please insert your payment.");
            }
        });

        this.testvendfeatures.setPickSlot3BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                disableAllPickBtns();
                String itemName3 = machine.getArraySlots().get(2).checkItem().getItemName();
                testvendfeatures.setOrderDetailsLbl(itemName3);
                testvendfeatures.setMessageLbl("Please insert your payment.");
            }
        });

        this.testvendfeatures.setPickSlot4BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                disableAllPickBtns();
                String itemName4 = machine.getArraySlots().get(3).checkItem().getItemName();
                testvendfeatures.setOrderDetailsLbl(itemName4);
                testvendfeatures.setMessageLbl("Please insert your payment.");
            }
        });

        this.testvendfeatures.setPickSlot5BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                disableAllPickBtns();
                String itemName5 = machine.getArraySlots().get(4).checkItem().getItemName();
                testvendfeatures.setOrderDetailsLbl(itemName5);
                testvendfeatures.setMessageLbl("Please insert your payment.");
            }
        });

        this.testvendfeatures.setPickSlot6BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                disableAllPickBtns();
                String itemName6 = machine.getArraySlots().get(5).checkItem().getItemName();
                testvendfeatures.setOrderDetailsLbl(itemName6);
                testvendfeatures.setMessageLbl("Please insert your payment.");
            }
        });

        this.testvendfeatures.setPickSlot7BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                disableAllPickBtns();
                String itemName7 = machine.getArraySlots().get(6).checkItem().getItemName();
                testvendfeatures.setOrderDetailsLbl(itemName7);
                testvendfeatures.setMessageLbl("Please insert your payment.");
            }
        });

        this.testvendfeatures.setPickSlot8BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                disableAllPickBtns();
                String itemName8 = machine.getArraySlots().get(7).checkItem().getItemName();
                testvendfeatures.setOrderDetailsLbl(itemName8);
                testvendfeatures.setMessageLbl("Please insert your payment.");
            }
        });

        this.testvendfeatures.setPickSlot9BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                disableAllPickBtns();
                String itemName9 = machine.getArraySlots().get(8).checkItem().getItemName();
                testvendfeatures.setOrderDetailsLbl(itemName9);
                testvendfeatures.setMessageLbl("Please insert your payment.");
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

        /*this.testmaintenance.setRestockProductBtnListener(new ActionListener() {
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
        });*/

    }

    public void loadRegularVending()
    {
        Item item1 = machine.getArraySlots().get(0).checkItem(); 
        testvendfeatures.setItemName1(item1.getItemName());
        testvendfeatures.setItemPrice1(((Milktea) item1).getPrice());
        testvendfeatures.setItemQuantity1(machine.getArraySlots().get(0).getNumberOfStock());
        testvendfeatures.setItemCalories1(item1.getItemCalories());
        testvendfeatures.setItemPicture1("/Elements/ItemElements/okinawa w_ pearls.png");
        
        Item item2 = machine.getArraySlots().get(1).checkItem(); 
        testvendfeatures.setItemName2(item2.getItemName());
        testvendfeatures.setItemPrice2(((Milktea) item2).getPrice());
        testvendfeatures.setItemQuantity2(machine.getArraySlots().get(1).getNumberOfStock());
        testvendfeatures.setItemCalories2(item2.getItemCalories());
        testvendfeatures.setItemPicture2("/Elements/ItemElements/okinawa w_ white pearls.png");

        Item item3 = machine.getArraySlots().get(2).checkItem(); 
        testvendfeatures.setItemName3(item3.getItemName());
        testvendfeatures.setItemPrice3(((Milktea) item3).getPrice());
        testvendfeatures.setItemQuantity3(machine.getArraySlots().get(2).getNumberOfStock());
        testvendfeatures.setItemCalories3(item3.getItemCalories());
        testvendfeatures.setItemPicture3("/Elements/ItemElements/okinawa w_ nata.png");
        
        Item item4 = machine.getArraySlots().get(3).checkItem(); 
        testvendfeatures.setItemName4(item4.getItemName());
        testvendfeatures.setItemPrice4(((Milktea) item4).getPrice());
        testvendfeatures.setItemQuantity4(machine.getArraySlots().get(3).getNumberOfStock());
        testvendfeatures.setItemCalories4(item4.getItemCalories());
        testvendfeatures.setItemPicture4("/Elements/ItemElements/wintermelon w_ pearls.png");

        Item item5 = machine.getArraySlots().get(4).checkItem(); 
        testvendfeatures.setItemName5(item5.getItemName());
        testvendfeatures.setItemPrice5(((Milktea) item5).getPrice());
        testvendfeatures.setItemQuantity5(machine.getArraySlots().get(4).getNumberOfStock());
        testvendfeatures.setItemCalories5(item5.getItemCalories());
        testvendfeatures.setItemPicture5("/Elements/ItemElements/wintermelon w_ white pearls.png");
        
        Item item6 = machine.getArraySlots().get(5).checkItem(); 
        testvendfeatures.setItemName6(item6.getItemName());
        testvendfeatures.setItemPrice6(((Milktea) item6).getPrice());
        testvendfeatures.setItemQuantity6(machine.getArraySlots().get(5).getNumberOfStock());
        testvendfeatures.setItemCalories6(item6.getItemCalories());
        testvendfeatures.setItemPicture6("/Elements/ItemElements/wintermelon w_ nat.png");

        Item item7 = machine.getArraySlots().get(6).checkItem(); 
        testvendfeatures.setItemName7(item7.getItemName());
        testvendfeatures.setItemPrice7(((Milktea) item7).getPrice());
        testvendfeatures.setItemQuantity7(machine.getArraySlots().get(6).getNumberOfStock());
        testvendfeatures.setItemCalories7(item7.getItemCalories());
        testvendfeatures.setItemPicture7("/Elements/ItemElements/hokkaido w_ pearls.png");
        
        Item item8 = machine.getArraySlots().get(7).checkItem(); 
        testvendfeatures.setItemName8(item8.getItemName());
        testvendfeatures.setItemPrice8(((Milktea) item8).getPrice());
        testvendfeatures.setItemQuantity8(machine.getArraySlots().get(7).getNumberOfStock());
        testvendfeatures.setItemCalories8(item8.getItemCalories());
        testvendfeatures.setItemPicture8("/Elements/ItemElements/hokkaido w_ white pearls.png");

        Item item9 = machine.getArraySlots().get(8).checkItem(); 
        testvendfeatures.setItemName9(item9.getItemName());
        testvendfeatures.setItemPrice9(((Milktea) item9).getPrice());
        testvendfeatures.setItemQuantity9(machine.getArraySlots().get(8).getNumberOfStock());
        testvendfeatures.setItemCalories9(item9.getItemCalories());
        testvendfeatures.setItemPicture9("/Elements/ItemElements/hokkaido w_ nata.png");
    }

    public void disableAllPickBtns()
    {
        testvendfeatures.setPickSlot1Enable(false);
        testvendfeatures.setPickSlot2Enable(false);
        testvendfeatures.setPickSlot3Enable(false);
        testvendfeatures.setPickSlot4Enable(false);
        testvendfeatures.setPickSlot5Enable(false);
        testvendfeatures.setPickSlot6Enable(false);
        testvendfeatures.setPickSlot7Enable(false);
        testvendfeatures.setPickSlot8Enable(false);
        testvendfeatures.setPickSlot9Enable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Create")){
            System.out.print("Create");
        }
    }
}
