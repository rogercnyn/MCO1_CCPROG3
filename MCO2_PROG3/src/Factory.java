
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Action;
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
    private TestMaintenanceFeatures testmaintenance;
    private RestockProduct restockproduct;
    private ChangePrice changeprice;
    private CollectBalance collectbalance;
    private Inventory inventory;
    private PrintSales printsales;
    private ReplenishBalance replenishbalance;
    private ReStock_Item restock;

    private RegularMachine machine;
    private Slot slot;
    
    public Factory (){
        this.mainMenu= new mainMenu();
        this.createvending = new CreateVending();
        this.testvending = new TestVending();
        this.testvendfeatures = new TestVendFeatures();
        this.testmaintenance = new TestMaintenanceFeatures();
        this.restockproduct = new RestockProduct();
        this.changeprice = new ChangePrice();
        this.collectbalance = new CollectBalance();
        this.inventory = new Inventory();
        this.printsales = new PrintSales();
        this.replenishbalance = new ReplenishBalance();
        this.slot = new Slot();
        this.restock = new ReStock_Item();

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
                loadRegularVending();
                testvendfeatures.setInsertCashEnable(false);
                testvendfeatures.setDispenseBtnEnable(false);
                testvendfeatures.setCancelBtnEnable(false);
                testvendfeatures.setDenomComboBoxValues(machine.getCashHandler().getAcceptedDenom());
                testvendfeatures.setVisible(true);
                testvending.setVisible(false);
            }
        });

        this.testvendfeatures.setInsertCashListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testvendfeatures.setCancelBtnEnable(true);
                int totalPayable = machine.getCashHandler().getTotalPayable();
                int getDenom = (Integer)testvendfeatures.getSelectedDenom();
                testvendfeatures.addInfoInMessageLbl("Inserting ₱" + getDenom + "...");
                machine.getCashHandler().addDenomToPayment(getDenom);
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
                if (machine.getArraySlots().get(0).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot1Enable(false);
                }

                else
                {
                    disableAllPickBtns();
                    Item item1 = machine.getArraySlots().get(0).checkItem();
                    testvendfeatures.setOrderDetailsLbl(item1.getItemName());
                    testvendfeatures.setMessageLbl("Please insert your payment.");
                    testvendfeatures.setInsertCashEnable(true);
                    machine.getCashHandler().setTotalPayable(((Milktea) item1).getPrice());
                    machine.setChosenItem(item1);
                    int totalPayable = machine.getCashHandler().getTotalPayable();
                    testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                }
                
            }
        });

        this.testvendfeatures.setPickSlot2BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine.getArraySlots().get(1).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot2Enable(false);
                }

                else
                {
                    disableAllPickBtns();
                    Item item2 = machine.getArraySlots().get(1).checkItem();
                    testvendfeatures.setOrderDetailsLbl(item2.getItemName());
                    testvendfeatures.setMessageLbl("Please insert your payment.");
                    testvendfeatures.setInsertCashEnable(true);
                    machine.getCashHandler().setTotalPayable(((Milktea) item2).getPrice());
                    machine.setChosenItem(item2);
                    int totalPayable = machine.getCashHandler().getTotalPayable();
                    testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                }
                
            }
        });

        this.testvendfeatures.setPickSlot3BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine.getArraySlots().get(2).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot3Enable(false);
                }
                
                else
                {
                    disableAllPickBtns();
                    Item item3 = machine.getArraySlots().get(2).checkItem();
                    testvendfeatures.setOrderDetailsLbl(item3.getItemName());
                    testvendfeatures.setMessageLbl("Please insert your payment.");
                    testvendfeatures.setInsertCashEnable(true);
                    machine.getCashHandler().setTotalPayable(((Milktea) item3).getPrice());
                    machine.setChosenItem(item3);
                    int totalPayable = machine.getCashHandler().getTotalPayable();
                    testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                }
            }
        });

        this.testvendfeatures.setPickSlot4BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine.getArraySlots().get(3).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot4Enable(false);
                }
                else
                {
                    disableAllPickBtns();
                    Item item4 = machine.getArraySlots().get(3).checkItem();
                    testvendfeatures.setOrderDetailsLbl(item4.getItemName());
                    testvendfeatures.setMessageLbl("Please insert your payment.");
                    testvendfeatures.setInsertCashEnable(true);
                    machine.getCashHandler().setTotalPayable(((Milktea) item4).getPrice());
                    machine.setChosenItem(item4);
                    int totalPayable = machine.getCashHandler().getTotalPayable();
                    testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                }
            }
        });

        this.testvendfeatures.setPickSlot5BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine.getArraySlots().get(4).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot5Enable(false);
                }

                else
                {
                    disableAllPickBtns();
                    Item item5 = machine.getArraySlots().get(4).checkItem();
                    testvendfeatures.setOrderDetailsLbl(item5.getItemName());
                    testvendfeatures.setMessageLbl("Please insert your payment.");
                    testvendfeatures.setInsertCashEnable(true);
                    machine.getCashHandler().setTotalPayable(((Milktea) item5).getPrice());
                    machine.setChosenItem(item5);
                    int totalPayable = machine.getCashHandler().getTotalPayable();
                    testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                }
            }
        });

        this.testvendfeatures.setPickSlot6BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine.getArraySlots().get(5).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot6Enable(false);
                }
                
                else
                {
                    disableAllPickBtns();
                    Item item6 = machine.getArraySlots().get(5).checkItem();
                    testvendfeatures.setOrderDetailsLbl(item6.getItemName());
                    testvendfeatures.setMessageLbl("Please insert your payment.");
                    testvendfeatures.setInsertCashEnable(true);
                    machine.getCashHandler().setTotalPayable(((Milktea) item6).getPrice());
                    machine.setChosenItem(item6);
                    int totalPayable = machine.getCashHandler().getTotalPayable();
                    testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                }
            }
        });

        this.testvendfeatures.setPickSlot7BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine.getArraySlots().get(6).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot7Enable(false);
                }
                else
                {
                    disableAllPickBtns();
                    Item item7 = machine.getArraySlots().get(6).checkItem();
                    testvendfeatures.setOrderDetailsLbl(item7.getItemName());
                    testvendfeatures.setMessageLbl("Please insert your payment.");
                    testvendfeatures.setInsertCashEnable(true);
                    machine.getCashHandler().setTotalPayable(((Milktea) item7).getPrice());
                    machine.setChosenItem(item7);
                    int totalPayable = machine.getCashHandler().getTotalPayable();
                    testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                }
            }
        });

        this.testvendfeatures.setPickSlot8BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine.getArraySlots().get(7).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot8Enable(false);
                }
                else
                {
                    disableAllPickBtns();
                    Item item8 = machine.getArraySlots().get(7).checkItem();
                    testvendfeatures.setOrderDetailsLbl(item8.getItemName());
                    testvendfeatures.setMessageLbl("Please insert your payment.");
                    testvendfeatures.setInsertCashEnable(true);
                    machine.getCashHandler().setTotalPayable(((Milktea) item8).getPrice());
                    machine.setChosenItem(item8);
                    int totalPayable = machine.getCashHandler().getTotalPayable();
                    testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                }
            }
        });

        this.testvendfeatures.setPickSlot9BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine.getArraySlots().get(8).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot9Enable(false);
                }

                else
                {
                    disableAllPickBtns();
                    Item item9 = machine.getArraySlots().get(8).checkItem();
                    testvendfeatures.setOrderDetailsLbl(item9.getItemName());
                    testvendfeatures.setMessageLbl("Please insert your payment.");
                    testvendfeatures.setInsertCashEnable(true);
                    machine.getCashHandler().setTotalPayable(((Milktea) item9).getPrice());
                    machine.setChosenItem(item9);
                    int totalPayable = machine.getCashHandler().getTotalPayable();
                    testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                }
            }
        });

        this.testvendfeatures.setDispenseBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int itemIndex = machine.getChosenItemIndex();
                boolean isSuccess = machine.getCashHandler().produceChange();
                if (isSuccess)
                {
                    machine.getArraySlots().get(itemIndex).dispenseItem();
                    JOptionPane.showMessageDialog(testvendfeatures, machine.getCashHandler().successChange());
                    machine.addTransactions();
                }

                else
                {
                    JOptionPane.showMessageDialog(testvendfeatures, machine.getCashHandler().failChange());
                }
                loadRegularVending();
                restartTestingVend();
            }
        });

        this.testvendfeatures.setCancelBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(testvendfeatures, machine.getCashHandler().cancel());
                restartTestingVend();
            }
        });

        this.testvendfeatures.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                restartTestingVend();
                testvending.setVisible(true);
                testvendfeatures.setVisible(false);
            }
        });

        this.testvending.setTestMaintenanceListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                loadRegularVendingmaintenance();
                testmaintenance.setVisible(true);
                testvending.setVisible(false);
                testmaintenance.disablePickBtn();
            }
        });

        this.testmaintenance.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testvending.setVisible(true);
                testmaintenance.setVisible(false);
            }
        });
        
        this.testmaintenance.setrestockBtnListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.enablePickBtn();
                testmaintenance.setPickSlot1BtnListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        testmaintenance.setVisible(false);
                        Item item1 = machine.getArraySlots().get(0).checkItem();
                        restock.setItemNameLbl(item1.getItemName());
                        restock.setVisible(true);
                        restock.setAddBtnListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                machine.getArraySlots().get(0).addItem(item1,restock.getQuantity());
                                restock.setVisible(false);
                                loadRegularVendingmaintenance();
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                testmaintenance.disablePickBtn();
                            }          
                        });
                    }
                });
                testmaintenance.setPickSlot2BtnListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        testmaintenance.setVisible(false);
                        Item item2 = machine.getArraySlots().get(1).checkItem();
                        restock.setItemNameLbl(item2.getItemName());
                        restock.setVisible(true);
                        restock.setAddBtnListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                machine.getArraySlots().get(1).addItem(item2,restock.getQuantity());
                                restock.setVisible(false);
                                loadRegularVendingmaintenance();
                                testmaintenance.setVisible(true);    
                                restock.cleartxtarea();
                                testmaintenance.disablePickBtn();                          
                            }
                        });            
                    }
                });
                        
                testmaintenance.setPickSlot3BtnListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        testmaintenance.setVisible(false);
                        Item item3 = machine.getArraySlots().get(2).checkItem();
                        restock.setItemNameLbl(item3.getItemName());
                        restock.setVisible(true);
                        restock.setAddBtnListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                machine.getArraySlots().get(2).addItem(item3,restock.getQuantity());
                                restock.setVisible(false);
                                loadRegularVendingmaintenance();
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                testmaintenance.disablePickBtn();
                            }
                        });            
                    }
                });
                testmaintenance.setPickSlot4BtnListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        testmaintenance.setVisible(false);
                        Item item4 = machine.getArraySlots().get(3).checkItem();
                        restock.setItemNameLbl(item4.getItemName());
                        restock.setVisible(true);
                        restock.setAddBtnListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                machine.getArraySlots().get(3).addItem(item4,restock.getQuantity());
                                restock.setVisible(false);
                                loadRegularVendingmaintenance();
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                testmaintenance.disablePickBtn();
                            }
                        });            
                    }
                });
                testmaintenance.setPickSlot5BtnListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        testmaintenance.setVisible(false);
                        Item item5 = machine.getArraySlots().get(4).checkItem();
                        restock.setItemNameLbl(item5.getItemName());
                        restock.setVisible(true);
                        restock.setAddBtnListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                machine.getArraySlots().get(4).addItem(item5,restock.getQuantity());
                                restock.setVisible(false);
                                loadRegularVendingmaintenance();
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                testmaintenance.disablePickBtn();
                            }
                        });            
                    }
                });
                testmaintenance.setPickSlot6BtnListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                       testmaintenance.setVisible(false);
                        Item item6 = machine.getArraySlots().get(5).checkItem();
                        restock.setItemNameLbl(item6.getItemName());
                        restock.setVisible(true);
                        restock.setAddBtnListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                machine.getArraySlots().get(5).addItem(item6,restock.getQuantity());
                                restock.setVisible(false);
                                loadRegularVendingmaintenance();
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                testmaintenance.disablePickBtn();
                            }
                        });            
                    }
                });
                testmaintenance.setPickSlot7BtnListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        testmaintenance.setVisible(false);
                        Item item7 = machine.getArraySlots().get(6).checkItem();
                        restock.setItemNameLbl(item7.getItemName());
                        restock.setVisible(true);
                        restock.setAddBtnListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                machine.getArraySlots().get(6).addItem(item7,restock.getQuantity());
                                restock.setVisible(false);
                                loadRegularVendingmaintenance();
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                testmaintenance.disablePickBtn();
                            }
                        });            
                    }
                });
                testmaintenance.setPickSlot8BtnListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        testmaintenance.setVisible(false);
                        Item item8 = machine.getArraySlots().get(7).checkItem();
                        restock.setItemNameLbl(item8.getItemName());
                        restock.setVisible(true);
                        restock.setAddBtnListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                machine.getArraySlots().get(7).addItem(item8,restock.getQuantity());
                                restock.setVisible(false);
                                loadRegularVendingmaintenance();
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                testmaintenance.disablePickBtn();
                            }
                        });            
                    }
                });
                testmaintenance.setPickSlot9BtnListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        testmaintenance.setVisible(false);
                        Item item9 = machine.getArraySlots().get(8).checkItem();
                        restock.setItemNameLbl(item9.getItemName());
                        restock.setVisible(true);
                        restock.setAddBtnListener(new ActionListener()
                        {
                            public void actionPerformed(ActionEvent e)
                            {
                                machine.getArraySlots().get(8).addItem(item9,restock.getQuantity());
                                restock.setVisible(false);
                                loadRegularVendingmaintenance();
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                testmaintenance.disablePickBtn();
                            }
                        });            
                    }
                });
            }
        });

        this.testvending.setBackBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                mainMenu.setVisible(true);
                testvending.setVisible(false);
            }
        });

        this.testmaintenance.setChangePrice(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.enablePickBtn();

                testmaintenance.setPickSlot1BtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        changeprice.setVisible(true);
                        testmaintenance.setVisible(false);
                        changeprice.setChangePriceBtnListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                                Item item1 = machine.getArraySlots().get(0).checkItem();
                                int newprice = changeprice.getNewPrice();
                                ((Milktea)item1).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.setVisible(false);
                            }
                        });
                    }
                });

                testmaintenance.setPickSlot2BtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        changeprice.setVisible(true);
                        testmaintenance.setVisible(false);     
                        changeprice.setChangePriceBtnListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                                Item item2 = machine.getArraySlots().get(1).checkItem();
                                int newprice = changeprice.getNewPrice();
                                ((Milktea)item2).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.setVisible(false);
                            }
                        });
                    }
                });

                testmaintenance.setPickSlot3BtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        changeprice.setVisible(true);
                        testmaintenance.setVisible(false);
                        changeprice.setChangePriceBtnListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                                Item item3 = machine.getArraySlots().get(2).checkItem();
                                int newprice = changeprice.getNewPrice();
                                ((Milktea)item3).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.setVisible(false);
                            }
                        });
                    }
                });

                testmaintenance.setPickSlot4BtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        changeprice.setVisible(true);
                        testmaintenance.setVisible(false);
                        changeprice.setChangePriceBtnListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                                Item item4 = machine.getArraySlots().get(3).checkItem();
                                int newprice = changeprice.getNewPrice();
                                ((Milktea)item4).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.setVisible(false);
                            }
                        });
                    }
                });


                testmaintenance.setPickSlot5BtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        changeprice.setVisible(true);
                        testmaintenance.setVisible(false);
                        changeprice.setChangePriceBtnListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                                Item item5 = machine.getArraySlots().get(4).checkItem();
                                int newprice = changeprice.getNewPrice();
                                ((Milktea)item5).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.setVisible(false);
                            }
                        });
                    }
                });

                testmaintenance.setPickSlot6BtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        changeprice.setVisible(true);
                        testmaintenance.setVisible(false);
                        changeprice.setChangePriceBtnListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                                Item item6 = machine.getArraySlots().get(5).checkItem();
                                int newprice = changeprice.getNewPrice();
                                ((Milktea)item6).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.setVisible(false);
                            }
                        });
                    }
                });

                testmaintenance.setPickSlot7BtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        changeprice.setVisible(true);
                        testmaintenance.setVisible(false);
                        changeprice.setChangePriceBtnListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                                Item item7 = machine.getArraySlots().get(6).checkItem();
                                int newprice = changeprice.getNewPrice();
                                ((Milktea)item7).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.setVisible(false);
                            }
                        });
                    }
                });

                testmaintenance.setPickSlot8BtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        changeprice.setVisible(true);
                        testmaintenance.setVisible(false);
                        changeprice.setChangePriceBtnListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                                Item item8 = machine.getArraySlots().get(7).checkItem();
                                int newprice = changeprice.getNewPrice();
                                ((Milktea)item8).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.setVisible(false);
                            }
                        });
                    }
                });

                testmaintenance.setPickSlot9BtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        changeprice.setVisible(true);
                        testmaintenance.setVisible(false);
                        changeprice.setChangePriceBtnListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                                Item item9 = machine.getArraySlots().get(8).checkItem();
                                int newprice = changeprice.getNewPrice();
                                ((Milktea)item9).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.setVisible(false);
                            }
                        });
                    }
                });

            }
        });

        this.testmaintenance.setReStockBalListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(false);
                replenishbalance.setVisible(true);
                replenishbalance.setReplenishBtn(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int i;
                        for(i=0;i<replenishbalance.getBal1();i++)
                        {
                            machine.getCashHandler().addQuantityToBalance(0);  
                        }
                        for(i=0;i<replenishbalance.getBal10();i++)
                        {
                            machine.getCashHandler().addQuantityToBalance(0);  
                        }
                        for(i=0;i<replenishbalance.getBal100();i++)
                        {
                            machine.getCashHandler().addQuantityToBalance(0);  
                        }
                        for(i=0;i<replenishbalance.getBal1000();i++)
                        {
                            machine.getCashHandler().addQuantityToBalance(0);  
                        }
                        for(i=0;i<replenishbalance.getBal20();i++)
                        {
                            machine.getCashHandler().addQuantityToBalance(0);  
                        }
                        for(i=0;i<replenishbalance.getBal200();i++)
                        {
                            machine.getCashHandler().addQuantityToBalance(0);  
                        }
                        for(i=0;i<replenishbalance.getBal5();i++)
                        {
                            machine.getCashHandler().addQuantityToBalance(0);  
                        }
                        for(i=0;i<replenishbalance.getBal5();i++)
                        {
                            machine.getCashHandler().addQuantityToBalance(0);  
                        }
                        for(i=0;i<replenishbalance.getBal50();i++)
                        {
                            machine.getCashHandler().addQuantityToBalance(0);  
                        }
                        for(i=0;i<replenishbalance.getBal500();i++)
                        {
                            machine.getCashHandler().addQuantityToBalance(0);  
                        }
                    }
                });
                
            }
        });

        this.testmaintenance.setCollectBalListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(false);
                collectbalance.setVisible(true);
                int[] denoms = machine.getCashHandler().getAcceptedDenom();
                collectbalance.setTextArea(machine.getCashHandler().printDenom(1) + "\n" + machine.getCashHandler().printDenom(5) + "\n" + 
                                            machine.getCashHandler().printDenom(10) + "\n" + machine.getCashHandler().printDenom(50) + "\n" +
                                            machine.getCashHandler().printDenom(100) + "\n" + machine.getCashHandler().printDenom(200) + "\n" +
                                            machine.getCashHandler().printDenom(500) + "\n" + machine.getCashHandler().printDenom(1000));
                collectbalance.setDoneBtnListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        JOptionPane.showMessageDialog(collectbalance, "Collected all balance");
                        int i,j;
                        for(i= 0;i < denoms.length;i++ )
                        {
                            for(j =0;j < machine.getCashHandler().getTotalAvailableDenom(denoms[i]);i++)
                            {
                                machine.getCashHandler().deductQuantityToBalance(denoms[i]);
                            }
                        }
                    }
                });
            }
        });
        

        this.testmaintenance.setSalesBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(false);
                printsales.setVisible(true);
                //printsales will display and all sales will display in a text area
                
            }
        });

        this.testmaintenance.setInventoryBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(false);
                inventory.setVisible(true);
                //will display all the inventory of all items in a text area

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

    public void loadRegularVendingmaintenance()
    {
        Item item1 = machine.getArraySlots().get(0).checkItem(); 
        testmaintenance.setItemName1(item1.getItemName());
        testmaintenance.setItemPrice1(((Milktea) item1).getPrice());
        testmaintenance.setItemQuantity1(machine.getArraySlots().get(0).getNumberOfStock());
        testmaintenance.setItemCalories1(item1.getItemCalories());
        testmaintenance.setItemPicture1("/Elements/ItemElements/okinawa w_ pearls.png");
        
        Item item2 = machine.getArraySlots().get(1).checkItem(); 
        testmaintenance.setItemName2(item2.getItemName());
        testmaintenance.setItemPrice2(((Milktea) item2).getPrice());
        testmaintenance.setItemQuantity2(machine.getArraySlots().get(1).getNumberOfStock());
        testmaintenance.setItemCalories2(item2.getItemCalories());
        testmaintenance.setItemPicture2("/Elements/ItemElements/okinawa w_ white pearls.png");

        Item item3 = machine.getArraySlots().get(2).checkItem(); 
        testmaintenance.setItemName3(item3.getItemName());
        testmaintenance.setItemPrice3(((Milktea) item3).getPrice());
        testmaintenance.setItemQuantity3(machine.getArraySlots().get(2).getNumberOfStock());
        testmaintenance.setItemCalories3(item3.getItemCalories());
        testmaintenance.setItemPicture3("/Elements/ItemElements/okinawa w_ nata.png");
        
        Item item4 = machine.getArraySlots().get(3).checkItem(); 
        testmaintenance.setItemName4(item4.getItemName());
        testmaintenance.setItemPrice4(((Milktea) item4).getPrice());
        testmaintenance.setItemQuantity4(machine.getArraySlots().get(3).getNumberOfStock());
        testmaintenance.setItemCalories4(item4.getItemCalories());
        testmaintenance.setItemPicture4("/Elements/ItemElements/wintermelon w_ pearls.png");

        Item item5 = machine.getArraySlots().get(4).checkItem(); 
        testmaintenance.setItemName5(item5.getItemName());
        testmaintenance.setItemPrice5(((Milktea) item5).getPrice());
        testmaintenance.setItemQuantity5(machine.getArraySlots().get(4).getNumberOfStock());
        testmaintenance.setItemCalories5(item5.getItemCalories());
        testmaintenance.setItemPicture5("/Elements/ItemElements/wintermelon w_ white pearls.png");
        
        Item item6 = machine.getArraySlots().get(5).checkItem(); 
        testmaintenance.setItemName6(item6.getItemName());
        testmaintenance.setItemPrice6(((Milktea) item6).getPrice());
        testmaintenance.setItemQuantity6(machine.getArraySlots().get(5).getNumberOfStock());
        testmaintenance.setItemCalories6(item6.getItemCalories());
        testmaintenance.setItemPicture6("/Elements/ItemElements/wintermelon w_ nat.png");

        Item item7 = machine.getArraySlots().get(6).checkItem(); 
        testmaintenance.setItemName7(item7.getItemName());
        testmaintenance.setItemPrice7(((Milktea) item7).getPrice());
        testmaintenance.setItemQuantity7(machine.getArraySlots().get(6).getNumberOfStock());
        testmaintenance.setItemCalories7(item7.getItemCalories());
        testmaintenance.setItemPicture7("/Elements/ItemElements/hokkaido w_ pearls.png");
        
        Item item8 = machine.getArraySlots().get(7).checkItem(); 
        testmaintenance.setItemName8(item8.getItemName());
        testmaintenance.setItemPrice8(((Milktea) item8).getPrice());
        testmaintenance.setItemQuantity8(machine.getArraySlots().get(7).getNumberOfStock());
        testmaintenance.setItemCalories8(item8.getItemCalories());
        testmaintenance.setItemPicture8("/Elements/ItemElements/hokkaido w_ white pearls.png");

        Item item9 = machine.getArraySlots().get(8).checkItem(); 
        testmaintenance.setItemName9(item9.getItemName());
        testmaintenance.setItemPrice9(((Milktea) item9).getPrice());
        testmaintenance.setItemQuantity9(machine.getArraySlots().get(8).getNumberOfStock());
        testmaintenance.setItemCalories9(item9.getItemCalories());
        testmaintenance.setItemPicture9("/Elements/ItemElements/hokkaido w_ nata.png");
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

    public void restartTestingVend()
    {
        testvendfeatures.setPickSlot1Enable(true);
        testvendfeatures.setPickSlot2Enable(true);
        testvendfeatures.setPickSlot3Enable(true);
        testvendfeatures.setPickSlot4Enable(true);
        testvendfeatures.setPickSlot5Enable(true);
        testvendfeatures.setPickSlot6Enable(true);
        testvendfeatures.setPickSlot7Enable(true);
        testvendfeatures.setPickSlot8Enable(true);
        testvendfeatures.setPickSlot9Enable(true);
        testvendfeatures.setDenomComboBoxSelectedIndex();
        testvendfeatures.restartOrderDetailsLbl();
        testvendfeatures.setMessageLbl("");
        testvendfeatures.setDispenseBtnEnable(false);
        testvendfeatures.setCancelBtnEnable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Create")){
            System.out.print("Create");
        }
    }
}