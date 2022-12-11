import { Product } from "../models/product";

export interface CartListDTO{
    cartId: number;
    cartItemId: number;
    product: Product;
    quantity: number;
    subtotal: number;
}