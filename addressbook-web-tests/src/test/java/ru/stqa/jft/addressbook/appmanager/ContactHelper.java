package ru.stqa.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.jft.addressbook.model.ContactData;
import ru.stqa.jft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnContactPage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//input[21]"));
    }

    public void fillContactData(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNik());
        click(By.name("home"));
        type(By.name("mobile"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());

    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

   public void selectById(int id) {
       wd.findElement(By.cssSelector("input[value ='" + id +"']")).click();
    }

    public void editContact(int id) {
        click(By.xpath("//input[@id = '" + id + "']/ancestor-or-self::td/following-sibling::td//a/img[@title = 'Edit']"));
    }

    public void submitContactModifiation() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void submitAlertMessage() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactData(contact);
        submitContactCreation();
        returnContactPage();
    }

    public boolean isThereAContact() {
        return isThereAnItem();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> names = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : names) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.cssSelector("td.center")).findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);

            contacts.add(contact);
        }
        return contacts;
    }

    public void delete(int index) {
        select(index);
        deleteSelectedContact();
        submitAlertMessage();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        deleteSelectedContact();
        submitAlertMessage();
    }

    public void modify(ContactData contact) {
        selectById(contact.getId());
        editContact(contact.getId());
        fillContactData(contact);
        submitContactModifiation();
        returnContactPage();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> names = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : names) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.cssSelector("td.center")).findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);

            contacts.add(contact);
        }
        return contacts;
    }
}
