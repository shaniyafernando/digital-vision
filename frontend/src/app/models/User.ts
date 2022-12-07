import { Address } from "./Address";
import { Cart } from "./cart";
import { WishList } from "./WishList";

export interface User{
    id: number;
    username: string;
    email: string;
    password: string;
    role: string;
    cartId: number;
    wishListId: number;
}