---
# 🧑‍💼 Cadastro de Pessoas em Java com Swing

Este projeto foi desenvolvido como prática dos conceitos de Java, Programação Orientada a Objetos (POO) e persistência com arquivos. Ele possui uma interface gráfica feita com **Swing**, onde você pode cadastrar, editar, listar e excluir **Alunos** e **Professores**.

---

## 🎯 Funcionalidades

- ✅ Cadastro de alunos e professores com nome, idade e curso/disciplina  
- ✅ Edição de dados clicando diretamente na tabela  
- ✅ Exclusão por ID (via campo de texto)  
- ✅ Listagem automática de todos os registros  
- ✅ Persistência com arquivos binários (`data/banco.dat`)  
- ✅ Geração automática de IDs únicos

---

## 🖥️ Como usar o programa

### 📦 Requisitos

- Java 11 ou superior  
- IntelliJ IDEA, VSCode ou terminal com suporte a compilação Java

---

### ▶️ Executando via IDE

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/seu-usuario/CadastroPessoasSwing.git

2. **Abra o projeto na sua IDE** (ex: IntelliJ IDEA)

3. Localize a classe `view.TelaSwing` e execute o método `main`.

---

## 📘 Como usar a interface

### 1. **Selecionar o tipo de pessoa**

Na parte superior, escolha **"Aluno"** ou **"Professor"** no menu suspenso.

### 2. **Preencher os campos**

* Nome
* Idade
* Curso (para Aluno) ou Disciplina (para Professor)

### 3. **Cadastrar**

Clique em **"Cadastrar"**. A pessoa será adicionada à tabela e salva no arquivo automaticamente.

### 4. **Editar**

1. Clique em uma linha da tabela para carregar os dados no formulário.
2. Altere as informações desejadas.
3. Clique em **"Atualizar"**.

### 5. **Remover por ID**

1. Pegue o **ID da pessoa** (coluna mais à esquerda da tabela).
2. Digite esse número no campo **"ID para remover"**.
3. Clique em **"Remover por ID"**.

> As alterações são persistidas automaticamente no arquivo `data/banco.dat`.

---

## 🗃️ Estrutura do projeto

```
CadastroPessoasSwing/
├── src/
│   ├── model/           → Classes: Pessoa, Aluno, Professor
│   ├── controller/      → PessoaController.java (lógica)
│   └── view/            → TelaSwing.java (interface gráfica)
├── data/                → banco.dat (salvamento automático)
├── README.md
```
---

## 📚 Conceitos praticados

* Programação Orientada a Objetos (POO)
* Herança e polimorfismo
* Encapsulamento com getters e setters
* Interface gráfica com Swing
* Persistência com arquivos binários (ObjectOutputStream)
* Separação em camadas (MVC básico)

---

## 📝 Licença

Este projeto é para fins educacionais.

---


