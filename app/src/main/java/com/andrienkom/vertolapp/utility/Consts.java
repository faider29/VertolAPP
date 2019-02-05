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
                return "fsk";
            }
        },
        sdk {
            public String toString() {
                return "sdk";
            }
        },
        ry {
            public String toString() {
                return "ry";
            }
        }
    }
}
