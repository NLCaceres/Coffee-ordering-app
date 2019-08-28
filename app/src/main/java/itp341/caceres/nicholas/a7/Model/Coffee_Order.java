package itp341.caceres.nicholas.a7.Model;

/**
 * Created by NLCaceres on 3/10/2016.
 */
public class Coffee_Order {
    private String Name;
    private String Size;
    private String Brew;
    private Boolean Sugar;
    // If true then sugar is added and switch in mainActivity is on
    private Boolean Milk;
    // If true then milk is added and box in mainActivity checked
    private String SpecialInstructions;

    public Coffee_Order() {
        Name = "";
        Size = "Small";
        Brew = "Kona";
        Sugar = false;
        Milk = false;
        SpecialInstructions = "";
    }

    public Coffee_Order(String name, String size, String brew, Boolean sugar, Boolean milk, String specialInstructions) {
        Name = name;
        Size = size;
        Brew = brew;
        Sugar = sugar;
        Milk = milk;
        SpecialInstructions = specialInstructions;
    }

    @Override
    public String toString() {

        if (Sugar == true && Milk == true) {
            return "1 " + Size + " " + Brew + " for " + Name + " with sugar and milk. " + SpecialInstructions;
        }
        else if (Sugar == true && Milk == false) {
            return "1 " + Size + " " + Brew + " for " + Name + " with sugar, no milk. " + SpecialInstructions;
        }
        else if (Sugar == false && Milk == true) {
            return "1 " + Size + " " + Brew + " for " + Name + " no sugar, add milk. " + SpecialInstructions;
        }
        else {
            return "1 " + Size + " " + Brew + " for " + Name + " without sugar and milk. " + SpecialInstructions;
        }
    }

    public String getCustomerName() {
        return Name;
    }
    public void setCustomerName(String name) {
        Name = name;
    }

    public String getSize() {
        return Size;
    }
    public void setSize(String size) {
        Size = size;
    }

    public String getBrew() {
        return Brew;
    }
    public void setBrew(String brew) {
        Brew = brew;
    }

    public Boolean getSugar() {
        return Sugar;
    }
    public void setSugar(Boolean sugar) {
        Sugar = sugar;
    }

    public Boolean getMilk() {
        return Milk;
    }
    public void setMilk(Boolean milk) {
        Milk = milk;
    }

    public String getSpecialInstructions() {
        return SpecialInstructions;
    }
    public void setSpecialInstructions(String specialInstructions) {
        SpecialInstructions = specialInstructions;
    }



}
