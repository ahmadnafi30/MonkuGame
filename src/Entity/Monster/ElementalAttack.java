package Entity.Monster;

import Entity.Monster.Monster.ElementType;

public class ElementalAttack {
    protected String nama;
    protected int power;
    protected ElementType element;

    public ElementalAttack(String nama, int power, ElementType element) {
        this.nama = nama;
        this.power = power;
        this.element = element;
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
