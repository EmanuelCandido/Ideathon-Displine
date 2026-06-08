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

---

# Checklist de Inspeção de Qualidade do Sistema



## 1. Checklist de Interface e Usabilidade

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| A tela de login é exibida corretamente | ☑ | ☐ | Tela inicial do sistema funcionando. |
| A tela de cadastro é exibida corretamente | ☑ | ☐ | Tela de cadastro com campos principais. |
| O dashboard é carregado após o login ou cadastro | ☑ | ☐ | O usuário é direcionado para o dashboard. |
| Os botões possuem nomes claros | ☑ | ☐ | Os botões indicam suas funções principais. |
| Os campos possuem identificação adequada | ☑ | ☐ | Os campos possuem textos e indicações visuais. |
| A interface mantém um padrão visual entre as telas | ☑ | ☐ | As telas seguem uma identidade visual parecida. |
| As mensagens de sucesso são exibidas corretamente | ☑ | ☐ | Mensagens de sucesso foram ajustadas. |
| As mensagens de erro são claras para o usuário | ☑ | ☐ | Erros foram tratados para não aparecerem apenas como JSON técnico. |
| O calendário é exibido de forma organizada | ☑ | ☐ | Calendário mensal implementado. |
| O dia atual é destacado no calendário | ☑ | ☐ | O sistema destaca o dia atual. |

---

## 2. Checklist de Cadastro de Usuário

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| O sistema permite cadastrar um aluno | ☑ | ☐ | Cadastro de aluno funcionando via API. |
| O campo nome é obrigatório | ☑ | ☐ | Validação aplicada no back-end. |
| O campo e-mail é obrigatório | ☑ | ☐ | Validação aplicada no DTO. |
| O sistema valida o formato do e-mail | ☑ | ☐ | Uso de validação de e-mail. |
| O campo senha é obrigatório | ☑ | ☐ | Senha adicionada ao cadastro. |
| A senha possui quantidade mínima de caracteres | ☑ | ☐ | Validação mínima aplicada. |
| O campo matrícula é obrigatório | ☑ | ☐ | Campo obrigatório no cadastro de aluno. |
| O campo turma é obrigatório | ☑ | ☐ | Campo obrigatório no cadastro. |
| O campo curso é obrigatório | ☑ | ☐ | Curso selecionado no cadastro. |
| O sistema impede cadastro duplicado quando necessário | ☑ | ☐ | Banco possui restrições como e-mail e matrícula únicos. |
| Após o cadastro, o usuário é direcionado corretamente | ☑ | ☐ | Após cadastrar, o sistema abre o dashboard. |

---

## 3. Checklist de Login e Sessão

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| O sistema permite login com e-mail e senha | ☑ | ☐ | Login implementado no back-end e front-end. |
| O sistema bloqueia login com dados inválidos | ☑ | ☐ | Dados inválidos retornam erro. |
| O usuário logado é identificado corretamente | ☑ | ☐ | Dados do usuário são armazenados na sessão do front-end. |
| O nome do usuário aparece no dashboard | ☑ | ☐ | Nome exibido no dashboard. |
| Os compromissos exibidos pertencem ao usuário logado | ☑ | ☐ | Eventos são buscados pelo ID do usuário. |
| O sistema não mistura dados de usuários diferentes | ☑ | ☐ | Eventos possuem vínculo com o usuário. |

---

## 4. Checklist de Gerenciamento de Compromissos

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| O sistema permite criar compromissos | ☑ | ☐ | Cadastro de compromisso implementado. |
| O compromisso é salvo no banco de dados | ☑ | ☐ | Evento salvo via API no PostgreSQL. |
| O compromisso aparece no calendário após ser criado | ☑ | ☐ | Evento aparece no dia correspondente. |
| O compromisso aparece na lista de próximos compromissos | ☑ | ☐ | Lista de compromissos implementada. |
| O sistema permite editar compromissos cadastrados | ☑ | ☐ | Compromissos podem ser clicados e editados. |
| As alterações são atualizadas no banco de dados | ☑ | ☐ | Edição enviada para a API. |
| O título do compromisso é obrigatório | ☑ | ☐ | Validação aplicada. |
| A data do compromisso é obrigatória | ☑ | ☐ | Validação aplicada. |
| O horário de início é obrigatório | ☑ | ☐ | Validação aplicada. |
| O horário de fim é obrigatório | ☑ | ☐ | Validação aplicada. |
| O tipo do compromisso é obrigatório | ☑ | ☐ | Validação aplicada. |
| A prioridade do compromisso é exibida corretamente | ☑ | ☐ | Prioridade aparece nos dados do compromisso. |
| A descrição do compromisso é exibida quando preenchida | ☑ | ☐ | Descrição foi adicionada aos cards. |

