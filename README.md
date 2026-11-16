# 📚 Cantinho da Leitura

O **Cantinho da Leitura** é uma aplicação de console desenvolvida em **Java** para resolver uma situação-problema do mundo real: o gerenciamento de uma biblioteca. O sistema permite cadastrar e consultar livros e usuários, registrar empréstimos, controlar devoluções e acompanhar o histórico, utilizando estruturas de dados escolhidas de acordo com as necessidades da aplicação.

O projeto é desenvolvido em etapas para a disciplina de **Abstração em Estrutura de Dados**. Na primeira versão, foram realizadas as entregas 1 e 2: definição do problema e das abstrações, seguida da implementação e dos testes das estruturas lineares necessárias. A nova versão dará continuidade ao trabalho com as entregas 3 e 4: incorporação justificada de estruturas de dados não lineares e avaliação do custo e do desempenho das operações, com documentação e relatório final.

---

## 1. 🧭 Justificativa e contexto do projeto

Este projeto atende ao trabalho da disciplina de **Abstração em Estrutura de Dados**, cujo objetivo é entregar uma aplicação que resolva uma situação-problema do mundo real por meio da implementação e do uso justificado de estruturas de dados. No caso do Cantinho da Leitura, a situação-problema é organizar o acervo, os usuários e os empréstimos de uma biblioteca.

Na **primeira versão**, foram concluídas as duas primeiras entregas:

1. **Definição do problema e das abstrações (entrega 1):** descrição do problema, identificação das entidades e especificação dos TADs necessários, suas interfaces e operações.
2. **Implementação das estruturas lineares (entrega 2):** implementação das estruturas lineares utilizadas pela aplicação e testes das operações correspondentes.

Na **nova versão**, o trabalho avançará para as entregas 3 e 4:

3. **Implementação de estruturas não lineares (entrega 3):** incorporação de pelo menos uma estrutura hierárquica ou de dispersão — como árvore, heap, tabela hash ou grafo —, com justificativa baseada nos requisitos da aplicação.
4. **Avaliação, documentação e entrega (entrega 4):** análise da complexidade das operações, medições de desempenho com entradas de tamanhos crescentes, comparação entre alternativas de implementação e entrega do código documentado junto ao relatório final.

---

## 2. 🚀 Tarefas a executar na nova versão do sistema

- [ ] **Criar duas branchs:** uma branch contendo a versão inicial e a segunda branch para incluir as novas implementações no sistema, principalmente as estruturas de dados não lineares.

- [ ] **Implementar a interface `Map`:** utilizar `LinkedHashMap` para armazenar, consultar e verificar livros por ISBN e título, além de usuários por CPF.

- [ ] **Implementar a interface `Deque`:** utilizar `Deque<EmprestimoModel>`, com implementação `ArrayDeque`, para armazenar e listar os empréstimos em ordem cronológica decrescente (do mais recente para o mais antigo).

- [ ] **Adicionar novas opções de consulta de livros:** atualizar o menu para permitir buscas por autor(a) e título, possibilitando o retorno de um ou mais livros correspondentes. Implementar as adaptações necessárias na camada de serviço.

- [ ] **Aprimorar o fluxo de empréstimos:** ao selecionar a opção de empréstimo no menu, solicitar primeiro a consulta do livro (por ISBN, título ou autor(a)) e, em seguida, o CPF do usuário que deseja realizar o empréstimo.

- [ ] **Atualizar os testes manuais:** revisar e executar os cenários de teste, contemplando as novas funcionalidades e estruturas de dados implementadas.

- [ ] **Avaliação, documentação e entrega:** analisar a complexidade computacional das operações implementadas, realizar testes de desempenho com conjuntos de dados de tamanhos crescentes, comparar diferentes alternativas de implementação e entregar o código devidamente documentado, acompanhado do relatório final.

---

## 3. 📊 Estruturas de dados propostas

A tabela resume as estruturas planejadas para a próxima versão. `Map` e `Deque` são interfaces que representam TADs; `LinkedHashMap` e `ArrayDeque` são implementações disponíveis na biblioteca padrão do Java.

