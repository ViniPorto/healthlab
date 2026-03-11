
<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/ViniPorto/healthlab?color=%2304D361">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/ViniPorto/healthlab">
  
  <a href="https://github.com/ViniPorto/healthlab/commits/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/ViniPorto/healthlab">
  </a>
    
   <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen">
   <a href="https://github.com/ViniPorto/healthlab/stargazers">
    <img alt="Stargazers" src="https://img.shields.io/github/stars/ViniPorto/healthlab?style=social">
  </a>

  <a href="https://www.linkedin.com/in/vinicius-porto-9a1996209/">
    <img alt="Feito por Vinícius Porto" src="https://img.shields.io/badge/feito%20por-Vinícius Porto-%237519C1">
  </a>
  
</p>
<h1 align="center">
    <img alt="HealthLab Logo" title="#HealthLab" src="." />
</h1>

<h4 align="center"> 
	🚧  HealthLab 🧬 Em construção 👷‍♂️ 🚧
</h4>

<h2 align="center">

![50%](https://progress-bar.dev/50)
    
</h2>


<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-funcionalidades">Funcionalidades</a> •
 <a href="#-layout">Layout</a> • 
 <a href="#-como-executar-o-projeto">Como executar</a> • 
 <a href="#-tecnologias">Tecnologias</a> • 
 <a href="#-contribuidores">Contribuidores</a> • 
 <a href="#-autor">Autor</a> • 
 <a href="#user-content--licença">Licença</a>
</p>


## 💻 Sobre o projeto

🧬 HealthLab - é uma plataforma de gerenciamento de exames laboratoriais conectando resultados aos pacientes.

Projeto desenvolvido apenas para fins estudantis, afim de treinar e aperfeiçoar a habilidade de programar. Este projeto deve ser o **principal no meu portóflio**, dada sua **complexidade**. Desta forma, deve servir para demonstrar habilidades na **programação backend com Java e Spring e frontend com React**.

---

## ⚙ Funcionalidades

- [x] Usuários do laboratório podem logar e realizar: 
  - [x] cadastro de pacientes
  - [x] cadastro de médicos
  - [x] gerenciamento de outros usuários
  - [x] gerenciamento de materiais biológicos
  - [x] gerenciamento de usuários bioquímicos
  - [x] usuários administradores
  - [x] agendamento de horários para coleta
  - [x] gerenciamento de exames laboratoriais, incluindo:
    - Vincular materiais biológicos
    - Vincular método laboratorial
    - Editar layout
    - Preço
  - [x] cadastro de requisições de exames:
    - Lista de exames cadastrados
    - Informar resultados aos exames
    - Status dinâmicos do estado dos exames
  - [x] usuários bioquímicos podem liberar os resultados dos exames, adicionando uma assinatura ao laudo
  - [x] triagem de amostras
  - [x] controle de coleta de materiais
  - [x] indicadores que atualizam de minuto em minuto dando um panorama geral dos exames que estão em andamento no momento
  - [x] geração de histórico para todas as ações realizadas pelos usuários
  - [x] geração de relatórios, podendo exportar os dados em PDF ou CSV
  - [x] geração de laudos de exames, com layout customizável pelos usuários
  - [x] solicitação de recoleta de amostras
  - [x] cadastro de requisições pré-programas para facilitar o cadastro de requisições iguais como para setores de UTI
  - [x] cadastro de lista exames principais para auxiliar o cadastro de requisições
  - [x] cadastro de orçamentos
  - [x] cadastro de requisições a partir de um orçamento

---

## 🎨 Layout

O layout da aplicação estará disponível no Figma quando for realizado.
<!---
<a href="">
  <img alt="" src="">
</a>
-->

### Web

<!---
<p align="center" style="display: flex; align-items: flex-start; justify-content: center;">
  <img alt="" title="" src="" width="400px">
  <img alt="" title="" src="" width="400px">
</p>
-->

---

## 🚀 Como executar o projeto

Este projeto é divido em 2 partes:
1. Backend (pasta Backend) 
2. Frontend (pasta Frontend)

💡O Frontend precisa que o Backend esteja sendo executado para funcionar.

## 🛠 Próximos passos

### 🌟 Implementar o front end em um ambiente Desktop com a linguagem Delphi ✨

---


### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html), [Maven](https://maven.apache.org/download.cgi), [MySQL](https://www.mysql.com/downloads/).
Além disto é bom ter um editor para trabalhar com o código como [VSCode](https://code.visualstudio.com/).

Por fim, crie 2 variáveis de ambiente chamadas "USERNAME_DB" e "PASSWORD_DB". Os valores das variáveis devem ser respectivamente o username e senha de um usuário do MySQL (pode ser user root).

#### 🎲 Rodando o Backend (servidor)

```bash

# Clone este repositório
$ git clone git@github.com:ViniPorto/healthlab.git

# Acesse a pasta do projeto no terminal/cmd
$ cd healthlab/Backend

# Execute a aplicação:
$ java -jar healthlabapi.jar

# O servidor inciará na porta:8080 - acesse http://localhost:8080 

```
<p align="center">
  <a href="https://github.com/viniporto/healthlab/insomnia/healthlab.json" target="_blank"><img src="https://insomnia.rest/images/run.svg" alt="Run in Insomnia"></a>
</p>


#### 🧭 Rodando a aplicação web (Frontend)

```bash

# Clone este repositório
$ git clone git@github.com:ViniPorto/healthlab.git

# Acesse a pasta do projeto no seu terminal/cmd
$ cd healthlab/Frontend

# Vá para a pasta da aplicação Front End
$ cd web

# Instale as dependências
$ npm install

# Execute a aplicação em modo de desenvolvimento
$ npm run start

# A aplicação será aberta na porta:3000 - acesse http://localhost:3000

```

---

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

#### **Website**  ([React](https://reactjs.org/))

-   **[React Router Dom](https://github.com/ReactTraining/react-router/tree/master/packages/react-router-dom)**
<!---
-   **[React Icons](https://react-icons.github.io/react-icons/)**
-   **[Axios](https://github.com/axios/axios)**
-   **[Leaflet](https://react-leaflet.js.org/en/)**
-   **[React Leaflet](https://react-leaflet.js.org/)**
-   **[React Dropzone](https://github.com/react-dropzone/react-dropzone)**

> Veja o arquivo  [package.json](https://github.com/viniporto/healthlab/blob/master/web/package.json)
-->

#### **Server**  ([Java](https://www.java.com/pt-BR/)  +  [Spring](https://spring.io/projects/spring-boot))

-   **[Spring Data JPA](https://github.com/spring-projects/spring-data-jpa)**
-   **[MySQL Connector J](https://github.com/mysql/mysql-connector-j)**
-   **[Bean Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)**
-   **[Spring Web](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html)**
-   **[Lombok](https://github.com/projectlombok/lombok)**
-   **[Flyway](https://github.com/flyway/flyway)**
-   **[Spring Test](https://docs.spring.io/spring-framework/reference/testing/introduction.html)**
-   **[Mockito](https://github.com/mockito/mockito)**

> Veja o arquivo  [pom.xml](https://github.com/ViniPorto/healthlab/blob/main/Backend/HealthLabApi/pom.xml)

#### [](https://github.com/tgmarinho/Ecoleta#utilit%C3%A1rios)**Utilitários**

-   Protótipo:  **[Figma](https://www.figma.com/)**  →  **[Protótipo (Healthlab)](https://www.figma.com/file/)**
-   Editor:  **[Visual Studio Code](https://code.visualstudio.com/)** 
-   Teste de API:  **[Insomnia](https://insomnia.rest/)**
-   Ícones:  **[Feather Icons](https://feathericons.com/)**,  **[Font Awesome](https://fontawesome.com/)**

---

## 💪 Como contribuir para o projeto

1. Faça um **fork** do projeto.
2. Crie uma nova branch com as suas alterações: `git checkout -b my-feature`
3. Salve as alterações e crie uma mensagem de commit contando o que você fez: `git commit -m "feature: My new feature"`
4. Envie as suas alterações: `git push origin my-feature`
> Caso tenha alguma dúvida confira este [guia de como contribuir no GitHub](./CONTRIBUTING.md)

---

## 🦸 Autor

<a href="https://github.com/ViniPorto">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/81120004?s=460&u=61b426b901b8fe02e12019b1fdb67bf0072d4f00&v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Vinícius Porto</b></sub></a> <a href="https://github.com/ViniPorto" title="Vinícius">🚀</a>
 <br />

---

## 📝 Licença

Este projeto esta sobe a licença [MIT](./LICENSE).

Feito com ❤️ por Vinícius Porto 👋 [Entre em contato!](https://www.linkedin.com/in/vinicius-porto-9a1996209/)

---
