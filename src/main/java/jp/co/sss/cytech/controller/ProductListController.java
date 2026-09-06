package jp.co.sss.cytech.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.cytech.entity.Companies;
import jp.co.sss.cytech.entity.Products;
import jp.co.sss.cytech.form.ProductListForm;
import jp.co.sss.cytech.repository.CompaniesRepository;
import jp.co.sss.cytech.repository.ProductsRepository;

@Controller
public class ProductListController {
	@Autowired
	ProductsRepository productsRepository;
	@Autowired
	CompaniesRepository companiesRepository;

	
	@RequestMapping(path = "/user/productList")
	public String showProductList(Model model) {
		List<Products> productItems = productsRepository.findAll();
		List<Companies> companyList = companiesRepository.findAll();
		List<ProductListForm> productItemList = new ArrayList<>();
		for(Products productItem: productItems) {
			Optional<Companies> company = companiesRepository.findById(productItem.getCompanyId());
			String companyName = company.get().getCompanyName();
			ProductListForm productItemInfo = new ProductListForm();
			productItemInfo.setProductId(productItem.getProductId());
			productItemInfo.setImagePath(productItem.getImgPath());
			productItemInfo.setProductName(productItem.getProductName());
			productItemInfo.setCompanyName(companyName);
			productItemInfo.setPrice(productItem.getPrice());
			productItemInfo.setIncludeTax(productItem.getIncludeTax());
			productItemList.add(productItemInfo);
		}
		model.addAttribute("productItemList", productItemList);
	return "user/productList";
	}
	
	@RequestMapping(path = "/user/search")
    public String searchProductList(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            Model model) {

        List<Products> productItems;
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        boolean hasCategory = categoryId != null;

        // 条件に応じて絞り込み
        if (hasKeyword && hasCategory) {
            productItems = productsRepository.findByProductNameContainingAndCategoryId(keyword, categoryId);
        } else if (hasKeyword) {
            productItems = productsRepository.findByProductNameContaining(keyword);
        } else if (hasCategory) {
            productItems = productsRepository.findByCategoryId(categoryId);
        } else {
            productItems = productsRepository.findAll();
        }

        // Formリストへ詰め替え
        List<ProductListForm> productItemList = createProductFormList(productItems);

        model.addAttribute("productItemList", productItemList);
        return "user/productList"; // 一覧画面と同じHTMLを返す
        
    }

    // 💡 Products から ProductListForm への変換ロジック（共通化）
    private List<ProductListForm> createProductFormList(List<Products> productItems) {
        List<ProductListForm> productItemList = new ArrayList<>();
        for (Products productItem : productItems) {
            Optional<Companies> company = companiesRepository.findById(productItem.getCompanyId());
            String companyName = company.isPresent() ? company.get().getCompanyName() : "不明";

            ProductListForm productItemInfo = new ProductListForm();
            productItemInfo.setProductId(productItem.getProductId());
            productItemInfo.setImagePath(productItem.getImgPath());
            productItemInfo.setProductName(productItem.getProductName());
            productItemInfo.setCompanyName(companyName);
            productItemInfo.setPrice(productItem.getPrice());
            productItemInfo.setIncludeTax(productItem.getIncludeTax());

            productItemList.add(productItemInfo);
        }
        return productItemList;
    }
}
