
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
    
    public Factory (){
        this.mainMenu= new mainMenu();
        this.createvending = new CreateVending();
        this.createregularview = new CreateRegularView();

        this.mainMenu.setVisible(true);
        
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
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Create")){
            System.out.print("Create");
        }
    }
}