| Dados/operação | TAD | Implementação Java | Organização e uso |
| --- | --- | --- | --- |
| Livros — cadastro, busca e prevenção de ISBN duplicado | Mapa (`Map`) | `LinkedHashMap<String, LivroModel>` | Chave: ISBN normalizado. Busca e verificação de duplicidade em O(1) médio. Mantém a ordem de cadastro na iteração. |
| Livros — busca por título | Mapa (`Map`) | `LinkedHashMap<String, List<LivroModel>>` como índice auxiliar | Chave: título normalizado; valor: lista de livros com esse título. Permite títulos repetidos e busca exata em O(1) médio, mais o custo de percorrer os resultados encontrados. |
| Usuários — cadastro, busca e prevenção de CPF duplicado | Mapa (`Map`) | `LinkedHashMap<String, UsuarioModel>` | Chave: CPF normalizado. Busca e verificação de duplicidade em O(1) médio, mantendo a ordem de cadastro. |
| Empréstimos — armazenamento do histórico e listagem do mais recente ao mais antigo | Pilha (`Deque`) | `ArrayDeque<EmprestimoModel>` | Inserir cada novo empréstimo no topo com `push`. Percorrer a pilha sem remover os itens para exibir primeiro o mais recente. |
| Livros e usuários — exibição completa | Coleção de valores do mapa | `Map.values()` | Percorre os registros em O(n). Com `LinkedHashMap`, preserva a ordem de inserção. |

### Observações

- As buscas por ISBN, título e CPF devem normalizar as chaves de forma consistente. O índice de títulos deve mapear para uma lista, pois títulos podem se repetir.
- A pilha representa o histórico completo. A listagem não deve usar `pop()`, pois isso removeria os empréstimos.
- A busca por empréstimo ativo ainda pode percorrer o histórico em O(n); um índice adicional pode ser considerado se essa operação se tornar um gargalo.

---

## 4. ✨ Funcionalidades principais

O sistema oferece, pela interface de console, as seguintes operações:

- Cadastrar e consultar livros por ISBN e título.
- Cadastrar e consultar usuários por CPF e nome.
- Listar o acervo e os usuários cadastrados.
- Registrar empréstimos e devoluções, verificando a disponibilidade dos livros.
- Consultar o histórico de empréstimos, incluindo registros ativos e concluídos.
- Carregar dados iniciais para facilitar a demonstração do sistema.

---

## 5. 🏗️ Arquitetura do projeto

A aplicação separa a interação com o usuário, as regras de negócio, os modelos de domínio e a carga de dados iniciais:

- **`app.Main`**: inicializa os serviços, carrega os dados de exemplo e inicia o menu.
- **`service.LivroService`**: gerencia o cadastro, a busca e a listagem de livros.
- **`service.UsuarioService`**: gerencia o cadastro, a busca e a listagem de usuários.
- **`service.BibliotecaService`**: coordena empréstimos, devoluções e o histórico.
- **`model`**: contém os modelos `LivroModel`, `UsuarioModel` e `EmprestimoModel`.
- **`ui`**: contém as interfaces de console para o menu, livros, usuários e empréstimos.
- **`data.Dados`**: fornece dados iniciais para a execução demonstrativa.

Essa organização mantém as responsabilidades mais claras e facilita testar os serviços sem depender diretamente da interface de console.

---

## 6. 🧱 Entidades do sistema

- **`LivroModel`**: representa um livro por ISBN, título e autor, além de registrar se está disponível.
- **`UsuarioModel`**: representa um usuário por nome e CPF e mantém a referência ao livro atualmente emprestado, quando houver.
- **`EmprestimoModel`**: associa um usuário a um livro e registra as datas do empréstimo, da devolução prevista e da devolução efetiva, além do estado do empréstimo.

---

## 7. ✅ Regras de negócio

- Não permitir o cadastro de livros com ISBN duplicado.
- Não permitir o cadastro de usuários com CPF ou nome duplicado.
- Não realizar empréstimo de livro inexistente ou indisponível.
- Não permitir que um usuário com empréstimo pendente faça outro empréstimo.
- Ao registrar um empréstimo ou devolução, atualizar o estado do livro e do usuário.
- Manter o registro do empréstimo no histórico após a devolução.

---

## 8. 📁 Estrutura de diretórios

