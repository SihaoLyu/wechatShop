package ufl.edu.wechatShop.exceptions;

public class ShopOperationException extends RuntimeException{
    private static final long serialVersionUID = 5520497484486965982L;

    public ShopOperationException(String msg) {
        super(msg);
    }
}
