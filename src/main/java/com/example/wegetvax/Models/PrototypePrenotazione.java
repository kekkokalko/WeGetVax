package com.example.wegetvax.Models;

/**
 * L'nterfaccia PrototypePrenotazione permette di dichiarare il metodo astratto 'clone' che è responsabile della clonazione di un oggetto di tipo 'ConcretePrototypePrenotazione'.
 */
public interface PrototypePrenotazione {

    public abstract ConcretePrototypePrenotazione clone();
}
