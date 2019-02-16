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
}
