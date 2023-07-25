
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
    private View view;
    private CreateVending createvending;
    
    public Factory (){
        this.view= new View();
        this.createvending = new CreateVending();

        this.view.setVisible(true);
        
        this.view.setCreateBtnListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                view.setVisible(false);
                createvending.setVisible(true);
            }
        });

        this.createvending.setBackBtnListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                createvending.setVisible(false);
                view.setVisible(true);
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
