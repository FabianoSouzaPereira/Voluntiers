# Voluntiers

![Static Badge](https://img.shields.io/badge/Jetbrains-1.9-blue?labelColor=green)
![Static Badge](https://img.shields.io/badge/SDK-34-blue?labelColor=brown)
![GitHub version](https://img.shields.io/badge/version-v1.0-green)
![GitHub repo size](https://img.shields.io/github/repo-size/FabianoSouzaPereira/Voluntiers?color=blue)
![GitHub language count](https://img.shields.io/github/languages/count/FabianoSouzaPereira/Voluntiers??color=red)
![GitHub top language](https://img.shields.io/github/languages/top/FabianoSouzaPereira/Voluntiers??color=green)
![Static Badge](https://img.shields.io/badge/Coroutine-2.6.2-blue?labelColor=brown)
![Static Badge](https://img.shields.io/badge/Retofit-2.9.0-blue?labelColor=brown)</br>

For Church Volunteers Volunteering is at the heart of the church spirit and we understand the
importance of a well-organized and efficient system for empowering our volunteers. The motivation
behind this project is to create a centralized platform that simplifies communication, task
management and collaboration between our volunteers.
<br />

## Architecture

**Why Use Layers?**
<br />
Dividing an application into layers is a common practice in software architecture that provides
several crucial benefits. Firstly, it enables the separation of responsibilities, where each layer
has a clearly defined role—such as the presentation layer handling user interface, the domain layer
managing business logic, and the data layer dealing with data access and storage. This separation
enhances code readability and simplifies system maintenance.

Moreover, the modular nature of layer division allows for the creation of independent modules,
facilitating the addition, removal, or replacement of functionalities without impacting other parts
of the system. This modularity also promotes code reusability, as specific business logic in the
domain layer can be easily employed in various sections of the application or even across different
projects.

The ease of maintenance is another advantage, as issues within a particular layer can be isolated
and addressed without affecting others, as long as the interfaces between the layers remain
consistent. This segmentation enhances testability, enabling the conduct of isolated unit tests for
each layer and ensuring the proper functioning of individual components.

Additionally, the architecture's scalability benefits from layer division, allowing different parts
of the application to scale independently. For example, if there's an increased load on the
database, the data layer can be scaled without directly impacting the presentation layer. The
flexibility of technology is also a notable advantage, as each layer can be implemented using
different technologies, provided the interfaces between them are respected. This flexibility allows
for the adaptation to new technologies without the need for a complete redevelopment of the entire
application. In summary, layer division promotes a well-organized, flexible, and easily maintainable
architecture, contributing to the development of robust and sustainable applications in the long
run.

Used Layers in this project

- Data
- Domain
- Presentation
  <br />

![alt text](https://github.com/FabianoSouzaPereira/Voluntiers/blob/feature/UseCase/images/clean_arch.png?raw=true)

<br />

## Data

The data layer is responsible for data access and interaction with external data sources, such as
APIs, databases, or caches. Here, you'll find elements related to data access and manipulation.

- **DataSources:** Classes responsible for interacting directly with external data sources. For
  example, a LoginDataSource that makes calls to an authentication service.

- **Repositories:** Interfaces and implementations that define how data is fetched and manipulated.
  They act as an abstraction layer between the DataSources and UseCases.

- **Data Models:** Classes that represent the structure of data in the application.
  <br />

## Domain

In the domain layer, you typically place domain entities, business rules, and application logic that
are not directly related to data manipulation or the user interface. Here are some common elements
that can be found in the domain layer:

- **Domain Entities:** Classes representing important business concepts. For example, if your
  application is a task management system, you may have a Task entity containing information about a
  task, such as title, description, creation date, etc.

- **Business Rules:** Logic that implements the rules and policies of the domain. For instance,
  specific validations for your domain that do not belong to the presentation or data layer.

- **Use Cases:** Classes that encapsulate a specific task or complete functionality of your
  application. They orchestrate the execution of operations on entities and repositories to perform
  specific actions.

- **Interfaces:** Contracts defining interactions between the domain layer and other layers, such as
  interfaces for repositories or external services.

- **Events or Observers:** Mechanisms for communication between components of the domain layer. This
  may include implementing patterns like Observer or using events to notify interested parties about
  changes in the state.

- **Domain Exceptions:** Domain-specific exception classes that capture errors or exceptional
  conditions occurring during the execution of business rules.
  <br />

## Presentation

The presentation layer is responsible for the user interface and data presentation. Here, you'll
find elements related to the UI, such as activities, fragments, adapters, ViewModels, and other
components that handle presentation logic. Additionally, you may have sub-packages for each resource
or functionality of the application.

- **ViewModels:** Classes containing presentation logic and responsible for managing and providing
  data to user interfaces.

- **Adapters:** If you are using lists or grids, adapters can be used to associate data with UI
  elements.
- **Reusable UI Components:** Any UI component that is shared among various parts of the
  application.
  <br />
  <br />

## Here's a quick explanation of the layers and their responsibilities:

This architecture follows principles of Clean Architecture and/or Hexagonal Architecture, promoting
a clear separation of responsibilities among different layers of your application.

- **UI (User Interface):** The user interface layer contains classes that directly handle user
  presentation and interaction. Typically, this includes activities, fragments, and UI components,
  as well as the ViewModel to manage UI logic.

- **ViewModel:** The ViewModel acts as an intermediary layer between the UI and use cases. It's
  responsible for managing the UI state and providing necessary data to the user interface. The
  ViewModel interacts with use cases to obtain data and perform actions.

- **UseCases:** Use cases encapsulate the specific business logic of your application. They
  orchestrate the operations required to meet the requirements of a specific functionality. Use
  cases interact with domain entities.

- **Entities:** Entities represent the main domain objects of your application. They are usually
  simple data objects containing minimal business logic if any.

- **Repository:** Repositories define interfaces for accessing data. They provide an abstraction
  layer between use cases and data sources (DataSource). Concrete repository implementations are
  responsible for coordinating specific data retrieval and storage operations.

- **DataSource (Web or Local):** DataSources are responsible for interacting directly with data
  sources, such as a web API or a local database. They provide a concrete implementation for basic
  data read and write operations.

This type of architecture promotes modularity, making it easier to replace or change components
without affecting other parts of the system. Additionally, it helps keep business logic independent
of implementation details and facilitates unit testing for each layer separately.

# Retrofit with Coroutine

Although the Retrofit API call is happening within a try-catch block, I considered using withContext
to ensure that the execution occurs in the appropriate context.

### In ViewModel

   ```kotlin
        loginButton.setOnClickListener {
    loadingProgressBar.visibility = View.VISIBLE
    lifecycleScope.launch {
        viewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
        )
    }
}
```

### In DataModule

```kotlin
        @Provides
@Singleton
fun provideAuthApiService(): IAuthApiService {
    return Retrofit.Builder()
            .baseUrl("https://example.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IAuthApiService::class.java)
}
 ```

## Visão Geral da estrutura do Dagger2

Este projeto utiliza o Dagger, uma biblioteca popular de injeção de dependência para Java e Android,
para gerenciar as dependências entre os componentes da aplicação de forma eficiente e desacoplada. A
estrutura de injeção de dependência é organizada em Componentes, Módulos, e Subcomponentes,
facilitando a manutenção e o teste do código.

### Componentes

Os Componentes do Dagger servem como pontes entre os objetos consumidores de dependências (como
atividades, fragmentos e serviços) e os módulos do Dagger que fornecem essas dependências.

- ApplicationComponent:É o componente raiz da aplicação. Ele vive durante todo o ciclo de vida da
  aplicação e fornece dependências de escopo global, como o contexto da aplicação (
  ApplicationContext)
  e serviços de rede.
  Este componente é inicializado na classe Application do Android (AppApplication neste caso).

### Módulos

Os Módulos do Dagger declaram as dependências que podem ser injetadas pelos componentes. Eles podem
fornecer instâncias de classes concretas ou interfaces, configurar objetos complexos e até mesmo
agrupar dependências relacionadas para facilitar a manutenção.

- AppModule: Fornece dependências relacionadas ao contexto da aplicação, como o ApplicationContext.
  Isso é crucial para acessar recursos do sistema, realizar operações relacionadas ao Android, e
  fornecer instâncias que necessitam do contexto.

- DataModule: Concentra-se em fornecer dependências relacionadas à camada de dados, como
  repositórios,
  fontes de dados remotas e locais, e serviços de API. Este módulo é essencial para abstrair a
  origem
  dos dados e facilitar o teste e a manutenção da lógica de negócios.

### Subcomponentes

Os Subcomponentes são componentes Dagger que herdam e estendem o grafo de objetos do componente pai.
Eles são úteis para escopos mais específicos, como atividades ou fragmentos, permitindo uma
separação clara das responsabilidades e um gerenciamento eficiente do ciclo de vida das
dependências.

- MainComponent: É um exemplo de subcomponente usado para injetar dependências em atividades,
  fragmentos e outras classes relacionadas à UI principal da aplicação. Ele pode acessar todas as
  dependências fornecidas pelo ApplicationComponent e incluir módulos adicionais específicos para as
  necessidades da UI.

### ViewModel e Factory

- ViewModelBuilder: Um módulo especializado que utiliza multibinding do Dagger para vincular
  ViewModels específicos da aplicação. Isso facilita a injeção de dependências em ViewModels e a
  criação de uma Factory personalizada para ViewModels, permitindo uma arquitetura MVVM limpa e
  testável.

- NavigationViewModelFactory: Uma implementação personalizada de ViewModelProvider.Factory que usa o
  Dagger para criar instâncias de ViewModel. Isso permite a injeção de dependências em ViewModels de
  forma dinâmica, baseada em seu tipo.

Esta estrutura de injeção de dependência, utilizando o Dagger, promove uma arquitetura limpa e
desacoplada, facilitando o desenvolvimento, teste e manutenção da aplicação. 