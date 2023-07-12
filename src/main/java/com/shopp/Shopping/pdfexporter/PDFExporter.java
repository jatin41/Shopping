//package com.shopp.Shopping.pdfexporter;
//
//import java.io.FileOutputStream;
//
//import com.itextpdf.html2pdf.ConverterProperties;
//import com.itextpdf.html2pdf.HtmlConverter;
//import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
//import com.itextpdf.io.source.ByteArrayOutputStream;
//import com.itextpdf.kernel.pdf.PdfWriter;
//
////import org.apache.pdfbox.pdmodel.PDDocument;
////import org.apache.pdfbox.pdmodel.PDPage;
////import org.apache.pdfbox.pdmodel.common.PDRectangle;
////import org.apache.pdfbox.pdmodel.PDPageContentStream;
////import org.thymeleaf.TemplateEngine;
////import org.thymeleaf.context.				Context;
////import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
////import org.thymeleaf.templateresolver.TemplateResolver;
////
////import java.io.File;
////import java.io.IOException;
////
////public class PDFExporter {
////
////    public void exportTableToPDF() throws IOException {
////        // Load your table data
////        Cart cart = loadCartData();
////
////        // Set up Thymeleaf template engine
////        TemplateEngine templateEngine = createTemplateEngine();
////
////        // Create a Thymeleaf context and add the cart data
////        Context context = new Context();
////        context.setVariable("cart", cart);
////
////        // Render the Thymeleaf template to HTML
////        String renderedHtml = templateEngine.process("cart-template.html", context);
////
////        // Set up PDF document and page
////        PDDocument document = new PDDocument();
////        PDPage page = new PDPage(PDRectangle.A4);
////        document.addPage(page);
////
////        // Write the HTML content to the PDF document
////        PDPageContentStream contentStream = new PDPageContentStream(document, page);
////        contentStream.beginText();
////        contentStream.setFont(PDType1Font.HELVETICA, 12);
////        contentStream.newLineAtOffset(50, 700);
////        contentStream.showText(renderedHtml);
////        contentStream.endText();
////        contentStream.close();
////
////        // Save the PDF document
////        document.save("cart.pdf");
////        document.close();
////    }
////
////    private TemplateEngine createTemplateEngine() {
////        TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
////        templateResolver.setPrefix("templates/");
////        templateResolver.setSuffix(".html");
////
////        TemplateEngine templateEngine = new TemplateEngine();
////        templateEngine.setTemplateResolver(templateResolver);
////
////        return templateEngine;
////    }
////
////    private Cart loadCartData() {
////        // Load your cart data from the database or any other source
////        // and return a Cart object
////    }
////
////    public static void main(String[] args) throws IOException {
////        PDFExporter exporter = new PDFExporter();
////        exporter.exportTableToPDF();
////    }
////}
////
//
//
//public class PDFExporter {
//	public String exportToPdf(String processedHtml) {
//		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//		
//		try {
//			PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
//			DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false,true,false);
//			
//			ConverterProperties converterProperties =  new ConverterProperties();
//			converterProperties.setFontProvider(defaultFontProvider);
//			HtmlConverter.convertToPdf(processedHtml, pdfWriter, converterProperties);
//			
//			
//			FileOutputStream fout = new FileOutputStream("");
//			byteArrayOutputStream.writeTo(fout);
//			byteArrayOutputStream.close();
//			byteArrayOutputStream.flush();
//			
//			return null;
//		}
//		catch(Exception e) {
////			
//		}
//		
//		
//	}
//}
//
