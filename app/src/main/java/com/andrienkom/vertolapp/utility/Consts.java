package com.andrienkom.vertolapp.utility;


public abstract class Consts {

    public static final String BASE_URL = "http://46.229.215.224:9999/api/";


    public enum Category {
        all {
            public String toString() {
                return "all";
            }
        },
        fsk {
            public String toString() {
                return "FSK";
            }
        },
        sdk {
            public String toString() {
                return "SDK";
            }
        },
        ry {
            public String toString() {
                return "RY";
            }
        }
    }

    public enum Month{
        all{
            public String toString(){
                return "null";
            }
        },
        january{
            public String toString(){
                return "January";
            }
        },
        february{
            public String toString(){
                return "February";
            }
        },
        march{
            public String toString(){
                return "March";
            }
        },
        april{
            public String toString(){
                return "April";
            }
        },
        may{
            public String toString(){
                return "May";
            }
        },
        june{
            public String toString(){
                return "June";
            }
        },
        july{
            public String toString(){
                return "July";
            }
        },
        august{
            public String toString(){
                return "August";
            }
        },
        september{
            public String toString(){
                return "September";
            }
        },
        october{
            public String toString(){
                return "October";
            }
        },
        november{
            public String toString(){
                return "November";
            }
        },
        december{
            public String toString(){
                return "December";
            }
        }

    }

}
