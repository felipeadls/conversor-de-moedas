# Conversor de Moedas
Este projeto é um conversor de moedas em Java que utiliza a API ExchangeRate para buscar taxas de câmbio em tempo real, permitindo a conversão entre moedas populares como dólar, euro, real, entre outras.

# Funcionalidades
## Mapeamento de Moedas: 

- O programa inclui um mapeamento que associa os nomes das moedas (como "dolar" ou "euro") aos seus códigos ISO (como "USD" e "EUR"), facilitando a escolha da moeda pelo usuário.
## Conversão de Valores:
- Após escolher a moeda de origem, a moeda de destino e informar o valor, o programa acessa a API para buscar a taxa de câmbio atual e calcula o valor convertido.

# Como usar
Execute o programa no terminal ou ambiente de sua escolha.

# Quando solicitado, digite:
- A moeda de origem (ex.: "dolar").
- A moeda de destino (ex.: "euro").
- O valor numérico que deseja converter (ex.: "1000").

### O programa consulta a taxa de câmbio e exibe o valor convertido.

# Tecnologias e Dependências
- Java 11+: Linguagem principal para execução do código.
- Biblioteca Gson: Usada para analisar e extrair dados JSON da resposta da API.
- API ExchangeRate: Fonte das taxas de câmbio atuais para realizar as conversões.
