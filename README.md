# Desafio

## Como executar
Clone o Projeto para um diretório qualquer em seu computador

```bash
git clone https://github.com/wendersondev/desafio-dev.git
```

Depois navegue até o diretório criado
```bash
cd desafio-dev
```

### Com Docker
É necessário instalar em sua maquina:
- [Docker](https://www.docker.com/)

Execute o comando:
```bash
docker-compose up -d
```

## Como acessar
Uma vez que o projeo esteja rodando corretamente, abra seu navegador e acesso a url
```
http://localhost:9001//swagger-ui.html
```

Uma inteface do Swagger será exibida para que você possa navegar entre os endpoint criados

## Endpoints via curl
- Importação de dados
```bash
curl --location 'http://localhost:9001/v1/cnab/transactions/load' \
--form 'file=@"/home/wenderson/desafio-dev/CNAB.txt"'
```
- Consulta de dados sem o nome da loja
```bash
curl --location 'http://localhost:9001/v1/cnab/transactions?page=0&size=10'
```
- Consulta de dados com o nome da loja
```bash
curl --location 'http://localhost:9001/v1/cnab/transactions?page=0&size=10&name=BAR%20DO%20JO%C3%83O'
```