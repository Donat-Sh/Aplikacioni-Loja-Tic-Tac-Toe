import java.awt.event.*;
import java.util.*;

public class Adaptuesi implements ActionListener
{
    private Controller controller;
    private View view;

    public Adaptuesi(Controller c, View v)      // Inicializojme kontrollerin se bashku me View
    {this.controller = c;
     this.view = v;}

    // Kjo eshte nje pjese tjeter shume e rendesishme e aplikacionit: Shton ActionEvent tek metoda e prekrijuar ne Java, kjo dergon mesazhin 'actionPerformed' per gjdo klikim te kryer
    public void actionPerformed(ActionEvent event)
    {
        if(view.isReset(event))     // me ndihmen e ketij urdheri ne shikojme se a eshte klikuar butoni reset qe ta fshijme tabelen 
            {controller.setRequest();}
        else
            {
            ArrayList<Integer> position = view.getPosition(event);   // urdheri else e merr kete veprim dhe ja dergon tabeles i cili e dergon ate nga kontrolleri tek modeli dhe ai pastaj e ben Update tabelen me pozitat e caktuara
            controller.setRequest(position);
            }
    }
}
