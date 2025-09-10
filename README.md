# 🍽️ TablesManagement
Uma aplicação para dispositivos moveis para mapear mesas de restaurantes

## Tabela de Conteúdos
- [Sobre](#sobre)
- [Tecnologias](#tecnologias)
- [Features](#features)
- [Arquitetura](#arquitetura)
- [Instalação](#instalacao)
- [Como usar](#como-usar)
- [Contribuição](#contribuicao)

## Sobre
este projeto é uma solução projetada para otimizar a gestão de mesas, melhorar o atendimento ao cliente e aumentar a eficiência operacional. O app permite que a equipe do restaurante visualize a disposição das mesas, o status de ocupação e o numero de pedidos de cada mesa cadastrada.

---

## Contexto
Muitos restaurantes por ter um grande fluxo de clientes ou um numero baixo de funcionarios, para atendimento ao cliente, aparece episodios onde uma mesa tenha uma demora ao ser atendido ou até mesmo o envio de um pedido para uma mesa errada. este projeto busca auxiliar os garçons com uma solução baseada no kotlin Android.

---

## Objetivos
- Desenvolver um App mobile em Kotlin para permitir que os garçons visualizem todo o fluxo de atendimento das mesas
- Garantir escalabilidade e organização dos dados

---

## Non-Goals
- não será implementado sistema para adicionar pedidos

---
## Alternativas
Foi considerado o uso de dependencias como Gson para a manipulação dos dados ou Kapt para a integração com o room 

---
### Requisitos Funcionais
- Listagem de mesas: Exibição de todas as mesas, indicando seu status (ocupada, livre, reservada, etc.).
- Pesquisa avançada: Busca por nome do cliente, número da mesa e nome do atendente.
- Filtros das mesas: Opções para filtrar mesas por visão geral, em atendimento, ocupadas e ociosas.
- Mostrar informações da mesa e das comandas na listagem: Implementar a visualização rápida de quem está atendendo a mesa, o nome do cliente, quantidade de comandas, há quanto tempo está sem fazer novo pedido, etc.

## Tecnologias
- Kotlin
- Jetpack Compose
- Android SDK
- ViewModel / Room/ Paging
- Serialization 
- Material Icons Extended

## 📱 Funcionalidades
- Tela Home
- Tela para mapeamento
- Lista de itens
- Detalhes de item
- Filtros e pesquisa
- Menu inferior

## 🍔 Arquitetura
```
app/
├── manifests/            # Arquivo AndroidManifest.xml
├── java/
│   └── com/
│       └── example/
│           └── tablesmanagement/ # Pacote raiz da aplicação
│               ├── data/
│               │   ├── local/            # Fonte de dados local (banco de dados)
│               │   │   ├── converter/    # Conversores para tipos de dados
│               │   │   ├── dao/          # Data Access Objects (DAOs) para acesso ao banco
│               │   │   └── entity/       # Classes de entidade (tabelas do banco de dados)
│               │   ├── AppDatabase.kt    # Classe de banco de dados Room
│               ├── model/                # Classes de modelo (estruturas de dados)
│               ├── view                  # Camada de UI (interface do usuário)
│               │   ├── components/       # Componentes de UI reutilizáveis
│               │   └── screens/          # Telas da aplicação
│               ├── viewmodel/            # ViewModels (lógica de negócio e estado da UI)
│               ├── MainActivity.kt       # Ponto de entrada da aplicação
│               └── MyApplication.kt      # Classe de aplicação customizada
└── res/                   # Recursos da aplicação (layouts, strings, imagens, etc.)
    ├── drawable/
    ├── font/
    ├── mipmap/
    ├── raw/
    └── values/
```
## Camadas
A aplicação esta estruturada em várias camadas bem definidas:
- Data ( Model e persistêscia local) → Base de dados, entidades, DAO e paginação.
- View (Interface do utilizador) → Componentes visuais com Compose e ecrãs principais.
- ViewModel (Lógica de negócio) → Liga a interface com os dados, gerindo estados e interações.

## ⚙️ Camada **Data**

### 🔹 AppDatabase.kt
- Define a base de dados Room da aplicação.
- Faz a ligação entre entidades e DAO.

#### Exemplos de uso
```kotlin
val db = AppDatabase.getDatabase(context)
val dao = db.checkPadDao()

dao.insert(CheckPadEntity(tableNumber = 5, customerName = "João", sellerName = "Maria"))
```
### 🔹 Converters.kt
- Classe responsável por converter listas de objetos em JSON para serem armazenadas no **Room** e vice-versa.
```kotlin
class Converters {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromPdvDeviceList(value: List<PdvDevice>): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toPdvDeviceList(value: String): List<PdvDevice> {
        return json.decodeFromString(value)
    }
....outros converters

```
### 🔹 CheckPadDao.kt
- Define as operações sobre a base de dados (CRUD).
- Exemplos: inserir comandas, listar mesas, pesquisar por ID.
- 
#### Exemplo de uso
val dao = AppDatabase.getDatabase(context).checkPadDao()

```
// Inserir lista de comandas
dao.insertAll(
    listOf(
        CheckPadEntity(id = 1, title = "Mesa 1", customerName = "João", sellerName = "Maria", activity = "Ativo"),
        CheckPadEntity(id = 2, title = "Mesa 2", customerName = "Ana", sellerName = "Pedro", activity = "Fechado")
    )
)

// Obter todas as comandas (UI atualiza automaticamente via Flow)
dao.getAllCheckPads().collect { checkPads ->
    println("Total comandas: ${checkPads.size}")
}

// Filtrar comandas com pesquisa
val results = dao.getFilteredCheckPads(
    pageSize = 10,
    offset = 0,
    searchQuery = "Ana",
    filterQuery = "ativo"
)
```
### 🔹 Entitys.kt
- Contém as entidades (tabelas) da base de dados.
- Cada entidade representa uma mesa, comanda ou outro elemento persistente.
```kotlin
@Entity(tableName = "check_pads", indices = [Index("title", unique = true)])
data class CheckPadEntity(
    @PrimaryKey val id: Int,
    val status: Boolean,
    val hash: String,
    val title: Int,
    val hasPdv: Boolean,
    val customerName: String,
    val sellerName: String,
    val lastOrderCreated: String? = null,
    val hasOrderSheets: Boolean,
    val hasOrder: Boolean,
    val idleTime: Int,
    val activity: String,
    val pdvDevices: List<PdvDevice>,
    val orderSheets: List<OrderSheet>
)
```
### 🔹 TablesPagingSource.kt
- Implementa paginação de dados (usado quando há muitas mesas).
- Permite carregar dados em blocos para otimizar desempenho.

#### Exemplo de uso
```kotlin
val pager = Pager(
    config = PagingConfig(pageSize = 10),
    pagingSourceFactory = {
        TablesPagingSource(
            checkPadDao = dao,
            searchQuery = "João",
            filterQuery = "em atendimento",
            pageSize = 10
        )
    }
)

val flow = pager.flow // Flow<PagingData<CheckPadEntity>>
```
## 📦 Camada **Model**
### 🔹 CheckPads.kt
- Define o modelo de dados que representa uma comanda.
- Usado tanto pela UI como pela camada de persistência.
```kotlin
@Stable
@Serializable
data class CheckPads(
    val id: Int,
    val status: Boolean,
    val hash: String,
    val title: Int,
    val hasPdv: Boolean,
    val lastOrderCreated: String? = null,
    val hasOrderSheets: Boolean,
    val hasOrder: Boolean,
    val idleTime: Int,
    val activity: String,
    val pdvDevices: List<PdvDevice> = emptyList(),
    val orderSheets: List<OrderSheet> = emptyList()
)
```
A tabela CheckPads é a princiapal utilizada através dela é criado diversas outras tabelas:
- OrderSheets
- PdvDevice
- Seller
- User
- Customer
- CustomerAccount

---

## 🎨 Camada **UI**
### 🔹 Theme (Color.kt, Theme.kt, Type.kt)
- Define estilos globais da aplicação (cores, tipografia, tema).
- Fonte Padrão = Poppins

 ### 🔹 Componentes (view/components)
- **ActionCard.kt** → Cartão de ação rápida.
- **BottomMenu.kt** → Menu inferior de navegação.
- **BottomSheetItem.kt** → Item apresentado em bottom sheets.
- **DetailPopUp.kt** → Janela popup com detalhes de uma mesa/comanda.
- **FilterChip.kt** → Botões de filtro de pesquisa.
- **FindBar.kt** → Barra de pesquisa.
- **Header.kt** → Cabeçalho de ecrãs.
- **LazyGridScope.kt** → Extensões para listas em grelha.
- **TableCard.kt** → Cartão representando uma mesa individual.
- **TableCardsList.kt** → Lista de cartões de mesas.

### 🔹 Screens (view/screens)
- **HomeScreen.kt** → Ecrã inicial, com lista de mesas.
- **MapScreen.kt** → Ecrã com representação gráfica (mapa) das mesas.

### 🔹 Navegação (view/ui)
- **AppNavigator.kt** → Define a navegação entre ecrãs (Home ↔ Mapa).
- **utils.kt** → Funções auxiliares para UI.

---

## 🧠 Camada **ViewModel**
### 🔹 TablesViewModel.kt
- Contém a lógica de negócio.
- Faz a ponte entre dados (DAO/Model) e UI.
- Expõe estados e eventos observáveis pela interface.
#### Exemplo de uso
```kotlin
@Composable
fun TablesScreen(viewModel: TablesViewModel) {
    val tables = viewModel.tablesPagingFlow.collectAsLazyPagingItems()
    val filter by viewModel.selectedFilter.collectAsState()

    Column {
        Text("Filtro: $filter")

        LazyColumn {
            items(tables) { table ->
                table?.let {
                    Text("Mesa: ${it.title} | Cliente: ${it.customerName}")
                }
            }
        }
    }
}
```
### 🔹 TablesViewModelFactory.kt
- Fornece instâncias do `TablesViewModel`.
- Necessário para injetar dependências (ex.: DAO, repositório).

#### exemplo de uso
```kotlin
val checkPadDao = AppDatabase.getDatabase(requireContext()).checkPadDao()
val factory = TablesViewModelFactory(requireActivity().application, checkPadDao)
val viewModel = ViewModelProvider(this, factory)[TablesViewModel::class.java]

// RecyclerView com PagingDataAdapter
val adapter = CheckPadsAdapter()
recyclerView.adapter = adapter

lifecycleScope.launchWhenStarted {
    viewModel.tablesPagingFlow.collectLatest { pagingData ->
        adapter.submitData(pagingData)
    }
}

// Atualiza filtro ou pesquisa
viewModel.updateSelectedFilter("Ativas")
viewModel.updateSearchQuery("Cliente X")
```

## 📱 Atividades Principais

### 🔹 MainActivity.kt
- Ponto de entrada da aplicação.
- Configura a interface com Jetpack Compose.
- Define o `setContent` inicial da app.

### 🔹 MyApplication.kt
- Classe `Application` personalizada.
- Inicializa configurações globais (ex.: base de dados, dependências).

---

## 🚀 Fluxo de Funcionamento
1. **MainActivity** inicia a aplicação e carrega a UI inicial.
2. O **AppNavigator** define qual tela deve ser mostrado (Home ou Mapa).
3. O **TablesViewModel** recolhe os dados do **DAO** via **AppDatabase**.
4. Os dados são convertidos em estados reativos que a UI consome.
5. O utilizador interage com componentes (ex.: TableCard, FilterChip), que disparam eventos tratados pelo ViewModel.
6. Alterações são refletidas em tempo real na interface.

## ✨ Exemplos de Uso

### Exibir lista de mesas
```kotlin
fun MapScreen(tablesViewModel: TablesViewModel){
val tables = viewModel.tables.collectAsState()
TableCardsList(tablesViewModel) { checkPad ->
                selectedCheckPad = checkPad
}
}
```

### Navegar para o mapa
```kotlin
AppNavigator.navigate("map")
```

## 📊 Estrutura dos Dados
O projeto utiliza uma base de dados Mock e armazena a um banco de dados local Room

### Principais Tabelas
- CheckPads
- OrderSheet
- Seller
- Customer
-- os dados principais são armazenados em uma Entidade
- CheckpadEntity

## Como executar

### pré-requisito
- Java Development Kit(JDK)
- Android SDK
- Gradle

### Instalação Local

1. instale a IDE
- Android Studio(recomendado) ou outra IDE com suporte:
-- Linguagem Kotlin
-- Integração com o Gradle
-- JetPack Compose

2. **Clone o repositorio**

```bash
git clone https://github.com/Glomes/TablesManagement.git
cd challange-blog-api
```

4. **Build a aplicação**

## 🔄 Fluxo de uso recomendado
1. **Inicie o App** 
2. **Aperte para navegar para o mapa de atendimento** 
3. **utilize os filtros e pesquisas** 
4. **Click nos Items para mais informações**

## Autor
**Lucas Gomes**

- Email: lucasgomesmendes13@gmail.com
- github: https://github.com/Glomes






