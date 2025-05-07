package com.example.korea_sleepTech_springboot.시험.controller;

import com.example.korea_sleepTech_springboot.시험.dto.request.ProductCreateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.request.ProductUpdateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ProductResponseDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.시험.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 1. CREATE
    @PostMapping
    public ResponseEntity<ResponseDto<ProductResponseDto>> createProduct(@RequestBody ProductCreateRequestDto dto){
        try{
            ResponseDto<ProductResponseDto> product = productService.createProduct(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 2. READ(전체)
    @GetMapping
    public ResponseEntity<ResponseDto<List<ProductResponseDto>>> gellAllProducts() {
        ResponseDto<List<ProductResponseDto>> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    // 3. READ(단건)
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ProductResponseDto>> getProductById(@PathVariable Long id){
        ResponseDto<ProductResponseDto> product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    // 4. UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ProductResponseDto>> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequestDto dto){
        try{
            ResponseDto<ProductResponseDto> product = productService.updateProduct(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();

    }
}
