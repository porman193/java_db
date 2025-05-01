package org.example.datos;

import org.example.datos.service.CatService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int optionMenu = -1;
        String [] buttonsMenu = {
                "1. Show Cats",
                "2. Show favorites",
                "3. Exit"
        };

        do{
            String option = (String) JOptionPane.showInputDialog(
                    null,
                    "Select an option",
                    "Menu",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    buttonsMenu,
                    buttonsMenu[0]
            );

            switch (option){
                case "1. Show Cats":
                    CatService.showCats();
                    break;
                case "2. Show favorites":
                    CatService.showFavorites();
                    break;
                case "3. Exit":
                    optionMenu = 1;
                    break;
                default:
                   break;
            }
        }while(optionMenu != 1);
    }
}