# 📚 Cantinho da Leitura

O **Cantinho da Leitura** é um projeto em **Java** desenvolvido para simular o gerenciamento de uma biblioteca via console. A aplicação permite cadastrar livros e usuários, registrar empréstimos, controlar devoluções e consultar o acervo sem depender de banco de dados nem de interface gráfica.

O projeto foi organizado com foco em **POO**, **camadas de responsabilidade** e **manipulação de coleções em memória**, sendo uma solução adequada para praticar **modelagem de domínio**, **serviços**, **interação com usuário** e **estruturas lineares**.

---

## 1. 🧭 Justificativa e contexto do projeto

Este projeto foi desenvolvido como requisito da disciplina de **Abstração em Estrutura de Dados**. A ideia era resolver uma situação real de biblioteca por meio da modelagem de entidades, das operações de negócio e do uso adequado de estruturas de dados em memória.

A solução foi pensada em duas etapas principais:

1. Identificar o problema, as entidades envolvidas e as operações necessárias.
2. Implementar um sistema com lógica de negócio, organização por camadas e testes das operações fundamentais.

---

## 2. ✨ Funcionalidades principais do sistema

O **Cantinho da Leitura** permite:

- 📖 Cadastro de livros com ISBN, título e autor
- 👤 Cadastro de usuários com nome e CPF
- 🔍 Busca por livro e usuário
- 📋 Listagem de livros e usuários cadastrados
- 📦 Empréstimo de livro com validação de disponibilidade
- 🔄 Devolução de livro com atualização de status
- 🕑 Registro de histórico de empréstimos ativos e encerrados
- ⚡ Popularização automática de dados iniciais para testes

---

## 3. 🏗️ Arquitetura do projeto

A aplicação foi organizada em camadas para facilitar manutenção e entendimento do código:

- ▶️ **app.Main**: ponto de entrada do sistema
- 📚 **service.LivroService**: lógica de cadastro, busca e listagem de livros
- 👥 **service.UsuarioService**: lógica de cadastro, busca e listagem de usuários
- 🔗 **service.BibliotecaService**: operações de empréstimo e devolução
- 📖 **model.LivroModel**: representa um livro do acervo
- 👤 **model.UsuarioModel**: representa um usuário da biblioteca
- 📑 **model.EmprestimoModel**: registra os dados de um empréstimo
- 💻 **ui.***: interação com o usuário no console
- 📂 **data.Dados**: carrega dados iniciais para simulação

Essa separação permite que a regra de negócio fique isolada das classes de modelo e da interface de console.

---

## 4. 🧱 Entidades do sistema

- **LivroModel** → ISBN, título, autor e status de disponibilidade
- **UsuarioModel** → nome, CPF e livro atualmente emprestado
- **EmprestimoModel** → usuário, livro, data de empréstimo, data prevista de devolução e status do empréstimo

---

## 5. ✅ Regras de negócio

As regras do sistema foram implementadas para garantir consistência no processo de empréstimo:

- 🚫 Não permite CPF duplicado
- 🚫 Não permite ISBN duplicado
- 🚫 Não permite empréstimo de livro indisponível
- 🚫 Não permite que usuário com empréstimo pendente pegue outro livro
- ✅ Atualiza status de livro e usuário em conjunto
- ✅ Registra devolução e encerra o empréstimo ativo

---

## 6. 📊 Estruturas de dados utilizadas

O projeto adota **estruturas lineares**, como `ArrayList` e `Array`, atendendo à restrição da disciplina de não usar árvores nem tabelas hash nesta etapa.

### 6.1 Visão geral técnica

- **ArrayList**: usado para armazenar e manipular listas dinâmicas de livros, usuários e empréstimos.
- **Array**: utilizado em etapas iniciais para popular dados em memória.

### 6.2 Complexidade das operações

| Operação | Array | ArrayList | Observação |
| --- | ---: | ---: | --- |
| Inserção no final | O(1) | O(1) amortizado | ArrayList redimensiona automaticamente |
| Inserção no início | O(n) | O(n) | deslocamento de elementos necessário |
| Acesso por índice | O(1) | O(1) | acesso direto por índice |
| Busca sequencial por valor | O(n) | O(n) | mesma complexidade linear |
| Remoção por valor | O(n) | O(n) | localizar + deslocar elementos |
| Iteração completa | O(n) | O(n) | eficiente para listagens |
| Crescimento dinâmico | Não automático | Sim | ArrayList gerencia redimensionamento |

### 6.3 Aplicação no sistema

- **Acervo de livros**: armazenamento centralizado de todas as entradas de livros.
- **Cadastro de usuários**: gerenciamento dos usuários cadastrados no sistema.
- **Registros de empréstimos**: histórico cronológico e controle de empréstimos ativos e encerrados.

---

## 7. 📁 Estrutura de diretórios

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

## 8. 🛠️ Tecnologias utilizadas

- ☕ Java 17+
- 🧱 Programação orientada a objetos
- 🔒 Encapsulamento e modelagem de domínio
- 📋 Coleções em memória com `ArrayList`
- 🔍 Busca e filtragem com streams
- 🖥️ Interface de console para interação com o usuário

---

## 9. ⚙️ Pré-requisitos

- ☕ JDK 17 ou superior
- 💻 IDE ou terminal compatível (IntelliJ, Eclipse, VS Code)
- 🌐 Git para versionamento

---

## 10. ▶️ Como executar o projeto

1. Abra o projeto em sua IDE favorita.
2. Compile e execute a classe principal em `src/app/Main.java`.
3. Navegue pelo menu no console para:
   - cadastrar livros;
   - cadastrar usuários;
   - emprestar e devolver livros;
   - visualizar o acervo e o histórico.

---

## 11. 🧪 Testes e validação

O projeto inclui testes manuais na pasta `src/test`, realizados sem uso de JUnit, com validações diretamente em Java para confirmar regras de negócio como:

- cadastro de livros duplicados;
- cadastro de usuários duplicados;
- busca por ISBN e por nome;
- empréstimo de livro disponível;
- tentativa de empréstimo com usuário ou livro inválido;
- devolução correta e atualização de disponibilidade.

---

## 12. 🚀 Próximos passos

- 🌳 Implementar estruturas de dados não lineares, como árvores e tabelas hash
- ⚡ Otimizar operações de busca e listagem
- 📊 Analisar complexidade de tempo e espaço com mais profundidade
- 🎨 Melhorar a interface do usuário
- 💾 Adicionar persistência em arquivo ou banco de dados
- 🧪 Expandir a suíte de testes para mais cenários de borda

---

## 13. 🎯 Conclusão

O uso de **estruturas lineares** como `ArrayList` torna o sistema **didático, simples de entender e funcional para cenários acadêmicos e de média escala**. Isso favorece o aprendizado de POO, organização de código e manipulação de estruturas de dados em memória.

Com o tempo, as próximas evoluções podem incluir **estruturas mais eficientes** e **persistência de dados**, ampliando a robustez e a escalabilidade do sistema.

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
