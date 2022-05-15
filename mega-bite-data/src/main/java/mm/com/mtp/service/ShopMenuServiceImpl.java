package mm.com.mtp.service;

import mm.com.mtp.datatable.ShopMenuDataTable;
import mm.com.mtp.exception.FileStorageException;
import mm.com.mtp.exception.ResourceNotFoundException;
import mm.com.mtp.form.ShopMenuForm;
import mm.com.mtp.mapper.ShopMenuMapper;
import mm.com.mtp.model.Menu;
import mm.com.mtp.model.Shop;
import mm.com.mtp.model.ShopMenu;
import mm.com.mtp.model.ShopMenuImage;
import mm.com.mtp.repository.MenuRepository;
import mm.com.mtp.repository.ShopMenuRepository;
import mm.com.mtp.repository.ShopRepository;
import mm.com.mtp.utils.AppConstant;
import mm.com.mtp.utils.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.mapping.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 4/6/2022.
 */
@Service
public class ShopMenuServiceImpl implements ShopMenuService {

    @Autowired
    ShopMenuRepository shopMenuRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ShopMenuMapper shopMenuMapper;

    @Autowired
    FileStorageService fileStorageService;


    @Override
    public ShopMenuForm createShopMenu(ShopMenuForm shopMenuForm) {

        Shop shop = shopRepository.findById(shopMenuForm.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "Id", shopMenuForm.getShopId()));

        Menu menu = menuRepository.findById(shopMenuForm.getMenuId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "Id", shopMenuForm.getMenuId()));

        ShopMenu shopMenu = null;
        if (shopMenuForm.getId() == null) {
            shopMenu = shopMenuMapper.toShopMenu(shop, menu, shopMenuForm);
        } else {
            shopMenu = shopMenuRepository.findById(shopMenuForm.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("ShopMenu", "Id", shopMenuForm.getId()));
            shopMenu = shopMenuMapper.toShopMenu(shopMenu, shop, menu, shopMenuForm);
        }

        shopMenu = shopMenuRepository.save(shopMenu);

        if (shopMenuForm.getIsNewImage()) {
            ShopMenuImage shopMenuImage = createShopMenuImage(shopMenu, shopMenuForm);

            if (shopMenu.getImage() == null) {
                shopMenu.setImage(shopMenuImage);
            } else {
                shopMenu.getImage().setName(shopMenuImage.getName());
                shopMenu.getImage().setType(shopMenuImage.getType());
                shopMenu.getImage().setSize(shopMenuImage.getSize());
                shopMenu.getImage().setPath(shopMenuImage.getPath());
            }
        }
        //shopMenu.setImage(createShopMenuImage(shopMenu, shopMenuForm));

        return shopMenuMapper.toShopMenuForm(shopMenuRepository.save(shopMenu));
    }

    @Override
    public ShopMenuForm getShopMenu(Long id) {
        ShopMenu shopMenu = shopMenuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShopMenu", "Id", id));
        return shopMenuMapper.toShopMenuForm(shopMenu);
    }

    @Override
    public ShopMenuDataTable getShopMenuDataTable(Long id) {
        ShopMenu shopMenu = shopMenuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShopMenu", "Id", id));
        return shopMenuMapper.toShopMenuDataTable(shopMenu);
    }

    @Override
    public List<ShopMenuDataTable> getShopMenuByShop(Long shopId) {
        return shopMenuMapper.toShopMenuDataTable(shopMenuRepository.findByShopId(shopId));
    }

    @Override
    public List<ShopMenuForm> getShopMenu() {
        return shopMenuMapper.toShopMenuForm(shopMenuRepository.findAll());
    }

    @Override
    public DataTablesOutput<ShopMenuDataTable> shopMenuDataTable(DataTablesInput dataTablesInput, Map<String, Object> filterConditions) {
        dataTablesInput.getOrder().add(new Order(0, "asc"));
        return shopMenuRepository.findAll(dataTablesInput, filterByConditions(filterConditions), null, shopMenuMapper::toShopMenuDataTable);
    }

    private Specification<ShopMenu> filterByConditions(Map<String, Object> filterConditions) {
        return new Specification<ShopMenu>() {
            @Override
            public Predicate toPredicate(Root<ShopMenu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                for (String key : filterConditions.keySet()) {

                    if (key.equals("shopId")) {
                        Join<ShopMenu, Shop> shopJoin = root.join("shop");
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lower(shopJoin.get("id")).in(filterConditions.get(key))));
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    @Override
    public Resource getImage(Long id) {
        ShopMenu shopMenu = shopMenuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShopMenu", "Id", id));

        if (shopMenu.getImage() == null) {
            throw new ResourceNotFoundException("ShopMenu image not found");
        }

        return fileStorageService.loadFileAsResource(Directory.SHOP_MENU, shopMenu.getImage().getName());

    }

    private ShopMenuImage createShopMenuImage(ShopMenu shopMenu, ShopMenuForm shopMenuForm) {
        String fileName = "";
        String fileDownloadUri = "";

        if (!shopMenuForm.getImage().isEmpty()) {
            try {
                fileName = fileStorageService.storeFile(Directory.SHOP_MENU, shopMenuForm.getImage());
                fileDownloadUri = AppConstant.API_ENDPOINT + "/shopMenu/" + shopMenu.getId() + "/images";
            } catch (Exception ex) {
                throw new FileStorageException("Could not store file . Please try again!", ex);
            }
        }

        return new ShopMenuImage(shopMenu,
                fileName, shopMenuForm.getImage().getContentType(),
                shopMenuForm.getImage().getSize(), fileDownloadUri);
    }

}
