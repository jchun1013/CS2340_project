package Model;

/**
 * Created by joon1 on 2017-04-22.
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
    }
}
