package yfu.practice.springboot.jpa;

import io.swagger.annotations.ApiOperation;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yfu.practice.springboot.jpa.entity.Card;
import yfu.practice.springboot.jpa.entity.CardContent;
import yfu.practice.springboot.jpa.entity.CardOwner;
import yfu.practice.springboot.jpa.entity.Member;
import yfu.practice.springboot.jpa.repository.CardOwnerRepo;
import yfu.practice.springboot.jpa.repository.CardRepo;
import yfu.practice.springboot.jpa.repository.MemberRepo;

@RestController
@RequestMapping("/jpa")
public class JpaController {

  @Autowired private CardRepo cardRepo;

  @Autowired private CardOwnerRepo cardOwnerRepo;

  @Autowired private MemberRepo memberRepo;

  /**
   * 測試FetchType
   *
   * @param cardId
   * @throws Exception
   */
  @PostMapping(value = "/queryCard/{id}")
  @ApiOperation("查詢卡片")
  public void queryCard(@PathVariable("id") String cardId) throws Exception {
    Card card = cardRepo.findById(cardId).orElseThrow(() -> new Exception("查無資料"));
    System.out.println(card);
  }

  /** 新增一方時連同多方一起新增 */
  @PostMapping(value = "/insertCard")
  @ApiOperation("新增卡片")
  public void insertCard() {
    Card card = new Card();
    card.setType("L");

    Set<CardContent> contents =
        Stream.of(
                new CardContent(card, 1, "吃飯"),
                new CardContent(card, 2, "睡覺"),
                new CardContent(card, 3, "打東東"))
            .collect(Collectors.toSet());
    card.setCardContents(contents);

    card.setCardOwner(new CardOwner(card, "陳X東"));

    Set<Member> editors =
        Stream.of(new Member("陳X南"), new Member("陳X北"), new Member("陳X中"))
            .collect(Collectors.toSet());
    card.setEditors(editors);

    cardRepo.save(card);
  }

  /**
   * 刪除一方時連同多方一併刪除
   *
   * @param cardId
   */
  @PostMapping(value = "/removeCard/{id}")
  @ApiOperation("刪除卡片")
  public void removeCard(@PathVariable("id") String cardId) {
    cardRepo.deleteById(cardId);
  }

  /**
   * 測試orphanRemoval
   *
   * @param cardId
   * @throws Exception
   */
  @PostMapping(value = "/removeLastContent/{id}")
  @ApiOperation("刪除最後一則卡片內容")
  public void removeLastContent(@PathVariable("id") String cardId) throws Exception {
    Card card = cardRepo.findById(cardId).orElseThrow(() -> new Exception("查無資料"));

    Set<CardContent> contents = card.getCardContents();
    contents.stream()
        .max(Comparator.comparing(CardContent::getOrderNumber))
        .ifPresent(contents::remove);

    cardRepo.save(card);
  }

  /** 新增PK同時為FK的資料 */
  @PostMapping(value = "/insertOwner")
  @ApiOperation("新增卡片所有者")
  public void insertOwner() {
    Card card = new Card();
    card.setType("M");
    card.setCardContents(
        Stream.of(new CardContent(card, 1, "ErgoLift軸承設計，NanoEdge窄邊框螢幕"))
            .collect(Collectors.toSet()));

    CardOwner cardOwner = new CardOwner();
    cardOwner.setOwner("陳X西");
    cardOwner.setCard(card);
    cardOwnerRepo.save(cardOwner);
  }

  /**
   * 透過一方更新另一個一方的資料
   *
   * @param cardId
   * @throws Exception
   */
  @PostMapping(value = "/updateCardWithOwner/{id}")
  @ApiOperation("透過卡片所有者更新卡片")
  public void updateCardWithOwner(@PathVariable("id") String cardId) throws Exception {
    CardOwner cardOwner = cardOwnerRepo.findById(cardId).orElseThrow(() -> new Exception("查無資料"));
    cardOwner.getCard().setType("D");
    cardOwnerRepo.save(cardOwner);
  }

  /**
   * 測試@OneToOne的cascade
   *
   * @param cardId
   */
  @PostMapping(value = "/removeOwner/{id}")
  @ApiOperation("刪除卡片所有者")
  public void removeOwner(@PathVariable("id") String cardId) {
    cardOwnerRepo.deleteById(cardId);
  }
}
