package com.example.korea_sleepTech_springboot.시험.repository;

import com.example.korea_sleepTech_springboot.시험.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoy extends JpaRepository<Product, Long> {
}
