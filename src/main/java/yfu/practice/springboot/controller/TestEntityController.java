package yfu.practice.springboot.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import yfu.practice.springboot.entity.YfuCard;
import yfu.practice.springboot.entity.YfuCardContent;
import yfu.practice.springboot.entity.YfuCardOwner;
import yfu.practice.springboot.entity.YfuMember;
import yfu.practice.springboot.repository.YfuCardRepo;

@RestController
public class TestEntityController {
	
	@Autowired
	private YfuCardRepo yfuCardRepo;

	/**
	 * 新增一方時連同多方一起新增
	 */
	@PostMapping(value = "/insertCard")
	@ApiOperation("新增一方同時新增多方")
	public void insertCard() {
		YfuCard yfuCard = new YfuCard();
		yfuCard.setType("L");
		yfuCard.setOwner(new YfuCardOwner(yfuCard, "陳X東"));
		
		Set<YfuCardContent> contents = new HashSet<>(Arrays.asList(
				new YfuCardContent(yfuCard, 1, "吃飯"),
				new YfuCardContent(yfuCard, 2, "睡覺"),
				new YfuCardContent(yfuCard, 3, "打東東")));
		yfuCard.setContents(contents);

		Set<YfuMember> editors = new HashSet<>(Arrays.asList(
				new YfuMember("陳X東"),
				new YfuMember("陳X北")));
		yfuCard.setEditors(editors);
		
		yfuCardRepo.save(yfuCard);
	}
	
	/**
	 * 刪除一方時連同多方一併刪除
	 * @param cardId
	 */
	@PostMapping(value = "/removeCard/{id}")
	@ApiOperation("刪除一方同時刪除多方")
	public void removeCard(@PathVariable("id") String cardId) {
		yfuCardRepo.deleteById(cardId);
	}
}