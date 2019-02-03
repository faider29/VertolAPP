package com.andrienkom.vertolapp.entities;

import android.graphics.drawable.Drawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EntityNews {

    private int mId;
    private String mTitle;
    private String mText;
    private String mImg;
    private String mDate;


        private String header;
        private String date;
        private Drawable image;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public Drawable getImage() {
            return image;
        }

        public void setImage(Drawable image) {
            this.image = image;
        }
    }



    /**
     * Да, сущность не должна уметь себя парсить
     * но я считаю что должна.
     * @param response - на вход получаем сырой JSON
     * @return - если удачно распарсили, то возвращаем список обьектов
     * @throws JSONException - если словили exception, то прокидываем его вызывающему методу
     */
   /* public static List<EssenceNews> getArticlesFromJson(JSONArray response) throws JSONException {
        List<EssenceNews> essenceNewsList = new ArrayList<>();
        try {
            for(int i = 0; i < response.length(); i++) {
                JSONObject object = response.getJSONObject(i);
                EssenceNews essenceNews = new EssenceNews();
                essenceNews.setTitle(object.getString("title"));
                essenceNews.setId(i);
                essenceNews.setDate(object.getString("date"));
                essenceNews.setImg(object.getString("image"));
                essenceNewsList.add(essenceNews);
            }
        } catch (JSONException e) {
            throw e;
        }
        return essenceNewsList;
    }*/

