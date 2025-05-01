package org.example.datos.service;

import com.google.gson.Gson;
import okhttp3.*;
import org.example.datos.Cat;
import org.example.datos.CatFavorite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CatService {

    private static OkHttpClient client = new OkHttpClient();
    private static  Gson gson = new Gson();
    public static  void showCats() {
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            String reponseBody = response.body().string();
            reponseBody = reponseBody.substring(1, reponseBody.length() - 1);
            Cat cat = gson.fromJson(reponseBody, Cat.class);

            String menu = "Options:\n" +
                    "1. Show Cat\n" +
                    "2. Favorite\n" +
                    "3. Go Back\n";

            String [] buttonsMenu = {
                    "1. Show Cat",
                    "2. Favorite",
                    "3. Go Back"
            };

            showCatImage(cat,menu,buttonsMenu,1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void  saveFavorite(Cat cat){
        try{
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(mediaType, "{\n    \"image_id\":\""+cat.getId()+"\"\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .post(requestBody)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", cat.getApiKey())
                    .build();
            Response response = client.newCall(request).execute();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public static void showFavorites(){
        Cat cat = new Cat();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .get()
                .addHeader("x-api-key", cat.getApiKey())
                .build();

        try {
            Response response = client.newCall(request).execute();
            String reponseBody = response.body().string();

            CatFavorite[] catFavorites = gson.fromJson(reponseBody, CatFavorite[].class);

            if(catFavorites.length > 0){
                int min = 1;
                int max = catFavorites.length;
                int randomIndex = (int) (Math.random() * (max - min + 1) + min);
                CatFavorite catFavorite = catFavorites[randomIndex - 1];
                cat.setId(catFavorite.getImage_id());
                cat.setUrl(catFavorite.getImage().getUrl());
                cat.setIdFavorite(catFavorite.getId());
            }

            String menu = "Options:\n" +
                    "1. Show Cat\n" +
                    "2. Delete from favorites\n" +
                    "3. Go Back\n";

            String [] buttonsMenu = {
                    "1. Show Cat",
                    "2. Delete from favorites",
                    "3. Go Back"
            };


            showCatImage(cat,menu,buttonsMenu,2);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showCatImage(Cat cat,String menu,String [] buttonsMenu,int menuType) {
        try{
            URL url = new URL(cat.getUrl());
            Image image = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(image);

            if(icon.getIconWidth() > 800 || icon.getIconHeight() > 800){
                icon = new ImageIcon(icon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH));
            }

            String option = (String) JOptionPane.showInputDialog(
                    null,
                    menu,
                    cat.getId(),
                    JOptionPane.INFORMATION_MESSAGE,
                    icon,
                    buttonsMenu,
                    buttonsMenu[0]);

            if (menuType == 1) {
                if (option.equals(buttonsMenu[0])) {
                    showCats();
                } else if (option.equals(buttonsMenu[1])) {
                    saveFavorite(cat);
                }
            } else if (menuType == 2) {
                if (option.equals(buttonsMenu[0])) {
                    showFavorites();
                } else if (option.equals(buttonsMenu[1])) {
                    deleteFromFavorites(cat.getIdFavorite(), cat.getApiKey());
                }
            }

        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public static void deleteFromFavorites(long id, String apiKey){
        try{
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/"+id)
                    .method("DELETE", body)
                    .addHeader("x-api-key", apiKey)
                    .build();
            Response response = client.newCall(request).execute();
            if(response.code() == 200) {
                JOptionPane.showMessageDialog(null, "Deleted from favorites");
            }else {
                JOptionPane.showMessageDialog(null, "Error deleting from favorites");
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
