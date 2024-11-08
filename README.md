*Em Breve vídeo demostrando as funcionalidades do aplicativo, requisição da api, card com os filmes, card do carrinho e etc* 

# WeMovie - Desafio WeFit

Aplicativo mobile de e-commerce simplificado desenvolvido para o desafio de estruturação de projeto nativo Android da WeFit. Este app foi construído com a estrutura MVVM, utilizando Kotlin como linguagem principal, e se conecta a uma API para exibir uma lista de filmes, permitindo que o usuário adicione itens ao carrinho e finalize a compra.

## Índice
- [WeMovie - Desafio WeFit](#wemovie---desafio-wefit)
  - [Índice](#índice)
  - [Sobre o Desafio](#sobre-o-desafio)
  - [Arquitetura](#arquitetura)
  - [Estrutura do Projeto](#estrutura-do-projeto)
  - [Funcionalidades](#funcionalidades)
  - [API](#api)
  - [Avaliação](#avaliação)
    - [Pontos Avaliados](#pontos-avaliados)
    - [Outros aspectos](#outros-aspectos)
  - [Repositório](#repositório)
  - [Observação](#observação)

## Sobre o Desafio

O desafio WeFit consiste na criação de um aplicativo de e-commerce com três telas principais:
1. **Home**: Exibe uma lista de filmes obtidos via requisição na API. Cada filme pode ser adicionado ao carrinho.
2. **Carrinho**: Lista os filmes adicionados ao carrinho, mostrando o valor total e permitindo a remoção de itens. Quando o carrinho está vazio, exibe uma tela de estado vazio (**empty state**), com opção de voltar para a tela inicial.
3. **Compra Realizada**: Confirma a compra e permite ao usuário retornar para a tela inicial.


## Arquitetura

O aplicativo utiliza a arquitetura MVVM (Model-View-ViewModel) para separar as responsabilidades de interface, lógica de negócios e manipulação de dados, garantindo um código modular e de fácil manutenção.

## Estrutura do Projeto

O projeto é dividido nas seguintes pastas e arquivos principais:
- **activity_main.xml**: Tela de boas-vindas.
- **activity_loading.xml**: Tela de carregamento enquanto espera a resposta da API.
- **activity_home.xml**: Tela principal que exibe a lista de filmes e barra de navegação inferior.
- **activity_cart.xml**: Tela do carrinho com itens e valor total.
- **activity_order_confirmation.xml**: Tela de confirmação de compra.

## Funcionalidades

- Exibir lista de filmes recebida da API.
- Adicionar e remover filmes do carrinho.
- Exibir valor total dos itens no carrinho.
- Empty State para carrinho vazio.
- Confirmação de compra com opção de retornar à tela inicial.

## API

A API simulada retorna uma lista de filmes e pode ser acessada através do endpoint:
- `GET https://wefit-movies.vercel.app/api/movies`

## Avaliação

### Pontos Avaliados
- **Fidelidade ao layout do Figma**
- **Funcionamento e ausência de bugs**
- **Qualidade do código**:
  - Estrutura e organização de pastas.
  - Componentização eficiente.
  - Aplicação de bibliotecas para otimizar o desenvolvimento.
  - Boas práticas de isolamento entre a UI e a lógica de integração.
  - Legibilidade e clareza no código, com variáveis nomeadas de forma intuitiva.

### Outros aspectos
- Visão sistêmica e estruturação adequada do projeto.