```text
cantinhodaleitura/
├── bin/
├── src/
│   ├── app/
│   │   └── Main.java
│   ├── data/
│   │   └── Dados.java
│   ├── model/
│   │   ├── EmprestimoModel.java
│   │   ├── LivroModel.java
│   │   └── UsuarioModel.java
│   ├── service/
│   │   ├── BibliotecaService.java
│   │   ├── LivroService.java
│   │   └── UsuarioService.java
│   ├── test/
│   │   ├── BibliotecaServiceTest.java
│   │   ├── LivroServiceTest.java
│   │   ├── ManualTestRunner.java
│   │   ├── TestUtils.java
│   │   └── UsuarioServiceTest.java
│   ├── ui/
│   │   ├── BibliotecaUI.java
│   │   ├── LivroUI.java
│   │   ├── MenuUI.java
│   │   └── UsuarioUI.java
├── livros.csv
├── cantinhodaleitura.iml
├── README.md
├── LICENSE
└── .gitignore
```

---

## 9. 🛠️ Tecnologias utilizadas

- **Java**: linguagem de programação da aplicação.
- **Java Collections Framework**: coleções em memória, como `ArrayList`; as estruturas indicadas para a próxima versão incluem `LinkedHashMap` e `ArrayDeque`.
- **Streams**: operações de filtragem usadas nas buscas da implementação atual.
- **Git e GitHub**: controle de versão e hospedagem do código-fonte.

---

## 10. ⚙️ Pré-requisitos

- JDK compatível com a versão de Java utilizada pelo projeto.
- IDE Java ou terminal com ferramentas de compilação e execução.
- Git para clonar o repositório e acompanhar as versões do projeto.

---

## 11. ▶️ Como executar o projeto

1. Abra ou importe o projeto em uma IDE Java.
2. Compile o código-fonte usando o JDK configurado para o projeto.
3. Execute a classe `app.Main`, localizada em `src/app/Main.java`.
4. Use as opções apresentadas no console para cadastrar e consultar livros e usuários ou registrar empréstimos e devoluções.

---

## 12. 🧪 Testes e validação

O projeto inclui testes manuais em `src/test`, sem dependência do JUnit. Eles podem ser executados pelo `ManualTestRunner` e verificam cenários como:

- cadastro de livros e rejeição de ISBN duplicado;
- cadastro de usuários e rejeição de CPF ou nome duplicado;
- buscas por ISBN, título, CPF e nome;
- empréstimo de livro disponível e rejeição de operações inválidas;
- devolução de livro e atualização da disponibilidade;
- preservação do registro no histórico após a devolução.

---

## 13. 🎯 Conclusão

O projeto demonstra como a escolha de estruturas de dados afeta as operações de uma aplicação. A implementação atual utiliza coleções em memória e oferece uma base funcional para o gerenciamento da biblioteca. Como evolução planejada, mapas podem acelerar buscas por chaves como ISBN e CPF, enquanto uma pilha pode apresentar o histórico de empréstimos do mais recente ao mais antigo. Essas mudanças devem preservar as regras de negócio e ser acompanhadas por testes.

---

## 14. 🎓 Informações acadêmicas

- **Instituição:** Centro Universitário Salesiano – UNISALES
- **Polo:** Vitória/ES
- **Disciplina:** Abstração em Estrutura de Dados
- **Professor Orientador:** Lucas Daniel Barboza

---

## 15. 👥 Equipe

| Nome | Curso | E-mail |
| :--- | :--- | :--- |
| Andrew Minto Neves | Engenharia de Software - 2º Período | <a.mintoneves@gmail.com> |
| Erasmo Ribeiro Bezerra | Sistema de Informações - 2º Período | <erasmo.ads.tech@gmail.com> |
| Gabriel de Jesus Santana Serri | Engenharia de Software - 2º Período | <gabriel.jesus@souunisales.com.br> | 

## 16. 🤝 Como contribuir

1. 🌱 Crie uma branch descritiva (exemplo: `feature/novo-cadastro`)
2. 📝 Faça commits pequenos e objetivos
3. 📐 Mantenha organização e clareza no código
4. 🔀 Abra um pull request explicando as mudanças realizadas

---

Obrigado por acompanhar o projeto **Cantinho da Leitura**!
