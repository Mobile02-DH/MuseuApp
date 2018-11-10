package br.edu.digitalhouse.museuapp.Interfaces;

public interface ServiceListener {

    void onSucess(Object object);

    void onError(Throwable throwable);
}
