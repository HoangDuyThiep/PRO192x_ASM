package vn.funix.FX21316.java.asm04.exception;

    public class CustomerIdNotValidException extends Exception {
        private String message;
        public CustomerIdNotValidException(String message) {
            this.message = message;

        }
        @Override
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

