package com.shopp.Shopping.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopp.Shopping.model.Cart;
import com.shopp.Shopping.model.Products;
import com.shopp.Shopping.repository.CartRepository;
import com.shopp.Shopping.service.CartService;
import com.shopp.Shopping.service.ProductService;
import com.shopp.Shopping.util.UserCartHolder;
import com.shopp.Shopping.web.dto.ProductsDto;
import com.shopp.Shopping.web.dto.RegistrationDto;

@Controller
public class ProductController {
	@Autowired
	public ProductService productService;
	@Autowired
	public UserCartHolder userCartHolder;
	@Autowired
	public CartRepository cartRepository;
	@Autowired
	public CartService cartService;
	
	@ModelAttribute("product")
	public ProductsDto product(){
		return new ProductsDto();
	}
	
	@GetMapping("/productregistration")
	public String addNewProduct() {
		return "productregistration";
	}
	@PostMapping("/productregistration")
	public String newProduct(@RequestParam("image") MultipartFile image,@ModelAttribute("product") ProductsDto productsDto) throws IOException{
		System.out.println(image.getOriginalFilename());
		productService.addNew(image, productsDto);
		return "redirect:/productregistration";
	}
	
	
	
	
	@GetMapping("/product")
	public String  getAllProducts(Model model){
		List<Products> productList = productService.getAllProducts();
		List<ProductsDto> productDTOList = new ArrayList<>();
		for (Products product : productList) {
            byte[] imageData = product.getData();
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            ProductsDto productDTO = new ProductsDto();
            productDTO.setData(imageData);
            productDTO.setBase64Image(base64Image);
            productDTO.setName(product.getName());
            productDTO.setInformation(product.getInformation());
            productDTO.setPrice(product.getPrice());
            productDTO.setId(product.getId());
            productDTOList.add(productDTO);
        }
		
		
		model.addAttribute("products",productDTOList);
		return "product";
		}
	@GetMapping("/product/{id}")
	public String delete(@PathVariable Long id, Model model) {
		productService.delete(id);
		return "redirect:/product";
	}
	@GetMapping("/product/my/{name}")
	public String get(@PathVariable String name, Model model) {
		model.addAttribute("myproduct", productService.getByName(name));
		Products product = productService.getByName(name);
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		Cart cart = cartRepository.getCartByUserEmail(username);
		Set<Products> set= new HashSet<>(cart.getProducts());;
		set.add(product);
//		product.setQuantity(2);
		cart.setProducts(set);
//		cart.setTotal(set.size());
//		System.out.println(cart.getTotal());
		cartRepository.save(cart);
		System.out.println(cart.getProducts().isEmpty());
		return "redirect:/product";
	}
	
