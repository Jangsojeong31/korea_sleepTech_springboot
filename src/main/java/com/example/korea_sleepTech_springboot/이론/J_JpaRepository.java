package com.example.korea_sleepTech_springboot.이론;

public class J_JpaRepository {
    // JpaRepository<E, ID>
    // : Spring Data JPA에서 제공하는 기본 인터페이스
    // - CRUD와 페이징, 정렬을 포함한 다양한 데이터 엑세스 메서드를 제공

    // 1. CRUD 메서드
    // 형식) 메서드명(매개변수): 반환타입
    // 1) save(E entity): E
    // - 새로운 엔티티를 저장하거나, 기존 엔티티를 업데이트

    // 2) findById(ID id): E
    // - 주어진 ID에 해당하는 엔티티를 조회

    // 3) existsById(ID id): boolean
    // - 주어진 ID가 존재하는지 확인

    // 4) findAll(): List<E>
    // - DB에 있는 모든 엔티티를 조회

    // 5) deleteById(ID id): void
    // - 주어진 아이디의 엔티티를 삭제

    // cf) E: 엔티티 클래스 타입, DB 테이블과 매핑되는 클래스
    // cf) ID: 엔티티의 기본키(PK)와 매핑되는 데이터

    // 2. 쿼리 메서드
    // : 메서드 이름만으로 JPQL(또는 SQL) 쿼리를 자동 생성하는 기능
    // - 별도 쿼리 작성을 하지 않고 이름 규칙에 따라 Spring Data JPA가 내부에서 해석하여 쿼리를 생성
    
    // cf) JPQL(Java Persistence Query Language): SQL을 기반으로 하는 객체 모델용 쿼리 언어
    
    // 1) 쿼리 메서드 기본 구조
    // - 보통 find, read, get, query 같은 키워드로 시작
    // - 위 키워드 뒤에 By를 붙여 조건을 명시
    
    // ex) 반환타입 findBy필드명(필드타입 필드값);
    
    // 2) 쿼리 메서드 키워드 정리
    /*
    findBy: 조회(SElECT) 시작
    And: AND 조건
    Or: OR 조건
    Between: 값 사이 검색 (BETWEEN A AND B)
    LessThan: 미만
    LessThanEqual: 이하
    GreaterThan: 초과
    GreaterThanEqual: 이상
    
    IsNull, IsNotNull: Null 여부 확인, Not Null 여부 확인
    
    Like, NotLike: SQL LIKE 패턴 매칭, 그 외의 패턴 매칭
    
    StartingWith, EndingWith, Containing: ~로 시작하는, ~로 끝나는, ~을 포함하는
    
    In, NotIn
    True, False
    OrderBy
    */
    
    // 기본 조회
    // Optional<T> findById(Long id);
    // 조건 검색
    // EX1) '가격'이 특정 금액 '이상'인 '상품'을 '조회'
    // : findByPriceGreaterThan(int price)
    // EX2) '이름'에 특정 문자열이 '포함'된 '상품'을 '조회'
    // : findByNameContaining(String keyword)
    // ex3) 카테고리가 일치하고 가격이 특정 범위 안에 있는 상품 조회
    // : findByCategoryAndPriceBetween(String category, int minPrice, int maxPrice)
    
    // cf) @Query: 조건이 복잡하거나 JOIN이 필요한 경우 메서드 이름만으로 한계가 있음, 직접 쿼리 작성 가능
    
}
