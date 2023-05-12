/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uno;

import java.io.RandomAccessFile;
import java.security.UnrecoverableEntryException;
import java.util.ArrayList;
import java.util.Random;

import javax.print.event.PrintServiceAttributeListener;
import javax.swing.ImageIcon;

/*
 * Class Deck terdiri dari 108 kartu uno dengan 4 warna (merah, kuning, biru, hijau)
 * Masing - masing memiliki satu buah kartu 0, dua buah kartu 1, 2, 3, 4, 5, 6, 7, 8, 9; dua kartu skip, dua kartu +2, dua kartu reverse
 * Terdapat tambahan empat kartu wild dan empat kartu wild+4
 */
public class UnoDeck {

    // Array kartu di deck
    private UnoCard[] cards;

    // track berapa kartu di deck
    private int cardsInDeck;

    // Jumlah kartu dalam deck
    public UnoDeck(){
        cards = new UnoCard[108];
        reset();
    }

    // mengisi value dari kartu dalam deck
    public void reset(){

        // mengisi value dari class color
        UnoCard.Color[] colors = UnoCard.Color.values();
        cardsInDeck = 0;

        // mengisi angka dan warna
        for (int i = 0; i <colors.length-1; i++){
            // mengisi value warna kartu dengan semua warna kecuali wild
            // kartu diisi mulai dari red hingga yellow secara berurutan
            UnoCard.Color color = colors[i];

            // Menetapkan satu kartu 0
            cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(0));

            // Menetapkan masing-masing dua buah kartu dari angka 1-9
            for (int j = 1; j < 10; j++){
                cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(i));
                cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(i));
            }
            
            // Array baru untuk mengisi kartu powerup
            UnoCard.Value[] values = new UnoCard.Value[] {UnoCard.Value.DrawTwo, UnoCard.Value.Skip, UnoCard.Value.Reverse};
            for(UnoCard.Value value :values){
                cards[cardsInDeck++] = new UnoCard(color, value);
                cards[cardsInDeck++] = new UnoCard(color, value);
            }
        }
        
        // Array baru untuk mengisi kartu wild
        UnoCard.Value[] values = new UnoCard.Value[] {UnoCard.Value.Wild, UnoCard.Value.Wild_Four};
        for (UnoCard.Value value : values){
            // buat 4 wild wild card dan wild +4
            for (int i = 0; i < 4; i++){
                cards[cardsInDeck++] = new UnoCard(UnoCard.Color.Wild, value);
            }
        }
    }
    // public void replaceDeckWith(ArrayList<UnoCard> cards){
    //     this.cards = cards.toArray(new UnoCard(cards, si));
    // }
    /*
     * 
     * @param cards (stockpile)
     * mengganti dek dengan sebuah arraylist berisi unocard (the stockpile/persediaan)
     */
    public void replaceDeckWith(ArrayList<UnoCard> cards) {
        this.cards = cards.toArray(new UnoCard[cards.size()]);  // merubah arraylist unocard
        this.cardsInDeck = this.cards.length;                   // menjadi array normal unocard
    }

    /*
     * 
     * @return
     */
    public boolean isEmpty(){
        return cardsInDeck == 0;
    }

    public void shuffle(){
        int n = cards.length;
        Random random = new Random();

        for (int i = 0; i < cards.length; i++) {

            // dapatkan indeks acak dari array melewati indeks saat ini
            // ... argumen berupa sebuah exclusive bound
            // menukar elemen acak dengan element saat ini
            
            int randomValue = i + random.nextInt(n - i);
            UnoCard randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }

    public UnoCard drawCard() throws IllegalArgumentException {
        if (isEmpty()){
            throw new IllegalArgumentException("Tidak bisa ambil kartu karena tidak ada kartu dalam dek");
        }
        return cards[--cardsInDeck];
    }

    public ImageIcon drawCardImage() throws IllegalArgumentException {
        if(isEmpty()){
            throw new IllegalArgumentException("Tidak bisa ambil kartu karena dek kosong");
        }
        return new ImageIcon(cards[--cardsInDeck].toString() + ".png");
    }

    public UnoCard[] drawCard(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Harus ambil kartu positif tapi mencoba mengambil " + n + " kartu");
        }

        if (n > cardsInDeck) {
            throw new IllegalArgumentException("Tidak bisa ambil " + n + " kartu karena hanya ada " + cardsInDeck + " kartu pada dek");
        }

        UnoCard[] ret = new UnoCard[n];

        for (int i = 0; i < 0; i++){
            ret[i] = cards[--cardsInDeck];
        }
        return ret;
    }
}