	@GetMapping("/cart")
	public String viewCart(@RequestParam(required = false) Integer quantity,Model model) {
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    Cart cart = cartRepository.getCartByUserEmail(username);
	    cart.setTotal(cart.getProducts().size());
	    double price=0;
//	    for (int i=0; i<cart.getProducts().size();i++) {
//	    	cart.getQuantity().add(quantity);
//	    }
	    for (Products product : cart.getProducts()) {
	    	price += product.getPrice();
	    	
	    }
	    
	    cart.setTotalPrice(price);
	    System.out.println(price);
	    model.addAttribute("cart", cart);

	    // Return the view name
	    return "cart";
	}
	@PostMapping("/cart/remove")
	public String removeFromCart(@RequestParam("productId") Long productId) {
	    // Get the authenticated user's cart
	    org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    Cart cart = cartRepository.getCartByUserEmail(username);

	    // Remove the product from the cart
	    Set<Products> products = cart.getProducts();
	    products.removeIf(product -> product.getId().equals(productId));
	    cart.setProducts(products);
	    cartRepository.save(cart);
	    double price=0;
	    for (Products product : cart.getProducts()) {
	    	price = price+ (product.getPrice()* product.getQuantity());
	    }
	    cart.setTotalPrice(price);
	    System.out.println(price);
	    return "redirect:/cart";
	}
	@GetMapping("/pdf")
public String exportToPdf() throws MessagingException {
	 org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    Cart cart = cartRepository.getCartByUserEmail(username);
	    try {
	        PDDocument document = new PDDocument();
	        PDPage page = new PDPage();
	        document.addPage(page);

	        PDPageContentStream contentStream = new PDPageContentStream(document, page);

	        // Set up table parameters
	        int rowsPerPage = 20;
	        float tableTopY = 700; // Adjust the table position as needed
	        float tableBottomY = 50;
	        float tableWidth = page.getMediaBox().getWidth() - 2 * 50; // Adjust the table width as needed
	        float startY = tableTopY;

	        // Add table headers
	        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	        contentStream.beginText();
	        contentStream.newLineAtOffset(50, startY);
	        contentStream.showText("Item Name");
	        contentStream.newLineAtOffset(150, 0);
	        contentStream.showText("Price");
	        contentStream.newLineAtOffset(100, 0);
	        contentStream.showText("Quantity");
//	        contentStream.newLineAtOffset(100, 0);
//	        contentStream.showText("Total");
	        contentStream.endText();

	        // Iterate over the cart items and add them to the table
	        contentStream.setFont(PDType1Font.HELVETICA, 12);
	        float currentY = startY - 20;
	        for (Products item : cart.getProducts()) {
	            contentStream.beginText();
//	        	System.out.println(item.getName());
	            contentStream.newLineAtOffset(50, currentY);
	            contentStream.showText(item.getName());
	            contentStream.newLineAtOffset(150, 0);
	            contentStream.showText(String.valueOf(item.getPrice()));
	            contentStream.newLineAtOffset(100, 0);
	            contentStream.showText(String.valueOf(item.getQuantity()));
	            contentStream.newLineAtOffset(100, 0);
//	            contentStream.showText(String.valueOf(item.getTotal()));
	            contentStream.endText();

	            currentY -= 20;

	            // Check if the page needs to be continued
	            if (currentY <= tableBottomY) {
	                contentStream.close();
	                PDPage newPage = new PDPage();
	                document.addPage(newPage);
	                contentStream = new PDPageContentStream(document, newPage);
	                contentStream.setFont(PDType1Font.HELVETICA, 12);
	                startY = newPage.getMediaBox().getHeight() - 50;
	                currentY = startY;
	                contentStream.beginText();
	                contentStream.newLineAtOffset(50, currentY);
	            }
	        }
	        contentStream.beginText();
	        contentStream.showText(username);
	        contentStream.showText(String.valueOf(cart.getTotal()));
	        contentStream.showText(String.valueOf(cart.getTotalPrice()));
	        contentStream.endText();
	        contentStream.close();

	        // Save the PDF to a file
	        String filename = "/Users/jatinagrawal/Desktop/Hacktober week/"+username+".pdf";
	        
	        document.save(filename);
	        document.close();
	        
//	        
//	        MimeMessage message = javaMailSender.createMimeMessage();
//
//	        // Use MimeMessageHelper to set the email properties
//	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//	        helper.setTo("agrawaljatin209@gmail.com"); // Set the recipient email address
//	        helper.setSubject("Cart PDF"); // Set the email subject
//	        helper.setText("Please find the PDF attached."); // Set the email content
//
//	        // Add the PDF file as an attachment
//	        FileSystemResource file = new FileSystemResource(new File(filename));
//	        helper.addAttachment("cart.pdf", file);
//
//	        // Send the email
//	        javaMailSender.send(message);

	        return "redirect:/cart";
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle the exception appropriately
	        return "redirect:/error";
	        
	    }}
		@PostMapping("/cart/update/{name}")
		public String updateQuantity(@RequestParam("quantity") int quantity,@PathVariable String name) {
			org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
		    System.out.println(username);
		    Cart cart = cartRepository.getCartByUserEmail(username);
		    
		    Products product = productService.getByName(name);
		    	product.setQuantity(quantity);
		    
		    
		    cartRepository.save(cart);
		    return "redirect:/cart";
		}
}
