package com.leonardocbotelho.wishlist.application.controller;

import com.leonardocbotelho.wishlist.infrastructure.database.entity.WishlistEntity;
import com.leonardocbotelho.wishlist.infrastructure.database.repository.WishlistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WishlistControllerTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @Autowired
    WishlistRepository repository;

    @Test
    void givenOneCustomerWithFiveProductsThatExists_whenGetCustomer_thenReturnsResponseOK() {
        final var entity = new WishlistEntity("customer001");
        entity.getProducts().add(new WishlistEntity.ProductEntity("product001"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product002"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product003"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product004"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product005"));
        repository.save(entity);

        when().get("/wishlist/customer001")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("customerId", equalTo("customer001"))
                .body("products.size()", equalTo(5))
                .body("products[0].productId", equalTo("product001"))
                .body("products[1].productId", equalTo("product002"))
                .body("products[2].productId", equalTo("product003"))
                .body("products[3].productId", equalTo("product004"))
                .body("products[4].productId", equalTo("product005"));
    }

    @Test
    void givenOneCustomerThatDoNotExists_whenGetCustomer_thenReturnsResponseNotFound() {
        when().get("/wishlist/customer999")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void givenACustomerIdAndAProductId_whenPut_thenReturnResponseOK() {
        when().put("/wishlist/customer101?productId=product101")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("customerId", equalTo("customer101"))
                .body("products.size()", equalTo(1))
                .body("products[0].productId", equalTo("product101"));
    }

    @Test
    void givenOneCustomerWithOneProductThatExists_whenPutTheSameProduct_thenReturnResponseOK() {
        final var entity = new WishlistEntity("customer102");
        entity.getProducts().add(new WishlistEntity.ProductEntity("product102"));
        repository.save(entity);

        when().put("/wishlist/customer102?productId=product102")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("customerId", equalTo("customer102"))
                .body("products.size()", equalTo(1))
                .body("products[0].productId", equalTo("product102"));
    }

    @Test
    void givenOneCustomerWithTwentyProductsThatExists_whenPutANewProduct_thenReturnResponseNotAcceptable() {
        final var entity = new WishlistEntity("customer103");
        entity.getProducts().add(new WishlistEntity.ProductEntity("product101"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product102"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product103"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product104"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product105"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product106"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product107"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product108"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product109"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product110"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product111"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product112"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product113"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product114"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product115"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product116"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product117"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product118"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product119"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product120"));
        repository.save(entity);

        when().put("/wishlist/customer103?productId=product121")
                .then()
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value());
    }

    @Test
    void givenOneCustomerWithFiveProductsThatExists_whenDeleteOneProduct_thenReturnsResponseOK() {
        final var entity = new WishlistEntity("customer201");
        entity.getProducts().add(new WishlistEntity.ProductEntity("product201"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product202"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product203"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product204"));
        entity.getProducts().add(new WishlistEntity.ProductEntity("product205"));
        repository.save(entity);

        when().delete("/wishlist/customer201/product201")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("customerId", equalTo("customer201"))
                .body("products.size()", equalTo(4))
                .body("products[0].productId", equalTo("product202"))
                .body("products[1].productId", equalTo("product203"))
                .body("products[2].productId", equalTo("product204"))
                .body("products[3].productId", equalTo("product205"));
    }

    @Test
    void givenOneCustomerWithOneProductThatExists_whenDeleteTheSameProduct_thenReturnResponseNoContent() {
        final var entity = new WishlistEntity("customer202");
        entity.getProducts().add(new WishlistEntity.ProductEntity("product202"));
        repository.save(entity);

        when().delete("/wishlist/customer202/product202")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void givenOneCustomerWithOneProductThatDoNotExists_whenDeleteTheSameProduct_thenReturnResponseNoContent() {
        when().delete("/wishlist/customer302/product302")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void givenOneCustomerWithOneProductThatExists_whenGetCustomerWithProduct_thenReturnResponseOK() {
        final var entity = new WishlistEntity("customer401");
        entity.getProducts().add(new WishlistEntity.ProductEntity("product401"));
        repository.save(entity);

        when().get("/wishlist/customer401/product401")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void givenOneCustomerWithOneProductThatDotNotExists_whenGetCustomerWithProduct_thenReturnResponseNotFound() {
        when().get("/wishlist/customer402/product402")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}
