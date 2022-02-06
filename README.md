<!-- Visualizador online: https://stackedit.io/ -->
 ![Logo da UDESC Alto Vale](http://www1.udesc.br/imagens/id_submenu/2019/marca_alto_vale_horizontal_assinatura_rgb_01.jpg)

---


Trabalho realizado para a disciplina de Desenvolvimento de Sistemas Paralelos e Distribuídos do [Centro de Educação Superior do Alto Vale do Itajaí (CEAVI/UDESC)](https://www.udesc.br/ceavi)<br>O objetivo do trabalho é implementar e praticar o desenvolvimento de sistemas concorrentes e distribuídos através da implementação de um sistema distribuído de livre escolha pelos alunos.



# Sumário 
* [Equipe](#equipe)

* [Descrição e Requisitos Funcionais](#descricao)

* [Requisitos Não Funcionais](#requisitos)

* [Regras de Negócio](#regras)

* [Especificação Mensagens(Cliente/Servidor)](#ClienteServidor)

* [Especificação Mensagens(Cliente/Cliente)](#ClienteCliente)

* [Diagramas](#diagramas)


---

## [Equipe](#equipe)
 - [**Robson de Jesus**](mailto:robsondejesus1996@hotmail.com) - [robsondejesus1996](https://github.com/robsondejesus1996)


---

## [Descrição e Requisitos Funcionais](#descricao)

<h1>Descrição</h1>
O trabalho tem como objetivo desenvolver uma aplicação Servivor/Cliente e Cliete/Cliente P2P para a troca de mensagens. Logo será desenvolvido aqui uma aplicação estilo Messenger que os usuários ao entrar<br>
devem informar o nome e uma porta para a comunicação entre as redes. Será utilizado  uma tecnologia chamada socket (network socket) que seria um ponto final de um fluxo de comunicação entre porcessos<br>
através de uma rede de computadores.<br>

<h1>Requisitos Funcionais</h1>
<b>[RF01]</b> - O sistema deve permitir a verificação do usuário ao fazer o login (Porta, endereço) <br>
<b>[RF02]</b> – O sistema deve permitir o envio de mensagens entre os usuários, a comunicação será direta entre cada usuário, logo será de forma privada. <br>
<b>[RF03]</b> - O sistema deve ter um mecanismo para retorna um erro de execução se o Server não estiver em execução. <br>
<b>[RF04]</b> - O sistema deve ter em cada tela ao entrar no sistema o nome especificado do cliente que acabou de logar no sistema. <br>
<b>[RF05]</b> - O sistema deve ter um botão para sempre atualizar a lista de usuários onlines no momento. Assim vamos conseguir ter a informação do nome e a rede que esse usuário está. <br>
<b>[RF06]</b> - O sistema ao iniciar uma conversa com outro usuário deve abrir duas janelas de chat, uma para quem inicio a conversa e outra para quem está sendo solicitado no momento da conversa. <br>



---

## [Requisitos Não Funcionais](##requisitos)

<h1>Requisitos são funcionais</h1>

<b>[RNF1]</b> -  Deve ser distribuído e executar simultanamente em no mínimo 3 clientes (hosts) diferentes.<br>

<b>[RNF2]</b> -  Se necessário, pode ser implementado um servidor para ser acessado pelos clientes.<br>

<p>a) Este servidor jamais deve criar conexões com clientes, apenas receber conexões.</p>
b) Não se deve utilizar nenhuma implementação pronta de qualquer servidor (ex: Express JS). Em
caso de dúvida, consulte o professor.


<b>[RNF3]</b> -  A comunicação pode ser realizada através de (a equipe escolhe a opção desejada):<br>
a) Sockets; ou
b) RMI. Ler seções 5.4 e 5.5 de (COULOURIS, DOLLIMORE, et al., 2013); ou
c) CORBA. Ler seção 8.3 de (COULOURIS, DOLLIMORE, et al., 2013).


<b>[RNF4]</b> -  Se utilizar Sockets no RNF3, então deve-se observar os seguintes requisitos:<br>
a) Sockets ociosos não podem existir (todos devem ser fechados logo após a comunicação).
b) Os dados enviados entre cliente/servidor e cliente/cliente podem estar no formato JSON ou XML.
Não é permitido o uso de serialização de objetos.


<b>[RNF5]</b> -  A aplicação cliente deve ter interface gráfica.<br>

a) A equipe pode utilizar interface gráfica disponível em outros projetos, citando a fonte/projeto.
MAS ATENÇÃO: o projeto utilizado como base não pode ser distribuído (não pode já ter
comunicação entre clientes/servidores)




## [Regras de Negócio](#regras)

<h1>Regras de Negócio</h1>

<b>[RN-01]</b> - Tela inicial de cadastro deve informar os campos de nome, porta de acesso e endereço de acesso ao servidor<br>
<b>[RN-02]</b> - Na tela de cadastro para entrar no sistema deve informar um pequeno tutorial para entrar no sistema <br>
<b>[RN-03]</b> - Na tela de opções de usuários deve ter uma lista de usuários conectados, com os nomes deles com o seu respectivo ip, e também dois botões uma para iniciar a conversa e outrar para atualizar a lista de usuários onlines <br>
<b>[RN-04]</b> - Sobre a atualização de usuários removidos, se um usuário encerrar a aplicação, o servidor deverá removê-lo da lista de usuários conectados<br>


---
## [Especificação Mensagens(Cliente/Servidor)](#ClienteServidor)





---


## [Especificação Mensagens(Cliente/Cliente)](#ClienteCliente)




