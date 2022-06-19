# desafio-sogo

Projeto desenvolvido para solucionar o desafio proposto pela [SOGO](https://sogo.com.br/).

## Requisitos
Para rodar o projeto é necessario ter:
- JDk 8
- maven
- docker

Ao clonar o projeto,na pasta raiz, é executar o _script_ `run.sh`, onde o mesmo se responsabilizara por criar um `.jar` </br>
do projeto e adicionalo na pasta `docker/app`e posteriormente dando `docker-compose up --build -d` para subir os containers. </br>

A aplicacao estará disponivel no link http://localhost:8081/, mas pode se obter uma documentacao em http://localhost:8081/swagger-ui.html#/ </br>

### Autenticacao

<p>Todas as requisições passam pelo filtro exceto as pagaina de:</p>

- [login](http://localhost:8081/login)
- [cadastro](http://localhost:8081/usuarios/cadastrar)
- [swagger-ui](http://localhost:8081/swagger-ui.html#)
- [popular](http://localhost:8081/popular)

Toda a autenticação é atraves do Beared token que é retornado ao realizar o login do usuario por tanto, toda e qualquer</br>
requisição filtrada deve ser enviado no header uma key `Authorization` com a chave `Bearer {chave}` </br>
onde deve substituir o `{chave}` pelo token recebido.

Vale resaltar que apenas o admin tem permissão para ver todos os usuarios cadastros incluindo seus respectivos projetos e tarefas, </br>
mas nem mesmo ele pode visualizar as senhas dos usuarios que estão criptogradas no banco

### Populando o banco

Para facilitar a visualização dos metodos, pode ser enviado uma requisição `POST`em http://localhost:8081/popular para realizar a população do banco de dados.
