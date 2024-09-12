package my.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import my.example.model.InventoryStatus;
import my.example.model.Product;
@ApplicationScoped
public class ProductService {
 private List<Product> products;
 
 	private static String description = "Product Description";
 	private static String accessories = "Accessories";
 	private static String fitness = "Fitness";
 	private static String clothing = "Clothing";
 	private static String electronics = "Electronics";
 	
    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1000, "f230fh0g3", "Bamboo Watch", description, "bamboo.jpg", 65,
        		accessories, 24, InventoryStatus.INSTOCK, 5, null));
        products.add(new Product(1001, "nvklal433", "Black Watch", description, "BlackWatch.jpg", 72,
        		accessories, 61, InventoryStatus.INSTOCK, 4, null));
        products.add(new Product(1002, "zz21cz3c1", "Blue Band", description, "Blue band.jpg", 79,
        		fitness, 2, InventoryStatus.LOWSTOCK, 3, null));
        products.add(new Product(1003, "244wgerg2", "Blue T-Shirt", description, "Blue T-Shirt.jpg", 29,
        		clothing, 25, InventoryStatus.INSTOCK, 5, null));
        products.add(new Product(1004, "h456wer53", "Bracelet", description, "bracelet.jpg", 15,
        		accessories, 73, InventoryStatus.INSTOCK, 4, null));
        products.add(new Product(1005, "av2231fwg", "Brown Purse", description, "brown-purse.jpg", 120,
        		accessories, 0, InventoryStatus.OUTOFSTOCK, 4, null));
        products.add(new Product(1006, "bib36pfvm", "Chakra Bracelet", description, "chakra braelet.jpg", 32,
        		accessories, 5, InventoryStatus.LOWSTOCK, 3, null));
        products.add(new Product(1007, "mbvjkgip5", "Galaxy Earrings", description, "galaxy-earrings.jpg", 34,
        		accessories, 23, InventoryStatus.INSTOCK, 5, null));
        products.add(new Product(1008, "vbb124btr", "Game Controller", description, "game-controller.jpg", 99,
        		electronics, 2, InventoryStatus.LOWSTOCK, 4, null));
        products.add(new Product(1009, "cm230f032", "Gaming Set", description, "gaming-set.jpg", 299,
        		electronics, 63, InventoryStatus.INSTOCK, 3, null));
        products.add(new Product(1010, "plb34234v", "Gold Phone Case", description, "gold-phone-case.jpg", 24,
        		accessories, 0, InventoryStatus.OUTOFSTOCK, 4, null));
        products.add(new Product(1011, "4920nnc2d", "Green Earbuds", description, "green-earbuds.jpg", 89,
        		electronics, 23, InventoryStatus.INSTOCK, 4, null));
        products.add(new Product(1012, "250vm23cc", "Green T-Shirt", description, "green-t-shirt.jpg", 49,
        		clothing, 74, InventoryStatus.INSTOCK, 5, null));
        products.add(new Product(1013, "fldsmn31b", "Grey T-Shirt", description, "grey-t-shirt.jpg", 48,
        		clothing, 0, InventoryStatus.OUTOFSTOCK, 3, null));
        products.add(new Product(1014, "waas1x2as", "Headphones", description, "headphones.jpg", 175,
        		electronics, 8, InventoryStatus.LOWSTOCK, 5, null));
        products.add(new Product(1015, "vb34btbg5", "Light Green T-Shirt", description, "light-green-t-shirt.jpg", 49,
        		clothing, 34, InventoryStatus.INSTOCK, 4, null));
        products.add(new Product(1016, "k8l6j58jl", "Lime Band", description, "lime-band.jpg", 79,
        		fitness, 12, InventoryStatus.INSTOCK, 3, null));
        products.add(new Product(1017, "v435nn85n", "Mini Speakers", description, "mini-speakers.jpg", 85,
        		clothing, 42, InventoryStatus.INSTOCK, 4, null));
        products.add(new Product(1018, "09zx9c0zc", "Painted Phone Case", description, "painted-phone-case.jpg", 56,
        		accessories, 41, InventoryStatus.INSTOCK, 5, null));
        products.add(new Product(1019, "mnb5mb2m5", "Pink Band", description, "pink-band.jpg", 79,
        		fitness, 63, InventoryStatus.INSTOCK, 4, null));
        products.add(new Product(1020, "r23fwf2w3", "Pink Purse", description, "pink-purse.jpg", 110,
        		accessories, 0, InventoryStatus.OUTOFSTOCK, 4, null));
        products.add(new Product(1021, "pxpzczo23", "Purple Band", description, "purple-band.jpg", 79,
        		fitness, 6, InventoryStatus.LOWSTOCK, 3, null));
        products.add(new Product(1022, "2c42cb5cb", "Purple Gemstone Necklace", description, "purple-gemstone-necklace.jpg", 45,
        		accessories, 62, InventoryStatus.INSTOCK, 4, null));
        products.add(new Product(1023, "5k43kkk23", "Purple T-Shirt", description, "purple-t-shirt.jpg", 49,
        		clothing, 2, InventoryStatus.LOWSTOCK, 5, null));
        products.add(new Product(1024, "lm2tny2k4", "Shoes", description, "shoes.jpg", 64,
        		clothing, 0, InventoryStatus.INSTOCK, 4, null));
        products.add(new Product(1025, "nbm5mv45n", "Sneakers", description, "sneakers.jpg", 78,
        		clothing, 52, InventoryStatus.INSTOCK, 4, null));
        products.add(new Product(1026, "zx23zc42c", "Teal T-Shirt", description, "teal-t-shirt.jpg", 49,
        		clothing, 3, InventoryStatus.LOWSTOCK, 3, null));
        products.add(new Product(1027, "acvx872gc", "Yellow Earbuds", description, "yellow-earbuds.jpg", 89,
        		electronics, 35, InventoryStatus.INSTOCK, 3, null));
        products.add(new Product(1028, "tx125ck42", "Yoga Mat", description, "yoga-mat.jpg", 20,
        		fitness, 15, InventoryStatus.INSTOCK, 5, null));
        products.add(new Product(1029, "gwuby345v", "Yoga Set", description, "yoga-set.jpg", 20,
        		fitness, 25, InventoryStatus.INSTOCK, 8, null));

    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public List<Product> getProducts(int size) {

        if (size > products.size()) {
            Random rand = new Random();

            List<Product> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(products.size());
                randomList.add(products.get(randomIndex));
            }

            return randomList;
        }

        else {
            return new ArrayList<>(products.subList(0, size));
        }

    }

    public List<Product> getClonedProducts(int size) {
        List<Product> results = new ArrayList<>();
        List<Product> originals = getProducts(size);
        for (Product original : originals) {
            results.add(original.copy());
        }

        // make sure to have unique codes
        for (Product product : results) {
            product.setCode(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        }

        return results;
    }
}