package util;
import java.util.*;
public class Dicionario {
    private Map<String, List<String>> temas = new HashMap<>();
    public Dicionario() { loadTemas(); }
    private void loadTemas() {
        temas.put("Animais", List.of("CACHORRO","GATO","ELEFANTE","TIGRE"));
        temas.put("Cores", List.of("VERMELHO","AZUL","VERDE","AMARELO"));
        temas.put("Países", List.of("BRASIL","CANADA","JAPAO","FRANCA"));
    }
    public Set<String> temasDisponiveis() { return temas.keySet(); }
    public String palavraAleatoria(String tema) {
        List<String> lista = temas.get(tema);
        return lista.get(new Random().nextInt(lista.size()));
    }
}
