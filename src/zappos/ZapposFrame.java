/**
 * Author: Rohan D. Shah, MS in CS student at utah state university
 * 
 * Description: 
 * ZapposFrame is a class that creates a frame and a panel to display information about a specific item from Zappos.com.  
 * It allows the user to search for an item, input their budget and number of items they want to buy and get suggestions
 * from the application about the combinations of the items in the search results, which they can buy.
 * 
 */

package zappos;

import java.awt.*;
import java.text.*;
import java.net.URL;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class ZapposFrame extends JFrame 
{
    ZapposDetailsPanel panel;
    int LIMIT;
    public ZapposFrame() 
    {
        super("Default");
    }

    public ZapposFrame(String s, int lim) 
    {
        super(s);
        LIMIT=lim;
        panel = new ZapposDetailsPanel(null);

        setContentPane(panel);

        setSize(1000, 1000);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS ); 
        scrollPane.setPreferredSize(new Dimension(600,600)); 
        panel.add(scrollPane);*/

        setVisible(true);

    }

    // custom panel for the frame
private class ZapposDetailsPanel extends JPanel
{
    // to store the items 
    private ZapposItem item[];
    
    // to display the image of the product
    private BufferedImage image;
    private JTextField searchField;                
    private JTextField numberOfItemsToBuy;
    private JTextField budget;
    private JButton linkButton, searchButton, suggestMeButton;

    public ZapposDetailsPanel(URL url) 
    {

        try {                
                if (image != null)
                        image = ImageIO.read(url);
        } catch (IOException ex) {
                System.out.println("EXCEPTION " + ex);
        }

        setLayout(null);
        setBackground(Color.WHITE);

        // initialize the ZapposItem
        item = new ZapposItem[LIMIT];
        for(int j=0 ; j<item.length ; j++)
        {
            item[j] = new ZapposItem();
            item[j].setPrice("-1");
        }
        // setup the link button that will take the user to the item on zappos.com
        linkButton = new JButton("See it on Zappos.com");
        linkButton.setBounds(260, 196, linkButton.getWidth(), linkButton.getHeight());
        linkButton.setVisible(false);


        linkButton.addActionListener(
                new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        // the parameter can be any item
                        goToZapposDotCom(item[0]);
                    }
                });

        // setup the search field
        searchField = new JTextField();
        searchField.setVisible(true);
        searchField.setPreferredSize(new Dimension(200, 30));

        // add action for 'Enter' key
        searchField.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e)
            {
                try {
                    searchForItemAndUpdatePanel(searchField.getText());
                } catch (IOException ex) {
                    Logger.getLogger(ZapposFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                    repaint();
            }
        }); 

        // setup the search button
        searchButton = new JButton("Search Zappos");
        searchButton.setVisible(true);

        //Add action listener to button
        searchButton.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e)
            {
                try {
                    searchForItemAndUpdatePanel(searchField.getText());
                } catch (IOException ex) {
                    Logger.getLogger(ZapposFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                    repaint();
            }
        });    
        budget = new JTextField("enter your budget",8);
        budget.setVisible(false);
        budget.setPreferredSize(new Dimension(200, 30));
     
       
        // setup the search button
        suggestMeButton = new JButton("Suggest Combinations");
        suggestMeButton.setVisible(false);
        suggestMeButton.setEnabled(false);
        //Add action listener to button
        suggestMeButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                if(budget.getText()!="" && numberOfItemsToBuy.getText()!="")
                    new suggestMe().suggestMeItems(item, budget.getText(), numberOfItemsToBuy.getText());
                else
                    suggestMeButton.setEnabled(false);
                    repaint();
            }
        });

        numberOfItemsToBuy = new JTextField("Enter the items to buy",4);
        numberOfItemsToBuy.setVisible(false);
        numberOfItemsToBuy.setPreferredSize(new Dimension(200, 30));
        
        // add all the components to the panel

        this.add(linkButton);
        this.add(searchField);
        this.add(searchButton);
        this.add(suggestMeButton);
        this.add(budget);
        this.add(numberOfItemsToBuy);
        /*JScrollPane scrollPane = new JScrollPane(panel,
ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
*/
    }

