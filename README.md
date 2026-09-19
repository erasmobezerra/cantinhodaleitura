# 📚 Cantinho da Leitura

O **Cantinho da Leitura** é um projeto em **Java** desenvolvido para simular o gerenciamento de uma biblioteca via console.  
A aplicação permite cadastrar livros e usuários, registrar empréstimos, controlar devoluções e consultar informações do acervo sem depender de banco de dados ou interface gráfica. O projeto foi estruturado com foco em **POO**, organização por camadas e separação de responsabilidades, sendo uma solução adequada para praticar **modelagem de domínio, serviços, interação com usuário e manipulação de coleções em memória**.

## 1. Justificativa do projeto

Este projeto foi desenvolvido como requisito da disciplina Abstração em Estrutura de Dados. Precisávamos resolver uma situação problema do mundo real por meio da implementação e do uso justificado de estruturas de dados.

Nesta primeira etapa, foram resolvidos dois objetivos: descriver a situação problema, identificar as entidades envolvidas e especificar os tipos abstratos de dados necessários, com suas interfaces e operações. Em Seguida, realizar a implementação das estruturas lineares com gerenciamento adequado de memória e testes de cada operação.

## ✨ 2. Funcionalidades

- 📖 Cadastro de livros com ISBN, título e autor  
- 👤 Cadastro de usuários com nome e CPF  
- 🔍 Busca por livro e usuário  
- 📋 Listagem de livros e usuários cadastrados  
- 📦 Empréstimo de livro com validação de disponibilidade  
- 🔄 Devolução de livro com atualização de status  
- 🕑 Registro de histórico de empréstimos ativos e encerrados  
- ⚡ Popularização automática de dados iniciais para testes  

## 🧮 3. Estruturas Abstratas de Dados, Interfaces e Operações

O projeto adota **estruturas de dados lineares** (`ArrayList e Array`), atendendo à restrição de não utilizar árvores nem tabelas hash nesta primeira etapa.

O ArrayList é uma estrutura de dados baseada em um array dinâmico que redimensiona seu tamanho automaticamente conforme adiciona ou remove elementos. A principal vantagem é quando a prioridade é buscar e ler dados rapidamente por índice com uma complexidade de tempo constante O(1) e quando a quantidade de elementos mudar com frequência ao longo do programa. A principal desvantagem do ArrayList em Java é a baixa performance para inserções ou remoções de elementos no meio ou no início da lista que possui uma complexidade O(1) em média, pois essa operação exige o deslocamento manual de todos os elementos seguintes na memória.

Vetores (arrays) são estruturas de dados lineares e estáticas que armazenam uma coleção de elementos do mesmo tipo de forma sequencial na memória. Sua principal vantagem é o acesso instantâneo a qualquer elemento através do seu índice numérico com complexidade de tempo constante O(1); por outro lado, sua maior desvantagem é possuir um tamanho fixo definido na criação, o que impede a estrutura de crescer ou diminuir dinamicamente e exige realocação de memória caso o limite de espaço seja atingido.

O sistema **Cantinho da Leitura** utiliza **arrays (vetores)** apenas na inicialização para popular os dados em memória e, a partir daí, opera principalmente com **`ArrayList`** (implementação da interface `List`) para buscas, listagens, empréstimos, devoluções e cadastros. Essa escolha atende à restrição de usar apenas **estruturas lineares** nesta fase da disciplina e privilegia clareza didática, facilidade de implementação e manutenção.

### ⚖️ Visão geral técnica

- **Motivação:** `ArrayList` combina simplicidade de uso com uma API rica (`List`, `Collections`, `streams`) que facilita filtros, ordenações e integração com a lógica de negócio.  
- **Contexto:** para um sistema pequeno e acadêmico, buscas lineares e operações de listagem são aceitáveis; porém, é importante documentar limitações e caminhos de evolução caso o volume cresça.

---

### 📊 Complexidade das operações (Array vs ArrayList)

| **Operação** | **Array (vetor)** | **ArrayList** | **Observação** |
|---|---:|---:|---|
| Inserção no final | O(1) | O(1) amortizado | ArrayList redimensiona automaticamente |
| Inserção no início | O(n) | O(n) | deslocamento de elementos necessário |
| Acesso por índice | O(1) | O(1) | acesso direto por índice |
| Busca sequencial por valor | O(n) | O(n) | mesma complexidade linear |
| Remoção por valor | O(n) | O(n) | localizar + deslocar elementos |
| Iteração completa | O(n) | O(n) | eficiente para listagens sequenciais |
| Crescimento dinâmico | Não automático | Sim | ArrayList gerencia redimensionamento |

---

### 📚 Acervo de Livros ArrayList

- **Uso:** armazenamento centralizado de todos os livros cadastrados.  
- **Vantagens:** **acesso indexado direto; listagens sequenciais eficientes; crescimento dinâmico; API rica para filtros e ordenações.**  
- **Desvantagens:** **busca por ISBN/título é O(n)**; remoções no meio da lista são O(n); redimensionamentos podem aumentar uso de memória temporariamente.  
- **Operações e complexidades:**  
  - `adicionarLivro` → O(1) amortizado  
  - `buscarLivroPorIsbn` → O(n)  
  - `buscarLivroPorTitulo` → O(n)  
  - `listarLivros` → O(n)

---

### 👥 Cadastro de Usuários ArrayList

