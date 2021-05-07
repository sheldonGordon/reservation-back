package fr.chatelain.reservation.exceptions;

public class RepositoryExeption extends Exception {

    private static final long serialVersionUID = -745705969974428576L;

    public RepositoryExeption() {
        super();
    }

    public RepositoryExeption(String message) {
        super(message);
    }

    public RepositoryExeption(String message, Throwable exception) {
        super(message, exception);
    }
}
