Jogo de Jokenpo através de APIs REST.


No jogo é permitido mais de dois jogadores, tendo como resultado um jogador vencedor ou empate.

## Desenvolvido com:

* 	[Grandle](https://gradle.org/) - Gerenciador de dependência
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework para desenvolvimento em Java
* 	[JUnit 5](https://junit.org/junit5/) - Framework utilizado para testes
* 	[GIT](https://git-scm.com/) - Versionador de código

### URLs

|  URL |  Method | Remarks |
|----------|--------------|--------------|
|`http://localhost:5000/jogadas`                           | GET | Lista de jogadores cadastrados|
|`http://localhost:5000/jogadadas`                       | POST | Cadastro do jogador |
|`http://localhost:5000/jogadas/{jogador}`                 | GET | Consulta de um jogador |
|`http://localhost:5000/jogadas/{jogador}` | DELETE | Remoção de um jogador |
|`http://localhost:5000/jogadas/jogar`                             | POST | Processa jogo e retorna resultado |


### Body
```
{
  "jogador": "nome unico",
  "acao": "acao" //Spock, Tesoura, Papel, Pedra e Largato
}
```
