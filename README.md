## Criando um Jogo da Forca com uma Aplicação Console Java.

![NTT-Java](https://github.com/user-attachments/assets/149e1032-79be-4f8a-8fb0-2019216b05e3)


**Bootcamp NTT DATA - Java e IA para Iniciantes**


🪢 **Jogo da Forca em Java (Console)**

📌 **Descrição do Projeto**

Este projeto é uma implementação completa do jogo da forca em Java, com Programação Orientada a Objetos (POO), menu robusto, temas pré-definidos, modo multi-jogador, pontuação acumulada, histórico de partidas salvo em arquivo CSV e desenho ASCII do boneco da forca.

O jogo roda no console e foi projetado para praticar conceitos de POO, como encapsulamento, separação de responsabilidades, manipulação de listas, enums, exceções personalizadas e persistência simples em arquivos.


✅ **Funcionalidades**

- Escolha de temas pré-definidos (Animais, Cores, Países);

- **Modo multi-jogador:** escolha do número de jogadores e alternância de turnos;

- Controle de erros e exibição visual do boneco (ASCII);

- Tentativas de letras ou palavra completa;

- Pontuação acumulada por jogador;

- Histórico de partidas salvo em arquivo CSV, incluindo data, jogador, tema, palavra e resultado;

- Menu para jogar várias rodadas e exibir placar final.



📂 **Explicação das Classes**

**1. App.java**

Ponto de entrada da aplicação (main).

Cria uma instância de JogoDaForca e chama iniciar() para iniciar o jogo.




**2. PalavraInvalidaException.java**

Exceção personalizada lançada quando a palavra secreta é inválida (vazia ou nula).



**3. Dicionario.java**

Contém os temas e as listas de palavras disponíveis.

**Método palavraAleatoria(tema)** retorna uma palavra aleatória do tema escolhido.

**Método temasDisponiveis()** retorna os nomes dos temas cadastrados.



**4. Palavra.java**

Representa a palavra secreta do jogo.

Gerencia o estado atual (letras descobertas).

**Métodos principais:**

**tentarLetra(char letra):** verifica e revela as letras corretas.

**isCompleta():** indica se a palavra foi completamente descoberta.

**getEstado():** retorna a palavra com as letras descobertas e tracinhos para as ocultas.



**5. Forca.java**

Controla o número de erros e gera o **desenho ASCII** do boneco.

Mantém lista de letras erradas.

**Métodos principais:**

**registrarErro(char letra):** adiciona letra errada e retorna se jogador foi enforcado.

**getBonecoAscii():** retorna a representação gráfica atual.



**6. Jogador.java**

Representa um jogador, armazenando:

Nome;

Pontuação acumulada.


**Método adicionarPonto()** para incrementar a pontuação ao vencer.



**7. Historico.java**

Salva e carrega histórico de partidas em **arquivo CSV** (historico_forca.csv).

Grava as seguintes informações:

Nome do jogador;

Data da partida;

Tema;

Palavra secreta;

Resultado (VENCEU ou PERDEU).


**Métodos:**

**registrar(...):** adiciona registro no arquivo.

**lerTudo():** retorna lista com todas as linhas do histórico.



**8. JogoDaForca.java**

Controla todo o fluxo do jogo:

Cadastro de jogadores.

**Menu para escolher tema.**

Alternância de turnos entre jogadores.

Opção de tentar letra ou palavra completa.

Atualização do placar.

Persistência no histórico.


Ao final, exibe placar acumulado e histórico completo.



▶️ **Como Executar**

**1. Clone o repositório:**

git clone https://github.com/Santosdevbjj/jogoDaForca/edit/main/README.md

cd jogo-da-forca-java


**2. Compile o projeto:**

javac -d out src/**/*.java


**3. Execute a aplicação:**

java -cp out App



📘 **Exemplo de Execução no Console**

### Forca Multi-Jogadores ###
Quantos jogadores? 2
Nome do jogador 1: Ana
Nome do jogador 2: João

Temas disponíveis: [Animais, Cores, Países]
Escolha um tema: Animais

Turno: Ana
Palavra: _ _ _ _ _ _ _
Erradas: []
Erros: 0/6
Opções: (1) Letra  (2) Palavra => 1
Digite uma letra: A
Acertou!

...

=== Histórico ===
Jogador,Data,Tema,Palavra,Resultado
Ana,2025-07-28,Animais,CACHORRO,VENCEU
João,2025-07-28,Animais,CACHORRO,PERDEU

=== Placar Final ===
Ana: 1 ponto(s)
João: 0 ponto(s)



📚 **Recursos Técnicos**

**POO aplicada:** encapsulamento, abstração, separação de responsabilidades.

**Manipulação de arquivos:** leitura e escrita em CSV.

**Coleções:** uso de Map, List, Set.

**ASCII Art:** representação do boneco com base nos erros.



## Jogo da Forca - Entendendo como jogar

<img width="1080" height="1048" alt="Screenshot_20250729-194841" src="https://github.com/user-attachments/assets/1f1be0f8-abae-4806-a913-067523e08240" />



- Para brincar de Forca, vamos precisar de uma caneta e um papel.

  
- Copie o desenho da “forca” que se encontra na próxima página, ou, se preferir, imprima a página.

  
- O objetivo deste jogo é descobrir uma palavra adivinhando suas letras.

  
- Um dos jogadores deverá escolher uma palavra e escrever no papel, embaixo do desenho da 
forca, o número de tracinhos correspondente a cada letra dessa palavra.

- Os outros jogadores, um de cada vez, deve descobrir qual palavra é essa escolhendo uma letra 
do alfabeto por vez.

- Se errar deve-se desenhar uma parte do boneco na forca (cabeça, corpo, um braço, uma perna, 
etc) e também se escreve a letra errada ao lado da forca para marcá-la.

- Se acertar escreve-se a letra certa em cima do seu tracinho correspondente.
  
- Caso já tenham sido desenhadas todas as partes do corpo do boneco e nenhum jogador tenha 
descoberto a palavra, faz-se um traço no pescoço do boneco, indicando que ele foi “enforcado” e

o jogo termina sem um vencedor.
- Vence o jogo, o jogador que descobrir a palavra antes que o boneco seja “enforcado”.


O jogo da forca é um jogo em que o jogador tem que acertar qual é a palavra proposta, tendo como dica o número de letras e o tema ligado à palavra. 

A cada letra errada, é desenhado uma parte do corpo do enforcado. O jogo termina ou com o acerto da palavra ou com o término do preenchimento das partes corpóreas do enforcado. 






