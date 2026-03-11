![NTT-Java](https://github.com/user-attachments/assets/149e1032-79be-4f8a-8fb0-2019216b05e3)

# 🪢 Jogo da Forca em Java — Console Application

> **Bootcamp NTT DATA — Java e IA para Iniciantes**

---

## 1. 🧩 Problema de Negócio

Projetos de console são frequentemente descartados em portfólios com a justificativa de que "são simples demais para impressionar". Essa visão ignora algo fundamental: **a complexidade não está no que o projeto faz, mas em como foi construído**.

Um jogo da forca mal implementado é um arquivo `main` com centenas de linhas, `if/else` aninhados, variáveis globais e nenhuma separação de responsabilidades. Funciona — mas não escala, não é testável e não demonstra maturidade de código.

> **O desafio real deste projeto:** construir um jogo da forca que qualquer desenvolvedor lendo o código consiga entender, modificar e estender — sem quebrar o que já funciona. Um projeto simples, escrito como engenheiro de software pensa.

---

## 2. 📌 Contexto

O projeto foi desenvolvido como parte do Bootcamp NTT DATA — Java e IA para Iniciantes, com foco em aplicar **Programação Orientada a Objetos na prática**, não apenas na teoria.

O Jogo da Forca foi escolhido porque o domínio é conhecido por todos — qualquer pessoa consegue jogar e validar o comportamento — mas implementá-lo com qualidade exige resolver problemas reais de design:

- Como separar o estado da palavra secreta da lógica do jogo?
- Como representar os erros sem misturar apresentação com regra de negócio?
- Como persistir o histórico de partidas sem travar a lógica principal?
- Como suportar múltiplos jogadores sem transformar o código em um emaranhado de condicionais?

A aplicação resultante suporta **modo multi-jogador**, **temas de palavras**, **pontuação acumulada**, **tentativa de palavra completa** e **histórico de partidas persistido em CSV** — tudo isso com cada responsabilidade isolada em sua própria classe.

---

## 3. 📐 Premissas da Solução

As seguintes premissas guiaram cada decisão de design ao longo do desenvolvimento:

- **Uma classe, uma responsabilidade** — `Palavra` gerencia o estado da palavra, `Forca` gerencia os erros e o boneco ASCII, `Historico` cuida da persistência. Nenhuma delas sabe o que a outra faz internamente;
- **Exceções de domínio têm nome próprio** — `PalavraInvalidaException` existe porque "palavra inválida" é um conceito do jogo, não um erro genérico de sistema;
- **O histórico não bloqueia o jogo** — a gravação em CSV não está acoplada ao fluxo principal. Se o arquivo falhar, o jogo continua;
- **O boneco ASCII é calculado, não hard-coded** — `Forca.getBonecoAscii()` retorna a representação correta com base no número atual de erros, sem uma string gigante com todos os estados;
- **Nenhum `System.out.println` dentro das classes de domínio** — apresentação é responsabilidade de `JogoDaForca`, as classes de modelo apenas retornam dados.

---

## 4. ⚙️ Estratégia da Solução

A construção seguiu uma abordagem de **domínio primeiro, interface depois** — o mesmo princípio usado em sistemas enterprise, aplicado a um jogo de console.

**Passo 1 — Modelagem do domínio**
Identificação das entidades do jogo como classes com responsabilidades claras: `Palavra` (estado da palavra secreta), `Forca` (controle de erros e representação visual), `Jogador` (identidade e pontuação), `Dicionario` (fonte de palavras por tema).

**Passo 2 — Exceção personalizada**
Criação de `PalavraInvalidaException` para proteger o construtor de `Palavra` contra entradas inválidas (nulas ou vazias) — programação defensiva aplicada desde a camada de modelo.

**Passo 3 — Persistência desacoplada**
`Historico` encapsula toda a lógica de leitura e escrita em CSV. `JogoDaForca` apenas chama `historico.registrar(...)` — sem saber nada sobre o formato do arquivo ou onde ele é salvo.

**Passo 4 — Orquestrador de fluxo**
`JogoDaForca` atua como orquestrador: cadastra jogadores, gerencia turnos, processa tentativas (letra ou palavra completa) e coordena a atualização do placar. É a única classe com acesso ao console.

**Passo 5 — Multi-jogador com alternância de turnos**
Implementado com uma lista de `Jogador` e controle de índice — sem duplicação de lógica para cada jogador. Adicionar um terceiro ou quarto jogador não exige nenhuma mudança no código.

---

## 5. 💡 Insights Técnicos

Os aprendizados mais valiosos vieram das decisões que pareciam triviais mas tinham consequências diretas na qualidade do código:

- **`Set<Character>` para letras erradas, não `List`.** A primeira implementação usou `List` — e exigia `contains()` com complexidade linear. Trocar para `Set` foi uma mudança de uma linha que melhorou a semântica e a performance ao mesmo tempo.

- **O boneco ASCII como função do estado, não como constante.** A tentação inicial era ter um array com 7 strings pré-montadas (uma para cada nível de erro). A solução final calcula o boneco dinamicamente — o que permite personalizar partes sem reescrever todas as representações.

- **`PalavraInvalidaException` revelou um bug de design.** Ao criar a exceção, ficou claro que havia dois lugares no código que criavam `Palavra` sem validar a entrada. A exceção forçou a centralizar a validação no construtor — e eliminou código defensivo duplicado.

- **Persistência em CSV tem armadilha de encoding.** Na primeira execução em sistemas Windows, caracteres especiais do português (ã, ç, é) apareciam corrompidos no arquivo. A solução foi especificar `StandardCharsets.UTF_8` explicitamente no `FileWriter` — um detalhe que não aparece em nenhum tutorial básico de escrita em arquivo.

- **A separação entre `JogoDaForca` e as classes de modelo** tornou fácil testar as regras de negócio isoladamente. `Palavra.tentarLetra('A')` pode ser chamada em um teste sem precisar simular console, arquivo ou jogadores — porque a classe não depende de nada externo.

---

## 6. 📊 Resultados

| Funcionalidade | Status |
|---|---|
| Escolha de tema (Animais, Cores, Países) | ✅ |
| Modo multi-jogador com alternância de turnos | ✅ |
| Tentativa de letra ou palavra completa | ✅ |
| Boneco ASCII progressivo por número de erros | ✅ |
| Pontuação acumulada por jogador | ✅ |
| Histórico de partidas salvo em CSV | ✅ |
| Exceção personalizada para palavra inválida | ✅ |
| Placar final com ranking de jogadores | ✅ |

**Diagrama UML das classes:**

<img width="1024" height="1536" alt="DiagramaUMLForca" src="https://github.com/user-attachments/assets/6690eb11-f89f-4b49-b26b-9c71333f2161" />

**Arquitetura de classes e responsabilidades:**

| Classe | Responsabilidade |
|---|---|
| `App` | Ponto de entrada — instancia e inicia `JogoDaForca` |
| `Palavra` | Estado da palavra secreta — letras descobertas e ocultas |
| `Forca` | Controle de erros e geração do boneco ASCII |
| `Jogador` | Identidade e pontuação acumulada de cada participante |
| `Dicionario` | Repositório de palavras por tema com seleção aleatória |
| `Historico` | Leitura e escrita de partidas em arquivo CSV |
| `JogoDaForca` | Orquestrador — fluxo completo do jogo e interface com console |
| `PalavraInvalidaException` | Exceção de domínio para entradas inválidas |

**Exemplo real de execução:**

```
### Forca Multi-Jogadores ###
Quantos jogadores? 2
Nome do jogador 1: Ana
Nome do jogador 2: João

Temas disponíveis: [Animais, Cores, Países]
Escolha um tema: Animais

Turno: Ana
Palavra: _ _ _ _ _ _ _
Erradas: []  |  Erros: 0/6
Opções: (1) Letra  (2) Palavra => 1
Digite uma letra: A
Acertou! ✅

=== Histórico ===
Jogador,Data,Tema,Palavra,Resultado
Ana,2025-07-28,Animais,CACHORRO,VENCEU
João,2025-07-28,Animais,CACHORRO,PERDEU

=== Placar Final ===
Ana: 1 ponto(s)
João: 0 ponto(s)
```

---

## 7. 🚀 Próximos Passos

A base de design bem estruturada torna a evolução do projeto direta:

- [ ] **Interface gráfica com JavaFX** — a separação entre domínio e console já está feita; basta substituir o orquestrador de console por um controller JavaFX sem tocar nas classes de modelo;
- [ ] **Banco de dados para o histórico** — substituir o CSV por SQLite ou H2 com JDBC, mantendo a interface de `Historico` intacta para o restante do sistema;
- [ ] **Dicionário externo via API** — consumir palavras de uma API de dicionário (ex.: Datamuse ou DicAPI) para eliminar as listas hard-coded;
- [ ] **Ranking persistido entre sessões** — salvar e carregar o placar de execuções anteriores, transformando o histórico em um sistema de recordes;
- [ ] **Testes unitários com JUnit 5** — `Palavra`, `Forca` e `Dicionario` são facilmente testáveis sem dependências externas; a estrutura atual já viabiliza isso sem refatoração;
- [ ] **Modo de dificuldade** — limitar o número de tentativas (fácil: 8, normal: 6, difícil: 4) configurável no início de cada partida.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Função no projeto |
|---|---|
| Java 17+ | Linguagem principal |
| POO — Encapsulamento | Proteção do estado de `Palavra` e `Forca` |
| POO — Separação de responsabilidades | Uma classe por conceito do domínio |
| `Set<Character>` | Letras erradas sem duplicatas e busca O(1) |
| `Map<String, List<String>>` | Dicionário de palavras por tema |
| Exceção personalizada | `PalavraInvalidaException` para validação de domínio |
| `FileWriter` + `BufferedReader` | Persistência do histórico em CSV com UTF-8 |
| ASCII Art | Boneco da forca calculado por número de erros |

---

## 🔧 Como Executar

**Pré-requisito:** Java 17+ instalado.

**1. Clone o repositório**
```bash
git clone https://github.com/Santosdevbjj/jogoDaForca.git
cd jogoDaForca
```

**2. Compile o projeto**
```bash
javac -d out src/**/*.java
```

**3. Execute o jogo**
```bash
java -cp out App
```

> O arquivo `historico_forca.csv` será criado automaticamente na raiz do projeto após a primeira partida.

---

## 📚 Aprendizados

Este projeto me ensinou algo que nenhum tutorial sobre POO consegue transmitir diretamente: **a separação de responsabilidades não é uma regra de estilo — é o que torna o código modificável**.

Quando precisei adicionar o modo multi-jogador, não toquei em `Palavra`, `Forca` nem `Dicionario`. Adicionei `Jogador`, ajustei o orquestrador em `JogoDaForca` e o jogo multi-jogador funcionou. Isso só foi possível porque as classes de domínio não sabiam nada sobre o fluxo do jogo.

O maior erro da primeira versão foi misturar `System.out.println` dentro da classe `Palavra`. Parecia inofensivo — mas tornou impossível reutilizar a classe em qualquer contexto diferente do console. Remover essa dependência foi a refatoração mais impactante do projeto inteiro.

Se fosse recomeçar, começaria desenhando o UML antes de escrever a primeira linha de código. Ter o diagrama de classes na mão antes de implementar evitaria as duas refatorações de design que precisei fazer no meio do desenvolvimento.

---

## 👤 Autor

**Sérgio Santos**



[![Portfólio](https://img.shields.io/badge/Portfólio-Sérgio_Santos-111827?style=for-the-badge&logo=githubpages&logoColor=00eaff)](https://portfoliosantossergio.vercel.app)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Sérgio_Santos-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/santossergioluiz)

---

> *"O mercado de trabalho não contrata quem usa ferramentas — contrata quem resolve problemas com elas."*
> — Meigarom Lopes
