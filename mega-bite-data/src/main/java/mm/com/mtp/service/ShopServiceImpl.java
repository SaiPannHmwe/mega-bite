package mm.com.mtp.service;

import mm.com.mtp.datatable.ShopDataTable;
import mm.com.mtp.exception.FileStorageException;
import mm.com.mtp.exception.ResourceNotFoundException;
import mm.com.mtp.form.PopularShopForm;
import mm.com.mtp.form.ShopForm;
import mm.com.mtp.mapper.ShopMapper;
import mm.com.mtp.model.Menu;
import mm.com.mtp.model.Shop;
import mm.com.mtp.model.ShopImage;
import mm.com.mtp.model.ShopMenu;
import mm.com.mtp.payload.ShopRequest;
import mm.com.mtp.payload.ShopResponse;
import mm.com.mtp.repository.ShopRepository;
import mm.com.mtp.utils.AppConstant;
import mm.com.mtp.utils.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sai Pann Hmwe on 4/2/2022.
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    FileStorageService fileStorageService;

    @Override
    public ShopForm getShop(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "Id", id));
        return shopMapper.toShopForm(shop);
    }

    @Override
    public ShopResponse getShopDetail(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "Id", id));
        return shopMapper.toShopResponse(shop);
    }

    @Override
    public List<ShopResponse> getShop() {
        return shopMapper.toShopResponses(shopRepository.findAll());
    }

    @Override
    public List<ShopResponse> getShop(String query, int start, int size) {
        Pageable pageable = PageRequest.of(start, size);

        DataTablesInput dataTablesInput = new DataTablesInput();
        dataTablesInput.setDraw(start + 1);
        dataTablesInput.setLength(size);
        dataTablesInput.setStart(start * size);

        /*Page<ShopResponse> shopResponses =(query.isEmpty())
                ?shopRepository.findAll(pageable).map(shopMapper::toShopResponse)
                : shopRepository.findAllByNameContaining(query, pageable).map(shopMapper::toShopResponse);
        return shopResponses.getContent();*/

        if (query.isEmpty()) {
            Page<ShopResponse> shopResponses =
                    shopRepository.findAll(pageable).map(shopMapper::toShopResponse);
            return shopResponses.getContent();
        } else {
            return shopRepository.findAll(dataTablesInput, shopFilters(query), null, shopMapper::toShopResponse).getData();
        }
        // : shopRepository.findAllByNameContaining(query, pageable).map(shopMapper::toShopResponse);

        //return shopResponses.getContent();
    }

    private Specification<Shop> shopFilters(String query) {
        return new Specification<Shop>() {
            @Override
            public Predicate toPredicate(Root<Shop> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Predicate predicate = null;

                if (!query.isEmpty()) {
                    //predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("deliveryDate"), LocalDate.now())));
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + query + "%")));
                    System.out.print(predicates.size());
                    predicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }

                if (StringUtils.hasText(query)) {
                    List<Predicate> searchPredicates = new ArrayList<>();

                    //searchPredicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + query + "%")));

                    Join<Shop, ShopMenu> shopMenuJoin = root.join("shopMenuList");
                    Join<ShopMenu, Menu> menuJoin = shopMenuJoin.join("menu");
                    searchPredicates.add(criteriaBuilder.and(criteriaBuilder.like(menuJoin.get("name"), "%" + query + "%")));
                    searchPredicates.add(criteriaBuilder.and(criteriaBuilder.like(menuJoin.get("nameChinese"), "%" + query + "%")));

                    criteriaQuery.distinct(true);
                    System.out.print(searchPredicates.size());
                    Predicate searchPredicate = criteriaBuilder.or(searchPredicates.toArray(new Predicate[searchPredicates.size()]));

                    predicate = predicate == null ? searchPredicate : criteriaBuilder.or(predicate, searchPredicate);
                }

                return predicate;
            }
        };
    }


    @Override
    public ShopResponse registerShop(ShopRequest shopRequest) {
        Shop shop = shopMapper.toShop(shopRequest);
        shop = shopRepository.save(shop);
        return shopMapper.toShopResponse(shop);
    }

    @Override
    public DataTablesOutput<ShopDataTable> shopDataTable(DataTablesInput dataTablesInput) {
        dataTablesInput.getOrder().add(new org.springframework.data.jpa.datatables.mapping.Order(0, "asc"));

        return shopRepository.findAll(dataTablesInput, filterByConditions(), null, shopMapper::toShopDataTable);

    }

    @Override
    public void save(ShopForm shopForm) {
        Shop shop;
        if (shopForm.getId() == null) {
            shop = shopMapper.toShop(shopForm);
        } else {
            shop = shopRepository.findById(shopForm.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Shop", "Id", shopForm.getId()));
            shop = shopMapper.toShop(shop, shopForm);
        }
        shop = shopRepository.save(shop);

        if (!shopForm.getImage().isEmpty() && shopForm.getIsNewImage()) {
            ShopImage shopImage = createShopImage(shop, shopForm);

            if (shop.getImage() == null) {
                shop.setImage(shopImage);
            } else {
                shop.getImage().setName(shopImage.getName());
                shop.getImage().setType(shopImage.getType());
                shop.getImage().setSize(shopImage.getSize());
                shop.getImage().setPath(shopImage.getPath());
            }
        }

        shopRepository.save(shop);
    }

    @Override
    public void savePopularShop(PopularShopForm shopForm) {
        Shop shop = shopRepository.findById(shopForm.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "Id", shopForm.getShopId()));
        shop = shopMapper.toShop(shop, shopForm);

        shop = shopRepository.save(shop);

        /*if (shopForm.getIsNewImage()) {
            ShopImage shopImage = createShopImage(shop, shopForm);

            if (shop.getImage() == null) {
                shop.setImage(shopImage);
            } else {
                shop.getImage().setName(shopImage.getName());
                shop.getImage().setType(shopImage.getType());
                shop.getImage().setSize(shopImage.getSize());
                shop.getImage().setPath(shopImage.getPath());
            }
        }

        shopRepository.save(shop);*/
    }

    @Override
    public Map<Long, String> getShopMap() {
        return shopRepository.findAll().stream().collect(Collectors.toMap(Shop::getId, Shop::getName));
    }

    @Override
    public Resource getImage(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("ShopMenu", "Id", shopId));
        if (shop.getImage() == null) {
            throw new ResourceNotFoundException("Shop image not found");
        }

        return fileStorageService.loadFileAsResource(Directory.SHOP, shop.getImage().getName());
    }

    @Override
    public void makePopular(Long shopId) {
        Shop newShop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop", "Id", shopId));

        Shop shop = shopRepository.findByIsMostPopular(true).orElse(null);
        if (shop != null) {
            shop.setMostPopular(false);
            shopRepository.save(shop);
        }

        newShop.setMostPopular(true);
        shopRepository.save(newShop);

    }

    @Override
    public ShopForm getPopularShop() {
        Shop shop = shopRepository.findByIsMostPopular(true).orElse(null);
        return shopMapper.toShopForm(shop);
    }

    private ShopImage createShopImage(Shop shop, ShopForm shopForm) {
        String fileName = "";
        String fileDownloadUri = "";

        if (!shopForm.getImage().isEmpty()) {
            try {
                fileName = fileStorageService.storeFile(Directory.SHOP, shopForm.getImage());
                fileDownloadUri = AppConstant.API_ENDPOINT + "/shop/" + shop.getId() + "/images";
            } catch (Exception ex) {
                throw new FileStorageException("Could not store file . Please try again!", ex);
            }
        }

        return new ShopImage(shop,
                fileName, shopForm.getImage().getContentType(),
                shopForm.getImage().getSize(), fileDownloadUri);
    }

    private Specification<Shop> filterByConditions() {
        return new Specification<Shop>() {
            @Override
            public Predicate toPredicate(Root<Shop> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                /*filterConditions.forEach((key, value) ->
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(key), value))));*/

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
