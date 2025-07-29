package model;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
public class Historico {
    private final Path path = Paths.get("historico_forca.csv");
    public Historico() {
        try {
            if (!Files.exists(path)) {
                Files.write(path, List.of("Jogador,Data,Tema,Palavra,Resultado"));
            }
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar histórico", e);
        }
    }
    public void registrar(String jogador, String tema, String palavra, boolean venceu) {
        String linha = String.format("%s,%s,%s,%s,%s",
            jogador, LocalDate.now(), tema, palavra,
            venceu ? "VENCEU" : "PERDEU");
        try {
            Files.write(path, List.of(linha), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Erro ao gravar histórico: " + e.getMessage());
        }
    }
    public List<String> lerTudo() {
        try { return Files.readAllLines(path); }
        catch (IOException e) { return List.of("Erro lendo histórico: " + e.getMessage()); }
    }
}
