package com.se.sample.controller;


import com.se.sample.models.Product;
import com.se.sample.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@Api(value = "spring-fox-swagger", description = "Operations pertaining to products in Online Store")
public class ProductController {


    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "View a list of available products", notes = "notes")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Successfully retrieved products",
                    response = Product.class,
                    responseContainer = "List"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Product> list() {
        Iterable<Product> productList = productService.listAll();
        return productList;
    }

    @ApiOperation(value = "Search a product with an ID", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list" ),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/{id}")
    @ResponseBody
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public Product showProduct(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @ApiOperation(value = "Add a product")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message =  "Successfully created"),
            @ApiResponse(code = 400, message = "Bad request - The product is not valid"),
            @ApiResponse(code = 500, message = "Internal server error - Something went wrong")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveProduct(@RequestBody @Valid Product product) {
        productService.save(product);
        return new ResponseEntity("Product saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a product")
    @ResponseBody
    @ApiResponses(value =
            {@ApiResponse(code = 200, message = "Successfully retrieved product", response = Product.class)
            })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product storedProduct = productService.getById(id);
        storedProduct.setDescription(product.getDescription());
        storedProduct.setImageUrl(product.getImageUrl());
        storedProduct.setPrice(product.getPrice());
        productService.save(storedProduct);
        return storedProduct;
    }

    @ApiOperation(value = "Delete a product")
    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id) {
        productService.delete(id);
        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
    }

    @ApiIgnore
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
