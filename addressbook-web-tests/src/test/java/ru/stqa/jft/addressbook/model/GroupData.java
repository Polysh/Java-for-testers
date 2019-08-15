package ru.stqa.jft.addressbook.model;

public class GroupData {
    private final String name;
    private final String footer;
    private final String header;

    public GroupData(String name, String footer, String header) {
        this.name = name;
        this.footer = footer;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public String getFooter() {
        return footer;
    }

    public String getHeader() {
        return header;
    }
}
