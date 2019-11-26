from socket import *
from datetime import date
import json
import os

#serverName = 'localhost'
#serverPort = 12000

#clientSocket = socket(AF_INET, SOCK_STREAM)

#three-way handshaking
#clientSocket.connect((serverName, serverPort))
#sentence = input('digite a msg para ser enviada:')

#sentence = '{"titulo": "Musculação", "conteudo": "Sem dor, sem ganho!"}\n'


##########################################################################################

class Client:
	def __init__(self, serverHost, serverPort):
		self.serverHost = serverHost
		self.serverPort = serverPort

	def connect(self):
		self.clientSocket = socket(AF_INET, SOCK_STREAM)
		self.clientSocket.connect((self.serverHost, self.serverPort))

	def closed(self):
		self.clientSocket.close()

	def sendResquest(self, objeto):
		self.clientSocket.send(objeto.encode())

	def received(self):
		receivedSentence = self.clientSocket.recv(4000)
		return receivedSentence.decode()

	def menu(self):
		print("========== EDITOR DE TEXTO ==========")
		print("1 - Listar textos")
		print("2 - Criar texto")
		print("3 - Editar texto")
		print("4 - Info texto")
		print("5 - Deletar texto")
		print("6 - Sair do editor")

	def listarTextos(self):
		print("========== TEXTOS ==========")
		sentence = {"id": "um"}
		objeto = str(sentence)+"\n"
		self.sendResquest(objeto)

	def criarTexto(self):
		print("========== CRIAR TEXTO ==========")
		titulo = input("Digite o titulo do texto: ")
		autor = input("Digite o nome do autor: ")
		conteudo = input("Digite o conteudo do texto: ")
		data = date.today()
		data = str(data)
		objeto = {"id": "dois", "titulo": titulo, "autor": autor, "conteudo": conteudo, "data": data}

		objeto = str(objeto)+"\n"
		#clientSocket.send(objeto.encode())
		self.sendResquest(objeto)

	def editarTexto(self):
		print("========== EDITAR TEXTO ==========")
		titulo = input("Digite o titulo do texto: ")

		info = []
		arquivo = open("textos/"+titulo+".txt", "r")
		for linha in arquivo:
			linha = linha.strip()
			info.append(linha)
		arquivo.close()

		sentence = "{"
		for i in info:
			sentence = sentence+i+', '
			print(i)

		chave = input("O que você deseja editar? ")
		valor = input("Digite o novo valor do campo: ")
		sentence = sentence+"chave: "+chave+", valor: "+valor+", id: tres}"
		dados = str(sentence)+"\n"
		#clientSocket.send(dados.encode())
		self.sendResquest(dados)

	def infoTexto(self):
		print("========== INFO TEXTO ==========")
		titulo = input("Digite o titulo do texto: ")
		sentence = {"id": "quatro", "titulo": titulo}
		dados = str(sentence)+"\n"
		self.sendResquest(dados)

	def deletarTexto(self):
		print("========== DELETAR TEXTO ==========")
		titulo = input("Digite o titulo do texto: ")

		info = []
		arquivo = open("textos/"+titulo+".txt", "r")
		for linha in arquivo:
			linha = linha.strip()
			info.append(linha)
		arquivo.close()

		for i in info:
			print(i)

		sentence = {"id": "cinco", "titulo": titulo}
		dados = str(sentence)+"\n"

		#clientSocket.send(dados.encode())
		self.sendResquest(dados)

	def fecharApp(self):
		sentence = {"id": "seis", "msg": "sair"}
		dados = str(sentence)+"\n"
		self.sendResquest(dados)

sair = True

while sair:
	cliente = Client(serverHost="localhost", serverPort=12000)
	cliente.connect()
	cliente.menu()
	resposta = ''

	op = input("\nDigite a operação: ")
	print("\n")

	if(op == "1"):
		cliente.listarTextos()
		resposta = cliente.received()

	elif(op == "2"):
		cliente.criarTexto()
		resposta = cliente.received()

	elif(op == "3"):
		cliente.editarTexto()
		resposta = cliente.received()

	elif(op == "4"):
		cliente.infoTexto()
		resposta = cliente.received()

	elif(op == "5"):
		cliente.deletarTexto()
		resposta = cliente.received()

	elif(op == "6"):
		sair = False
		cliente.fecharApp()
		resposta = cliente.received()
		cliente.closed()


	# não precisa indica ip e porta porque isso já 
	# foi decidido no estabelecimento de conexão

	#receivedSentence = clientSocket.recv(4000)
	#resposta = cliente.received()

	print('From Server: ', resposta)
#clientSocket.close()
