# Displine

## 📚 Sobre o Projeto

O **Displine** é uma plataforma acadêmica desenvolvida como continuação do **Ideathon**, com o objetivo de promover maior integração entre alunos e professores, além de auxiliar na organização da vida acadêmica dos estudantes.

A proposta do sistema surgiu da necessidade de centralizar informações importantes da faculdade em um único ambiente, permitindo que os alunos acompanhem compromissos, eventos e prazos acadêmicos, reduzindo o risco de perder datas relevantes. Além disso, o Displine oferece um espaço de interação por meio de fóruns, onde professores podem compartilhar avisos, criar enquetes e estimular a participação da comunidade acadêmica.

Atualmente, o projeto encontra-se em fase de desenvolvimento e possui uma versão **MVP (Minimum Viable Product)** com funcionalidades essenciais já implementadas.

---

## 🎯 Objetivos

* Facilitar a organização acadêmica dos estudantes.
* Melhorar a comunicação entre alunos e professores.
* Centralizar informações importantes da instituição.
* Promover maior engajamento da comunidade acadêmica.
* Disponibilizar um ambiente colaborativo para compartilhamento de informações.

---

## ✨ Funcionalidades Implementadas

### 📅 Gerenciamento de Compromissos

* Cadastro de compromissos acadêmicos.
* Definição de data e horário para eventos.
* Organização de atividades importantes.

### 🗓️ Calendário

* Visualização dos compromissos cadastrados.
* Acompanhamento de eventos e atividades futuras.
* Melhor gerenciamento das responsabilidades acadêmicas.

### 💬 Fórum Acadêmico

* Espaço para interação entre alunos e professores.
* Compartilhamento de avisos.
* Criação de enquetes.
* Discussões sobre assuntos acadêmicos.

---

## 🏗️ Arquitetura

O projeto foi desenvolvido seguindo o padrão arquitetural **MVC (Model-View-Controller)**, proporcionando uma melhor organização do código, separação de responsabilidades e maior facilidade de manutenção.

### Estrutura MVC

* **Model:** Responsável pela representação e manipulação dos dados.
* **View:** Responsável pela interface gráfica desenvolvida em JavaFX.
* **Controller:** Responsável pela comunicação entre a interface e as regras de negócio.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **JavaFX**
* **PostgreSQL**
* **MVC (Model-View-Controller)**

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

* Java Development Kit (JDK) 21
* PostgreSQL instalado e configurado
* pgAdmin
* IDE compatível com Java (IntelliJ IDEA, Eclipse ou NetBeans)
* JavaFX configurado no ambiente

### Tutorial em Vídeo

Caso prefira acompanhar uma demonstração prática da instalação e execução do projeto, assista ao vídeo abaixo:

**🎥 Vídeo de configuração e execução:** *https://youtu.be/5f8wO_f6NCA*

### Passo a Passo

1. Baixe o repositório em formato **.zip**.

2. Extraia os arquivos para uma pasta de sua preferência.

3. O projeto possui duas partes principais:

```text
displine
```

Responsável pelo **back-end** da aplicação.

```text
Front
```

Responsável pelo **front-end** da aplicação.

4. Abra cada parte como um projeto separado na IDE.

   Primeiro, abra a pasta:

```text
displine
```

   Essa janela da IDE será utilizada para executar o back-end.

5. Em outra janela da IDE, abra a pasta:

```text
Front
```

   Essa segunda janela será utilizada para executar o front-end.

6. Configure a conexão com o PostgreSQL no projeto back-end, ajustando:

   * Usuário do banco de dados;
   * Senha;
   * Porta utilizada pelo PostgreSQL;
   * Nome do banco de dados;
   * Demais parâmetros de conexão, se necessário.

7. No projeto back-end, localizado na pasta **displine**, encontre a classe:

```text
DisplineApplication
```

8. Execute a classe **DisplineApplication** para iniciar o back-end da aplicação.

9. Aguarde o back-end iniciar completamente. Ele deve permanecer em execução durante o uso do sistema.

10. Com o back-end em execução, vá para a outra janela da IDE, onde está aberto o projeto **Front**.

11. No projeto front-end, localizado na pasta **Front**, encontre a classe:

```text
Launcher
```

12. Execute a classe **Launcher** para iniciar a aplicação JavaFX.

### Observações

* O projeto foi desenvolvido utilizando **Java 21**.
* Certifique-se de que o PostgreSQL esteja em execução antes de iniciar o sistema.
* O back-end deve ser executado antes do front-end.
* O back-end e o front-end devem ser abertos como projetos separados na IDE.
* Recomenda-se utilizar duas janelas da IDE: uma para o projeto **displine** e outra para o projeto **Front**.
* O front-end depende do back-end em execução para realizar login, cadastro e gerenciamento dos compromissos.
* É necessário que o JavaFX esteja corretamente configurado na IDE.
* A versão atual corresponde a um MVP (Minimum Viable Product) e ainda está em desenvolvimento.

---


## 📈 Status do Projeto

🚧 **Em Desenvolvimento**

O Displine encontra-se atualmente em fase de desenvolvimento. A versão atual corresponde a um MVP contendo as funcionalidades essenciais propostas durante o Ideathon.

Funcionalidades adicionais poderão ser incorporadas em versões futuras para ampliar a experiência dos usuários e fortalecer ainda mais a integração acadêmica.

---

## 👥 Equipe de Desenvolvimento

* João Pedro Melo Xavier
* José Soares da Silva
* Emanuel Cândido da Silva
* Arthur Andrade
* Rainan Coelho
* Iago Frânquel Freitas Sousa

---

## 🎓 Contexto Acadêmico

Projeto desenvolvido como continuação do **Ideathon**, aplicando conhecimentos de Engenharia de Software, desenvolvimento de interfaces gráficas, banco de dados e arquitetura de software para a construção de uma solução voltada ao ambiente acadêmico.

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos e educacionais.
