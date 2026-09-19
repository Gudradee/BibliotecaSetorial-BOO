# BOO - Biblioteca Orientada a Objetos

| | |
|---|---|
| **Aluno** | Gustavo Andrade |
| **Matrícula** | 202508703858 |
| **Domínio** | Biblioteca (Livro → Empréstimos) |

Aplicação Spring Boot 4.1 com API REST, validação, regra de negócio no Service, página Thymeleaf e banco H2 em memória.

## Como rodar

Requisitos: JDK 26 (ou ajuste `java.version` no `pom.xml` para a versão do seu JDK, mínimo 17). O Maven vem embutido pelo wrapper.

```bash
./mvnw spring-boot:run
```

- Página web: http://localhost:8080/livros/pagina
- Console H2: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:biblioteca`, usuário `sa`, senha em branco)

Ao iniciar, o `DataLoader` insere 20 livros e 18 empréstimos.

## Entidades

- **Livro** (principal): `id`, `titulo`, `autor`, `isbn`, `anoPublicacao`, `disponivel`
- **Emprestimo**: `id`, `livro` (`@ManyToOne`, chave estrangeira `livro_id`), `nomeRetirante`, `dataRetirada`, `dataPrevistaDevolucao`, `devolvido`

Um livro tem vários empréstimos. A chave estrangeira fica na tabela `emprestimo`.

## Endpoints

| Verbo | Caminho | Resposta |
|---|---|---|
| POST | `/livros` | 201 com o livro criado, 400 se inválido ou ISBN repetido |
| GET | `/livros` | 200 com a lista |
| GET | `/livros/{id}` | 200 com o livro, ou 404 |
| PUT | `/livros/{id}` | 200 com o livro atualizado, 404 se não existir, 400 se inválido |
| DELETE | `/livros/{id}` | 204 sem corpo, 404 se não existir, 400 se o livro tiver empréstimos |
| GET | `/livros/{id}/emprestimos` | 200 com os empréstimos do livro, ou 404 se o livro não existir |
| POST | `/emprestimos` | 201 com o empréstimo criado, 400 se inválido ou livro inexistente |
| GET | `/emprestimos` | 200 com a lista |
| GET | `/livros/pagina` | Página web com listagem e formulário de cadastro |

## Regras de validação

**Livro**
- `titulo`: obrigatório, no máximo 150 caracteres
- `autor`: obrigatório, no máximo 100 caracteres
- `isbn`: obrigatório, entre 10 e 17 caracteres
- `anoPublicacao`: obrigatório, entre 1450 e 2100

**Emprestimo**
- `livro`: obrigatório
- `nomeRetirante`: obrigatório, no máximo 100 caracteres
- `dataRetirada`: obrigatória, não pode estar no futuro
- `dataPrevistaDevolucao`: obrigatória

Requisição inválida devolve **400** com um JSON `campo → mensagem`, em português.

## Regras de negócio no Service (consultam o banco)

- **ISBN único**: não é permitido cadastrar ou atualizar um livro com ISBN já existente. Mensagem: `Já existe um livro cadastrado com o ISBN ...`
- **Livro do empréstimo deve existir**: `Livro não encontrado: {id}`
- **Data de devolução** não pode ser anterior à de retirada
- **Exclusão protegida**: livro com empréstimos não pode ser excluído

Todas devolvem 400 com `{"erro": "mensagem"}`.

## Exemplos com curl

POST de um livro:

```bash
curl -i -X POST http://localhost:8080/livros \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Dom Casmurro","autor":"Machado de Assis","isbn":"9788535910663","anoPublicacao":1899,"disponivel":true}'
```

GET da lista e de um livro:

```bash
curl -i http://localhost:8080/livros
curl -i http://localhost:8080/livros/1
curl -i http://localhost:8080/livros/1/emprestimos
```

POST inválido (devolve 400 com as mensagens):

```bash
curl -i -X POST http://localhost:8080/livros \
  -H "Content-Type: application/json" \
  -d '{"titulo":"","autor":"","isbn":"12","anoPublicacao":1000}'
```

## Estrutura

```
com.ibmec.biblioteca
├── controller/   LivroController, EmprestimoController, LivroWebController, ManipuladorDeErros
├── model/        Livro, Emprestimo
├── repository/   LivroRepository, EmprestimoRepository
├── service/      LivroService, EmprestimoService
└── DataLoader
```
