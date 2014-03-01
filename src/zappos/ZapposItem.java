/**
 * Author: Rohan D. Shah, MS in CS student at utah state university
 * 
 * Description: 
 * ZapposItem is a class that represents a single item from Zappos.com.  
 * 
 */

package zappos;

public class ZapposItem 
{

    private String productId, brandName, productName, thumbnailImageUrl, 
                   originalPrice, price, percentOff, productUrl, defaultImageUrl;


    public ZapposItem(String productName, String percentOff, String productId, String brandName) 
    {
        this.productName = productName;
        this.percentOff = percentOff;
        this.productId = productId;
        this.brandName = brandName;
    }

    public ZapposItem() 
    {
        this.productId = "";
        this.productName = "";
        this.brandName = "";
        this.originalPrice = "";
        this.percentOff = "0%";
        this.price = "";
        this.productUrl = "";
    }

    // setters and getters

    // Product Name
    public String getProductName() 
    {
        return productName;
    }

    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    // Percent Off
    public String getPercentOff() 
    {
        return percentOff;
    }

    public void setPercentOff(String percentOff) 
    {
        this.percentOff = percentOff;
    }	

    // Default Image URL
    public String getDefaultImageUrl() 
    {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) 
    {
        this.defaultImageUrl = defaultImageUrl;
    }

    // Thumbnail Image URL
    public String getThumbnailImageUrl() 
    {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) 
    {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    // Product ID
    public String getProductId() 
    {
        return productId;
    }

    public void setProductId(String productId) 
    {
        this.productId = productId;
    }
    // Brand Name
    public String getBrandName() 
    {
        return brandName;
    }

    public void setBrandName(String brandName) 
    {
        this.brandName = brandName;
    }

    // Price
    public String getPrice() 
    {
        return price;
    }

    public void setPrice(String price) 
    {
        this.price = price;
    }

    // Original Price
    public String getOriginalPrice() 
    {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) 
    {
        this.originalPrice = originalPrice;
    }

    // Product URL
    public String getProductUrl() 
    {
        return productUrl;
    }

    public void setProductUrl(String productUrl) 
    {
        this.productUrl = productUrl;
    }

    @Override
    public String toString() 
    {
        return brandName + " - " + productName + "\n" + price + " - " + percentOff + " off\n" + productUrl;
    }
}
