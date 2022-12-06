import { Cart } from "./cart";
import { Product } from "./product";

export interface CartItem{
    id: number;
    product: Product;
    quantityAddedToCart: number;
    cart: Cart;
}