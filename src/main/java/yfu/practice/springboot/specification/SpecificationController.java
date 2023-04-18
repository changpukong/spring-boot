package yfu.practice.springboot.specification;

import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spec")
public class SpecificationController {

  @Autowired private SpecificationSvc specificationSvc;

  @PostMapping(value = "/findByPublicationDateBetweenOrderByPriceDesc")
  @ApiOperation("以出版日期查詢Book，並以書價降冪排序")
  public void findByPublicationDateBetweenOrderByPriceDesc() {
    specificationSvc.findByPublicationDateBetweenOrderByPriceDesc(
        LocalDate.of(2016, 1, 1), LocalDate.of(2016, 12, 31));
  }

  @PostMapping(value = "/findQuantityGroupByOrderId")
  @ApiOperation("統計指定訂單的訂購總量")
  public void findQuantityGroupByOrderId() {
    specificationSvc.findQuantityGroupByOrderId(1, 2);
  }

  @PostMapping(value = "/findByQuantityGroupByPublisherIdHavingQuantity/{min}")
  @ApiOperation("統計各出版社的訂購總量，並篩選出指定數量以上的資料")
  public void findByQuantityGroupByPublisherIdHavingQuantity(@PathVariable("min") int min) {
    specificationSvc.findQuantityGroupByPublisherIdHavingQuantity(min);
  }

  @PostMapping(value = "/findByNotExistsOrderDetail")
  @ApiOperation("找出未被訂購的書籍")
  public void findByNotExistsOrderDetail() {
    specificationSvc.findByNotExistsOrderDetail();
  }
}
