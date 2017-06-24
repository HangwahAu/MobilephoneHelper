package com.example.oukenghua.mobilephonehelper;

/**
 * Created by oukenghua on 2017/6/24.
 */

public class Item {

    private String itemName;
    private String itemContent;
    private int itemImageId;

    public Item(String itemName,String itemContent,int itemImageId){

        this.itemName = itemName;
        this.itemContent = itemContent;
        this.itemImageId = itemImageId;

    }

    public String getItemName() {
        return itemName;
    }

    public String getItemContent() {
        return itemContent;
    }

    public int getItemImageId() {
        return itemImageId;
    }

    public static Item[] items = {
            new Item("已下载应用","查看已下载的应用程序",R.drawable.download),
            new Item("应用更新","您有应用可更新",R.drawable.update),
            new Item("空间清理","一键清理，释放更多空间",R.drawable.clean),
            new Item("应用卸载","卸载已安装应用",R.drawable.uninstall),
            new Item("应用同步助手","同步应用列表，换机刷机都不怕",R.drawable.syn),
            new Item("网络诊断","检查您的网络状况",R.drawable.check),};
}