@Override
protected void paintComponent(Graphics g) 
{

    super.paintComponent(g);
    
    // for placing elements on the panel
    int x = 260;
    int y = 148;
    int imgy= 50;
    int imgx = 10;
    // if it is the initial time to paint, display a message to search
    for(int j=0;j<item.length;j++){
    if (item[j].getPrice().equals("-1")) 
    {
        g.drawString("Search for an item from Zappos.com", 135, 240);
    } 
    else if (!item[j].getProductName().equals("")) 
    {
        suggestMeButton.setVisible(true);
        try {
            image = ImageIO.read(new URL(item[j].getDefaultImageUrl()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ZapposFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ZapposFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
            if (image != null)
                    x = image.getWidth() + 20;

            // reposition the image based on it's height
            if (image.getHeight() == 320)
                    g.drawImage(image, imgx, imgy, null);
            else
                    g.drawImage(image, imgx, imgy+30, null);

            // draw the brand name
        g.drawString(item[j].getBrandName(), x, y);


        // draw the product name, if it's too long, draw a substring of it plus '...'
        if (item[j].getProductName().length() > 32) 
                g.drawString(item[j].getProductName().substring(0, 32) + " ...", x, y + 24);
        else
                g.drawString(item[j].getProductName(), x, y + 24);

        // if the item is discounted currently, draw the original price with a strikethrough
        if (!item[j].getPrice().equals(item[j].getOriginalPrice())) {
                AttributedString origPrice = new AttributedString(item[j].getOriginalPrice());

        origPrice.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON,0, item[j].getPrice().length());

            g.drawString(origPrice.getIterator(), x, y + 48);

            g.setColor(Color.RED);
            g.drawString(item[j].getPrice(), x + 60, y + 48);
            g.setColor(Color.BLACK);

        } 
        else if (!item[j].getPrice().equals("")) 
        {
            g.setColor(Color.RED);
            g.drawString(item[j].getPrice(), x, y + 48);
            g.setColor(Color.BLACK);
        }

        // if there is currently a discount, draw the percent off, if not, skip it
        // draw the link button to the item's URL if it's available
        if (!item[j].getPercentOff().equals("0%")) 
        {
            g.drawString(item[j].getPercentOff() + " off", x, y + 72);
            linkButton.setVisible(true);
            linkButton.setBounds(x - 5, y + 81, linkButton.getWidth(), linkButton.getHeight());
        } 
        else if (!item[j].getProductUrl().equals("")) 
        {
            linkButton.setVisible(true);
            linkButton.setBounds(x - 6, y + 58, linkButton.getWidth(), linkButton.getHeight());
        } 
        numberOfItemsToBuy.setVisible(true);
        budget.setVisible(true);

        if(!budget.getText().isEmpty() && !numberOfItemsToBuy.getText().isEmpty() )
        suggestMeButton.setEnabled(true);
         imgy+=image.getHeight()+10;
    } 
    else 
    {
        linkButton.setVisible(false);
        g.drawString("No results for \'" + searchField.getText() + "\'", 150, 240);
    }

    // position the other components properly on the panel

    y+=400;
}
searchField.setBounds(80, 5, searchField.getWidth(), searchField.getHeight());
searchButton.setBounds(285, 5, searchButton.getWidth(), searchButton.getHeight());
}

// searches for the item and displays them
private void searchForItemAndUpdatePanel(String searchTerm) throws IOException 
{
    item = Zappos.getZapposItemForSearchTerm(searchTerm);

    // load the image 
    try {
            URL url = new URL(item[1].getDefaultImageUrl());
            image = ImageIO.read(url);

    } catch (IOException ex) {

            System.out.println(ex.getMessage());
    }
} 

// to view the item on the zappos.com website
private void goToZapposDotCom(ZapposItem item) 
{
    try {
            Desktop.getDesktop().browse(new URL(item.getProductUrl()).toURI());

    } catch (Exception e) {

            System.out.println("Exception: " + e.getMessage());
    }
   }
}


/*private class CheckLabel extends JLabel 
{
    private final Polygon polygon;

    public CheckLabel() 
    {
        setPreferredSize(new Dimension(30, 30));

        int[] xPoints = new int[]{0, 6, 28, 7, 0, 0};
        int[] yPoints = new int[]{10, 25, 3, 20, 10, 10};

        polygon = new Polygon(xPoints, yPoints, xPoints.length);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D)g;

        // set the color to be dark green
        g2.setColor(new Color(37, 111, 39));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillPolygon(polygon);
    }
}

// same as above, but it draws a red 'x' to show that the email address field must contain
// a valid email address
private class XLabel extends JLabel 
{
    private final Polygon leftX, rightX;

    public XLabel() 
    {
        setPreferredSize(new Dimension(30, 30));

        int[] lXPoints = new int[]{6, 9, 24, 21};
        int[] lYPoints = new int[]{9, 6, 21, 24};

        int[] rXPoints = new int[]{6, 9, 24, 21};
        int[] rYPoints = new int[]{21, 24, 9, 6};

        leftX = new Polygon(lXPoints, lYPoints, lXPoints.length);
        rightX = new Polygon(rXPoints, rYPoints, rXPoints.length);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(new Color(159, 0, 4));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillPolygon(leftX);
        g2.fillPolygon(rightX);

    }
  }*/
}