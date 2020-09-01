package model;

import java.util.Objects;

public class PassengerWagoon extends BaseEntity {

    Long id = super.getId();
    TypeWagoon typeWagoon;  //тип вагона
    int numberWagoon;       //номер вагина
    int amountSeats;        //колличество мест в вагоне
    int numberSeats;        //номер места в вагоне

    public PassengerWagoon() {
    }

    public PassengerWagoon(TypeWagoon typeWagoon, int numberWagoon, int amountSeats, int numberSeats) {
        this.typeWagoon = typeWagoon;
        this.numberWagoon = numberWagoon;
        this.amountSeats = amountSeats;
        this.numberSeats = numberSeats;
    }

    public TypeWagoon getTypeWagoon() {
        return typeWagoon;
    }

    public void setTypeWagoon(TypeWagoon typeWagoon) {
        this.typeWagoon = typeWagoon;
    }

    public int getNumberWagoon() {
        return numberWagoon;
    }

    public void setNumberWagoon(int numberWagoon) {
        this.numberWagoon = numberWagoon;
    }

    public int getAmountSeats() {
        return amountSeats;
    }

    public void setAmountSeats(int amountSeats) {
        this.amountSeats = amountSeats;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerWagoon)) return false;
        PassengerWagoon that = (PassengerWagoon) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "PassengerWagoon{" +
                ", typeWagoon=" + typeWagoon +
                ", numberWagoon=" + numberWagoon +
                ", amountSeats=" + amountSeats +
                ", numberSeats=" + numberSeats +
                '}' + "\n";
    }

    public enum TypeWagoon {
        PLATSKART,
        KYPE,
        CW
    }
}
