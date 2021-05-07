package fr.chatelain.reservation.exceptions;

public class RepositoryExeption extends Exception{
    
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
