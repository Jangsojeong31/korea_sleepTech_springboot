package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.OrderLog;
import com.example.korea_sleepTech_springboot.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLog, Long> {
//    Stock findByProduct_Id(Long productId);

}
