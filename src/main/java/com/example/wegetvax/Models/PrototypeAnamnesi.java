package com.example.wegetvax.Models;

/**
 * L'nterfaccia PrototypeAnamnesi permette di dichiarare il metodo astratto 'clone' che è responsabile della clonazione di un oggetto di tipo 'ConcretePrototypeAnamnesi'.
 */
public interface PrototypeAnamnesi {

    public abstract ConcretePrototypeAnamnesi clone();
}
