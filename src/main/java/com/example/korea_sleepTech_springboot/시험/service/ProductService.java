package com.example.korea_sleepTech_springboot.시험.service;

import com.example.korea_sleepTech_springboot.시험.dto.request.ProductCreateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.request.ProductUpdateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ProductResponseDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.시험.entity.Product;
import com.example.korea_sleepTech_springboot.시험.repository.ProductRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepositoy productRepositoy;

    public ResponseDto<ProductResponseDto> createProduct(ProductCreateRequestDto requestDto) {
        try{
            Product newProduct = new Product(null, requestDto.getName(), requestDto.getDescription()
                                ,requestDto.getPrice(), null, null);
            Product savedProduct = productRepositoy.save(newProduct);

            ProductResponseDto response = new ProductResponseDto(
                    savedProduct.getId(),
                    savedProduct.getName(),
                    savedProduct.getDescription(),
                    savedProduct.getPrice()
            );

            return ResponseDto.setSuccess("새로운 제품이 정상적으로 등록되었습니다.", response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("제품 등록 중 오류가 발생하였습니다." + e.getMessage());
        }
    }

    public ResponseDto<List<ProductResponseDto>> getAllProducts() {
        try{
            List<Product> products = productRepositoy.findAll();

            List<ProductResponseDto> responseDtos= products.stream()
                    .map(product -> new ProductResponseDto(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice()
                    ))
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess("전체 제품이 조회되었습니다.", responseDtos);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("전체 제품 조회 중 오류가 발생하였습니다." + e.getMessage());
        }
    }
    public ResponseDto<ProductResponseDto> getProductById(Long id) {
        try{
            Product product = productRepositoy.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id의 제품을 찾을 수 없습니다."));

            ProductResponseDto responseDto = new ProductResponseDto(
                   product.getId(),
                   product.getName(),
                   product.getDescription(),
                   product.getPrice()
            );

            return ResponseDto.setSuccess("해당 id의 제품이 조회되었습니다.", responseDto);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("해당 id의 제품 조회 중 오류가 발생하였습니다." + e.getMessage());
        }
    }

    public ResponseDto<ProductResponseDto> updateProduct(Long id, ProductUpdateRequestDto dto) {
        try {
            Product product = productRepositoy.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id의 제품을 찾을 수 없습니다."));

            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());

            Product updatedProduct = productRepositoy.save(product);

            ProductResponseDto response = new ProductResponseDto(
                    updatedProduct.getId(),
                    updatedProduct.getName(),
                    updatedProduct.getDescription(),
                    updatedProduct.getPrice()
            );

            return ResponseDto.setSuccess("제품이 수정되었습니다.", response);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("제품 수정 중 오류가 발생하였습니다." + e.getMessage());
        }
    }

    public void deleteProduct(Long id) {
        try{
            Product product = productRepositoy.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id의 제품을 찾을 수 없습니다."));

            productRepositoy.delete(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
