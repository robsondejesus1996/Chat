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
<p>
O presente trabalho tem como objetivo desenvolver uma aplicação de troca de mensagens(Chat) essa aplicação suporta múltiplos usuários. Logo será desenvolvido aqui uma aplicação estilo Messenger que os usuários<br> ao entrar devem informar o nome, uma porta para a comunicação entre a rede, e o endereço da maquina do servidor. Sobre a comunicação dos clientes ele será feita de modo privado.Será utilizado  uma tecnologia <br> chamada socket (network socket) que seria um ponto final de um fluxo de comunicação entre porcessos através de uma rede de computadores.<br>
</p>


## [Requisitos Não Funcionais](##requisitos)

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

<p>a) Este servidor jamais deve criar conexões com clientes, apenas receber conexões. X</p>
<p>b) Não se deve utilizar nenhuma implementação pronta de qualquer servidor (ex: Express JS). Em
caso de dúvida, consulte o professor.</p>


<b>[RNF3]</b> -  A comunicação pode ser realizada através de (a equipe escolhe a opção desejada):<br>
<p>a) Sockets; ou</p>
<p>b) RMI. Ler seções 5.4 e 5.5 de (COULOURIS, DOLLIMORE, et al., 2013); ou</p>
<p>c) CORBA. Ler seção 8.3 de (COULOURIS, DOLLIMORE, et al., 2013).</p>


<b>[RNF4]</b> -  Se utilizar Sockets no RNF3, então deve-se observar os seguintes requisitos:<br>
<p>a) Sockets ociosos não podem existir (todos devem ser fechados logo após a comunicação).</p>
<p>b) Os dados enviados entre cliente/servidor e cliente/cliente podem estar no formato JSON ou XML.
Não é permitido o uso de serialização de objetos.</p>


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


## [Especificação Mensagens(Cliente/Cliente)](#ClienteCliente)

<h1>Especificações mensagens trocadas entre cliente/cliente</h1>

<ul>
    <b>Inicio chat:</b><br>
    <li>Conteúdo Mensagem: {nome: 'nome', enderecoip: 'enderecoip', porta: 'porta'}</li>
    <li>Descrição: dados enviados contendo informações para o usuário iniciar</li> 
    <li>Return: Nulo</li> 
</ul>

<h2>Diagrama de comunicação para o processo de inicio de chat:</h2>

![1](https://user-images.githubusercontent.com/31260719/152686747-f98b015a-1658-4dd2-a4e5-ccd3d3fa0ad4.png)




<ul>
    <b>Envio de mensagem:</b><br>
    <li>Conteúdo Mensagem: {nome: 'nome', hora: 'horário de envio', mensagem: 'mensagem'}</li>
    <li>Descrição: dados da mensagem sendo enviados para outro usuário</li> 
    <li>Return: Nulo</li> 
</ul>

<h2>Diagrama de comunicação para o processo de envio de mensagem:</h2>

![2](https://user-images.githubusercontent.com/31260719/152686772-c16a064d-9428-4e68-8c40-1ef1f1b459fa.png)



<ul>
    <b>Usuário saindo:</b><br>
    <li>Conteúdo Mensagem: {instrução: 'off'}</li>
    <li>Descrição: finalizar o chat atual</li> 
    <li>Return: Nulo</li> 
</ul>

<h2>Diagrama de comunicação para o processo de saindo do chat:</h2>

![3](https://user-images.githubusercontent.com/31260719/152687774-a98a36af-554b-4d82-92b7-295b4d1bcbd8.png)





## [Especificação Mensagens(Cliente/Servidor)](#ClienteServidor)




<h1>Especificações mensagens trocadas entre cliente/servidor</h1>

<ul>
    <b>Cadastro se usuário no servidor:</b><br>
    <li>Conteúdo Mensagem: {nome: 'nome', enderecoip: 'ip do usuario', porta: 'porta do usuario'}</li>
    <li>Descrição: Cadastro do usuário e conexão ao servidor</li> 
    <li>Return: Nulo</li> 
</ul>

<h2>Diagrama de comunicação para o processo de cadastro no servidor:</h2>

![4](https://user-images.githubusercontent.com/31260719/152687505-2cff70ef-99fd-4860-bbd5-3a1d1823b416.png)



<ul>
    <b>Lista de usuários conectados:</b><br>
    <li>Conteúdo Mensagem: {instrucao:'atualizar',}</li>
    <li>Descrição: Pedido ao servidor todos os usuários conectados</li> 
    <li>Return List-Json: {nome:'nome do usuario, enderecoip:'endereco ip do usuario', porta:'porta'}</li> 
</ul>

<h2>Diagrama de comunicação para o processo de atualização de conectados:</h2>

![5](https://user-images.githubusercontent.com/31260719/152687538-d621825c-9098-4d29-a6a9-93f26f4e686c.png)




<ul>
    <b>Desconexão ou exclusão de usuário:</b><br>
    <li>Conteúdo Mensagem: {instrucao: 'off||excluir', porta: 'porta do usuario'}</li>
    <li>Descrição: desconecta ou deletar usuário do servidor </li> 
    <li>Return: Nulo</li> 
</ul>

<h2>Diagrama de comunicação para o processo de desconexão ou exclusão de usuário:</h2>

![6](https://user-images.githubusercontent.com/31260719/152687581-ffdca173-343a-412b-b7f3-b4ec68f4c6b7.png)




<ul>
    <b>Deletar usuário do servidor:</b><br>
    <li>Conteúdo Mensagem: {instrucao: 'excluir', porta: 'porta do usuario'}</li>
    <li>Descrição: deletar usuário do servidor</li> 
    <li>Return: Nulo</li> 
</ul>

<h2>Diagrama de comunicação para o processo de excluir usuário do servidor:</h2>

![7](https://user-images.githubusercontent.com/31260719/152687645-ef995536-1269-4664-8575-fa15435dc3d0.png)



* [Diagramas](#diagramas)


---

## [Diagramas](#diagramas)
 <h1> Diagrama de classes preliminar </h1>


![diagrama_classes_preliminar](https://user-images.githubusercontent.com/31260719/152685359-0f8597a0-640b-4d73-b556-71fd4460951d.png)
