package jp.co.sss.cytech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.cytech.entity.SalesItems;
import jp.co.sss.cytech.repository.SalesItemsRepository;

@Controller
public class TopController {
	@Autowired
	private SalesItemsRepository salesItemsRepository;
	
	@RequestMapping(path = "/user/top")
	public String showTop(Model model) {
		List<SalesItems> salesItems = salesItemsRepository.findAll();
		model.addAttribute("salesItems", salesItems);
	    return "user/top";
	}
}