/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uno;

public class UnoCard {
    // Atribut Kartu 
    // menggunakan enum agar value kartu bisa disimpan jelas tapi bisa disimpan dengan array integer 
    enum Color{
        Red, Blue, Green, Yellow, Wild;
        
        private static final Color[] colors = Color.values();
        public static Color getColor (int i){
            return Color.colors[i];
        }
    }
    enum Value{
        Zero, One, Two, three, Four, Five, Six, Seven, Eight, Nine, DrawTwo, Skip, Reverse, Wild, Wild_Four;
        
        private static final Value[] values = Value.values();
        public static Value getValue (int i){
            return Value.values[i];
        }
    }

    // Private agar tidak diakses class lain
    // Final agar menetapkan value tidak berubah
    private final Color color;
    private final Value value;

    public UnoCard (final Color color, final Value value){
        this.color = color;
        this.value = value;
    }

    public Color getColor(){
        return this.color;
    }

    public Value getValue(){
        return this.value;
    }

    // mengubah return address menjadi return color dan value
    public String toString(){
        return color + "_" + value;
    }
}
