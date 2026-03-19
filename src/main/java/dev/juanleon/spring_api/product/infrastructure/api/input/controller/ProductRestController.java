package dev.juanleon.spring_api.product.infrastructure.api.input.controller;

import dev.juanleon.spring_api.common.mediator.Mediator;
import dev.juanleon.spring_api.product.application.commands.delete.DeleteProductCommand;
import dev.juanleon.spring_api.product.application.commands.post.SaveProductCommand;
import dev.juanleon.spring_api.product.application.commands.update.UpdateProductCommand;
import dev.juanleon.spring_api.product.application.dto.RequestProduct;
import dev.juanleon.spring_api.product.application.dto.ResponseProduct;
import dev.juanleon.spring_api.common.utils.dto.ResponseRequest;
import dev.juanleon.spring_api.product.application.queries.GetAllProductsQuery;
import dev.juanleon.spring_api.product.application.queries.GetByIdProductQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Product", description = "Product API operation")
@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final Mediator mediator;

    public ProductRestController(Mediator mediator) {
        this.mediator = mediator;
    }

    @Operation(summary = "Get all products", description = "obtiene todos los productos en una List<>")
    @GetMapping
    public ResponseEntity<List<ResponseProduct>> getAll() {
        GetAllProductsQuery query = new GetAllProductsQuery();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.mediator.dispatch(query));
    }

    @Operation(summary = "Get product by id", description = "obtiene un ResponseProduct por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseProduct> getById(@PathVariable("id") UUID id) {
        GetByIdProductQuery query = new GetByIdProductQuery(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.mediator.dispatch(query));
    }

    @Operation(summary = "create requestProduct", description = "crea o guarda un requestProduct")
    @PostMapping
    public ResponseEntity<ResponseRequest> save(@Valid @ModelAttribute RequestProduct requestProduct) {
        SaveProductCommand command = new SaveProductCommand(requestProduct);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.mediator.dispatch(command));
    }

    @Operation(summary = "Update product by id", description = "actualiza un product por ID")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseRequest> updateById(@Valid @PathVariable("id") UUID id, @Valid @RequestBody RequestProduct requestProduct) {
        UpdateProductCommand command = new UpdateProductCommand(id, requestProduct);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.mediator.dispatch(command));
    }

    @Operation(summary = "delete product by id", description = "elimina un product por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseRequest> deleteById(@Valid @PathVariable("id") UUID id) {
        DeleteProductCommand command = new DeleteProductCommand(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.mediator.dispatch(command));
    }
}
