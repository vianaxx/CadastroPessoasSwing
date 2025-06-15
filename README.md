# 🧑‍💼 Cadastro de Pessoas (Java + Swing)

Projeto desenvolvido para praticar os fundamentos da linguagem Java, programação orientada a objetos e persistência de dados com arquivos binários.

## 💡 Funcionalidades

- Cadastro de Aluno ou Professor
- Listagem em tabela com interface Swing
- Edição de registros ao clicar na tabela
- Remoção de pessoas por ID
- Persistência local dos dados (`data/banco.dat`)
- Interface gráfica simples e funcional

## 🛠️ Tecnologias

- Java 11+
- Swing (Java GUI)
- Serialização de objetos com `ObjectOutputStream`
- MVC simples (model, view, controller separados)

## 🎓 Estrutura de Classes

- `Pessoa` (classe abstrata)
  - `Aluno`
  - `Professor`
- `PessoaController`: controla cadastro, edição, remoção
- `TelaSwing`: interface gráfica principal
- `banco.dat`: arquivo binário onde os dados são salvos

## 📝 Licença

Este projeto é para fins educacionais.





