# WeMovie - Desafio WeFit

Este projeto é um aplicativo mobile de e-commerce simplificado desenvolvido para o desafio de estruturação de projeto nativo Android da WeFit. O aplicativo foi construído utilizando a arquitetura MVVM (Model-View-ViewModel), com Kotlin como linguagem de programação principal. Ele se conecta a uma API para exibir uma lista de filmes, permitindo que o usuário adicione itens ao carrinho e finalize a compra.

## Demonstração
Em breve, vídeo demonstrando as funcionalidades do aplicativo, requisição da API, card com os filmes, card do carrinho e etc.

## Índice
- [Sobre o Desafio](#sobre-o-desafio)
- [Arquitetura MVVM](#arquitetura-mvvm)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Funcionalidades](#funcionalidades)
- [API](#api)
- [Rodando o Projeto](#rodando-o-projeto)
- [Bibliotecas](#bibliotecas)
- [Avaliação](#avaliação)
- [Pontos Avaliados](#pontos-avaliados)
- [Repositório](#repositório)

## Sobre o Desafio
O desafio WeFit consiste na criação de um aplicativo de e-commerce com as seguintes funcionalidades:
- Exibir uma lista de filmes obtidos de uma API.
- Permitir que o usuário adicione filmes a um carrinho de compras.
- Exibir o carrinho de compras com o valor total da compra.
- Permitir que o usuário remova itens do carrinho.
- Exibir uma tela de confirmação de compra.
- Implementar navegação entre as telas.

## Arquitetura MVVM
A arquitetura MVVM (Model-View-ViewModel) foi utilizada para garantir a separação de responsabilidades e facilitar a manutenção do código.

### Componentes da arquitetura MVVM:
- **Model**: Representa os dados do aplicativo (no caso, os filmes).
- **View**: Responsável pela exibição da interface do usuário e pela interação com o usuário.
- **ViewModel**: Atua como intermediário entre a View e a Model, fornecendo dados para a View e processando as ações do usuário.

### Benefícios da arquitetura MVVM:
- **Testabilidade**: Separação clara entre a lógica da interface e a lógica de negócios, facilitando a criação de testes unitários.
- **Manutenibilidade**: Código mais organizado e modular, facilitando a manutenção e a evolução do aplicativo.
- **Reusabilidade**: ViewModels podem ser reutilizados em diferentes Views.

## Estrutura do Projeto
O projeto está organizado da seguinte forma:

### UI (User Interface):
Contém os componentes da interface do usuário, como Activities, Fragments e layouts XML.

#### Activities:
- **MainActivity**: Tela inicial de boas-vindas.
- **LoadingActivity**: Tela de carregamento.
- **HomeActivity**: Activity principal que hospeda os Fragments.

#### Fragments:
- **HomeFragment**: Exibe a lista de filmes.
- **CartFragment**: Exibe o carrinho de compras.
- **OrderConfirmationFragment**: Exibe a confirmação da compra.
- **CarrinhoVazioFragment**: Exibe uma mensagem quando o carrinho está vazio.

#### Layouts:
- `activity_main.xml`, `activity_loading.xml`, `activity_home.xml`, `fragment_home.xml`, `fragment_cart.xml`, `fragment_order_confirmation.xml`, `fragment_carrinho_vazio.xml`, `item_movie_card.xml`, `item_movie_cart.xml`

#### ViewModel:
Contém as classes ViewModel que gerenciam os dados da UI.
- **HomeViewModel**: Gerencia os dados da tela Home.
- **SharedViewModel**: Gerencia os dados compartilhados entre os Fragments, como o carrinho de compras.

#### Model:
Contém as classes que representam os dados do aplicativo.
- **Movie**: Representa um filme.
- **Data**: Contém as classes responsáveis pela comunicação com a API.
  - **MovieRepository**: Responsável por buscar os filmes da API.
  - **MovieService**: Interface que define os endpoints da API.

#### Adapter:
Contém os Adapters para o RecyclerView.
- **HomeAdapter**: Adapter para a lista de filmes na tela Home.
- **MovieAdapter**: Adapter para o carrinho de compras.

## Funcionalidades
O aplicativo possui as seguintes funcionalidades:
- Exibe uma lista de filmes obtida de uma API.
- Permite adicionar filmes ao carrinho de compras.
- Exibe o carrinho de compras com o total da compra.
- Permite remover filmes do carrinho de compras.
- Exibe uma tela de confirmação de compra.
- Implementa navegação entre as telas.
- Exibe mensagens de "carregando" e "carrinho vazio".

## API
O aplicativo consome uma API REST que retorna uma lista de filmes. O endpoint utilizado é:

- `GET https://wefit-movies.vercel.app/api/movies`

## Rodando o Projeto
Para rodar o projeto, siga estes passos:
1. Clone o repositório do projeto.
2. Abra o projeto no Android Studio.
3. Compile e execute o aplicativo em um emulador ou dispositivo físico.

## Bibliotecas
O projeto utiliza as seguintes bibliotecas:
- **Retrofit**: Para realizar as requisições HTTP à API.
- **Gson**: Para converter os dados JSON da API em objetos Kotlin.
- **Glide**: Para carregar e exibir as imagens dos filmes.
- **AndroidX**: Conjunto de bibliotecas que fornecem funcionalidades e componentes para o desenvolvimento Android.
- **ViewModel**: Para gerenciar os dados da UI.
- **LiveData**: Para observar mudanças nos dados e atualizar a UI.
- **Data Binding**: Para vincular os dados do aplicativo aos elementos da UI.
- **Navigation**: Para implementar a navegação entre as telas.
- **Coroutines**: Para realizar operações assíncronas.

## Avaliação

### Pontos Avaliados:
- Fidelidade ao layout do Figma.
- Funcionamento e ausência de bugs.
- Qualidade do código:
  - Estrutura e organização de pastas.
  - Componentização.
  - Uso de bibliotecas.
  - Separação de responsabilidades (MVVM).
  - Legibilidade e clareza do código.
  - Boas práticas de desenvolvimento.

## Repositório
O código fonte do projeto está disponível no seguinte repositório:

[https://docs.github.com/pt/repositories](https://docs.github.com/pt/repositories)

### Observação
Este projeto foi desenvolvido como parte do desafio WeFit e pode ser utilizado como base para o desenvolvimento de aplicativos Android com a arquitetura MVVM.