- **Uso:** gerenciamento global de usuários.  
- **Vantagens:** **inserção simples e rápida; estrutura previsível para iterações; fácil integração com validações (ex.: CPF).**  
- **Desvantagens:** **validação de CPF único exige busca O(n)**; remoções e atualizações por atributo são O(n).  
- **Operações e complexidades:**  
  - `adicionarUsuario` → O(1) amortizado  
  - `buscarUsuarioPorNome` → O(n)  
  - `buscarUsuarioPorCpf` → O(n)  
  - `listarUsuarios` → O(n)

---

### 🔗 Registros de Empréstimos ArrayList

- **Uso:** histórico cronológico e controle de empréstimos ativos e encerrados.  
- **Vantagens:** **inserção no final O(1); preservação natural da ordem cronológica; facilidade para relatórios por período; filtragem com streams.**  
- **Desvantagens:** **buscar empréstimo ativo por usuário ou livro é O(n)**; histórico muito grande consome memória e torna listagens/backup mais pesadas.  
- **Operações e complexidades:**  
  - `emprestarLivro` → O(n) para validações (buscar usuário, livro, empréstimo ativo)  
  - `devolverLivro` → O(n) para localizar empréstimo ativo  
  - `listarEmprestimos` → O(n)  
  - `buscarEmprestimoAtivo` → O(n)

## 🗂️ Visão geral da arquitetura

A aplicação foi organizada em camadas para facilitar manutenção e entendimento:

- ▶️ **app.Main**: ponto de entrada do sistema  
- 📚 **service.LivroService**: lógica de cadastro, busca e listagem de livros  
- 👥 **service.UsuarioService**: cadastro, busca e listagem de usuários  
- 🔗 **service.BibliotecaService**: operações de empréstimo e devolução  
- 📖 **model.LivroModel**: representa um livro do acervo  
- 👤 **model.UsuarioModel**: representa um usuário da biblioteca  
- 📑 **model.EmprestimoModel**: registra os dados de um empréstimo  
- 💻 **ui.***: interação com o usuário no terminal  
- 📂 **data.Dados**: carrega dados iniciais para simulação  

## 📁 Estrutura do projeto

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
│   ├── ui/
│   │   ├── BibliotecaUI.java
│   │   ├── LivroUI.java
│   │   ├── MenuUI.java
│   │   └── UsuarioUI.java
│   └── test/
├── livros.csv
├── cantinhodaleitura.iml
├── README.md
└── .gitignore
```

---

## 🧩 Entidades do sistema

- 📖 **LivroModel** → ISBN, título, autor, status de disponibilidade  
- 👤 **UsuarioModel** → nome, CPF, livro emprestado  
- 🔗 **EmprestimoModel** → usuário, livro, datas de empréstimo/devolução, status ativo/inativo  

## 🧠 Regras de negócio

- 🚫 Não permite CPF duplicado  
- 🚫 Não permite ISBN duplicado  
- 🚫 Não permite empréstimo de livro indisponível  
- 🚫 Não permite que usuário com empréstimo pendente pegue outro livro  
- ✅ Atualiza status de livro e usuário em conjunto  

## 🛠️ Tecnologias utilizadas

- ☕ Java 17+  
- 🧱 Programação orientada a objetos  
- 🔒 Encapsulamento e modelagem de domínio  
- 📋 Coleções em memória com `ArrayList`  
- 🔍 Métodos de busca e filtragem com streams  
- 🖥️ Separação entre lógica de negócio, modelos e interface de console  

## ⚙️ Pré-requisitos

- ☕ Java JDK 17 ou superior  
- 💻 IDE ou terminal compatível (IntelliJ, Eclipse, VS Code)  
- 🌐 Git para versionamento  

## 📌 Próximos passos

- 🌳 Implementar estruturas de dados não lineares (árvores, hash tables, grafos)  
- ⚡ Otimizar operações de busca e listagem  
- 📊 Analisar complexidade de tempo e espaço  
- 🎨 Melhorar interface de usuário  
- 💾 Persistência em arquivos ou banco de dados  

## 🎯 Conclusão

O uso de **estruturas lineares** como `ArrayList` garante que o sistema seja **didático, eficiente para cenários moderados e fácil de expandir**. Entretanto, **se o acervo ou a base de usuários crescerem significativamente**, buscas lineares e remoções deslocadas se tornarão gargalos.

Nos próximos passos, a introdução de **estruturas não lineares** (árvores, tabelas hash, grafos) permitirá explorar **otimização de buscas** e **comparação de desempenho**, enriquecendo ainda mais o aprendizado.

## 🎓 Informações Acadêmicas

- **Instituição:** Centro Universitário Salesiano – UNISALES  
- **Polo:** Vitória/ES  
- **Disciplina:** Lógica Digital na Resolução de Problemas  
- **Professor Orientador:** Mestre Wesley Lucas Bred  

## 👥 Equipe

| Nome | Curso | E-mail |
| :--- | :--- | :--- |
| Andrew Minto Neves | Engenharia de Software - 2º Período | a.mintoneves@gmail.com |
| Erasmo Ribeiro Bezerra | Sistema de Informações - 2º Período | erasmo.ads.tech@gmail.com |
| Gabriela Conceição Viana | Engenharia de Software - 2º Período | gabivianac@gmail.com |

## 🤝 Como contribuir

1. 🌱 Crie uma branch descritiva (ex: `feature/novo-cadastro`)  
2. 📝 Faça commits pequenos e objetivos  
3. 📐 Mantenha organização e clareza no código  
4. 🔀 Abra um pull request explicando as mudanças  
