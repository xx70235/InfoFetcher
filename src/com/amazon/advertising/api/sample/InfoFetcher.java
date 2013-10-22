package com.amazon.advertising.api.sample;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.widgets.ToolBar;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class InfoFetcher
{

    protected Shell shlApi;
    private Text text;
    private List lsCategory;
    private ArrayList<String> categories;
    private String keywords;
    private HashSet<String> attributes;
    private List lsSeletedAttr;

    /**
     * Launch the application.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            InfoFetcher window = new InfoFetcher();
            window.open();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Open the window.
     */
    public void open()
    {
        Display display = Display.getDefault();
        initial();
        createContents();
        shlApi.open();
        shlApi.layout();
        while (!shlApi.isDisposed())
        {
            if (!display.readAndDispatch())
            {
                display.sleep();
            }
        }
    }

    protected void initial()
    {
        categories = new ArrayList<String>();
        attributes = new HashSet<String>();
    }

    /**
     * Create contents of the window.
     */
    protected void createContents()
    {
        shlApi = new Shell();
        shlApi.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 14, SWT.NORMAL));
        shlApi.setSize(797, 607);
        shlApi.setText("API\u4FE1\u606F\u83B7\u53D6");
        shlApi.setLayout(new BorderLayout(0, 0));

        ToolBar toolBar = new ToolBar(shlApi, SWT.FLAT | SWT.RIGHT);
        toolBar.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 15, SWT.NORMAL));
        toolBar.setLayoutData(BorderLayout.NORTH);

        ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
        toolItem.setText("\u5F00\u59CB");

        ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
        toolItem_1.setText("\u6682\u505C");

        ToolItem toolItem_2 = new ToolItem(toolBar, SWT.NONE);
        toolItem_2.setText("\u505C\u6B62");

        TabFolder tabFolder = new TabFolder(shlApi, SWT.NONE);
        tabFolder.setLayoutData(BorderLayout.CENTER);

        TabItem tbtmAmazon = new TabItem(tabFolder, 0);
        tbtmAmazon.setText("Amazon");

        Composite composite = new Composite(tabFolder, SWT.NONE);
        tbtmAmazon.setControl(composite);

        Group group = new Group(composite, SWT.NONE);
        group.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 16, SWT.NORMAL));
        group.setText("\u58F9");
        group.setBounds(22, 10, 203, 478);

        lsCategory = new List(group, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        lsCategory.setSelection(1);
        lsCategory.setBounds(11, 65, 182, 382);
        lsCategory.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
        lsCategory.setItems(new String[] { "All", "Apparel", "Appliances",
                "Automotive", "Baby", "Beauty", "Books", "Electronics",
                "Grocery", "HealthPersonalCare", "Home", "HomeImprovement",
                "Jewelry", "KindleStore", "Miscellaneous", "Music",
                "OfficeProducts", "PetSupplies", "Photo", "Shoes", "Software",
                "SportingGoods", "Toys", "Video", "VideoGames", "Watches" });

        Label label = new Label(group, SWT.NONE);
        label.setBounds(46, 28, 106, 35);
        label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 15, SWT.NORMAL));
        label.setText("\u4EA7\u54C1\u7C7B\u522B\uFF1A");

        Group group_1 = new Group(composite, SWT.NONE);
        group_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 16, SWT.NORMAL));
        group_1.setText("\u8D30");
        group_1.setBounds(242, 11, 512, 117);

        Label label_1 = new Label(group_1, SWT.NONE);
        label_1.setText("\u5173\u952E\u5B57\uFF1A");
        label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 15, SWT.NORMAL));
        label_1.setBounds(22, 48, 80, 35);

        text = new Text(group_1, SWT.BORDER);
        text.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 14, SWT.NORMAL));
        text.setBounds(108, 48, 376, 35);

        Group group_2 = new Group(composite, SWT.NONE);
        group_2.setText("\u53C1");
        group_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 16, SWT.NORMAL));
        group_2.setBounds(242, 154, 512, 255);

        Label label_2 = new Label(group_2, SWT.NONE);
        label_2.setText("\u53EF\u9009\u5C5E\u6027\uFF1A");
        label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 15, SWT.NORMAL));
        label_2.setBounds(76, 27, 108, 35);

        final List lsAvailAttr = new List(group_2, SWT.BORDER | SWT.V_SCROLL);
        lsAvailAttr.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
        lsAvailAttr.setItems(new String[] { "Tags", "Help", "ListMinimum",
                "VariationSummary", "VariationMatrix", "TransactionDetails",
                "VariationMinimum", "VariationImages", "PartBrandBinsSummary",
                "CustomerFull", "CartNewReleases", "ItemIds", "SalesRank",
                "TagsSummary", "Fitments", "Subjects", "Medium",
                "ListmaniaLists", "PartBrowseNodeBinsSummary", "TopSellers",
                "Request", "HasPartCompatibility", "PromotionDetails",
                "ListFull", "Small", "Seller", "OfferFull", "Accessories",
                "VehicleMakes", "MerchantItemAttributes", "TaggedItems",
                "VehicleParts", "BrowseNodeInfo", "ItemAttributes",
                "PromotionalTag", "VehicleOptions", "ListItems", "Offers",
                "TaggedGuides", "NewReleases", "VehiclePartFit",
                "OfferSummary", "VariationOffers", "CartSimilarities",
                "Reviews", "ShippingCharges", "ShippingOptions",
                "EditorialReview", "CustomerInfo", "PromotionSummary",
                "BrowseNodes", "PartnerTransactionDetails", "VehicleYears",
                "SearchBins", "VehicleTrims", "Similarities",
                "AlternateVersions", "SearchInside", "CustomerReviews",
                "SellerListing", "OfferListings", "Cart",
                "TaggedListmaniaLists", "VehicleModels", "ListInfo", "Large",
                "CustomerLists", "Tracks", "CartTopSellers", "Images",
                "Variations", "RelatedItems", "Collections", "Reviews" });
        lsAvailAttr.setBounds(36, 68, 168, 155);

        Button btnAddAttrAll = new Button(group_2, SWT.NONE);
        btnAddAttrAll.setBounds(232, 163, 49, 27);
        btnAddAttrAll.setText(">>");

        Button btnRemoveAtrrAll = new Button(group_2, SWT.NONE);
        btnRemoveAtrrAll.setText("<<");
        btnRemoveAtrrAll.setBounds(232, 196, 49, 27);

        lsSeletedAttr = new List(group_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        lsSeletedAttr.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
        lsSeletedAttr.setItems(new String[] {"ItemAttributes", "Images", "EditorialReview", "Reviews"});
        lsSeletedAttr.setBounds(311, 68, 168, 155);

        Label label_3 = new Label(group_2, SWT.NONE);
        label_3.setText("\u5DF2\u9009\u5C5E\u6027\uFF1A");
        label_3.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 15, SWT.NORMAL));
        label_3.setBounds(348, 27, 108, 35);

        Button btnAddAtrr = new Button(group_2, SWT.NONE);
        btnAddAtrr.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e)
            {

              
                lsSeletedAttr.removeAll();
                for (String str : attributes)
                {
                    lsSeletedAttr.add(str);
                }
            }
        });
        btnAddAtrr.setText(">");
        btnAddAtrr.setBounds(232, 68, 49, 27);

        Button btnRemoveAtrr = new Button(group_2, SWT.NONE);
        btnRemoveAtrr.setText("<");
        btnRemoveAtrr.setBounds(232, 101, 49, 27);

        Group group_3 = new Group(composite, SWT.NONE);
        group_3.setText("\u8086");
        group_3.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 16, SWT.NORMAL));
        group_3.setBounds(242, 425, 512, 63);

        Button btnBegin = new Button(group_3, SWT.NONE);
        btnBegin.addSelectionListener(new BeginAction());
        btnBegin.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 15, SWT.NORMAL));
        btnBegin.setBounds(49, 26, 80, 27);
        btnBegin.setText("\u5F00\u59CB");

        Button btnPause = new Button(group_3, SWT.NONE);
        btnPause.setText("\u6682\u505C");
        btnPause.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 15, SWT.NORMAL));
        btnPause.setBounds(219, 26, 80, 27);

        Button btnStop = new Button(group_3, SWT.NONE);
        btnStop.setText("\u505C\u6B62");
        btnStop.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 15, SWT.NORMAL));
        btnStop.setBounds(389, 26, 80, 27);

    }

    private class BeginAction extends SelectionAdapter
    {
        public void widgetSelected(SelectionEvent e)
        {
            categories.clear();
            if (lsCategory.getSelection().length == 0)
            {
                categories.add("All");
            }
            else
            {
                for (String str : lsCategory.getSelection())
                {
                    categories.add(str);
                }
            }
            lsSeletedAttr.selectAll();
            for (String str : lsSeletedAttr.getSelection())
            {
                attributes.add(str);

            }
            
            String keyword = text.getText();

            AmazonAPI api = new AmazonAPI();
            java.util.List<String> asinList = new ArrayList<String>();
            asinList = api.getASINbyKeyword(categories, keyword);
            api.getItemInfo(asinList, attributes);
        }

        // TODO:categories ºÍ attributes
        // ÒÑ¾­ÉèÖÃºÃ£¬°ÑtextÀïµÄ¶«Î÷Á¬Í¬ÒÔÉÏÁ½Õß´«µ½AmazonAPIÖÐ£¬Íê³É»ù±¾¹¦ÄÜ²âÊÔ
    }

}
