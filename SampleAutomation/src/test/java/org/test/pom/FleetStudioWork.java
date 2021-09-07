package org.test.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.test.base.Base;

public class FleetStudioWork extends Base {
	
	public FleetStudioWork() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[contains(text(),'Work')]")
	private WebElement btnWork;

	@FindBy(xpath="//select[@name='filter']")
	private WebElement tag;
	
	@FindBy(xpath="(//p[contains(text(),'BMW')])[3]")
	private WebElement qaProj;	
	
	@FindBy(xpath="(//p[contains(text(),'Gallo')])")
	private WebElement devProj;
	
	@FindBy(xpath="(//div[contains(text(),' Next')])")
	private WebElement elementNext;
		
		
		
		public WebElement getElementNext() {
		return elementNext;
	}


		public WebElement getTag() {
			return tag;
		}


		public WebElement getQaProj() {
			return qaProj;
		}


		public WebElement getDevProj() {
			return devProj;
		}

		public WebElement getBtnWork() {
			return btnWork;
		}


}
