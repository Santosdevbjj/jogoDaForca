package model;
import java.util.*;
public class Forca {
    private final int maxErros = 6;
    private int erros = 0;
    private Set<Character> tentativasErradas = new HashSet<>();

    public boolean registrarErro(char letra) {
        tentativasErradas.add(Character.toUpperCase(letra));
        erros++;
        return erros >= maxErros;
    }

    public boolean jaTentouErrado(char letra) {
        return tentativasErradas.contains(Character.toUpperCase(letra));
    }

    public String getBonecoAscii() {
        switch (erros) {
            case 0: return "\n\n\n\n\n";
            case 1: return "\n\n\n\n ___\n";
            case 2: return " |\n |\n |\n |\n_|_\n";
            case 3: return " ___\n | |\n O |\n   |\n _|_\n";
            case 4: return " ___\n | |\n O |\n | |\n _|_\n";
            case 5: return " ___\n | |\n O |\n/|\\|\n _|_\n";
            case 6: return " ___\n | |\n O |\n/|\\|\n/ \\|\n_|_\n";
            default: return "";
        }
    }

    public int getErros() { return erros; }
    public Set<Character> getTentativasErradas() { return tentativasErradas; }
    public int getMaxErros() { return maxErros; }
} 
