package com.company.model;

public class PassengerWagoon {
    long id;
    TypeWagoon typeWagoon;  //тип вагона
    int numberWagoon;       //номер вагина
    int amountSeats;        //колличество мест в вагоне
    int numberSeats;        //номер места в вагоне

    public PassengerWagoon(){  }

    public PassengerWagoon(long id, TypeWagoon typeWagoon, int numberWagoon, int amountSeats, int numberSeats) {
        this.id = id;
        this.typeWagoon = typeWagoon;
        this.numberWagoon = numberWagoon;
        this.amountSeats = amountSeats;
        this.numberSeats = numberSeats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public String toString() {
        return "PassengerWagoon{" +
                "id=" + id +
                ", typeWagoon=" + typeWagoon +
                ", numberWagoon=" + numberWagoon +
                ", amountSeats=" + amountSeats +
                ", numberSeats=" + numberSeats +
                '}'+"\n";
    }

    public enum TypeWagoon{
        PLATSKART,
        KYPE,
        CW
    }
}
