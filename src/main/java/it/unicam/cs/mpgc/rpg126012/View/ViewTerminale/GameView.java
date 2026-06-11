package it.unicam.cs.mpgc.rpg126012.View.ViewTerminale;
import java.util.Scanner;
/*classe per generare una view provvisoria da terminale per testare il funzionamento del programma*/
public class GameView {
    private Scanner scanner;
    //quando creo la view inizializzo lo scanner
    public GameView(){
        this.scanner = new Scanner(System.in);
    }

    //metodo per stampare
    public void mostraTesto(String testo){
        System.out.println("\n" + testo + "\n");
    }
    //metodo per prendere input utente
    public int leggiScelta(){
        int scelta=-1;
        boolean inputValido=false;
        while(!inputValido){
            System.out.println("Inserisci la tua scelta(1 o 2): ");
            String input = scanner.nextLine();//leggo l'input come stringa
            try{
                scelta = Integer.parseInt(input);//trasformo l'input in intero
                if(scelta==1 || scelta==2){
                    inputValido=true;
                }
                else System.out.println("Scelta non valida, inserire 1 o 2");
            }catch(NumberFormatException e){
                //se l'utente inserisce lettere
                System.out.println("Input deve essere un numero, 1 o 2");
            }
        }
        return scelta-1;//-1 per convertire 1 in 0 e 2 in 1
    }
}
