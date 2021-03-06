# CountryDetails

O Country Details é um webservice que exibe informações de países obtidas do site da wikipédia.

##1. Manual do usuário

A página inicial é acessada acessando apenas http://localhost:8080/

![alt tag](https://github.com/CaroleneBertoldi/CountryDetails/blob/master/documentacao/tela-01-inicio)

No primeiro acesso nenhum país ainda foi selecionado e ainda não há histórico de buscas de países. O algoritmo de ordenação padrão é o Quicksort.
	
######1.1 Busca de países

No campo “Pesquisar país” é possível iniciar uma busca de um país. 
Ao iniciar a pesquisa aparecerão todas as opções de países para a sequência de caracteres escrita. A busca deve ser realizada sem acentuação, pois os países com acento já são filtrados com busca simples.

![alt tag](https://github.com/CaroleneBertoldi/CountryDetails/blob/master/documentacao/tela-02.png)

As informações do país selecionado são exibidas e o país é adicionado no histórico de buscas.

![alt tag](https://github.com/CaroleneBertoldi/CountryDetails/blob/master/documentacao/tela-03.png)

######1.2. Ordenação do histórico

O histórico é inicialmente exibido em ordem das buscas realizadas, exibindo sempre a ultima busca na primeira linha. E possui opção de ordenação por nome de países.

![alt tag](https://github.com/CaroleneBertoldi/CountryDetails/blob/master/documentacao/tela-04.png)

Ao clicar no ícone para ordenação por nome de país o histórico é ordenado utilizando o algoritmo de ordenação que estiver selecionado e o ícone é modificado exibindo a opção de retirar a ordenação.

![alt tag](https://github.com/CaroleneBertoldi/CountryDetails/blob/master/documentacao/tela-05.png)

O algoritmo de ordenação pode ser selecionado na caixa “Configuração de ordenação”. O histórico é recarregado para utilização do algoritmo selecionado.

![alt tag](https://github.com/CaroleneBertoldi/CountryDetails/blob/master/documentacao/tela-06.png)

######1.3 Detalhes do histórico

Através da listagem dos países exibidos no histórico é possível acessar os detalhes da busca realizada anteriormente.

![alt tag](https://github.com/CaroleneBertoldi/CountryDetails/blob/master/documentacao/tela-07.png)

######1.4. Integridade do histórico

Ao subir o sistema novamente, este é recarregado listando o histórico de navegação do ultimo acesso.

![alt tag](https://github.com/CaroleneBertoldi/CountryDetails/blob/master/documentacao/tela-08.png)

##2. Manual do desenvolvedor

O sistema foi desenvolvido utilizando o servidor de aplicação Jetty, por isso será necessário apenas certificar que possui o Java 7 ou 8 (jdk) instalado, e que o Path está apontando para o jdk, ou seja, que em linha de comando o javac esteja funcionando em qualquer diretório.
Necessário também que o computador possua acesso a internet.
Depois é só seguir os demais passos:

######2.1 Linux / Windows

<ol>
<li>Descompactar projeto em pasta pessoal</li>
<li>No terminal, localizar pasta do projeto e abrir projeto
	<code>cd CountryDetails/country</code>
</li>
<li>Realizar deploy
	<code>mvn clean install</code>
</li>
<li>Voltar a pasta anterior e “subir” projeto
	<code>java -jar jetty-runner.jar country/target/country.war</code>
</li>
<li>acessar url do projeto
	<code>http://localhost:8080/</code>
</li>
</ol>

##3. Diagrama de caso de uso

![alt tag](https://github.com/CaroleneBertoldi/CountryDetails/blob/master/documentacao/caso_de_uso.png)

######Atores:

A1 Usuário - pessoa que ira interagir com o sistema por interface gráfica.

######Casos de uso:

C1 “Seleciona país a ser pesquisado” -  o usuário seleciona o país a ser pesquisado via autocomplete.

C2 “Escolher algoritmo de ordenação” - o usuário escolhe algoritmo de ordenação que o sistema utilizará.

C3 “Escolhe ordenação de histórico por nome de país ou sem ordenação” - o usuário escolhe se deixará o sistema ordenado por data e hora decrescente, ou seja, os últimos países buscados sendo exibidos primeiro, ou se deixará o histórico ordenado por nome de país em ordem alfabética. 

C4 “Seleciona país do histórico” - os países listados no histórico possuem link para os detalhes de cada país.




