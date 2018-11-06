# musical-advisor

## Docker

Para executar o servidor, rode

```
gradlew.bat jibDockerBuild
```
para criar a imagem Docker, e depois rode

```
docker-composer up
```
e a API RESTful do back-end estará disponível no caminho http://localhost:8080/advisor.
  
Obs.: Todos os testes foram realizados em ambiente Windows 10 Pro (Build 1809) com Oracle JDK 11 e Docker 18.06.1-ce-win73.

## Considerações de design

Este projeto foi baseado em princípios de DDD (Domain-Driven Design), CQRS (Command Query Responsibility Segregation) e, principalmente, Orientação a Objetos. Deste modo, trabalhei com os seguintes objetivos:

1. Dar ênfase ao design do Domain Model, isto é, às entidades do sistema e seus *comportamentos* em vez de apenas seus *dados*.

2. Dar ênfase ao aspecto de testabilidade e interpretação de código, utilizando nomes claros para a nomeação de classes, métodos, variáveis, etc., separando funcionalidades do sistema em pequenos métodos os mais genéricos possíveis, fazendo uso do conceito de injeção de dependências por meio de construtores (protegendo assim as invariantes das classes), usando interfaces e polimorfismo abundantemente, etc.

3. Dar ênfase a padrões de design que permitam ao sistema uma evolução consistente e correta, tais quais a separação de representações REST de entidades do domínio, -- representações essas modeladas como "View Objects", que a princípio podem parecer redundantes mas permitem a evolução das entidades independentemente da API externa do sistema, -- etc.

## Possíveis melhorias

Este projeto foi desenvolvido em pouquíssimo tempo por diversas razões. Todavia, seu design deixa claro algumas intenções e possibilidades para o futuro, como por exemplo

1. Implementação de testes funcionais end-to-end a partir dos endpoints REST;
2. Implementação do requisito HATEOAS para APIs REST;
3. Implementação de documentação/SDK baseada em Swagger (OpenAPI 3.0);
4. Implementação de internacionalização de mensagens de erro.


## Endpoints REST

### advisor

#### GET
##### http://localhost:8080/advisor?cityName={cityName}&latitude={latitude}&longitude={longitude}

Retorna recomendações musicais baseadas no nome da cidade OU das coordenadas geográficas informadas (caso *cityName* não seja informado, *latitude* e *longitude* se tornam obrigatórios; e caso os 3 campos sejam informados, serão consideradas as coordenadas geográficas para fins de busca/recomendação)
