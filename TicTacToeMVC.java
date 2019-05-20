public class TicTacToeMVC
{
    public static void main(String args[])
    {
        Controller controller = new Controller();  //Ketu kemi krijuar Kontrollerin
        View view = new View();                    //Ketu kemi krijuar View
        Model model = new Model();                 //Ketu kemi krijuar Modelin

        model.registerView(view);
        controller.setModel(model);
        view.setActionListener(controller);     // Ky urdher fillon lojen, pasi qe ne kete moment kemi arritur stasis!
    }
}