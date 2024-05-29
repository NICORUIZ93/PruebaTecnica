package com.pruebatecnica.backend.constans;

public class CoreRouter {
    private static final String API = "cats";

    public static class ThecatAPI {
        private ThecatAPI() {
            throw new IllegalStateException("TheCatAPI");
        }

        public static final String ROOT = API + "/consult";
        public static final String BREEDS = "/breeds";
        public static final String BREED_BY_ID = "/breeds/{breedId}";
        public static final String SEARCH_BREEDS = "/breeds/search/{searchBreed}";


    }
}
