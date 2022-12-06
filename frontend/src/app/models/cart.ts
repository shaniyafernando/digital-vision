import { CartItem } from "./cartItem";
import { User } from "./User";

export interface Cart{
    id: number;
    cartItems: CartItem[];
    total: number;
    user:User;
}