package com.company.mockAnimeStore.api;

import com.company.mockAnimeStore.models.Product;
import com.company.mockAnimeStore.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
//@RequestMapping("/products")
//public class ProductController {
//    private ProductRepository repository;
//
//    public ProductController(ProductRepository repository) {
//        this.repository = repository;
//    }

//    @GetMapping("")
//    public List<Product> getAllProducts(){
//        return repository.findAll();
//    }

//    @GetMapping("{name}")
//    public Mono<Product> getProduct(@PathVariable String name){
//        return Mono.just(repository.findByName(name));
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Mono<Product> saveProduct(@RequestBody Product product) {
//        return repository.save(product);
//    }
//}


//WEBFLUX CONTROLLER
// TODO make this client and rest
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Flux<Product> getAllProducts(){
        return repository.findAll();
    }
//
//    @GetMapping("{id}")
//    public Mono<ResponseEntity<Product>> getProduct(@PathVariable String id){
//        return repository.findById(id)
//                .map(product -> ResponseEntity.ok(product))
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }

    @GetMapping("{name}")
    public Mono<Product> getProduct(@PathVariable String name){
        return repository.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> saveProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable(value = "id") String id, @RequestBody Product product) {
        return repository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    return repository.save(existingProduct);
                })
                .map(updateProduct -> ResponseEntity.ok(updateProduct))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable(value = "id") String id) {
        return repository.findById(id)
                .flatMap(existingProduct ->
                        repository.delete(existingProduct)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllProducts() {
        return repository.deleteAll();
    }
}