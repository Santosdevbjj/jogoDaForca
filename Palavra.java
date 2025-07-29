package model;
import exception.PalavraInvalidaException;
public class Palavra {
    private final String texto;
    private final char[] estado;
    private final String tema;

    public Palavra(String texto, String tema) {
        if (texto == null || texto.isBlank())
            throw new PalavraInvalidaException("Palavra inválida");
        this.texto = texto.toUpperCase();
        this.tema = tema;
        this.estado = new char[texto.length()];
        for (int i = 0; i < estado.length; i++) estado[i] = '_';
    }

    public boolean tentarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        boolean acertou = false;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == letra) {
                estado[i] = letra;
                acertou = true;
            }
        }
        return acertou;
    }

    public boolean isCompleta() {
        for (char c : estado) if (c == '_') return false;
        return true;
    }

    public String getEstado() {
        StringBuilder sb = new StringBuilder();
        for (char c: estado) sb.append(c).append(' ');
        return sb.toString().trim();
    }

    public String getTexto() { return texto; }
    public String getTema() { return tema; }
} 
