package operators;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;
import org.testng.Assert;

import datadriven.data;

public class pdfvalidator {
	//public static List<PDField> fields=null;
	public static void main(String args[]) throws IOException {

	      
		PDDocument doc = PDDocument.load(new File("C:\\gitlast\\Web.IFSNAO\\TestAutomation\\BDDFramework\\downloadedfiles\\319441_Traditional IRA Adoption Agreement.pdf"));
		PDDocumentCatalog docCatalog = doc.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();   
        System.out.println(acroForm.getField("PR.addDet.legAddress.concatAddrsLines").getValueAsString()) ;
        List<PDField> fields = acroForm.getFields(); 
        //System.out.println(fields.get(0));
        fields.forEach(f -> {
		    listFields(f);              
		});
        //fields.contains(o)
       /* for(int i=0;i<fields.size();i++){
        	
        	System.out.println(fields.get(i).getValueAsString());//
        }*/
	   }
	
	public static String pdfformvalidation(String pdfpath,String fieldname) throws IOException{
		PDDocument doc = PDDocument.load(new File(pdfpath));
		PDDocumentCatalog docCatalog = doc.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();            
		String fieldvalue=acroForm.getField(fieldname).getValueAsString();
		System.out.println(fieldvalue);
		return fieldvalue;
	}
	
	public static List<PDField> pdfformvalidationlist(String pdfpath) throws IOException{
		PDDocument doc = PDDocument.load(new File(pdfpath));
		PDDocumentCatalog docCatalog = doc.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();          
        List<PDField> fields = acroForm.getFields(); 
        //System.out.println(fields.iterator().next().getAcroForm().getField("PR.addDet.legAddress.concatAddrsLines").getValueAsString());           		
		return fields;
	}
	
	public void assertfieldvalues(String pdfpath,String fieldname,String assertvalue) throws IOException{
		
		String expected =pdfformvalidation(pdfpath,fieldname);
		Assert.assertEquals(assertvalue, expected);
				
	}
	public static void listFields(PDField f){
	    if(f instanceof PDNonTerminalField) {
	        ((PDNonTerminalField) f).getChildren().forEach(ntf-> {
	            listFields(ntf);
	        });         
	    }else {
	       System.out.println(f);
	        //System.out.println("assertpdfval(\""+f.getFullyQualifiedName()+"\",data.getTestValue(\"dummy\", tcid));");
	        //assertpdfval("PR.addDet.legAddress.concatAddrsLines",data.getTestValue("ipci_addline1", tcid));
	    }
	}

}
