package model;
import util.Dicionario;
import exception.PalavraInvalidaException;
import java.util.*;

public class JogoDaForca {
    private Dicionario dic = new Dicionario();
    private Historico historico;
    private Scanner sc = new Scanner(System.in);

    public JogoDaForca() {
        this.historico = new Historico();
    }

    public void iniciar() {
        System.out.println("### Forca Multi-Jogadores ###");
        System.out.print("Quantos jogadores? ");
        int n = Integer.parseInt(sc.nextLine());
        List<Jogador> jogadores = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            System.out.print("Nome do jogador " + i + ": ");
            jogadores.add(new Jogador(sc.nextLine().trim()));
        }

        boolean continuar = true;
        while (continuar) {
            System.out.println("Temas disponíveis: " + dic.temasDisponiveis());
            System.out.print("Escolha um tema: ");
            String tema = sc.nextLine().trim();
            if (!dic.temasDisponiveis().contains(tema)) {
                System.out.println("Tema inválido. Reiniciar seleção.");
                continue;
            }
            String palavraSecreta = dic.palavraAleatoria(tema);
            System.out.println("Iniciando rodada do tema: " + tema);

            for (Jogador jog : jogadores) {
                System.out.println("\nTurno: " + jog.getNome());
                Palavra pw = new Palavra(palavraSecreta, tema);
                Forca forca = new Forca();
                while (true) {
                    System.out.println("\nPalavra: " + pw.getEstado());
                    System.out.println(forca.getBonecoAscii());
                    System.out.println("Erradas: " + forca.getTentativasErradas());
                    System.out.println("Erros: " + forca.getErros() + "/" + forca.getMaxErros());
                    System.out.print("Opções: (1) Letra  (2) Palavra => ");
                    String op = sc.nextLine();
                    if ("2".equals(op)) {
                        System.out.print("Tente a palavra: ");
                        String tentativa = sc.nextLine().trim().toUpperCase();
                        if (tentativa.equals(pw.getTexto())) {
                            System.out.println("Acertou a palavra!");
                            jog.adicionarPonto();
                            historico.registrar(jog.getNome(), tema, pw.getTexto(), true);
                        } else {
                            System.out.println("Errou a palavra. Enforcado!");
                            historico.registrar(jog.getNome(), tema, pw.getTexto(), false);
                        }
                        break;
                    } else {
                        System.out.print("Digite uma letra: ");
                        String s = sc.nextLine();
                        if (s.isEmpty()) continue;
                        char letra = s.charAt(0);
                        if (forca.jaTentouErrado(letra)) {
                            System.out.println("Já tentou essa letra e errou.");
                            continue;
                        }
                        boolean ok = pw.tentarLetra(letra);
                        if (!ok) {
                            System.out.println("Letra incorreta.");
                            boolean enforcou = forca.registrarErro(letra);
                            if (enforcou) {
                                System.out.println(forca.getBonecoAscii());
                                System.out.println("Você foi enforcado! Palavra: " + pw.getTexto());
                                historico.registrar(jog.getNome(), tema, pw.getTexto(), false);
                                break;
                            }
                        } else {
                            System.out.println("Acertou!");
                            if (pw.isCompleta()) {
                                System.out.println("Parabéns! Palavra completa: " + pw.getTexto());
                                jog.adicionarPonto();
                                historico.registrar(jog.getNome(), tema, pw.getTexto(), true);
                                break;
                            }
                        }
                    }
                }
            }

            System.out.print("\nNova rodada? (s/n): ");
            continuar = sc.nextLine().trim().equalsIgnoreCase("s");
        }

        System.out.println("\n=== Histórico ===");
        historico.lerTudo().forEach(l -> System.out.println(l));

        System.out.println("\n=== Placar Final ===");
        jogadores.forEach(j -> System.out.printf("%s: %d pontos%n", j.getNome(), j.getPontos()));
    }
}
