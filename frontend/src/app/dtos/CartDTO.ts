import { Product } from "../models/product";

export interface CartDTO{
    cartId: number;
    cartItemId: number;
    product: Product;
    quantity: number;
    subtotal: number;
}