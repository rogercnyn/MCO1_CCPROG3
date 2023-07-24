
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
    
    public Factory (View view){
        this.view=view;
        
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Create")){
            System.out.print("Create");
        }
    }
}
