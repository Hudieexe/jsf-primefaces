package my.example.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import my.example.model.Product;
import my.example.service.ProductService;

@Slf4j
@Named
@ViewScoped
@Getter
@Setter
public class CrudView implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Product> products;

    private Product selectedProduct;

    private List<Product> selectedProducts;
    
    private static String[] messageFormat = {"form:messages","form:dt-products"};


    @Inject
    private ProductService productService;

    @PostConstruct
    public void init() {
        this.products = this.productService.getClonedProducts(100);
        this.selectedProducts = new ArrayList<>();
    }

    public void openNew() {
        this.selectedProduct = new Product(0, null, null, null, null, 0, null, 0, null, 0, null);
    }

    public void saveProduct() {
        if (this.selectedProduct.getCode() == null) {
        	log.debug("begin -> {}",this.selectedProduct.getCode());
            this.selectedProduct.setCode(UUID.randomUUID().toString().replace("-", "").substring(0, 9));
            this.products.add(this.selectedProduct);
            log.debug("end -> {}",this.selectedProduct.getCode());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update(messageFormat);
    }

    public void deleteProduct() {
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update(messageFormat);
    }
    
    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update(messageFormat);
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

}