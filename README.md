# Wishlist

## Descrição Geral
Projeto de estudo Spring Boot, Rest, MongoDB e Docker.

## Pré Requisitos
* [Java] 22
* [Docker]

## Acesso ao sistema

Execução via Docker (Maven + Spring):
```
./mvnw spring-boot:run -Pdocker-compose
```

A port default é a 8080.
```
http://localhost:8080/wishlist
```

## Métodos

* Consultar os produtos da Wishlist de um Código de Cliente:
```
(GET) http://localhost:8080/wishlist/{CODIGO_CLIENTE}
```

* Consultar se um Código de Produto existe na Wishlist de um Código de Cliente:
```
(GET) http://localhost:8080/wishlist/{CODIGO_CLIENTE}/{CODIGO_PRODUTO}
```

* Adicionar um Código de Produto na Wishlist de um Código de Cliente:
```
(PUT) http://localhost:8080/wishlist/{CODIGO_CLIENTE}?productId={CODIGO_PRODUTO}
```

* Remover um Código de Produto da Wishlist de um Código de Cliente:
```
(DELETE) http://localhost:8080/wishlist/{CODIGO_CLIENTE}/{CODIGO_PRODUTO}
```

## Contrato de Response

```
{
    "customerId": "CODIGO_CLIENTE",
    "products": [
        {
            "productId": "CODIGO_PRODUTO"
        }
    ]
}
```