# CountryDetails

O Country Details é um webservice que exibe informações de países obtidas do site da wikipédia.

##1. Manual do desenvolvedor

O sistema foi desenvolvido utilizando o servidor de aplicação Jetty, por isso será necessário apenas certificar que possui o Java 7 ou 8 (jdk) instalado, e que o Path está apontando para o jdk, ou seja, que em linha de comando o javac esteja funcionando em qualquer diretório.
Necessário também que o computador possua acesso a internet.
Depois é só seguir os demais passos:

######1.1 Linux / Windows

<ol>
<li>Descompactar projeto em pasta pessoal</li>
<li>No terminal, localizar pasta do projeto e abrir projeto
	<code>cd CountryDetails</code>
</li>
<li>“subir” projeto
	<code>java -jar jetty-runner.jar country/country.war</code>
</li>
<li>acessar url do projeto
	<code>http://localhost:8080/</code>
</li>
</ol>
	




