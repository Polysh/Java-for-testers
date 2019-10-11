package ru.stqa.jft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.jft.mantis.model.Users;

public class UserHelper extends HelperBase {

    protected Users usersCache = null;

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void goToManaging() {
        click(By.xpath("//span[text() = ' Управление ']"));
    }

    public void chooseTab(String tabName) {
        click(By.xpath("//a[text() = '" + tabName + "']"));
    }

    public void chooseUser(String username) {
        click(By.linkText(username));
    }

    public void resetPassword() {
        WebElement resetButton = wd.findElement(By.cssSelector("input[value = 'Сбросить пароль']"));
        resetButton.click();
    }

    public void resetUserPassword(String user) {
        app.us().goToManaging();
        app.us().chooseTab("Управление пользователями");
        app.us().chooseUser(user);
        app.us().resetPassword();
    }


//    public void returnGroupPage() {
//        click(By.linkText("group page"));
//    }
//
//    public void submitGroupCreation() {
//        click(By.name("submit"));
//    }
//
//    public void fillGroupForm(GroupData groupData) {
//        type(By.name("group_name"), groupData.getName());
//        type(By.name("group_footer"), groupData.getFooter());
//        type(By.name("group_header"), groupData.getHeader());
//    }
//
//    public void initGroupCreation() {
//        click(By.name("new"));
//    }
//
//    public void deleteSelectedGroup() {
//        click(By.name("delete"));
//    }
//
//    public void selectGroupById(int id) {
//        wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
//    }
//
//    public void initGroupModification() {
//        click(By.name("edit"));
//    }
//
//    public void submitGroupModification() {
//        click(By.name("update"));
//    }
//
//    public void create(GroupData group) {
//        initGroupCreation();
//        fillGroupForm(group);
//        submitGroupCreation();
//        groupCache = null;
//        returnGroupPage();
//    }
//
//    public void modify(GroupData group) {
//        selectGroupById(group.getId());
//        initGroupModification();
//        fillGroupForm(group);
//        submitGroupModification();
//        groupCache = null;
//        returnGroupPage();
//    }
//
//    public boolean isThereAGroup() {
//        return isThereAnItem();
//    }
//
//    public int count() {
//        return wd.findElements(By.name("selected[]")).size();
//    }
//
//    public Users all() {
//        if (usersCache != null) {
//            return new Users(usersCache);
//        }
//        usersCache = new Users();
//        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
//        for (WebElement element : elements) {
//            String username = element.getText();
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            usersCache.add(new UserData().withId(id).withUserName(username));
//        }
//        return new Users(usersCache);
//    }
//
//    public void delete(GroupData group) {
//        selectGroupById(group.getId());
//        deleteSelectedGroup();
//        groupCache = null;
//        returnGroupPage();
//    }
//
//    public GroupData findInGroups(Groups groups, int group_id) {
//        GroupData newGroup = null;
//        for (GroupData group : groups) {
//            if (group.getId() == group_id) newGroup = group;
//        }
//        return newGroup;
//    }


}
