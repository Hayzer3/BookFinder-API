#  BookFinder API

## Descrição
O **BookFinder API** é um projeto em Java que implementa uma API REST simples para busca e gerenciamento de livros.  
Permite consultar livros da **Open Library API**, salvar livros favoritos em um banco de dados **Oracle** e gerenciar a coleção pessoal.

---

##  Funcionalidades
- Buscar livros na **Open Library API**
- Salvar livros favoritos no banco **Oracle**
- Listar livros salvos
- Excluir livros da coleção

---

##  Estrutura do Projeto
```
com.fiap.bookfinder/
├── config/
│   └── BookFinderHttpServer.java   # servidor HTTP com endpoints REST
├── controller/
│   └── BookController.java         # controlador principal
├── service/
│   └── BookService.java            # integração com API externa
├── repository/
│   └── BookRepository.java         # camada de persistência no banco
├── model/
│   └── Book.java                   # modelo de dados
└── Main.java                       # ponto de entrada do sistema
```

---

##  Tecnologias Utilizadas
- **Java 11**
- **Maven**
- **Oracle Database**
- **HTTP Server nativo do Java**
- **Jackson Databind (JSON)**

---

## ⚙️ Como Executar
1. Configure as credenciais do banco Oracle no `BookRepository`.
2. Compile o projeto com o Maven:
   ```bash
   mvn clean install
   ```
3. Execute a classe `Main` para iniciar o servidor na porta **8080**.

---

##  Endpoints
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| **GET** | `/api/books/search?q=termo` | Busca livros na API externa |
| **GET** | `/api/books/saved` | Lista livros salvos no banco |
| **POST** | `/api/books/save` | Salva um livro na coleção pessoal |
| **DELETE** | `/api/books/delete/{id}` | Remove um livro da coleção |

---

##  Observações
- As requisições podem ser feitas via **Postman**, **Insomnia** ou **curl**.  
- O servidor retorna respostas em formato **JSON**.  
- O Jackson é usado internamente para conversão entre objetos Java e JSON.

---

Projeto acadêmico para demonstração de conceitos de **API REST**, **integração com banco de dados** e **consumo de APIs externas**.