---

## 5. Checklist de Calendário

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| O calendário abre no mês atual | ☑ | ☐ | Calendário inicia no mês atual. |
| O usuário consegue navegar para o mês anterior | ☑ | ☐ | Navegação implementada. |
| O usuário consegue navegar para o próximo mês | ☑ | ☐ | Navegação implementada. |
| O botão para voltar ao mês atual funciona corretamente | ☑ | ☐ | Função de retorno ao mês atual implementada. |
| Os compromissos aparecem no dia correto | ☑ | ☐ | Eventos são relacionados pela data. |
| O usuário consegue clicar em um compromisso no calendário | ☑ | ☐ | Clique abre edição. |
| Ao clicar no compromisso, a tela de edição é aberta | ☑ | ☐ | Modal de edição implementado. |

---

## 6. Checklist de Integração entre Front-end e Back-end

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| O front-end se comunica corretamente com a API | ☑ | ☐ | Comunicação feita por HTTP. |
| O back-end é executado antes do front-end | ☑ | ☐ | Necessário para funcionamento correto. |
| O cadastro envia os dados corretamente para a API | ☑ | ☐ | JSON enviado para o endpoint de aluno. |
| O login envia os dados corretamente para a API | ☑ | ☐ | Login integrado com endpoint de usuário. |
| A listagem de eventos busca os dados do usuário logado | ☑ | ☐ | Busca feita por ID do usuário. |
| Os erros vindos da API são tratados no front-end | ☑ | ☐ | Mensagens foram formatadas para o usuário. |
| As respostas da API são convertidas corretamente no front-end | ☑ | ☐ | Uso de Gson para converter eventos. |

---

## 7. Checklist de Banco de Dados

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| O PostgreSQL está configurado corretamente | ☑ | ☐ | Banco configurado no `application.properties`. |
| O banco de dados `Discipline` foi criado | ☑ | ☐ | Banco utilizado pelo sistema. |
| As tabelas principais foram criadas | ☑ | ☐ | Tabelas de usuário, aluno, professor e evento. |
| A tabela de usuários armazena os dados corretamente | ☑ | ☐ | Dados gerais salvos em `usuario`. |
| A tabela de alunos armazena os dados corretamente | ☑ | ☐ | Dados específicos salvos em `aluno`. |
| A tabela de eventos armazena os compromissos corretamente | ☑ | ☐ | Eventos salvos em `evento`. |
| Os eventos possuem vínculo com o usuário | ☑ | ☐ | Uso de chave estrangeira para usuário. |
| As chaves estrangeiras estão funcionando corretamente | ☑ | ☐ | Relacionamento entre tabelas funcionando. |
| O sistema não salva eventos sem usuário associado | ☑ | ☐ | Evento exige usuário vinculado. |

---

## 8. Checklist de Validação e Tratamento de Erros

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| O sistema valida campos obrigatórios | ☑ | ☐ | Validações aplicadas com DTOs. |
| O sistema exibe erro quando o cadastro está incompleto | ☑ | ☐ | Mensagens de erro exibidas no front-end. |
| O sistema exibe erro quando o login é inválido | ☑ | ☐ | Login inválido é bloqueado. |
| O sistema exibe erro quando o banco não está disponível | ☑ | ☐ | Erro aparece caso a API não consiga conectar ao banco. |
| As mensagens de erro são compreensíveis para o usuário final | ☑ | ☐ | Melhoradas no JavaFX. |
| O sistema trata erros sem fechar inesperadamente | ☑ | ☐ | Uso de `try/catch` e alertas. |
| O back-end retorna respostas adequadas para erros | ☑ | ☐ | Tratamento com exceções e validações. |

