import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Action;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Factory is the main controller of the program.
 * @author kriastiankintanar
 * @author rogercanayon
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

    /**
     * Once Factory is initialized, the whole program will run.
     * The programmers followed the MVC architecture for this program.
     */
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
                machine.setInventory(slot.regularItems());
                mainMenu.setTestEnable(true);
                mainMenu.setVisible(true);
                createvending.setVisible(false);
            }
        });
        
        this.createvending.setCreateSpecialListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(createvending, "Special Vending Machine is successfully created!");
                machine = new SpecialMachine(slot.specialItems());
                machine.setInventory(slot.specialItems());
                mainMenu.setVisible(true);
                mainMenu.setTestEnable(true);
                createvending.setVisible(false);
            }
        });
        
        // TEST FEATURES  
        
        this.mainMenu.setTestBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine instanceof SpecialMachine)
                {
                    loadSpecialVending();
                }
                else
                {
                    loadRegularVending();
                }
                testvending.setVisible(true);
                mainMenu.setVisible(false);
            }
        });


        this.testvending.setTestVendingListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine instanceof SpecialMachine)
                {
                    loadSpecialVending();
                    disableFlavor();
                }

                else
                {
                    enableFlavor();
                    loadRegularVending();
                }
                testvendfeatures.setInsertCashEnable(false);
                testvendfeatures.setDispenseBtnEnable(false);
                testvendfeatures.setCancelBtnEnable(false);
                testvendfeatures.setDenomComboBoxValues(machine.getCashHandler().getAcceptedDenom());

                if (machine.getArraySlots().get(0).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot1Enable(false);
                }
                if (machine.getArraySlots().get(1).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot2Enable(false);
                }

                if (machine.getArraySlots().get(2).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot3Enable(false);
                }
                if (machine.getArraySlots().get(3).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot4Enable(false);
                }
                if (machine.getArraySlots().get(4).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot5Enable(false);
                }
                if (machine.getArraySlots().get(5).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot6Enable(false);
                }
                if (machine.getArraySlots().get(6).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot7Enable(false);
                }
                if (machine.getArraySlots().get(7).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot8Enable(false);
                }
                if (machine.getArraySlots().get(8).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot9Enable(false);
                }

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
                if (machine.getArraySlots().get(0).getNumberOfStock() == 0)
                {
                    testvendfeatures.setPickSlot1Enable(false);
                }

                else
                {
                    testvendfeatures.setCancelBtnEnable(true);
                    Item item1 = machine.getArraySlots().get(0).checkItem();
                    if (machine instanceof SpecialMachine)
                    {
                        testvendfeatures.setPickSlot1Enable(false);
                        enableFlavor();
                        disableSinkers();
                        machine.setChosenItem(item1);
                        testvendfeatures.setOrderDetailsLbl(item1.getItemName());
                        testvendfeatures.setMessageLbl("Please pick the flavor of your milktea.");
                    }

                    else 
                    {
                        disableAllPickBtns();
                        testvendfeatures.setOrderDetailsLbl(item1.getItemName());
                        testvendfeatures.setMessageLbl("Please insert your payment.");
                        testvendfeatures.setInsertCashEnable(true);
                        machine.getCashHandler().setTotalPayable(((Milktea) item1).getPrice());
                        machine.setChosenItem(item1);
                        int totalPayable = machine.getCashHandler().getTotalPayable();
                        testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                    }
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
                    testvendfeatures.setCancelBtnEnable(true);
                    Item item2 = machine.getArraySlots().get(1).checkItem();
                    if (machine instanceof SpecialMachine)
                    {
                        Item chosenItem = machine.getChosenItem();
                        ((Milktea) chosenItem).setFlavor((Flavor) item2);
                        testvendfeatures.setFlavorDetailsLbl(((Flavor) item2).getItemName());
                        testvendfeatures.setMessageLbl("Pick the sinker of your choice.");
                        disableFlavor();
                        enableSinkers();
                    }
                    
                    else
                    {
                        disableAllPickBtns();
                        testvendfeatures.setOrderDetailsLbl(item2.getItemName());
                        testvendfeatures.setMessageLbl("Please insert your payment.");
                        testvendfeatures.setInsertCashEnable(true);
                        machine.getCashHandler().setTotalPayable(((Milktea) item2).getPrice());
                        machine.setChosenItem(item2);
                        int totalPayable = machine.getCashHandler().getTotalPayable();
                        testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                    }
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
                    testvendfeatures.setCancelBtnEnable(true);
                    Item item3 = machine.getArraySlots().get(2).checkItem();
                    if (machine instanceof SpecialMachine)
                    {
                        Item chosenItem = machine.getChosenItem();
                        ((Milktea) chosenItem).setFlavor((Flavor) item3);
                        testvendfeatures.setFlavorDetailsLbl(((Flavor) item3).getItemName());
                        testvendfeatures.setMessageLbl("Pick the sinker of your choice.");
                        disableFlavor();
                        enableSinkers();
                    }
                    
                    else
                    {
                        disableAllPickBtns();
                        testvendfeatures.setOrderDetailsLbl(item3.getItemName());
                        testvendfeatures.setMessageLbl("Please insert your payment.");
                        testvendfeatures.setInsertCashEnable(true);
                        machine.getCashHandler().setTotalPayable(((Milktea) item3).getPrice());
                        machine.setChosenItem(item3);
                        int totalPayable = machine.getCashHandler().getTotalPayable();
                        testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                    }
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
                    testvendfeatures.setCancelBtnEnable(true);
                    Item item4 = machine.getArraySlots().get(3).checkItem();
                    disableAllPickBtns();
                    if (machine instanceof SpecialMachine)
                    {
                        if (machine.getChosenItem() == null)
                        {
                            testvendfeatures.setOrderDetailsLbl(item4.getItemName());
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Sinker) item4).getPrice());
                            machine.setChosenItem(item4);
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                        else
                        {
                            Item chosenItem = machine.getChosenItem();
                            testvendfeatures.setSinkerDetailsLbl(((Sinker) item4).getItemName());
                            ((Milktea) chosenItem).setSinker((Sinker)item4);
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Milktea) chosenItem).getPrice());
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                    }

                    else
                    {
                        testvendfeatures.setCancelBtnEnable(true);
                        testvendfeatures.setOrderDetailsLbl(item4.getItemName());
                        testvendfeatures.setMessageLbl("Please insert your payment.");
                        testvendfeatures.setInsertCashEnable(true);
                        machine.getCashHandler().setTotalPayable(((Milktea) item4).getPrice());
                        machine.setChosenItem(item4);
                        int totalPayable = machine.getCashHandler().getTotalPayable();
                        testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                    }
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
                    testvendfeatures.setCancelBtnEnable(true);
                    Item item5 = machine.getArraySlots().get(4).checkItem();
                    disableAllPickBtns();
                    if (machine instanceof SpecialMachine)
                    {
                        if (machine.getChosenItem() == null)
                        {
                            testvendfeatures.setOrderDetailsLbl(item5.getItemName());
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Sinker) item5).getPrice());
                            machine.setChosenItem(item5);
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                        else
                        {
                            Item chosenItem = machine.getChosenItem();
                            testvendfeatures.setSinkerDetailsLbl(((Sinker) item5).getItemName());
                            ((Milktea) chosenItem).setSinker((Sinker)item5);
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Milktea) chosenItem).getPrice());
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                    }

                    else
                    {
                        testvendfeatures.setCancelBtnEnable(true);
                        testvendfeatures.setOrderDetailsLbl(item5.getItemName());
                        testvendfeatures.setMessageLbl("Please insert your payment.");
                        testvendfeatures.setInsertCashEnable(true);
                        machine.getCashHandler().setTotalPayable(((Milktea) item5).getPrice());
                        machine.setChosenItem(item5);
                        int totalPayable = machine.getCashHandler().getTotalPayable();
                        testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                    }
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
                    testvendfeatures.setCancelBtnEnable(true);
                    Item item6 = machine.getArraySlots().get(5).checkItem();
                    disableAllPickBtns();
                    if (machine instanceof SpecialMachine)
                    {
                        if (machine.getChosenItem() == null)
                        {
                            testvendfeatures.setOrderDetailsLbl(item6.getItemName());
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Sinker) item6).getPrice());
                            machine.setChosenItem(item6);
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                        else
                        {
                            Item chosenItem = machine.getChosenItem();
                            testvendfeatures.setSinkerDetailsLbl(((Sinker) item6).getItemName());
                            ((Milktea) chosenItem).setSinker((Sinker)item6);
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Milktea) chosenItem).getPrice());
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                    }

                    else
                    {
                        testvendfeatures.setOrderDetailsLbl(item6.getItemName());
                        testvendfeatures.setMessageLbl("Please insert your payment.");
                        testvendfeatures.setInsertCashEnable(true);
                        machine.getCashHandler().setTotalPayable(((Milktea) item6).getPrice());
                        machine.setChosenItem(item6);
                        int totalPayable = machine.getCashHandler().getTotalPayable();
                        testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                    }
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
                    testvendfeatures.setCancelBtnEnable(true);
                    Item item7 = machine.getArraySlots().get(6).checkItem();
                    disableAllPickBtns();
                    if (machine instanceof SpecialMachine)
                    {
                        testvendfeatures.setCancelBtnEnable(true);
                        if (machine.getChosenItem() == null)
                        {
                            testvendfeatures.setOrderDetailsLbl(item7.getItemName());
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Sinker) item7).getPrice());
                            machine.setChosenItem(item7);
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                        else
                        {
                            Item chosenItem = machine.getChosenItem();
                            testvendfeatures.setSinkerDetailsLbl(((Sinker) item7).getItemName());
                            ((Milktea) chosenItem).setSinker((Sinker)item7);
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Milktea) chosenItem).getPrice());
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                    }

                    else
                    {
                        testvendfeatures.setOrderDetailsLbl(item7.getItemName());
                        testvendfeatures.setMessageLbl("Please insert your payment.");
                        testvendfeatures.setInsertCashEnable(true);
                        machine.getCashHandler().setTotalPayable(((Milktea) item7).getPrice());
                        machine.setChosenItem(item7);
                        int totalPayable = machine.getCashHandler().getTotalPayable();
                        testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                    }
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
                    testvendfeatures.setCancelBtnEnable(true);
                    Item item8 = machine.getArraySlots().get(8).checkItem();
                    disableAllPickBtns();
                    if (machine instanceof SpecialMachine)
                    {
                        if (machine.getChosenItem() == null)
                        {
                            testvendfeatures.setOrderDetailsLbl(item8.getItemName());
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Sinker) item8).getPrice());
                            machine.setChosenItem(item8);
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                        else
                        {
                            Item chosenItem = machine.getChosenItem();
                            testvendfeatures.setSinkerDetailsLbl(((Sinker) item8).getItemName());
                            ((Milktea) chosenItem).setSinker((Sinker)item8);
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Milktea) chosenItem).getPrice());
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                    }

                    else
                    {
                        testvendfeatures.setOrderDetailsLbl(item8.getItemName());
                        testvendfeatures.setMessageLbl("Please insert your payment.");
                        testvendfeatures.setInsertCashEnable(true);
                        machine.getCashHandler().setTotalPayable(((Milktea) item8).getPrice());
                        machine.setChosenItem(item8);
                        int totalPayable = machine.getCashHandler().getTotalPayable();
                        testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                    }
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
                    testvendfeatures.setCancelBtnEnable(true);
                    Item item9 = machine.getArraySlots().get(8).checkItem();
                    disableAllPickBtns();
                    if (machine instanceof SpecialMachine)
                    {
                        if (machine.getChosenItem() == null)
                        {
                            testvendfeatures.setOrderDetailsLbl(item9.getItemName());
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Sinker) item9).getPrice());
                            machine.setChosenItem(item9);
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                        else
                        {
                            Item chosenItem = machine.getChosenItem();
                            testvendfeatures.setSinkerDetailsLbl(((Sinker) item9).getItemName());
                            ((Milktea) chosenItem).setSinker((Sinker)item9);
                            testvendfeatures.setMessageLbl("Please insert your payment.");
                            testvendfeatures.setInsertCashEnable(true);
                            machine.getCashHandler().setTotalPayable(((Milktea) chosenItem).getPrice());
                            int totalPayable = machine.getCashHandler().getTotalPayable();
                            testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                        }
                    }

                    else
                    {
                        testvendfeatures.setOrderDetailsLbl(item9.getItemName());
                        testvendfeatures.setMessageLbl("Please insert your payment.");
                        testvendfeatures.setInsertCashEnable(true);
                        machine.getCashHandler().setTotalPayable(((Milktea) item9).getPrice());
                        machine.setChosenItem(item9);
                        int totalPayable = machine.getCashHandler().getTotalPayable();
                        testvendfeatures.addInfoInMessageLbl("Total payable: ₱" + totalPayable);
                    }
                }
            }
        });

        this.testvendfeatures.setDispenseBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine instanceof SpecialMachine)
                {
                    if (machine.getChosenItem() instanceof Milktea)
                    {
                        Milktea order = ((Milktea) machine.getChosenItem());
                        Flavor flavor = order.getFlavor();
                        Sinker sinker = order.getSinker();

                        int orderIndex = machine.locateItemIndex(order);
                        int flavorIndex = machine.locateItemIndex(flavor);
                        int sinkerIndex = machine.locateItemIndex(sinker);

                        boolean isSuccess = machine.getCashHandler().produceChange();
                        if (isSuccess)
                        {
                            testvendfeatures.setMessageLbl("----- Preparing Milktea -----");
                            testvendfeatures.addInfoInMessageLbl("Adding " + sinker.getItemName() + "...");
                            testvendfeatures.addInfoInMessageLbl("Pouring " + flavor.getItemName() + "...");
                            testvendfeatures.addInfoInMessageLbl(flavor.getItemName() + " with " + sinker.getItemName() + " done!");
                            machine.getArraySlots().get(orderIndex).dispenseItem();
                            machine.getArraySlots().get(flavorIndex).dispenseItem();
                            machine.getArraySlots().get(sinkerIndex).dispenseItem();
                            JOptionPane.showMessageDialog(testvendfeatures, machine.getCashHandler().successChange());
                            machine.addTransactions();
                            ((SpecialMachine) machine).addTransactions(sinker);
                            ((SpecialMachine) machine).addTransactions(flavor);
                            testvendfeatures.restartOrderDetailsLbl();
                            loadSpecialVending();
                            restartTestingSpecial();
                        }

                        else
                        {
                            JOptionPane.showMessageDialog(testvendfeatures, machine.getCashHandler().failChange());
                            loadSpecialVending();
                            restartTestingSpecial();
                        }
                    }

                    else if (machine.getChosenItem() instanceof Sinker)
                    {
                        Sinker sinker = ((Sinker) machine.getChosenItem());
                        int sinkerIndex = machine.getChosenItemIndex();
                        boolean isSuccess = machine.getCashHandler().produceChange();
                        if (isSuccess)
                        {
                            testvendfeatures.setMessageLbl("----- Preparing Sinker -----");
                            testvendfeatures.addInfoInMessageLbl("Getting " + sinker.getItemName() + "...");
                            testvendfeatures.addInfoInMessageLbl("Packing it up...");
                            testvendfeatures.addInfoInMessageLbl(sinker.getItemName() + " done!");
                            machine.getArraySlots().get(sinkerIndex).dispenseItem();
                            JOptionPane.showMessageDialog(testvendfeatures, machine.getCashHandler().successChange());
                            machine.addTransactions();
                            testvendfeatures.restartOrderDetailsLbl();
                            loadSpecialVending();
                            restartTestingSpecial();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(testvendfeatures, machine.getCashHandler().failChange());
                            loadSpecialVending();
                            restartTestingSpecial();
                        }
                    }
                }

                else
                {
                    int itemIndex = machine.getChosenItemIndex();
                    Item item = machine.getArraySlots().get(itemIndex).checkItem();
                    boolean isSuccess = machine.getCashHandler().produceChange();
                    if (isSuccess)
                    {
                        testvendfeatures.setMessageLbl("----- Preparing Milktea -----");
                        testvendfeatures.addInfoInMessageLbl("Adding " + ((Milktea) item).getSinker().getItemName() + "...");
                        testvendfeatures.addInfoInMessageLbl("Pouring " + ((Milktea) item).getFlavor().getItemName() + "...");
                        testvendfeatures.addInfoInMessageLbl(item.getItemName() + " done!");
                        machine.getArraySlots().get(itemIndex).dispenseItem();
                        JOptionPane.showMessageDialog(testvendfeatures, machine.getCashHandler().successChange());
                        machine.addTransactions();
                        testvendfeatures.restartOrderDetailsLbl();
                    }

                    else
                    {
                        JOptionPane.showMessageDialog(testvendfeatures, machine.getCashHandler().failChange());
                    }
                    loadRegularVending();
                    restartTestingVend();
                }
                machine.setChosenItem(null);
            }
        });

        this.testvendfeatures.setCancelBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                machine.setChosenItem(null);
                if (machine.getCashHandler().isPaymentStackEmpty())
                {
                    JOptionPane.showMessageDialog(testvendfeatures, "Transaction is now cancelled.");
                }
                else
                {
                    JOptionPane.showMessageDialog(testvendfeatures, machine.getCashHandler().cancel());
                }

                if (machine instanceof SpecialMachine)
                {
                    restartTestingSpecial();
                }

                else
                {
                    restartTestingVend();
                }
            }
        });

        this.testvendfeatures.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine instanceof SpecialMachine)
                {
                    restartTestingSpecial();
                }
                else
                {
                    restartTestingVend();
                }
                testvending.setVisible(true);
                testvendfeatures.setVisible(false);
            }
        });

        this.testvending.setTestMaintenanceListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (machine instanceof SpecialMachine)
                {
                    loadSpecialVendingmaintenance();
                    testmaintenance.disableFlavorBtn();
                }
                else
                {
                    loadRegularVendingmaintenance();
                    testmaintenance.enableFlavorBtn();
                }
                testmaintenance.setVisible(true);
                testvending.setVisible(false);
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

        this.testmaintenance.setDoneBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testvending.setVisible(true);
                testmaintenance.setVisible(false);
            }
        });

        this.testmaintenance.setreStock1BtnListener(new ActionListener(){
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
                    int addStock;
                    try {
                        addStock = restock.getQuantity();
                        if (addStock <= 0)
                        {
                            restock.setWarningMessage("Please input a positive integer.");
                            restock.setWarningVisible(true);
                        }
                        else
                        {
                            machine.getArraySlots().get(0).addItem(item1, addStock);
                            machine.updateLastRestockDate();
                            ArrayList<Slot> currentInventory = machine.getArraySlots();
                            machine.setInventory(currentInventory);
                            if (machine instanceof SpecialMachine)
                            {
                                loadSpecialVendingmaintenance();
                            }
                            else
                            {
                                loadRegularVendingmaintenance();
                            }
                            testmaintenance.setVisible(true);
                            restock.cleartxtarea();
                            restock.setVisible(false);
                            restock.setWarningMessage(null);
                        }
                    } catch (Exception d) {
                        restock.setWarningMessage("Please input a positive integer.");
                        restock.setWarningVisible(true);
                    }
                }       
            });
        }
    });
        this.testmaintenance.setreStock2BtnListener(new ActionListener(){
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
                        int addStock;
                        try {
                            addStock = restock.getQuantity();
                            if (addStock <= 0)
                            {
                                restock.setWarningMessage("Please input a positive integer.");
                                restock.setWarningVisible(true);
                            }
                            else
                            {
                                machine.getArraySlots().get(1).addItem(item2, addStock);
                                machine.updateLastRestockDate();
                                ArrayList<Slot> currentInventory = machine.getArraySlots();
                                machine.setInventory(currentInventory);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                restock.setVisible(false); 
                                restock.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            restock.setWarningMessage("Please input a positive integer.");
                            restock.setWarningVisible(true);
                        }                         
                    }
                });  
            }
        });
        this.testmaintenance.setreStock3BtnListener(new ActionListener(){
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
                        int addStock;
                        try {
                            addStock = restock.getQuantity();
                            if (addStock <= 0)
                            {
                                restock.setWarningMessage("Please input a positive integer.");
                                restock.setWarningVisible(true);
                            }
                            else
                            {
                                machine.getArraySlots().get(2).addItem(item3, addStock);
                                machine.updateLastRestockDate();
                                ArrayList<Slot> currentInventory = machine.getArraySlots();
                                machine.setInventory(currentInventory);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                restock.setVisible(false);
                                restock.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            restock.setWarningMessage("Please input a positive integer.");
                            restock.setWarningVisible(true);
                        }
                    }
                });          
            }
        });
        this.testmaintenance.setreStock4BtnListener(new ActionListener(){
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
                    int addStock;
                        try {
                            addStock = restock.getQuantity();
                            if (addStock <= 0)
                            {
                                restock.setWarningMessage("Please input a positive integer.");
                                restock.setWarningVisible(true);
                            }
                            else
                            {
                                machine.getArraySlots().get(3).addItem(item4, addStock);
                                machine.updateLastRestockDate();
                                ArrayList<Slot> currentInventory = machine.getArraySlots();
                                machine.setInventory(currentInventory);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                restock.setVisible(false);
                                restock.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            restock.setWarningMessage("Please input a positive integer.");
                            restock.setWarningVisible(true);
                        }
                    }
                });          
            }
        });
        this.testmaintenance.setreStock5BtnListener(new ActionListener(){
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
                        int addStock;
                        try {
                            addStock = restock.getQuantity();
                            if (addStock <= 0)
                            {
                                restock.setWarningMessage("Please input a positive integer.");
                                restock.setWarningVisible(true);
                            }
                            else
                            {
                                machine.getArraySlots().get(4).addItem(item5, addStock);
                                machine.updateLastRestockDate();
                                ArrayList<Slot> currentInventory = machine.getArraySlots();
                                machine.setInventory(currentInventory);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                restock.setVisible(false);
                                restock.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            restock.setWarningMessage("Please input a positive integer.");
                            restock.setWarningVisible(true);
                        }
                    }
                });         
            }
        });
        this.testmaintenance.setreStock6BtnListener(new ActionListener(){
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
                        int addStock;
                        try {
                            addStock = restock.getQuantity();
                            if (addStock <= 0)
                            {
                                restock.setWarningMessage("Please input a positive integer.");
                                restock.setWarningVisible(true);
                            }
                            else
                            {
                                machine.getArraySlots().get(5).addItem(item6, addStock);
                                machine.updateLastRestockDate();
                                ArrayList<Slot> currentInventory = machine.getArraySlots();
                                machine.setInventory(currentInventory);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                restock.setVisible(false);
                                restock.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            restock.setWarningMessage("Please input a positive integer.");
                            restock.setWarningVisible(true);
                        }
                    }
                });     
            }
        });
        this.testmaintenance.setreStock7BtnListener(new ActionListener(){
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
                        int addStock;
                        try {
                            addStock = restock.getQuantity();
                            if (addStock <= 0)
                            {
                                restock.setWarningMessage("Please input a positive integer.");
                                restock.setWarningVisible(true);
                            }
                            else
                            {
                                machine.getArraySlots().get(6).addItem(item7, addStock);
                                machine.updateLastRestockDate();
                                ArrayList<Slot> currentInventory = machine.getArraySlots();
                                machine.setInventory(currentInventory);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                restock.setVisible(false);
                                restock.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            restock.setWarningMessage("Please input a positive integer.");
                            restock.setWarningVisible(true);
                        }
                    }
                });           
            }
        });
        this.testmaintenance.setreStock8BtnListener(new ActionListener(){
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
                        int addStock;
                        try {
                            addStock = restock.getQuantity();
                            if (addStock <= 0)
                            {
                                restock.setWarningMessage("Please input a positive integer.");
                                restock.setWarningVisible(true);
                            }
                            else
                            {
                                machine.getArraySlots().get(7).addItem(item8, addStock);
                                machine.updateLastRestockDate();
                                ArrayList<Slot> currentInventory = machine.getArraySlots();
                                machine.setInventory(currentInventory);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                restock.setVisible(false);
                                restock.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            restock.setWarningMessage("Please input a positive integer.");
                            restock.setWarningVisible(true);
                        }
                    }
                });          
            }
        });
        this.testmaintenance.setreStock9BtnListener(new ActionListener(){
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
                        int addStock;
                        try {
                            addStock = restock.getQuantity();
                            if (addStock <= 0)
                            {
                                restock.setWarningMessage("Please input a positive integer.");
                                restock.setWarningVisible(true);
                            }
                            else
                            {
                                machine.getArraySlots().get(8).addItem(item9, addStock);
                                machine.updateLastRestockDate();
                                ArrayList<Slot> currentInventory = machine.getArraySlots();
                                machine.setInventory(currentInventory);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                testmaintenance.setVisible(true);
                                restock.cleartxtarea();
                                restock.setVisible(false);
                                restock.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            restock.setWarningMessage("Please input a positive integer.");
                            restock.setWarningVisible(true);
                        }
                    }
                });           
            }
        });

        testmaintenance.setPrice1BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Item item1 = machine.getArraySlots().get(0).checkItem();
                changeprice.setItemName(item1.getItemName());
                changeprice.setVisible(true);
                testmaintenance.setVisible(false);
                changeprice.setChangePriceBtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int newprice;
                        try {
                            newprice = changeprice.getNewPrice();
                            if (newprice <= 0)
                            {
                                changeprice.setWarningMessage("Please input a positive integer.");
                                changeprice.setWarningVisible(true);
                            }
                            else
                            {
                                ((Milktea)item1).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.clearTA();
                                changeprice.setVisible(false);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                changeprice.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            changeprice.setWarningMessage("Please input a positive integer.");
                            changeprice.setWarningVisible(true);
                        }
                    }
                });
            }
        });
        testmaintenance.setPrice2BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Item item2 = machine.getArraySlots().get(1).checkItem();
                changeprice.setItemName(item2.getItemName());
                changeprice.setVisible(true);
                testmaintenance.setVisible(false);     
                changeprice.setChangePriceBtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int newprice;
                        try {
                            newprice = changeprice.getNewPrice();
                            if (newprice <= 0)
                            {
                                changeprice.setWarningMessage("Please input a positive integer.");
                                changeprice.setWarningVisible(true);
                            }
                            else
                            {
                                ((Milktea)item2).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.clearTA();
                                changeprice.setVisible(false);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                changeprice.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            changeprice.setWarningMessage("Please input a positive integer.");
                            changeprice.setWarningVisible(true);
                        }                       
                    }
                });
            }
        });
        testmaintenance.setPrice3BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Item item3 = machine.getArraySlots().get(2).checkItem();
                changeprice.setItemName(item3.getItemName());
                changeprice.setVisible(true);
                testmaintenance.setVisible(false);
                changeprice.setChangePriceBtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int newprice;
                        try {
                            newprice = changeprice.getNewPrice();
                            if (newprice <= 0)
                            {
                                changeprice.setWarningMessage("Please input a positive integer.");
                                changeprice.setWarningVisible(true);
                            }
                            else
                            {
                                ((Milktea)item3).setPrice(newprice);
                                testmaintenance.setVisible(true);
                                changeprice.clearTA();
                                changeprice.setVisible(false);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                changeprice.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            changeprice.setWarningMessage("Please input a positive integer.");
                            changeprice.setWarningVisible(true);
                        }
                    }
                });
            }
        });
        testmaintenance.setPrice4BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Item item4 = machine.getArraySlots().get(3).checkItem();
                changeprice.setItemName(item4.getItemName());
                changeprice.setVisible(true);
                testmaintenance.setVisible(false);
                changeprice.setChangePriceBtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int newprice;
                        try {
                            newprice = changeprice.getNewPrice();
                            if (newprice <= 0)
                            {
                                changeprice.setWarningMessage("Please input a positive integer.");
                                changeprice.setWarningVisible(true);
                            }
                            else
                            {
                                if (machine instanceof SpecialMachine)
                                {
                                    ((Sinker)item4).setPrice(newprice);
                                }
                                else
                                {
                                    ((Milktea)item4).setPrice(newprice);
                                }
                                testmaintenance.setVisible(true);
                                changeprice.clearTA();
                                changeprice.setVisible(false);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                changeprice.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            changeprice.setWarningMessage("Please input a positive integer.");
                            changeprice.setWarningVisible(true);
                        }
                    }
                });
            }
        });
        testmaintenance.setPrice5BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Item item5 = machine.getArraySlots().get(4).checkItem();
                changeprice.setItemName(item5.getItemName());
                changeprice.setVisible(true);
                testmaintenance.setVisible(false);
                changeprice.setChangePriceBtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int newprice;
                        try {
                            newprice = changeprice.getNewPrice();
                            if (newprice <= 0)
                            {
                                changeprice.setWarningMessage("Please input a positive integer.");
                                changeprice.setWarningVisible(true);
                            }
                            else
                            {
                                if (machine instanceof SpecialMachine)
                                {
                                    ((Sinker)item5).setPrice(newprice);
                                }
                                else
                                {
                                    ((Milktea)item5).setPrice(newprice);
                                }
                                testmaintenance.setVisible(true);
                                changeprice.clearTA();
                                changeprice.setVisible(false);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                changeprice.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            changeprice.setWarningMessage("Please input a positive integer.");
                            changeprice.setWarningVisible(true);
                        }
                    }
                });
            }
        });
        testmaintenance.setPrice6BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Item item6 = machine.getArraySlots().get(5).checkItem();
                changeprice.setItemName(item6.getItemName());
                changeprice.setVisible(true);
                testmaintenance.setVisible(false);
                changeprice.setChangePriceBtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int newprice;
                        try {
                            newprice = changeprice.getNewPrice();
                            if (newprice <= 0)
                            {
                                changeprice.setWarningMessage("Please input a positive integer.");
                                changeprice.setWarningVisible(true);
                            }
                            else
                            {
                                if (machine instanceof SpecialMachine)
                                {
                                    ((Sinker)item6).setPrice(newprice);
                                }
                                else
                                {
                                    ((Milktea)item6).setPrice(newprice);
                                }
                                testmaintenance.setVisible(true);
                                changeprice.clearTA();
                                changeprice.setVisible(false);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                changeprice.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            changeprice.setWarningMessage("Please input a positive integer.");
                            changeprice.setWarningVisible(true);
                        }
                    }
                });
            }
        });
        testmaintenance.setPrice7BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Item item7 = machine.getArraySlots().get(6).checkItem();
                changeprice.setItemName(item7.getItemName());
                changeprice.setVisible(true);
                testmaintenance.setVisible(false);
                changeprice.setChangePriceBtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int newprice;
                        try {
                            newprice = changeprice.getNewPrice();
                            if (newprice <= 0)
                            {
                                changeprice.setWarningMessage("Please input a positive integer.");
                                changeprice.setWarningVisible(true);
                            }
                            else
                            {
                                if (machine instanceof SpecialMachine)
                                {
                                    ((Sinker)item7).setPrice(newprice);
                                }
                                else
                                {
                                    ((Milktea)item7).setPrice(newprice);
                                }
                                testmaintenance.setVisible(true);
                                changeprice.clearTA();
                                changeprice.setVisible(false);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                changeprice.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            changeprice.setWarningMessage("Please input a positive integer.");
                            changeprice.setWarningVisible(true);
                        }
                    }
                });
            }
        });
        testmaintenance.setPrice8BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Item item8 = machine.getArraySlots().get(7).checkItem();
                changeprice.setItemName(item8.getItemName());
                changeprice.setVisible(true);
                testmaintenance.setVisible(false);
                changeprice.setChangePriceBtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int newprice;
                        try {
                            newprice = changeprice.getNewPrice();
                            if (newprice <= 0)
                            {
                                changeprice.setWarningMessage("Please input a positive integer.");
                                changeprice.setWarningVisible(true);
                            }
                            else
                            {
                                if (machine instanceof SpecialMachine)
                                {
                                    ((Sinker)item8).setPrice(newprice);
                                }
                                else
                                {
                                    ((Milktea)item8).setPrice(newprice);
                                }
                                testmaintenance.setVisible(true);
                                changeprice.clearTA();
                                changeprice.setVisible(false);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                changeprice.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            changeprice.setWarningMessage("Please input a positive integer.");
                            changeprice.setWarningVisible(true);
                        }
                    }
                });
            }
        });
        testmaintenance.setPrice9BtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Item item9 = machine.getArraySlots().get(8).checkItem();
                changeprice.setItemName(item9.getItemName());
                changeprice.setVisible(true);
                testmaintenance.setVisible(false);
                changeprice.setChangePriceBtnListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int newprice;
                        try {
                            newprice = changeprice.getNewPrice();
                            if (newprice <= 0)
                            {
                                changeprice.setWarningMessage("Please input a positive integer.");
                                changeprice.setWarningVisible(true);
                            }
                            else
                            {
                                if (machine instanceof SpecialMachine)
                                {
                                    ((Sinker)item9).setPrice(newprice);
                                }
                                else
                                {
                                    ((Milktea)item9).setPrice(newprice);
                                }
                                testmaintenance.setVisible(true);
                                changeprice.clearTA();
                                changeprice.setVisible(false);
                                if (machine instanceof SpecialMachine)
                                {
                                    loadSpecialVendingmaintenance();
                                }
                                else
                                {
                                    loadRegularVendingmaintenance();
                                }
                                changeprice.setWarningMessage(null);
                            }
                        } catch (Exception d) {
                            changeprice.setWarningMessage("Please input a positive integer.");
                            changeprice.setWarningVisible(true);
                        }
                    }
                });
            }
        });

        this.testmaintenance.setReStockBalListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(false);
                replenishbalance.setWarningVisible(false);;
                replenishbalance.setVisible(true);
                replenishbalance.setReplenishBtn(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        int i;
                        int replenish;
                        try {
                            if (replenishbalance.getBal1() >= 0 && replenishbalance.getBal5() >= 0 &&
                                replenishbalance.getBal10() >= 0 && replenishbalance.getBal20() >= 0 &&
                                replenishbalance.getBal50() >= 0 && replenishbalance.getBal100() >= 0 &&
                                replenishbalance.getBal200() >= 0 && replenishbalance.getBal500() >= 0 &&
                                replenishbalance.getBal1000() >= 0)
                            {
                                for(i=0;i<replenishbalance.getBal1();i++)
                                {
                                    machine.getCashHandler().addQuantityToBalance(1);  
                                }
                                for(i=0;i<replenishbalance.getBal10();i++)
                                {
                                    machine.getCashHandler().addQuantityToBalance(10);  
                                }
                                for(i=0;i<replenishbalance.getBal100();i++)
                                {
                                    machine.getCashHandler().addQuantityToBalance(100);  
                                }
                                for(i=0;i<replenishbalance.getBal1000();i++)
                                {
                                    machine.getCashHandler().addQuantityToBalance(1000);  
                                }
                                for(i=0;i<replenishbalance.getBal20();i++)
                                {
                                    machine.getCashHandler().addQuantityToBalance(20);  
                                }
                                for(i=0;i<replenishbalance.getBal200();i++)
                                {
                                    machine.getCashHandler().addQuantityToBalance(200);  
                                }
                                for(i=0;i<replenishbalance.getBal5();i++)
                                {
                                    machine.getCashHandler().addQuantityToBalance(5);  
                                }
                                for(i=0;i<replenishbalance.getBal50();i++)
                                {
                                    machine.getCashHandler().addQuantityToBalance(50);  
                                }
                                for(i=0;i<replenishbalance.getBal500();i++)
                                {
                                    machine.getCashHandler().addQuantityToBalance(500);  
                                }
                                JOptionPane.showMessageDialog(replenishbalance, "Added Balance");
                                testmaintenance.setVisible(true);
                                replenishbalance.setVisible(false);
                                replenishbalance.clearTA();
                            }
                            else
                            {
                                replenishbalance.setWarningMessage("Please input a positive integer.");
                                replenishbalance.setWarningVisible(true);
                            }
                        } catch (Exception d) {
                            replenishbalance.setWarningMessage("Please input a positive integer.");
                            replenishbalance.setWarningVisible(true);
                        }
                    }
                });
            }
        });

        this.testmaintenance.setCollectBalListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int[] denoms = machine.getCashHandler().getAcceptedDenom();
                if (machine.getCashHandler().hasDenomStock(denoms[0]) || machine.getCashHandler().hasDenomStock(denoms[1]) ||
                    machine.getCashHandler().hasDenomStock(denoms[2]) || machine.getCashHandler().hasDenomStock(denoms[3]) ||
                    machine.getCashHandler().hasDenomStock(denoms[4]) || machine.getCashHandler().hasDenomStock(denoms[5]) ||
                    machine.getCashHandler().hasDenomStock(denoms[6]) || machine.getCashHandler().hasDenomStock(denoms[7]) ||
                    machine.getCashHandler().hasDenomStock(denoms[8]))
                {
                    testmaintenance.setVisible(false);
                    collectbalance.setVisible(true);
                    collectbalance.clearTxtArea();
                    collectbalance.setTextArea(machine.getCashHandler().printDenom(1) + "\n" + machine.getCashHandler().printDenom(5) + "\n" + 
                                                machine.getCashHandler().printDenom(10) + "\n" + machine.getCashHandler().printDenom(50) + "\n" +
                                                machine.getCashHandler().printDenom(100) + "\n" + machine.getCashHandler().printDenom(200) + "\n" +
                                                machine.getCashHandler().printDenom(500) + "\n" + machine.getCashHandler().printDenom(1000));
                    collectbalance.setDoneBtnListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                            JOptionPane.showMessageDialog(collectbalance, "Collected all balance.");
                            machine.getCashHandler().collectBalance();
                            collectbalance.setVisible(false);                    
                            testmaintenance.setVisible(true);
                        }
                    });
                }

                else if (!machine.getCashHandler().hasDenomStock(denoms[0]) && !machine.getCashHandler().hasDenomStock(denoms[1]) &&
                    !machine.getCashHandler().hasDenomStock(denoms[2]) && !machine.getCashHandler().hasDenomStock(denoms[3]) &&
                    !machine.getCashHandler().hasDenomStock(denoms[4]) && !machine.getCashHandler().hasDenomStock(denoms[5]) &&
                    !machine.getCashHandler().hasDenomStock(denoms[6]) && !machine.getCashHandler().hasDenomStock(denoms[7]) &&
                    !machine.getCashHandler().hasDenomStock(denoms[8]))
                {
                    JOptionPane.showMessageDialog(collectbalance, "There are currently no balance available.");
                }
            }
        });

        this.testmaintenance.setSalesBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(false);
                printsales.setVisible(true);
                //printsales will display and all sales will display in a text area
                
                int i, j;
                int soldCounter = 0;
                LocalDate today = LocalDate.now();
                ArrayList<Slot> arraySlots =machine.getArraySlots();
                ArrayList<Transactions> transactions = machine.geTransactions();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                String display;

                display = ("Sales Report from: " + formatter.format(machine.getLastRestockDate()) + " to " + formatter.format(today));

                int totalSales = 0;

                for (i = 0; i < transactions.size(); i++)
                {
                    if(transactions.get(i).getDate().isAfter(today) || transactions.get(i).getDate().isBefore(today) || transactions.get(i).getDate().equals(today))
                    {
                        totalSales = totalSales + transactions.get(i).getTotalPrice();
                    }
                }

                display += ("\nTotal Sales Generated: Php " + totalSales + "\n");

                display += ("Product Sales:");

                for (i = 0; i < arraySlots.size(); i++)
                {
                    soldCounter = 0;
                    for (j = 0; j < transactions.size(); j++)
                    {
                        if(transactions.get(j).getDate().isAfter(today) || transactions.get(j).getDate().isBefore(today) || transactions.get(j).getDate().equals(today))
                        {
                                if (arraySlots.get(i).checkItem().getItemName().equals(transactions.get(j).getItemName()))
                                {
                                    soldCounter++;
                                }
                        }
                    }
                    display += ("\n[" + soldCounter + " sold] " + arraySlots.get(i).checkItem().getItemName()+"\n");
                }
                printsales.setTA(display);
                printsales.setDoneBtnListener(new ActionListener() {
                    public void actionPerformed (ActionEvent e)
                    {
                        printsales.setVisible(false);
                        testmaintenance.setVisible(true);
                    }
                });
            }
        });

        this.testmaintenance.setInventoryBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                testmaintenance.setVisible(false);
                inventory.setVisible(true);
                
                String displayInitial = "", displayCurrent = "";

                for (int i = 0; i < machine.getInventory().size(); i++)
                {
                    Item item = machine.getInventory().get(i).checkItem();
                    displayInitial += item.getItemName() + " (" + machine.getInventory().get(i).getNumberOfStock() + " available)\n";
                }
                inventory.setInitialText(displayInitial);   

                for (int i = 0; i < machine.getArraySlots().size(); i++)
                {
                    Item item = machine.getArraySlots().get(i).checkItem();
                    displayCurrent += item.getItemName() + " (" + machine.getArraySlots().get(i).getNumberOfStock() + " available)\n";
                }
                inventory.setCurrentText(displayCurrent);
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

    /**
     * Enables the buttons that hold the flavor slots.
     */
    public void enableFlavor()
    {
        testvendfeatures.setPickSlot2Enable(true);
        testvendfeatures.setPickSlot3Enable(true);
    }

    /**
     * Enables the buttons that hold the sinker slots.
     */
    public void enableSinkers()
    {
        testvendfeatures.setPickSlot4Enable(true);
        testvendfeatures.setPickSlot5Enable(true);
        testvendfeatures.setPickSlot6Enable(true);
        testvendfeatures.setPickSlot7Enable(true);
        testvendfeatures.setPickSlot8Enable(true);
        testvendfeatures.setPickSlot9Enable(true);
    }

    /**
     * Disables the buttons that hold the flavor slots.
     */
    public void disableFlavor()
    {
        testvendfeatures.setPickSlot2Enable(false);
        testvendfeatures.setPickSlot3Enable(false);
    }

    /**
     * Disables the buttons that hold the sinker slots.
     */
    public void disableSinkers()
    {
        testvendfeatures.setPickSlot4Enable(false);
        testvendfeatures.setPickSlot5Enable(false);
        testvendfeatures.setPickSlot6Enable(false);
        testvendfeatures.setPickSlot7Enable(false);
        testvendfeatures.setPickSlot8Enable(false);
        testvendfeatures.setPickSlot9Enable(false);
    }

    /**
     * Loads the special items for special vending machine.
     */
    public void loadSpecialVending()
    {
        Item item1 = machine.getArraySlots().get(0).checkItem(); 
        testvendfeatures.setItemName1(item1.getItemName());
        testvendfeatures.setItemPrice1(((Milktea) item1).getPrice());
        testvendfeatures.setItemQuantity1(machine.getArraySlots().get(0).getNumberOfStock());
        testvendfeatures.setItemCalories1(148);
        testvendfeatures.setItemPicture1("/Elements/ItemElements/milkteacup.png");
        
        Item item2 = machine.getArraySlots().get(1).checkItem(); 
        testvendfeatures.setItemName2(item2.getItemName());
        //testvendfeatures.setItemPrice2(((Flavor) item2).getPrice());
        testvendfeatures.setItemQuantity2(machine.getArraySlots().get(1).getNumberOfStock());
        testvendfeatures.setItemCalories2(item2.getItemCalories());
        testvendfeatures.setItemPicture2("/Elements/ItemElements/okinawa.png");

        Item item3 = machine.getArraySlots().get(2).checkItem(); 
        testvendfeatures.setItemName3(item3.getItemName());
        //testvendfeatures.setItemPrice3(((Flavor) item3).getPrice());
        testvendfeatures.setItemQuantity3(machine.getArraySlots().get(2).getNumberOfStock());
        testvendfeatures.setItemCalories3(item3.getItemCalories());
        testvendfeatures.setItemPicture3("/Elements/ItemElements/wintermelon.png");
        
        Item item4 = machine.getArraySlots().get(3).checkItem(); 
        testvendfeatures.setItemName4(item4.getItemName());
        testvendfeatures.setItemPrice4(((Sinker) item4).getPrice());
        testvendfeatures.setItemQuantity4(machine.getArraySlots().get(3).getNumberOfStock());
        testvendfeatures.setItemCalories4(item4.getItemCalories());
        testvendfeatures.setItemPicture4("/Elements/ItemElements/pearls.png");

        Item item5 = machine.getArraySlots().get(4).checkItem(); 
        testvendfeatures.setItemName5(item5.getItemName());
        testvendfeatures.setItemPrice5(((Sinker) item5).getPrice());
        testvendfeatures.setItemQuantity5(machine.getArraySlots().get(4).getNumberOfStock());
        testvendfeatures.setItemCalories5(item5.getItemCalories());
        testvendfeatures.setItemPicture5("/Elements/ItemElements/whitepearls.png");
        
        Item item6 = machine.getArraySlots().get(5).checkItem(); 
        testvendfeatures.setItemName6(item6.getItemName());
        testvendfeatures.setItemPrice6(((Sinker) item6).getPrice());
        testvendfeatures.setItemQuantity6(machine.getArraySlots().get(5).getNumberOfStock());
        testvendfeatures.setItemCalories6(item6.getItemCalories());
        testvendfeatures.setItemPicture6("/Elements/ItemElements/natadecoco.png");

        Item item7 = machine.getArraySlots().get(6).checkItem(); 
        testvendfeatures.setItemName7(item7.getItemName());
        testvendfeatures.setItemPrice7(((Sinker) item7).getPrice());
        testvendfeatures.setItemQuantity7(machine.getArraySlots().get(6).getNumberOfStock());
        testvendfeatures.setItemCalories7(item7.getItemCalories());
        testvendfeatures.setItemPicture7("/Elements/ItemElements/grassjelly.png");
        
        Item item8 = machine.getArraySlots().get(7).checkItem(); 
        testvendfeatures.setItemName8(item8.getItemName());
        testvendfeatures.setItemPrice8(((Sinker) item8).getPrice());
        testvendfeatures.setItemQuantity8(machine.getArraySlots().get(7).getNumberOfStock());
        testvendfeatures.setItemCalories8(item8.getItemCalories());
        testvendfeatures.setItemPicture8("/Elements/ItemElements/eggpudding.png");

        Item item9 = machine.getArraySlots().get(8).checkItem(); 
        testvendfeatures.setItemName9(item9.getItemName());
        testvendfeatures.setItemPrice9(((Sinker) item9).getPrice());
        testvendfeatures.setItemQuantity9(machine.getArraySlots().get(8).getNumberOfStock());
        testvendfeatures.setItemCalories9(item9.getItemCalories());
        testvendfeatures.setItemPicture9("/Elements/ItemElements/coffeejelly.png");
    }

    /**
     * Loads the items for regular vending machine.
     */
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

    /**
     * Loads the items for regular vending machine maintenance.
     */
    public void loadRegularVendingmaintenance()
    {
        Item item1 = machine.getArraySlots().get(0).checkItem(); 
        testmaintenance.setItemName1(item1.getItemName());
        testmaintenance.setItemPrice1(((Milktea) item1).getPrice());
        testmaintenance.setItemQuantity1(machine.getArraySlots().get(0).getNumberOfStock());
        testmaintenance.setItemPicture1("/Elements/ItemElements/okinawa w_ pearls.png");
        
        Item item2 = machine.getArraySlots().get(1).checkItem(); 
        testmaintenance.setItemName2(item2.getItemName());
        testmaintenance.setItemPrice2(((Milktea) item2).getPrice());
        testmaintenance.setItemQuantity2(machine.getArraySlots().get(1).getNumberOfStock());
        testmaintenance.setItemPicture2("/Elements/ItemElements/okinawa w_ white pearls.png");

        Item item3 = machine.getArraySlots().get(2).checkItem(); 
        testmaintenance.setItemName3(item3.getItemName());
        testmaintenance.setItemPrice3(((Milktea) item3).getPrice());
        testmaintenance.setItemQuantity3(machine.getArraySlots().get(2).getNumberOfStock());
        testmaintenance.setItemPicture3("/Elements/ItemElements/okinawa w_ nata.png");
        
        Item item4 = machine.getArraySlots().get(3).checkItem(); 
        testmaintenance.setItemName4(item4.getItemName());
        testmaintenance.setItemPrice4(((Milktea) item4).getPrice());
        testmaintenance.setItemQuantity4(machine.getArraySlots().get(3).getNumberOfStock());
        testmaintenance.setItemPicture4("/Elements/ItemElements/wintermelon w_ pearls.png");

        Item item5 = machine.getArraySlots().get(4).checkItem(); 
        testmaintenance.setItemName5(item5.getItemName());
        testmaintenance.setItemPrice5(((Milktea) item5).getPrice());
        testmaintenance.setItemQuantity5(machine.getArraySlots().get(4).getNumberOfStock());
        testmaintenance.setItemPicture5("/Elements/ItemElements/wintermelon w_ white pearls.png");
        
        Item item6 = machine.getArraySlots().get(5).checkItem(); 
        testmaintenance.setItemName6(item6.getItemName());
        testmaintenance.setItemPrice6(((Milktea) item6).getPrice());
        testmaintenance.setItemQuantity6(machine.getArraySlots().get(5).getNumberOfStock());
        testmaintenance.setItemPicture6("/Elements/ItemElements/wintermelon w_ nat.png");

        Item item7 = machine.getArraySlots().get(6).checkItem(); 
        testmaintenance.setItemName7(item7.getItemName());
        testmaintenance.setItemPrice7(((Milktea) item7).getPrice());
        testmaintenance.setItemQuantity7(machine.getArraySlots().get(6).getNumberOfStock());
        testmaintenance.setItemPicture7("/Elements/ItemElements/hokkaido w_ pearls.png");
        
        Item item8 = machine.getArraySlots().get(7).checkItem(); 
        testmaintenance.setItemName8(item8.getItemName());
        testmaintenance.setItemPrice8(((Milktea) item8).getPrice());
        testmaintenance.setItemQuantity8(machine.getArraySlots().get(7).getNumberOfStock());
        testmaintenance.setItemPicture8("/Elements/ItemElements/hokkaido w_ white pearls.png");

        Item item9 = machine.getArraySlots().get(8).checkItem(); 
        testmaintenance.setItemName9(item9.getItemName());
        testmaintenance.setItemPrice9(((Milktea) item9).getPrice());
        testmaintenance.setItemQuantity9(machine.getArraySlots().get(8).getNumberOfStock());
        testmaintenance.setItemPicture9("/Elements/ItemElements/hokkaido w_ nata.png");
    }

    /**
     * Loads the special items for special vending machine maintenance.
     */
    public void loadSpecialVendingmaintenance()
    {
        Item item1 = machine.getArraySlots().get(0).checkItem(); 
        testmaintenance.setItemName1(item1.getItemName());
        testmaintenance.setItemPrice1(((Milktea) item1).getPrice());
        testmaintenance.setItemQuantity1(machine.getArraySlots().get(0).getNumberOfStock());
        testmaintenance.setItemPicture1("/Elements/ItemElements/milkteacup.png");
        
        Item item2 = machine.getArraySlots().get(1).checkItem(); 
        testmaintenance.setItemName2(item2.getItemName());
        //testvendfeatures.setItemPrice2(((Flavor) item2).getPrice());
        testmaintenance.setItemQuantity2(machine.getArraySlots().get(1).getNumberOfStock());
        testmaintenance.setItemPicture2("/Elements/ItemElements/okinawa.png");

        Item item3 = machine.getArraySlots().get(2).checkItem(); 
        testmaintenance.setItemName3(item3.getItemName());
        //testvendfeatures.setItemPrice3(((Flavor) item3).getPrice());
        testmaintenance.setItemQuantity3(machine.getArraySlots().get(2).getNumberOfStock());
        testmaintenance.setItemPicture3("/Elements/ItemElements/wintermelon.png");
        
        Item item4 = machine.getArraySlots().get(3).checkItem(); 
        testmaintenance.setItemName4(item4.getItemName());
        testmaintenance.setItemPrice4(((Sinker) item4).getPrice());
        testmaintenance.setItemQuantity4(machine.getArraySlots().get(3).getNumberOfStock());
        testmaintenance.setItemPicture4("/Elements/ItemElements/pearls.png");

        Item item5 = machine.getArraySlots().get(4).checkItem(); 
        testmaintenance.setItemName5(item5.getItemName());
        testmaintenance.setItemPrice5(((Sinker) item5).getPrice());
        testmaintenance.setItemQuantity5(machine.getArraySlots().get(4).getNumberOfStock());
        testmaintenance.setItemPicture5("/Elements/ItemElements/whitepearls.png");
        
        Item item6 = machine.getArraySlots().get(5).checkItem(); 
        testmaintenance.setItemName6(item6.getItemName());
        testmaintenance.setItemPrice6(((Sinker) item6).getPrice());
        testmaintenance.setItemQuantity6(machine.getArraySlots().get(5).getNumberOfStock());
        testmaintenance.setItemPicture6("/Elements/ItemElements/natadecoco.png");

        Item item7 = machine.getArraySlots().get(6).checkItem(); 
        testmaintenance.setItemName7(item7.getItemName());
        testmaintenance.setItemPrice7(((Sinker) item7).getPrice());
        testmaintenance.setItemQuantity7(machine.getArraySlots().get(6).getNumberOfStock());
        testmaintenance.setItemPicture7("/Elements/ItemElements/grassjelly.png");
        
        Item item8 = machine.getArraySlots().get(7).checkItem(); 
        testmaintenance.setItemName8(item8.getItemName());
        testmaintenance.setItemPrice8(((Sinker) item8).getPrice());
        testmaintenance.setItemQuantity8(machine.getArraySlots().get(7).getNumberOfStock());
        testmaintenance.setItemPicture8("/Elements/ItemElements/eggpudding.png");

        Item item9 = machine.getArraySlots().get(8).checkItem(); 
        testmaintenance.setItemName9(item9.getItemName());
        testmaintenance.setItemPrice9(((Sinker) item9).getPrice());
        testmaintenance.setItemQuantity9(machine.getArraySlots().get(8).getNumberOfStock());
        testmaintenance.setItemPicture9("/Elements/ItemElements/coffeejelly.png"); 
    }
    
    /**
     * Disable all of the pick buttons of vending machine.
     */
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

    /**
     * Restarts the machine after testing the regular machine.
     * It enables all of the pick buttons.
     */
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

    /**
     * Restarts the machine after testing the special machine.
     * It enables all of the pick buttons, except the flavor buttons.
     */
    public void restartTestingSpecial()
    {
        testvendfeatures.setPickSlot1Enable(true);
        testvendfeatures.setPickSlot2Enable(false);
        testvendfeatures.setPickSlot3Enable(false);
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