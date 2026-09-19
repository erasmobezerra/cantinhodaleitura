# 📚 Cantinho da Leitura

O **Cantinho da Leitura** é um projeto em **Java** desenvolvido para simular o gerenciamento de uma biblioteca via console.  
A aplicação permite cadastrar livros e usuários, registrar empréstimos, controlar devoluções e consultar informações do acervo sem depender de banco de dados ou interface gráfica. O projeto foi estruturado com foco em **POO**, organização por camadas e separação de responsabilidades, sendo uma solução adequada para praticar **modelagem de domínio, serviços, interação com usuário e manipulação de coleções em memória**.

## Justificativa do projeto

Este projeto foi desenvolvido como requisito da disciplina Abstração em Estrutura de Dados. Precisávamos resolver uma situação problema do mundo real por meio da implementação e do uso justificado de estruturas de dados.

Nesta primeira etapa, foram resolvidos dois objetivos: descriver a situação problema, identificar as entidades envolvidas e especificar os tipos abstratos de dados necessários, com suas interfaces e operações. Em Seguida, realizar a implementação das estruturas lineares com gerenciamento adequado de memória e testes de cada operação.

---

## 🧮 Estruturas Abstratas de Dados, Interfaces e Operações

O projeto adota **estruturas de dados lineares** (principalmente `ArrayList`), atendendo à restrição de não utilizar árvores nem tabelas hash nesta primeira etapa.  
Essa escolha foi feita para garantir **simplicidade de implementação**, **facilidade de manutenção** e boa aderência ao contexto de uma biblioteca de pequeno e médio porte.

### ⚖️ Vantagens da abordagem

- ✅ Implementação simples e clara  
- 🔧 Fácil manutenção e evolução  
- 📊 Desempenho satisfatório para volumes moderados de dados  
- 📚 Boa adequação ao cenário acadêmico e didático  

---

### 📈 Análise de complexidade das operações principais

Considerando o uso de `ArrayList`, temos:

- ➕ Inserção no final → **O(1)** em média  
- 🔍 Busca sequencial por valor → **O(n)**  
- 📌 Acesso por índice → **O(1)**  
- 📋 Listagem completa → **O(n)**  
- ❌ Remoção por valor → **O(n)** (precisa localizar antes de remover)  
- 🔄 Iteração sobre elementos → **O(n)**  

👉 Para acervos de tamanho moderado, o custo de busca linear é aceitável e mantém o código mais legível.

---

### 📚 Acervo de Livros (ArrayList)

- **Uso:** armazenamento centralizado de todos os livros cadastrados  
- **Vantagens:** acesso indexado direto, eficiência em listagens sequenciais  
- **Operações:**  
  - `adicionarLivro`  
  - `buscarLivroPorIsbn`  
  - `buscarLivroPorTitulo`  
  - `listarLivros`  

---

### 👥 Cadastro de Usuários (ArrayList)

- **Uso:** gerenciamento global de usuários  
- **Vantagens:** crescimento simples, consultas previsíveis  
- **Operações:**  
  - `adicionarUsuario`  
  - `buscarUsuarioPorNome`  
  - `buscarUsuarioPorCpf`  
  - `listarUsuarios`  

---

### 🔗 Registros de Empréstimos (ArrayList)

- **Uso:** manutenção do histórico e controle de empréstimos ativos/concluídos  
- **Vantagens:** inserção eficiente no final, preservação da ordem cronológica  
- **Operações:**  
  - `emprestarLivro`  
  - `devolverLivro`  
  - `listarEmprestimos`  
  - `buscarEmprestimoAtivo`  

---

## ✨ Funcionalidades

- 📖 Cadastro de livros com ISBN, título e autor  
- 👤 Cadastro de usuários com nome e CPF  
- 🔍 Busca por livro e usuário  
- 📋 Listagem de livros e usuários cadastrados  
- 📦 Empréstimo de livro com validação de disponibilidade  
- 🔄 Devolução de livro com atualização de status  
- 🕑 Registro de histórico de empréstimos ativos e encerrados  
- ⚡ Popularização automática de dados iniciais para testes  

---

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

---

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

---

## 🧠 Regras de negócio

- 🚫 Não permite CPF duplicado  
- 🚫 Não permite ISBN duplicado  
- 🚫 Não permite empréstimo de livro indisponível  
- 🚫 Não permite que usuário com empréstimo pendente pegue outro livro  
- ✅ Atualiza status de livro e usuário em conjunto  

---

## 🛠️ Tecnologias utilizadas

- ☕ Java 17+  
- 🧱 Programação orientada a objetos  
- 🔒 Encapsulamento e modelagem de domínio  
- 📋 Coleções em memória com `ArrayList`  
- 🔍 Métodos de busca e filtragem com streams  
- 🖥️ Separação entre lógica de negócio, modelos e interface de console  

---

## ⚙️ Pré-requisitos

- ☕ Java JDK 17 ou superior  
- 💻 IDE ou terminal compatível (IntelliJ, Eclipse, VS Code)  
- 🌐 Git para versionamento  

---

## 🤝 Como contribuir

1. 🌱 Crie uma branch descritiva (ex: `feature/novo-cadastro`)  
2. 📝 Faça commits pequenos e objetivos  
3. 📐 Mantenha organização e clareza no código  
4. 🔀 Abra um pull request explicando as mudanças  

---

## 📌 Próximos passos

- 🌳 Implementar estruturas de dados não lineares (árvores, hash tables, grafos)  
- ⚡ Otimizar operações de busca e listagem  
- 📊 Analisar complexidade de tempo e espaço  
- 🎨 Melhorar interface de usuário  
- 💾 Persistência em arquivos ou banco de dados  

---

## 🎯 Conclusão

O uso de **estruturas lineares** como `ArrayList` garante que o sistema seja **didático, eficiente para cenários moderados e fácil de expandir**.  
Nos próximos passos, a introdução de **estruturas não lineares** (árvores, tabelas hash, grafos) permitirá explorar **otimização de buscas** e **comparação de desempenho**, enriquecendo ainda mais o aprendizado.

---

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
