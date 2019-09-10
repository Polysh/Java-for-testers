package ru.stqa.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.jft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void editContact(String index) {
//        click(By.xpath("//img[@alt='Edit']"));
        click(By.xpath("//tr[" + index + "]//a/img[@alt='Edit']"));
        //tr[6]//a/img[@alt='Edit']
    }

    public void submitContactModifiation() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void submitAlertMessage() {
        wd.switchTo().alert().accept();
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactData(contact);
        submitContactCreation();
        returnContactPage();
    }

    public boolean isThereAContact() {
        return isThereAnItem();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> names = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : names) {
            List<String> contactParams = Arrays.asList(element.getText().split(" "));
            String lastName = contactParams.get(0);
            String firstName = contactParams.get(1);
            int id = Integer.parseInt(element.findElement(By.cssSelector("td.center")).findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstName, null, lastName, null, null, null);
            contacts.add(contact);
        }

        return contacts;
    }
}
