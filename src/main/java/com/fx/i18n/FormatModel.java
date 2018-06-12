package com.fx.i18n;

import java.util.Date;

public class FormatModel {
    private String money;
    private Date date;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FormatModel(String money, Date date) {
        this.money = money;
        this.date = date;
    }
    public FormatModel(){

    }
}