---

## 9. Checklist de Organização do Código

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| O back-end está separado do front-end | ☑ | ☐ | Back em `displine` e front em `Front`. |
| O back-end está organizado em Controller, Service, Repository, DTO e Entity | ☑ | ☐ | Estrutura em camadas aplicada. |
| O front-end está organizado com Controllers, FXML e classes auxiliares | ☑ | ☐ | JavaFX organizado por telas e controllers. |
| Os arquivos FXML estão na pasta correta de resources | ☑ | ☐ | Estrutura corrigida para `resources`. |
| Os arquivos gerados, como `target` e `out`, não fazem parte do código principal | ☑ | ☐ | Pasta `out` removida. |
| O projeto possui `.gitignore` configurado | ☑ | ☐ | Ignora arquivos gerados e da IDE. |
| O README explica como executar o projeto | ☑ | ☐ | README ajustado com execução do back e front. |
| O script SQL está disponível no repositório | ☑ | ☐ | Arquivo `tabelasdispline.sql` disponível. |

---

## 10. Checklist de Execução

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| O back-end executa sem erro | ☑ | ☐ | Executa pela classe `DisplineApplication`. |
| O front-end executa sem erro | ☑ | ☐ | Executa pela classe `Launcher`. |
| O PostgreSQL está ativo antes da execução do back-end | ☑ | ☐ | Necessário para iniciar a API. |
| O back-end é executado antes do front-end | ☑ | ☐ | Fluxo correto do sistema. |
| O sistema funciona com os dois projetos abertos separadamente na IDE | ☑ | ☐ | Recomendado abrir `displine` e `Front` em janelas separadas. |
| A API fica disponível em `http://localhost:8080` | ☑ | ☐ | Porta padrão do Spring Boot. |
| O front-end consegue acessar a API corretamente | ☑ | ☐ | Requisições funcionando. |

---

## 11. Checklist de Segurança Básica

| Item avaliado | Sim | Não | Observações |
|---|---|---|---|
| O sistema não exibe mensagens técnicas para o usuário final | ☑ | ☐ | Mensagens foram tratadas no front-end. |
| O sistema não permite acessar compromissos de outros usuários | ☑ | ☐ | Eventos são filtrados pelo usuário logado. |
| O sistema separa corretamente os dados por usuário logado | ☑ | ☐ | Sessão do usuário usada para buscar os eventos. |
| As senhas não são exibidas na interface | ☑ | ☐ | Campo de senha usa `PasswordField`. |
| O arquivo de configuração do banco não deve conter senha real em repositório público | ☐ | ☑ | Ainda precisa ser melhorado usando arquivo de exemplo ou variável de ambiente. |

---

## 12. Resultado da Inspeção

| Critério | Resultado |
|---|---|
| Sistema executa corretamente | ☑ Aprovado ☐ Reprovado ☐ Parcial |
| Interface adequada | ☑ Aprovado ☐ Reprovado ☐ Parcial |
| Funcionalidades principais funcionando | ☑ Aprovado ☐ Reprovado ☐ Parcial |
| Integração front-end/back-end funcionando | ☑ Aprovado ☐ Reprovado ☐ Parcial |
| Banco de dados funcionando | ☑ Aprovado ☐ Reprovado ☐ Parcial |
| Código organizado | ☑ Aprovado ☐ Reprovado ☐ Parcial |

---

## 13. Observações Gerais

Durante a inspeção, o sistema apresentou funcionamento adequado para a proposta de MVP. As principais funcionalidades foram verificadas, incluindo cadastro de aluno, login, dashboard, calendário, cadastro e edição de compromissos, além da integração entre front-end, back-end e banco de dados.

Como pontos de melhoria, recomenda-se:

```text
- Implementar criptografia de senha.
- Criar autenticação mais segura, como uso de token.
- Melhorar o controle de perfis entre aluno e professor.
- Persistir funcionalidades completas do fórum no banco de dados.
- Evitar deixar senha real do banco no repositório público.
- Ampliar os testes do sistema.
