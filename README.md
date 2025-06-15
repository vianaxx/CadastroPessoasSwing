# 🧑‍💼 Cadastro de Pessoas em Java com Swing

Este projeto foi desenvolvido como prática dos conceitos de Java, Programação Orientada a Objetos (POO) e persistência com arquivos. Ele possui uma interface gráfica feita com **Swing**, onde você pode cadastrar, editar, listar e excluir **Alunos** e **Professores**.

---

## 🎯 Funcionalidades

- ✅ Cadastro de alunos e professores com nome, idade e curso/disciplina
- ✅ Edição de dados clicando diretamente na tabela
- ✅ Exclusão por ID
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

   git clone http://github.com/vianaxx/CadastroPessoasSwing.git

2. **Abra o projeto na sua IDE** (ex: IntelliJ IDEA)

3. Localize a classe `view.TelaSwing` e execute o método `main`.

---

## 🧭 Guia Rápido da Interface

A tela inicial exibe uma tabela com todos os cadastros já existentes (se houver).

### ➕ Cadastrar nova pessoa

1. Selecione o tipo da pessoa no campo "Tipo" (Aluno ou Professor)
2. Preencha os campos:

   * Nome
   * Idade
   * Curso (se Aluno) ou Disciplina (se Professor)
3. Clique em **"Cadastrar"**
4. A tabela será atualizada automaticamente

### ✏️ Editar um registro existente

1. Clique sobre uma linha da tabela
2. Os dados serão carregados nos campos
3. Faça as alterações desejadas
4. Clique em **"Atualizar"**

### 🗑️ Remover por ID

1. Digite o **ID** no campo correspondente (parte inferior do formulário)
2. Clique em **"Remover por ID"**
3. O registro será removido da tabela e do arquivo

> 💾 As alterações são salvas automaticamente no arquivo `data/banco.dat`

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

Este projeto é livre para fins educacionais.
