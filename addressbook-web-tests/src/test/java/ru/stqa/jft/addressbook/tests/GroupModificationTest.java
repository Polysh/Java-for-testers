package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test11", null, null));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = 0;
        GroupData group = new GroupData(before.get(index).getId(), "test0", "test02", "test03");
        app.getGroupHelper().modifyGroup(index, group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before, after);
    }



}
