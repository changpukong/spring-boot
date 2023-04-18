package yfu.practice.springboot.specification;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import yfu.practice.springboot.specification.entity.Book;
import yfu.practice.springboot.specification.entity.OrderDetail;
import yfu.practice.springboot.specification.entity.Publisher;
import yfu.practice.springboot.specification.repository.BookRepo;

@Service
public class SpecificationSvc {

  @Autowired private BookRepo bookRepo;

  @Autowired private EntityManager em;

  /**
   * 以出版日期查詢Book，並以書價降冪排序
   *
   * @param start
   * @param end
   */
  public void findByPublicationDateBetweenOrderByPriceDesc(LocalDate start, LocalDate end) {
    Specification<Book> spec =
        (root, query, builder) -> {
          List<Predicate> conditions = new ArrayList<>();
          conditions.add(
              builder.between(root.get("publicationDate"), Date.valueOf(start), Date.valueOf(end)));

          query.orderBy(builder.desc(root.get("price")));
          return builder.and(conditions.toArray(new Predicate[0]));
        };

    bookRepo.findAll(spec).forEach(System.out::println);
  }

  /**
   * 統計指定訂單的訂購總量
   *
   * @param orderIds
   */
  public void findQuantityGroupByOrderId(Integer... orderIds) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Tuple> query = builder.createTupleQuery();
    Root<OrderDetail> root = query.from(OrderDetail.class);

    query.multiselect(root.get("orderId"), builder.sum(root.get("quantity")));
    query.where(root.get("orderId").in(orderIds));
    query.groupBy(root.get("orderId"));

    List<Tuple> list = em.createQuery(query).getResultList();
    list.forEach(e -> System.out.println(e.get(0, Integer.class) + ":" + e.get(1, Long.class)));
  }

  /**
   * 統計各出版社的訂購總量，並篩選出指定數量以上的資料
   *
   * @param quantity
   */
  public void findQuantityGroupByPublisherIdHavingQuantity(long quantity) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Tuple> query = builder.createTupleQuery();
    Root<Publisher> root = query.from(Publisher.class);

    Expression<Long> sum = builder.sum(root.join("books").join("orderDetails").get("quantity"));

    query.multiselect(root.get("publisherId"), root.get("publisherName"), sum);
    query.groupBy(root.get("publisherId"), root.get("publisherName"));
    query.having(builder.greaterThanOrEqualTo(sum, quantity));

    List<Tuple> list = em.createQuery(query).getResultList();
    list.forEach(
        e ->
            System.out.println(
                e.get(0, String.class)
                    + ":"
                    + e.get(1, String.class)
                    + ":"
                    + e.get(2, Long.class)));
  }

  /** 找出未被訂購的書籍 */
  public void findByNotExistsOrderDetail() {
    Specification<Book> spec =
        (root, query, builder) -> {
          Subquery<OrderDetail> subQuery = query.subquery(OrderDetail.class);
          Root<OrderDetail> subRoot = subQuery.from(OrderDetail.class);
          subQuery.select(subRoot.get("orderId"));
          subQuery.where(builder.equal(root.get("isbn"), subRoot.get("isbn")));

          List<Predicate> conditions = new ArrayList<>();
          conditions.add(builder.not(builder.exists(subQuery)));
          return builder.and(conditions.toArray(new Predicate[0]));
        };

    bookRepo.findAll(spec).forEach(System.out::println);
  }
}
