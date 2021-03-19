package entity.media;

import dao.media.MediaDAO;
import entity.db.AIMSDB;
import utils.Utils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * The general media class, for another media it can be done by inheriting this class
 * @author nguyenlm
 */
public class Media {

    private static Logger LOGGER = Utils.getLogger(Media.class.getName());


    // common coupling

    protected Statement stm;
    protected int id;
    protected String title;
    protected String category;
    protected int value; // the real price of product (eg: 450)
    protected int price; // the price which will be displayed on browser (eg: 500)
    protected int quantity;
    protected String type;
    protected String imageURL;
    protected boolean rushSupported;

    public Media() throws SQLException{
        stm = AIMSDB.getConnection().createStatement();
    }


    // Data coupling
    public Media (int id, String title, String category, int price, int quantity, String type) throws SQLException{
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    // Data coupling
    public Media(int id, String title, int quantity, String category, String imageUrl, int price, String type) throws SQLException {
        this(id, title, category, price, quantity, type);
        this.imageURL = imageUrl;
    }

    //OCP: Phụ thuộc vào MediaDAO
    public int getQuantity() throws SQLException {
        int updated_quantity = new MediaDAO().getMediaById(id).quantity;
        this.quantity = updated_quantity;
        return updated_quantity;
    }

    // getter and setter 
    public int getId() {
        return this.id;
    }

// Data coupling
    private Media setId(int id){
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    // Data coupling
    public Media setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    // Data coupling
    public Media setCategory(String category) {
        this.category = category;
        return this;
    }

    public int getPrice() {
        return this.price;
    }

    // Data coupling
    public Media setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getImageURL(){
        return this.imageURL;
    }

    // Data coupling
    public Media setMediaURL(String url){
        this.imageURL = url;
        return this;
    }

    // Data coupling
    public Media setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getType() {
        return this.type;
    }

    // Data coupling
    public Media setType(String type) {
        this.type = type;
        return this;
    }

    //communicational cohesion: một vài phương thức dùng dung thuộc tính
}