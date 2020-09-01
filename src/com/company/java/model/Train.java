package model;

import java.util.HashSet;
import java.util.Set;

public class Train extends NamedEntity{
    Locomotive locomotive;
    Set<PassengerWagoon> wagoons;

    public Train() {
    }

    public Train(Locomotive locomotive, Set<PassengerWagoon> wagoons) {
        this.locomotive = locomotive;
        this.wagoons = wagoons;
    }

    public Train(String name, Locomotive locomotive, Set<PassengerWagoon> wagoons) {
        super(name);
        this.locomotive = locomotive;
        this.wagoons = wagoons;
    }

    public Train(Long id, String name, Locomotive locomotive, Set<PassengerWagoon> wagoons) {
        super(id, name);
        this.locomotive = locomotive;
        this.wagoons = wagoons;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public Set<PassengerWagoon> getWagoons() {
        return wagoons;
    }

    public void setWagoons(Set<PassengerWagoon> wagoons) {
        this.wagoons = wagoons;
    }

    @Override
    public String toString() {
        return "Train{" +
                "locomotive=" + locomotive +
                ", wagoons=" + wagoons +
                "} " + super.toString();
    }
}
