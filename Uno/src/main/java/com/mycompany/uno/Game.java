/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uno;

import java.awt.Font;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game {
    private int currentPlayer;  // track pemain yang dapat giliran
    private String[] playerIds; // kumpulan nama pemain

    private UnoDeck deck;       // deck yang dimainkan pemain
    private ArrayList<ArrayList<UnoCard>> playerHand; //sekumpulan tangan pemain yang memegang sekumpulan kartu
    private ArrayList<UnoCard> stockpile;   // untuk saat menaruh kartu    

    private UnoCard.Color validColor;
    private UnoCard.Value validValue;

    boolean gameDirection;

    public Game(String[] pids) {
        deck = new UnoDeck();
        deck.shuffle();
        stockpile = new ArrayList<UnoCard>();

        playerIds = pids;
        currentPlayer = 0;
        gameDirection = false;

        playerHand = new ArrayList<ArrayList<UnoCard>>();

        for (int i = 0; i < pids.length; i ++){
            // membuat segenggam 7 kartu
            ArrayList<UnoCard> hand = new ArrayList<UnoCard>(Arrays.asList(deck.drawCard(7)));
            // for(int j = 0; j < hand.size(); j++) {   
            // System.out.print(hand.get(j));
            // } 
            // membagikan segenggam kartu ke tangan pemain
            playerHand.add(hand);
        }
    }

    public void start(Game game) {
        UnoCard card = deck.drawCard();
        validColor = card.getColor();
        validValue = card.getValue();

        if (card.getValue() == UnoCard.Value.Wild) {
            start(game);
        }

        if (card.getValue() == UnoCard.Value.Wild_Four || card.getValue() == UnoCard.Value.DrawTwo) {
            start(game);
        }

        if (card.getValue() == UnoCard.Value.Skip) {
            JLabel message = new JLabel(playerIds[currentPlayer] + " diskip");
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, message);

            if (gameDirection == false) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            }

            else if (gameDirection == true) {
                currentPlayer = (currentPlayer - 1) % playerIds.length;
                if (currentPlayer == -1) {
                    currentPlayer = playerIds.length - 1;
                }
            }
        }

        if (card.getValue() == UnoCard.Value.Reverse) {
            JLabel message = new JLabel(playerIds[currentPlayer] + " arah permainan berubah!");
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, message);
            gameDirection ^= true; //mereverse arah
            currentPlayer = playerIds.length - 1;
        }

        stockpile.add(card);
    }

    public UnoCard getTopCard() {
        return new UnoCard(validColor, validValue);
    }

    public ImageIcon getTopCardImage() {
        return new ImageIcon(validColor + "_" + validValue + ".png");
    }

    public boolean isGameOver() {
        for (String player : this.playerIds) {
            if (hasEmptyHand(player)) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentPlayer() {
        return this.playerIds[this.currentPlayer];
    }

    public String getPreviousPlayer(int i) {
        int index = this.currentPlayer - i;
        if (index == -1) {
            index = playerIds.length - 1;
        }
        return this.playerIds[index];
    }

    public String[] getPlayers() {
        return playerIds;
    }

    public ArrayList<UnoCard> getPlayerHand(String pid) {
        int index = Arrays.asList(playerIds).indexOf(pid);
        // for(int i = 0; i < playerHand.size(); i++) {   
        //     System.out.print(playerHand.get(i));
        // }  
        return playerHand.get(index);
    }

    public int getPlayerHandSize(String pid) {
        return getPlayerHand(pid).size();
    }

    public UnoCard getPlayerCard(String pid, int choice) {
        ArrayList<UnoCard> hand = getPlayerHand(pid);
        return hand.get(choice);
    }

    public boolean hasEmptyHand(String pid) {
        return getPlayerHand(pid).isEmpty();
    }

    public boolean validCardPlay(UnoCard card) {
        return card.getColor() == validColor || card.getValue() == validValue;
    }

    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException {
        if (this.playerIds[this.currentPlayer] != pid) {
            throw new InvalidPlayerTurnException("Bukan giliran pemain " + pid, pid);
        }
    }

    public void submitDraw(String pid) throws InvalidPlayerTurnException {
        checkPlayerTurn(pid);

        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockpile);
            deck.shuffle();
        }

        getPlayerHand(pid).add(deck.drawCard());
        if (gameDirection == false) {
            currentPlayer = (currentPlayer + 1) % playerIds.length;
        }

        else if (gameDirection == true) {
            currentPlayer = (currentPlayer - 1) % playerIds.length;
            if (currentPlayer == -1) {
                currentPlayer = playerIds.length - 1;
            }
        }
    }

    public void setCardColor(UnoCard.Color color) {
        validColor = color;
    }

    public void submitPlayerCard(String pid, UnoCard card, UnoCard.Color declaredColor) 
        throws InvalidColorSubmissionException, InvalidValueSubmissionException, InvalidPlayerTurnException {
        checkPlayerTurn(pid);

        ArrayList<UnoCard> pHand = getPlayerHand(pid);

        if (!validCardPlay(card)) {
            if (card.getColor() == UnoCard.Color.Wild) {
                validColor = card.getColor();
                validValue = card.getValue();
            }

            if (card.getColor() != validColor) {
                JLabel message = new JLabel("Warna salah, seharusnya warna: " + validColor + " bukan warna " + card.getColor());
                message.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message);
                throw new InvalidColorSubmissionException("Warna salah, seharusnya warna: " + validColor + " bukan warna " + card.getColor(), card.getColor(), validColor);
            }

            else if (card.getValue() != validValue) {
                JLabel message2 = new JLabel("angka salah, seharusnya angka: " + validColor + " bukan angka " + card.getValue());
                message2.setFont(new Font("Arial", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null, message2);
                throw new InvalidValueSubmissionException("angka salah, seharusnya angka: " + validColor + " bukan angka " + card.getValue(), card.getValue(), validValue);
            }
        }

        pHand.remove(card);

        if (hasEmptyHand(this.playerIds[currentPlayer])) {
            JLabel message3 = new JLabel("Pemain " + this.playerIds[currentPlayer] + " Menang!");
            message3.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, message3);
            System.exit(0);
        }

        validColor = card.getColor();
        validValue = card.getValue();
        stockpile.add(card);

        if (gameDirection == false) {
            currentPlayer = (currentPlayer + 1) % playerIds.length;
        }
        else if (gameDirection == true) {
            currentPlayer = (currentPlayer - 1) % playerIds.length;
            if (currentPlayer == -1) {
                currentPlayer = playerIds.length - 1;
            }
        }

        if (card.getColor() == UnoCard.Color.Wild) {
            validColor = declaredColor;
        }

        if (card.getValue() == UnoCard.Value.DrawTwo) {
            pid = playerIds[currentPlayer];
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            JLabel message = new JLabel(pid + " telah ambil 2 kartu");
        }

        if (card.getValue() == UnoCard.Value.Wild_Four) {
            pid = playerIds[currentPlayer];
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            JLabel message = new JLabel(pid + " telah ambil 4 kartu");
        }

        if (card.getValue() == UnoCard.Value.Skip) {
            JLabel message = new JLabel("Pemain " + playerIds[currentPlayer] + " diskip.");
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, message);
            if (gameDirection == false) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            }
            else if (gameDirection == true) {
                currentPlayer = (currentPlayer - 1) % playerIds.length;
                if (currentPlayer == -1) {
                    currentPlayer = playerIds.length - 1;
                }
            }
        }

        if (card.getValue() == UnoCard.Value.Reverse) {
            JLabel message = new JLabel(pid + " merubah arah permainan");
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, message);

            gameDirection ^= true;
            if (gameDirection = true) {
                currentPlayer = (currentPlayer - 2) % playerIds.length;
                if (currentPlayer == -1) {
                    currentPlayer = playerIds.length - 1;
                }

                if (currentPlayer == -2) {
                    currentPlayer = playerIds.length - 2;
                }
            }
            else if (gameDirection == false) {
                currentPlayer = (currentPlayer + 2) % playerIds.length;
            }
        }
    }
}

class InvalidPlayerTurnException extends Exception {
    String playerId;

    public InvalidPlayerTurnException(String message, String pid) {
        super(message);
        playerId = pid;
    }

    public String getPid() {
        return playerId;
    }
}

class InvalidColorSubmissionException extends Exception {
    private UnoCard.Color expected;
    private UnoCard.Color actual;

    public InvalidColorSubmissionException(String message, UnoCard.Color actual, UnoCard.Color expected) {
        this.actual = actual;
        this.expected = expected;
    }
}

class InvalidValueSubmissionException extends Exception {
    private UnoCard.Value expected;
    private UnoCard.Value actual;

    public InvalidValueSubmissionException(String message, UnoCard.Value actual, UnoCard.Value expected) {
        this.actual = actual;
        this.expected = expected;
    }
}