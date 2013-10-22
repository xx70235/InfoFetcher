package com.apis.models;

public class AmazonItems
{
    public String getBinding()
    {
        return binding;
    }
    public void setBinding(String binding)
    {
        this.binding = binding;
    }
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public String getBrand()
    {
        return brand;
    }
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    public String getLabel()
    {
        return label;
    }
    public void setLabel(String label)
    {
        this.label = label;
    }
    public String[] getFeature()
    {
        return feature;
    }
    public void setFeature(String[] feature)
    {
        this.feature = feature;
    }
    public String getFormattedPrice()
    {
        return formattedPrice;
    }
    public void setFormattedPrice(String formattedPrice)
    {
        this.formattedPrice = formattedPrice;
    }
    public String getManufacturer()
    {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }
    public String getPublisher()
    {
        return publisher;
    }
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }
    public String getStudio()
    {
        return studio;
    }
    public void setStudio(String studio)
    {
        this.studio = studio;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getReviewsIFrameURL()
    {
        return reviewsIFrameURL;
    }
    public void setReviewsIFrameURL(String reviewsIFrameURL)
    {
        this.reviewsIFrameURL = reviewsIFrameURL;
    }
    public boolean isHasReview()
    {
        return hasReview;
    }
    public void setHasReview(boolean hasReview)
    {
        this.hasReview = hasReview;
    }
    public String getEditorialReviews()
    {
        return editorialReviews;
    }
    public void setEditorialReviews(String editorialReviews)
    {
        this.editorialReviews = editorialReviews;
    }
    public int getAmount()
    {
        return amount;
    }
    public void setAmount(int amount)
    {
        this.amount = amount;
    }
    public String getMidImage()
    {
        return midImage;
    }
    public void setMidImage(String midImage)
    {
        this.midImage = midImage;
    }
    public String getLargeImage()
    {
        return largeImage;
    }
    public void setLargeImage(String largeImage)
    {
        this.largeImage = largeImage;
    }
    public String getSmallImage()
    {
        return smallImage;
    }
    public void setSmallImage(String smallImage)
    {
        this.smallImage = smallImage;
    }
    public String getProductGroup()
    {
        return productGroup;
    }
    public void setProductGroup(String productGroup)
    {
        this.productGroup = productGroup;
    }
    String itemId;
    String smallImage;
    String midImage;
    String largeImage;
    String binding;
    String author;//可选
    String brand;
    String label;
    String[] feature;//可选，多个
    String currencyCode;
    String formattedPrice;
    String manufacturer;
    String productGroup;
    String productTypeName;
    String publisher;
    String studio;
    String title;
    String reviewsIFrameURL;
    boolean hasReview;//在Reviews结点下
    String editorialReviews;//EditorialReviews下的content结点中的内容
    String customerReviews;
    int amount;
    public String getItemId()
    {
        return itemId;
    }
    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }
    public String getCurrencyCode()
    {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }
    public String getProductTypeName()
    {
        return productTypeName;
    }
    public void setProductTypeName(String productTypeName)
    {
        this.productTypeName = productTypeName;
    }
    public String getCustomerReviews()
    {
        return customerReviews;
    }
    public void setCustomerReviews(String customerReviews)
    {
        this.customerReviews = customerReviews;
    }
    
   
    
}
