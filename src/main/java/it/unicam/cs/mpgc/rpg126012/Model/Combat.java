package it.unicam.cs.mpgc.rpg126012.Model;
/*
* Interfaccia per il combattimento
* Per futuri sviluppi di diversi tipi di combattimento
* interfaccia di base da poter implementare in diversi modi
* */
public interface Combat {
    public boolean Win();
    public void doCombat();
}
