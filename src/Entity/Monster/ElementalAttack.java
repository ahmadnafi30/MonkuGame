/**
 * The `ElementalAttack` class in the `Entity.Monster` package defines properties for a monster's
 * elemental attack including name, power, and element type.
 */
package Entity.Monster;

import Entity.Monster.Monster.ElementType;

public class ElementalAttack {
    protected String nama;
    protected int power;
    protected ElementType element;
    protected int quantity;

    public ElementalAttack(String nama, int power, ElementType element, int quantity) {
        this.nama = nama;
        this.power = power;
        this.element = element;
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getNama() {
        return this.nama;
    }

    public int getPower() {
        return this.power;
    }

    public ElementType getElement() {
        return this.element;
    }

    // @Override
    // public void toString(){}

}
