package com.example.barthing;

public class Drug {
    String Name;
    Integer Amount;
    String Id;
    Integer Price;

    public Drug(String Name, Integer Amount, String Id, Integer Price) {
        this.Name = Name;
        this.Amount = Amount;
        this.Id = Id;
        this.Price = Price;
    }

    public Integer getAmount() {
        return Amount;
    }

    public String getId() {
        return Id;
    }

    public Integer getPrice() {
        return Price;
    }

    public String getName() {
        return Name;
    }
}
