package yfu.practice.springboot.specification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import yfu.practice.springboot.specification.entity.OrderDetail;
import yfu.practice.springboot.specification.entity.OrderDetailPk;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, OrderDetailPk>, JpaSpecificationExecutor<OrderDetail> {}
