# musical-advisor

## Instruções de Execução

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

## Considerações de Design

Este projeto foi baseado em princípios de DDD (Domain-Driven Design), CQRS (Command Query Responsibility Segregation) e, principalmente, Orientação a Objetos. Deste modo, trabalhei com os seguintes objetivos:

1. Dar ênfase ao design do Domain Model, isto é, às entidades do sistema e seus *comportamentos* em vez de apenas seus *dados*.

2. Dar ênfase ao aspecto de testabilidade e interpretação de código, utilizando nomes claros para a nomeação de classes, métodos, variáveis, etc., separando funcionalidades do sistema em pequenos métodos os mais genéricos possíveis, fazendo uso do conceito de injeção de dependências por meio de construtores (protegendo assim as invariantes das classes), usando interfaces e polimorfismo abundantemente, etc.

3. Dar ênfase a padrões de design que permitam ao sistema uma evolução consistente e correta, tais quais a separação de representações REST de entidades do domínio, -- representações essas modeladas como "View Objects", que a princípio podem parecer redundantes mas permitem a evolução das entidades independentemente da API externa do sistema, a implementação de uma camada anti-corrupção para conversão de View Objects em entidade do domínio e vice-versa, etc.

4. Dar ênfase à escabilidade, performance, tolerância a falhas e monitoramento da solução, utilizando Spring WebFlux e o paradigma reativo para obter um modelo non-blocking capaz de suportar muito mais acessos concorrentes, aplicando o cacheamento temporário (~10 segundos, como prova de conceito) de respostas recebidas dos serviços externos para aumentar significativamente a performance de novas buscas com critérios usados recentemente, aplicando o pattern de circuit breaker por meio da biblioteca Hystrix para lidar com falhas no acesso a serviços remotos, ativando os recursos de monitoramento do back-end por meio do módulo Spring Boot Actuator, etc.

## Possíveis Melhorias

Este projeto foi desenvolvido em pouquíssimo tempo por diversas razões. Todavia, seu design deixa claro algumas intenções e possibilidades para o futuro, como por exemplo

1. Implementação de testes funcionais end-to-end a partir dos endpoints REST;
2. Implementação do requisito HATEOAS para APIs REST;
3. Implementação de documentação/SDK baseada em Swagger (OpenAPI 3.0);
4. Implementação de internacionalização de mensagens de erro;
5. Definição de um conjunto de sugestões a ser retornado quando não forem encontrados dados acerca da cidade buscada ou quando ocorrer algum erro na comunicação com os serviços remotos.


## Endpoints REST

### advisor

#### GET
##### http://localhost:8080/advisor?cityName={String}&latitude={Double}&longitude={Double}

Retorna recomendações musicais baseadas no nome da cidade OU das coordenadas geográficas informadas (caso *cityName* não seja informado, *latitude* e *longitude* se tornam obrigatórios; e caso os 3 campos sejam informados, serão consideradas as coordenadas geográficas para fins de busca/recomendação)
