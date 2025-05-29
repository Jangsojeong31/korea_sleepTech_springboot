package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // 뷰를 직접 조회
    @Query(value = "SELECT * FROM order_summary WHERE order_id = :orderId", nativeQuery = true)
    List<Object[]> getOrderSummary(@Param("orderId") Long orderId);

    // Projection 활용
//    @Query(value = """
//        SELECT order_id, user_id, product_name, quantity, price, total_price
//        FROM order_summary
//        WHERE order_id = :orderId
//        """, nativeQuery = true)
//    List<OrderSummaryProjection> getOrderSummary(@Param("orderId") Long orderId);
//
//    public interface OrderSummaryProjection {
//        // getter 메서드명은 SQL에서 alias 한 컬럼명과 정확히 일치해야 함
//        // >> 뷰에서 제공하는 이름이 Projection 인터페이스와 같으면 자동 매핑
//        Long getOrderId();
//        Long getUserId();
//        String getProductName();
//        Integer getQuantity();
//        Integer getPrice();
//        Integer getTotalPrice();
//    }
}
