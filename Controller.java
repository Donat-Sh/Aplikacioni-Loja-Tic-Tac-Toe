import java.util.*;

public class Controller
{
    private Model model;

    public void setModel(Model m) // Inicializimi i Modelit
       {this.model = m;}

    public void setRequest()     // Kur kjo metode thirret nga Adapter atehere ne e bejme reset array tek modeli i cili pastaj e ben reset array me butons tek View
    {model.ResetModel();}

    public void setRequest(ArrayList<Integer> position)  //Kjo metode dergon kordinatat e kerkuara perkatese tek Modeli ashtu qe te kemi mundesin ti shqyrtojme levizjet e caktuara
    {
        model.PlayMove(position.get(0), position.get(1));
    }

}