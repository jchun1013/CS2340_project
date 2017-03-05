package com.example.frys.waters.model;

/**
 * Created by Frys on 3/5/2017.
 */

public enum UserType {

    USER {
        @Override
        public String toString() { return "USER"; }
    },

    WORKER {
        @Override
        public String toString() { return "WORKER"; }
    },

    MANAGER {
        @Override
        public String toString() { return "MANAGER"; }
    },

    ADMIN {
        @Override
        public String toString() { return "ADMIN"; }
    };
}
