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
-Listagem de mesas: Exibição de todas as mesas, indicando seu status (ocupada, livre, reservada, etc.).
-Pesquisa avançada: Busca por nome do cliente, número da mesa e nome do atendente.
-Filtros das mesas: Opções para filtrar mesas por visão geral, em atendimento, ocupadas e ociosas.
-Mostrar informações da mesa e das comandas na listagem: Implementar a visualização rápida de quem está atendendo a mesa, o nome do cliente, quantidade de comandas, há quanto tempo está sem fazer novo pedido, etc.

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




